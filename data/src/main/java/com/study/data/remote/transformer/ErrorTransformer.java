package com.study.data.remote.transformer;

import com.study.data.remote.BaseResponse;
import com.study.data.remote.Global;
import com.study.data.remote.exception.RepositoryException;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * @author op
 * @version 1.0
 * @description 对response数据进行拦截处理
 * @createDate 2016/3/24
 */
public class ErrorTransformer<T> implements ObservableTransformer<BaseResponse<T>, T> {

  @Override public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
    return upstream.flatMap(it -> flatResponse(it));
  }

  private Observable<T> flatResponse(final BaseResponse<T> response) {
    return Observable.create(new ObservableOnSubscribe<T>() {
      @Override public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
        if (response.isSuccess()) {
          if (!subscriber.isDisposed()) {
            Global.SERVER_TIME = response.getTime();
            subscriber.onNext(response.getResult());
          }
        } else {
          if (!subscriber.isDisposed()) {
            subscriber.onError(new RepositoryException(response.getMessage()));
          }
          return;
        }

        if (!subscriber.isDisposed()) {
          subscriber.onComplete();
        }
      }
    });
  }
}
