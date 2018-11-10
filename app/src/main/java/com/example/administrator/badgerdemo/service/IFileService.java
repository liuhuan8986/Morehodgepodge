package com.example.administrator.badgerdemo.service;

import com.example.administrator.badgerdemo.bean.ResultData;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface IFileService {

    /**
     * 上传单个文件
     * @return
     */
    @Multipart
    @POST
    Observable<ResultData<String>> uploadFile(@Url String url, @Part MultipartBody.Part file);

    /**
     * 上传多个文件
     * @return
     */
    @POST
    Observable<ResultData<String>> uploadFiles();

    @POST
    Observable<ResultData<String>> upLoadFileAndParams(@Url String url , @Body MultipartBody multipartBody);
}
