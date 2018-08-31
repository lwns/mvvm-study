package com.timper.res.view.dialog;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.timper.res.R;

public class Dialog {
  private final FrameLayout.LayoutParams params =
      new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);

  protected Context context;
  protected ViewGroup contentContainer;
  private ViewGroup decorView;//activity的根View
  private ViewGroup rootView;//附加View 的 根View

  private View vShade;

  private OnDismissListener onDismissListener;
  private boolean isDismissing = true;

  private boolean isAnimIng = false;

  private Animation outAnim;
  private Animation inAnim;
  private int gravity = Gravity.BOTTOM;

  private DialogBuilder builder;

  public Dialog(DialogBuilder builder) {
    this.context = builder.getContext();
    this.builder = builder;
    initViews();
    init();
    initEvents();
  }

  protected OnDialogLisetener[] onDialogLisetener;

  public void setOnDialogLisetener(OnDialogLisetener... onDialogLisetener) {
    this.onDialogLisetener = onDialogLisetener;
  }

  protected void initViews() {
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    decorView = (ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
    rootView = (ViewGroup) layoutInflater.inflate(R.layout.widget_basepickerview, decorView, false);

    rootView.setLayoutParams(
        new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    if (!builder.isBackgroud()) {
      rootView.findViewById(R.id.outmost_container).setBackgroundColor(context.getResources().getColor(R.color.transparent));
    }
    if (builder.isFullScreen()) {
      rootView.findViewById(R.id.v_shade).setBackgroundColor(context.getResources().getColor(R.color.transparent));
    }
    contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
    vShade = rootView.findViewById(R.id.v_shade);
    vShade.setAlpha(0f);
    contentContainer.setLayoutParams(builder.getContentParams());
  }

  protected void init() {
    inAnim = getInAnimation();
    inAnim.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {
        isAnimIng = true;
      }

      @Override public void onAnimationEnd(Animation animation) {
        isAnimIng = false;
      }

      @Override public void onAnimationRepeat(Animation animation) {

      }
    });
    outAnim = getOutAnimation();

    //消失动画
    outAnim.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {

        isAnimIng = true;
        //                decorView.post(new Runnable() {
        //                    @Override
        //                    public void run() {
        //                        //从activity根视图移除
        ////                        rootView.setAnimation(AnimationUtils
        ////                                .loadAnimation(context, R.anim.picker_alpha_out));
        //                        decorView.removeView(rootView);
        //                        if (onDismissListener != null) {
        //                            onDismissListener.onDismiss(Dialog.this);
        //                        }
        //                        isDismissing = true;
        //                    }
        //                });
      }

      @Override public void onAnimationEnd(Animation animation) {
        decorView.post(new Runnable() {
          @Override public void run() {
            //从activity根视图移除
            decorView.removeView(rootView);
            if (onDismissListener != null) {
              onDismissListener.onDismiss(Dialog.this);
            }
            isDismissing = true;
            isAnimIng = false;
          }
        });
      }

      @Override public void onAnimationRepeat(Animation animation) {
      }
    });
  }

  protected void initEvents() {
    setCancelable(builder.isCancelable());
  }

  public static DialogBuilder newDialog(Context context) {
    return new DialogBuilder(context);
  }

  /**
   * show的时候调用
   *
   * @param view 这个View
   */
  private void onAttached(View view) {
    decorView.addView(view);
    //        view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.picker_alpha_in));
    alphaIn(vShade).start();
    contentContainer.startAnimation(inAnim);
    contentContainer.requestFocus();
    contentContainer.setOnKeyListener(new View.OnKeyListener() {
      @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (event.getAction()) {
          case KeyEvent.ACTION_UP:
            if (keyCode == KeyEvent.KEYCODE_BACK) {
              //                            if (builder.isCancelable()) {
              dismiss();
              //                            }
              return true;
            }
            break;
          default:
            break;
        }
        return false;
      }
    });
  }

  private ObjectAnimator alphaOut(View view) {
    ObjectAnimator alphaOut = ObjectAnimator.ofFloat(view, "alpha", view.getAlpha(), 0f).setDuration(300);
    alphaOut.setInterpolator(new DecelerateInterpolator());
    return alphaOut;
  }

  private ObjectAnimator alphaIn(View view) {
    ObjectAnimator alphaOut = ObjectAnimator.ofFloat(view, "alpha", view.getAlpha(), 1f).setDuration(300);
    alphaOut.setInterpolator(new DecelerateInterpolator());
    return alphaOut;
  }

  /**
   * 添加这个View到Activity的根视图
   */
  public synchronized void show() {
    if (isAnimIng || !isDismissing || isShowing()) {
      return;
    }
    isDismissing = false;
    /**隐藏软键盘**/
    View view = ((Activity) context).getWindow().peekDecorView();
    if (view != null) {
      InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
      inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    onAttached(rootView);
  }

  /**
   * 检测该View是不是已经添加到根视图
   *
   * @return 如果视图已经存在该View返回true
   */
  public boolean isShowing() {
    View view = decorView.findViewById(R.id.outmost_container);
    return view != null;
  }

  public synchronized void dismiss() {
    if (isDismissing) {
      return;
    }
    isDismissing = true;
    if (builder.isFullScreen()) {
      decorView.removeView(rootView);
      if (onDismissListener != null) {
        onDismissListener.onDismiss(Dialog.this);
      }
      isAnimIng = false;
      return;
    }
    //
    //        rootView.setAnimation(AnimationUtils
    //                .loadAnimation(context, R.anim.picker_alpha_out));
    alphaOut(vShade).start();
    contentContainer.startAnimation(outAnim);
    //        rootView.startAnimation(outAnim);
  }

  public synchronized void dismissMoment() {
    if (isDismissing) {
      return;
    }
    isDismissing = true;
    isAnimIng = false;
    decorView.removeView(rootView);
  }

  public Animation getInAnimation() {
    return builder.getInAnimation();
  }

  public Animation getOutAnimation() {
    return builder.getOutAnimation();
  }

  public Dialog setOnDismissListener(OnDismissListener onDismissListener) {
    this.onDismissListener = onDismissListener;
    return this;
  }

  public Dialog setCancelable(boolean isCancelable) {
    View view = rootView.findViewById(R.id.outmost_container);

    if (isCancelable) {
      view.setOnTouchListener(onCancelableTouchListener);
    } else {
      view.setOnTouchListener(null);
    }
    return this;
  }

  /**
   * Called when the user touch on black overlay in order to dismiss the dialog
   */
  private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
    @Override public boolean onTouch(View v, MotionEvent event) {
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        if (builder.isCancelable() && !isAnimIng) {
          dismiss();
        }
      }
      return false;
    }
  };

  public View findViewById(int id) {
    return contentContainer.findViewById(id);
  }
}
