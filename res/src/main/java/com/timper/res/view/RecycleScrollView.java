package com.timper.res.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class RecycleScrollView extends NestedScrollView {

    private int downX;
    private int downY;
    private int mTouchSlop;

    private OnScrollChangedListener onScrollChangedListener;

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    public RecycleScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public RecycleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public RecycleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(NestedScrollView scrollView, int l, int t, int oldl, int oldt);
    }
}
