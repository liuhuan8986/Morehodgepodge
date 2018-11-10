package com.example.administrator.badgerdemo.modle;

import com.example.administrator.badgerdemo.bean.ResultData;
import com.example.administrator.badgerdemo.retrofit.UploadFileRequestBody;
import com.example.administrator.badgerdemo.rxjava.FileUploadCombination;
import com.example.administrator.badgerdemo.rxjava.FileUploadObserver;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface IFileUploadModel {

    /**
     * 上传单个文件
     * @param fileName 文件名
     * @param filePath 文件路径
     * @return
     */
    Observable<ResultData<String>>  upLoadFile(String url, String fileName, String filePath, UploadFileRequestBody<String> uploadFileRequestBody);

    Observable<ResultData<String>>  upLoadFiles(String url,List<String> filePaths);

    /**
     * 上传多个文件 不能监听进度
     * @param url
     * @param filePaths
     * @param params
     * @return
     */
    Observable<ResultData<String>>  upLoadFiles(String url,List<String> filePaths, Map<String,String> params);

    /**
     *上传多个文件 每个文件可以监听进度
     * @param url
     * @param params
     * @param list
     * @return
     */
    Observable<ResultData<String>> upLoadFilesProgress(String url, List<FileUploadCombination> list, Map<String,String> params);
}
