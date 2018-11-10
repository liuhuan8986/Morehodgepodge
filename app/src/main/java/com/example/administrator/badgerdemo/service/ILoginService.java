package com.example.administrator.badgerdemo.service;

import com.example.administrator.badgerdemo.bean.ResultData;
import com.example.administrator.badgerdemo.bean.UserInfo;
import com.example.administrator.badgerdemo.constant.Constant;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ILoginService {
    @FormUrlEncoded
    @POST(Constant.LOGIN_URL)
    Observable<ResultData<UserInfo>> login(@FieldMap Map<String,String> map);
}
