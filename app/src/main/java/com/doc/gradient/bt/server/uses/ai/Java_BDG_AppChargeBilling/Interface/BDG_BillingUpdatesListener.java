package com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.Interface;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;

import java.util.ArrayList;
import java.util.List;

public interface BDG_BillingUpdatesListener {
    default void BDG_onBillingClientSetupFinished() {}

    void BDG_onBillingClientRetryFailed(int retryComeFrom);

    void BDG_onQueryPurchasesFailed(String message); // callBackFrom = 0: BillingClient , 1:QueryPurchase

    void BDG_onSuccessesPurchasesResponse(Purchase purchase, int plan_id);

    void BDG_onPurchaseFlowLaunchingFailed(String message);

    void BDG_onConsumeFinished(String token, @BillingClient.BillingResponseCode int result);

    void BDG_onConsumeFailed(String message);

    void BDG_onProductDetailsResponse(ArrayList<List<ProductDetails>> productDetailsList);

    void BDG_onProductDetailsFailed(String message);

    void BDG_onMiningPriceChangeConfirmationResult(BillingResult billingResult, SkuDetails skuDetails);

    void BDG_onMiningPriceChangeConfirmationFailed(String message);
}
