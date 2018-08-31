package com.timper.res.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.timper.res.utils.context.App;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片缓存处理
 *
 * @author Created by jz on 2017/3/26 16:13
 */
public class ImageUtil {

  static MediaScannerConnection sMediaScannerConnection;

  private ImageUtil() {
  }

  /*
       *  判断该Bitmap是否可以设置到BitmapFactory.Options.inBitmap上
       */
  public static boolean canUseForInBitmap(Bitmap bitmap, BitmapFactory.Options options) {
    // 在Android4.4以后，如果要使用inBitmap的话，只需要解码的Bitmap比inBitmap设置的小就行了，对inSampleSize没有限制
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      int width = options.outWidth;
      int height = options.outHeight;
      if (options.inSampleSize > 0) {
        width /= options.inSampleSize;
        height /= options.inSampleSize;
      }
      int byteCount = width * height * getBytesPerPixel(bitmap.getConfig());
      return byteCount <= bitmap.getAllocationByteCount();
    }
    // 在Android4.4之前，如果想使用inBitmap的话，解码的Bitmap必须和inBitmap设置的宽高相等，且inSampleSize为1
    return bitmap.getWidth() == options.outWidth && bitmap.getHeight() == options.outHeight && options.inSampleSize == 1;
  }

  //获取每个像素所占用的Byte数
  private static int getBytesPerPixel(Bitmap.Config config) {
    if (config == Bitmap.Config.ARGB_8888) {
      return 4;
    } else if (config == Bitmap.Config.RGB_565) {
      return 2;
    } else if (config == Bitmap.Config.ARGB_4444) {
      return 2;
    } else if (config == Bitmap.Config.ALPHA_8) {
      return 1;
    }
    return 1;
  }

  /**
   * 回收Bitmap内存
   *
   * @param bitmap 图片
   */
  public static void recycleBitmap(Bitmap bitmap) {
    if (bitmap != null && !bitmap.isRecycled()) {
      bitmap.recycle();
    }
  }

  public static void saveImage(Context context, Bitmap bitmap) {
    String dir = Environment.getExternalStorageDirectory().getPath() + "/images/";
    File file = new File(dir);
    if (!file.exists()) {
      file.mkdir();
    }

    String imagePath = dir + System.currentTimeMillis() + ".jpg";
    File imageFile = new File(imagePath);
    if (!imageFile.exists()) {
      try {
        imageFile.createNewFile();
      } catch (IOException e) {
      }
    }

    saveBitmap(bitmap, imagePath);
    insertImage(context, imagePath);
  }

  public static void saveBitmap(Bitmap bitmap, String filePath) {
    FileOutputStream out = null;
    try {
      out = new FileOutputStream(filePath);
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);//PNG
    } catch (Exception e) {
      Log.d("test", e.getMessage());
    } finally {
      try {
        if (out != null) {
          out.close();
        }
      } catch (IOException e) {
        Log.d("test", e.getMessage());
      }
    }
  }

  public static void insertImage(Context context, final String filePath) {
    sMediaScannerConnection =
        new MediaScannerConnection(App.context(), new MediaScannerConnection.MediaScannerConnectionClient() {
          @Override public void onMediaScannerConnected() {
            Log.d("test", "scannerConnected, scan local path:" + filePath);
            sMediaScannerConnection.scanFile(filePath, "image/*");
          }

          @Override public void onScanCompleted(String path, Uri uri) {
            Log.d("test", "scan complete");
            sMediaScannerConnection.disconnect();
          }
        });
    sMediaScannerConnection.connect();
  }

  //保存文件到指定路径
  public static boolean saveImageToGallery(Context context, Bitmap bmp) {
    // 首先保存图片
    String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "images";
    File appDir = new File(storePath);
    if (!appDir.exists()) {
      appDir.mkdir();
    }
    String fileName = System.currentTimeMillis() + ".jpg";
    File file = new File(appDir, fileName);
    try {
      FileOutputStream fos = new FileOutputStream(file);
      //通过io流的方式来压缩保存图片
      boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
      fos.flush();
      fos.close();

      //把文件插入到系统图库
      //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

      //保存图片后发送广播通知更新数据库
      Uri uri = Uri.fromFile(file);
      context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
      return isSuccess;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * 放大缩小图片
   */
  public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
    Bitmap newbmp = null;
    if (bitmap != null) {
      int width = bitmap.getWidth();
      int height = bitmap.getHeight();
      Matrix matrix = new Matrix();
      float scaleWidht = ((float) w / width);
      float scaleHeight = ((float) h / height);
      matrix.postScale(scaleWidht, scaleHeight);
      newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }
    return newbmp;
  }
}
