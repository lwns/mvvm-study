package com.timper.res.view.dialog;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2017/2/22
 */
public class DialogBuilder {
  private static final int INVALID = -1;

  private final int[] margin = new int[4];
  private final int[] padding = new int[4];
  private final int[] outMostMargin = new int[4];

  private int gravity = Gravity.BOTTOM;
  private boolean isCancelable = true;

  private boolean isBackgroud = true;

  private boolean isFullScreen = false;

  private WeakReference<Context> context;

  private int inAnimation = INVALID;
  private int outAnimation = INVALID;
  private final FrameLayout.LayoutParams params =
      new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);

  public DialogBuilder(Context context) {
    if (context == null) {
      throw new NullPointerException("Context may not be null");
    }
    this.context = new WeakReference<Context>(context);
    Arrays.fill(margin, INVALID);
  }

  public static DialogBuilder newDialog(Context context) {
    return new DialogBuilder(context);
  }

  public FrameLayout.LayoutParams getContentParams() {
    params.setMargins(margin[0], margin[1], margin[2], margin[3]);
    return params;
  }

  /**
   * Define if the dialog is cancelable and should be closed when back pressed or click outside is pressed
   */
  public DialogBuilder setCancelable(boolean isCancelable) {
    this.isCancelable = isCancelable;
    return this;
  }

  public boolean isFullScreen() {
    return isFullScreen;
  }

  public DialogBuilder setFullScreen(boolean fullScreen) {
    isFullScreen = fullScreen;
    return this;
  }

  public boolean isCancelable() {
    return isCancelable;
  }

  public boolean isBackgroud() {
    return isBackgroud;
  }

  public DialogBuilder setBackgroud(boolean backgroud) {
    isBackgroud = backgroud;
    return this;
  }

  /**
   * Set the gravity you want the dialog to have among the ones that are provided
   */
  public DialogBuilder setGravity(int gravity) {
    this.gravity = gravity;
    params.gravity = gravity;
    return this;
  }

  /**
   * Customize the in animation by passing an animation resource
   */
  public DialogBuilder setInAnimation(int inAnimResource) {
    this.inAnimation = inAnimResource;
    return this;
  }

  /**
   * Customize the out animation by passing an animation resource
   */
  public DialogBuilder setOutAnimation(int outAnimResource) {
    this.outAnimation = outAnimResource;
    return this;
  }

  /**
   * Add margins to your outmost view which contains everything. As default they are 0
   * are applied
   */
  public DialogBuilder setOutMostMargin(int left, int top, int right, int bottom) {
    this.outMostMargin[0] = left;
    this.outMostMargin[1] = top;
    this.outMostMargin[2] = right;
    this.outMostMargin[3] = bottom;
    return this;
  }

  /**
   * Add margins to your dialog. They are set to 0 except when gravity is center. In that case basic margins
   * are applied
   */
  public DialogBuilder setMargin(int left, int top, int right, int bottom) {
    this.margin[0] = left;
    this.margin[1] = top;
    this.margin[2] = right;
    this.margin[3] = bottom;
    return this;
  }

  /**
   * Set paddings for the dialog content
   */
  public DialogBuilder setPadding(int left, int top, int right, int bottom) {
    this.padding[0] = left;
    this.padding[1] = top;
    this.padding[2] = right;
    this.padding[3] = bottom;
    return this;
  }

  public Context getContext() {
    return context.get();
  }

  public Animation getInAnimation() {
    @AnimRes int res = (inAnimation == INVALID) ? DialogAnimateUtil.getAnimationResource(this.gravity, true) : inAnimation;
    return AnimationUtils.loadAnimation(context.get(), res);
  }

  public Animation getOutAnimation() {
    @AnimRes int res = (outAnimation == INVALID) ? DialogAnimateUtil.getAnimationResource(this.gravity, false) : outAnimation;
    return AnimationUtils.loadAnimation(context.get(), res);
  }

  /**
   * Create the dialog using this builder
   */
  public Dialog create() {
    return new Dialog(this);
  }
}
