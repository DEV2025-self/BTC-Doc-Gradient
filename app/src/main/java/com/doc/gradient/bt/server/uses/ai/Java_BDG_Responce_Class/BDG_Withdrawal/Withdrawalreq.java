package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Withdrawal;

import com.google.gson.annotations.SerializedName;

public class Withdrawalreq {
    @SerializedName("user_key")
    private String userkey;

    @SerializedName("address")
    private String address;

    @SerializedName("amount")
    private Double amount;

    @SerializedName("status")
    private String Success;

    @SerializedName("coin_name")
    private String coinname;

    @SerializedName("app_id")
    private Integer APP_ID;

    public Withdrawalreq() {
        // Default constructor
    }

    public Withdrawalreq(String userkey, String address, Double amount, String Success, String coinname, Integer APP_ID) {
        this.userkey = userkey;
        this.address = address;
        this.amount = amount;
        this.Success = Success;
        this.coinname = coinname;
        this.APP_ID = APP_ID;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public String getCoinname() {
        return coinname;
    }

    public void setCoinname(String coinname) {
        this.coinname = coinname;
    }

    public Integer getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(Integer APP_ID) {
        this.APP_ID = APP_ID;
    }
}
