package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Signup;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Registerreq implements Serializable {
    @SerializedName("first_name")
    private String firstname;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("password_confirmation")
    private String confirmation;

    @SerializedName("country")
    private String country;

    @SerializedName("referral_code")
    private String referralcode;

    @SerializedName("app_id")
    private Integer appId;
    // Constructors
    public Registerreq() {
    }

    public Registerreq(String firstname, String email, String password, String confirmation,
                       String country, String referralcode, Integer appId) {
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.confirmation = confirmation;
        this.country = country;
        this.referralcode = referralcode;
        this.appId = appId;
    }

    // Getters and Setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReferralcode() {
        return referralcode;
    }

    public void setReferralcode(String referralcode) {
        this.referralcode = referralcode;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}
