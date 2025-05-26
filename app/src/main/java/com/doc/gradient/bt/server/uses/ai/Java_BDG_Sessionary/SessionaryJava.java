package com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary;


import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_CONST_FORCE_DISABLE_ADS;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.BDG_Utils;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_AppSetting.BDG_AppSettingData;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_AppSetting.BDG_AppSettingResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Login.BDG_LoginData;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Login.BDG_LoginResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.BDG_PlanData;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Signup.BDG_SignupModelClass;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfo.BDG_UserInfoData;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfo.BDG_UserInfoResponse;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class SessionaryJava {
    // Constants
    public static final String USER_APP_SETTING = "user_app_setting";
    public static final String USER_LOGIN = "user_login";
    public static final String USER_INFO = "user_info";
    public static final String USER_SIGNUP = "user_signup";
    public static final String GET_PLAN_DETAILS = "get_Plan_Details";
    public static final String KEY_IS_PURCHASED_AD_FREE = "is_purchased_ad_free";
    public static final String KEY_IS_PURCHASED_RESTORE = "is_purchased_restore";
    public static final String KEY_PURCHASED_DETAIL = "purchased_detail";
    public static final String KEY_REMOVE_ADS_PURCHASE_DETAILS = "remove_ads_purchase_details";
    public static final String KEY_BASIC_PRICE_DETAILS = "basic_price_details";
    public static final String KEY_STANDARD_PRICE_DETAILS = "standard_price_details";
    public static final String KEY_SILVER_PRICE_DETAILS = "silver_price_details";
    public static final String GET_PLAN_DETAILS_NEW = "get_Plan_Details_new";
    public static final String KEY_GOLD_PRICE_DETAILS = "gold_price_details";
    public static final String KEY_DIAMOND_PRICE_DETAILS = "diamond_price_details";
    public static final String PLAN_LIST = "plan_list";
    public static final String SHOW_ADS = "show_ads";
    public static final String IS_LOGOUT = "is_logout";

    private static final String TAG = "SessioNexus";
    private static SessionaryJava sessionary;
    private static Gson gson;
    // Editor for Shared preferences
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;
    private Context _context;

    public static SessionaryJava getSessionaryinstance() {
        if (sessionary == null) {
            sessionary = new SessionaryJava();
        }
        return sessionary;
    }

    private Gson getGsonInstance() {
        if (gson == null) {
            gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
        }
        return gson;
    }

    public void initSharedPreferencesManager(Context context) {
        _context = context;
        pref = _context.getSharedPreferences(context.getApplicationInfo().packageName, 0);
        prefEditor = pref.edit();
        prefEditor.apply();

        if (pref != null && prefEditor != null) {
            BDG_Utils.getUtilsInstance().setSharedPreferences(pref, prefEditor);
        }

    }

    public BDG_AppSettingData getAppSettingApi() {
        BDG_AppSettingResponse appSettingResponse = new BDG_AppSettingResponse();
        String json = pref.getString(USER_APP_SETTING, "");

        if (!TextUtils.isEmpty(json) && !json.isEmpty() && !json.equals("null")) {
            appSettingResponse = getGsonInstance().fromJson(json, BDG_AppSettingResponse.class);
        } else {
            appSettingResponse = new BDG_AppSettingResponse();
        }
        return appSettingResponse.getData();
    }

    public void SetsettingsClient(BDG_AppSettingResponse appSettingResponse) {
        String json = getGsonInstance().toJson(appSettingResponse);
        prefEditor.putString(USER_APP_SETTING, json);
        prefEditor.commit();
    }

    public int getShowAdsCount() {
        return pref.getInt(SHOW_ADS, 0);
    }

    public void setShowAdsCount(int showAdsCount) {
        prefEditor.putInt(SHOW_ADS, showAdsCount);
        prefEditor.commit();
    }

    public void SetSignUP(BDG_SignupModelClass loginResponse) {
        String json = getGsonInstance().toJson(loginResponse);
        prefEditor.putString(USER_SIGNUP, json);
        prefEditor.commit();
    }

    public void SetLoginApi(BDG_LoginResponse loginResponse) {
        String json = getGsonInstance().toJson(loginResponse);
        prefEditor.putString(USER_LOGIN, json);
        prefEditor.commit();
    }

    public void setUserLogOut(Boolean isLogout) {
        if (isLogout != null) {
            prefEditor.putBoolean(IS_LOGOUT, isLogout);
        }
        prefEditor.apply();
    }

    public boolean getUserLogout() {
        return pref.getBoolean(IS_LOGOUT, true);
    }

    public void SetUserInfo(BDG_UserInfoResponse appSettingResponse) {
        String json = getGsonInstance().toJson(appSettingResponse);
        prefEditor.putString(USER_INFO, json);
        prefEditor.commit();
    }

    public BDG_UserInfoData getUserInfo() {
        BDG_UserInfoResponse appuserResponse = new BDG_UserInfoResponse();
        String json = pref.getString(USER_INFO, "");

        if (!TextUtils.isEmpty(json) && !json.isEmpty() && !json.equals("null")) {
            appuserResponse = getGsonInstance().fromJson(json, BDG_UserInfoResponse.class);
        } else {
            appuserResponse = new BDG_UserInfoResponse();
        }
        return appuserResponse.getData();
    }

    public BDG_LoginData getLoginApi() {
        BDG_LoginResponse loginResponse = new BDG_LoginResponse();
        String json = pref.getString(USER_LOGIN, "");

        if (!TextUtils.isEmpty(json) && !json.isEmpty() && !json.equals("null")) {
            loginResponse = getGsonInstance().fromJson(json, BDG_LoginResponse.class);
        } else {
            loginResponse = new BDG_LoginResponse();
        }
        return (loginResponse.getData() != null) ? loginResponse.getData() : new BDG_LoginData();
    }

    public void saveAllPlans(List<BDG_PlanData> plansDataArrayList) {
        pref.edit().putString(PLAN_LIST, getGsonInstance().toJson(plansDataArrayList)).apply();
    }

    public ArrayList<BDG_PlanData> getAllPlan() {
        String json = pref.getString(PLAN_LIST, null);
        ArrayList<BDG_PlanData> plansDataArrayList = new ArrayList<>();

        if (!TextUtils.isEmpty(json) && !json.isEmpty() && !json.equals("null")) {
            plansDataArrayList = getGsonInstance().fromJson(json, new TypeToken<ArrayList<BDG_PlanData>>() {
            }.getType());
        }
        return plansDataArrayList;
    }

    public void SetPlanDetails(ArrayList<BDG_PlanData> planResponse) {
        String json = getGsonInstance().toJson(planResponse);
        prefEditor.putString(GET_PLAN_DETAILS, json);
        prefEditor.commit();
    }

    public ArrayList<BDG_PlanData> getPlanDetails() {
        String json = pref.getString(GET_PLAN_DETAILS, "");
        ArrayList<BDG_PlanData> plansDataArrayList = new ArrayList<>();

        if (!TextUtils.isEmpty(json) && !json.isEmpty() && !json.equals("null")) {
            plansDataArrayList = getGsonInstance().fromJson(json, new TypeToken<ArrayList<BDG_PlanData>>() {
            }.getType());
        }
        return plansDataArrayList;
    }

    public void setIsPurchaseAdFree(boolean isPurchaseAdFree) {
        Log.i(TAG, "Purchase status changed to: " + isPurchaseAdFree);
        prefEditor.putBoolean(KEY_IS_PURCHASED_AD_FREE, isPurchaseAdFree);
        prefEditor.commit();
    }

    public boolean getIsPurchasedRestore() {
        return pref.getBoolean(KEY_IS_PURCHASED_RESTORE, Project_CONST_FORCE_DISABLE_ADS);
    }

    public void setIsPurchasedRestore(boolean isPurchased) {
        Log.i(TAG, "Purchase Restore status changed to: " + isPurchased);
        prefEditor.putBoolean(KEY_IS_PURCHASED_RESTORE, isPurchased);
        prefEditor.commit();
    }

    public String getKeyPurchasedDetail() {
        return pref.getString(KEY_PURCHASED_DETAIL, "");
    }

    public void setKeyPurchasedDetail(String newPurchasedDetail) {
        Log.i(TAG, "newPurchasedDetail changed to: " + newPurchasedDetail);
        prefEditor.putString(KEY_PURCHASED_DETAIL, newPurchasedDetail);
        prefEditor.commit();
    }

    public String getBasicPriceDetails() {
        return pref.getString(KEY_BASIC_PRICE_DETAILS, "");
    }

    public void setBasicPriceDetails(String newMonthlyPriceDetails) {
        Log.i(TAG, "newMonthlyPriceDetails changed to: " + newMonthlyPriceDetails);
        prefEditor.putString(KEY_BASIC_PRICE_DETAILS, newMonthlyPriceDetails);
        prefEditor.commit();
    }

    public String getStandardPriceDetails() {
        return pref.getString(KEY_STANDARD_PRICE_DETAILS, "");
    }

    public void setStandardPriceDetails(String newSixMonthlyPriceDetails) {
        Log.i(TAG, "newSixMonthlyPriceDetails changed to: " + newSixMonthlyPriceDetails);
        prefEditor.putString(KEY_STANDARD_PRICE_DETAILS, newSixMonthlyPriceDetails);
        prefEditor.commit();
    }

    public String getSilverPriceDetails() {
        return pref.getString(KEY_SILVER_PRICE_DETAILS, "");
    }

    public void setSilverPriceDetails(String newTwelveMonthlyPriceDetails) {
        Log.i(TAG, "newTwelveMonthlyPriceDetails changed to: " + newTwelveMonthlyPriceDetails);
        prefEditor.putString(KEY_SILVER_PRICE_DETAILS, newTwelveMonthlyPriceDetails);
        prefEditor.commit();
    }

    public String getGoldPriceDetails() {
        return pref.getString(KEY_GOLD_PRICE_DETAILS, "");
    }

    public void setGoldPriceDetails(String newTwelveMonthlyPriceDetails) {
        Log.i(TAG, "newTwelveMonthlyPriceDetails changed to: " + newTwelveMonthlyPriceDetails);
        prefEditor.putString(KEY_GOLD_PRICE_DETAILS, newTwelveMonthlyPriceDetails);
        prefEditor.commit();
    }

    public String getDiamondPriceDetails() {
        return pref.getString(KEY_DIAMOND_PRICE_DETAILS, "");
    }

    public void setDiamondPriceDetails(String newTwelveMonthlyPriceDetails) {
        Log.i(TAG, "newTwelveMonthlyPriceDetails changed to: " + newTwelveMonthlyPriceDetails);
        prefEditor.putString(KEY_DIAMOND_PRICE_DETAILS, newTwelveMonthlyPriceDetails);
        prefEditor.commit();
    }

    public String getRemoveAdsPurchaseDetails() {
        return pref.getString(KEY_REMOVE_ADS_PURCHASE_DETAILS, "");
    }

    public void setRemoveAdsPurchaseDetails(String newOneTimePurchaseDetails) {
        Log.i(TAG, "removeAdsPriceDetails changed to: " + newOneTimePurchaseDetails);
        prefEditor.putString(KEY_REMOVE_ADS_PURCHASE_DETAILS, newOneTimePurchaseDetails);
        prefEditor.commit();
    }

    public String getPriceDetails(String key) {
        return pref.getString(key, "");
    }

    public void setPriceDetails(String key, String price) {
        prefEditor.putString(key, price);
        prefEditor.commit();
    }

    public boolean getIsPurchasedAdFree() {
        // In App Purchase
        return Project_CONST_FORCE_DISABLE_ADS;
    }

    public ArrayList<BDG_PlanData> getNewPlanDetails() {
        String json = pref.getString(GET_PLAN_DETAILS_NEW, "");
        ArrayList<BDG_PlanData> plansDataArrayList = new ArrayList<>();

        if (json != null && !json.isEmpty() && !json.equals("null")) {
            plansDataArrayList = getGsonInstance().fromJson(json, new TypeToken<ArrayList<BDG_PlanData>>() {
            }.getType());
        }
        return plansDataArrayList;
    }

    public void setNewPlanDetails(ArrayList<BDG_PlanData> planResponse) {
        String json = getGsonInstance().toJson(planResponse);
        Log.i(TAG, "KeyAppUseDate changed to: " + json);
        prefEditor.putString(GET_PLAN_DETAILS_NEW, json);
        prefEditor.commit();
    }
}