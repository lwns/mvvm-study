package com.study.app.feature.main;

import com.study.app.R;
import com.study.app.databinding.ActMainBinding;
import com.timper.lonelysword.annotations.apt.AfterViews;
import com.timper.lonelysword.annotations.apt.BeforeViews;
import com.timper.lonelysword.annotations.apt.Dagger;
import com.timper.lonelysword.annotations.apt.RootView;
import com.timper.res.base.BaseActivity;

@Dagger @RootView(R.layout.act_main) public class MainActivity extends BaseActivity<MainViewModel, ActMainBinding> {

  @AfterViews void afterViews() {

  }

  @BeforeViews void beforViews() {

  }
}
