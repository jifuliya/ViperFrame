package com.example.liuyulong.myapplication.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.liuyulong.myapplication.R;
import com.example.liuyulong.myapplication.databinding.ActivityMainBinding;
import com.example.liuyulong.myapplication.viper.base_viper.BaseViperActivity;
import com.example.liuyulong.myapplication.viper.contracts.MainContracts;
import com.example.liuyulong.myapplication.viper.interactor.MainViperInteractor;
import com.example.liuyulong.myapplication.viper.module.MainViperModule;
import com.example.liuyulong.myapplication.viper.presenter.MainViperPresenter;
import com.example.liuyulong.myapplication.viper.router.MainViperRouter;

import javax.inject.Inject;

public class MainActivity extends BaseViperActivity<MainViperPresenter> implements MainContracts.MainViewImp {

    @Inject
    MainViperModule mainViperModule;

    @Inject
    MainViperPresenter mainViperPresenter;

    @Inject
    MainViperInteractor mainViperInteractor;

    @Inject
    MainViperRouter mainViperRouter;

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (viperPresenter != null) {
            viperPresenter.onAttch(this, mainViperInteractor, mainViperModule, mainViperRouter);
        }

        viperPresenter.show();
        mainBinding.tex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viperPresenter.click();
            }
        });
    }

    @Override
    protected MainViperPresenter initPresenter() {
        return mainViperPresenter;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}