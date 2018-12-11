package com.example.administrator.badgerdemo.bean;

import android.support.annotation.IntDef;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class Info {
    @Id
    private String ID;
    private int age;
    private String name;
    private String sex;
    private String filed;
    @Generated(hash = 1559971134)
    public Info(String ID, int age, String name, String sex, String filed) {
        this.ID = ID;
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.filed = filed;
    }
    @Generated(hash = 614508582)
    public Info() {
    }
    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getFiled() {
        return this.filed;
    }
    public void setFiled(String filed) {
        this.filed = filed;
    }
}
