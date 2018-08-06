package com.example.liuyulong.myapplication.viper.interactor;

import com.example.liuyulong.myapplication.viper.base_viper.IBaseViperInteractor;
import com.example.liuyulong.myapplication.viper.contracts.MainContracts;
import com.example.liuyulong.myapplication.viper.module.MainViperModule;

public class MainViperInteractor
        extends IBaseViperInteractor<MainContracts.MainInteractorOutputImp, MainViperModule>
        implements MainContracts.MainInteractorImp {

    @Override
    public String loadData(String string) {
        return viperModule.getData();
    }
}
