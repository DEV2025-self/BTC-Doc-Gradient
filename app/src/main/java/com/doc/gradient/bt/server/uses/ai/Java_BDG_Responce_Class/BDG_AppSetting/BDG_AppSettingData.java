package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_AppSetting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BDG_AppSettingData implements Serializable {
    @SerializedName("sender_reward_point")
    @Expose
    private long senderRewardPoint;
    @SerializedName("receiver_reward_point")
    @Expose
    private String receiverRewardPoint;
    @SerializedName("isAppRemove")
    @Expose
    private Boolean isAppRemove;
    @SerializedName("isShowFailPurchase")
    @Expose
    private Boolean isShowFailPurchase;
    @SerializedName("isShowPurchaseEntryInFirebase")
    @Expose
    private Boolean isShowPurchaseEntryInFirebase;
    @SerializedName("support_email")
    @Expose
    private String supportEmail;
    @SerializedName("privacy_policy")
    @Expose
    private String privacyPolicy;
    @SerializedName("terms_and_condition")
    @Expose
    private String termsAndCondition;
    @SerializedName("active_miner_min")
    @Expose
    private String activeMinerMin;
    @SerializedName("active_miner_max")
    @Expose
    private String activeMinerMax;
    @SerializedName("isTestAd")
    @Expose
    private Boolean isTestAd;
    @SerializedName("isAdmobAndFBMeditation")
    @Expose
    private Boolean isAdmobAndFBMeditation;
    @SerializedName("facebook_ads")
    @Expose
    private Boolean facebookAds;
    @SerializedName("fb_native_ad")
    @Expose
    private String fbNativeAd;
    @SerializedName("fb_native_banner_ad")
    @Expose
    private String fbNativeBannerAd;
    @SerializedName("fb_banner_ad")
    @Expose
    private String fbBannerAd;
    @SerializedName("fb_medium_rectangle_250")
    @Expose
    private String fbMediumRectangle250;
    @SerializedName("fb_interstitial_ad")
    @Expose
    private String fbInterstitialAd;
    @SerializedName("fb_rewarded_video_ad")
    @Expose
    private String fbRewardedVideoAd;
    @SerializedName("admob_ads_id")
    @Expose
    private Boolean admobAdsId;
    @SerializedName("admob_native_ad")
    @Expose
    private String admobNativeAd;
    @SerializedName("admob_banner_ad")
    @Expose
    private String admobBannerAd;
    @SerializedName("admob_interstitial_ad")
    @Expose
    private String admobInterstitialAd;
    @SerializedName("admob_rewarded_video_ad")
    @Expose
    private String admobRewardedVideoAd;
    @SerializedName("admob_app_open")
    @Expose
    private String admobAppOpen;
    @SerializedName("is_show_ads")
    @Expose
    private Boolean isShowAds;
    @SerializedName("payment_gateway")
    @Expose
    private Boolean paymentGateway;
    @SerializedName("razor_pay")
    @Expose
    private Boolean razorPay;
    @SerializedName("razor_merchant_key")
    @Expose
    private String razorMerchantKey;
    @SerializedName("razor_solt_key")
    @Expose
    private String razorSoltKey;
    @SerializedName("payu_new")
    @Expose
    private Boolean payuNew;
    @SerializedName("payu_new_merchant_key")
    @Expose
    private String payuNewMerchantKey;
    @SerializedName("payu_new_solt_key")
    @Expose
    private String payuNewSoltKey;
    @SerializedName("payu_old")
    @Expose
    private Boolean payuOld;
    @SerializedName("payu_old_merchant_key")
    @Expose
    private String payuOldMerchantKey;
    @SerializedName("payu_old_solt_key")
    @Expose
    private String payuOldSoltKey;
    @SerializedName("cash_free")
    @Expose
    private Boolean cashFree;
    @SerializedName("cash_merchant_key")
    @Expose
    private String cashMerchantKey;
    @SerializedName("cash_solt_key")
    @Expose
    private String cashSoltKey;
    @SerializedName("paytm")
    @Expose
    private Boolean paytm;
    @SerializedName("paytm_merchant_key")
    @Expose
    private String paytmMerchantKey;
    @SerializedName("paytm_solt_key")
    @Expose
    private String paytmSoltKey;
    @SerializedName("upi")
    @Expose
    private Boolean upi;
    @SerializedName("upi_merchant")
    @Expose
    private String upiMerchant;
    @SerializedName("upi_api")
    @Expose
    private Boolean upiApi;
    @SerializedName("upi_api_merchant_key")
    @Expose
    private String upiApiMerchantKey;
    @SerializedName("upi_api_token")
    @Expose
    private String upiApiToken;
    @SerializedName("upi_api_call_back_url")
    @Expose
    private String upiApiCallBackUrl;
    @SerializedName("in_app_purchase")
    @Expose
    private Boolean inAppPurchase;
    @SerializedName("show_all_world")
    @Expose
    private Boolean showAllWorld;
    @SerializedName("outside_india")
    @Expose
    private Boolean outsideIndia;
    @SerializedName("applovin_ads")
    @Expose
    private Boolean applovinAds;
    @SerializedName("applovin_small_native_ad")
    @Expose
    private String applovinSmallNativeAd;
    @SerializedName("applovin_medium_banner_ad")
    @Expose
    private String applovinMediumBannerAd;
    @SerializedName("applovin_large_native_ad")
    @Expose
    private String applovinLargeNativeAd;
    @SerializedName("applovin_interstitial_ad")
    @Expose
    private String applovinInterstitialAd;
    @SerializedName("applovin_rewarded_video_ad")
    @Expose
    private String applovinRewardedVideoAd;
    @SerializedName("is_account_delete")
    @Expose
    private Boolean isAccountDelete;
    @SerializedName("app_update")
    @Expose
    private Boolean appUpdate;
    @SerializedName("app_update_type_immediate")
    @Expose
    private Boolean appUpdateTypeImmediate;
    @SerializedName("coin_price")
    @Expose
    private List<BDG_AppControlsCoinPrice> coinPrice;
    @SerializedName("json")
    @Expose
    private BDG_AppSettingJson json;

    public long getSenderRewardPoint() {
        return senderRewardPoint;
    }

    public void setSenderRewardPoint(long senderRewardPoint) {
        this.senderRewardPoint = senderRewardPoint;
    }

    public String getReceiverRewardPoint() {
        return receiverRewardPoint;
    }

    public void setReceiverRewardPoint(String receiverRewardPoint) {
        this.receiverRewardPoint = receiverRewardPoint;
    }

    public Boolean getIsAppRemove() {
        return isAppRemove;
    }

    public void setIsAppRemove(Boolean isAppRemove) {
        this.isAppRemove = isAppRemove;
    }

    public Boolean getIsShowFailPurchase() {
        return isShowFailPurchase;
    }

    public void setIsShowFailPurchase(Boolean isShowFailPurchase) {
        this.isShowFailPurchase = isShowFailPurchase;
    }

    public Boolean getIsShowPurchaseEntryInFirebase() {
        return isShowPurchaseEntryInFirebase;
    }

    public void setIsShowPurchaseEntryInFirebase(Boolean isShowPurchaseEntryInFirebase) {
        this.isShowPurchaseEntryInFirebase = isShowPurchaseEntryInFirebase;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public String getTermsAndCondition() {
        return termsAndCondition;
    }

    public void setTermsAndCondition(String termsAndCondition) {
        this.termsAndCondition = termsAndCondition;
    }

    public String getActiveMinerMin() {
        return activeMinerMin;
    }

    public void setActiveMinerMin(String activeMinerMin) {
        this.activeMinerMin = activeMinerMin;
    }

    public String getActiveMinerMax() {
        return activeMinerMax;
    }

    public void setActiveMinerMax(String activeMinerMax) {
        this.activeMinerMax = activeMinerMax;
    }

    public Boolean getIsTestAd() {
        return isTestAd;
    }

    public void setIsTestAd(Boolean isTestAd) {
        this.isTestAd = isTestAd;
    }

    public Boolean getIsAdmobAndFBMeditation() {
        return isAdmobAndFBMeditation;
    }

    public void setIsAdmobAndFBMeditation(Boolean isAdmobAndFBMeditation) {
        this.isAdmobAndFBMeditation = isAdmobAndFBMeditation;
    }

    public Boolean getFacebookAds() {
        return facebookAds;
    }

    public void setFacebookAds(Boolean facebookAds) {
        this.facebookAds = facebookAds;
    }

    public String getFbNativeAd() {
        return fbNativeAd;
    }

    public void setFbNativeAd(String fbNativeAd) {
        this.fbNativeAd = fbNativeAd;
    }

    public String getFbNativeBannerAd() {
        return fbNativeBannerAd;
    }

    public void setFbNativeBannerAd(String fbNativeBannerAd) {
        this.fbNativeBannerAd = fbNativeBannerAd;
    }

    public String getFbBannerAd() {
        return fbBannerAd;
    }

    public void setFbBannerAd(String fbBannerAd) {
        this.fbBannerAd = fbBannerAd;
    }

    public String getFbMediumRectangle250() {
        return fbMediumRectangle250;
    }

    public void setFbMediumRectangle250(String fbMediumRectangle250) {
        this.fbMediumRectangle250 = fbMediumRectangle250;
    }

    public String getFbInterstitialAd() {
        return fbInterstitialAd;
    }

    public void setFbInterstitialAd(String fbInterstitialAd) {
        this.fbInterstitialAd = fbInterstitialAd;
    }

    public String getFbRewardedVideoAd() {
        return fbRewardedVideoAd;
    }

    public void setFbRewardedVideoAd(String fbRewardedVideoAd) {
        this.fbRewardedVideoAd = fbRewardedVideoAd;
    }

    public Boolean getAdmobAdsId() {
        return admobAdsId;
    }

    public void setAdmobAdsId(Boolean admobAdsId) {
        this.admobAdsId = admobAdsId;
    }

    public String getAdmobNativeAd() {
        return admobNativeAd;
    }

    public void setAdmobNativeAd(String admobNativeAd) {
        this.admobNativeAd = admobNativeAd;
    }

    public String getAdmobBannerAd() {
        return admobBannerAd;
    }

    public void setAdmobBannerAd(String admobBannerAd) {
        this.admobBannerAd = admobBannerAd;
    }

    public String getAdmobInterstitialAd() {
        return admobInterstitialAd;
    }

    public void setAdmobInterstitialAd(String admobInterstitialAd) {
        this.admobInterstitialAd = admobInterstitialAd;
    }

    public String getAdmobRewardedVideoAd() {
        return admobRewardedVideoAd;
    }

    public void setAdmobRewardedVideoAd(String admobRewardedVideoAd) {
        this.admobRewardedVideoAd = admobRewardedVideoAd;
    }

    public String getAdmobAppOpen() {
        return admobAppOpen;
    }

    public void setAdmobAppOpen(String admobAppOpen) {
        this.admobAppOpen = admobAppOpen;
    }

    public Boolean getIsShowAds() {
        return isShowAds;
    }

    public void setIsShowAds(Boolean isShowAds) {
        this.isShowAds = isShowAds;
    }

    public Boolean getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(Boolean paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Boolean getRazorPay() {
        return razorPay;
    }

    public void setRazorPay(Boolean razorPay) {
        this.razorPay = razorPay;
    }

    public String getRazorMerchantKey() {
        return razorMerchantKey;
    }

    public void setRazorMerchantKey(String razorMerchantKey) {
        this.razorMerchantKey = razorMerchantKey;
    }

    public String getRazorSoltKey() {
        return razorSoltKey;
    }

    public void setRazorSoltKey(String razorSoltKey) {
        this.razorSoltKey = razorSoltKey;
    }

    public Boolean getPayuNew() {
        return payuNew;
    }

    public void setPayuNew(Boolean payuNew) {
        this.payuNew = payuNew;
    }

    public String getPayuNewMerchantKey() {
        return payuNewMerchantKey;
    }

    public void setPayuNewMerchantKey(String payuNewMerchantKey) {
        this.payuNewMerchantKey = payuNewMerchantKey;
    }

    public String getPayuNewSoltKey() {
        return payuNewSoltKey;
    }

    public void setPayuNewSoltKey(String payuNewSoltKey) {
        this.payuNewSoltKey = payuNewSoltKey;
    }

    public Boolean getPayuOld() {
        return payuOld;
    }

    public void setPayuOld(Boolean payuOld) {
        this.payuOld = payuOld;
    }

    public String getPayuOldMerchantKey() {
        return payuOldMerchantKey;
    }

    public void setPayuOldMerchantKey(String payuOldMerchantKey) {
        this.payuOldMerchantKey = payuOldMerchantKey;
    }

    public String getPayuOldSoltKey() {
        return payuOldSoltKey;
    }

    public void setPayuOldSoltKey(String payuOldSoltKey) {
        this.payuOldSoltKey = payuOldSoltKey;
    }

    public Boolean getCashFree() {
        return cashFree;
    }

    public void setCashFree(Boolean cashFree) {
        this.cashFree = cashFree;
    }

    public String getCashMerchantKey() {
        return cashMerchantKey;
    }

    public void setCashMerchantKey(String cashMerchantKey) {
        this.cashMerchantKey = cashMerchantKey;
    }

    public String getCashSoltKey() {
        return cashSoltKey;
    }

    public void setCashSoltKey(String cashSoltKey) {
        this.cashSoltKey = cashSoltKey;
    }

    public Boolean getPaytm() {
        return paytm;
    }

    public void setPaytm(Boolean paytm) {
        this.paytm = paytm;
    }

    public String getPaytmMerchantKey() {
        return paytmMerchantKey;
    }

    public void setPaytmMerchantKey(String paytmMerchantKey) {
        this.paytmMerchantKey = paytmMerchantKey;
    }

    public String getPaytmSoltKey() {
        return paytmSoltKey;
    }

    public void setPaytmSoltKey(String paytmSoltKey) {
        this.paytmSoltKey = paytmSoltKey;
    }

    public Boolean getUpi() {
        return upi;
    }

    public void setUpi(Boolean upi) {
        this.upi = upi;
    }

    public String getUpiMerchant() {
        return upiMerchant;
    }

    public void setUpiMerchant(String upiMerchant) {
        this.upiMerchant = upiMerchant;
    }

    public Boolean getUpiApi() {
        return upiApi;
    }

    public void setUpiApi(Boolean upiApi) {
        this.upiApi = upiApi;
    }

    public String getUpiApiMerchantKey() {
        return upiApiMerchantKey;
    }

    public void setUpiApiMerchantKey(String upiApiMerchantKey) {
        this.upiApiMerchantKey = upiApiMerchantKey;
    }

    public String getUpiApiToken() {
        return upiApiToken;
    }

    public void setUpiApiToken(String upiApiToken) {
        this.upiApiToken = upiApiToken;
    }

    public String getUpiApiCallBackUrl() {
        return upiApiCallBackUrl;
    }

    public void setUpiApiCallBackUrl(String upiApiCallBackUrl) {
        this.upiApiCallBackUrl = upiApiCallBackUrl;
    }

    public Boolean getInAppPurchase() {
        return inAppPurchase;
    }

    public void setInAppPurchase(Boolean inAppPurchase) {
        this.inAppPurchase = inAppPurchase;
    }

    public Boolean getShowAllWorld() {
        return showAllWorld;
    }

    public void setShowAllWorld(Boolean showAllWorld) {
        this.showAllWorld = showAllWorld;
    }

    public Boolean getOutsideIndia() {
        return outsideIndia;
    }

    public void setOutsideIndia(Boolean outsideIndia) {
        this.outsideIndia = outsideIndia;
    }

    public Boolean getApplovinAds() {
        return applovinAds;
    }

    public void setApplovinAds(Boolean applovinAds) {
        this.applovinAds = applovinAds;
    }

    public String getApplovinSmallNativeAd() {
        return applovinSmallNativeAd;
    }

    public void setApplovinSmallNativeAd(String applovinSmallNativeAd) {
        this.applovinSmallNativeAd = applovinSmallNativeAd;
    }

    public String getApplovinMediumBannerAd() {
        return applovinMediumBannerAd;
    }

    public void setApplovinMediumBannerAd(String applovinMediumBannerAd) {
        this.applovinMediumBannerAd = applovinMediumBannerAd;
    }

    public String getApplovinLargeNativeAd() {
        return applovinLargeNativeAd;
    }

    public void setApplovinLargeNativeAd(String applovinLargeNativeAd) {
        this.applovinLargeNativeAd = applovinLargeNativeAd;
    }

    public String getApplovinInterstitialAd() {
        return applovinInterstitialAd;
    }

    public void setApplovinInterstitialAd(String applovinInterstitialAd) {
        this.applovinInterstitialAd = applovinInterstitialAd;
    }

    public String getApplovinRewardedVideoAd() {
        return applovinRewardedVideoAd;
    }

    public void setApplovinRewardedVideoAd(String applovinRewardedVideoAd) {
        this.applovinRewardedVideoAd = applovinRewardedVideoAd;
    }

    public Boolean getIsAccountDelete() {
        return isAccountDelete;
    }

    public void setIsAccountDelete(Boolean isAccountDelete) {
        this.isAccountDelete = isAccountDelete;
    }

    public Boolean getAppUpdate() {
        return appUpdate;
    }

    public void setAppUpdate(Boolean appUpdate) {
        this.appUpdate = appUpdate;
    }

    public Boolean getAppUpdateTypeImmediate() {
        return appUpdateTypeImmediate;
    }

    public void setAppUpdateTypeImmediate(Boolean appUpdateTypeImmediate) {
        this.appUpdateTypeImmediate = appUpdateTypeImmediate;
    }

    public List<BDG_AppControlsCoinPrice> getCoinPrice() {
        return coinPrice;
    }

    public void setCoinPrice(List<BDG_AppControlsCoinPrice> coinPrice) {
        this.coinPrice = coinPrice;
    }

    public BDG_AppSettingJson getJson() {
        return json;
    }

    public void setJson(BDG_AppSettingJson json) {
        this.json = json;
    }
}
