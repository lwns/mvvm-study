package com.timper.res.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.timper.res.R;
import com.timper.res.utils.context.App;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/3/12
 */
public class AppToast {

  private static long oneTime = 0;
  private static long twoTime = 0;
  private static String oldMsg = "";
  private static Toast toast;

  public static void show(String message) {
    Context context = App.context();
    if (toast == null) {
      toast = new Toast(context);
    }
    getToastView(context, message);
    twoTime = System.currentTimeMillis();
    if (message.equals(oldMsg)) {
      if (twoTime - oneTime > Toast.LENGTH_SHORT) {
        toast.show();
      }
    } else {
      oldMsg = message;
      toast.show();
    }
    oneTime = twoTime;
  }

  private static void getToastView(Context context, String message) {
    View view = LayoutInflater.from(context).inflate(R.layout.widget_toast, null);
    TextView textView = (TextView) view.findViewById(R.id.tv_toast);
    textView.setGravity(Gravity.CENTER);
    toast.setView(view);
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.setDuration(Toast.LENGTH_SHORT);
    textView.setText(message);
  }

  public static void show(@StringRes int message) {
    show(App.context().getString(message));
  }
}
