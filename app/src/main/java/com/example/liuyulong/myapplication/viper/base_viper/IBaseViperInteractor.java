package com.example.liuyulong.myapplication.viper.base_viper;

public class IBaseViperInteractor<I extends IBaseContracts.BaseInteractorOutputImp,M extends IBaseViperModule>
        implements IBaseContracts.BaseInteractorImp {

    protected I viperInteractorOutput;

    protected M viperModule;

    @SuppressWarnings("uncheck")
    public void onAttch(I baseInteractorOutput, M viperModule) {
        this.viperInteractorOutput = baseInteractorOutput;
        this.viperModule = viperModule;
    }

    @Override
    public void onDetach() {
        if (viperInteractorOutput != null || viperModule != null) {
            viperInteractorOutput = null;
            viperModule = null;
        }
    }
}
