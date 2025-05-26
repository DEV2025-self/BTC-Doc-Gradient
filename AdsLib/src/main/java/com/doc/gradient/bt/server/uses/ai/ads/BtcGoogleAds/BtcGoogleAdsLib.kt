package com.doc.gradient.bt.server.uses.ai.ads.BtcGoogleAds

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.doc.gradient.bt.server.uses.ai.ads.BtcManager.BtcAppUtilsAdsLib.isValidContext
import com.doc.gradient.bt.server.uses.ai.ads.BtcManager.BtcConfigManagerAds
import com.doc.gradient.bt.server.uses.ai.ads.R
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.nativead.NativeAd


@SuppressLint("StaticFieldLeak")
object BtcGoogleAdsLib {

    val TAG = "GoogleAds:-"
    var testDevicesList: ArrayList<String> = ArrayList()
    private var adRequest: AdRequest? = null
    private var unifiedSmallNativeAds: NativeAd? = null
    var context: Activity? = null

    /***********************************
     ******** Goggle banner Ads *********
     ***********************************/

    @SuppressLint("MissingPermission")
    @JvmStatic
    fun loadGoogleBannerAds(
        activity: Activity,
        framelayout: FrameLayout,
        hideView: LinearLayout
    ) {

        if (framelayout != null && BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmobBanner() && isValidContext(
                activity
            ) && !BtcConfigManagerAds.Sessionaryinstance!!.getAdmobBannerAdId()
                .isNullOrEmpty() && BtcConfigManagerAds.Sessionaryinstance!!.getAdmobBannerAdId() != "null"
        ) {
            context = activity
            framelayout.removeAllViews()
            val layoutInflater = activity.layoutInflater
            if (layoutInflater != null) {
                val adLayout = layoutInflater.inflate(
                    R.layout.ads_admob_banner_fix_height_lay,
                    null as ViewGroup?
                )
                if (adLayout != null) {
                    framelayout.addView(adLayout)
                    val mAdView = AdView(activity)
                    mAdView.adUnitId = BtcConfigManagerAds.Sessionaryinstance!!.getAdmobBannerAdId()
                    val adViewContainer =
                        adLayout.findViewById<View>(R.id.adViewContainer) as FrameLayout
                    val layLoadingView =
                        adLayout.findViewById<View>(R.id.layLoadingView) as LinearLayout
                    val adSize = getAdSize(activity)
                    val lp = RelativeLayout.LayoutParams(
                        adSize!!.getWidthInPixels(activity),
                        adSize.getHeightInPixels(activity)
                    )
                    adViewContainer.layoutParams = lp
                    adViewContainer.removeAllViews()
                    adViewContainer.addView(mAdView)

                    layLoadingView.visibility = View.VISIBLE
                    if (adSize != null) {
                        mAdView.setAdSize(adSize)
                        mAdView.loadAd(adRequestInstance!!)
                        mAdView.adListener = object : AdListener() {
                            override fun onAdClosed() {
                                Log.i(TAG, "onAdClosed()")
                                super.onAdClosed()
                            }

                            override fun onAdLoaded() {
                                Log.i(TAG, "onAdLoaded()")
                                layLoadingView.visibility = View.GONE
                                hideView.visibility = View.GONE
                                framelayout.visibility = View.VISIBLE
                                super.onAdLoaded()
                            }

                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                try {
                                    Log.i(
                                        TAG,
                                        "loadGoogleBannerAds : Error  : ${loadAdError.message}"
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                framelayout.visibility = View.GONE
                                super.onAdFailedToLoad(loadAdError)
                                    hideView.visibility = View.GONE
                            }

                            override fun onAdOpened() {
                                Log.i(TAG, "onAdOpened()")
                                super.onAdOpened()
                            }
                        }
                    } else {
                        framelayout.visibility = View.VISIBLE
                        hideView.visibility = View.GONE
                    }
                } else {
                    framelayout.visibility = View.VISIBLE
                    hideView.visibility = View.GONE
                }
            } else {
                framelayout.visibility = View.VISIBLE
                hideView.visibility = View.GONE
            }
        } else {
            hideView.visibility = View.GONE
        }

    }

    fun getAdSize(activity: Activity): AdSize? {

        return if (isValidContext(activity)) {
            val windowManager = activity.windowManager
            if (windowManager != null) {
                val display = windowManager.defaultDisplay
                val outMetrics = DisplayMetrics()
                display.getMetrics(outMetrics)
                val widthPixels = outMetrics.widthPixels.toFloat()
                val density = outMetrics.density
                val adWidth = (widthPixels / density).toInt()
                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    activity,
                    adWidth
                )
            } else {
                null
            }
        } else {
            null
        }

    }

    val adRequestInstance: AdRequest?
        get() = if (adRequest == null) {
            adRequest = initAdRequest()
            adRequest
        } else {
            adRequest
        }

    private fun initAdRequest(): AdRequest {

        val extras = Bundle()
        val consentInformation = ConsentInformation.getInstance(context)
        if (consentInformation.consentStatus == ConsentStatus.NON_PERSONALIZED) {
            extras.putString("npa", "1")
        }
        val adRequest = AdRequest.Builder()
        val requestConfiguration = RequestConfiguration.Builder()
        testDevicesList.clear()
        testDevicesList.add("93FF8D3D7A4D490CDB783F42C4563B84")
        testDevicesList.add("18D309D55BF18A6B3CD3BE5D989E918A")
        testDevicesList.add("AB9DA8501D4372B6409CEE6C4DA001DE")
        testDevicesList.add("9934F5C68EEE40E2CE37B72279C30CA8")
        testDevicesList.add("9261E3D590EBA1796890AAB6A3BD1098")
        testDevicesList.add("E8D20192854472FB61946D3D967926B4")
        testDevicesList.add("9C683C656FDF231BD4452A8A2D044C86")
        testDevicesList.add("9591BDF7481704F5F6E8D8C616BFD186")
        testDevicesList.add("B97BE502AA8DD5E546F7D69318CF682D")
        requestConfiguration.setTestDeviceIds(testDevicesList)
        MobileAds.setRequestConfiguration(requestConfiguration.build())
        return adRequest.addNetworkExtrasBundle(AdMobAdapter::class.java, extras).build()

    }
}