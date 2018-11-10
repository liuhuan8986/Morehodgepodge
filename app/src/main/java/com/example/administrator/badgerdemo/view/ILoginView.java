package com.example.administrator.badgerdemo.view;

import com.example.administrator.badgerdemo.bean.UserInfo;

public interface ILoginView extends IBaseView{

    void setData(UserInfo userInfo);

    void setText(String data);
}
