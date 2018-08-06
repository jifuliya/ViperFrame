package com.example.liuyulong.myapplication.viper.base_viper;

public abstract class IBaseViperPresenter<V extends IBaseContracts.BaseViewImp,
        I extends IBaseViperInteractor, M extends IBaseViperModule, R extends IBaseViperRutor>
        implements IBaseContracts.BasePresenterImp, IBaseContracts.BaseInteractorOutputImp {

    protected V viperView;

    protected I viperInteractor;

    protected M viperModule;

    protected R viperRouter;

    @SuppressWarnings("unchecked")
    public void onAttch(V baseView, I interactor, M module, R router) {
        this.viperView = baseView;
        this.viperInteractor = interactor;
        this.viperModule = module;
        this.viperRouter = router;
        viperInteractor.onAttch(this, viperModule);
        router.bindRouterView((BaseViperActivity) baseView);
    }

    @Override
    public void onDetach() {
        if (viperView != null || viperInteractor != null || viperModule != null) {
            viperInteractor.onDetach();
            viperView = null;
            viperInteractor = null;
            viperModule = null;
        }
    }
}
