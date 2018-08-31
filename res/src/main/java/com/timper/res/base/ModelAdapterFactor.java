package com.timper.res.base;

import android.arch.lifecycle.ViewModelProvider;
import javax.inject.Inject;

/**
 * User: tangpeng.yang
 * Date: 19/03/2018
 * Description:
 * FIXME
 */
public class ModelAdapterFactor<V> implements ViewModelProvider.Factory {

  private V viewModel;

  @Inject public ModelAdapterFactor(V viewModel) {
    this.viewModel = viewModel;
  }

  @Override public <T extends android.arch.lifecycle.ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(viewModel.getClass())) {
      return (T) viewModel;
    }
    throw new IllegalArgumentException("Unknown class name");
  }
}

