package com.timper.res.bindingadapter.recyclerview;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.timper.res.bindingadapter.action.Command;
import com.timper.res.bindingadapter.collection.BindingCollectionAdapter;
import com.timper.res.bindingadapter.collection.ItemBinding;
import io.reactivex.subjects.PublishSubject;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @see {@link BindingCollectionAdapter}
 */
public class BindingRecyclerViewAdapters {
  // RecyclerView
  @SuppressWarnings("unchecked")
  @BindingAdapter(value = { "itemBinding", "items", "adapter", "itemIds", "viewHolder" }, requireAll = false)
  public static <T> void setAdapter(RecyclerView recyclerView, ItemBinding<T> itemBinding, List<T> items,
      BindingRecyclerViewAdapter<T> adapter, BindingRecyclerViewAdapter.ItemIds<? super T> itemIds,
      BindingRecyclerViewAdapter.ViewHolderFactory viewHolderFactory) {
    if (itemBinding == null) {
      throw new IllegalArgumentException("itemBinding must not be null");
    }
    BindingRecyclerViewAdapter oldAdapter = (BindingRecyclerViewAdapter) recyclerView.getAdapter();
    if (adapter == null) {
      if (oldAdapter == null) {
        adapter = new BindingRecyclerViewAdapter<>();
      } else {
        adapter = oldAdapter;
      }
    }
    adapter.setItemBinding(itemBinding);
    adapter.setItems(items);
    adapter.setItemIds(itemIds);
    adapter.setViewHolderFactory(viewHolderFactory);
    if (oldAdapter != adapter) {
      recyclerView.setAdapter(adapter);
    }
  }

  @BindingAdapter("layoutManager")
  public static void setLayoutManager(RecyclerView recyclerView, LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
    recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
  }

  @BindingAdapter(value = { "hasFixedSize", "scrollingEnabled" }, requireAll = false)
  public static void setIndex(final RecyclerView recyclerView, final Boolean hasFixedSize, final Boolean scrollingEnabled) {
    recyclerView.setHasFixedSize(hasFixedSize);
    recyclerView.setNestedScrollingEnabled(scrollingEnabled);
  }

  @BindingAdapter(value = { "onScrollChange", "onScrollStateChanged" }, requireAll = false)
  public static void onScrollChangeCommand(final RecyclerView recyclerView,
      final Command<ScrollDataWrapper> onScrollChangeCommand, final Command<Integer> onScrollStateChangedCommand) {
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      private int state;

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (onScrollChangeCommand != null) {
          onScrollChangeCommand.execute(new ScrollDataWrapper(dx, dy, state));
        }
      }

      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        state = newState;
        if (onScrollStateChangedCommand != null) {
          onScrollChangeCommand.equals(newState);
        }
      }
    });
  }

  @BindingAdapter(value = { "onLoadMore" }, requireAll = false)
  public static void onLoadMoreCommand(final RecyclerView recyclerView, final Command<Integer> onLoadMoreCommand) {
    recyclerView.addOnScrollListener(new OnLoadMoreScrollListener(onLoadMoreCommand));
  }

  @BindingAdapter(value = { "itemDecoration" }, requireAll = false)
  public static void addItemDecoration(final RecyclerView recyclerView, final RecyclerView.ItemDecoration decor) {
    recyclerView.addItemDecoration(decor);
  }

  public static class OnLoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private PublishSubject<Integer> methodInvoke = PublishSubject.create();

    private Command<Integer> onLoadMoreCommand;

    public OnLoadMoreScrollListener(Command<Integer> onLoadMoreCommand) {
      this.onLoadMoreCommand = onLoadMoreCommand;
      methodInvoke.throttleFirst(1, TimeUnit.SECONDS).subscribe(c -> onLoadMoreCommand.execute(c));
    }

    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
      int visibleItemCount = layoutManager.getChildCount();
      int totalItemCount = layoutManager.getItemCount();
      int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
      if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
        if (onLoadMoreCommand != null) {
          methodInvoke.onNext(recyclerView.getAdapter().getItemCount());
        }
      }
    }

    @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      super.onScrollStateChanged(recyclerView, newState);
    }
  }

  public static class ScrollDataWrapper {
    public float scrollX;
    public float scrollY;
    public int state;

    public ScrollDataWrapper(float scrollX, float scrollY, int state) {
      this.scrollX = scrollX;
      this.scrollY = scrollY;
      this.state = state;
    }
  }

  public static class ItemDataWrapper {
    public RecyclerView.Adapter adapter;
    public View view;
    public int position;

    public ItemDataWrapper(RecyclerView.Adapter adapter, View view, int position) {
      this.adapter = adapter;
      this.view = view;
      this.position = position;
    }
  }
}
