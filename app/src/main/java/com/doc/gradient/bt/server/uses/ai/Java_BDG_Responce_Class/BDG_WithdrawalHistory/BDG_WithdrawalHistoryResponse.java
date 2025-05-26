package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_WithdrawalHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_WithdrawalHistoryResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("data")
    @Expose
    private BDG_WithdrawalHistoryData data;

    @SerializedName("message")
    @Expose
    private String message;

    // Getter methods
    public Boolean getStatus() {
        return status;
    }

    public BDG_WithdrawalHistoryData getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "BDG_WithdrawalHistoryResponse{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
