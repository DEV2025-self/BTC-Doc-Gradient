package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BDG_GetAllUserData implements Serializable {

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

    @SerializedName("user")
    @Expose
    private ArrayList<BDG_GetAllUsersItem> user;

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

    public ArrayList<BDG_GetAllUsersItem> getUser() {
        return user;
    }

    public void setUser(ArrayList<BDG_GetAllUsersItem> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BDG_GetAllUserData{" +
                "total=" + total +
                ", currentPage=" + currentPage +
                ", perPage=" + perPage +
                ", isNextPage=" + isNextPage +
                ", user=" + user +
                '}';
    }
}
