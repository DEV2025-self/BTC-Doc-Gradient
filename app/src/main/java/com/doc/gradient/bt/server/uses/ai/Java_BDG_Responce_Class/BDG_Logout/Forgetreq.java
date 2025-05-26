package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Logout;

import com.google.gson.annotations.SerializedName;

public class Forgetreq{


    @SerializedName("email")
    private String email;

    @SerializedName("app_id")
    private Integer appId;

    public Forgetreq(String email, Integer appId) {
        this.email = email;
        this.appId = appId;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}
