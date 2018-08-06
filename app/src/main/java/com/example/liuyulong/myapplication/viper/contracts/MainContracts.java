package com.example.liuyulong.myapplication.viper.contracts;

import com.example.liuyulong.myapplication.viper.base_viper.IBaseContracts;
import com.example.liuyulong.myapplication.viper.base_viper.IBaseContracts.BaseInteractorImp;
import com.example.liuyulong.myapplication.viper.base_viper.IBaseContracts.BaseInteractorOutputImp;
import com.example.liuyulong.myapplication.viper.base_viper.IBaseContracts.BaseModuleImp;
import com.example.liuyulong.myapplication.viper.base_viper.IBaseContracts.BasePresenterImp;
import com.example.liuyulong.myapplication.viper.base_viper.IBaseContracts.BaseViewImp;

public class MainContracts {

    public interface MainViewImp extends BaseViewImp {

        void showToast(String msg);
    }

    public interface MainModule extends BaseModuleImp {
        String getData();
    }

    public interface MainPresenterImp extends BasePresenterImp {
    }

    public interface MainInteractorImp extends BaseInteractorImp {
        String loadData(String string);
    }

    public interface MainInteractorOutputImp extends BaseInteractorOutputImp {
    }

    public interface MainRouterImp extends IBaseContracts.BaseRouterImp {
    }
}
