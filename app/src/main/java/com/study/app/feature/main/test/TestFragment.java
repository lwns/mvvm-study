package com.study.app.feature.main.test;

import com.study.app.R;
import com.study.app.databinding.FrgTestBinding;
import com.study.app.feature.main.MainActivity;
import com.timper.lonelysword.annotations.apt.AfterViews;
import com.timper.lonelysword.annotations.apt.BeforeViews;
import com.timper.lonelysword.annotations.apt.Dagger;
import com.timper.lonelysword.annotations.apt.RootView;
import com.timper.res.base.BaseFragment;

/**
 * User: tangpeng.yang
 * Date: 24/08/2018
 * Description:
 * FIXME
 */
@Dagger(MainActivity.class) @RootView(R.layout.frg_test) public class TestFragment
    extends BaseFragment<TestViewModel, FrgTestBinding> {

  @BeforeViews void beforViews() {

  }

  @AfterViews void afterViews() {

  }
}
