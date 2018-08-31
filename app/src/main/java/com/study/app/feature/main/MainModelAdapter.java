package com.study.app.feature.main;

import android.arch.lifecycle.MutableLiveData;
import com.study.data.GetEventUseCase;
import com.study.data.GetNewsUseCase;
import com.study.data.GetTweetUseCase;
import com.study.data.bean.Event;
import com.study.data.bean.base.PageBean;
import com.timper.res.base.BaseModelAdapter;
import io.reactivex.observers.DisposableObserver;
import java.util.List;
import javax.inject.Inject;

/**
 * User: tangpeng.yang
 * Date: 24/08/2018
 * Description:
 * FIXME
 */
public class MainModelAdapter extends BaseModelAdapter {

  GetEventUseCase getEventUseCase;

  public MutableLiveData<List<Event>> events;

  @Inject public MainModelAdapter(GetEventUseCase getEventUseCase,GetNewsUseCase getNewsUseCase) {
    this.getEventUseCase = getEventUseCase;
    events = new MutableLiveData<>();
  }

  public void getEvents(String params) {
    getEventUseCase.execute(new DisposableObserver<PageBean<Event>>() {
      @Override public void onNext(PageBean<Event> value) {
        if (events.getValue() != null) {
          events.getValue().clear();
        }
        events.setValue(value.getItems());
      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onComplete() {

      }
    }, params);
  }
}
