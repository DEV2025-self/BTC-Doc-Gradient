package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_MiningHistroy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_MiningHistroyResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("data")
    @Expose
    private BDG_MiningData data;

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

    public BDG_MiningData getData() {
        return data;
    }

    public void setData(BDG_MiningData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // toString
    @Override
    public String toString() {
        return "BDG_MiningHistoryResponse{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
