package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_AppSetting;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_AppSettingResponse implements Serializable {

    @SerializedName("data")
    private BDG_AppSettingData data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public BDG_AppSettingData getData() {
        return data;
    }

    public void setData(BDG_AppSettingData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BDG_AppSettingResponse{" + "data = '" + data + '\'' + ",message = '" + message + '\'' + ",status = '" + status + '\'' + "}";
    }
}
