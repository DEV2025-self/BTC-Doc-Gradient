package com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class BDG_LaunchPurchaseFlow {
    private static final String TAG = "launchPurchaseFlow";
    private static Gson gson;
    private static String BDG_SELECTED_PURCHASE_ID = "";

    public static void launchPurchaseFlow(ProductDetails productDetails, Activity activity) {
        // This method is called when a user selects a product for purchase and manages the flow
        // based on whether the user has already purchased any other product or the same product,
        // and also checks if the user is purchasing this product for the first time.

        BDG_SELECTED_PURCHASE_ID = productDetails.getProductId();

        if (BDG_Utils.isValidContext(activity)) {
            Log.i(TAG, "launchSubsPurchaseFlow: ============");

            Log.i(TAG, "purchaseSubs:  ");
            String purchase_Id = getAlreadyPurchasedId();
            String purchaseToken = getAlreadyPurchasedToken();
            Log.i(TAG, " >>> purchaseSubs <<< : purchase_Id -> " + purchase_Id);
            Log.i(TAG, " >>> purchaseSubs <<< : purchaseToken -> " + purchaseToken);
            Log.i(TAG, " >>> purchaseSubs <<< : skuDetails -> " + productDetails);

            if (!purchase_Id.isEmpty() && BDG_SELECTED_PURCHASE_ID.equals(purchase_Id)) {
                // If the selected product is the same as the already purchased product, open manage subscription.
                if (BDG_Utils.isValidContext(activity)) {
                    Log.i(
                            TAG,
                            "lunchSubsPurchaseFlow: *********************** Manage Subscription"
                    );
                    BDG_PurchaseBillingClient.getInstance()
                            .openBrowser(Uri.parse("https://play.google.com/store/account/subscriptions?sku=" + BDG_SELECTED_PURCHASE_ID + "&package=" + activity.getPackageName()));
                }
            } else {
                // If the user selects a product that is not the same as the already purchased product,
                // this method is called for upgrading or downgrading the plan.
                if (!purchase_Id.isEmpty()) {
                    BDG_PurchaseBillingClient.getInstance().initiatePurchaseFlow(
                            activity,
                            productDetails.getProductId(),
                            BillingClient.SkuType.SUBS,
                            productDetails,
                            purchase_Id,
                            BillingFlowParams.SubscriptionUpdateParams.ReplacementMode.CHARGE_FULL_PRICE,
                            purchaseToken
                    );
                } else {
                    // If the user is purchasing any product for the first time, this method is called.
                    BDG_PurchaseBillingClient.getInstance().initiatePurchaseFlow(
                            activity,
                            BillingClient.SkuType.SUBS,
                            productDetails.getProductId(),
                            productDetails,
                            purchase_Id,
                            BillingFlowParams.SubscriptionUpdateParams.ReplacementMode.WITH_TIME_PRORATION,
                            purchaseToken
                    );
                }
            }
        }
    }

    private static String getAlreadyPurchasedId() {
        // This method gets the product purchased ID from the local database.
        // If the ID is empty, it returns an empty String.
        // If not empty, it returns the purchased product ID.
        if (BDG_Utils.sharedPreferences != null && BDG_Utils.sharedPreferences.getBoolean(
                BDG_Utils.IS_PURCHASED_AD_FREE,
                false
        )) {
            String purchasedDetail = BDG_Utils.sharedPreferences.getString(BDG_Utils.KEY_PURCHASED_DETAIL, "");
            if (purchasedDetail != null && !purchasedDetail.isEmpty()) {
                Purchase purchase = null;
                try {
                    purchase = getGsonInstance().fromJson(
                            purchasedDetail,
                            Purchase.class
                    );
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                String purchase_sku_id = "";
                if (purchase != null) {
                    if (purchase.getSkus() != null && purchase.getSkus().size() > 0) {
                        Log.i(
                                TAG,
                                " >>> getAlreadyPurchasedId <<< : purchase.getSkus() -> " + purchase
                        );
                        for (String skuID : purchase.getSkus()) {
                            if (skuID != null && skuID.length() > 0) {
                                purchase_sku_id = skuID;
                                Log.i(
                                        TAG,
                                        " >>> getAlreadyPurchasedId <<< : purchase_sku_id -> " + purchase_sku_id
                                );
                                break;
                            }
                        }
                    }
                }
                return purchase_sku_id;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    private static String getAlreadyPurchasedToken() {
        // This method gets the product purchased Token from the local database.
        // If the token is empty, it returns an empty String.
        // If not empty, it returns the purchased product token.
        Log.i(TAG, "getAlreadyPurchasedToken: ");
        if (BDG_Utils.sharedPreferences != null && BDG_Utils.sharedPreferences.getBoolean(
                BDG_Utils.IS_PURCHASED_AD_FREE,
                false
        )) {
            String purchasedDetail = BDG_Utils.sharedPreferences.getString(BDG_Utils.KEY_PURCHASED_DETAIL, "");
            if (purchasedDetail != null && !purchasedDetail.isEmpty()) {
                Purchase purchase = null;
                try {
                    purchase = getGsonInstance().fromJson(
                            purchasedDetail,
                            Purchase.class
                    );
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                if (purchase != null && purchase.getPurchaseToken() != null && !purchase.getPurchaseToken().isEmpty()) {
                    return purchase.getPurchaseToken();
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    private static Gson getGsonInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}