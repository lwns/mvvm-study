package com.study.app;

import com.study.app.di.DaggerAppComponent;
import com.timper.lonelysword.base.App;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * User: tangpeng.yang
 * Date: 24/08/2018
 * Description:
 * FIXME
 */
public class MainApplication extends App {

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().create(this);
  }
}
