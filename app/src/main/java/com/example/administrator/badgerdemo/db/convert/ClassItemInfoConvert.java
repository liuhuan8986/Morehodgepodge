package com.example.administrator.badgerdemo.db.convert;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.badgerdemo.bean.ClassItemInfo;
import com.example.administrator.badgerdemo.bean.SchoolInfo;

import org.greenrobot.greendao.converter.PropertyConverter;

public class ClassItemInfoConvert implements PropertyConverter<ClassItemInfo,String> {
    @Override
    public ClassItemInfo convertToEntityProperty(String databaseValue) {
        if(TextUtils.isEmpty(databaseValue)){
            return null;
        }
        return JSON.parseObject(databaseValue,ClassItemInfo.class);
    }

    @Override
    public String convertToDatabaseValue(ClassItemInfo entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
