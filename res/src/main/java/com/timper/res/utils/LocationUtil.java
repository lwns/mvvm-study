package com.timper.res.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.timper.res.utils.context.App;
import com.timper.res.utils.permission.PermissionListener;
import com.timper.res.utils.permission.VomPermission;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by ning.li.o on 2017/11/10.
 */

public class LocationUtil implements LocationListener {

  private boolean isGPSEnabled;
  private boolean isPassiveEnabled;
  private boolean isNetworkEnabled;
  private Location location;
  private LocationManager locationManager;
  private UpdateLocationListener updateLocationListener;
  private String[] locations;
  private double minLocation = 0.0;

  public interface UpdateLocationListener {
    void onUpdateLocation(String[] locations);
  }

  public void checkPermission() {
    Log.d("location", "checkPermission");
    VomPermission.with(App.context())
        .requestCode(100)
        .permission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
        .callback(new PermissionListener() {
          @Override public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            Log.d("location", "onSucceed");
          }

          @Override public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            Log.d("location", "onFailed");
          }
        })
        .start();
  }

  public String[] getLocation() {//同步获取  不需要update
    return getLocation(false, null);
  }

  public String[] getLocation(UpdateLocationListener updateLocationListener) {//异步获取
    return getLocation(true, updateLocationListener);
  }

  @SuppressLint("MissingPermission")
  private String[] getLocation(boolean needRequestLocationUpdate, UpdateLocationListener updateLocationListener) {

    this.updateLocationListener = updateLocationListener;

    int MIN_TIME_BW_UPDATES = 1000;
    int MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;

    if (ContextCompat.checkSelfPermission(App.context(), Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      return null;
    }

    try {
      Log.d("location", "getLocation");
      locationManager = (LocationManager) App.context().getSystemService(LOCATION_SERVICE);

      isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

      isPassiveEnabled = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);

      isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

      if (isGPSEnabled || isNetworkEnabled) {// || isPassiveEnabled

        if (isNetworkEnabled && location == null) {//
          if (needRequestLocationUpdate) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, LocationUtil.this);
          }
          if (locationManager != null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
          }
        }

        // if GPS Enabled get lat/long using GPS Services
        if (isGPSEnabled && location == null) {//
          if (needRequestLocationUpdate) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, LocationUtil.this);
          }
          if (locationManager != null) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
          }
        }

        //                if (isPassiveEnabled && location == null) {
        //                    if (needRequestLocationUpdate) {
        //                        locationManager.requestLocationUpdates(
        //                                LocationManager.PASSIVE_PROVIDER,
        //                                MIN_TIME_BW_UPDATES,
        //                                MIN_DISTANCE_CHANGE_FOR_UPDATES, LocationUtil.this);
        //                    }
        //                    if (locationManager != null) {
        //                        location = locationManager
        //                                .getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        //                    }
        //                }

        if (location != null && (location.getLatitude() > minLocation) && (location.getLongitude() > minLocation)) {
          //通过接口获取城市名字
          locations = new String[2];
          locations[0] = location.getLatitude() + "";
          locations[1] = location.getLongitude() + "";
          Log.d("location", "getLocation-0--" + locations[0]);
          Log.d("location", "getLocation-1--" + locations[1]);
        }
      }
    } catch (Exception e) {
      Log.d("Exception", e.getMessage());
      e.printStackTrace();
    }

    if (locations != null && locations.length > 1) {
      return locations;
    }
    return null;
  }

  @Override public void onLocationChanged(Location location) {
    if (updateLocationListener != null) {
      if (location != null) {//need  要call出去
        String string = "onLocationChanged---纬度为：" + location.getLatitude() + ",经度为：" + location.getLongitude();

        Log.d("location", string);
        if (location.getLatitude() > minLocation && location.getLongitude() > minLocation) {
          locations = new String[2];
          locations[0] = location.getLatitude() + "";
          locations[1] = location.getLongitude() + "";
          updateLocationListener.onUpdateLocation(locations);
        }
      } else {
        Log.d("location", "空的");
      }
    }
  }

  @Override public void onStatusChanged(String provider, int status, Bundle extras) {

  }

  @Override public void onProviderEnabled(String provider) {

  }

  @Override public void onProviderDisabled(String provider) {

  }

  @SuppressLint("MissingPermission") public void removeUpdates() {
    if (locationManager != null) {
      locationManager.removeUpdates(LocationUtil.this);
    }
  }
}
