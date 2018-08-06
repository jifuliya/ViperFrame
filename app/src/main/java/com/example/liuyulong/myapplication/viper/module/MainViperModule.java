package com.example.liuyulong.myapplication.viper.module;

import com.example.liuyulong.myapplication.viper.base_viper.IBaseViperModule;
import com.example.liuyulong.myapplication.viper.contracts.MainContracts;

public class MainViperModule extends IBaseViperModule implements MainContracts.MainModule{

    @Override
    public String getData() {
        return "moduel";
    }
}
