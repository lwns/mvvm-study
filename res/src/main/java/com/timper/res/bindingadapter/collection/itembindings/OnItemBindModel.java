package com.timper.res.bindingadapter.collection.itembindings;

import com.timper.res.bindingadapter.collection.ItemBinding;
import com.timper.res.bindingadapter.collection.OnItemBind;
import java.util.Map;

/**
 * An {@link OnItemBind} that selects item views by delegating to each item. Items must implement
 * {@link ItemBindingModel}.
 */
public class OnItemBindModel<T extends ItemBindingModel> implements OnItemBind<T> {

    @Override
    public void onItemBind(ItemBinding itemBinding, int position, T item) {
        item.onItemBind(itemBinding);
    }

    @Override public OnItemBind<T> onItemClickBind(ItemBinding.OnItemClickListener onItemClickListener) {
        return null;
    }

    @Override public OnItemBind<T> onItemChildClickBind(int resId, ItemBinding.OnItemChildClickListener onItemChildClick) {
        return null;
    }

    @Override public ItemBinding.OnItemClickListener getOnItemClick(T item) {
        return null;
    }

    @Override public Map<Integer, ItemBinding.OnItemChildClickListener> getOnItemChildClick(T item) {
        return null;
    }
}
