package com.guoqiang.greendao.entity;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by wangguoqiang on 2016/11/25.
 */
public class NoteTypeConverter implements PropertyConverter<NoteType,String>{
    @Override
    public NoteType convertToEntityProperty(String databaseValue) {
        return NoteType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(NoteType entityProperty) {
        return entityProperty.name();
    }
}
