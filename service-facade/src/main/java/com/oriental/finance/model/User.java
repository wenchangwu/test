package com.oriental.finance.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/14 0014.
 */
public class User implements Serializable {

    private String userName;
    private String male;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
