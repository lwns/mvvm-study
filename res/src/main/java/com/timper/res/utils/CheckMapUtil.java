package com.timper.res.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: yang.wang
 * Date: 29/03/2018
 * Description:检测手机上是否安装地图软件
 * FIXME
 */
public class CheckMapUtil {
  /**
   * 检查手机上是否安装了指定的软件
   */
  public static boolean isAvilible(Context context, String packageName) {
    //获取packagemanager
    final PackageManager packageManager = context.getPackageManager();
    //获取所有已安装程序的包信息
    List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
    //用于存储所有已安装程序的包名
    List<String> packageNames = new ArrayList<String>();
    //从pinfo中将包名字逐一取出，压入pName list中
    if (packageInfos != null) {
      for (int i = 0; i < packageInfos.size(); i++) {
        String packName = packageInfos.get(i).packageName;
        packageNames.add(packName);
      }
    }
    //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
    return packageNames.contains(packageName);
  }

  /***
   * 是否安装腾讯地图
   * @return
   */
  public static boolean isHaveTencentMap() {
    try {
      if (!new File("/data/data/" + "com.tencent.map").exists()) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * 调用腾讯地图app路径规划
   *
   * @param from 选 出发地址
   * @param fromcoord 选 出发经纬度   移动端如果起点名称和起点坐标均未传递，则使用当前定位位置作为起点 如 39.9761,116.3282
   * @param to 必 目标地址
   * @param tocoord 必 目标经纬度 39.9761,116.3282
   * @param policy 选  本参数取决于type参数的取值
   * 公交：type=bus，policy有以下取值 0：较快捷 1：少换乘 2：少步行 3：不坐地铁
   * 驾车：type=drive，policy有以下取值 0：较快捷 1：无高速 2：距离 policy的取值缺省为0
   * @param coord_type 选 坐标类型，取值如下：1 GPS  2 腾讯坐标（默认）  如果用户指定该参数为非腾讯地图坐标系，则URI API自动进行坐标处理，以便准确对应到腾讯地图底图上。
   * @param type 必 公交：bus  驾车：drive  步行：walk（仅适用移动端）
   * @param referer 必  调用来源，一般为您的应用名称，为了保障对您的服务，请务必填写！
   */
  public static void openMap_TX(Context context, @NonNull String type, String coord_type, String from, String fromcoord,
      @NonNull String to, @NonNull String tocoord, String policy, @NonNull String referer) {
    StringBuffer stringBuffer = new StringBuffer("qqmap://map/").append("routeplan?")
        .append("type=")
        .append(type)
        .append("&to=")
        .append(to)
        .append("&tocoord=")
        .append(tocoord)
        .append("&referer=")
        .append(referer);
    if (!TextUtils.isEmpty(from)) {
      stringBuffer.append("&from=").append(from);
    }
    if (!TextUtils.isEmpty(fromcoord)) {
      stringBuffer.append("&fromcoord=").append(fromcoord);
    }
    if (!TextUtils.isEmpty(policy)) {
      stringBuffer.append("&policy=").append(policy);
    }
    if (!TextUtils.isEmpty(coord_type)) {
      stringBuffer.append("&coord_type=").append(coord_type);
    }
    Intent intent = new Intent();
    intent.setData(Uri.parse(stringBuffer.toString()));
    context.startActivity(intent);
  }

  /**
   * 调用百度地图app路径规划
   *
   * @param origin 起始地址
   * @param destination 终点地址 必填
   * @param mode 出行方式       必填
   */
  public static void openMap_BD(Context context, String origin, String destination, String mode) {
    StringBuffer stringBuffer = new StringBuffer("baidumap://map/").append("direction?")
        .append("destination=")
        .append(destination)
        .append("&mode=")
        .append(mode);
    if (!TextUtils.isEmpty(origin)) {
      stringBuffer.append("&origin=").append(origin);
    }
    Intent intent = new Intent();
    intent.setData(Uri.parse(stringBuffer.toString()));
    context.startActivity(intent);
  }

  /**
   * 调用高德地图app路径规划
   *
   * @param sourceAppNmae 应用名称 必填
   * @param slat 起点纬度
   * @param slon 起点经度
   * @param sname 起点名称
   * @param dlat 终点纬度    必填
   * @param dlon 终点经度    必填
   * @param dname 终点名称
   * @param dev 起终点是否偏移 0 ，1 必填
   * @param t 出行方式 必填
   */
  public static void openMap_GD(Context context, String sourceAppNmae, String slat, String slon, String sname, String dlat,
      String dlon, String dname, String dev, String t) {
    StringBuffer stringBuffer = new StringBuffer("amapuri://").append("route/plan/?")
        .append("sourceApplication=")
        .append(sourceAppNmae)
        .append("&dlat=")
        .append(dlat)
        .append("&dlon=")
        .append(dlon)
        .append("&dev=")
        .append(dev)
        .append("&t=")
        .append(t);
    if (!TextUtils.isEmpty(slat)) {
      stringBuffer.append("&slat=").append(slat);
    }
    if (!TextUtils.isEmpty(slon)) {
      stringBuffer.append("&slon=").append(slon);
    }
    if (!TextUtils.isEmpty(sname)) {
      stringBuffer.append("&sname=").append(sname);
    }
    if (!TextUtils.isEmpty(dname)) {
      stringBuffer.append("&dname=").append(dname);
    }
    Intent intent = new Intent();
    intent.setData(Uri.parse(stringBuffer.toString()));
    intent.setAction(Intent.ACTION_VIEW);
    intent.addCategory(Intent.CATEGORY_DEFAULT);
    intent.setPackage("com.autonavi.minimap");
    context.startActivity(intent);
  }

  /**
   * 调用谷歌地图app路径规划
   *
   * @param lat 终点纬度 必填
   * @param lng 终点经度 必填
   * @param address 终点名称
   */
  public static void openMap_GG(Context context, String lat, String lng, String address) {
    StringBuffer stringBuffer = new StringBuffer("geo:").append(lat).append(",").append(lng);
    if (!TextUtils.isEmpty(address)) {
      stringBuffer.append("?q=").append(address);
    }
    Intent intent = new Intent();
    intent.setData(Uri.parse(stringBuffer.toString()));
    intent.setAction(Intent.ACTION_VIEW);
    intent.setPackage("com.google.android.apps.maps");
    context.startActivity(intent);
  }
}
