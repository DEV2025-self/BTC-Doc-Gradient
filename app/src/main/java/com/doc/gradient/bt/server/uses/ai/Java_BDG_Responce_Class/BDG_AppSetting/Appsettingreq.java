package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_AppSetting;

import com.google.gson.annotations.SerializedName;

public class Appsettingreq {

    @SerializedName("app_id")
    private String appid;

    public Appsettingreq(int project_APP_ID) {
    }

    public Appsettingreq(String appid) {
        this.appid = appid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}