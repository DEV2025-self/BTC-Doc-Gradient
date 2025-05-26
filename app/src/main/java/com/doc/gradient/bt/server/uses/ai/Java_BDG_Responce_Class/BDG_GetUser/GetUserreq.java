package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetUser;

import com.google.gson.annotations.SerializedName;

public class GetUserreq {
    @SerializedName("pageNumber")
    Integer page;

    @SerializedName("item_count")
    Integer perpage;

    @SerializedName("app_id")
    Integer APP_ID;


    // Constructor with all fields
    public GetUserreq(Integer page, Integer perpage, Integer APP_ID) {
        this.page = page;
        this.perpage = perpage;
        this.APP_ID = APP_ID;
    }

    // Getters and Setters
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }

    public Integer getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(Integer APP_ID) {
        this.APP_ID = APP_ID;
    }
}
