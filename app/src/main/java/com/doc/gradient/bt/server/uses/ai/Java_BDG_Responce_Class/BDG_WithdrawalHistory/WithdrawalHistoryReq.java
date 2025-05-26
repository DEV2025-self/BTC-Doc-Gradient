package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_WithdrawalHistory;

import com.google.gson.annotations.SerializedName;

public class WithdrawalHistoryReq {
    @SerializedName("user_key")
    private String userkey;

    @SerializedName("app_id")
    private Integer APP_ID;

    @SerializedName("pageNumber")
    private String pagenumber;

    @SerializedName("item_count")
    private String itemcount;

    public WithdrawalHistoryReq(String userkey, String pagenumber, String itemcount, Integer APP_ID) {
        this.userkey = userkey;
        this.pagenumber = pagenumber;
        this.itemcount = itemcount;
        this.APP_ID = APP_ID;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public Integer getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(Integer APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(String pagenumber) {
        this.pagenumber = pagenumber;
    }

    public String getItemcount() {
        return itemcount;
    }

    public void setItemcount(String itemcount) {
        this.itemcount = itemcount;
    }

    @Override
    public String toString() {
        return "WithdrawalHistoryReq{" +
                "userkey='" + userkey + '\'' +
                ", APP_ID='" + APP_ID + '\'' +
                ", pagenumber='" + pagenumber + '\'' +
                ", itemcount='" + itemcount + '\'' +
                '}';
    }
}
