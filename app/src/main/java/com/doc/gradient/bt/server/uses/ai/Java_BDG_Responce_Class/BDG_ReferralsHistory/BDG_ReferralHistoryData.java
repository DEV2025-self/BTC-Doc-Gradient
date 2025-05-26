package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_ReferralsHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BDG_ReferralHistoryData implements Serializable {

    @SerializedName("referralsHistory")
    @Expose
    private ArrayList<BDG_ReferralsHistoryItem> referralsHistory;

    @SerializedName("total")
    @Expose
    private Integer total;

    @SerializedName("current_page")
    @Expose
    private Integer currentPage;

    @SerializedName("per_page")
    @Expose
    private Integer perPage;

    @SerializedName("is_next_page")
    @Expose
    private Boolean isNextPage;

    public ArrayList<BDG_ReferralsHistoryItem> getReferralsHistory() {
        return referralsHistory;
    }

    public void setReferralsHistory(ArrayList<BDG_ReferralsHistoryItem> referralsHistory) {
        this.referralsHistory = referralsHistory;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Boolean getIsNextPage() {
        return isNextPage;
    }

    public void setIsNextPage(Boolean isNextPage) {
        this.isNextPage = isNextPage;
    }

    @Override
    public String toString() {
        return "BDG_ReferralHistoryData{" +
                "referralsHistory=" + referralsHistory +
                ", total=" + total +
                ", currentPage=" + currentPage +
                ", perPage=" + perPage +
                ", isNextPage=" + isNextPage +
                '}';
    }
}
