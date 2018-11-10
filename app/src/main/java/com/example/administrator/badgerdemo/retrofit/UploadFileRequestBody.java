package com.example.administrator.badgerdemo.retrofit;

import com.example.administrator.badgerdemo.bean.ResultData;
import com.example.administrator.badgerdemo.rxjava.FileUploadObserver;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * 扩展OkHttp的请求体，实现上传时的进度提示
 */
public class UploadFileRequestBody<T> extends RequestBody {


    private RequestBody mRequestBody;
    private FileUploadObserver<T> fileUploadObserver;
    private BufferedSink mBufferedSink;
    public UploadFileRequestBody(File file, FileUploadObserver<T> fileUploadObserver) {
        this.mRequestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        this.fileUploadObserver = fileUploadObserver;
    }


    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

        if (sink instanceof Buffer) {
            // Log Interceptor
            mRequestBody.writeTo(sink);
            return;
        }
        if (mBufferedSink == null) {
            mBufferedSink = Okio.buffer(new CountingSink(sink));
        }
        mRequestBody.writeTo(mBufferedSink);
        mBufferedSink.flush();
    }

    protected final class CountingSink extends ForwardingSink {

        private long bytesWritten = 0;

        public CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);

            bytesWritten += byteCount;
            if (fileUploadObserver != null) {
                fileUploadObserver.onProgressChange(bytesWritten, contentLength());
            }

        }
    }

  /*  private Sink wrapSink(Sink sink) {
        return new ForwardingSink(sink) {

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (mProgressInfo.total == 0) {
                    mProgressInfo.total = contentLength();
                }
                mProgressInfo.current += byteCount;
                mEmitter.onNext(mProgressInfo);
            }
        };
    }*/
}
