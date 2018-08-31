package com.study.app.feature.splash;

import android.arch.lifecycle.ViewModelProviders;
import com.study.app.databinding.ActSplashBinding;
import com.timper.lonelysword.ActivityScope;
import com.timper.lonelysword.base.AppActivity;
import com.timper.lonelysword.base.AppViewModel;
import com.timper.res.base.ModelAdapterFactor;
import javax.inject.Inject;

/**
 * User:
 * Date:
 * Description:
 * FIXME
 */
@ActivityScope public class SplashViewModel extends AppViewModel<ActSplashBinding> {

  SplashModelAdapter splashModelAdapter;

  @Inject public SplashViewModel(AppActivity activity, ModelAdapterFactor<SplashModelAdapter> factor) {
    super(activity);
    this.splashModelAdapter = ViewModelProviders.of(activity, factor).get(SplashModelAdapter.class);
  }

  @Override public void afterViews() {
    super.afterViews();
  }
}