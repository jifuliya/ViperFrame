package com.example.liuyulong.myapplication.viper.base_viper;

public class IBaseViperRutor<A extends BaseViperActivity> implements IBaseContracts.BaseRouterImp {

    A view;

    public void bindRouterView(A view){
        this.view = view;
    }

    @Override
    public void gotoActivity() {

    }
}
