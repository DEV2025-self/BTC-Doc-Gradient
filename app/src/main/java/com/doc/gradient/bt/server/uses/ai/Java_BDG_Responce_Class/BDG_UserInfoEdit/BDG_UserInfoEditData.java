package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfoEdit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_UserInfoEditData implements Serializable {

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

    // Getters
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public Integer getAppId() {
        return appId;
    }

    public String getUserKey() {
        return userKey;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "BDG_UserInfoEditData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", appId=" + appId +
                ", userKey='" + userKey + '\'' +
                '}';
    }
}
