package com.example.administrator.badgerdemo.db.convert;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.badgerdemo.bean.SchoolInfo;

import org.greenrobot.greendao.converter.PropertyConverter;

public class SchoolInfoConvert implements PropertyConverter<SchoolInfo,String> {
    @Override
    public SchoolInfo convertToEntityProperty(String databaseValue) {
        if(TextUtils.isEmpty(databaseValue)){
            return null;
        }

        return JSON.parseObject(databaseValue,SchoolInfo.class);
    }

    @Override
    public String convertToDatabaseValue(SchoolInfo entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
