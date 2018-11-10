package com.example.administrator.badgerdemo.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.badgerdemo.bean.ResultData;
import com.example.administrator.badgerdemo.bean.UserInfo;
import com.example.administrator.badgerdemo.constant.Constant;
import com.example.administrator.badgerdemo.modle.FileUploadModel;
import com.example.administrator.badgerdemo.modle.IFileUploadModel;
import com.example.administrator.badgerdemo.modle.ILoginModel;
import com.example.administrator.badgerdemo.modle.LoginModel;
import com.example.administrator.badgerdemo.presenter.base.BasePresenter;
import com.example.administrator.badgerdemo.retrofit.ExceptionHandle;
import com.example.administrator.badgerdemo.retrofit.UploadFileRequestBody;
import com.example.administrator.badgerdemo.rxjava.CommonObserver;
import com.example.administrator.badgerdemo.rxjava.FileUploadCombination;
import com.example.administrator.badgerdemo.rxjava.FileUploadObserver;
import com.example.administrator.badgerdemo.view.ILoginView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LoginPresenter extends BasePresenter<ILoginView> {
    private ILoginModel iLoginModel;

    private IFileUploadModel iFileUploadModel;
    public LoginPresenter(@NonNull ILoginView view) {
        super(view);
        iLoginModel = new LoginModel();
        iFileUploadModel = new FileUploadModel();
    }


    public void login(String Mobile, String Code, String RegisterType, String RegisterID){
        getView().showLoading();
        Observable<ResultData<UserInfo>> observable = iLoginModel.login(Mobile,Code,RegisterType,RegisterID);
        compositeDisposable.add(observable.subscribe());
        observable.subscribe(new CommonObserver<UserInfo>() {
            @Override
            public void onSuccess(UserInfo data) {
                if(isViewAttach()){
                    getView().closeLoading();
                    getView().setData(data);
                }
            }

            @Override
            public void onFail(ExceptionHandle.ResponeThrowable throwable) {
                if(isViewAttach()){
                    getView().closeLoading();
                    getView().showToast(throwable.message);
                }
            }
        });
    }

    /**
     * 上传单个文件 带进度
     * @param name
     * @param filePath
     */
    public void upLoadFile(String name,String filePath){
        //"/storage/emulated/0/DCIM/Camera/IMG_20180822_104144.jpg";
        File file  =new File(filePath);
        FileUploadObserver<String> fileUploadObserver = new FileUploadObserver<String>(){

            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onFail(ExceptionHandle.ResponeThrowable throwable) {

            }

            @Override
            public void onProgress(int progress) {

            }
        };
        UploadFileRequestBody<String> requestBody = new UploadFileRequestBody(file,fileUploadObserver);
        iFileUploadModel.upLoadFile(Constant.SERVICE_URL,name,filePath,requestBody).subscribe(fileUploadObserver);
    }

    /**
     * 上传多个文件 不带进度
     * @param filePaths
     */
    public void upLoadFiles(List<String> filePaths){
        Map<String,String> map = new HashMap<>();
        map.put("Module","chat");
        getView().showLoading();
        iFileUploadModel.upLoadFiles(Constant.UP_LOAD_URL,filePaths,map).subscribe(new CommonObserver<String>() {
            @Override
            public void onSuccess(String data) {
                if(isViewAttach()){
                    getView().closeLoading();
                    getView().setText(data);
                }
            }

            @Override
            public void onFail(ExceptionHandle.ResponeThrowable throwable) {
                if(isViewAttach()){
                    getView().closeLoading();
                    getView().setText(throwable.message);
                }
            }
        });
    }

    /**
     * 上传多个文件  每个文件带进度
     * @param list
     */
    public void upLoadFiles2(List<FileUploadCombination> list ){
        Map<String,String> map = new HashMap<>();
        map.put("Module","chat");
        getView().showLoading();
        iFileUploadModel.upLoadFilesProgress(Constant.UP_LOAD_URL,list,map).subscribe(new CommonObserver<String>() {
            @Override
            public void onSuccess(String data) {
                if(isViewAttach()){
                    getView().closeLoading();
                    getView().setText(data);
                }
            }

            @Override
            public void onFail(ExceptionHandle.ResponeThrowable throwable) {
                if(isViewAttach()){
                    getView().closeLoading();
                    getView().setText(throwable.message);
                }
            }
        });
    }
}
