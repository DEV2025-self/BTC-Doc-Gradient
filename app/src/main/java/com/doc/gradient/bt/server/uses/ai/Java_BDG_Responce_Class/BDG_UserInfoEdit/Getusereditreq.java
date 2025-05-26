package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfoEdit;

import com.google.gson.annotations.SerializedName;

public class Getusereditreq {
    @SerializedName("user_key")
    private String userkey;

    @SerializedName("app_id")
    private Integer APP_ID;

    @SerializedName("first_name")
    private String firstname;
    @SerializedName("country")
    private String country;


    // Constructor with all fields
    public Getusereditreq(String userkey, String firstname, Integer APP_ID, String country) {
        this.userkey = userkey;
        this.firstname = firstname;
        this.APP_ID = APP_ID;
        this.country = country;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
