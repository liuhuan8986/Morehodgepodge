package com.example.administrator.badgerdemo.db.convert;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.badgerdemo.bean.SchoolInfo;
import com.example.administrator.badgerdemo.bean.StudentItemInfo;

import org.greenrobot.greendao.converter.PropertyConverter;

public class StudentItemInfoConvert implements PropertyConverter<StudentItemInfo,String> {
    @Override
    public StudentItemInfo convertToEntityProperty(String databaseValue) {
        if(TextUtils.isEmpty(databaseValue)){
            return null;
        }
        return JSON.parseObject(databaseValue,StudentItemInfo.class);
    }

    @Override
    public String convertToDatabaseValue(StudentItemInfo entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
