package com.timper.res.base.loadmore;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import com.timper.lonelysword.base.AppActivity;
import com.timper.lonelysword.base.AppViewModel;
import com.timper.res.R;
import com.timper.res.BR;
import com.timper.res.bindingadapter.action.Command;
import com.timper.res.bindingadapter.collection.collections.MergeObservableList;
import com.timper.res.bindingadapter.collection.itembindings.OnItemBindClass;
import com.timper.res.databinding.FrgLoadmoreBinding;
import com.timper.res.view.EmptyLayout;

/**
 * User:
 * Date:
 * Description:
 * FIXME
 */
public abstract class LoadMoreViewModel<T> extends AppViewModel<FrgLoadmoreBinding> {

  protected int pageSize = 10;

  protected int pageNum = 1;

  protected boolean loadMore = false;

  public ObservableField<EmptyLayout.Status> state = new ObservableField<>();

  public ObservableField<Boolean> refresh = new ObservableField<>(false);

  public LoadViewModel loadViewModel = new LoadViewModel();

  public OnItemBindClass itemBinding = onItemBindClass().map(LoadViewModel.class, BR.viewModel, R.layout.item_loadmore);

  public int[] colorScheme = new int[] { R.color.app_theme_background_00bebe };

  public ObservableList<T> list = new ObservableArrayList<>();

  public final MergeObservableList<Object> datas = new MergeObservableList<>();

  public Command onRefresh = new Command(() -> {
    refresh.set(true);
    pageNum = 1;
    refresh();
  });

  public Command<Integer> onLoadMore = new Command(data -> {
    if (loadMore) {
      pageNum++;
      loadMore();
    }
  });

  public Command onRetry = new Command(() -> {
    refresh.set(true);
    pageNum = 1;
    refresh();
  });

  public LoadMoreViewModel(AppActivity activity) {
    super(activity);
  }

  protected void notifyData(boolean error) {
    refresh.set(false);
    if (pageNum == 1 && list.size() < pageSize) {
      if (loadMore) {
        datas.removeItem(loadViewModel);
      }
      loadMore = false;
      loadViewModel.setStatus(LoadViewModel.Status.NOMORE);
    } else if (list.size() / pageNum < pageSize) {
      loadViewModel.setStatus(LoadViewModel.Status.LOADCOMPLATE);
    } else {
      if (!loadMore) {
        datas.insertItem(loadViewModel);
      }
      loadMore = true;
      loadViewModel.setStatus(LoadViewModel.Status.LOADMORE);
    }

    if (error) {
      state.set(EmptyLayout.Status.EMPTY);
    } else if (list.size() == 0) {
      state.set(EmptyLayout.Status.EMPTY);
    } else {
      state.set(EmptyLayout.Status.NONE);
    }
  }

  abstract public OnItemBindClass onItemBindClass();

  abstract public void refresh();

  abstract public void loadMore();
}