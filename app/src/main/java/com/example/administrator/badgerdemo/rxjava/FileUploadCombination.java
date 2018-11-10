package com.example.administrator.badgerdemo.rxjava;


/**
 * 当上传文件需要进度时 需要这个类
 */
public class FileUploadCombination {
    private String path;
    private FileUploadObserver fileUploadObserver;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileUploadObserver getFileUploadObserver() {
        return fileUploadObserver;
    }

    public void setFileUploadObserver(FileUploadObserver fileUploadObserver) {
        this.fileUploadObserver = fileUploadObserver;
    }
}
