package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BDG_PlanResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("data")
    @Expose
    private ArrayList<BDG_PlanData> data;

    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<BDG_PlanData> getData() {
        return data;
    }

    public void setData(ArrayList<BDG_PlanData> data) {
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
        return "PlanResponse{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
