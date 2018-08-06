package com.example.liuyulong.myapplication.viper.base_viper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseViperActivity<P extends IBaseViperPresenter>
        extends DaggerAppCompatActivity
        implements IBaseContracts.BaseViewImp {

    public P viperPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viperPresenter = initPresenter();
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viperPresenter.onDetach();
    }
}
