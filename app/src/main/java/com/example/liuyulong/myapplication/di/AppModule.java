package com.example.liuyulong.myapplication.di;

import android.content.Context;

import com.example.liuyulong.myapplication.BaseApplication;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Singleton
@Module(includes = NetModule.class)
public abstract class AppModule {

    private BaseApplication application;

    public AppModule(BaseApplication application){
        this.application = application;
    }

    @Binds
    abstract Context provideContext(BaseApplication application);
}