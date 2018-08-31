package com.study.data.remote.transformer;

import com.study.data.remote.BaseResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/7/21
 */
public class DefaultTransformer<T> implements ObservableTransformer<BaseResponse<T>, T> {

  @Override public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
    return upstream.compose(SchedulerTransformer.create()).compose(new ErrorTransformer<>());
  }
}