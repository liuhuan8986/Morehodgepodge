package com.example.administrator.badgerdemo.presenter.base;

import android.support.annotation.NonNull;

import com.example.administrator.badgerdemo.view.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public  class BasePresenter<V extends IBaseView> implements IBasePresenter {
    // 防止 Activity 不走 onDestory() 方法，所以采用弱引用来防止内存泄漏
    private WeakReference<V> mViewRef;
    /**
     * 这个是用来取消订阅的
     */
    public CompositeDisposable compositeDisposable;

    public BasePresenter(@NonNull V view) {
        compositeDisposable = new CompositeDisposable();
        attachView(view);
    }

    public V getView() {
        return mViewRef.get();
    }

    private void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    @Override
    public boolean isViewAttach() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public void detachView() {
        if(compositeDisposable!=null && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
            compositeDisposable =null;
        }

        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
