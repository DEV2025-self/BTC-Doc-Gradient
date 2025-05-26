package com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_AppSetting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BDG_AppSettingJson {

    @SerializedName("data_deletion_url")
    @Expose
    private String dataDeletionUrl;
    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;
    @SerializedName("newUrl")
    @Expose
    private String newUrl;
    @SerializedName("webUrl")
    @Expose
    private String webUrl;
    @SerializedName("isQureka")
    @Expose
    private Boolean isQureka;
    @SerializedName("is_crypto_payment")
    @Expose
    private Boolean isCryptoPayment;
    @SerializedName("adsCount")
    @Expose
    private String adsCount;
    @SerializedName("qurekaAdsBannerImage")
    @Expose
    private String qurekaAdsBannerImage;
    @SerializedName("qurekaAdsNativeImage")
    @Expose
    private String qurekaAdsNativeImage;
    @SerializedName("qurekaAds250Image")
    @Expose
    private String qurekaAds250Image;
    @SerializedName("qurekaAdsInterstitialImage")
    @Expose
    private String qurekaAdsInterstitialImage;
    @SerializedName("qurekaAdsOpenAppImage")
    @Expose
    private String qurekaAdsOpenAppImage;
    @SerializedName("qurekaAds250Gif")
    @Expose
    private String qurekaAds250Gif;
    @SerializedName("qurekaAdsNativeGif")
    @Expose
    private String qurekaAdsNativeGif;
    @SerializedName("qurekaAdsSmallNativeGif")
    @Expose
    private String qurekaAdsSmallNativeGif;
    @SerializedName("qurekaAdsInterstitialGif")
    @Expose
    private String qurekaAdsInterstitialGif;
    @SerializedName("qurekaAdsOpenAppGif")
    @Expose
    private String qurekaAdsOpenAppGif;
    @SerializedName("Fb_Banner")
    @Expose
    private Boolean fbBanner;
    @SerializedName("Fb_Native")
    @Expose
    private Boolean fbNative;
    @SerializedName("Fb_SmallNative")
    @Expose
    private Boolean fbSmallNative;
    @SerializedName("Fb_250rectangle")
    @Expose
    private Boolean fb250rectangle;
    @SerializedName("Fb_Interstitial")
    @Expose
    private Boolean fbInterstitial;
    @SerializedName("Admob_Banner")
    @Expose
    private Boolean admobBanner;
    @SerializedName("Admob_Native")
    @Expose
    private Boolean admobNative;
    @SerializedName("Admob_Interstitial")
    @Expose
    private Boolean admobInterstitial;
    @SerializedName("Admob_SmallNative")
    @Expose
    private Boolean admobSmallNative;
    @SerializedName("Admob_RewordAds")
    @Expose
    private Boolean admobRewordAds;
    @SerializedName("Applovin_Banner")
    @Expose
    private Boolean applovinBanner;
    @SerializedName("Applovin_Native")
    @Expose
    private Boolean applovinNative;
    @SerializedName("Applovin_Interstitial")
    @Expose
    private Boolean applovinInterstitial;
    @SerializedName("Qureka_Banner")
    @Expose
    private Boolean qurekaBanner;
    @SerializedName("Qureka_Native")
    @Expose
    private Boolean qurekaNative;
    @SerializedName("Qureka_SmallNative")
    @Expose
    private Boolean qurekaSmallNative;
    @SerializedName("Qureka_250rectangle")
    @Expose
    private Boolean qureka250rectangle;
    @SerializedName("Qureka_Interstitial")
    @Expose
    private Boolean qurekaInterstitial;
    @SerializedName("Qureka_AppOpen")
    @Expose
    private Boolean qurekaAppOpen;

    @SerializedName("admob_adcount_inter")
    private Integer adMobCount;

    public Integer getAdMobCount() {
        return adMobCount;
    }

    public void setAdMobCount(Integer adMobCount) {
        this.adMobCount = adMobCount;
    }

    public Boolean getCryptoPayment() {
        return isCryptoPayment;
    }

    public void setCryptoPayment(Boolean cryptoPayment) {
        isCryptoPayment = cryptoPayment;
    }

    public Boolean getQureka() {
        return isQureka;
    }

    public void setQureka(Boolean qureka) {
        isQureka = qureka;
    }

    public String getDataDeletionUrl() {
        return dataDeletionUrl;
    }

    public void setDataDeletionUrl(String dataDeletionUrl) {
        this.dataDeletionUrl = dataDeletionUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Boolean getIsQureka() {
        return isQureka;
    }

    public void setIsQureka(Boolean isQureka) {
        this.isQureka = isQureka;
    }

    public Boolean getIsCryptoPayment() {
        return isCryptoPayment;
    }

    public void setIsCryptoPayment(Boolean isCryptoPayment) {
        this.isCryptoPayment = isCryptoPayment;
    }

    public String getAdsCount() {
        return adsCount;
    }

    public void setAdsCount(String adsCount) {
        this.adsCount = adsCount;
    }

    public String getQurekaAdsBannerImage() {
        return qurekaAdsBannerImage;
    }

    public void setQurekaAdsBannerImage(String qurekaAdsBannerImage) {
        this.qurekaAdsBannerImage = qurekaAdsBannerImage;
    }

    public String getQurekaAdsNativeImage() {
        return qurekaAdsNativeImage;
    }

    public void setQurekaAdsNativeImage(String qurekaAdsNativeImage) {
        this.qurekaAdsNativeImage = qurekaAdsNativeImage;
    }

    public String getQurekaAds250Image() {
        return qurekaAds250Image;
    }

    public void setQurekaAds250Image(String qurekaAds250Image) {
        this.qurekaAds250Image = qurekaAds250Image;
    }

    public String getQurekaAdsInterstitialImage() {
        return qurekaAdsInterstitialImage;
    }

    public void setQurekaAdsInterstitialImage(String qurekaAdsInterstitialImage) {
        this.qurekaAdsInterstitialImage = qurekaAdsInterstitialImage;
    }

    public String getQurekaAdsOpenAppImage() {
        return qurekaAdsOpenAppImage;
    }

    public void setQurekaAdsOpenAppImage(String qurekaAdsOpenAppImage) {
        this.qurekaAdsOpenAppImage = qurekaAdsOpenAppImage;
    }

    public String getQurekaAds250Gif() {
        return qurekaAds250Gif;
    }

    public void setQurekaAds250Gif(String qurekaAds250Gif) {
        this.qurekaAds250Gif = qurekaAds250Gif;
    }

    public String getQurekaAdsNativeGif() {
        return qurekaAdsNativeGif;
    }

    public void setQurekaAdsNativeGif(String qurekaAdsNativeGif) {
        this.qurekaAdsNativeGif = qurekaAdsNativeGif;
    }

    public String getQurekaAdsSmallNativeGif() {
        return qurekaAdsSmallNativeGif;
    }

    public void setQurekaAdsSmallNativeGif(String qurekaAdsSmallNativeGif) {
        this.qurekaAdsSmallNativeGif = qurekaAdsSmallNativeGif;
    }

    public String getQurekaAdsInterstitialGif() {
        return qurekaAdsInterstitialGif;
    }

    public void setQurekaAdsInterstitialGif(String qurekaAdsInterstitialGif) {
        this.qurekaAdsInterstitialGif = qurekaAdsInterstitialGif;
    }

    public String getQurekaAdsOpenAppGif() {
        return qurekaAdsOpenAppGif;
    }

    public void setQurekaAdsOpenAppGif(String qurekaAdsOpenAppGif) {
        this.qurekaAdsOpenAppGif = qurekaAdsOpenAppGif;
    }

    public Boolean getFbBanner() {
        return fbBanner;
    }

    public void setFbBanner(Boolean fbBanner) {
        this.fbBanner = fbBanner;
    }

    public Boolean getFbNative() {
        return fbNative;
    }

    public void setFbNative(Boolean fbNative) {
        this.fbNative = fbNative;
    }

    public Boolean getFbSmallNative() {
        return fbSmallNative;
    }

    public void setFbSmallNative(Boolean fbSmallNative) {
        this.fbSmallNative = fbSmallNative;
    }

    public Boolean getFb250rectangle() {
        return fb250rectangle;
    }

    public void setFb250rectangle(Boolean fb250rectangle) {
        this.fb250rectangle = fb250rectangle;
    }

    public Boolean getFbInterstitial() {
        return fbInterstitial;
    }

    public void setFbInterstitial(Boolean fbInterstitial) {
        this.fbInterstitial = fbInterstitial;
    }

    public Boolean getAdmobBanner() {
        return admobBanner;
    }

    public void setAdmobBanner(Boolean admobBanner) {
        this.admobBanner = admobBanner;
    }

    public Boolean getAdmobNative() {
        return admobNative;
    }

    public void setAdmobNative(Boolean admobNative) {
        this.admobNative = admobNative;
    }

    public Boolean getAdmobInterstitial() {
        return admobInterstitial;
    }

    public void setAdmobInterstitial(Boolean admobInterstitial) {
        this.admobInterstitial = admobInterstitial;
    }

    public Boolean getAdmobSmallNative() {
        return admobSmallNative;
    }

    public void setAdmobSmallNative(Boolean admobSmallNative) {
        this.admobSmallNative = admobSmallNative;
    }

    public Boolean getAdmobRewordAds() {
        return admobRewordAds;
    }

    public void setAdmobRewordAds(Boolean admobRewordAds) {
        this.admobRewordAds = admobRewordAds;
    }

    public Boolean getApplovinBanner() {
        return applovinBanner;
    }

    public void setApplovinBanner(Boolean applovinBanner) {
        this.applovinBanner = applovinBanner;
    }

    public Boolean getApplovinNative() {
        return applovinNative;
    }

    public void setApplovinNative(Boolean applovinNative) {
        this.applovinNative = applovinNative;
    }

    public Boolean getApplovinInterstitial() {
        return applovinInterstitial;
    }

    public void setApplovinInterstitial(Boolean applovinInterstitial) {
        this.applovinInterstitial = applovinInterstitial;
    }

    public Boolean getQurekaBanner() {
        return qurekaBanner;
    }

    public void setQurekaBanner(Boolean qurekaBanner) {
        this.qurekaBanner = qurekaBanner;
    }

    public Boolean getQurekaNative() {
        return qurekaNative;
    }

    public void setQurekaNative(Boolean qurekaNative) {
        this.qurekaNative = qurekaNative;
    }

    public Boolean getQurekaSmallNative() {
        return qurekaSmallNative;
    }

    public void setQurekaSmallNative(Boolean qurekaSmallNative) {
        this.qurekaSmallNative = qurekaSmallNative;
    }

    public Boolean getQureka250rectangle() {
        return qureka250rectangle;
    }

    public void setQureka250rectangle(Boolean qureka250rectangle) {
        this.qureka250rectangle = qureka250rectangle;
    }

    public Boolean getQurekaInterstitial() {
        return qurekaInterstitial;
    }

    public void setQurekaInterstitial(Boolean qurekaInterstitial) {
        this.qurekaInterstitial = qurekaInterstitial;
    }

    public Boolean getQurekaAppOpen() {
        return qurekaAppOpen;
    }

    public void setQurekaAppOpen(Boolean qurekaAppOpen) {
        this.qurekaAppOpen = qurekaAppOpen;
    }
}
