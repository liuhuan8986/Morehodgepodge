package com.example.administrator.badgerdemo.rxjava;

import com.example.administrator.badgerdemo.retrofit.ExceptionHandle;

import io.reactivex.observers.DefaultObserver;

/**
 * 只接收上传进度，上传后，后台返回的结果 自己写观察者Observer接收
 */
public abstract class MultiFileUploadObserver extends FileUploadObserver {
    @Override
    public void onNext(Object o) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onFail(ExceptionHandle.ResponeThrowable throwable) {

    }
}
