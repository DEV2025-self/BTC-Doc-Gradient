package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_LoginData implements Serializable {

    private static final long serialVersionUID = 1L; // For Serializable

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

    @SerializedName("user_key")
    @Expose
    private String userKey;

    @SerializedName("app_id")
    @Expose
    private Integer appId;

    @SerializedName("google_login")
    @Expose
    private Integer google_login;

    public Integer getGoogle_login() {
        return google_login;
    }

    public void setGoogle_login(Integer google_login) {
        this.google_login = google_login;
    }

    // Getters and Setters for all fields
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

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "BDG_LoginData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", userKey='" + userKey + '\'' +
                ", appId=" + appId +
                ", google_login=" + google_login +
                '}';
    }
}
