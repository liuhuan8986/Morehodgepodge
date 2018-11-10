package com.example.administrator.badgerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.administrator.badgerdemo.bean.UserInfo;
import com.example.administrator.badgerdemo.db.GreenDaoManager;
import com.example.administrator.badgerdemo.presenter.LoginPresenter;
import com.example.administrator.badgerdemo.rxjava.FileUploadCombination;
import com.example.administrator.badgerdemo.rxjava.MultiFileUploadObserver;
import com.example.administrator.badgerdemo.rxjava.RxJavaUitls;
import com.example.administrator.badgerdemo.view.ILoginView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DefaultObserver;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView{

    //LoginPresenter loginPresenter;
    private TextView textView;
    Button test;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.txt);
        test = findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().login("13570649568","000111","android","");
                //testUplaods2();
            }
        });

    }

    @Override
    public void setData(UserInfo userInfo) {
        //textView.setText(JSON.toJSONString(userInfo));
        GreenDaoManager.getInstance().getDaoSession().getUserInfoDao().save(userInfo);
        List<UserInfo> list = GreenDaoManager.getInstance().getDaoSession().getUserInfoDao().loadAll();
        textView.setText(JSON.toJSONString(list));
    }

    @Override
    public void setText(String data) {
        textView.setText(data);
    }

    @Override
    public LoginPresenter onBindPresenter() {
        return new LoginPresenter(this);
    }

    /**
     * 上传多个文件 没有监听
     */
    private void testUploads1(){
        List<String> paths = new ArrayList<>();
        paths.add("/storage/emulated/0/DCIM/Camera/IMG_20180822_104144.jpg");
        //paths.add("/storage/emulated/0/DCIM/Camera/IMG_20170228_094837.jpg");
        getPresenter().upLoadFiles(paths);
    }
    /**
     * 上传多个文件 有监听
     */
    private void testUplaods2(){
        List<FileUploadCombination> list = new ArrayList<>();
        //封装的实体
        FileUploadCombination combination = new FileUploadCombination();
        //设置要上传的文件
        combination.setPath("/storage/emulated/0/DCIM/Camera/IMG_20180822_104144.jpg");
        //设置该文件的上传进度监听
        combination.setFileUploadObserver(new MultiFileUploadObserver() {
            @Override
            public void onProgress(final int progress) {
                //这里还是在子线程
                Log.i("progress：",progress+"");
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(progress);
                    }
                }).compose(RxJavaUitls.<Integer>schedulersObservableTransformer()).subscribe(new DefaultObserver<Integer>() {
                    @Override
                    public void onNext(Integer value) {
                        textView.setText(value+"");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
        list.add(combination);
        getPresenter().upLoadFiles2(list);
    }

}
