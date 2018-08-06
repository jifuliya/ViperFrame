package com.example.liuyulong.myapplication.di.module;

import com.example.liuyulong.myapplication.di.scope.ActivityScope;
import com.example.liuyulong.myapplication.viper.interactor.MainViperInteractor;
import com.example.liuyulong.myapplication.viper.module.MainViperModule;
import com.example.liuyulong.myapplication.viper.presenter.MainViperPresenter;
import com.example.liuyulong.myapplication.viper.router.MainViperRouter;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class MainActivityModule {

    @Provides
    MainViperPresenter getPresenter() {
        return new MainViperPresenter();
    }

    @Provides
    MainViperInteractor getInteractor() {
        return new MainViperInteractor();
    }

    @Provides
    MainViperRouter getRouter() {
        return new MainViperRouter();
    }

    @Provides
    MainViperModule getModule() {
        return new MainViperModule();
    }
}
