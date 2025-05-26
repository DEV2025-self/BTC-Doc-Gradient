package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static androidx.fragment.app.DialogFragment.STYLE_NORMAL;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_CURRENT_PLAN;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_USER_KEY;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.UniversalDialogJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Withdrawal.Withdrawalreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdCallBack;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdsLib;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityFundBinding;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Fund extends BaseCoreParentJava<ActivityFundBinding> implements BtcAllInterstitialAdCallBack {

    static final int Fund_WITHDRAWAL = 1;
    static final int Fund_WITHDRAWAL_HISTORY = 2;
    AppStateViewModel fundVModel;
    boolean fundisConnect = false;
    LinearLayout fundhideView = null;
    int fundselectType = 0;
    AutoConnectNetwork autoconnectnetwork = null;

    @Override
    protected ActivityFundBinding DataBridge() {
        return ActivityFundBinding.inflate(getLayoutInflater());
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
                    fundisConnect = true;
                    hideDataDialog();
                }
            }
        });
    }

    @Override
    protected void RecallBindingProcess() {
        fundVModel = new ViewModelProvider(this).get(AppStateViewModel.class);

        AppStatusBarjava(this);

        Intent intent = getIntent();
        if (intent != null) {
            String one = intent.getStringExtra("CurrentPoint");
            if (one != null) {
                allbindData.txtCurrentBtcbdg.setText(one);
            } else {
                allbindData.txtCurrentBtcbdg.setText("0.00000000");
            }
        }
        allbindData.loader.loop(true);
        allbindData.loader.setAnimation(R.raw.loader);
        allbindData.loader.playAnimation();
        allbindData.btnbackbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        allbindData.btnhistorybdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fundselectType = Fund_WITHDRAWAL_HISTORY;
                showUserItemClickAd();
            }
        });
        allbindData.btnwithdrawbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fundselectType = Fund_WITHDRAWAL;
                showUserItemClickAd();
            }
        });
    }

    @Override
    protected void ModelSyncObserver() {
        fundVModel.getWithdrawalResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    HideSpin();
                    try {
                        if (result.data.getMessage() != null) {
                            if (result.data.getMessage().equals("User not found.")) {
                                userDeleted(this);
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (result.data != null) {
                        if (result.data.getStatus()) {
                            try {
                                if (UserInteractionStatsJava.isValidContext(this)) {
                                    FragmentManager fm = getSupportFragmentManager();
                                    if (fm != null) {
                                        UniversalDialogJava withdrawSuccessDialog = new UniversalDialogJava(
                                                STATUS_FUNDS_WITHDRAW,
                                                this, decision -> {
                                        });
                                        withdrawSuccessDialog.setCancelable(false);
                                        withdrawSuccessDialog.setStyle(
                                                STYLE_NORMAL,
                                                android.R.style.Theme_Light_NoTitleBar_Fullscreen
                                        );
                                        withdrawSuccessDialog.show(fm, UniversalDialogJava.class.getName());
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            bothsnackBars(allbindData.getRoot(), result.data.getMessage(), 1);
                        }
                    } else {
                        bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    }

                    allbindData.txtMiningPoint.setText("");
                    allbindData.txtWalletAddress.setText("");
                    allbindData.btnwithdrawbdg.setVisibility(View.VISIBLE);
                    allbindData.loader.setVisibility(View.GONE);
                    break;

                case NOMALYU:
                    HideSpin();
                    allbindData.txtMiningPoint.setText("");
                    allbindData.txtWalletAddress.setText("");
                    allbindData.btnwithdrawbdg.setVisibility(View.VISIBLE);
                    allbindData.loader.setVisibility(View.GONE);
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });
    }

    @Override
    protected void viewAds() {
        fundhideView = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(fundhideView, Fund.this);
        showUserItemClickAd();
    }

    private void showUserItemClickAd() {
        BtcAllInterstitialAdsLib.showItemClickAd(Fund.this, Fund.this);
    }

    @Override
    public void gotoNext() {
        View view = Fund.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        switch (fundselectType) {
            case Fund_WITHDRAWAL_HISTORY:
                startActivity(new Intent(Fund.this, FundVault.class));
                break;

            case Fund_WITHDRAWAL:
                String walletAddress = allbindData.txtWalletAddress.getText().toString().trim();
                String walletPoint = allbindData.txtMiningPoint.getText().toString().trim();
                boolean addressIsValid = addressForValidator(walletAddress);

                if (walletAddress.isEmpty() || walletAddress == null) {
                    allbindData.txtWalletAddress.setError("Enter BTC wallet Address.");
                    return;
                } else if (walletPoint.isEmpty() || walletPoint == null) {
                    allbindData.txtMiningPoint.setError("Enter BTC Mining Point.");
                    return;
                } else if (!addressIsValid) {
                    allbindData.txtWalletAddress.setError("Enter Valid BTC wallet Address.");
                    return;
                } else {
                    try {
                        double miningPointFloat = Double.parseDouble(allbindData.txtMiningPoint.getText().toString());
                        double currentMiningPointFloat = Double.parseDouble(allbindData.txtCurrentBtcbdg.getText().toString().replace(",", "."));

                        if (miningPointFloat > currentMiningPointFloat) {
                            allbindData.txtMiningPoint.setError("Entered amount is more than Your current balance amount.");
                            return;
                        }

                        long miningPoint = (long) (miningPointFloat * 100000000);
                        long currentMiningPointLong = (long) (currentMiningPointFloat * 100000000);

                        if (currentMiningPointLong < minimumWithdrawal() || miningPoint < minimumWithdrawal()) {
                            allbindData.txtMiningPoint.setError(errorMsgBTC());
                            return;
                        } else {
                            if (fundisConnect) {
                                allbindData.btnwithdrawbdg.setVisibility(View.GONE);
                                allbindData.loader.setVisibility(View.VISIBLE);
                                GetWithdrawalApi(walletAddress, walletPoint);
                            } else {
                                bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(Fund.this, "Something Went To wrong..!!", Toast.LENGTH_SHORT).show();
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }
                }
                break;
        }
    }

    private void setPlan() {
        for (int i = 0; i < SessionaryJava.getSessionaryinstance().getAllPlan().size(); i++) {
            if (SessionaryJava.getSessionaryinstance().getAllPlan().get(i).getId() == Project_CURRENT_PLAN) {
                try {
                    String minWithdraw = "*Minimum Withdrawal " + SessionaryJava.getSessionaryinstance().getAllPlan().get(i).getMinimumWithdraw() + " BTC";
                    allbindData.txtMinimum.setText(minWithdraw);
                } catch (Exception e) {
                    allbindData.txtMinimum.setText("*Minimum Withdrawal 4.0 BTC");
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private boolean addressForValidator(String address) {
        if (address == null || address.isEmpty()) {
            return false;
        }
        return address.length() > 25;
    }

    private long minimumWithdrawal() {
        for (int i = 0; i < SessionaryJava.getSessionaryinstance().getAllPlan().size(); i++) {
            if (SessionaryJava.getSessionaryinstance().getAllPlan().get(i).getId() == Project_CURRENT_PLAN) {
                try {
                    double point = Double.parseDouble(SessionaryJava.getSessionaryinstance().getAllPlan().get(i).getMinimumWithdraw());
                    return (long) (point * 100000000);
                } catch (Exception e) {
                    e.printStackTrace();
                    return (long) (2.0 * 100000000);
                }
            }
        }
        return (long) (2.0 * 100000000);
    }

    private String errorMsgBTC() {
        String minWithdraw = "";
        for (int i = 0; i < SessionaryJava.getSessionaryinstance().getAllPlan().size(); i++) {
            if (SessionaryJava.getSessionaryinstance().getAllPlan().get(i).getId() == Project_CURRENT_PLAN) {
                try {
                    minWithdraw = SessionaryJava.getSessionaryinstance().getAllPlan().get(i).getMinimumWithdraw() + " BTC";
                } catch (Exception e) {
                    e.printStackTrace();
                    minWithdraw = "4.0 BTC";
                }
                return "Please enter withdrawal amount minimum " + minWithdraw + ".";
            }
        }
        return "Please enter withdrawal amount minimum 4.0 BTC";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HideSpin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPlan();
    }

    private void GetWithdrawalApi(String walletAddress, String walletPoint) {
        fundVModel.WithdrawalApi(new Withdrawalreq(
                Project_USER_KEY,
                walletAddress,
                Double.parseDouble(walletPoint),
                "status",
                "BTC",
                Project_APP_ID
        ));
    }
}
