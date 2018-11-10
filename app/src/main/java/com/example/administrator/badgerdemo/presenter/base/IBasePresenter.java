package com.example.administrator.badgerdemo.presenter.base;

public interface IBasePresenter<V> {
    /**
     * 判断 presenter 是否与 view 建立联系，防止出现内存泄露状况
     */
    boolean isViewAttach();

    /**
     * 断开 presenter 与 view 直接的联系
     */
    void detachView();
}
