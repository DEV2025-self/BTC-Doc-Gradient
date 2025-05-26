package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Socket_Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MiningDataSocketResponse implements Serializable {

    @SerializedName("time")
    @Expose
    private String timeString;

    @SerializedName("mining_point")
    @Expose
    private String mining;

    @SerializedName("current_speed")
    @Expose
    private Integer currentSpeed;

    @SerializedName("isMiningStart")
    @Expose
    private Boolean isMiningStart;

    // Getters and Setters
    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public String getMining() {
        return mining;
    }

    public void setMining(String mining) {
        this.mining = mining;
    }

    public Integer getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(Integer currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public Boolean getIsMiningStart() {
        return isMiningStart;
    }

    public void setIsMiningStart(Boolean isMiningStart) {
        this.isMiningStart = isMiningStart;
    }

    @Override
    public String toString() {
        return "MiningDataSocketResponse{" +
                "timeString='" + timeString + '\'' +
                ", mining='" + mining + '\'' +
                ", currentSpeed=" + currentSpeed +
                ", isMiningStart=" + isMiningStart +
                '}';
    }
}
