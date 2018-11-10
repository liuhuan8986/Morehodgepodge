package com.example.administrator.badgerdemo.db.convert;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.badgerdemo.bean.SchoolInfo;
import com.example.administrator.badgerdemo.bean.StudentItemInfo;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

public class StudentItemInfoListConvert implements PropertyConverter<List<StudentItemInfo>,String> {
    @Override
    public List<StudentItemInfo> convertToEntityProperty(String databaseValue) {
        if(TextUtils.isEmpty(databaseValue)){
            return null;
        }
        return JSON.parseArray(databaseValue,StudentItemInfo.class);
    }

    @Override
    public String convertToDatabaseValue(List<StudentItemInfo> entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
