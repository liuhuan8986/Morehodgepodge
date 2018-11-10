package com.example.administrator.badgerdemo.view;

import android.content.Context;

public interface IBaseView {
    Context getContext();

    void showToast(String msg);

    /**
     * 显示正在加载 view
     */
    void showLoading();

    /**
     * 关闭正在加载 view
     */
    void closeLoading();
}
