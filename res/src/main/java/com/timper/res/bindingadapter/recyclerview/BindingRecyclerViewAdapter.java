package com.timper.res.bindingadapter.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.timper.res.bindingadapter.collection.AdapterReferenceCollector;
import com.timper.res.bindingadapter.collection.BindingCollectionAdapter;
import com.timper.res.bindingadapter.collection.ItemBinding;
import com.timper.res.bindingadapter.collection.Utils;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * A {@link RecyclerView.Adapter} that binds items to layouts using the given {@link ItemBinding}.
 * If you give it an {@link ObservableList} it will also updated itself based on changes to that
 * list.
 */
public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> implements BindingCollectionAdapter<T> {
  private static final Object DATA_INVALIDATION = new Object();

  private ItemBinding<T> itemBinding;
  private WeakReferenceOnListChangedCallback<T> callback;
  private List<T> items;
  private LayoutInflater inflater;
  private ItemIds<? super T> itemIds;
  private ViewHolderFactory viewHolderFactory;
  private OnItemClickListener onItemClickListener;
  private OnItemLongClickListener onItemLongClickListener;

  // Currently attached recyclerview, we don't have to listen to notifications if null.
  @Nullable private RecyclerView recyclerView;

  public OnItemClickListener getOnItemClickListener() {
    return onItemClickListener;
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public OnItemLongClickListener getOnItemLongClickListener() {
    return onItemLongClickListener;
  }

  public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
    this.onItemLongClickListener = onItemLongClickListener;
  }

  @Override public void setItemBinding(ItemBinding<T> itemBinding) {
    this.itemBinding = itemBinding;
  }

  @Override public ItemBinding<T> getItemBinding() {
    return itemBinding;
  }

  @Override public void setItems(@Nullable List<T> items) {
    if (this.items == items) {
      return;
    }
    // If a recyclerview is listening, set up listeners. Otherwise wait until one is attached.
    // No need to make a sound if nobody is listening right?
    if (recyclerView != null) {
      if (this.items instanceof ObservableList) {
        ((ObservableList<T>) this.items).removeOnListChangedCallback(callback);
        callback = null;
      }
      if (items instanceof ObservableList) {
        callback = new WeakReferenceOnListChangedCallback<>(this, (ObservableList<T>) items);
        ((ObservableList<T>) items).addOnListChangedCallback(callback);
      }
    }
    this.items = items;
    notifyDataSetChanged();
  }

  @Override public T getAdapterItem(int position) {
    return items.get(position);
  }

  @Override public ViewDataBinding onCreateBinding(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup viewGroup) {
    return DataBindingUtil.inflate(inflater, layoutId, viewGroup, false);
  }

  @Override public void onBindBinding(ViewDataBinding binding, int variableId, @LayoutRes int layoutRes, int position, T item) {
    if (itemBinding.bind(binding, position, item)) {
      binding.executePendingBindings();
    }
  }

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    if (this.recyclerView == null && items != null && items instanceof ObservableList) {
      callback = new WeakReferenceOnListChangedCallback<>(this, (ObservableList<T>) items);
      ((ObservableList<T>) items).addOnListChangedCallback(callback);
    }
    this.recyclerView = recyclerView;
  }

  @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    if (this.recyclerView != null && items != null && items instanceof ObservableList) {
      ((ObservableList<T>) items).removeOnListChangedCallback(callback);
      callback = null;
    }
    this.recyclerView = null;
  }

  @Override public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int layoutId) {
    if (inflater == null) {
      inflater = LayoutInflater.from(viewGroup.getContext());
    }
    ViewDataBinding binding = onCreateBinding(inflater, layoutId, viewGroup);
    final ViewHolder holder = onCreateViewHolder(binding);
    bindViewClickListener(holder, binding);//binding item itemlong click
    binding.addOnRebindCallback(new OnRebindCallback() {
      @Override public boolean onPreBind(ViewDataBinding binding) {
        return recyclerView != null && recyclerView.isComputingLayout();
      }

      @Override public void onCanceled(ViewDataBinding binding) {
        if (recyclerView == null || recyclerView.isComputingLayout()) {
          return;
        }
        int position = holder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
          notifyItemChanged(position, DATA_INVALIDATION);
        }
      }
    });
    return holder;
  }

  private void bindViewClickListener(final ViewHolder holder, final ViewDataBinding binding) {
    final View view = binding.getRoot();
    if (view == null) {
      return;
    }
    if (getOnItemClickListener() != null) {
      view.setOnClickListener(
          v -> getOnItemClickListener().onItemClick(BindingRecyclerViewAdapter.this, v, holder.getLayoutPosition()));
    }
    if (getOnItemLongClickListener() != null) {
      view.setOnLongClickListener(
          v -> getOnItemLongClickListener().onItemLongClick(BindingRecyclerViewAdapter.this, v, holder.getLayoutPosition()));
    }
  }

  /**
   * Constructs a view holder for the given databinding. The default implementation is to use
   * {@link ViewHolderFactory} if provided, otherwise use a default view holder.
   */
  public ViewHolder onCreateViewHolder(ViewDataBinding binding) {
    if (viewHolderFactory != null) {
      return viewHolderFactory.createViewHolder(binding);
    } else {
      return new BindingViewHolder(binding);
    }
  }

  private static class BindingViewHolder extends ViewHolder {
    public BindingViewHolder(ViewDataBinding binding) {
      super(binding.getRoot());
    }
  }

  @Override public final void onBindViewHolder(ViewHolder viewHolder, int position) {
    T item = items.get(position);
    ViewDataBinding binding = DataBindingUtil.getBinding(viewHolder.itemView);
    onBindBinding(binding, itemBinding.variableId(), itemBinding.layoutRes(), position, item);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
    if (isForDataBinding(payloads)) {
      ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
      binding.executePendingBindings();
    } else {
      super.onBindViewHolder(holder, position, payloads);
    }
  }

  private boolean isForDataBinding(List<Object> payloads) {
    if (payloads == null || payloads.size() == 0) {
      return false;
    }
    for (int i = 0; i < payloads.size(); i++) {
      Object obj = payloads.get(i);
      if (obj != DATA_INVALIDATION) {
        return false;
      }
    }
    return true;
  }

  @Override public int getItemViewType(int position) {
    itemBinding.onItemBind(position, items.get(position));
    return itemBinding.layoutRes();
  }

  private boolean isShowAddItem(int position) {
    int size = items.size() == 0 ? 0 : items.size();
    return position == size;
  }

  /**
   * Set the item id's for the items. If not null, this will set {@link
   * android.support.v7.widget.RecyclerView.Adapter#setHasStableIds(boolean)} to true.
   */
  public void setItemIds(@Nullable ItemIds<? super T> itemIds) {
    if (this.itemIds != itemIds) {
      this.itemIds = itemIds;
      setHasStableIds(itemIds != null);
    }
  }

  /**
   * Set the factory for creating view holders. If null, a default view holder will be used. This
   * is useful for holding custom state in the view holder or other more complex customization.
   */
  public void setViewHolderFactory(@Nullable ViewHolderFactory factory) {
    viewHolderFactory = factory;
  }

  @Override public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  @Override public long getItemId(int position) {
    return itemIds == null ? position : itemIds.getItemId(position, items.get(position));
  }

  private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
    final WeakReference<BindingRecyclerViewAdapter<T>> adapterRef;

    WeakReferenceOnListChangedCallback(BindingRecyclerViewAdapter<T> adapter, ObservableList<T> items) {
      this.adapterRef = AdapterReferenceCollector.createRef(adapter, items, this);
    }

    @Override public void onChanged(ObservableList sender) {
      BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
      if (adapter == null) {
        return;
      }
      Utils.ensureChangeOnMainThread();
      adapter.notifyDataSetChanged();
    }

    @Override public void onItemRangeChanged(ObservableList sender, final int positionStart, final int itemCount) {
      BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
      if (adapter == null) {
        return;
      }
      Utils.ensureChangeOnMainThread();
      adapter.notifyDataSetChanged();
      //adapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override public void onItemRangeInserted(ObservableList sender, final int positionStart, final int itemCount) {
      BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
      if (adapter == null) {
        return;
      }
      Utils.ensureChangeOnMainThread();
      adapter.notifyDataSetChanged();
      //adapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableList sender, final int fromPosition, final int toPosition, final int itemCount) {
      BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
      if (adapter == null) {
        return;
      }
      Utils.ensureChangeOnMainThread();
      for (int i = 0; i < itemCount; i++) {
        //adapter.notifyItemMoved(fromPosition + i, toPosition + i);
      }

      adapter.notifyDataSetChanged();
    }

    @Override public void onItemRangeRemoved(ObservableList sender, final int positionStart, final int itemCount) {
      BindingRecyclerViewAdapter<T> adapter = adapterRef.get();
      if (adapter == null) {
        return;
      }
      Utils.ensureChangeOnMainThread();
      //adapter.notifyItemRangeRemoved(positionStart, itemCount);

      adapter.notifyDataSetChanged();
    }
  }

  public interface ItemIds<T> {
    long getItemId(int position, T item);
  }

  public interface ViewHolderFactory {
    RecyclerView.ViewHolder createViewHolder(ViewDataBinding binding);
  }

  /**
   * Interface definition for a callback to be invoked when an item in this
   * RecyclerView itemView has been clicked.
   */
  public interface OnItemClickListener {

    /**
     * Callback method to be invoked when an item in this RecyclerView has
     * been clicked.
     *
     * @param adapter the adpater
     * @param view The itemView within the RecyclerView that was clicked (this
     * will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     */
    void onItemClick(RecyclerView.Adapter adapter, View view, int position);
  }

  /**
   * Interface definition for a callback to be invoked when an item in this
   * view has been clicked and held.
   */
  public interface OnItemLongClickListener {
    /**
     * callback method to be invoked when an item in this view has been
     * click and held
     *
     * @param adapter the adpater
     * @param view The view whihin the RecyclerView that was clicked and held.
     * @param position The position of the view int the adapter
     * @return true if the callback consumed the long click ,false otherwise
     */
    boolean onItemLongClick(RecyclerView.Adapter adapter, View view, int position);
  }
}
