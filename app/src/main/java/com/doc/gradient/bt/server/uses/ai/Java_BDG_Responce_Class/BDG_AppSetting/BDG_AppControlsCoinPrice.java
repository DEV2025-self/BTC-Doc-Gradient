package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_AppSetting;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_AppControlsCoinPrice implements Serializable {
    @SerializedName("price")
    private String price;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "CoinPriceItem{" +
                        "price = '" + price + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}