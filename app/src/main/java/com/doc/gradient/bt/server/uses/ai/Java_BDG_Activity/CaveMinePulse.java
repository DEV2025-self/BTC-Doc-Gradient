package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter.CaveMinePulsePremium;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Adapter.MegaCaveMinePulse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.BDG_Utils;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.Interface.BDG_BillingUpdatesListener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_PaymentData.BDG_PaymentModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.BDG_PlanData;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityCaveMinePulseBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CaveMinePulse extends BaseCoreParentJava<ActivityCaveMinePulseBinding> implements BDG_BillingUpdatesListener {
    final String TAG = "CaveMinePulse";
    AutoConnectNetwork autoconnectnetwork = null;
    CaveMinePulsePremium caveMinePulsePremium = null;
    MegaCaveMinePulse megaCaveMinePulse = null;
    BDG_PlanData minePulsePlanItemJson = null;
    Gson gson = null;
    ArrayList<BDG_PlanData> minePlanList = new ArrayList<>();
    ArrayList<BDG_PlanData> mineMegaPlanList = new ArrayList<>();

    DatabaseReference mineDatabaseReference = null;
    long mineCountSuccess = 0;
    DecimalFormat mineDecimalFormat = null;
    String mineAppName = "BTC Mining";
    ArrayList<String> mineProductIds = new ArrayList<>();
    ArrayList<BDG_PlanData> minePlanDetailsList = new ArrayList<>();
    BDG_PaymentModel minePaymentData = null;


    @Override
    protected ActivityCaveMinePulseBinding DataBridge() {
        return ActivityCaveMinePulseBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {
        autoconnectnetwork = new AutoConnectNetwork(this);
        autoconnectnetwork.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasConnection) {
                if (Boolean.FALSE.equals(hasConnection)) {
                    noDataDialog();
                } else {
                    hideDataDialog();
                }
            }
        });
    }

    @Override
    protected void RecallBindingProcess() {
        AppStatusBarjava(this);

        allbindData.privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaveMinePulse.this, SafeDataInfo.class);
                intent.putExtra("Flag", "Privacy");
                startActivity(intent);
            }
        });

        allbindData.termsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaveMinePulse.this, SafeDataInfo.class);
                intent.putExtra("Flag", "Condition");
                startActivity(intent);
            }
        });

        allbindData.btnbackbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gson = new Gson();
        mineDecimalFormat = new DecimalFormat();
        mineDecimalFormat.setMaximumFractionDigits(2);
        mineAppName = getString(R.string.app_name);

        mineDatabaseReference = FirebaseDatabase.getInstance().getReference("paymentsData_success_INAPP");
        mineDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i(TAG, "onDataChange: " + snapshot.getChildrenCount());
                mineCountSuccess = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "onCancelled: " + error);
            }
        });

        minePaymentData = new BDG_PaymentModel();
        prepareInApp();
        initAdapter();
    }

    @Override
    protected void ModelSyncObserver() {

    }

    @Override
    protected void viewAds() {

    }


    private void initAdapter() {
        minePlanList.clear();
        mineMegaPlanList.clear();

        for (BDG_PlanData plan : minePlanDetailsList) {
            if (plan.getPlanType() == 0 && plan.getActive() == 1) {
                minePlanList.add(plan);
            } else if (plan.getPlanType() == 1 && plan.getActive() == 1) {
                mineMegaPlanList.add(plan);
            }
        }

        if (minePlanList.isEmpty()) {
            allbindData.layBooster.setVisibility(View.GONE);
        } else {
            allbindData.layBooster.setVisibility(View.VISIBLE);
            caveMinePulsePremium = new CaveMinePulsePremium(
                    this,
                    minePlanList,
                    new CaveMinePulsePremium.OnTextureForIndexChangeListener() {
                        @Override
                        public void onUserPlanSelect(int position, BDG_PlanData planItem) {
                            if (planItem != null) {
                                minePulsePlanItemJson = planItem;
                                goToNextActivity();
                            }
                        }
                    });
            allbindData.recyclerViewPlanItem.setAdapter(caveMinePulsePremium);
        }

        if (mineMegaPlanList.isEmpty()) {
            allbindData.offerboosterlay.setVisibility(View.GONE);
        } else {
            allbindData.offerboosterlay.setVisibility(View.VISIBLE);
            megaCaveMinePulse = new MegaCaveMinePulse(
                    this,
                    mineMegaPlanList,
                    new MegaCaveMinePulse.OnTextureForIndexChangeListener() {
                        @Override
                        public void onUserPlanSelect(int position, BDG_PlanData planItem) {
                            if (planItem != null) {
                                minePulsePlanItemJson = planItem;
                                goToNextActivity();
                            }
                        }
                    });
            allbindData.PlanMegaBoosterbdg.setAdapter(megaCaveMinePulse);
        }
    }

    private void goToNextActivity() {
        if (minePulsePlanItemJson != null) {
            Intent intent = new Intent(this, CaveMinePulsePurchase.class);
            String response = gson.toJson(minePulsePlanItemJson, BDG_PlanData.class);
            intent.putExtra("planItem", response);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void prepareInApp() {
        Log.i(TAG, "prepareInApp: ");
        if (!SessionaryJava.getSessionaryinstance().getAllPlan().isEmpty()) {
            minePlanDetailsList.clear();
            mineProductIds.clear();
            for (int i = 0; i < SessionaryJava.getSessionaryinstance().getAllPlan().size(); i++) {
                BDG_PlanData planItem = SessionaryJava.getSessionaryinstance().getAllPlan().get(i);
                if (planItem != null && Integer.parseInt(planItem.getCategory()) == ProjectConstantsJava.Project_INA_PURCHASE_CATEGORY_ID && planItem.getActive() == 1) {
                    minePlanDetailsList.add(planItem);
                    mineProductIds.add(String.valueOf(planItem.getPlanId()));
                }
            }

            if (!minePlanDetailsList.isEmpty()) {
                for (BDG_PlanData planData : minePlanDetailsList) {
                    planData.setPriceInApp(String.valueOf(planData.getPrice()));
                    planData.setPriceInAppInNumber(String.valueOf(planData.getPrice()));
                }
            }
        }

        if (!mineProductIds.isEmpty()) {
            Log.i(TAG, "onCreate: " + mineProductIds);
            BDG_Utils.getUtilsInstance()
                    .setAppName(mineAppName)
                    .prepareProductIdList(mineProductIds)
                    .setBillingUpdatesListener(this, this);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateUserSubsPriceByCurrency(ArrayList<List<ProductDetails>> productDetailsList) {
        Log.i(TAG, "updateSubsPriceByCurrency: ");

        if (productDetailsList != null && productDetailsList.size() == mineProductIds.size()) {
            Log.i(TAG, "ShowProductList: " + productDetailsList);
            for (int i = 0; i < productDetailsList.size(); i++) {
                if (!productDetailsList.get(i).isEmpty()) {
                    for (int j = 0; j < minePlanDetailsList.size(); j++) {
                        if (minePlanDetailsList.get(j).getPlanId().equals(productDetailsList.get(i).get(0).getProductId())) {
                            String originalPrice = productDetailsList.get(i).get(0)
                                    .getSubscriptionOfferDetails().get(0)
                                    .getPricingPhases().getPricingPhaseList()
                                    .get(productDetailsList.get(i).get(0).getSubscriptionOfferDetails().get(0)
                                            .getPricingPhases().getPricingPhaseList().size() - 1)
                                    .getFormattedPrice();
                            minePlanDetailsList.get(j).setPriceInApp(originalPrice.replace(".00", ""));
                            break;
                        }
                    }
                }
            }

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (caveMinePulsePremium != null) {
                        caveMinePulsePremium.notifyDataSetChanged();
                    }
                    if (megaCaveMinePulse != null) {
                        megaCaveMinePulse.notifyDataSetChanged();
                    }
                }
            }, 200);
        }
    }

    @Override
    public void BDG_onBillingClientRetryFailed(int retryComeFrom) {
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

    @Override
    public void BDG_onQueryPurchasesFailed(String message) {
        bothsnackBars(allbindData.getRoot(), message, 1);
    }

    @Override
    public void BDG_onSuccessesPurchasesResponse(Purchase purchase, int planId) {
    }

    @Override
    public void BDG_onPurchaseFlowLaunchingFailed(String message) {
    }

    @Override
    public void BDG_onConsumeFinished(String token, int result) {
    }

    @Override
    public void BDG_onConsumeFailed(String message) {
    }

    @Override
    public void BDG_onProductDetailsResponse(ArrayList<List<ProductDetails>> productDetailsList) {
        Log.i(TAG, "onProductDetailsResponse: >>> productDetailsList " + productDetailsList);
        if (productDetailsList != null) {
            for (int i = 0; i < productDetailsList.size(); i++) {
                updateUserSubsPriceByCurrency(productDetailsList);
            }
        }
    }

    @Override
    public void BDG_onProductDetailsFailed(String message) {
        bothsnackBars(allbindData.getRoot(), message, 1);
    }

    @Override
    public void BDG_onMiningPriceChangeConfirmationResult(BillingResult billingResult, SkuDetails skuDetails) {
    }

    @Override
    public void BDG_onMiningPriceChangeConfirmationFailed(String message) {
    }
}
