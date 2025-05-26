package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_ReferralsHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_ReferralsHistoryResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("data")
    @Expose
    private BDG_ReferralHistoryData data;

    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public BDG_ReferralHistoryData getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BDG_ReferralsHistoryResponse{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
