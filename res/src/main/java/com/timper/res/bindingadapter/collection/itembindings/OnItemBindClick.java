package com.timper.res.bindingadapter.collection.itembindings;

import android.support.annotation.LayoutRes;
import com.timper.res.bindingadapter.collection.ItemBinding;
import com.timper.res.bindingadapter.collection.OnItemBind;
import java.util.HashMap;
import java.util.Map;

/**
 * User: tangpeng.yang
 * Date: 26/03/2018
 * Description:
 * FIXME
 */
public class OnItemBindClick<T> implements OnItemBind<T> {

  ItemBinding.OnItemClickListener onItemClickListener;
  Map<Integer, ItemBinding.OnItemChildClickListener> map;
  int variableId;
  @LayoutRes int layoutRes;

  public OnItemBindClick(int variableId, @LayoutRes int layoutRes) {
    this.variableId = variableId;
    this.layoutRes = layoutRes;
  }

  @Override public void onItemBind(ItemBinding itemBinding, int position, T item) {
    itemBinding.set(variableId, layoutRes);
  }

  public OnItemBindClick<T> onItemClickBind(ItemBinding.OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
    return this;
  }

  public OnItemBindClick<T> onItemChildClickBind(int resId, ItemBinding.OnItemChildClickListener onItemChildClick) {
    if (map == null) {
      map = new HashMap<>();
    }
    map.put(resId, onItemChildClick);
    return this;
  }

  public ItemBinding.OnItemClickListener getOnItemClick(T item) {
    return onItemClickListener;
  }

  public Map<Integer, ItemBinding.OnItemChildClickListener> getOnItemChildClick(T item) {
    return map;
  }
}
