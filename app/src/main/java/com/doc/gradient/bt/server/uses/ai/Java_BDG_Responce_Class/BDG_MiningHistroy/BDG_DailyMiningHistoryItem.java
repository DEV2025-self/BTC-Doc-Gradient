package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_MiningHistroy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_DailyMiningHistoryItem implements Serializable {

    private static final long serialVersionUID = 1L; // For Serializable

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("start_time")
    @Expose
    private Object startTime;
    @SerializedName("end_time")
    @Expose
    private Object endTime;
    @SerializedName("mining")
    @Expose
    private Double mining;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("is_deleted")
    @Expose
    private Integer isDeleted;
    @SerializedName("start_time_timestamp")
    @Expose
    private String startTimeTimestamp;
    @SerializedName("end_time_timestamp")
    @Expose
    private String endTimeTimestamp;
    @SerializedName("app_id")
    @Expose
    private Integer appId;
    @SerializedName("is_mining")
    @Expose
    private Integer isMining;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public BDG_DailyMiningHistoryItem(Integer id) {
        this.id=id;
    }

    // Getter and Setter methods

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getStartTime() {
        return startTime;
    }

    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public Double getMining() {
        return mining;
    }

    public void setMining(Double mining) {
        this.mining = mining;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getStartTimeTimestamp() {
        return startTimeTimestamp;
    }

    public void setStartTimeTimestamp(String startTimeTimestamp) {
        this.startTimeTimestamp = startTimeTimestamp;
    }

    public String getEndTimeTimestamp() {
        return endTimeTimestamp;
    }

    public void setEndTimeTimestamp(String endTimeTimestamp) {
        this.endTimeTimestamp = endTimeTimestamp;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getIsMining() {
        return isMining;
    }

    public void setIsMining(Integer isMining) {
        this.isMining = isMining;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BDG_DailyMiningHistoryItem{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", mining=" + mining +
                ", userId=" + userId +
                ", isDeleted=" + isDeleted +
                ", startTimeTimestamp='" + startTimeTimestamp + '\'' +
                ", endTimeTimestamp='" + endTimeTimestamp + '\'' +
                ", appId=" + appId +
                ", isMining=" + isMining +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
