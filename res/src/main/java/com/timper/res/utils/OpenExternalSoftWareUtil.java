/*
 * Copyright (c) 2012, Pandoranews Corporation, All Rights Reserved
 */
package com.timper.res.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.File;

/**
 * @author LiNing
 * @description 各种不同的打开文件的intent  比较全   里面对应的type可以根据需求改变  压缩文件需要下载对应的软件才可以打开
 */
public class OpenExternalSoftWareUtil {

  public static void openFile(Context context, String path) {

    if (StrUtil.isEmpty(path)) {
      return;
    }
    File file = new File(path);
    openFile(context, file);
  }

  public static void openFile(Context context, File file) {
    if (file != null && file.exists()) {

      try {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        // 获取文件file的MIME类型
        String type = getMIMEType(file);
        // 设置intent的data和Type属性。
        intent.setDataAndType(/* uri */Uri.fromFile(file), type);

        //            Bundle bundle = new Bundle();
        //            bundle.putString("filepath", path);
        //            intent.putExtras(bundle);

        Intent intentGo = Intent.createChooser(intent, "Open File");

        // 跳转
        if (context != null) {
          context.startActivity(intentGo);
        }
        // Intent.createChooser(intent, "请选择对应的软件打开该附件！");
      } catch (Exception e) {
        //Toast.makeText(App.context(), App.context().getString(R.string.app_common_no_software), Toast.LENGTH_LONG).show();
      }
    }
  }

  private static String getMIMEType(File file) {

    String type = "";
    String fName = file.getName();
    // 获取后缀名前的分隔符"."在fName中的位置。
    int dotIndex = fName.lastIndexOf(".");
    if (dotIndex < 0) {
      return type;
    }
        /* 获取文件的后缀名 */
    String end = fName.substring(dotIndex, fName.length()).toLowerCase();
    if (end == "") return type;
    // 在MIME和文件类型的匹配表中找到对应的MIME类型。
    for (int i = 0; i < MIME_MapTable.length; i++) {

      if (end.equals(MIME_MapTable[i][0])) type = MIME_MapTable[i][1];
    }
    return type;
  }

  // 可以自己根据需求添加
  private static String[][] MIME_MapTable = {
      // {后缀名，MIME类型}
      { ".doc", "application/msword" }, { ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
      { ".pdf", "application/pdf" }, { ".xls", "application/vnd.ms-excel" },
      { ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
      { ".ppt", "application/vnd.ms-powerpoint" },
      { ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" }, { ".avi", "video/x-msvideo" },
      { ".flv", "video/x-msvideo" }, { ".wmv", "video/x-msvideo" }, { ".3gp", "video/3gpp" }, { ".mpg", "video/*" },
      { ".mpe", "video/mpeg" }, { ".mpeg", "video/*" }, { ".mp4", "video/mp4" }, { ".vob", "video/*" }, { ".rmvb", "video/*" },
      { ".rm", "video/*" }, { ".zip", "application/x-zip-compressed" }, { ".rar", "application/x-zip-compressed" },
      { ".apk", "application/vnd.android.package-archive" }, { ".asf", "video/x-ms-asf" }, { ".bin", "application/octet-stream" },
      { ".bmp", "image/bmp" }, { ".c", "text/plain" }, { ".class", "application/octet-stream" }, { ".conf", "text/plain" },
      { ".cpp", "text/plain" }, { ".exe", "application/octet-stream" }, { ".gif", "image/gif" },
      { ".gtar", "application/x-gtar" }, { ".gz", "application/x-gzip" }, { ".h", "text/plain" }, { ".htm", "text/html" },
      { ".html", "text/html" }, { ".jar", "application/java-archive" }, { ".java", "text/plain" }, { ".jpeg", "image/jpeg" },
      { ".jpg", "image/jpeg" }, { ".js", "application/x-javascript" }, { ".log", "text/plain" }, { ".m3u", "audio/x-mpegurl" },
      { ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" }, { ".m4p", "audio/mp4a-latm" },
      { ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" }, { ".mov", "video/quicktime" }, { ".mp2", "audio/x-mpeg" },
      { ".mp3", "audio/x-mpeg" }, { ".mpc", "application/vnd.mpohun.certificate" }, { ".mpg4", "video/mp4" },
      { ".mpga", "audio/mpeg" }, { ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" }, { ".png", "image/png" },
      { ".pps", "application/vnd.ms-powerpoint" }, { ".prop", "text/plain" }, { ".rc", "text/plain" },
      { ".rtf", "application/rtf" }, { ".sh", "text/plain" }, { ".tar", "application/x-tar" },
      { ".tgz", "application/x-compressed" }, { ".txt", "text/plain" }, { ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
      { ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" }, { ".z", "application/x-compress" },
  };
}