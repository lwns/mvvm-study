package com.study.data.remote.service;

import com.study.data.bean.Banner;
import com.study.data.bean.Blog;
import com.study.data.bean.BlogDetail;
import com.study.data.bean.Event;
import com.study.data.bean.EventDetail;
import com.study.data.bean.Mention;
import com.study.data.bean.News;
import com.study.data.bean.NewsDetail;
import com.study.data.bean.Question;
import com.study.data.bean.QuestionDetail;
import com.study.data.bean.ShakeNews;
import com.study.data.bean.SoftwareDetail;
import com.study.data.bean.SubBean;
import com.study.data.bean.Tweet;
import com.study.data.bean.UserV2;
import com.study.data.bean.base.PageBean;
import com.study.data.remote.BaseResponse;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * User: tangpeng.yang
 * Date: 04/06/2018
 * Description:
 * FIXME
 */
public interface ApiClient {
  @GET("action/apiv2/banner") Observable<BaseResponse<PageBean<Banner>>> getBanner(@Query("catalog") String catalog);

  @GET("action/apiv2/news") Observable<BaseResponse<PageBean<News>>> getNews(@Query("pageToken") String pageToken);

  @GET("action/apiv2/blog") Observable<BaseResponse<PageBean<Blog>>> getBlog(@Query("catalog") String catalog,
      @Query("pageToken") String pageToken);

  @GET("action/apiv2/question") Observable<BaseResponse<PageBean<Question>>> getQuestion(@Query("catalog") String catalog,
      @Query("pageToken") String pageToken);

  @GET("action/apiv2/event") Observable<BaseResponse<PageBean<Event>>> getEvent(@Query("pageToken") String pageToken);

  @GET("action/apiv2/tweets") Observable<BaseResponse<PageBean<Tweet>>> getTweet(@Query("type") String tag,
      @Query("pageToken") String pageToken);

  @GET("action/apiv2/tweets") Observable<BaseResponse<PageBean<Tweet>>> getUserTweet(@Query("authorId") String authorId,
      @Query("pageToken") String pageToken);

  @GET("action/apiv2/user_info") Observable<BaseResponse<UserV2>> getUserInfo();

  @GET("action/apiv2/user_msg_mentions") Observable<BaseResponse<PageBean<Mention>>> getMineMessage(
      @Query("pageToken") String pageToken);

  @GET("action/apiv2/blog_list") Observable<BaseResponse<PageBean<SubBean>>> getMineBlog(@Query("authorId") String authorId,
      @Query("authorName") String authorName, @Query("pageToken") String pageToken);

  @Multipart @POST("action/apiv2/user_edit_portrait") Observable<BaseResponse<UserV2>> uploadUserHead(
      @Part MultipartBody.Part file);

  @GET("action/apiv2/news") Observable<BaseResponse<NewsDetail>> getNewsDetail(@Query("id") String id);

  @GET("action/apiv2/blog") Observable<BaseResponse<BlogDetail>> getBlogDetail(@Query("id") String id);

  @GET("action/apiv2/question") Observable<BaseResponse<QuestionDetail>> getQuestionDetail(@Query("id") String id);

  @GET("action/apiv2/event") Observable<BaseResponse<EventDetail>> getEventDetail(@Query("id") String id);

  @GET("action/apiv2/tweet") Observable<BaseResponse<Tweet>> getTweetDetail(@Query("id") String id);

  @GET("action/apiv2/software") Observable<BaseResponse<SoftwareDetail>> getSoftwareDetail(@Query("id") String id);

  @GET("action/apiv2/shake_news") Observable<BaseResponse<ShakeNews>> getShake();

  @FormUrlEncoded @POST("action/apiv2/tweet") Observable<BaseResponse> pubTweet(@Field("content") String content);
}
