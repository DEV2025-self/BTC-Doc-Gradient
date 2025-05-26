package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_DeleteUser;

import com.google.gson.annotations.SerializedName;

public class Deletereq {
    @SerializedName("user_key")
    private String userkey;

    @SerializedName("app_id")
    private Integer APP_ID;

    // Default constructor
    public Deletereq() {
    }

    // Constructor with all fields
    public Deletereq(String userkey, Integer APP_ID) {
        this.userkey = userkey;
        this.APP_ID = APP_ID;
    }

    // Getters and Setters
    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public Integer getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(Integer APP_ID) {
        this.APP_ID = APP_ID;
    }
}
