package com.study.app.feature.main.active;

import android.arch.lifecycle.ViewModelProviders;
import com.study.app.databinding.FrgActiveBinding;
import com.study.app.feature.main.MainModelAdapter;
import com.timper.lonelysword.ActivityScope;
import com.timper.lonelysword.base.AppActivity;
import com.timper.res.base.BaseViewModel;
import com.timper.res.base.ModelAdapterFactor;
import javax.inject.Inject;

@ActivityScope public class ActiveViewModel extends BaseViewModel<FrgActiveBinding> {

  MainModelAdapter mainModelAdapter;

  @Inject public ActiveViewModel(AppActivity activity, ModelAdapterFactor<MainModelAdapter> factor) {
    super(activity);
    this.mainModelAdapter = ViewModelProviders.of(activity, factor).get(MainModelAdapter.class);
  }

  @Override public void afterViews() {
    super.afterViews();
  }
}