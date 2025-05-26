package com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.service.controls.ControlsProviderService;
import android.util.Log;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.Interface.BDG_BillingUpdatesListener;

import java.util.ArrayList;

public class BDG_Utils {

    public static final String KEY_PURCHASED_DETAIL = "purchased_detail";
    public static final String IS_PURCHASED_AD_FREE = "is_purchased_ad_free";
    static final String TAG = "BDG_Utils";
    static Activity activity;
    static BDG_BillingUpdatesListener mBillingUpdatesListener;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;
    static String appname = "";
    static String APPLICATION_PURCHASE_TYPE = "";
    static String PURCHASE_TYPE_INAPP = "";
    static String PURCHASE_TYPE_SUB = "subs";
    static String PURCHASE_TYPE_BOTH = "";
    static String DEFAULT_PURCHASE_SELECTION = "";
    static BDG_Utils utils;
    String BDG_PURCHASE_ID_AD_FREE = "";
    ArrayList<String> productIds = new ArrayList<>();

    public static BDG_Utils getUtilsInstance() {
        if (utils == null) {
            utils = new BDG_Utils();
        }
        return utils;
    }

    public static boolean isValidContext(Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return !activity.isDestroyed() && !activity.isFinishing();
            } else {
                return !activity.isFinishing();
            }
        }
        return true;
    }

    public static String throwFatalWithParams(String title, String function, String callback, String error, int error_code, String app_name, String message) {
        String exception_message = String.format(
                "Title : %s\n" +
                        "Function : %s\n" +
                        "Callback : %s\n" +
                        "Error : %s\n" +
                        "Error_code : %s\n" +
                        "App_name : %s\n" +
                        "Message : %s",
                title, function, callback, error, error_code, app_name, message
        );
        Log.i(ControlsProviderService.TAG, "throwFatalExceptionWithParams: exception_message :- " + exception_message);
        return exception_message;
    }

    // Getters for static fields
    public static Activity getActivity() {
        return activity;
    }

    public static BDG_BillingUpdatesListener getmBillingUpdatesListener() {
        return mBillingUpdatesListener;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static SharedPreferences.Editor getEditor() {
        return editor;
    }

    public static String getAppname() {
        return appname;
    }

    public static String getAPPLICATION_PURCHASE_TYPE() {
        return APPLICATION_PURCHASE_TYPE;
    }

    public static String getPURCHASE_TYPE_INAPP() {
        return PURCHASE_TYPE_INAPP;
    }

    public static String getPURCHASE_TYPE_SUB() {
        return PURCHASE_TYPE_SUB;
    }

    public static String getPURCHASE_TYPE_BOTH() {
        return PURCHASE_TYPE_BOTH;
    }

    public static String getDEFAULT_PURCHASE_SELECTION() {
        return DEFAULT_PURCHASE_SELECTION;
    }

    public BDG_Utils setSharedPreferences(SharedPreferences sharedPreference, SharedPreferences.Editor editors) {
        sharedPreferences = sharedPreference;
        editor = editors;
        return this;
    }

    public BDG_Utils setAppName(String appName) {
        appname = appName;
        return this;
    }

    public BDG_Utils setIdAdFree(String PURCHASE_ID_AD_FREE) {
        this.BDG_PURCHASE_ID_AD_FREE = PURCHASE_ID_AD_FREE;
        return this;
    }

    public BDG_Utils setTypeInApp(String PURCHASE_TYPE_INAPP1) {
        PURCHASE_TYPE_INAPP = PURCHASE_TYPE_INAPP1;
        return this;
    }

    public BDG_Utils setTypeSubs(String PURCHASE_TYPE_SUB1) {
        PURCHASE_TYPE_SUB = PURCHASE_TYPE_SUB1;
        return this;
    }

    public BDG_Utils setTypeBoth(String PURCHASE_TYPE_BOTH1) {
        PURCHASE_TYPE_BOTH = PURCHASE_TYPE_BOTH1;
        return this;
    }

    public BDG_Utils setAppPurchaseType(String APPLICATION_PURCHASE_TYPE1) {
        APPLICATION_PURCHASE_TYPE = APPLICATION_PURCHASE_TYPE1;
        return this;
    }

    public BDG_Utils setDefaultPurchaseSelection(String DEFAULT_PURCHASE_SELECTION1) {
        DEFAULT_PURCHASE_SELECTION = DEFAULT_PURCHASE_SELECTION1;
        return this;
    }

    public BDG_Utils prepareProductIdList(ArrayList<String> productIds) {
        this.productIds.clear();
        this.productIds.addAll(productIds);
        Log.i(TAG, "prepareSkuList: ProductIdList" + this.productIds);

        if (!productIds.isEmpty()) {
            Log.i(TAG, "prepareSkuList: ProductIdList" + productIds);
            BDG_PurchaseBillingClient.getInstance().queryInventory();
        }
        return this;
    }

    public BDG_Utils setBillingUpdatesListener(BDG_BillingUpdatesListener mBillingUpdatesListener1, Activity activity1) {
        Log.i(TAG, "setBillingUpdatesListener: ");
        mBillingUpdatesListener = mBillingUpdatesListener1;
        activity = activity1;
        return this;
    }

    // Getter for instance fields
    public ArrayList<String> getProductIds() {
        return productIds;
    }

    public String getBDG_PURCHASE_ID_AD_FREE() {
        return BDG_PURCHASE_ID_AD_FREE;
    }
}