package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_WithdrawalHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_WithdrawHistoryItem implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("coin_name")
    @Expose
    private String coinName;

    @SerializedName("app_id")
    @Expose
    private Integer appId;

    @SerializedName("created_at")
    @Expose
    private Object createdAt;

    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("email")
    @Expose
    private String email;

    // Getter methods
    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getCoinName() {
        return coinName;
    }

    public Integer getAppId() {
        return appId;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "BDG_WithdrawHistoryItem{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount='" + amount + '\'' +
                ", address='" + address + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                ", coinName='" + coinName + '\'' +
                ", appId=" + appId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
