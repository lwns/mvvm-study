package com.study.data.repository;

import com.study.data.MainRepository;
import com.study.data.bean.Banner;
import com.study.data.bean.Blog;
import com.study.data.bean.BlogDetail;
import com.study.data.bean.Event;
import com.study.data.bean.EventDetail;
import com.study.data.bean.News;
import com.study.data.bean.NewsDetail;
import com.study.data.bean.Question;
import com.study.data.bean.QuestionDetail;
import com.study.data.bean.ShakeNews;
import com.study.data.bean.SoftwareDetail;
import com.study.data.bean.Tweet;
import com.study.data.bean.base.PageBean;
import com.study.data.bean.request.PageBeanRequest;
import com.study.data.remote.service.ApiClient;
import com.study.data.remote.transformer.ErrorTransformer;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * User: tangpeng.yang
 * Date: 04/06/2018
 * Description:
 * FIXME
 */
public class MainRepositoryImp implements MainRepository {
  ApiClient apiClient;

  @Inject public MainRepositoryImp(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @Override public Observable<PageBean<News>> getNews(String pageToken) {
    return apiClient.getNews(pageToken).compose(new ErrorTransformer<>());
  }

  @Override public Observable<PageBean<Banner>> getBanner(String catalog) {
    return apiClient.getBanner(catalog).compose(new ErrorTransformer<>());
  }

  @Override public Observable<PageBean<Blog>> getBlog(PageBeanRequest request) {
    return apiClient.getBlog(request.getCatalog(), request.getPageToken()).compose(new ErrorTransformer<>());
  }

  @Override public Observable<PageBean<Question>> getQuestion(PageBeanRequest request) {
    return apiClient.getQuestion(request.getCatalog(), request.getPageToken()).compose(new ErrorTransformer<>());
  }

  @Override public Observable<PageBean<Event>> getEvent(String pageToken) {
    return apiClient.getEvent(pageToken).compose(new ErrorTransformer<>());
  }

  @Override public Observable<PageBean<Tweet>> getTweet(PageBeanRequest request) {
    return apiClient.getTweet(request.getCatalog(), request.getPageToken()).compose(new ErrorTransformer<>());
  }

  @Override public Observable<PageBean<Tweet>> getUserTweet(PageBeanRequest request) {
    return apiClient.getUserTweet(request.getCatalog(), request.getPageToken()).compose(new ErrorTransformer<>());
  }

  @Override public Observable<NewsDetail> getNewsDetail(String id) {
    return apiClient.getNewsDetail(id).compose(new ErrorTransformer<>());
  }

  @Override public Observable<BlogDetail> getBlogDetail(String id) {
    return apiClient.getBlogDetail(id).compose(new ErrorTransformer<>());
  }

  @Override public Observable<QuestionDetail> getQuestionDetail(String id) {
    return apiClient.getQuestionDetail(id).compose(new ErrorTransformer<>());
  }

  @Override public Observable<EventDetail> getEventDetail(String id) {
    return apiClient.getEventDetail(id).compose(new ErrorTransformer<>());
  }

  @Override public Observable<Tweet> getTweetDetail(String id) {
    return apiClient.getTweetDetail(id).compose(new ErrorTransformer<>());
  }

  @Override public Observable<SoftwareDetail> getSoftwareDetail(String id) {
    return apiClient.getSoftwareDetail(id).compose(new ErrorTransformer<>());
  }

  @Override public Observable<ShakeNews> getShake() {
    return apiClient.getShake().compose(new ErrorTransformer<>());
  }

  @Override public Observable pubTweet(String content) {
    return apiClient.pubTweet(content).compose(new ErrorTransformer());
  }
}
