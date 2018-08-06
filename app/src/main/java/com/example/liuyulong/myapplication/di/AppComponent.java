package com.example.liuyulong.myapplication.di;

import android.app.Application;

import com.example.liuyulong.myapplication.BaseApplication;
import com.example.liuyulong.myapplication.api.ApiService;
import com.example.liuyulong.myapplication.di.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@ApplicationScope
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityInjectorDispatcher.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    ApiService getApiService();

    OkHttpClient getOkHttp();

    Retrofit getRetrofit();
}
