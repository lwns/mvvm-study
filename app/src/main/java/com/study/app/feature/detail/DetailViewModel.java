package com.study.app.feature.detail;

import com.study.app.databinding.ActDetailBinding;
import com.timper.lonelysword.ActivityScope;
import com.timper.lonelysword.base.AppActivity;
import com.timper.lonelysword.base.AppViewModel;
import com.timper.res.base.ModelAdapterFactor;
import android.arch.lifecycle.ViewModelProviders;
import javax.inject.Inject;

/**
 * User:
 * Date:
 * Description:
 * FIXME
 */
@ActivityScope public class DetailViewModel extends AppViewModel<ActDetailBinding> {

  DetailModelAdapter detailModelAdapter;

  @Inject public DetailViewModel(AppActivity activity, ModelAdapterFactor<DetailModelAdapter> factor) {
    super(activity);
    this.detailModelAdapter = ViewModelProviders.of(activity, factor).get(DetailModelAdapter.class);
  }

  @Override public void afterViews() {
    super.afterViews();
  }
}