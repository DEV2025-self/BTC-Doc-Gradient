package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Add_Plan;

import com.google.gson.annotations.SerializedName;

public class Addplanreq {
    @SerializedName("user_key")
    private String userkey;

    @SerializedName("app_id")
    private Integer APP_ID;

    @SerializedName("purchase_id")
    private Integer purchase_id;

    // Default constructor
    public Addplanreq() {
    }

    // Constructor with all fields
    public Addplanreq(String userkey, Integer APP_ID, Integer purchase_id) {
        this.userkey = userkey;
        this.APP_ID = APP_ID;
        this.purchase_id = this.purchase_id;
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

    public Integer getPLAN_ID() {
        return purchase_id;
    }

    public void setPLAN_ID(Integer PLAN_ID) {
        this.purchase_id = purchase_id;
    }
}
