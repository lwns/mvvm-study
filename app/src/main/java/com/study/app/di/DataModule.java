package com.study.app.di;

import com.study.app.BuildConfig;
import com.study.data.MainRepository;
import com.study.data.remote.ServiceFactor;
import com.study.data.remote.service.ApiClient;
import com.study.data.repository.MainRepositoryImp;
import com.timper.lonelysword.data.executor.JobExecutor;
import com.timper.lonelysword.data.executor.ThreadExecutor;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static com.study.data.remote.Global.API_PATH;

/**
 * User: tangpeng.yang
 * Date: 20/03/2018
 * Description:
 * FIXME
 */
@Module @Singleton public class DataModule {

  @Provides @Singleton public ApiClient provideMainService() {
    return ServiceFactor.createService(API_PATH, BuildConfig.DEBUG ? true : false, ApiClient.class);
  }

  @Provides @Singleton public MainRepository bindMainRepository(MainRepositoryImp mainRepository) {
    return mainRepository;
  }

  @Provides @Singleton public ThreadExecutor bindThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }
}

