package com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class BDG_PurchaseBillingClient {
    private static final String TAG = "BDG_PurchaseBillingClient";
    private static final long RECONNECT_TIMER_START_MILLISECONDS = 1000L;
    private static final long RECONNECT_TIMER_MAX_TIME_MILLISECONDS = 1000L * 4L; // 5 sec
    @SuppressLint("StaticFieldLeak")
    private static BDG_PurchaseBillingClient sInstance;
    private final Handler handler = new Handler();
    private final ArrayList<List<ProductDetails>> mSkuDetailsLists = new ArrayList<>();
    private final List<Purchase> subsPurchaseResultList = new ArrayList<>();
    private final List<Purchase> finalePurchaseList = new ArrayList<>();
    private boolean RETRY_QUERY_INVENTORY = false;
    private boolean RETRY_QUERY_PURCHASE = false;
    private Context context;
    private Gson gson;
    private boolean isProcessRunning = false;
    private BillingClient billingClient;
    private int counter = 0;
    private int acknowledge_purchase_counter = 0;
    private int current_count = 0;
    private List<Purchase> acknowledgePurchaseList;
    private BillingResult subsBillingResult;
    private long reconnectMilliseconds = RECONNECT_TIMER_START_MILLISECONDS;

    public static BDG_PurchaseBillingClient getInstance() {
        if (sInstance == null) {
            Log.i(TAG, " getInstance : BillingManager.class ");
            synchronized (BDG_PurchaseBillingClient.class) {
                if (sInstance == null) {
                    Log.i(TAG, " getInstance : Getting NULL create New");
                    sInstance = new BDG_PurchaseBillingClient();
                }
            }
        }
        return sInstance;
    }

    public boolean isBillingClientConnected() {
        return billingClient != null && billingClient.getConnectionState() == BillingClient.ConnectionState.CONNECTED;
    }

    public int getBillingClientConnectionState() {
        return billingClient != null ? billingClient.getConnectionState() : -1;
    }

    private boolean isBillingClientReadyForReConnect() {
        if (billingClient == null) {
            return true;
        } else if (getBillingClientConnectionState() == BillingClient.ConnectionState.DISCONNECTED) {
            return true;
        } else if (getBillingClientConnectionState() == BillingClient.ConnectionState.CLOSED) {
            destroyBillingClient();
            return true;
        }
        return false;
    }

    public boolean areSubscriptionsSupported() {
        if (!isBillingClientConnected()) {
            retryBillingServiceConnectionWithExponentialBackoff();
            return false;
        }
        int responseCode = billingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS).getResponseCode();
        return responseCode == BillingClient.BillingResponseCode.OK;
    }

    private Gson getGsonInstance() {
        if (gson == null) {
            gson = new GsonBuilder().serializeSpecialFloatingPointValues().create();
        }
        return gson;
    }

    public void destroyBillingClient() {
        Log.i(TAG, " destroyBillingClient : ");
        if (billingClient != null && billingClient.isReady()) {
            billingClient.endConnection();
            billingClient = null;
        }
    }

    public void initBillingManager(Context context) {
        Log.i(TAG, " initBillingManager : ");
        this.context = context;
    }

    public void queryInventory() {
        Log.i(TAG, "queryInventory: ProductIdList" + BDG_Utils.getUtilsInstance().getProductIds());
        queryProductDetailsAsync();
    }

    private void queryProductDetailsAsync() {
        isProcessRunning = true;
        Log.i(TAG, " >>> querySkuDetailsAsync <<< : productIdList-> " + BDG_Utils.getUtilsInstance().getProductIds());
        if (!isBillingClientConnected()) {
            RETRY_QUERY_INVENTORY = true;
            retryBillingServiceConnectionWithExponentialBackoff();
            return;
        }
        if (BDG_Utils.getUtilsInstance().getProductIds() == null || BDG_Utils.getUtilsInstance().getProductIds().isEmpty()) {
            isProcessRunning = false;
            if (BDG_Utils.getmBillingUpdatesListener() != null) {
                onProductDetailsFailed(null, "ProductIdList getting null or empty.");
            }
            return;
        }
        Log.i(TAG, "querySkuDetailsAsync: skuList.size" + BDG_Utils.getUtilsInstance().getProductIds().size());
        mSkuDetailsLists.clear();
        getProductData(BDG_Utils.getUtilsInstance().getProductIds().size());
    }

    private void getProductData(int c) {
        Log.i(TAG, "getproductdata: " + c);
        AtomicInteger count = new AtomicInteger(c);

        if (count.get() > 0) {
            List<QueryProductDetailsParams.Product> productList = new ArrayList<>();
            productList.add(
                    QueryProductDetailsParams.Product.newBuilder()
                            .setProductId(BDG_Utils.getUtilsInstance().getProductIds().get(BDG_Utils.getUtilsInstance().getProductIds().size() - count.get()))
                            .setProductType(BDG_Utils.PURCHASE_TYPE_SUB)
                            .build()
            );

            QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                    .setProductList(productList)
                    .build();

            billingClient.queryProductDetailsAsync(params, (billingResult, prodDetailsList) -> {
                Log.i(TAG, "getProductData: billingResult prodDetailsList" + billingResult + "  " + prodDetailsList);
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    count.getAndDecrement();
                    if (prodDetailsList != null && !prodDetailsList.isEmpty()) {
                        mSkuDetailsLists.add(prodDetailsList);
                    }
                    Log.i(TAG, "queryProductDetailsAsync:mSkuDetailsLists.size==productIdList.size " + mSkuDetailsLists.size() + "  " + BDG_Utils.getUtilsInstance().getProductIds().size());

                    if (BDG_Utils.getmBillingUpdatesListener() != null && mSkuDetailsLists.size() == BDG_Utils.getUtilsInstance().getProductIds().size()) {
                        isProcessRunning = false;
                        ArrayList<List<ProductDetails>> duplicateSkuDetailsList = new ArrayList<>(mSkuDetailsLists);
                        BDG_Utils.getmBillingUpdatesListener().BDG_onProductDetailsResponse(duplicateSkuDetailsList);
                        onProductDetailsResponse(duplicateSkuDetailsList);
                        mSkuDetailsLists.clear();
                    }
                    getProductData(count.get());
                } else {
                    count.getAndDecrement();
                    if (BDG_Utils.getmBillingUpdatesListener() != null) {
                        isProcessRunning = false;
                        Log.i(TAG, " >>> onSkuDetailsResponse <<< : onSkuDetailsFailed -> ");
                        onProductDetailsFailed(billingResult, billingResult.getDebugMessage());
                    }
                    getProductData(count.get());
                }
            });
        }
    }

    private void retryBillingServiceConnectionWithExponentialBackoff() {
        if (reconnectMilliseconds <= RECONNECT_TIMER_MAX_TIME_MILLISECONDS) {
            if (isBillingClientReadyForReConnect()) {
                handler.postDelayed(() -> {
                    if (BDG_Utils.isValidContext(BDG_Utils.getActivity())) {
                        new Handler().post(() -> {
                            Log.i(TAG, " >>> run <<< :  -> ");
                            establishConnection();
                        });
                    }
                }, reconnectMilliseconds);
                Log.i(TAG, " >>> retryBillingServiceConnectionWithExponentialBackoff <<< : reconnectMilliseconds : -> " + reconnectMilliseconds);
                reconnectMilliseconds *= 2;
            }
        } else {
            Log.i(TAG, " >>> retryBillingServiceConnectionWithExponentialBackoff <<< : isOneTimeServiceRetry -> ");
            isProcessRunning = false;
            if (BDG_Utils.getmBillingUpdatesListener() != null) {
                if (RETRY_QUERY_INVENTORY) {
                    onBillingClientRetryFailed(0);
                } else if (RETRY_QUERY_PURCHASE) {
                    onBillingClientRetryFailed(1);
                } else {
                    onBillingClientRetryFailed(2);
                }
            }
        }
    }

    public void establishConnection() {
        Log.i(TAG, " getInstanceOfBillingClient : ");
        Log.i(TAG, "Starting setup.");
        isProcessRunning = true;
        if (billingClient == null && BDG_Utils.isValidContext(context)) {
            Log.i(TAG, " getInstanceOfBillingClient : Billing Client Init");
            billingClient = BillingClient.newBuilder(context)
                    .enablePendingPurchases()
                    .setListener((billingResult, purchases) -> {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
                            isProcessRunning = false;
                            if (finalePurchaseList != null) {
                                finalePurchaseList.clear();
                                finalePurchaseList.addAll(purchases);
                            }
                            prepareAcknowledgePurchase();
                        } else {
                            isProcessRunning = false;
                            if (BDG_Utils.getmBillingUpdatesListener() != null) {
                                onQueryPurchasesFailed(billingResult.getResponseCode(), getResponseDesc(billingResult.getResponseCode()), 0);
                            } else {
                                Log.i(TAG, " >>> onPurchasesUpdated <<< : mBillingUpdatesListener Getting null -> ");
                            }
                        }
                    })
                    .build();
        }

        Log.i(TAG, " getInstanceOfBillingClient : Client not NULL");
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                Log.e(TAG, "onBillingServiceDisconnected()");
                retryBillingServiceConnectionWithExponentialBackoff();
            }

            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                Log.i(TAG, " onBillingSetupFinished : ");
                int responseCode = billingResult.getResponseCode();
                String debugMessage = billingResult.getDebugMessage();
                Log.d(TAG, "onBillingSetupFinished: " + responseCode + " " + debugMessage);
                if (responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.i(TAG, " onBillingSetupFinished : BillingClient.BillingResponseCode.OK");
                    reconnectMilliseconds = RECONNECT_TIMER_START_MILLISECONDS;
                    if (BDG_Utils.getmBillingUpdatesListener() != null) {
                        Log.i(TAG, " onBillingSetupFinished : onBillingClientSetupFinished CALLED");
                        BDG_Utils.getmBillingUpdatesListener().BDG_onBillingClientSetupFinished();
                    }
                    if (RETRY_QUERY_INVENTORY) {
                        RETRY_QUERY_INVENTORY = false;
                        if (BDG_Utils.getUtilsInstance().getProductIds() != null) {
                            queryInventory();
                        }
                    } else if (RETRY_QUERY_PURCHASE) {
                        RETRY_QUERY_PURCHASE = false;
                        subsQueryPurchases();
                    } else {
                        isProcessRunning = false;
                    }
                } else {
                    retryBillingServiceConnectionWithExponentialBackoff();
                }
            }
        });
    }

    public void subsQueryPurchases() {
        isProcessRunning = true;
        Log.i(TAG, " >>> subsQueryPurchases <<< :  -> ");
        if (!isBillingClientConnected()) {
            RETRY_QUERY_PURCHASE = true;
            retryBillingServiceConnectionWithExponentialBackoff();
            return;
        }

        if (areSubscriptionsSupported()) {
            Log.i(TAG, " >>> subsQueryPurchases <<< : areSubscriptionsSupported YES -> ");
            billingClient.queryPurchasesAsync(
                    QueryPurchasesParams.newBuilder().setProductType(BDG_Utils.PURCHASE_TYPE_SUB).build(),
                    (billingResult, purchases) -> {
                        Log.i(TAG, "Querying subscriptions result code: " + billingResult.getResponseCode());
                        Log.i(TAG, "subsQueryPurchases: purchases" + purchases);
                        subsBillingResult = billingResult;
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            if (subsPurchaseResultList != null && purchases != null && !purchases.isEmpty()) {
                                Log.i(TAG, " onQueryPurchasesResponse : purchases SIZE : " + purchases.size());
                                Log.i(TAG, " onQueryPurchasesResponse : purchases toString : " + purchases);
                                subsPurchaseResultList.addAll(purchases);
                            }
                        }
                        prepareQueryPurchaseResponse(subsBillingResult, subsPurchaseResultList);
                    }
            );
        }
    }

    private void prepareQueryPurchaseResponse(BillingResult billingResultSubs, List<Purchase> subsPurchaseResultList) {
        Log.i(TAG, "prepareQueryPurchaseResponse: subsPurchaseResultList" + subsPurchaseResultList.size());
        Log.i(TAG, "prepareQueryPurchaseResponse: subsPurchaseResultList" + subsPurchaseResultList);

        if (billingResultSubs != null && billingResultSubs.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            Log.i(TAG, " prepareQueryPurchaseResponse : Only Subs PurchaseResult = OK");
            finalePurchaseList.clear();
            finalePurchaseList.addAll(subsPurchaseResultList);
            prepareAcknowledgePurchase();
        } else {
            Log.i(TAG, " prepareQueryPurchaseResponse :  PurchaseResult Not OK  Found Result ");
            isProcessRunning = false;
            if (BDG_Utils.getmBillingUpdatesListener() != null) {
                if (billingResultSubs != null) {
                    onQueryPurchasesFailed(billingResultSubs.getResponseCode(), getResponseDesc(billingResultSubs.getResponseCode()), 1);
                } else {
                    onQueryPurchasesFailed(-100, " result getting null !!", 1);
                    Log.i(TAG, " >>> prepareQueryPurchaseResponse <<< : listener getting bull -> ");
                }
            }
        }
    }

    private void prepareAcknowledgePurchase() {
        Log.i(TAG, " >>> prepareAcknowledgePurchase <<< :  -> ");
        if (acknowledgePurchaseList == null) {
            acknowledgePurchaseList = new ArrayList<>();
        }
        acknowledgePurchaseList.clear();
        counter = 0;
        acknowledge_purchase_counter = 0;
        current_count = 0;
        if (finalePurchaseList != null && !finalePurchaseList.isEmpty()) {
            Log.i(TAG, "prepareAcknowledgePurchase() => " + finalePurchaseList.size());
            for (int i = 0; i < finalePurchaseList.size(); i++) {
                if (finalePurchaseList.get(i).getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                    if (!finalePurchaseList.get(i).isAcknowledged()) {
                        Log.i(TAG, " >>> prepareAcknowledgePurchase <<< : purchase.isAcknowledged()  -> " + finalePurchaseList.get(i).isAcknowledged());
                        acknowledgePurchaseList.add(finalePurchaseList.get(i));
                    }
                }
            }
            if (!acknowledgePurchaseList.isEmpty()) {
                counter = acknowledgePurchaseList.size();
            }
            if (counter > 0) {
                startAcknowledgement();
            } else {
                isProcessRunning = false;
                if (BDG_Utils.getmBillingUpdatesListener() != null) {
                    List<Purchase> duplicateFinalePurchaseList = new ArrayList<>(finalePurchaseList);
                    updatePurchaseStatus(duplicateFinalePurchaseList);
                }
            }
        } else {
            Log.i(TAG, " prepareAcknowledgePurchase : Purchase List getting NULL");
            isProcessRunning = false;
            if (BDG_Utils.getmBillingUpdatesListener() != null) {
                List<Purchase> duplicateFinalePurchaseList = new ArrayList<>(finalePurchaseList);
                Log.e(TAG, "prepareAcknowledgePurchase: " + finalePurchaseList);
                updatePurchaseStatus(duplicateFinalePurchaseList);
            }
        }
    }

    private void startAcknowledgement() {
        Log.i(TAG, " >>> startAcknowledgement <<< :  : -> ");
        if (acknowledgePurchaseList != null && current_count < acknowledgePurchaseList.size()) {
            Purchase purchase = acknowledgePurchaseList.get(current_count);
            if (isBillingClientConnected()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

                if (billingClient != null) {
                    billingClient.acknowledgePurchase(acknowledgePurchaseParams, billingResult -> {
                        Log.i(TAG, "onAcknowledgePurchaseResponse getDebugMessage: " + billingResult.getDebugMessage());
                        Log.i(TAG, "onAcknowledgePurchaseResponse getResponseCode: " + billingResult.getResponseCode());
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            Log.i(TAG, "onAcknowledgePurchaseResponse: SuccessFully Acknowledged");
                            updateAcknowledgePurchaseCounter(true);
                        } else {
                            updateAcknowledgePurchaseCounter(false);
                            String title = "InAppBilling";
                            String function = "handlePurchase()";
                            String callback = "onAcknowledgePurchaseResponse";
                            String error = getResponseDesc(billingResult.getResponseCode());
                            int error_code = billingResult.getResponseCode();
                            String app_name = BDG_Utils.getAppname();
                            String message = "This error will come when Acknowledge Purchase is fail.";
                            String exception_msg = BDG_Utils.throwFatalWithParams(title, function, callback, error, error_code, app_name, message);
                        }
                    });
                }
            } else {
                updateAcknowledgePurchaseCounter(false);
                Log.e(TAG, "handlePurchase mBillingManager: BillingSetupInProgress");
            }
        }
    }

    private void updateAcknowledgePurchaseCounter(boolean successfullyAcknowledgePurchase) {
        Log.i(TAG, " >>> updateAcknowledgePurchaseCounter <<< : successfullyAcknowledgePurchase : -> " + successfullyAcknowledgePurchase);
        if (successfullyAcknowledgePurchase) {
            acknowledge_purchase_counter++;
        }
        if (current_count >= counter - 1) {
            isProcessRunning = false;
            if (BDG_Utils.getmBillingUpdatesListener() != null) {
                Log.i(TAG, " >>> updateAcknowledgePurchaseCounter <<< : onQueryPurchasesResponse -> ");
                List<Purchase> duplicateFinalePurchaseList = new ArrayList<>(acknowledgePurchaseList);
                updatePurchaseStatus(duplicateFinalePurchaseList);
            }
            if (counter == acknowledge_purchase_counter) {
                Log.i(TAG, "All Purchase are acknowledge successfully.");
            } else {
                Log.i(TAG, (counter - acknowledge_purchase_counter) + " Purchase acknowledge Failed.");
            }
        } else {
            current_count++;
            startAcknowledgement();
        }
    }

    public String getResponseDesc(int code) {
        Log.e(TAG, "getResponseDesc() : " + code);
        switch (code) {
            case BillingClient.BillingResponseCode.BILLING_UNAVAILABLE:
                return "Billing API version is not supported for the type requested.";
            case BillingClient.BillingResponseCode.DEVELOPER_ERROR:
                return "Invalid arguments provided to the API.";
            case BillingClient.BillingResponseCode.ERROR:
                return "Fatal error during the API action.";
            case BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED:
                return "Requested feature is not supported by Play Store on the current device.";
            case BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED:
                return "Failure to purchase since item is already owned.";
            case BillingClient.BillingResponseCode.ITEM_NOT_OWNED:
                return "Failure to consume since item is not owned.";
            case BillingClient.BillingResponseCode.ITEM_UNAVAILABLE:
                return "Requested product is not available for purchase.";
            case BillingClient.BillingResponseCode.OK:
                return "Success.";
            case BillingClient.BillingResponseCode.SERVICE_DISCONNECTED:
                return "Play Store service is not connected now-potentially transient state.";
            case BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE:
                return "Network connection is down.";
            case BillingClient.BillingResponseCode.USER_CANCELED:
                return "User pressed back or canceled a dialog.";
            default:
                return "Unknown error";
        }
    }

    public void initiatePurchaseFlow(Activity mActivity, String itemType, String skuId, ProductDetails productDetails, String oldProductId, int prorationType, String purchaseToken) {
        isProcessRunning = true;
        if (!isBillingClientConnected()) {
            retryBillingServiceConnectionWithExponentialBackoff();
            return;
        }
        if (!getInstance().areSubscriptionsSupported()) {
            isProcessRunning = false;
            if (BDG_Utils.getmBillingUpdatesListener() != null) {
                BDG_Utils.getmBillingUpdatesListener().BDG_onPurchaseFlowLaunchingFailed("Subscriptions are not supported on your device yet. Sorry!");
            }
            return;
        }
        Log.i(TAG, "Launching in-app purchase flow. Replace old SKU? " + (oldProductId != null && !oldProductId.isEmpty()));

        if (productDetails != null) {
            String offerToken = productDetails.getSubscriptionOfferDetails().get(0).getOfferToken();
            BillingFlowParams billingFlowParams;
            if (oldProductId != null && !oldProductId.isEmpty() && !oldProductId.equals(skuId) && prorationType != -1) {
                billingFlowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(
                                List.of(
                                        BillingFlowParams.ProductDetailsParams.newBuilder()
                                                .setProductDetails(productDetails)
                                                .setOfferToken(offerToken)
                                                .build()
                                )
                        )
                        .setSubscriptionUpdateParams(
                                BillingFlowParams.SubscriptionUpdateParams.newBuilder()
                                        .setOldPurchaseToken(purchaseToken)
                                        .setSubscriptionReplacementMode(prorationType)
                                        .build()
                        )
                        .build();
            } else {
                Log.i(TAG, "initiatePurchaseFlow: " + productDetails.getSubscriptionOfferDetails().get(0).getOfferToken());
                List<BillingFlowParams.ProductDetailsParams> productDetailsParamsList = List.of(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                                .setProductDetails(productDetails)
                                .setOfferToken(offerToken)
                                .build()
                );
                billingFlowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(productDetailsParamsList)
                        .build();
            }
            if (BDG_Utils.isValidContext(mActivity) && mActivity != null) {
                Log.i(TAG, "initiatePurchaseFlow : launchBillingFlow: ");
                billingClient.launchBillingFlow(mActivity, billingFlowParams);
            } else {
                isProcessRunning = false;
            }
        } else {
            isProcessRunning = false;
            if (BDG_Utils.getmBillingUpdatesListener() != null) {
                Log.e(TAG, " >>> initiatePurchaseFlow <<< :  : -> skuDetails getting null. ");
                BDG_Utils.getmBillingUpdatesListener().BDG_onPurchaseFlowLaunchingFailed("Please try again......");
            }
        }
    }

    public void openBrowser(Uri uriOfSocialMedia) {
        if (BDG_Utils.isValidContext(BDG_Utils.getActivity())) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uriOfSocialMedia);
            @SuppressLint("WrongConstant")
            android.content.pm.ActivityInfo activityInfo = browserIntent.resolveActivityInfo(
                    BDG_Utils.getActivity().getPackageManager(), browserIntent.getFlags()
            );
            if (activityInfo != null && activityInfo.exported) {
                BDG_Utils.getActivity().startActivity(browserIntent);
            } else {
                Toast.makeText(BDG_Utils.getActivity(), "err_no_app_found", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onQueryPurchasesFailed(int responseCode, String message, int callBackFrom) {
        String title = "InAppBilling";
        String function = "";
        String callback = "";
        String error = "";
        switch (callBackFrom) {
            case 0:
                function = "establishConnection ";
                callback = "onPurchasesUpdated()";
                error = "Error occurred while BillingClient establish OR Purchase flow lunch failed.";
                break;
            case 1:
                function = "queryPurchasesAsync ";
                callback = "onQueryPurchasesResponse()";
                error = "Error occurred while QueryPurchase failed.";
                break;
        }
        String exception_msg = BDG_Utils.throwFatalWithParams(title, function, callback, error, responseCode, BDG_Utils.getAppname(), message);
        switch (responseCode) {
            case BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED:
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : message : -> " + message);
                BDG_Utils.getEditor().putBoolean(BDG_Utils.IS_PURCHASED_AD_FREE, true);
                BDG_Utils.getEditor().commit();
                break;
            case BillingClient.BillingResponseCode.BILLING_UNAVAILABLE:
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : message : -> " + message);
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : exception_msg : -> " + exception_msg);
                setPurchaseStateAfterBillingError();
                break;
            case BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED:
            case BillingClient.BillingResponseCode.ITEM_UNAVAILABLE:
            case BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE:
            case BillingClient.BillingResponseCode.ITEM_NOT_OWNED:
            case BillingClient.BillingResponseCode.SERVICE_DISCONNECTED:
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : message : -> " + message);
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : exception_msg : -> " + exception_msg);
                BDG_Utils.getmBillingUpdatesListener().BDG_onQueryPurchasesFailed(getResponseDesc(responseCode));
                break;
            case BillingClient.BillingResponseCode.USER_CANCELED:
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : message : -> " + message);
                break;
            case BillingClient.BillingResponseCode.ERROR:
            case BillingClient.BillingResponseCode.DEVELOPER_ERROR:
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : message : -> " + message);
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : exception_msg : -> " + exception_msg);
                break;
            default:
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : message : -> " + message);
                Log.i(TAG, " >>> onQueryPurchasesFailed <<< : exception_msg : -> " + exception_msg);
                break;
        }
    }

    public void onBillingClientRetryFailed(int retryComeFrom) {
        if (BDG_Utils.getmBillingUpdatesListener() != null) {
            BDG_Utils.getmBillingUpdatesListener().BDG_onBillingClientRetryFailed(retryComeFrom);
        }
        switch (retryComeFrom) {
            case 0:
                Log.i(TAG, " >>> retryComeFrom <<< : QUERY_INVENTORY -> ");
                break;
            case 1:
                Log.i(TAG, " >>> retryComeFrom <<< : QUERY_PURCHASE -> ");
                break;
            case 2:
                Log.i(TAG, "<<< retryComeFrom >>>: NONE ");
                break;
        }
    }

    private void updatePurchaseStatus(List<Purchase> purchaseList) {
        Log.i(TAG, " >>> updatePurchaseStatus <<< :  -> ");
        if (purchaseList != null && !purchaseList.isEmpty()) {
            Log.i(TAG, "updatePurchaseStatus() => " + purchaseList.size());
            boolean ifUserHasAtLeastOnePurchase = false;
            for (int i = 0; i < purchaseList.size(); i++) {
                Purchase purchase = purchaseList.get(i);
                if (purchase != null) {
                    if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                        Log.i(TAG, " >>> updatePurchaseStatus <<< : Purchase.PurchaseState.PURCHASED -> ");
                        ifUserHasAtLeastOnePurchase = true;
                        if (purchase.getSkus() != null && !purchase.getSkus().isEmpty()) {
                            List<String> purchaseSkus = purchase.getSkus();
                            Log.i(TAG, " updatePurchaseStatus : purchaseSkus: " + purchaseSkus);
                            for (String purchaseSku : purchaseSkus) {
                                if (purchaseSku != null && !purchaseSku.isEmpty()) {
                                    for (int j = 0; j < BDG_Utils.getUtilsInstance().getProductIds().size(); j++) {
                                        if (BDG_Utils.getUtilsInstance().getProductIds().get(j).equals(purchaseSku)) {
                                            Log.i(TAG, "updatePurchaseStatus: " + "User has purchased BASIC_PLAN subs product.");
                                            successfullyPurchase(purchase, false);
                                            BDG_Utils.getmBillingUpdatesListener().BDG_onSuccessesPurchasesResponse(purchase, j);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
                        // Handle pending purchase
                    } else if (purchase.getPurchaseState() == Purchase.PurchaseState.UNSPECIFIED_STATE) {
                        Log.i(TAG, "onPurchasesUpdated: UNSPECIFIED_STATE");
                    }
                }
            }
            if (!ifUserHasAtLeastOnePurchase) {
                Log.i(TAG, "onPurchasesUpdated > ifUserHasAtLeastOnePurchase = FALSE > userHasNotPurchase");
                userHasNotPurchase();
            }
        } else {
            Log.i(TAG, "onPurchasesUpdated > userHasNotPurchase");
            userHasNotPurchase();
        }
    }

    private void successfullyPurchase(Purchase purchase, boolean isInApp) {
        Log.e(TAG, "*************** User Purchase successful  *****************");
        if (purchase != null && purchase.getOriginalJson() != null && !purchase.getOriginalJson().isEmpty()) {
            Log.i(TAG, "successfullyPurchase : Original JSON:" + purchase.getOriginalJson());
        }
        BDG_Utils.getEditor().putString(BDG_Utils.KEY_PURCHASED_DETAIL, getGsonInstance().toJson(purchase, Purchase.class));
        BDG_Utils.getEditor().commit();
        Log.i(TAG, "successfullyPurchase: " + BDG_Utils.getSharedPreferences().getString(BDG_Utils.KEY_PURCHASED_DETAIL, ""));
        BDG_Utils.getEditor().putBoolean(BDG_Utils.IS_PURCHASED_AD_FREE, true);
        BDG_Utils.getEditor().commit();
    }

    private void userHasNotPurchase() {
        Log.e(TAG, "*************** User has not Purchase version *****************");
        BDG_Utils.getEditor().putString(BDG_Utils.KEY_PURCHASED_DETAIL, "");
        BDG_Utils.getEditor().commit();
        BDG_Utils.getEditor().putBoolean(BDG_Utils.IS_PURCHASED_AD_FREE, false);
        BDG_Utils.getEditor().commit();
    }

    public void onProductDetailsResponse(ArrayList<List<ProductDetails>> productDetailsList) {
        if (productDetailsList != null && !productDetailsList.isEmpty() && BDG_Utils.getUtilsInstance().getProductIds().size() == productDetailsList.size()) {
            Log.i(TAG, "onSkuDetailsResponse productDetailsList: " + productDetailsList);
            Log.i(TAG, "onSkuDetailsResponse productDetailsList: " + productDetailsList.size());
            for (List<ProductDetails> productDetails : productDetailsList) {
                Log.i(TAG, "onSkuDetailsResponse:productDetails  " + productDetails.get(0).getProductId());
                String sku = productDetails.get(0).getProductId();
                for (int i = 0; i < BDG_Utils.getUtilsInstance().getProductIds().size(); i++) {
                    if (BDG_Utils.getUtilsInstance().getProductIds().get(i).equals(sku)) {
                        try {
                            Log.i(TAG, " >>> onSkuDetailsResponse <<< : SkuDetailsPriceDetails  -> ");
                            BDG_Utils.getEditor().putString(BDG_Utils.getUtilsInstance().getProductIds().get(i), productDetails.toString());
                            BDG_Utils.getEditor().commit();
                            Log.i(TAG, "onSkuDetailsResponse: sharedPreferences" + BDG_Utils.getSharedPreferences().getString(BDG_Utils.getUtilsInstance().getProductIds().get(i), ""));
                        } catch (Throwable e) {
                            throwQuerySkuDetailsNonFatal(i, e);
                        }
                    }
                }
            }
            subsQueryPurchases();
        } else {
            Log.e(TAG, "skuDetailsList is null");
        }
    }

    private void throwQuerySkuDetailsNonFatal(int SkuIds, Throwable throwable) {
        String title = "InAppBilling";
        String function = "Query Inventory";
        String callback = "onSkuDetailsResponse()";
        String error = "Query Inventory getting a crash while parsing Json from session.\n --Crash Report: " + throwable.getMessage();
        int error_code = -1;
        String app_name = "In App Demo";
        String message = "Price Details of " + BDG_Utils.getSharedPreferences().getString(BDG_Utils.getUtilsInstance().getProductIds().get(SkuIds), "");
        String exception_msg = BDG_Utils.throwFatalWithParams(title, function, callback, error, error_code, app_name, message);
        Log.i(TAG, "throwQuerySkuDetailsNonFatal: " + exception_msg);
    }

    public void onProductDetailsFailed(BillingResult billingResult, String message) {
        Log.i(TAG, " >>> onSkuDetailsFailed <<< :  -> message : " + message);
        if (billingResult != null) {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE) {
                BDG_Utils.getmBillingUpdatesListener().BDG_onProductDetailsFailed(message);
            }
        } else {
            BDG_Utils.getmBillingUpdatesListener().BDG_onProductDetailsFailed(message);
            Log.i(TAG, " >>> onSkuDetailsFailed <<< : billingResult Getting null  -> message : " + message);
        }
    }

    public void restorePurchases(Context context) {
        Log.i(TAG, "launchBtnRestorePurchaseFlow: yes");
        BillingClient billingClient = BillingClient.newBuilder(context)
                .enablePendingPurchases()
                .setListener((billingResult, list) -> {})
                .build();

        BillingClient finalBillingClient = billingClient;
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {}

            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    finalBillingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BDG_Utils.PURCHASE_TYPE_SUB).build(),
                            (billingResult1, purchases) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    Log.i(TAG, "onBillingSetupFinished: " + purchases);
                                    if (!purchases.isEmpty()) {
                                        BDG_Utils.getEditor().putString(
                                                BDG_Utils.KEY_PURCHASED_DETAIL,
                                                getGsonInstance().toJson(purchases.get(0), Purchase.class)
                                        ).apply();
                                        BDG_Utils.getEditor().putBoolean(BDG_Utils.IS_PURCHASED_AD_FREE, true);
                                        BDG_Utils.getEditor().commit();
                                        BDG_Utils.getmBillingUpdatesListener().BDG_onConsumeFailed("Restore Successfully");
                                    } else {
                                        BDG_Utils.getEditor().putString(BDG_Utils.KEY_PURCHASED_DETAIL, "");
                                        BDG_Utils.getEditor().commit();
                                        BDG_Utils.getmBillingUpdatesListener().BDG_onConsumeFailed("Not purchase anything");
                                    }
                                }
                            }
                    );
                }
            }
        });
    }

    private void setPurchaseStateAfterBillingError() {
        if (BDG_Utils.getSharedPreferences().getBoolean(BDG_Utils.IS_PURCHASED_AD_FREE, false)) {
            if (BDG_Utils.getSharedPreferences().getString(BDG_Utils.KEY_PURCHASED_DETAIL, "") != null
                    && !BDG_Utils.getSharedPreferences().getString(BDG_Utils.KEY_PURCHASED_DETAIL, "").isEmpty()) {
                Purchase purchase = null;
                try {
                    purchase = getGsonInstance().fromJson(
                            BDG_Utils.getSharedPreferences().getString(BDG_Utils.KEY_PURCHASED_DETAIL, ""),
                            Purchase.class
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String purchase_sku_id = "";
                if (purchase != null) {
                    if (purchase.getSkus() != null && !purchase.getSkus().isEmpty()) {
                        for (String skuID : purchase.getSkus()) {
                            if (skuID != null && !skuID.isEmpty()) {
                                purchase_sku_id = skuID;
                                break;
                            }
                        }
                    }
                }
                long purchaseTime = purchase != null && purchase.getPurchaseTime() != 0L ? purchase.getPurchaseTime() : 0;
                if (purchase_sku_id != null && !purchase_sku_id.isEmpty() && purchaseTime != 0L) {
                    for (int i = 0; i < BDG_Utils.getUtilsInstance().getProductIds().size(); i++) {
                        if (BDG_Utils.getUtilsInstance().getProductIds().get(i).equals(purchase_sku_id)) {
                            Log.i(TAG, "setPurchaseStateAfterBillingError: Purchase BASIC_PLAN ");
                            if (!isValideSubscription(purchase.getPurchaseTime(), purchase_sku_id)) {
                                userHasNotPurchase();
                            }
                        }
                    }
                } else {
                    userHasNotPurchase();
                    Log.i(TAG, "setPurchaseStateAfterBillingError :  purchase ==null || purchase_sku_id==NULL || purchaseTime=0");
                }
            } else {
                userHasNotPurchase();
                Log.i(TAG, "setPurchaseStateAfterBillingError 2 : APP IS NOT PURCHASED !!");
            }
        } else {
            Log.i(TAG, "setPurchaseStateAfterBillingError 1 : APP IS NOT PURCHASED !!");
        }
    }

    private boolean isValideSubscription(Long timeStamp, String sku) {
        if (timeStamp != null && timeStamp != 0L && sku != null && !sku.isEmpty()) {
            Date purchaseDate = new Date(timeStamp);
            Date currentDateTime = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
            Calendar purchaseCalendar = Calendar.getInstance();
            purchaseCalendar.setTime(purchaseDate);
            purchaseCalendar.add(Calendar.DAY_OF_MONTH, 7);
            Date expireDate = purchaseCalendar.getTime();
            Log.i(TAG, " Expire Date :" + format.format(expireDate));
            return !expireDate.before(currentDateTime);
        }
        return false;
    }
}
