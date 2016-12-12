package com.guoqiang.activeandroidtest.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by wangguoqiang on 2016/10/31.
 */
@Table(name = "People")
public class People extends Model {
    @Column(name = "Name")
    public String name;
    @Column(name = "Age")
    public int age;

    public People(){
        super();
    }

    public People(String name,int age){
        super();
        this.name = name;
        this.age = age;
    }
}
