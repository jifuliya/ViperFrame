package com.example.liuyulong.myapplication;

import com.example.liuyulong.myapplication.di.AppComponent;
import com.example.liuyulong.myapplication.di.DaggerAppComponent;
import com.example.liuyulong.myapplication.viper.util.rxutil.DownObserverWraper;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.BiFunction;
import io.reactivex.plugins.RxJavaPlugins;

public class BaseApplication extends DaggerApplication {

    private AppComponent appComponent;
    private static BaseApplication instance;

    public static BaseApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRxPlugin();
    }

    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);
        this.appComponent = appComponent;
        return appComponent;
    }

    public  AppComponent getAppComponent() {
        return appComponent;
    }

    @SuppressWarnings("unchecked")
    private void initRxPlugin(){
        RxJavaPlugins.setOnObservableSubscribe(new BiFunction<Observable, Observer, Observer>() {
            @Override
            public Observer apply(Observable observable, Observer observer) throws Exception {
                return new DownObserverWraper(observer);
            }
        });
    }
}
