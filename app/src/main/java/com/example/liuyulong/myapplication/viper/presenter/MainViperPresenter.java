package com.example.liuyulong.myapplication.viper.presenter;

import com.example.liuyulong.myapplication.viper.base_viper.IBaseViperPresenter;
import com.example.liuyulong.myapplication.viper.contracts.MainContracts;
import com.example.liuyulong.myapplication.viper.interactor.MainViperInteractor;
import com.example.liuyulong.myapplication.viper.module.MainViperModule;
import com.example.liuyulong.myapplication.viper.router.MainViperRouter;

import io.reactivex.disposables.Disposable;

public class MainViperPresenter
        extends IBaseViperPresenter<MainContracts.MainViewImp, MainViperInteractor, MainViperModule, MainViperRouter>
        implements MainContracts.MainPresenterImp, MainContracts.MainInteractorOutputImp{

    private Disposable disposable;

    public void show() {
        viperView.showToast(viperInteractor.loadData("i come from viperInteractor!"));
    }

    public void click() {
        viperRouter.gotoActivity();
//        disposable = BaseApplication
//                .getInstance()
//                .getAppComponent()
//                .getApiService()
//                .listRepos("wu-leaf")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<Repo>>() {
//                    @Override
//                    public void accept(List<Repo> repos) throws Exception {
//                        Log.d("DownObserverWraper", "Real onSuccess");
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.d("DownObserverWraper", "Real Fail");
//                    }
//                });

    }

//    public void ondesdroy() {
//        CompositeDisposable compositeDisposable = new CompositeDisposable();
//        compositeDisposable.add(disposable);
//        compositeDisposable.clear();
//    }
}
