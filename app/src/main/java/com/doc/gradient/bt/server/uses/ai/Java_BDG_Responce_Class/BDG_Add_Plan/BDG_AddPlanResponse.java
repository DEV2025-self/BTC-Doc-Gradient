package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Add_Plan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_AddPlanResponse implements Serializable {
    @SerializedName("message")
    @Expose
    private final String message = null;

    @SerializedName("status")
    @Expose
    private final Boolean status = null;

    public String getMessage() {
        return message;
    }

    public Boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "BDG_AddPlanResponse{" +
                "message = '" + message + '\'' +
                ",status = '" + status + '\'' +
                "}";
    }
}