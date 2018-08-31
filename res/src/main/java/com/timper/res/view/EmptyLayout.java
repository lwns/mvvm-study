package com.timper.res.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.timper.res.R;
import com.timper.res.utils.NetUtil;
import com.timper.res.utils.StrUtil;

import static com.timper.res.view.EmptyLayout.Status.NO_NETWORK;

public class EmptyLayout extends FrameLayout {

  public enum Status {
    NO_NETWORK, EMPTY, REFRESH, NONE
  }

  private final Context context;
  private LayoutInflater inflater;

  private OnRefreshListener onRefreshListener;

  private Status status;

  private String emptyInfo;

  private FrameLayout flRoot;

  public EmptyLayout(Context context) {
    super(context);
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    init();
  }

  public EmptyLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    init();
  }

  private void init() {
    View view = LayoutInflater.from(getContext()).inflate(R.layout.widget_empty, this, false);
    flRoot = (FrameLayout) view.findViewById(R.id.fl_root);
    this.setOnClickListener(view1 -> {
    });
    addView(view);
    if (this.getVisibility() == GONE) {
      return;
    }
    if (NetUtil.isNetworkAvailable(context)) {
      this.setVisibility(GONE);
    } else {
      this.setStatus(NO_NETWORK);
    }
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
  }

  public OnRefreshListener getOnRefreshListener() {
    return onRefreshListener;
  }

  public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
    this.onRefreshListener = onRefreshListener;
  }

  public void setStatus(Status status, String info) {
    this.emptyInfo = info;
    this.status = status;
    if (!NetUtil.isNetworkAvailable(context)) {
      this.status = NO_NETWORK;
    }
    flRoot.removeAllViews();
    switch (this.status) {
      case NO_NETWORK:
        View view = inflater.inflate(R.layout.widget_empty_nonet, this, false);
        view.findViewById(R.id.tv_refresh).setOnClickListener(v -> {
          if (onRefreshListener != null && NetUtil.isNetworkAvailable(context)) {
            onRefreshListener.onRefresh();
          }
        });
        flRoot.addView(view);
        setVisibility(View.VISIBLE);
        break;
      case EMPTY:
        View noDataView = inflater.inflate(R.layout.widget_empty_nodata, null);
        flRoot.addView(noDataView);
        TextView textView = (TextView) noDataView.findViewById(R.id.tv_no_data);
        if (!StrUtil.isEmpty(emptyInfo)) {
          textView.setText(emptyInfo);
        }
        setVisibility(View.VISIBLE);
        break;
      case REFRESH:
        flRoot.addView(inflater.inflate(R.layout.widget_empty_refresh, null));
        setVisibility(View.VISIBLE);
        break;
      case NONE:
        setVisibility(View.GONE);
        break;
      default:
        break;
    }
  }

  public void setStatus(Status status) {
    setStatus(status, "");
  }

  public interface OnRefreshListener {
    void onRefresh();
  }
}
