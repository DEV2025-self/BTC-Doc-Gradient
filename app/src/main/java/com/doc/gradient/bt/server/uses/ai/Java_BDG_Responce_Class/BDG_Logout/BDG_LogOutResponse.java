package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Logout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_LogOutResponse  implements Serializable {
    // For Serializable

    @SerializedName("status")
    @Expose
    private Boolean status;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BDG_LogOutResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
