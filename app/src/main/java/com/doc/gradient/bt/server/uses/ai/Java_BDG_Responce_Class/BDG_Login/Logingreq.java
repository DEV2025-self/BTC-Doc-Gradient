package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Login;

import com.google.gson.annotations.SerializedName;

public class Logingreq {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("device_token")
    private String deviceToken;

    @SerializedName("google_login")
    private Boolean googleLogin;

    @SerializedName("app_id")
    private Integer appId;


    public Logingreq(String email, String password, String deviceToken,  Integer appId) {
        this.email = email;
        this.password = password;
        this.deviceToken = deviceToken;
        this.appId = appId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Boolean getGoogleLogin() {
        return googleLogin;
    }

    public void setGoogleLogin(Boolean googleLogin) {
        this.googleLogin = googleLogin;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
