package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_UserInfoResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("data")
    @Expose
    private BDG_UserInfoData data;

    @SerializedName("message")
    @Expose
    private String message;

    // Getters and Setters
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BDG_UserInfoData getData() {
        return data;
    }

    public void setData(BDG_UserInfoData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BDG_UserInfoResponse{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
