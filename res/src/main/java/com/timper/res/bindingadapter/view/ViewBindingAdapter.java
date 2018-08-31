package com.timper.res.bindingadapter.view;

import android.databinding.BindingAdapter;
import android.view.MotionEvent;
import android.view.View;
import com.timper.res.bindingadapter.action.Command;
import com.timper.res.bindingadapter.action.ResponseCommand;

/**
 * User: tangpeng.yang
 * Date: 20/03/2018
 * Description:
 * FIXME
 */
public final class ViewBindingAdapter {

  @BindingAdapter({ "onClick" }) public static void clickCommand(View view, final Command clickCommand) {
    view.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (clickCommand != null) {
          clickCommand.execute();
        }
      }
    });
  }

  @BindingAdapter({ "requestFocus" }) public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
    if (needRequestFocus) {
      view.setFocusableInTouchMode(true);
      view.requestFocus();
    } else {
      view.clearFocus();
    }
  }

  @BindingAdapter({ "onFocusChange" })
  public static void onFocusChangeCommand(View view, final Command<Boolean> onFocusChangeCommand) {
    view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override public void onFocusChange(View v, boolean hasFocus) {
        if (onFocusChangeCommand != null) {
          onFocusChangeCommand.execute(hasFocus);
        }
      }
    });
  }

  @BindingAdapter({ "onTouch" })
  public static void onTouchCommand(View view, final ResponseCommand<MotionEvent, Boolean> onTouchCommand) {
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        if (onTouchCommand != null) {
          return onTouchCommand.execute(event);
        }
        return false;
      }
    });
  }
}
