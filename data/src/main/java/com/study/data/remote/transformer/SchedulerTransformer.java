package com.study.data.remote.transformer;

import com.study.data.remote.BaseResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author op
 * @version 1.0
 * @description 请求线程
 * @createDate 2016/7/21
 */
public class SchedulerTransformer<T> implements ObservableTransformer<BaseResponse<T>, BaseResponse<T>> {

  @Override public ObservableSource<BaseResponse<T>> apply(Observable<BaseResponse<T>> upstream) {
    return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> SchedulerTransformer<T> create() {
    return new SchedulerTransformer<T>();
  }
}
