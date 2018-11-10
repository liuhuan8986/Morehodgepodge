package com.example.administrator.badgerdemo.rxjava;

import io.reactivex.observers.DefaultObserver;

/**
 * 上传进度 和上传后,后台返回的结果 都可以使用FileUploadObserver来接收
 * @param <T>
 */
public abstract class FileUploadObserver<T> extends CommonObserver<T> {

    // 上传进度回调
    public abstract void onProgress(int progress);

    // 监听进度的改变
    public void onProgressChange(long bytesWritten, long contentLength) {
        onProgress((int) (bytesWritten * 100 / contentLength));
    }
}
