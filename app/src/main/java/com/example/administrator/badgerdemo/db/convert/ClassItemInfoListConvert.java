package com.example.administrator.badgerdemo.db.convert;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.badgerdemo.bean.ClassItemInfo;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

public class ClassItemInfoListConvert implements PropertyConverter<List<ClassItemInfo>,String> {
    @Override
    public List<ClassItemInfo> convertToEntityProperty(String databaseValue) {
        if(TextUtils.isEmpty(databaseValue)){
            return null;
        }
        return JSON.parseArray(databaseValue,ClassItemInfo.class);
    }

    @Override
    public String convertToDatabaseValue(List<ClassItemInfo> entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
