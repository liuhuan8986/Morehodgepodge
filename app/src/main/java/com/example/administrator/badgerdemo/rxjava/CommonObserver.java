package com.example.administrator.badgerdemo.rxjava;

import com.example.administrator.badgerdemo.bean.ResultData;
import com.example.administrator.badgerdemo.retrofit.ExceptionHandle;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * 和Observer配套使用
 * @param <T>
 */
public abstract class CommonObserver<T> extends DefaultObserver<ResultData<T>> {
    private static final int RESULT_CODE_SCCESS = 1000;

    @Override
    public void onNext(ResultData<T> data) {
        if(data!=null){
            if(data.getFlag()==RESULT_CODE_SCCESS){
                onSuccess(data.getData());
            }else{
                onError(new ExceptionHandle.ServiceThrowable(data.getMsg()));
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        //RxJavaPlugins.onError(e);
        ExceptionHandle.ResponeThrowable responeThrowable =ExceptionHandle.handleException(e);
        onFail(responeThrowable);

    }

    @Override
    public void onComplete() {

    }
    public abstract void onSuccess(T data);
    public abstract void onFail(ExceptionHandle.ResponeThrowable throwable);
}
