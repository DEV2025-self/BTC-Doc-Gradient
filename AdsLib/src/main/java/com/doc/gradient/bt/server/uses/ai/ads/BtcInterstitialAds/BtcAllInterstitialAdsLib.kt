package com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.doc.gradient.bt.server.uses.ai.ads.BtcManager.BtcAppUtilsAdsLib
import com.doc.gradient.bt.server.uses.ai.ads.BtcManager.BtcConfigManagerAds
import com.doc.gradient.bt.server.uses.ai.ads.R
import com.facebook.ads.AbstractAdListener
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.InterstitialAd
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

@SuppressLint("StaticFieldLeak")
object BtcAllInterstitialAdsLib {

    private var facebookInterstitialAd: InterstitialAd? = null
    private var TAG = "FacebookInterstitialAd:Load"
    var context: Activity? = null
    private var mInterstitialAd: com.google.android.gms.ads.interstitial.InterstitialAd? = null
    private var mainProgress: ProgressDialog? = null
    private var allInterstitialAdCallBack: BtcAllInterstitialAdCallBack? = null
    private var COUNT = 0
    private var ADS_COUNT = 3
    private var isNext = false
    private var ADMOB_COUNT = 0
    private var ADMOB = 0
    @JvmStatic
    fun loadAllInterstitialAds(
        activity: Activity,
        allInterstitialAdCallBack1: BtcAllInterstitialAdCallBack
    ) {
        if (BtcConfigManagerAds.Sessionaryinstance!!.getIsAdsShow() && !BtcConfigManagerAds.Sessionaryinstance!!.getIsInterstitialPurchased()) {
            loadFbInterstitialAd(activity, allInterstitialAdCallBack1)
            loadGoogleInterstitialAdLoad(activity, allInterstitialAdCallBack1)
        }
    }

    /********************************************
     ******** FaceBook Interstitial Ads *********
     ********************************************/
    @JvmStatic
    fun loadFbInterstitialAd(
        activity: Activity,
        allInterstitialAdCallBack1: BtcAllInterstitialAdCallBack
    ) {
        if (BtcConfigManagerAds.Sessionaryinstance!!.getIsFaceBook() && BtcConfigManagerAds.Sessionaryinstance!!.getIsFbInterstitial() && !BtcConfigManagerAds.Sessionaryinstance!!.getFbInterstitialAdId()
                .isNullOrEmpty() && BtcConfigManagerAds.Sessionaryinstance!!.getFbInterstitialAdId() != "null"
        ) {
            if (BtcAppUtilsAdsLib.isValidContext(activity)) {
                context = activity
                allInterstitialAdCallBack = allInterstitialAdCallBack1
                facebookInterstitialAd = InterstitialAd(
                    activity, BtcConfigManagerAds.Sessionaryinstance!!.getFbInterstitialAdId()
                )
                val adListener: AbstractAdListener = object : AbstractAdListener() {
                    override fun onError(ad: Ad, error: AdError) {
                        HideDialog()
                        super.onError(ad, error)
                        Log.i(TAG, "loadFbInterstitialAd onError: " + error.errorMessage.toString())
                    }

                    override fun onAdLoaded(ad: Ad) {
                        Log.i(TAG, "loadFbInterstitialAd onAdLoaded: ")
                        super.onAdLoaded(ad)
                    }

                    override fun onAdClicked(ad: Ad) {
                        Log.i(TAG, "loadFbInterstitialAd onAdClicked: ")
                        super.onAdClicked(ad)
                    }

                    override fun onInterstitialDisplayed(ad: Ad) {
                        Log.i(TAG, "loadFbInterstitialAd onInterstitialDisplayed: ")
                        super.onInterstitialDisplayed(ad)
                        HideDialog()
                        if (facebookInterstitialAd != null && !facebookInterstitialAd!!.isAdLoaded) {
                            facebookInterstitialAd!!.loadAd()
                        }
                    }

                    override fun onInterstitialDismissed(ad: Ad) {
                        super.onInterstitialDismissed(ad)
                        Log.i(TAG, "loadFbInterstitialAd onInterstitialDismissed: ")
                        HideDialog()
                        if (isNext) {
                            isNext = false
                            allInterstitialAdCallBack!!.gotoNext()
                        }
                        if (facebookInterstitialAd != null && !facebookInterstitialAd!!.isAdLoaded) {
                            facebookInterstitialAd!!.loadAd()
                        }
                    }
                }
                val interstitialLoadAdConfig = facebookInterstitialAd!!.buildLoadAdConfig()
                    .withAdListener(adListener)
                    .build()
                facebookInterstitialAd!!.loadAd(interstitialLoadAdConfig)
            }
        }

    }


