package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetPlan;

import com.google.gson.annotations.SerializedName;

public class GetPlanreq {
    @SerializedName("user_key")
    private String userKey;

    @SerializedName("app_id")
    private Integer appId;

    // Default constructor
    public GetPlanreq() {}

    // Parameterized constructor
    public GetPlanreq(String userKey, Integer appId) {
        this.userKey = userKey;
        this.appId = appId;
    }

    // Getters and Setters
    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}
