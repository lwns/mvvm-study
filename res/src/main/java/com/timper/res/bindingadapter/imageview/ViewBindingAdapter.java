package com.timper.res.bindingadapter.imageview;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;
import com.timper.res.GlideApp;

public final class ViewBindingAdapter {

  @BindingAdapter(value = { "uri", "defaultSrc", "errorSrc" }, requireAll = false)
  public static void setImageUri(ImageView imageView, String uri, @DrawableRes int defaultRes, @DrawableRes int error) {
    if (!TextUtils.isEmpty(uri)) {
      GlideApp.with(imageView.getContext()).asBitmap().placeholder(defaultRes).error(error).load(uri).into(imageView);
    }
  }

  @BindingAdapter(value = { "imageSrc" }, requireAll = false)
  public static void setImageSrc(ImageView imageView, @DrawableRes int defaultRes) {
    if (defaultRes != -1) {
      imageView.setBackgroundResource(defaultRes);
    }
  }
}
