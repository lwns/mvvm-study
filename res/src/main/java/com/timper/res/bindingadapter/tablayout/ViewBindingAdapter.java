package com.timper.res.bindingadapter.tablayout;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public final class ViewBindingAdapter {
  @BindingAdapter(value = { "viewPager" }, requireAll = false)
  public static void setViewPager(TabLayout tabLayout, ViewPager viewPager) {
    if (viewPager == null || tabLayout == null) {
      return;
    }
    tabLayout.setupWithViewPager(viewPager);
  }
}

