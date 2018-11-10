package com.example.administrator.badgerdemo.modle;

import com.example.administrator.badgerdemo.bean.ResultData;
import com.example.administrator.badgerdemo.retrofit.RetrofitClient;
import com.example.administrator.badgerdemo.retrofit.UploadFileRequestBody;
import com.example.administrator.badgerdemo.rxjava.FileUploadCombination;
import com.example.administrator.badgerdemo.rxjava.FileUploadObserver;
import com.example.administrator.badgerdemo.rxjava.RxJavaUitls;
import com.example.administrator.badgerdemo.service.IFileService;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileUploadModel implements IFileUploadModel {
    @Override
    public Observable<ResultData<String>> upLoadFile(String url,String fileName, String filePath,UploadFileRequestBody<String> uploadFileRequestBody) {
        IFileService service = RetrofitClient.getInstance().createApi(IFileService.class);
        File file = new File(filePath);
        MultipartBody.Part part = MultipartBody.Part.createFormData(fileName, file.getName(), uploadFileRequestBody);
        return  service.uploadFile(url, part).compose(RxJavaUitls.<ResultData<String>>schedulersObservableTransformer());
    }

    @Override
    public Observable<ResultData<String>>  upLoadFiles(String url, List<String> filePaths) {
        return null;
    }

    @Override
    public Observable<ResultData<String>> upLoadFiles(String url, List<String> filePaths, Map<String,String> params) {
        //构建body
        //addFormDataPart()第一个参数为表单名字，这是和后台约定好的
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //注意，file是后台约定的参数，如果是多图，file[]，如果是单张图片，file就行
        builder.setType(MultipartBody.FORM);

        if(params!=null){
            for (String key:params.keySet()){
                builder.addFormDataPart(key,params.get(key));
            }
        }

        if(filePaths!=null){
            for (String path : filePaths){
                File file = new File(path);
                builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
            }
        }
        IFileService service = RetrofitClient.getInstance().createApi(IFileService.class);
        return service.upLoadFileAndParams(url,builder.build()).compose(RxJavaUitls.<ResultData<String>>schedulersObservableTransformer());
    }



    public Observable<ResultData<String>> upLoadFilesProgress(String url, List<FileUploadCombination> list, Map<String,String> params) {
        //构建body
        //addFormDataPart()第一个参数为表单名字，这是和后台约定好的
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //注意，file是后台约定的参数，如果是多图，file[]，如果是单张图片，file就行
        builder.setType(MultipartBody.FORM);

        if(params!=null){
            for (String key:params.keySet()){
                builder.addFormDataPart(key,params.get(key));
            }
        }
        if(list!=null){
           for (FileUploadCombination combination:list){
               File file =  new File(combination.getPath());
               FileUploadObserver fileUploadObserver =  combination.getFileUploadObserver();
               builder.addFormDataPart("file",file.getName() ,new UploadFileRequestBody(file,fileUploadObserver));
           }
        }
        IFileService service = RetrofitClient.getInstance().createApi(IFileService.class);
        return service.upLoadFileAndParams(url,builder.build()).compose(RxJavaUitls.<ResultData<String>>schedulersObservableTransformer());
    }
}
