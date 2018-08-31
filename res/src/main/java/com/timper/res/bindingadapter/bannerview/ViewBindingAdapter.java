package com.timper.res.bindingadapter.bannerview;

import android.databinding.BindingAdapter;
import com.timper.res.bindingadapter.collection.BindingLoopViewPagerAdapter;
import com.timper.res.bindingadapter.collection.BindingViewPagerAdapter;
import com.timper.res.bindingadapter.collection.ItemBinding;
import com.timper.res.view.BannerView;
import java.util.List;

/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {

  @SuppressWarnings("unchecked") @BindingAdapter(value = { "itemBinding", "items", "adapter", "pageTitles" }, requireAll = false)
  public static <T> void initBanner(BannerView bannerView, ItemBinding<T> itemBinding, List<T> items,
      BindingViewPagerAdapter<T> adapter, BindingViewPagerAdapter.PageTitles<T> pageTitles) {
    if (itemBinding == null) {
      throw new IllegalArgumentException("itemBinding must not be null");
    }

    BindingLoopViewPagerAdapter<T> oldAdapter = (BindingLoopViewPagerAdapter<T>) bannerView.getViewPager().getAdapter();
    if (adapter == null) {
      if (oldAdapter == null) {
        adapter = new BindingLoopViewPagerAdapter<>();
      } else {
        adapter = oldAdapter;
      }
    }
    adapter.setItemBinding(itemBinding);
    adapter.setItems(items);
    adapter.setPageTitles(pageTitles);

    if (oldAdapter != adapter) {
      bannerView.getViewPager().setAdapter(adapter);
    }
    if (items != null && items.size() > 1) bannerView.delayTime(5).startScroll();
  }
}

