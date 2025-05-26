package com.doc.gradient.bt.server.uses.ai.ads.BtcManager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.facebook.BuildConfig
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds

class BtcConfigManagerAds {

    var context: Context? = null

    fun initObAdMobConfigManager(applicationContext: Context) {

        this.context = applicationContext
        MobileAds.initialize(applicationContext) {
            Log.i(TAG, "onInitializationComplete : MobileAds initialize successfully.")
        }

        AudienceNetworkAds.initialize(applicationContext)
        try {
            FacebookSdk.fullyInitialize()
            FacebookSdk.setAutoInitEnabled(true)
            FacebookSdk.setAdvertiserIDCollectionEnabled(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (BuildConfig.DEBUG) {
            AdSettings.setTestMode(true)
            AdSettings.addTestDevice("d2229ea2-081e-4142-845f-065f6e710024")
            FacebookSdk.setIsDebugEnabled(true)
            FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS)
        }

    }

    /*********************************************
     ******** Check Enable Or Not Start **********
     *********************************************/

    var isBannerPurchased = false
    var isNativePurchased = false
    var isFb250Purchased = false
    var isSmallNativePurchased = false
    var isInterstitialPurchased = false
    var isRewordVideoAdsPurchased = false
    var isOpenAppPurchased = false
    var isFaceBook = false
    var isAdmob = false
    var isTestAds = false
    var isAdsShow = false
    var adsCount: Int = 3
    var adMobCount1: Int = 0

    fun getIsTestAds(): Boolean {
        return isTestAds
    }

    fun setIsTestAds(isTestAds: Boolean): BtcConfigManagerAds {
        this.isTestAds = isTestAds
        if (isTestAds) {
            AdSettings.setTestMode(true)
            AdSettings.addTestDevice("d2229ea2-081e-4142-845f-065f6e710024")
            FacebookSdk.setIsDebugEnabled(true)
            FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS)
        }
        return this
    }

    fun getIsBannerPurchased(): Boolean {
        return isBannerPurchased
    }
    fun getIsInterstitialPurchased(): Boolean {
        return isInterstitialPurchased
    }
    fun getIsAdsShow(): Boolean {
        return isAdsShow
    }

    fun setIsAdsShow(isAdsShow: Boolean): BtcConfigManagerAds {
        this.isAdsShow = isAdsShow
        return this
    }

    fun getIsFaceBook(): Boolean {
        return isFaceBook
    }

    fun setIsFaceBook(isFaceBook: Boolean): BtcConfigManagerAds {
        this.isFaceBook = isFaceBook
        return this
    }

    fun gettingAdsCount(): Int {
        return adsCount
    }

    fun settingAdsCount(adsCount: Int): BtcConfigManagerAds {
        this.adsCount = adsCount
        return this
    }

    fun setAdMobCount(value: Int): BtcConfigManagerAds {
        adMobCount1 = value
        return this
    }


    fun getIsAdmob(): Boolean {
        return isAdmob
    }

    fun setIsAdmob(isAdmob: Boolean): BtcConfigManagerAds {
        this.isAdmob = isAdmob
        return this
    }


    /*********************************************
     ******** Check Enable Or Not End **********
     *********************************************/

    /*****************************************
     ******** FaceBook Ads Id Start **********
     *****************************************/

    var fbNativeBannerAd: String? = null
    var fbNativeAd: String? = null
    var fbInterstitialAd: String? = null
    var fbNativeBanner250: String? = null
    var isFbBanner = true
    var isFbNative = true
    var isFbSmallNative = true
    var isFb250rectangle = true
    var isFbInterstitial = true

    fun getIsFbBanner(): Boolean {
        return isFbBanner
    }

    fun setIsFbBanner(isFbBanner: Boolean): BtcConfigManagerAds {
        this.isFbBanner = isFbBanner
        return this
    }

    fun setIsFbNative(isFbNative: Boolean): BtcConfigManagerAds {
        this.isFbNative = isFbNative
        return this
    }

    fun setIsFbSmallNative(isFbSmallNative: Boolean): BtcConfigManagerAds {
        this.isFbSmallNative = isFbSmallNative
        return this
    }


    fun setIsFb250rectangle(isFb250rectangle: Boolean): BtcConfigManagerAds {
        this.isFb250rectangle = isFb250rectangle
        return this
    }

    fun getIsFbInterstitial(): Boolean {
        return isFbInterstitial
    }

    fun setIsFbInterstitial(isFbInterstitial: Boolean): BtcConfigManagerAds {
        this.isFbInterstitial = isFbInterstitial
        return this
    }


