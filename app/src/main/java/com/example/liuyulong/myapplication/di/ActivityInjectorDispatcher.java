package com.example.liuyulong.myapplication.di;

import com.example.liuyulong.myapplication.di.module.MainActivityModule;
import com.example.liuyulong.myapplication.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityInjectorDispatcher {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();
}
