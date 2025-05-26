package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetPlan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_GetPlanData implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("start_time")
    @Expose
    private String startTime;

    @SerializedName("end_time")
    @Expose
    private String endTime;

    @SerializedName("has_rate_speed")
    @Expose
    private Integer hasRateSpeed;

    @SerializedName("app_id")
    @Expose
    private Integer appId;

    @SerializedName("plan_id")
    @Expose
    private Integer planId;

    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @SerializedName("start_time_timestamp")
    @Expose
    private String startTimeTimestamp;

    @SerializedName("end_time_timestamp")
    @Expose
    private String endTimeTimestamp;

    @SerializedName("is_deleted")
    @Expose
    private Integer isDeleted;

    @SerializedName("created_at")
    @Expose
    private Object createdAt;

    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    @SerializedName("user_key")
    @Expose
    private String userKey;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getHasRateSpeed() {
        return hasRateSpeed;
    }

    public void setHasRateSpeed(Integer hasRateSpeed) {
        this.hasRateSpeed = hasRateSpeed;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "BDG_GetPlanData{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", hasRateSpeed=" + hasRateSpeed +
                ", appId=" + appId +
                ", planId=" + planId +
                ", userId=" + userId +
                ", startTimeTimestamp='" + startTimeTimestamp + '\'' +
                ", endTimeTimestamp='" + endTimeTimestamp + '\'' +
                ", isDeleted=" + isDeleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", userKey='" + userKey + '\'' +
                '}';
    }
}
