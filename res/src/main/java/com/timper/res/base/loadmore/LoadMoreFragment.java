package com.timper.res.base.loadmore;

import com.timper.lonelysword.annotations.apt.DisableNetwork;
import com.timper.lonelysword.annotations.apt.EnableNetwork;
import com.timper.lonelysword.base.AppFragment;
import com.timper.res.databinding.FrgLoadmoreBinding;
import com.timper.res.view.EmptyLayout;

public abstract class LoadMoreFragment<V extends LoadMoreViewModel, T extends FrgLoadmoreBinding> extends AppFragment<V, T> {

  @DisableNetwork public void networkDisable() {
    viewModel.state.set(EmptyLayout.Status.NO_NETWORK);
  }

  @EnableNetwork public void networkEnable() {
  }
}