    fun getFbNativeBannerAdId(): String {
        return fbNativeBannerAd!!
    }

    fun setFbNativeBannerAdId(fbNativeBannerAd: String): BtcConfigManagerAds {
        this.fbNativeBannerAd = fbNativeBannerAd
        return this
    }

    fun getFbNativeAdId(): String {
        return fbNativeAd!!
    }

    fun setFbNativeAdId(fbNativeAd: String): BtcConfigManagerAds {
        this.fbNativeAd = fbNativeAd
        return this
    }

    fun getFbInterstitialAdId(): String {
        return fbInterstitialAd!!
    }

    fun setFbInterstitialAdId(fbInterstitialAd: String): BtcConfigManagerAds {
        this.fbInterstitialAd = fbInterstitialAd
        return this
    }

    fun setFbNativeBanner250Id(fbNativeBanner250: String): BtcConfigManagerAds {
        this.fbNativeBanner250 = fbNativeBanner250
        return this
    }

    /*****************************************
     ******** FaceBook Ads Id End ************
     *****************************************/

    /****************************************
     ******** Google Ads Id Start************
     ****************************************/

    var admobBannerAd: String? = null
    var admobNativeAd: String? = null
    var admobInterstitialAd: String? = null
    var admobRewordAdsIds: String? = null
    var isAdmobBanner = true
    var isAdmobNative = true
    var isAdmobInterstitial = true
    var isAdmobSmallNative = true
    var isAdmobRewordAds = true

    fun getIsAdmobBanner(): Boolean {
        return isAdmobBanner
    }

    fun setIsAdmobBanner(isAdmobBanner: Boolean): BtcConfigManagerAds {
        this.isAdmobBanner = isAdmobBanner
        return this
    }

    fun setIsAdmobNative(isAdmobNative: Boolean): BtcConfigManagerAds {
        this.isAdmobNative = isAdmobNative
        return this
    }

    fun setIsAdmobSmallNative(isAdmobSmallNative: Boolean): BtcConfigManagerAds {
        this.isAdmobSmallNative = isAdmobSmallNative
        return this
    }

    fun setIsAdmobRewordAds(isAdmobRewordAds: Boolean): BtcConfigManagerAds {
        this.isAdmobRewordAds = isAdmobRewordAds
        return this
    }

    fun getIsAdmobInterstitial(): Boolean {
        return isAdmobInterstitial
    }

    fun setIsAdmobInterstitial(isAdmobInterstitial: Boolean): BtcConfigManagerAds {
        this.isAdmobInterstitial = isAdmobInterstitial
        return this
    }


    fun getAdmobBannerAdId(): String {
        if (getIsTestAds()) {
            this.admobBannerAd = "ca-app-pub-3940256099942544/6300978111"
        }
        return admobBannerAd!!
    }

    fun setAdmobBannerAdId(admobBannerAd: String): BtcConfigManagerAds {
        this.admobBannerAd = admobBannerAd
        return this
    }

    fun getAdmobNativeAdId(): String {
        if (isTestAds) {
            this.admobNativeAd = "ca-app-pub-3940256099942544/2247696110"
        }
        return admobNativeAd!!
    }

    fun setAdmobNativeAdId(admobNativeAd: String): BtcConfigManagerAds {
        this.admobNativeAd = admobNativeAd
        return this
    }

    fun getAdmobInterstitialAdId(): String {
        if (isTestAds) {
            this.admobInterstitialAd = "ca-app-pub-3940256099942544/1033173712"
        }
        return admobInterstitialAd!!
    }

    fun setAdmobInterstitialAdId(admobInterstitialAd: String): BtcConfigManagerAds {
        this.admobInterstitialAd = admobInterstitialAd
        return this
    }

    fun getAdmobRewordAdsId(): String {
        if (getIsTestAds()) {
            this.admobRewordAdsIds = "ca-app-pub-3940256099942544/5224354917"
        }
        return admobRewordAdsIds!!
    }

    fun setAdmobRewordAdsId(admobRewordAdsIds: String): BtcConfigManagerAds {
        this.admobRewordAdsIds = admobRewordAdsIds
        return this
    }


    /****************************************
     ******** Google Ads Id End *************
     ****************************************/


    companion object {

        @SuppressLint("StaticFieldLeak")
        var adMobsConfigManager: BtcConfigManagerAds? = null
        var TAG = "BtcConfigManagerAds--------------------"

        @JvmStatic
        val Sessionaryinstance: BtcConfigManagerAds?
            get() {
                if (adMobsConfigManager == null) {
                    adMobsConfigManager = BtcConfigManagerAds()
                }
                return adMobsConfigManager
            }

    }

}