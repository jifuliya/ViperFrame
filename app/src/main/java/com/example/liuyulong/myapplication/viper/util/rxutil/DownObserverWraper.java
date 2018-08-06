package com.example.liuyulong.myapplication.viper.util.rxutil;

import android.util.Log;

import com.example.liuyulong.myapplication.Exceptions;
import com.example.liuyulong.myapplication.viper.module.model.BaseResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DownObserverWraper<T> implements Observer<T> {

    private static final String TAG = "DownObserverWraper";

    private Observer<T> actual;

    public DownObserverWraper(Observer<T> actual) {
        this.actual = actual;
    }

    @Override
    public void onSubscribe(Disposable d) {
        actual.onSubscribe(d);
    }

    @Override
    public void onNext(T t) {
        Log.d(TAG, "Hooked onSuccess");
        hookOnNext(t);
        actual.onNext(t);
    }

    private void hookOnNext(T t) {
        if (t instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) t;
            if (baseResponse.getCode() == 100) {

                //TODO

                throw new Exceptions.TokenExpired(); //注意这里的trick
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "Hooked onError");
        hookOnError(e);
        actual.onError(e);
    }

    private void hookOnError(Throwable e){
        if (e instanceof ConnectException) {
            Log.e(TAG, "Connect failed: ", e);
            //处理ConnectException
            actual.onError(new Exceptions.Offline()); //注意这里的trick
            return;
        }

        if (e instanceof SocketTimeoutException) {
            Log.e(TAG, "Time out ", e);
            //处理SocketTimeoutException
            actual.onError(new Exceptions.TimeOut()); //注意这里的trick
            return;
        }
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "Hooked onComplete");
        actual.onComplete();
    }
}
