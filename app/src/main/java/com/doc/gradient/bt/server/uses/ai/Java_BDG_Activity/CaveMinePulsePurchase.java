package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_INA_PURCHASE_CATEGORY_ID;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_USER_KEY;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava.calculateOriginalPrice;

import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.bumptech.glide.Glide;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.BDG_LaunchPurchaseFlow;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.BDG_PurchaseBillingClient;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.BDG_Utils;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.Interface.BDG_BillingUpdatesListener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.UniversalDialogJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Add_Plan.Addplanreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_PaymentData.BDG_PaymentModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.BDG_PlanData;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityCaveMinePulsePurchaseBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CaveMinePulsePurchase extends BaseCoreParentJava<ActivityCaveMinePulsePurchaseBinding> implements BDG_BillingUpdatesListener {
    static final String TAG = "CaveMinePulsePurchase";
    AutoConnectNetwork autoconnectnetwork = null;
    AppStateViewModel minePurchaseViewModel;
    Gson gson;
    BDG_PlanData minePurchasePlanItem;
    int minePurchasepurchase_id = 1;
    String minePurchaseAmount = "";
    DatabaseReference minePurchaseDatabaseReference;
    long minePurchaseCountSuccess = 0;
    BDG_PaymentModel purchasePaymentData;

    boolean minePurchaseDialogShow = false;
    ArrayList<List<ProductDetails>> purchaseProdDetailsLists = new ArrayList<>();
    ArrayList<BDG_PlanData> purchasePlanDetailsList = new ArrayList<>();
    ArrayList<String> purchaseProductIds = new ArrayList<>();

    @Override
    protected ActivityCaveMinePulsePurchaseBinding DataBridge() {
        return ActivityCaveMinePulsePurchaseBinding.inflate(getLayoutInflater());
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
        minePurchaseViewModel = new ViewModelProvider(this).get(AppStateViewModel.class);
        AppStatusBarjava(this);

        TextView marqueeText = findViewById(R.id.bdgDetailsBody);
        marqueeText.setSelected(true);

        TextView marqueeText1 = findViewById(R.id.bdgDetailsScreen);
        marqueeText1.setSelected(true);

        TextView marqueeText2 = findViewById(R.id.bdgDetailsUi);
        marqueeText2.setSelected(true);

        TextView marqueeText3 = findViewById(R.id.bdgDetailsDownFan);
        marqueeText3.setSelected(true);

        TextView marqueeText4 = findViewById(R.id.bdgDetailsButtons);
        marqueeText4.setSelected(true);

        TextView marqueeText5 = findViewById(R.id.bdgDetailsUpgrade);
        marqueeText5.setSelected(true);

        TextView marqueeText6 = findViewById(R.id.bdgDetailsWalls);
        marqueeText6.setSelected(true);

        TextView marqueeText7 = findViewById(R.id.bdgDetailsBasement);
        marqueeText7.setSelected(true);

        allbindData.setLifecycleOwner(this);

        purchasePaymentData = new BDG_PaymentModel();
        minePurchaseDatabaseReference = FirebaseDatabase.getInstance().getReference("paymentsData_success_INAPP");
        minePurchaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                minePurchaseCountSuccess = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle cancellation
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String response = intent.getStringExtra("planItem");
            minePurchasePlanItem = getGsonInstance().fromJson(response, BDG_PlanData.class);

            if (minePurchasePlanItem != null) {
                allbindData.titlebdg.setText(minePurchasePlanItem.getPlanName());
                minePurchasepurchase_id = minePurchasePlanItem.getId();
                minePurchaseAmount = String.valueOf(minePurchasePlanItem.getPriceInApp());
                Glide.with(getApplicationContext()).load(minePurchasePlanItem.getImagePath()).into(allbindData.boosterimgbdg);

                allbindData.bdganticipatedrol.setText(minePurchasePlanItem.getAnticipatedRol() + "%");
                allbindData.bdghistoricalrol.setText(minePurchasePlanItem.getHistoricalRol() + "%");
                allbindData.txhashrateSpeedbdg.setText(minePurchasePlanItem.getSpeed() + " GH/S");
                allbindData.textshabdg.setText(minePurchasePlanItem.getSha());
                allbindData.textwithdrawalbdg.setText(minePurchasePlanItem.getMinimumWithdraw());
                allbindData.textcomputingpowerbdg.setText(minePurchasePlanItem.getComputingPower());
                allbindData.textenergyefficiencybdg.setText(minePurchasePlanItem.getEnergyEfficiency());
                allbindData.textplanvaliditybdg.setText(minePurchasePlanItem.getContract() + " Days");
                allbindData.textmainpricebdg.setText(minePurchaseAmount);
                allbindData.bdgDetailsBody.setText(minePurchasePlanItem.getBody());
                allbindData.bdgDetailsScreen.setText(minePurchasePlanItem.getScreen());
                allbindData.bdgDetailsUi.setText(minePurchasePlanItem.getUi());
                allbindData.bdgDetailsDownFan.setText(minePurchasePlanItem.getTopFan());
                allbindData.bdgDetailsButtons.setText(minePurchasePlanItem.getButtons());
                allbindData.bdgDetailsUpgrade.setText(minePurchasePlanItem.getUpgrade());
                allbindData.bdgDetailsWalls.setText(minePurchasePlanItem.getWalls());
                allbindData.bdgDetailsBasement.setText(minePurchasePlanItem.getBasement());

                try {
                    if (minePurchasePlanItem.getDiscount() != null && !minePurchasePlanItem.getDiscount().isEmpty() && minePurchaseAmount != null && !minePurchaseAmount.isEmpty()) {
                        allbindData.layOfferbdg.setVisibility(View.VISIBLE);
                        String numericPart = minePurchaseAmount.replaceAll("[^\\d.]", "");
                        double price = Double.parseDouble(numericPart);
                        int discount = Integer.parseInt(minePurchasePlanItem.getDiscount());
                        allbindData.txtwhitePricebdg.setText(calculateOriginalPrice(price, discount));
                    } else {
                        allbindData.layOfferbdg.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    // Log any parsing or data issues
                    e.printStackTrace();
                }

                try {
                    if (minePurchasePlanItem != null && minePurchasePlanItem.getDiscount() != null && !minePurchasePlanItem.getDiscount().isEmpty()) {
                        allbindData.layOfferbdg.setVisibility(View.VISIBLE);
                        String numericPart = minePurchaseAmount.replaceAll("[^\\d.]", "");
                        double price = Double.parseDouble(numericPart);
                        int discount = Integer.parseInt(minePurchasePlanItem.getDiscount());
                        int discountprice = calculateOriginalPrice(price, discount);
                        allbindData.txtwhitePricebdg.setText("" + discountprice);
                    } else {
                        allbindData.layOfferbdg.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                purchasePaymentData.setEmail(SessionaryJava.getSessionaryinstance().getLoginApi().getEmail());
                purchasePaymentData.setPaymentAmount(minePurchaseAmount);
                purchasePaymentData.setPaymentType(allbindData.titlebdg.getText().toString());
                purchasePaymentData.setPayuOld(SessionaryJava.getSessionaryinstance().getAppSettingApi().getPayuOld());
                purchasePaymentData.setPayuNew(SessionaryJava.getSessionaryinstance().getAppSettingApi().getPayuNew());
                purchasePaymentData.setPayuOldSoltKey(SessionaryJava.getSessionaryinstance().getAppSettingApi().getPayuOldSoltKey());
                purchasePaymentData.setPayuNewSoltKey(SessionaryJava.getSessionaryinstance().getAppSettingApi().getPayuNewSoltKey());
                purchasePaymentData.setRazorMerchantKey(SessionaryJava.getSessionaryinstance().getAppSettingApi().getRazorMerchantKey());
                purchasePaymentData.setRazorpayEnable(SessionaryJava.getSessionaryinstance().getAppSettingApi().getRazorPay());
                purchasePaymentData.setUpi(SessionaryJava.getSessionaryinstance().getAppSettingApi().getUpi());
                purchasePaymentData.setUpiMerchant(SessionaryJava.getSessionaryinstance().getAppSettingApi().getUpiMerchant());
                purchasePaymentData.setPaytm(SessionaryJava.getSessionaryinstance().getAppSettingApi().getPaytm());
                purchasePaymentData.setCashFree(SessionaryJava.getSessionaryinstance().getAppSettingApi().getCashFree());
                purchasePaymentData.setCashMerchantKey(SessionaryJava.getSessionaryinstance().getAppSettingApi().getCashMerchantKey());
                purchasePaymentData.setInAppPurchase(SessionaryJava.getSessionaryinstance().getAppSettingApi().getInAppPurchase());
                purchasePaymentData.setShowAllWorld(SessionaryJava.getSessionaryinstance().getAppSettingApi().getShowAllWorld());
                purchasePaymentData.setOutsideIndia(SessionaryJava.getSessionaryinstance().getAppSettingApi().getOutsideIndia());
                purchasePaymentData.setUpiApi(SessionaryJava.getSessionaryinstance().getAppSettingApi().getUpiApi());
                purchasePaymentData.setPaymentGatWay("In App");
            }
        }

        allbindData.layspecification.setOnClickListener(v -> {
            if (allbindData.layspecificationdetail.getVisibility() == View.VISIBLE) {
                allbindData.layspecificationdetail.setVisibility(View.GONE);
                allbindData.imageView0.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_dropdown));
            } else {
                allbindData.layspecificationdetail.setVisibility(View.VISIBLE);
                allbindData.imageView0.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_dropup));
            }
        });

        allbindData.btnbackbdg.setOnClickListener(v -> finish());
        allbindData.privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SafeDataInfo.class);
                intent.putExtra("Flag", "Privacy");
                startActivity(intent);
            }
        });

        allbindData.termsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SafeDataInfo.class);
                intent.putExtra("Flag", "Condition");
                startActivity(intent);
            }
        });

        allbindData.btnActivateNowBdg.setOnClickListener(v -> {
            if (!purchaseProdDetailsLists.isEmpty()) {
                for (List<ProductDetails> productDetailsList : purchaseProdDetailsLists) {
                    if (productDetailsList.get(0).getProductId().equals(minePurchasePlanItem.getPlanId())) {
                        BDG_LaunchPurchaseFlow.launchPurchaseFlow(productDetailsList.get(0), CaveMinePulsePurchase.this);
                        minePurchaseDialogShow = true;
                    }
                }
            } else {
                bothsnackBars(allbindData.getRoot(), "Please try again......", 1);
            }
        });

        allbindData.Restore.setOnClickListener(v -> restoreBtn());

        prepareInApp();
    }

    @Override
    protected void ModelSyncObserver() {
        minePurchaseViewModel.getaddplanResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:
                    break;
                case MALYU:
                    if (result.data != null) {
                        ProjectConstantsJava.Project_CURRENT_PLAN_SHOW = minePurchasePlanItem.getPlanName();
                        ProjectConstantsJava.Project_CURRENT_PLAN = minePurchasePlanItem.getId();
                    }
                    break;

                case NOMALYU:
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });
    }

    @Override
    protected void viewAds() {

    }

    private Gson getGsonInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void restoreBtn() {
        String fullSentence = getString(R.string.restore);
        SpannableString spannableString = new SpannableString(fullSentence);
        int startIndex = fullSentence.indexOf("‘Restore’");
        int endIndex = startIndex + "‘Restore’".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                launchBtnRestorePurchaseFlow();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                int textColor = ContextCompat.getColor(CaveMinePulsePurchase.this, R.color.app_color);
                ds.setColor(textColor);
            }
        };

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        allbindData.Restore.setText(spannableString);
        allbindData.Restore.setMovementMethod(LinkMovementMethod.getInstance());
        allbindData.Restore.setHighlightColor(Color.TRANSPARENT);
    }

    private void launchBtnRestorePurchaseFlow() {
        Log.i(TAG, "launchBtnRestorePurchaseFlow: ");
        BDG_PurchaseBillingClient.getInstance().restorePurchases(this);
    }

    private void prepareInApp() {
        Log.i(TAG, "prepareInApp: ");
        if (!SessionaryJava.getSessionaryinstance().getAllPlan().isEmpty()) {
            purchasePlanDetailsList.clear();
            purchaseProductIds.clear();
            for (int i = 0; i < SessionaryJava.getSessionaryinstance().getAllPlan().size(); i++) {
                BDG_PlanData purchasePlanItem = SessionaryJava.getSessionaryinstance().getAllPlan().get(i);
                if (purchasePlanItem != null && Integer.parseInt(purchasePlanItem.getCategory()) == Project_INA_PURCHASE_CATEGORY_ID) {
                    purchasePlanDetailsList.add(purchasePlanItem);
                    purchaseProductIds.add(purchasePlanItem.getPlanId());
                    Log.e(TAG, "prepareInApp: " + purchasePlanDetailsList.toString());
                }
            }
        }

        if (!purchaseProductIds.isEmpty()) {
            Log.i(TAG, "onCreate: ");
            BDG_Utils.getUtilsInstance().prepareProductIdList(purchaseProductIds)
                    .setBillingUpdatesListener(this, this);
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
        minePurchaseDialogShow = false;
    }

    @Override
    public void BDG_onQueryPurchasesFailed(String message) {
        minePurchaseDialogShow = false;
    }

    @Override
    public void BDG_onSuccessesPurchasesResponse(Purchase purchase, int plan_id) {
        Log.i(TAG, "onSuccessesPurchasesResponse: ");
        successfullyPurchase(purchase);
    }

    @Override
    public void BDG_onPurchaseFlowLaunchingFailed(String message) {
        Log.i(TAG, "onPurchaseFlowLaunchingFailed: ");
        minePurchaseDialogShow = false;
        // Handle message
    }

    @Override
    public void BDG_onConsumeFinished(String token, int result) {
        Log.i(TAG, "onConsumeFinished: ");
    }

    @Override
    public void BDG_onConsumeFailed(String message) {
        Log.i(TAG, "onConsumeFailed: ");
        bothsnackBars(allbindData.getRoot(), message, 1);
    }

    @Override
    public void BDG_onProductDetailsResponse(ArrayList<List<ProductDetails>> productDetailsList) {
        if (productDetailsList != null && !productDetailsList.isEmpty()) {
            purchaseProdDetailsLists.addAll(productDetailsList);
        }
    }

    @Override
    public void BDG_onProductDetailsFailed(String message) {
        Log.i(TAG, "onProductDetailsFailed: ");
    }

    @Override
    public void BDG_onMiningPriceChangeConfirmationResult(BillingResult billingResult, SkuDetails skuDetails) {
        Log.i(TAG, "onMiningPriceChangeConfirmationResult: ");
    }

    @Override
    public void BDG_onMiningPriceChangeConfirmationFailed(String message) {
        Log.i(TAG, "onMiningPriceChangeConfirmationFailed: ");
    }

    private void successfullyPurchase(Purchase purchase) {
        if (minePurchaseDialogShow) {
            if (purchase.isAutoRenewing()) {
                Log.i(TAG, "successfullyPurchase: Display Celebration Dialog");
                ProjectConstantsJava.Project_CURRENT_PLAN_SHOW = minePurchasePlanItem.getPlanName() + " Plan";
                ProjectConstantsJava.Project_CURRENT_PLAN = minePurchasePlanItem.getId();
                setCurrentPlan();
                showPurchaseSuccessfulDialog();
                try {
                    purchasePaymentData.setResponse(purchase.toString());
                } catch (Exception e) {
                    purchasePaymentData.setResponse("Success");
                    e.printStackTrace();
                }
                purchasePaymentData.setPaymentResponse("Success");
                addPaymentData(purchasePaymentData);
            }
        }
    }

    private void setCurrentPlan() {
        minePurchaseViewModel.AddPlanResponseapi(new Addplanreq(
                Project_USER_KEY,
                Project_APP_ID,
                minePurchasepurchase_id
        ));
    }

    private void showPurchaseSuccessfulDialog() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());
        Date date = null;
        try {
            date = sdf.parse(currentDateAndTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (minePurchasePlanItem != null) {
                calendar.add(Calendar.DAY_OF_YEAR, 3);
            } else {
                calendar.add(Calendar.DAY_OF_YEAR, 7);
            }
            ProjectConstantsJava.Project_PLAN_EXPIRE_TIME = calendar.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        minePurchaseDialogShow = false;

        showPurchase();
    }

    private boolean addPaymentData(BDG_PaymentModel paymentData) {
        if (SessionaryJava.getSessionaryinstance().getAppSettingApi().getIsShowPurchaseEntryInFirebase()) {
            try {
                String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
                String id = "(" + minePurchaseCountSuccess + ") " + paymentData.getPaymentResponse() + " Rs : " +
                        paymentData.getPaymentAmount() + " : " + minePurchasePlanItem.getPlanName() + " : " +
                        paymentData.getPaymentGatWay() + " : " + currentDate;
                paymentData.setId(id);
                paymentData.setDate(currentDate);
                minePurchaseDatabaseReference.child(id).setValue(paymentData);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
                    String id = "(" + minePurchaseCountSuccess + ") " + paymentData.getPaymentResponse() + " : " +
                            minePurchasePlanItem.getPlanName() + " : " + paymentData.getPaymentGatWay() + " : " + currentDate;
                    paymentData.setId(id);
                    paymentData.setDate(currentDate);
                    minePurchaseDatabaseReference.child(id).setValue(paymentData);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return true;
    }

    private void showPurchase() {
        try {
            if (UserInteractionStatsJava.isValidContext(this)) {
                FragmentManager fm = getSupportFragmentManager();
                if (fm != null) {
                    UniversalDialogJava withdrawSuccessDialog = new UniversalDialogJava(STATUS_ITEM_PURCHASED, this, result -> {
                    });
                    withdrawSuccessDialog.setCancelable(false);
                    withdrawSuccessDialog.setStyle(
                            DialogFragment.STYLE_NORMAL,
                            android.R.style.Theme_Light_NoTitleBar_Fullscreen
                    );
                    withdrawSuccessDialog.show(fm, UniversalDialogJava.class.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
