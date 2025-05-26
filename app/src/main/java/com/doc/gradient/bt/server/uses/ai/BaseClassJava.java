package com.doc.gradient.bt.server.uses.ai;

import androidx.appcompat.app.AppCompatDelegate;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.BDG_PurchaseBillingClient;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.BDG_Decode;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.ads.BtcManager.BtcConfigManagerAds;
import com.google.firebase.FirebaseApp;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseClassJava extends android.app.Application {

    protected static BaseClassJava instance;
    static {
        System.loadLibrary("server_config");
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    // Native method declarations
    public native String getBaseUrl();

    public native String getSoketUrl();

    public native String getToken();

    @Override
    public void onCreate() {
        super.onCreate();

        String baseUrl = getBaseUrl();
        String socketUrl = getSoketUrl();
        String token = getToken();

        ProjectConstantsJava.Project_BASE_URL = BDG_Decode.BDG_decode(baseUrl);
        ProjectConstantsJava.Project_SOCKET_URL = BDG_Decode.BDG_decode(socketUrl);
        ProjectConstantsJava.Project_TOKEN = BDG_Decode.BDG_decode(token);
        instance = this;

        FirebaseApp.initializeApp(this);

        // Init SharedPreferences Manager
        if (SessionaryJava.getSessionaryinstance() != null) {
            SessionaryJava.getSessionaryinstance().initSharedPreferencesManager(getApplicationContext());
        }

        if (BtcConfigManagerAds.getSessionaryinstance() != null) {
            BtcConfigManagerAds.getSessionaryinstance().initObAdMobConfigManager(getApplicationContext());
        }

        if (BDG_PurchaseBillingClient.getInstance() != null) {
            BDG_PurchaseBillingClient.getInstance().initBillingManager(getApplicationContext());
        }
    }

}