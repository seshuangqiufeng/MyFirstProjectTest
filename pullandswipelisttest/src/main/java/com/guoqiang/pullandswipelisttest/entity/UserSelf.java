package com.guoqiang.pullandswipelisttest.entity;

import java.io.Serializable;

/**
 * Created by wangguoqiang on 2016/12/1.
 */
public class UserSelf implements Serializable {

    private String name;
    private String schoolName;
    private int age;
    private String sex;

    public UserSelf(String name, String schoolName, int age, String sex) {
        this.name = name;
        this.schoolName = schoolName;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserSelf{" +
                "name='" + name + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
