package com.study.data;

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
import com.timper.lonelysword.annotations.apt.UseCase;
import io.reactivex.Observable;

/**
 * User: tangpeng.yang
 * Date: 12/06/2018
 * Description:
 * FIXME
 */
public interface MainRepository {

  @UseCase Observable<PageBean<News>> getNews(String pageToken);

  @UseCase Observable<PageBean<Banner>> getBanner(String catalog);

  @UseCase Observable<PageBean<Blog>> getBlog(PageBeanRequest request);

  @UseCase Observable<PageBean<Question>> getQuestion(PageBeanRequest request);

  @UseCase Observable<PageBean<Event>> getEvent(String pageToken);

  @UseCase Observable<PageBean<Tweet>> getTweet(PageBeanRequest request);

  @UseCase Observable<PageBean<Tweet>> getUserTweet(PageBeanRequest request);

  @UseCase Observable<NewsDetail> getNewsDetail(String id);

  @UseCase Observable<BlogDetail> getBlogDetail(String id);

  @UseCase Observable<QuestionDetail> getQuestionDetail(String id);

  @UseCase Observable<SoftwareDetail> getSoftwareDetail(String id);

  @UseCase Observable<EventDetail> getEventDetail(String id);

  @UseCase Observable<Tweet> getTweetDetail(String id);

  @UseCase Observable<ShakeNews> getShake();

  @UseCase Observable pubTweet(String content);
}
