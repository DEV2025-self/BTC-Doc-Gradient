package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfo;

import com.google.gson.annotations.SerializedName;

public class GetUserinforeq {
    @SerializedName("user_key")
    private String userkey;

    @SerializedName("app_id")
    private Integer APP_ID;

    public GetUserinforeq(String userkey, Integer APP_ID) {
        this.userkey = userkey;
        this.APP_ID = APP_ID;
    }

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
