package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Withdrawal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_WithdrawalResponse implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private Boolean status;

    // Getter methods
    public String getMessage() {
        return message;
    }

    public Boolean getStatus() {
        return status;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "BDG_WithdrawalResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

}
