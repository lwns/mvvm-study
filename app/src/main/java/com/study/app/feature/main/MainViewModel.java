package com.study.app.feature.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ObservableField;
import com.study.app.databinding.ActMainBinding;
import com.timper.lonelysword.ActivityScope;
import com.timper.lonelysword.base.AppActivity;
import com.timper.res.base.BaseViewModel;
import com.timper.res.base.ModelAdapterFactor;
import javax.inject.Inject;

/**
 * User: tangpeng.yang
 * Date: 04/07/2018
 * Description:
 * FIXME
 */
@ActivityScope public class MainViewModel extends BaseViewModel<ActMainBinding> {

  public ObservableField<String> event = new ObservableField<>("sdfadf");

  MainModelAdapter modelAdater;

  @Inject public MainViewModel(AppActivity activity, ModelAdapterFactor<MainModelAdapter> factor) {
    super(activity);
    this.modelAdater = ViewModelProviders.of(activity, factor).get(MainModelAdapter.class);
  }

  @Override public void afterViews() {
    super.afterViews();

    modelAdater.events.observe(activity, data -> {
      event.set(data.size() > 0 ? data.get(0).getBody() : "data null");
    });
  }

  @Override public void onStart() {
    super.onStart();
    modelAdater.getEvents("");
  }
}
