package com.example.administrator.badgerdemo.retrofit;

import android.content.Context;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.example.administrator.badgerdemo.MyApplication;
import com.example.administrator.badgerdemo.constant.Constant;
import com.example.administrator.badgerdemo.service.BaseService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;
    private final int DEFAULT_TIMEOUT = 30;
    private static OkHttpClient mOkHttpClient;

  /*  private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(mContext);
    }*/
    private RetrofitClient(Context context) {
        initOkHttpClient(context);
    }

    public static RetrofitClient getInstance() {
        if(instance ==null){
            synchronized (RetrofitClient.class){
                if(instance ==null){
                    instance = new RetrofitClient(MyApplication.getInstance());
                }
            }
        }
        return instance;
    }


    public <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new Retrofit2ConverterFactory())
                .build();
        return retrofit.create(clazz);
    }
    public <T> T createApi(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(Constant.SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
    /**
     * 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志,设置UA拦截器
     */
    private void initOkHttpClient(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitClient.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    //Cache cache = new Cache(new File(BilibiliApp.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 10);
                    mOkHttpClient = new OkHttpClient.Builder()
                            //.cache(cache)
                            .addInterceptor(interceptor)
                            //.addNetworkInterceptor(new CacheInterceptor())
                            //.addNetworkInterceptor(new StethoInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            //.cookieJar(new CookieManger(context))
                            //.addInterceptor(new UserAgentInterceptor())
                            .build();
                }
            }
        }
    }
}
