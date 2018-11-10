package com.example.administrator.badgerdemo.db.convert;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.badgerdemo.bean.ClassItemInfo;
import com.example.administrator.badgerdemo.bean.SchoolInfo;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

public class SchoolInfoListConvert implements PropertyConverter<List<SchoolInfo>,String> {
    @Override
    public List<SchoolInfo> convertToEntityProperty(String databaseValue) {
        if(TextUtils.isEmpty(databaseValue)){
            return null;
        }
        return JSON.parseArray(databaseValue,SchoolInfo.class);
    }

    @Override
    public String convertToDatabaseValue(List<SchoolInfo> entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
