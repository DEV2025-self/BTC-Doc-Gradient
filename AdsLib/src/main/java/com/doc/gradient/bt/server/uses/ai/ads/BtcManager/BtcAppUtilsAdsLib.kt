package com.doc.gradient.bt.server.uses.ai.ads.BtcManager

import android.app.Activity
import android.content.Context
import android.os.Build


object BtcAppUtilsAdsLib {

    @JvmStatic
    fun isValidContext(context: Context?): Boolean {
        if (context == null) {
            return false
        }
        if (context is Activity) {
            val activity = context
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                !activity.isDestroyed && !activity.isFinishing
            } else {
                !activity.isFinishing
            }
        }
        return true
    }

}