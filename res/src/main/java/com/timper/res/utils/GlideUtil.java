package com.timper.res.utils;

/**
 * @author tangpeng.yang.o(tangpeng.yang.o@nio.com)
 * @date 2017-10-13 19:59
 * Description:
 * FIXME
 */

public class GlideUtil {

  //public static void setUri(Context context, ImageView view, String uri) {
  //
  //  if (StrUtil.isEmpty(uri)) {
  //    return;
  //  }
  //  if (!isContextAvailable(context)) {
  //    return;
  //  }
  //  Glide.with(context).load(uri).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new SimpleTarget<Bitmap>() {
  //    @Override public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
  //      view.setImageBitmap(bitmap);
  //    }
  //  });
  //}
  //
  //public static void setUri(Context context, ImageView view, @DrawableRes int defaultImg, String uri) {
  //  if (StrUtil.isEmpty(uri)) {
  //    view.setImageResource(defaultImg);
  //    return;
  //  }
  //  if (!isContextAvailable(context)) {
  //    return;
  //  }
  //  Glide.with(context)
  //    .load(uri)
  //    .asBitmap()
  //    .placeholder(defaultImg)
  //    .dontAnimate()
  //    .error(defaultImg)
  //    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
  //    .into(view);
  //}
  //
  //public static void setUri(Context context, ImageView view, @DrawableRes int defaultImg, @DrawableRes int errorImg, String uri) {
  //
  //  if (StrUtil.isEmpty(uri)) {
  //    return;
  //  }
  //  if (!isContextAvailable(context)) {
  //    return;
  //  }
  //  Glide.with(context)
  //    .load(uri)
  //    .asBitmap()
  //    .placeholder(defaultImg)
  //    .error(errorImg)
  //    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
  //    .into(new SimpleTarget<Bitmap>() {
  //      @Override public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
  //        com.nio.vomcore.log.Logger.i("image change");
  //        view.setImageBitmap(bitmap);
  //      }
  //    });
  //}
  //
  //public static void setUri(Context context, ImageView view, @DrawableRes int defaultImg, String uri, int width, int height) {
  //  if (StrUtil.isEmpty(uri)) {
  //    return;
  //  }
  //
  //  if (!isContextAvailable(context)) {
  //    return;
  //  }
  //  Glide.with(context)
  //    .load(uri)
  //    .asBitmap()
  //    .placeholder(defaultImg)
  //    .dontAnimate()
  //    .error(defaultImg)
  //    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
  //    .override(width, height)
  //    .priority(Priority.HIGH)
  //    .format(PREFER_ARGB_8888)
  //    .centerCrop()
  //    .into(view);
  //}
  //
  //public static void setRealyUri(Context context, final ImageView view, @DrawableRes int defaultImg, String uri, int width,
  //  int height) {
  //  if (StrUtil.isEmpty(uri)) {
  //    return;
  //  }
  //  if (!isContextAvailable(context)) {
  //    return;
  //  }
  //  Glide.with(context).load(uri).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
  //    @Override public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
  //      view.setImageBitmap(ImageUtil.zoomBitmap(resource, width, height));
  //    }
  //  });
  //}
  //
  //public static boolean isContextAvailable(Context context) {
  //  if (context instanceof Activity) {
  //    if (((Activity) context).isFinishing() || ((Activity) context).isDestroyed()) {
  //      return false;
  //    }
  //  } else if (context == null) {
  //    return false;
  //  }
  //  return true;
  //}
}
