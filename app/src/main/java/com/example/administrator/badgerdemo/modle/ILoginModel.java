package com.example.administrator.badgerdemo.modle;

import com.example.administrator.badgerdemo.bean.ResultData;
import com.example.administrator.badgerdemo.bean.UserInfo;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface ILoginModel {
    Observable<ResultData<UserInfo>> login(String Mobile, String Code, String RegisterType, String RegisterID);
}
