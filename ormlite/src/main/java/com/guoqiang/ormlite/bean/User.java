package com.guoqiang.ormlite.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wangguoqiang on 2016/11/1.
 */
@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(columnName = "_id",generatedId = true)
    private Long _id;
    @DatabaseField(columnName = "age",dataType = DataType.INTEGER)
    private int age;
    @DatabaseField(columnName = "name",dataType = DataType.STRING)
    private String name;

    public User(){

    }

    public User(String name,int age){
        this.age  = age;
        this.name = name;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
