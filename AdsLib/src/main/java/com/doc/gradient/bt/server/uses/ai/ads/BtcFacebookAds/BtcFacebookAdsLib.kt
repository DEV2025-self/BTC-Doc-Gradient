package  com.doc.gradient.bt.server.uses.ai.ads.FacebookAds

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.doc.gradient.bt.server.uses.ai.ads.BtcGoogleAds.BtcGoogleAdsLib
import com.doc.gradient.bt.server.uses.ai.ads.BtcManager.BtcAppUtilsAdsLib.isValidContext
import com.doc.gradient.bt.server.uses.ai.ads.BtcManager.BtcConfigManagerAds
import com.doc.gradient.bt.server.uses.ai.ads.R
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.AdOptionsView
import com.facebook.ads.MediaView
import com.facebook.ads.NativeAdBase
import com.facebook.ads.NativeAdLayout
import com.facebook.ads.NativeAdListener
import com.facebook.ads.NativeBannerAd

object BtcFacebookAdsLib {

    private const val TAG = "FacebookAdsConfig"

    /**************************************
     ******** FaceBook Banner Ads *********
     **************************************/

    @JvmStatic
    fun loadBannerAds(
        context: Activity,
        layNative: FrameLayout,
        hideView: LinearLayout
    ) {
        if (BtcConfigManagerAds.Sessionaryinstance!!.getIsAdsShow() && !BtcConfigManagerAds.Sessionaryinstance!!.getIsBannerPurchased()) {
            if (BtcConfigManagerAds.Sessionaryinstance!!.getIsFaceBook() && BtcConfigManagerAds.Sessionaryinstance!!.getIsFbBanner() && !BtcConfigManagerAds.Sessionaryinstance!!.getFbNativeBannerAdId()
                    .isNullOrEmpty() && BtcConfigManagerAds.Sessionaryinstance!!.getFbNativeBannerAdId() != "null"
            ) {
                val nativeAd = NativeBannerAd(
                    context,
                    BtcConfigManagerAds.Sessionaryinstance!!.getFbNativeBannerAdId()
                )
                val nativeAdListener: NativeAdListener = object : NativeAdListener {
                    override fun onMediaDownloaded(ad: Ad) {}
                    override fun onError(ad: Ad, adError: AdError) {
                        try {
                            Log.i(
                                TAG,
                                "loadBannerFB onError :${adError.errorMessage} "
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        if (nativeAd == null || nativeAd !== ad) {
                            return
                        }
                        layNative.visibility = View.GONE
                        if (BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmob() && BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmobBanner()) {
                            BtcGoogleAdsLib.loadGoogleBannerAds(context, layNative, hideView)
                        } else {
                            hideView.visibility = View.GONE
                        }
                    }

                    override fun onAdLoaded(ad: Ad) {
                        Log.i(TAG, "loadNativeBannerFBBannerAd onAdLoaded: ")
                        if (nativeAd == null || nativeAd !== ad) {
                            return
                        }
                        val inflater = context.layoutInflater
                        val view = inflater.inflate(R.layout.ads_native_layout, null)
                        val nativeAdLayout =
                            view.findViewById<NativeAdLayout>(R.id.native_ad_container)
                        layNative.removeAllViews()
                        layNative.addView(view, 0)
                        layNative.visibility = View.VISIBLE
                        nativeAdLayout.visibility = View.VISIBLE
                        if (isValidContext(context)) {
                            inflateAdNativeBanner(context, nativeAd, nativeAdLayout)
                        }
                        nativeAd.downloadMedia()
                    }

                    override fun onAdClicked(ad: Ad) {}
                    override fun onLoggingImpression(ad: Ad) {}
                }
                nativeAd.loadAd(
                    nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
                        .build()
                )
            } else if (BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmob() && BtcConfigManagerAds.Sessionaryinstance!!.getIsAdmobBanner()) {
                BtcGoogleAdsLib.loadGoogleBannerAds(context, layNative, hideView)
            } else {
                hideView.visibility = View.GONE
            }
        } else {
            hideView.visibility = View.GONE
        }

    }

    @SuppressLint("RestrictedApi", "CutPasteId")
    private fun inflateAdNativeBanner(
        context: Activity,
        nativeBannerAd: NativeBannerAd,
        nativeAdLayout: NativeAdLayout
    ) {

        try {
            // Unregister last ad
            nativeBannerAd.unregisterView()

            // Add the Ad view into the ad container.
            val inflater = LayoutInflater.from(context)
            // Inflate the Ad view.  The layout referenced is the one you created in the last step.
            val adView =
                inflater.inflate(R.layout.ads_item_native_banner, nativeAdLayout, false)
            nativeAdLayout.addView(adView)

            // Add the AdChoices icon
            val adChoicesContainer = adView.findViewById<RelativeLayout>(R.id.ad_choices_container)
            if (adChoicesContainer != null) {
                val adOptionsView = AdOptionsView(context, nativeBannerAd, nativeAdLayout)
                adChoicesContainer.removeAllViews()
                adChoicesContainer.addView(adOptionsView, 0)

                // Create native UI using the ad metadata.
                val nativeAdTitle = adView.findViewById<TextView>(R.id.native_ad_title)
                val nativeAdSocialContext =
                    adView.findViewById<TextView>(R.id.native_ad_social_context)
                val sponsoredLabel = adView.findViewById<TextView>(R.id.native_ad_sponsored_label)
                val nativeAdIconView = adView.findViewById<MediaView>(R.id.native_icon_view)
                val nativeAdCallToAction =
                    adView.findViewById<AppCompatButton>(R.id.native_ad_call_to_action)
                nativeAdCallToAction.text = nativeBannerAd.adCallToAction
                nativeAdCallToAction.visibility =
                    if (nativeBannerAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE
                nativeAdTitle.text = nativeBannerAd.advertiserName
                nativeAdSocialContext.text = nativeBannerAd.adSocialContext
                sponsoredLabel.text = nativeBannerAd.sponsoredTranslation

                // Register the Title and CTA button to listen for clicks.
                val clickableViews: MutableList<View> = ArrayList()
                clickableViews.add(nativeAdTitle)
                clickableViews.add(nativeAdCallToAction)
                nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}