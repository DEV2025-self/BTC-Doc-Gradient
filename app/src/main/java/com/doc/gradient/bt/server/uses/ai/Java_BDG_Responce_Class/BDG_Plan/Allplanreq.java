package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan;

import com.google.gson.annotations.SerializedName;

public class Allplanreq {
    @SerializedName("app_id")
    private Integer APP_ID;

    public Allplanreq() {
        // Default constructor
    }

    public Allplanreq(Integer APP_ID) {
        this.APP_ID = APP_ID;
    }

    public Integer getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(Integer APP_ID) {
        this.APP_ID = APP_ID;
    }
}
