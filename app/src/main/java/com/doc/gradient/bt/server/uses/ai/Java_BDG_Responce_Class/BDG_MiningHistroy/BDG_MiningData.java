package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_MiningHistroy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BDG_MiningData implements Serializable {

    private static final long serialVersionUID = 1L; // For Serializable

    @SerializedName("miningHistory")
    @Expose
    private ArrayList<BDG_DailyMiningHistoryItem> miningHistory;

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

    // Getter and Setter methods

    public ArrayList<BDG_DailyMiningHistoryItem> getMiningHistory() {
        return miningHistory;
    }

    public void setMiningHistory(ArrayList<BDG_DailyMiningHistoryItem> miningHistory) {
        this.miningHistory = miningHistory;
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
        return "BDG_MiningData{" +
                "miningHistory=" + miningHistory +
                ", total=" + total +
                ", currentPage=" + currentPage +
                ", perPage=" + perPage +
                ", isNextPage=" + isNextPage +
                '}';
    }
}
