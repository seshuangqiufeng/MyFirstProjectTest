package com.guoqiang.threelevellistview.entity;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/11/3.
 */
public class NewClass {
    private String className;
    private ArrayList<Student> students;

    public NewClass() {
    }

    public NewClass(String className, ArrayList<Student> students) {
        this.className = className;
        this.students = students;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "NewClass{" +
                "className='" + className + '\'' +
                ", students=" + students +
                '}';
    }
}
