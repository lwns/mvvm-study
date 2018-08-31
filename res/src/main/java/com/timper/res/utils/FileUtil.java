package com.timper.res.utils;

import android.app.Activity;
import android.os.Environment;
import java.io.File;

/**
 * User: yang.wang
 * Date: 16/04/2018
 * Description:文件创建及删除
 * FIXME
 */
public class FileUtil {
  public static String getExternalFolderPath() {
    String path = Environment.getExternalStorageDirectory() + "/NioSteward/";
    File file = new File(path);
    if (file.mkdirs()) {
      return path;
    }
    return path;
  }

  public static String getCacheFolderPath(Activity activity) {
    String cachePath = activity.getCacheDir() + "/picture_cache/";
    File file = new File(cachePath);
    if (file.mkdirs()) {
      return cachePath;
    }
    return cachePath;
  }

  public static void clearImgCache(Activity activity) {
    //PictureFileUtils.deleteCacheDirFile(activity);
    // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
    //RxPermissions permissions = new RxPermissions(activity);
    //permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
    //  @Override public void onSubscribe(Disposable d) {
    //  }
    //
    //  @Override public void onNext(Boolean aBoolean) {
    //    if (aBoolean) {
    //      PictureFileUtils.deleteCacheDirFile(activity);
    //    } else {
    //      Toast.makeText(activity, activity.getResources().getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
    //    }
    //  }
    //
    //  @Override public void onError(Throwable e) {
    //    e.printStackTrace();
    //  }
    //
    //  @Override public void onComplete() {
    //  }
    //});
  }
}
