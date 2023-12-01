package com.mat.zip.boss.model;

import java.util.Date;

public class MemberVO {

    private int uniqueNumber;
    private String user_id;
    private String password;
    private String name;
    private String gender;
    private String ageGroup;
    private String nickName;
    private String user;
    private String mark;
    private String profile;
    private int point;
    private Date accountDate;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "MemberVO [uniqueNumber=" + uniqueNumber + ", user_id=" + user_id + ", password=" + password
                + ", name=" + name + ", gender=" + gender + ", ageGroup=" + ageGroup + ", nickName=" + nickName
                + ", user=" + user + ", mark=" + mark + ", profile=" + profile + ", point=" + point
                + ", accountDate=" + accountDate + "]";
    }
}
