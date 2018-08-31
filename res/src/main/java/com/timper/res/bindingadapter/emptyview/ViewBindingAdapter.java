package com.timper.res.bindingadapter.emptyview;

import android.databinding.BindingAdapter;
import com.timper.res.bindingadapter.action.Command;
import com.timper.res.utils.StrUtil;
import com.timper.res.view.EmptyLayout;

public final class ViewBindingAdapter {

  @BindingAdapter({ "onRetry" }) public static void retry(EmptyLayout emptyLayout, Command command) {
    emptyLayout.setOnRefreshListener(() -> {
      if (command != null) {
        command.execute();
      }
    });
  }

  @BindingAdapter(value = { "state", "info" }, requireAll = false)
  public static void setStatus(EmptyLayout emptyLayout, EmptyLayout.Status status, String info) {
    if (status != null) {
      if (StrUtil.isEmpty(info)) {
        emptyLayout.setStatus(status);
      } else {
        emptyLayout.setStatus(status, info);
      }
    }
  }
}

