package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_UserInfoData implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("UID")
    @Expose
    private String uid;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("app_id")
    @Expose
    private Integer appId;

    @SerializedName("user_key")
    @Expose
    private String userKey;

    @SerializedName("referral_code")
    @Expose
    private String referralCode;

    @SerializedName("referral_by")
    @Expose
    private Object referralBy;  // Can be changed to appropriate type based on your usage

    @SerializedName("plan_id")
    @Expose
    private Integer planId;

    @SerializedName("plan_name")
    @Expose
    private String planName;

    @SerializedName("has_rate_speed")
    @Expose
    private Integer hasRateSpeed;

    @SerializedName("mining_point")
    @Expose
    private String miningPoint;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Object getReferralBy() {
        return referralBy;
    }

    public void setReferralBy(Object referralBy) {
        this.referralBy = referralBy;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getHasRateSpeed() {
        return hasRateSpeed;
    }

    public void setHasRateSpeed(Integer hasRateSpeed) {
        this.hasRateSpeed = hasRateSpeed;
    }

    public String getMiningPoint() {
        return miningPoint;
    }

    public void setMiningPoint(String miningPoint) {
        this.miningPoint = miningPoint;
    }

    @Override
    public String toString() {
        return "BDG_UserInfoData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", appId=" + appId +
                ", userKey='" + userKey + '\'' +
                ", referralCode='" + referralCode + '\'' +
                ", referralBy=" + referralBy +
                ", planId=" + planId +
                ", planName='" + planName + '\'' +
                ", hasRateSpeed=" + hasRateSpeed +
                ", miningPoint='" + miningPoint + '\'' +
                '}';
    }
}
