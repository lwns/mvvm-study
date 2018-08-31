package com.timper.res.bindingadapter.textview;

import android.databinding.BindingAdapter;
import android.text.method.MovementMethod;
import android.widget.TextView;

public final class ViewBindingAdapter {

  @BindingAdapter({ "movementMethod" })
  public static void setMovementMethod(TextView textView, final MovementMethod movementMethod) {
    textView.setMovementMethod(movementMethod);
  }

  //@BindingAdapter(value = { "selectPosition" }, requireAll = false)
  //public static void setIndex(final ViewPager viewPager, final Integer position) {
  //  if (viewPager != null && viewPager.getAdapter() != null) viewPager.setCurrentItem(position);
  //}
}