    /******************************************
     ******** Google Interstitial Ads *********
     ******************************************/
    @JvmStatic
    fun loadGoogleInterstitialAdLoad(
        activity: Activity,
        allInterstitialAdCallBack1: BtcAllInterstitialAdCallBack
    ) {

        if (BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmob() && BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmobInterstitial() && !BtcConfigManagerAds.Sessionaryinstance!!.getAdmobInterstitialAdId()
                .isNullOrEmpty() && BtcConfigManagerAds.Sessionaryinstance!!.getAdmobInterstitialAdId() != "null"
        ) {
            allInterstitialAdCallBack = allInterstitialAdCallBack1
            val adRequest: AdRequest = AdRequest.Builder().build()
            context = activity
            com.google.android.gms.ads.interstitial.InterstitialAd.load(
                activity,
                BtcConfigManagerAds.Sessionaryinstance!!.getAdmobInterstitialAdId(),
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(interstitialAd: com.google.android.gms.ads.interstitial.InterstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd
                        mInterstitialAd?.fullScreenContentCallback =
                            object : FullScreenContentCallback() {
                                override fun onAdClicked() {
                                    // Called when a click is recorded for an ad.
                                    Log.i(TAG, "Google InterstitialAd Ads onAdClicked: ")
                                }

                                override fun onAdDismissedFullScreenContent() {
                                    mInterstitialAd = null
                                    if (isNext) {
                                        isNext = false
                                        allInterstitialAdCallBack!!.gotoNext()
                                    }
                                    loadGoogleInterstitialAdLoad(
                                        context!!,
                                        allInterstitialAdCallBack!!
                                    )
                                }

                                override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                                    Log.i(
                                        TAG,
                                        "Google InterstitialAd Ads onAdFailedToShowFullScreenContent: "
                                    )
                                    mInterstitialAd = null

                                    HideDialog()
                                        if (isNext) {
                                            isNext = false
                                            allInterstitialAdCallBack!!.gotoNext()
                                        }

                                }

                                override fun onAdImpression() {
                                    Log.i(TAG, "Google InterstitialAd Ads onAdImpression: ")
                                    // Called when an impression is recorded for an ad.
                                }

                                override fun onAdShowedFullScreenContent() {
                                    HideDialog()
                                    Log.i(
                                        TAG,
                                        "Google InterstitialAd Ads onAdShowedFullScreenContent: "
                                    )
                                }
                            }
                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        Log.i(TAG, "Google Interstitial onAdFailedToLoad : ${loadAdError.message}")
                        // Handle the error
                        Log.d(TAG, loadAdError.toString())
                        mInterstitialAd = null

                        HideDialog()
                            if (isNext) {
                                isNext = false
                                allInterstitialAdCallBack!!.gotoNext()
                            }
                    }
                })
        }

    }
    /********************************************
     ******** Interstitial Ads CallBack *********
     ********************************************/
    @JvmStatic
    fun showItemClickAd(
        context1: Activity,
        allInterstitialAdCallBack1: BtcAllInterstitialAdCallBack,
    ) {

        isNext = true
        allInterstitialAdCallBack = allInterstitialAdCallBack1
        context = context1

        if (!BtcConfigManagerAds.Sessionaryinstance!!.getIsAdsShow() || BtcConfigManagerAds.Sessionaryinstance!!.getIsInterstitialPurchased()) {
            if (isNext) {
                isNext = false
                allInterstitialAdCallBack!!.gotoNext()
            }
            return
        }

        ADS_COUNT = try {
            BtcConfigManagerAds.Sessionaryinstance!!.gettingAdsCount()
        } catch (e: Exception) {
            4
        }

        ADMOB_COUNT = BtcConfigManagerAds.Sessionaryinstance!!.adMobCount1
        try {
            if (ADS_COUNT == 0 || ADS_COUNT == 1 || COUNT == 0 || COUNT == ADS_COUNT) {
                COUNT = 0
                COUNT++
                ADMOB++
                if (allInterstitialAdCallBack != null && BtcConfigManagerAds.Sessionaryinstance!!.getIsFbInterstitial() && context != null && BtcConfigManagerAds.Sessionaryinstance!!.getIsFaceBook() && !BtcConfigManagerAds.Sessionaryinstance!!.getFbInterstitialAdId()
                        .isNullOrEmpty() && BtcConfigManagerAds.Sessionaryinstance!!.getFbInterstitialAdId() != "null"
                ) {
                    if (facebookInterstitialAd != null && facebookInterstitialAd!!.isAdLoaded) {
                        // showing Ad
                        ShowDialog()
                        facebookInterstitialAd!!.show()
                    } else {
                        if (facebookInterstitialAd != null) {
                            facebookInterstitialAd!!.loadAd()
                        }
                        if (allInterstitialAdCallBack != null && BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmobInterstitial() && context != null && BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmob() && !BtcConfigManagerAds.Sessionaryinstance!!.getAdmobInterstitialAdId()
                                .isNullOrEmpty() && BtcConfigManagerAds.Sessionaryinstance!!.getAdmobInterstitialAdId() != "null" && ADMOB >= ADMOB_COUNT
                        ) {
                            if (mInterstitialAd != null) {
                                // showing Ad
                                ShowDialog()
                                mInterstitialAd!!.show(context!!)
                                ADMOB = 0
                            } else {
                                    if (isNext) {
                                        isNext = false
                                        allInterstitialAdCallBack!!.gotoNext()
                                    }
                            }
                        } else {
                            if (isNext) {
                                isNext = false
                                allInterstitialAdCallBack!!.gotoNext()
                            }
                        }
                    }
                } else if (allInterstitialAdCallBack != null && context != null && BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmob() && !BtcConfigManagerAds.Sessionaryinstance!!.getAdmobInterstitialAdId()
                        .isNullOrEmpty() && BtcConfigManagerAds.Sessionaryinstance!!.getAdmobInterstitialAdId() != "null" && ADMOB >= ADMOB_COUNT
                ) {
                    if (mInterstitialAd != null) {
                        // showing Ad
                        ShowDialog()
                        mInterstitialAd!!.show(context!!)
                        ADMOB = 0
                    } else {
                        if (isNext) {
                                isNext = false
                                allInterstitialAdCallBack!!.gotoNext()
                            }
                    }
                } else {
                    if (isNext) {
                        isNext = false
                        allInterstitialAdCallBack!!.gotoNext()
                    }
                }

            } else {
                COUNT++
                if (isNext) {
                    isNext = false
                    allInterstitialAdCallBack!!.gotoNext()
                }
            }
        } catch (e: Exception) {
            COUNT++
            if (isNext) {
                isNext = false
                allInterstitialAdCallBack!!.gotoNext()
            }
        }

    }
    /******************************************
     ******** Dialog Interstitial Ads *********
     ******************************************/
    @JvmStatic
    fun ShowDialog() {

        try {
            val activity: Activity = context!!
            if (BtcAppUtilsAdsLib.isValidContext(activity)) {
                if (mainProgress == null) {
                    mainProgress = ProgressDialog(activity, R.style.AdsLibRoundedProgressDialog)
                    mainProgress!!.setMessage("Loading Ads...")
                    mainProgress!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    mainProgress!!.isIndeterminate = true
                    mainProgress!!.setCancelable(false)
                    mainProgress!!.show()
                } else if (mainProgress!!.isShowing) {
                    mainProgress!!.setMessage("Loading Ads...")
                } else if (!mainProgress!!.isShowing) {
                    mainProgress!!.setMessage("Loading Ads...")
                    mainProgress!!.show()
                }
            }
        } catch (th: Exception) {
            th.printStackTrace()
        }

    }
    @JvmStatic
    private fun HideDialog() {

        Handler(Looper.getMainLooper()).post {
            try {
                if (mainProgress != null && mainProgress!!.isShowing) {
                    mainProgress!!.dismiss()
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

    }

}