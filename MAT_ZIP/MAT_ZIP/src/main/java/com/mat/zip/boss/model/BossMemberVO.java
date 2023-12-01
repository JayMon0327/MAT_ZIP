package com.mat.zip.boss.model;

import java.util.Date;

public class BossMemberVO {

    private String user_id;
    private String password;
    private String store_id;
    private Date regdate;
    private String nickname;

    // getters and setters
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Boss_memberVO [user_id=" + user_id + ", password=" + password + ", store_id=" + store_id + ", regdate="
                + regdate + ", nickname=" + nickname + "]";
    }


}
