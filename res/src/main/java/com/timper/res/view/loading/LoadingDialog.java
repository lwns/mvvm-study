package com.timper.res.view.loading;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import com.timper.res.R;
import com.timper.res.view.dialog.Dialog;
import com.timper.res.view.dialog.DialogBuilder;

/**
 * @author : tangpeng.yang.o(tangpeng.yang.o@nio.com)
 * @Date: 2017-10-28 20:00
 * Description:
 * FIXME
 */

public class LoadingDialog extends Dialog {

  View view;

  private Integer level = 0;

  public LoadingDialog(Activity activity) {
    super(DialogBuilder.newDialog(activity).setGravity(Gravity.CENTER).setCancelable(false).setFullScreen(true));
  }

  public LoadingDialog(DialogBuilder builder) {
    super(builder);
  }

  @Override public synchronized void show() {
    synchronized (level) {
      if (!isShowing()) {
        view = LayoutInflater.from(context).inflate(R.layout.dlg_loading, contentContainer, true);
        super.show();
      }
    }
  }

  public synchronized void showNoFoucus() {
    synchronized (level) {
      if (!isShowing()) {
        view = LayoutInflater.from(context).inflate(R.layout.dlg_loading, contentContainer, true);
        view.setFocusable(false);
        view.setFocusableInTouchMode(false);
        super.show();
      }
    }
  }

  @Override public synchronized void dismiss() {
    synchronized (level) {
      if (isShowing()) {
        contentContainer.removeAllViews();
        super.dismiss();
      }
    }
  }
}
