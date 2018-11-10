package com.example.administrator.badgerdemo.db.convert;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.badgerdemo.bean.SchoolInfo;

import org.greenrobot.greendao.converter.PropertyConverter;

public class CommonConvert<T> implements PropertyConverter<T,String> {
    private T t;
    @Override
    public T convertToEntityProperty(String databaseValue) {
        if(TextUtils.isEmpty(databaseValue)){
            return null;
        }
        Class clazz = t.getClass();
        return (T) JSON.parseObject(databaseValue,clazz);
    }

    @Override
    public String convertToDatabaseValue(T entityProperty) {
        return null;
    }
}
