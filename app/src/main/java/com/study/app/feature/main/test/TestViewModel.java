package com.study.app.feature.main.test;

import android.arch.lifecycle.ViewModelProviders;
import com.study.app.databinding.FrgTestBinding;
import com.study.app.feature.main.MainModelAdapter;
import com.timper.lonelysword.ActivityScope;
import com.timper.lonelysword.annotations.apt.AfterViews;
import com.timper.lonelysword.base.AppActivity;
import com.timper.res.base.BaseViewModel;
import com.timper.res.base.ModelAdapterFactor;
import javax.inject.Inject;

/**
 * User: tangpeng.yang
 * Date: 24/08/2018
 * Description:
 * FIXME
 */
@ActivityScope public class TestViewModel extends BaseViewModel<FrgTestBinding> {

  MainModelAdapter modelAdater;

  @Inject public TestViewModel(AppActivity activity, ModelAdapterFactor<MainModelAdapter> factor) {
    super(activity);
    this.modelAdater = ViewModelProviders.of(activity, factor).get(MainModelAdapter.class);
  }

  @Override public void afterViews() {
    super.afterViews();

    this.modelAdater.events.observe(activity,data->{

    });
  }
}
