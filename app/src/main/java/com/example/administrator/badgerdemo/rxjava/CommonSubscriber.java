package com.example.administrator.badgerdemo.rxjava;


import com.example.administrator.badgerdemo.bean.ResultData;
import com.example.administrator.badgerdemo.retrofit.ExceptionHandle;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 * 和 Flowable 配套使用
 * @param <T>
 */
public abstract class CommonSubscriber<T> implements Subscriber<ResultData<T>> {
    private static final int RESULT_CODE_SCCESS = 1000;
    @Override
    public void onSubscribe(Subscription s) {
    }
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
    public void onError(Throwable t) {
        ExceptionHandle.ResponeThrowable responeThrowable =ExceptionHandle.handleException(t);
        onFail(responeThrowable);
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T data);
    public abstract void onFail(ExceptionHandle.ResponeThrowable throwable);
}
