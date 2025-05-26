package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Country;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BDG_CountryResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean isSuccess;

    @SerializedName("data")
    @Expose
    private ArrayList<BDG_CountryDataItem> data;

    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public ArrayList<BDG_CountryDataItem> getData() {
        return data;
    }

    public void setData(ArrayList<BDG_CountryDataItem> data) {
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
        return "BDG_CountryResponse{" +
                "data='" + data + '\'' +
                ", success='" + isSuccess + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
