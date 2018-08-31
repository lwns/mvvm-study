package com.timper.res.base;

import android.databinding.ViewDataBinding;
import com.timper.lonelysword.base.AppActivity;
import com.timper.lonelysword.base.AppViewModel;

/**
 * User: tangpeng.yang
 * Date: 22/08/2018
 * Description:
 * FIXME
 */
public class BaseViewModel<T extends ViewDataBinding> extends AppViewModel<T> {

  public BaseViewModel(AppActivity activity) {
    super(activity);
  }
}
