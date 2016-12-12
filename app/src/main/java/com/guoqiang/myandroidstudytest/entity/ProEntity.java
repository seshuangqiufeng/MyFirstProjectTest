package com.guoqiang.myandroidstudytest.entity;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/8/8.
 */
public class ProEntity {

    private String name;
    private ArrayList<NameEntity> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<NameEntity> getList() {
        return list;
    }

    public void setList(ArrayList<NameEntity> list) {
        this.list = list;
    }
}
