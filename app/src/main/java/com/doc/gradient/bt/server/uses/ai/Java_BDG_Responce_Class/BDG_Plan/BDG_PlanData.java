package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BDG_PlanData implements Serializable {

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("plan_name")
    @Expose
    String planName;

    @SerializedName("price")
    @Expose
    String price;

    @SerializedName("discount")
    @Expose
    String discount;

    @SerializedName("active")
    @Expose
    Integer active;

    @SerializedName("speed")
    @Expose
    Integer speed;

    @SerializedName("contract")
    @Expose
    Integer contract;

    @SerializedName("minimum_withdraw")
    @Expose
    String minimumWithdraw;

    @SerializedName("availability")
    @Expose
    String availability;

    @SerializedName("category")
    @Expose
    String category;

    @SerializedName("app_id")
    @Expose
    Integer appId;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("image_path")
    @Expose
    String imagePath;

    @SerializedName("created_at")
    @Expose
    String createdAt;

    @SerializedName("updated_at")
    @Expose
    String updatedAt;

    @SerializedName("plan_id")
    @Expose
    String planId;

    @SerializedName("plan_type")
    @Expose
    Integer planType;

    @SerializedName("anticipated_rol")
    @Expose
    String anticipatedRol;

    @SerializedName("historical_rol")
    @Expose
    String historicalRol;

    @SerializedName("sha")
    @Expose
    String sha;

    @SerializedName("computing_power")
    @Expose
    String computingPower;

    @SerializedName("energy_efficiency")
    @Expose
    String energyEfficiency;

    @SerializedName("body")
    @Expose
    String body;

    @SerializedName("screen")
    @Expose
    String screen;

    @SerializedName("ui")
    @Expose
    String ui;

    @SerializedName("top_fan")
    @Expose
    String topFan;

    @SerializedName("buttons")
    @Expose
    String buttons;

    @SerializedName("upgrade")
    @Expose
    String upgrade;

    @SerializedName("walls")
    @Expose
    String walls;

    @SerializedName("basement")
    @Expose
    String basement;

    @SerializedName("priceInApp")
    @Expose
    String priceInApp;

    @SerializedName("priceInAppInNumber")
    @Expose
    String priceInAppInNumber;

    @SerializedName("purchase_id")
    @Expose
    int purchaseId;

    @SerializedName("crypto_price")
    @Expose
    String cryptoPrice;

    @Override
    public String toString() {
        return "BDG_PlanData{" +
                "id=" + id +
                ", planName='" + planName + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", active=" + active +
                ", speed=" + speed +
                ", contract=" + contract +
                ", minimumWithdraw='" + minimumWithdraw + '\'' +
                ", availability='" + availability + '\'' +
                ", category='" + category + '\'' +
                ", appId=" + appId +
                ", image='" + image + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", planId='" + planId + '\'' +
                ", planType=" + planType +
                ", anticipatedRol=" + anticipatedRol +
                ", historicalRol=" + historicalRol +
                ", sha=" + sha +
                ", computingPower=" + computingPower +
                ", energyEfficiency=" + energyEfficiency +
                ", body=" + body +
                ", screen=" + screen +
                ", ui=" + ui +
                ", topFan=" + topFan +
                ", buttons=" + buttons +
                ", upgrade=" + upgrade +
                ", walls=" + walls +
                ", basement=" + basement +
                ", priceInApp='" + priceInApp + '\'' +
                ", priceInAppInNumber='" + priceInAppInNumber + '\'' +
                ", purchaseId=" + purchaseId +
                ", cryptoPrice='" + cryptoPrice + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getContract() {
        return contract;
    }

    public void setContract(Integer contract) {
        this.contract = contract;
    }

    public String getMinimumWithdraw() {
        return minimumWithdraw;
    }

    public void setMinimumWithdraw(String minimumWithdraw) {
        this.minimumWithdraw = minimumWithdraw;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public String getAnticipatedRol() {
        return anticipatedRol;
    }

    public void setAnticipatedRol(String anticipatedRol) {
        this.anticipatedRol = anticipatedRol;
    }

    public String getHistoricalRol() {
        return historicalRol;
    }

    public void setHistoricalRol(String historicalRol) {
        this.historicalRol = historicalRol;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getComputingPower() {
        return computingPower;
    }

    public void setComputingPower(String computingPower) {
        this.computingPower = computingPower;
    }

    public String getEnergyEfficiency() {
        return energyEfficiency;
    }

    public void setEnergyEfficiency(String energyEfficiency) {
        this.energyEfficiency = energyEfficiency;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getUi() {
        return ui;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public String getTopFan() {
        return topFan;
    }

    public void setTopFan(String topFan) {
        this.topFan = topFan;
    }

    public String getButtons() {
        return buttons;
    }

    public void setButtons(String buttons) {
        this.buttons = buttons;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public String getBasement() {
        return basement;
    }

    public void setBasement(String basement) {
        this.basement = basement;
    }

    public String getPriceInApp() {
        return priceInApp;
    }

    public void setPriceInApp(String priceInApp) {
        this.priceInApp = priceInApp;
    }

    public String getPriceInAppInNumber() {
        return priceInAppInNumber;
    }

    public void setPriceInAppInNumber(String priceInAppInNumber) {
        this.priceInAppInNumber = priceInAppInNumber;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getCryptoPrice() {
        return cryptoPrice;
    }

    public void setCryptoPrice(String cryptoPrice) {
        this.cryptoPrice = cryptoPrice;
    }
}