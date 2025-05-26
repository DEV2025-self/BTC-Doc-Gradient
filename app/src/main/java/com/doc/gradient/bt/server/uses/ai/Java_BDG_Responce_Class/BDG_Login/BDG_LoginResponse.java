package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_LoginResponse implements Serializable {

    private static final long serialVersionUID = 1L; // For Serializable

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("data")
    @Expose
    private BDG_LoginData data;

    @SerializedName("message")
    @Expose
    private String message;

    // Getter and Setter methods
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BDG_LoginData getData() {
        return data;
    }

    public void setData(BDG_LoginData data) {
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
        return "BDG_LoginResponse{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
