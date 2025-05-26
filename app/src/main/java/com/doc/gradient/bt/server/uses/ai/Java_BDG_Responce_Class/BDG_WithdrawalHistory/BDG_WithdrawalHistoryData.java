package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_WithdrawalHistory;

import com.doc.gradient.bt.server.uses.ai.BDG_Responce_Class.BDG_WithdrawalHistory.BDG_WithdrawHistoryItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BDG_WithdrawalHistoryData implements Serializable {

    @SerializedName("withdrawHistory")
    @Expose
    private ArrayList<BDG_WithdrawHistoryItem> withdrawHistory;

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

    // Getter methods
    public ArrayList<BDG_WithdrawHistoryItem> getWithdrawHistory() {
        return withdrawHistory;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Boolean getIsNextPage() {
        return isNextPage;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "BDG_WithdrawalHistoryData{" +
                "withdrawHistory=" + withdrawHistory +
                ", total=" + total +
                ", currentPage=" + currentPage +
                ", perPage=" + perPage +
                ", isNextPage=" + isNextPage +
                '}';
    }
}
