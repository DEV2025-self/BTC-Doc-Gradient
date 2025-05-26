package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_PaymentData;

import java.io.Serializable;

public class BDG_PaymentModel implements Serializable {

    String date;
    String email;
    String id;
    String paymentAmount;
    String paymentGatWay;
    String paymentResponse;
    String paymentType;
    boolean isPayuOld;
    boolean isPayuNew;
    String payuNewSoltKey;
    String payuOldSoltKey;
    String phoneNo;
    String razorMerchantKey;
    boolean isRazorpayEnable;
    boolean isUpi;
    String upiMerchant;
    boolean isPaytm;
    boolean isCashFree;
    String cashMerchantKey;
    boolean isInAppPurchase;
    boolean isUpiApi;
    boolean isShowAllWorld;
    boolean isOutsideIndia;
    String response;
    String status = "424";

    public BDG_PaymentModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentGatWay() {
        return paymentGatWay;
    }

    public void setPaymentGatWay(String paymentGatWay) {
        this.paymentGatWay = paymentGatWay;
    }

    public String getPaymentResponse() {
        return paymentResponse;
    }

    public void setPaymentResponse(String paymentResponse) {
        this.paymentResponse = paymentResponse;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isPayuOld() {
        return isPayuOld;
    }

    public void setPayuOld(boolean payuOld) {
        isPayuOld = payuOld;
    }

    public boolean isPayuNew() {
        return isPayuNew;
    }

    public void setPayuNew(boolean payuNew) {
        isPayuNew = payuNew;
    }

    public String getPayuNewSoltKey() {
        return payuNewSoltKey;
    }

    public void setPayuNewSoltKey(String payuNewSoltKey) {
        this.payuNewSoltKey = payuNewSoltKey;
    }

    public String getPayuOldSoltKey() {
        return payuOldSoltKey;
    }

    public void setPayuOldSoltKey(String payuOldSoltKey) {
        this.payuOldSoltKey = payuOldSoltKey;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRazorMerchantKey() {
        return razorMerchantKey;
    }

    public void setRazorMerchantKey(String razorMerchantKey) {
        this.razorMerchantKey = razorMerchantKey;
    }

    public boolean isRazorpayEnable() {
        return isRazorpayEnable;
    }

    public void setRazorpayEnable(boolean razorpayEnable) {
        isRazorpayEnable = razorpayEnable;
    }

    public boolean isUpi() {
        return isUpi;
    }

    public void setUpi(boolean upi) {
        isUpi = upi;
    }

    public String getUpiMerchant() {
        return upiMerchant;
    }

    public void setUpiMerchant(String upiMerchant) {
        this.upiMerchant = upiMerchant;
    }

    public boolean isPaytm() {
        return isPaytm;
    }

    public void setPaytm(boolean paytm) {
        isPaytm = paytm;
    }

    public boolean isCashFree() {
        return isCashFree;
    }

    public void setCashFree(boolean cashFree) {
        isCashFree = cashFree;
    }

    public String getCashMerchantKey() {
        return cashMerchantKey;
    }

    public void setCashMerchantKey(String cashMerchantKey) {
        this.cashMerchantKey = cashMerchantKey;
    }

    public boolean isInAppPurchase() {
        return isInAppPurchase;
    }

    public void setInAppPurchase(boolean inAppPurchase) {
        isInAppPurchase = inAppPurchase;
    }

    public boolean isUpiApi() {
        return isUpiApi;
    }

    public void setUpiApi(boolean upiApi) {
        isUpiApi = upiApi;
    }

    public boolean isShowAllWorld() {
        return isShowAllWorld;
    }

    public void setShowAllWorld(boolean showAllWorld) {
        isShowAllWorld = showAllWorld;
    }

    public boolean isOutsideIndia() {
        return isOutsideIndia;
    }

    public void setOutsideIndia(boolean outsideIndia) {
        isOutsideIndia = outsideIndia;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BDG_PaymentModel{" +
                "date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", paymentAmount='" + paymentAmount + '\'' +
                ", paymentGatWay='" + paymentGatWay + '\'' +
                ", paymentResponse='" + paymentResponse + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", isPayuOld=" + isPayuOld +
                ", isPayuNew=" + isPayuNew +
                ", payuNewSoltKey='" + payuNewSoltKey + '\'' +
                ", payuOldSoltKey='" + payuOldSoltKey + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", razorMerchantKey='" + razorMerchantKey + '\'' +
                ", razorpayEnable=" + isRazorpayEnable +
                ", upi=" + isUpi +
                ", upiMerchant='" + upiMerchant + '\'' +
                ", paytm=" + isPaytm +
                ", cashFree=" + isCashFree +
                ", cashMerchantKey='" + cashMerchantKey + '\'' +
                ", inAppPurchase=" + isInAppPurchase +
                ", isUpiApi=" + isUpiApi +
                ", showAllWorld=" + isShowAllWorld +
                ", outsideIndia=" + isOutsideIndia +
                ", response='" + response + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
