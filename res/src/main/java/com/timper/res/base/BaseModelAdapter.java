package com.timper.res.base;

import android.arch.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

/**
 * User: tangpeng.yang
 * Date: 24/08/2018
 * Description:
 * FIXME
 */
public abstract class BaseModelAdapter extends ViewModel {

  private CompositeDisposable mCompositeDisposable;

  public BaseModelAdapter() {
    this.mCompositeDisposable = new CompositeDisposable();
  }

  @Override protected void onCleared() {
    mCompositeDisposable.dispose();
    super.onCleared();
  }

  public CompositeDisposable getCompositeDisposable() {
    return mCompositeDisposable;
  }
}
