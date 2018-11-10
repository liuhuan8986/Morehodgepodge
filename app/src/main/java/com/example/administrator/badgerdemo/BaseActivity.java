package com.example.administrator.badgerdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.badgerdemo.presenter.base.BasePresenter;
import com.example.administrator.badgerdemo.view.IBaseView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{
    private P presenter;
    private ProgressDialog progressDialog;
    public abstract P onBindPresenter();


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        if(!isFinishing()){
            progressDialog.show();
        }
    }

    @Override
    public void closeLoading() {
        if(!isFinishing()){
            if(progressDialog!=null){
                progressDialog.dismiss();
            }
        }
    }

    public P getPresenter() {
        if (presenter == null) {
            presenter = onBindPresenter();
        }
        return presenter;
    }

    @Override
    protected void onDestroy() {
        if(presenter!=null){
            presenter.detachView();
        }
        super.onDestroy();
    }
}
