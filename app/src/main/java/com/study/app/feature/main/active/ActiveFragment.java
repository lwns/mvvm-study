package com.study.app.feature.main.active;

import com.study.app.R;
import com.study.app.databinding.FrgActiveBinding;
import com.timper.lonelysword.annotations.apt.AfterViews;
import com.timper.lonelysword.annotations.apt.BeforeViews;
import com.timper.lonelysword.annotations.apt.RootView;
import com.timper.res.base.BaseFragment;

@RootView(R.layout.frg_active) public final class ActiveFragment extends BaseFragment<ActiveViewModel, FrgActiveBinding> {

  public static ActiveFragment instance() {
    return new ActiveFragment();
  }

  @BeforeViews void beforeViews() {
  }

  @AfterViews void afterViews() {
  }
}
