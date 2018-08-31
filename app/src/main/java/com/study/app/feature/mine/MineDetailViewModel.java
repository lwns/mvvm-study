package com.study.app.feature.mine;

import com.study.app.databinding.ActMinedetailBinding;
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
@ActivityScope public class MineDetailViewModel extends AppViewModel<ActMinedetailBinding> {

  MineDetailModelAdapter minedetailModelAdapter;

  @Inject public MineDetailViewModel(AppActivity activity, ModelAdapterFactor<MineDetailModelAdapter> factor) {
    super(activity);
    this.minedetailModelAdapter = ViewModelProviders.of(activity, factor).get(MineDetailModelAdapter.class);
  }

  @Override public void afterViews() {
    super.afterViews();
  }
}