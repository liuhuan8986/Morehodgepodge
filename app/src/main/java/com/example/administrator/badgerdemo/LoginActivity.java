package com.example.administrator.badgerdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.badgerdemo.bean.Info;
import com.example.administrator.badgerdemo.bean.UserInfo;
import com.example.administrator.badgerdemo.bean.UserInfoDao;
import com.example.administrator.badgerdemo.db.GreenDaoManager;
import com.example.administrator.badgerdemo.presenter.LoginPresenter;
import com.example.administrator.badgerdemo.rxjava.FileUploadCombination;
import com.example.administrator.badgerdemo.rxjava.MultiFileUploadObserver;
import com.example.administrator.badgerdemo.rxjava.RxJavaUitls;
import com.example.administrator.badgerdemo.utils.LGImgCompressor;
import com.example.administrator.badgerdemo.view.ILoginView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    //LoginPresenter loginPresenter;
    private TextView textView;
    Button test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.txt);
        test = findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().login("13570649568", "000111", "android", "");
                //testUplaods2();
            }
        });

        //UserInfo userInfo = GreenDaoManager.getInstance().getDaoSession().getUserInfoDao().load("d48ac639-640e-4f0e-82b0-57f21ef319b1");
        //List<Info> list = GreenDaoManager.getInstance().getDaoSession().getInfoDao().loadAll();
        //textView.setText(JSON.toJSONString(list));

        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            String szImei = TelephonyMgr.getDeviceId();
            textView.setText(szImei);
        }




    }

    @Override
    public void setData(UserInfo userInfo) {
        //textView.setText(JSON.toJSONString(userInfo));
        //List<UserInfo> list = GreenDaoManager.getInstance().getDaoSession().getUserInfoDao().loadAll();
        GreenDaoManager.getInstance().getDaoSession().startAsyncSession().insertOrReplace(userInfo);
        Info info = new Info();
        info.setID("12345679abcd"+System.currentTimeMillis());
        info.setAge(22);
        info.setName("testUser1111");
        info.setSex("男");
        info.setFiled("filed");
        //GreenDaoManager.getInstance().getDaoSession().getInfoDao().deleteAll();
        GreenDaoManager.getInstance().getDaoSession().getInfoDao().insertOrReplace(info);
        Observable.create(new ObservableOnSubscribe<List<Info>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Info>> emitter) throws Exception {
                List<Info> infoList = GreenDaoManager.getInstance().getDaoSession().getInfoDao().loadAll();
                emitter.onNext(infoList);
            }
        }).compose(RxJavaUitls.<List<Info>>schedulersObservableTransformer())
                .subscribe(new Consumer<List<Info>>() {
                    @Override
                    public void accept(List<Info> infoList) throws Exception {

                        textView.setText("size:" + infoList.size() + "\n" + JSON.toJSONString(infoList));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("ee",throwable.getMessage());
                    }
                });

       /* LGImgCompressor.getInstance(this).withListener(new LGImgCompressor.CompressListener() {
            @Override
            public void onCompressStart() {

            }

            @Override
            public void onCompressEnd(LGImgCompressor.CompressResult compressResult) {
                if (compressResult.getStatus() == LGImgCompressor.CompressResult.RESULT_ERROR){
                    Toast.makeText(LoginActivity.this, "压缩失败", Toast.LENGTH_SHORT).show();
                    return;
                }else {//压缩失败

                }


            }
        }).starCompressWithDefault(Uri.fromFile(new File("/storage/emulated/0/BGAPhotoPickerDownload/9d9522b020975b095b69a49903a64fed.png")).toString());*/

        Luban.with(this)
                .load("/storage/emulated/0/BGAPhotoPickerDownload/9d9522b020975b095b69a49903a64fed.png")
                .ignoreBy(200)
                .setTargetDir("/storage/emulated/0/BGAPhotoPickerDownload/xxx")
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        Toast.makeText(LoginActivity.this, "lu压缩成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                        Toast.makeText(LoginActivity.this, "lu压缩失败", Toast.LENGTH_SHORT).show();
                    }
                }).launch();
    }

    @Override
    public void setText(String data) {
        textView.setText(data);
    }

    @Override
    public LoginPresenter onBindPresenter() {
        return new LoginPresenter(this);
    }

    /**
     * 上传多个文件 没有监听
     */
    private void testUploads1(){
        List<String> paths = new ArrayList<>();
        paths.add("/storage/emulated/0/DCIM/Camera/IMG_20180822_104144.jpg");
        //paths.add("/storage/emulated/0/DCIM/Camera/IMG_20170228_094837.jpg");
        getPresenter().upLoadFiles(paths);
    }
    /**
     * 上传多个文件 有监听
     */
    private void testUplaods2(){
        List<FileUploadCombination> list = new ArrayList<>();
        //封装的实体
        FileUploadCombination combination = new FileUploadCombination();
        //设置要上传的文件
        combination.setPath("/storage/emulated/0/DCIM/Camera/IMG_20180822_104144.jpg");
        //设置该文件的上传进度监听
        combination.setFileUploadObserver(new MultiFileUploadObserver() {
            @Override
            public void onProgress(final int progress) {
                //这里还是在子线程
                Log.i("progress：",progress+"");
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(progress);
                    }
                }).compose(RxJavaUitls.<Integer>schedulersObservableTransformer()).subscribe(new Consumer<Integer>(){
                    @Override
                    public void accept(Integer value) throws Exception {
                        textView.setText(value+"");
                    }
                } );
            }
        });
        list.add(combination);
        getPresenter().upLoadFiles2(list);
    }

}
