package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_SignupData implements Serializable {

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

    @Override
    public String toString() {
        return "BDG_SignupData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", appId=" + appId +
                ", userKey='" + userKey + '\'' +
                ", referralCode='" + referralCode + '\'' +
                '}';
    }
}
