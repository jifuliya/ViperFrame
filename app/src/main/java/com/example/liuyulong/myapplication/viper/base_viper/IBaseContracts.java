package com.example.liuyulong.myapplication.viper.base_viper;

public class IBaseContracts {

    public interface BaseViewImp {
    }

    public interface BaseModuleImp {
    }

    public interface BasePresenterImp {
        void onDetach();
    }

    public interface BaseInteractorImp {
        void onDetach();
    }

    public interface BaseInteractorOutputImp {
    }

    /*
     *intent for activity,if you need to change the activity,you should achieve it
     * use interface to do it
     **/
    public interface BaseRouterImp {

        void gotoActivity();
    }
}
