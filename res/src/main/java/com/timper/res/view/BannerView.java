package com.timper.res.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.timper.res.R;
import com.timper.res.databinding.WeightBannerBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/**
 * 自定义Banner无限轮播控件
 */
public class BannerView extends RelativeLayout {

  private WeightBannerBinding binding;

  private CompositeDisposable compositeSubscription;

  //默认轮播时间，10s
  private int delayTime = 10;

  private Context context;

  private boolean isStopScroll = false;

  public BannerView(Context context) {
    this(context, null);
  }

  public BannerView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;
    LayoutInflater inflater = LayoutInflater.from(context);
    binding = DataBindingUtil.inflate(inflater, R.layout.weight_banner, this, true);
    init();
  }

  public ViewPager getViewPager() {
    return binding.viewpager;
  }

  private void init() {
    binding.viewpager.clearOnPageChangeListeners();
    binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override public void onPageSelected(int pos) {
      }

      @Override public void onPageScrollStateChanged(int state) {

        switch (state) {
          case ViewPager.SCROLL_STATE_IDLE:
            if (isStopScroll) {
              startScroll();
            }
            break;
          case ViewPager.SCROLL_STATE_DRAGGING:
            stopScroll();
            break;
        }
      }
    });
    compositeSubscription = new CompositeDisposable();
  }

  /**
   * 设置轮播间隔时间
   *
   * @param time 轮播间隔时间，单位秒
   */
  public BannerView delayTime(int time) {
    this.delayTime = time;
    return this;
  }

  /**
   * 图片开始轮播
   */
  public void startScroll() {
    destory();
    compositeSubscription = new CompositeDisposable();

    Disposable disposable = Observable.interval(delayTime, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(data -> {
      binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() + 1);
    });
    isStopScroll = false;
    compositeSubscription.add(disposable);
  }

  /**
   * 图片停止轮播
   */
  public void stopScroll() {
    isStopScroll = true;
    destory();
  }

  public void destory() {
    if (compositeSubscription != null) {
      compositeSubscription.dispose();
    }
  }
}
