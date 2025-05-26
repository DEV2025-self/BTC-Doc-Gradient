package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Country;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_CountryDataItem implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("country_code")
    @Expose
    private String countryCode;

    @SerializedName("country_name")
    @Expose
    private String countryName;

    @SerializedName("count")
    @Expose
    private int count = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BDG_CountryDataItem{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", count=" + count +
                '}';
    }
}
