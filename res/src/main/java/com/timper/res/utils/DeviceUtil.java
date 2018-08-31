package com.timper.res.utils;

import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.timper.res.utils.context.App;

/**
 * Created by tangpeng.yang.o on 2017/8/15.
 */

public class DeviceUtil {

  /**
   * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
   */
  public static int dip2px(float dpValue) {
    final float scale = App.context().getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  /**
   * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
   */
  public static int px2dip(float pxValue) {
    final float scale = App.context().getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  /**
   * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
   */
  public static int px2sp(float pxValue) {
    float fontScale = App.context().getResources().getDisplayMetrics().scaledDensity;
    return (int) (pxValue / fontScale + 0.5f);
  }

  /**
   * 根据手机的分辨率从 sp 的单位 转成为 px
   */
  public static int sp2px(float spValue) {
    float fontScale = App.context().getResources().getDisplayMetrics().scaledDensity;
    return (int) (spValue * fontScale + 0.5f);
  }

  /**
   * 获取屏幕密度
   */
  public static float getDensity() {
    DisplayMetrics dm = App.context().getResources().getDisplayMetrics();
    return dm.density;
  }

  /**
   * 获取屏幕宽度
   */
  public static int getScreenW() {
    DisplayMetrics dm = new DisplayMetrics();
    dm = App.context().getResources().getDisplayMetrics();
    int w = dm.widthPixels;
    return w;
  }

  /**
   * 获取屏幕高度
   */
  public static int getScreenH() {
    DisplayMetrics dm = new DisplayMetrics();
    dm = App.context().getResources().getDisplayMetrics();
    int h = dm.heightPixels;
    return h;
  }

  public static int getNavigationBarHeight() {
    Resources resources = App.context().getResources();
    int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
    int height = resources.getDimensionPixelSize(resourceId);
    return height;
  }

  public static int getStatusHeight() {
    Resources resources = App.context().getResources();
    int statusHeight = -1;
    //获取status_bar_height资源的ID
    int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      //根据资源ID获取响应的尺寸值
      statusHeight = resources.getDimensionPixelSize(resourceId);
    }
    return statusHeight;
  }

  public void restartApp() {
    final Intent intent = App.context().getPackageManager().getLaunchIntentForPackage(App.context().getPackageName());
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    App.context().startActivity(intent);
  }
}
