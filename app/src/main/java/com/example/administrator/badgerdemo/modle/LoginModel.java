package com.example.administrator.badgerdemo.modle;

import com.example.administrator.badgerdemo.bean.ResultData;
import com.example.administrator.badgerdemo.bean.UserInfo;
import com.example.administrator.badgerdemo.retrofit.RetrofitClient;
import com.example.administrator.badgerdemo.rxjava.RxJavaUitls;
import com.example.administrator.badgerdemo.service.ILoginService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements ILoginModel{

    /**
     * params.put("Mobile", mLoginRequestEntity.account);
     params.put("Code",mLoginRequestEntity.password);
     params.put("RegisterType", "android");
     params.put("RegisterID",registerID);
     */
    public Observable<ResultData<UserInfo>> login(String Mobile, String Code, String RegisterType, String RegisterID){
        ILoginService service = RetrofitClient.getInstance().createApi(ILoginService.class);
        Map<String,String> params = new HashMap<>();
        params.put("Mobile",Mobile);
        params.put("Code",Code);
        params.put("RegisterType",RegisterType);
        params.put("RegisterID",RegisterID);
        return service.login(params).compose(RxJavaUitls.<ResultData<UserInfo>>schedulersObservableTransformer());
    }

}
