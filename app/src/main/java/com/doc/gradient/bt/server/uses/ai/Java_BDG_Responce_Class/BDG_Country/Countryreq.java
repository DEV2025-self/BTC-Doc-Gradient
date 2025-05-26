package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Country;

import com.google.gson.annotations.SerializedName;

public class Countryreq {

    @SerializedName("user_key")
    private String userKey;

    @SerializedName("app_id")
    private int appId;


    public Countryreq(int appId) {
        this.appId = appId;
    }

    // Getters and Setters
    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }
}
