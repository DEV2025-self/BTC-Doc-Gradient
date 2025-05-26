package com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_CONST_FORCE_DISABLE_ADS;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity.SecureLoginJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdCallBack;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdsLib;
import com.doc.gradient.bt.server.uses.ai.ads.FacebookAds.BtcFacebookAdsLib;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseCoreParentJava<B extends ViewBinding> extends AppCompatActivity {
    public static final int STATUS_OFFLINE_MODE = 1;
    public static final int STATUS_FUNDS_WITHDRAW = 2;
    public static final int STATUS_SESSION_TERMINATED = 3;
    public static final int STATUS_ACCOUNT_REMOVED = 4;
    public static final int STATUS_ITEM_PURCHASED = 5;
    public static final int STATUS_TRANSACTION_SUCCESSFUL = 8;
    public static final int STATUS_TRANSACTION_ERROR = 9;
    public static final int STATUS_TRANSACTION_PENDING = 10;
    protected B allbindData;
    private ProgressDialog BaseprogressDialog;
    private UniversalDialogJava noData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allbindData = DataBridge();
        setContentView(allbindData.getRoot());

        NetBridge();
        RecallBindingProcess();
        ModelSyncObserver();
        viewAds();
    }

    protected abstract B DataBridge();

    protected abstract void NetBridge();

    protected abstract void RecallBindingProcess();

    protected abstract void ModelSyncObserver();

    protected abstract void viewAds();

    protected void bothsnackBars(View view, String warning, int value) {
        Snackbar warningBar = Snackbar.make(view, warning, Snackbar.LENGTH_SHORT);
        if (value == 1) {
            warningBar.setBackgroundTint(getApplicationContext().getResources().getColor(R.color.red));
        }else if (value == 2){
            warningBar.setBackgroundTint(getApplicationContext().getResources().getColor(R.color.green));
        }
        warningBar.setTextColor(getApplicationContext().getResources().getColor(R.color.white));

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) warningBar.getView().getLayoutParams();
        params.width = FrameLayout.LayoutParams.MATCH_PARENT;
        warningBar.getView().setLayoutParams(params);

        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        warningBar.show();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void AppStatusBarjava(Activity activity) {
        Window window = activity.getWindow();
        Drawable background = activity.getResources().getDrawable(R.color.background);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.background));
        activity.getWindow().setNavigationBarColor(activity.getResources().getColor(R.color.background));
        activity.getWindow().setBackgroundDrawable(background);
    }

    protected void noDataDialog() {
        try {
            if (UserInteractionStatsJava.validContext(this)) {
                FragmentManager fm = getSupportFragmentManager();
                if (fm != null) {
                    noData = new UniversalDialogJava(STATUS_OFFLINE_MODE, this, (decision -> {}));
                    noData.setCancelable(false);
                    noData.setStyle(
                            DialogFragment.STYLE_NORMAL,
                            android.R.style.Theme_Light_NoTitleBar_Fullscreen
                    );
                    noData.show(fm, UniversalDialogJava.class.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideDataDialog() {
        if (noData != null && noData.isAdded() && noData.isVisible()) {
            noData.dismiss();
            RecallBindingProcess();
        }
    }

    protected void ShowSpin(Activity activity) {
        try {
            if (UserInteractionStatsJava.validContext(activity)) {
                if (BaseprogressDialog == null) {
                    BaseprogressDialog = new ProgressDialog(activity, R.style.RoundedProgressDialog);
                    BaseprogressDialog.setMessage(getString(R.string.loading));
                    BaseprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    BaseprogressDialog.setIndeterminate(true);
                    BaseprogressDialog.setCancelable(false);
                    BaseprogressDialog.show();
                } else if (BaseprogressDialog.isShowing()) {
                    BaseprogressDialog.setMessage(getString(R.string.loading));
                } else if (!BaseprogressDialog.isShowing()) {
                    BaseprogressDialog.setMessage(getString(R.string.loading));
                    BaseprogressDialog.show();
                }
            }
        } catch (Exception th) {
            th.printStackTrace();
        }
    }

    protected void HideSpin() {
        new Handler(Looper.getMainLooper()).post(() -> {
            try {
                if (BaseprogressDialog != null && BaseprogressDialog.isShowing()) {
                    BaseprogressDialog.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    protected void userDeleted(Activity activity) {
        ProjectConstantsJava.Project_CURRENT_POINT = 0.00000000;
        SessionaryJava.getSessionaryinstance().setUserLogOut(true);
        SessionaryJava.getSessionaryinstance().SetUserInfo(null);
        Intent intent = new Intent(activity, SecureLoginJava.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        activity.finish();
    }

    protected void showBannerAds(LinearLayout hideView, Activity activity) {
        try {
            if (SessionaryJava.getSessionaryinstance().getAppSettingApi().getIsShowAds() && !Project_CONST_FORCE_DISABLE_ADS) {

                BtcFacebookAdsLib.loadBannerAds(
                        activity,
                        findViewById(R.id.bannerAdViewbdg),
                        hideView
                );
            } else {
                hideView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            hideView.setVisibility(View.GONE);
        }
    }

    protected void showInterstitialsAds(Activity activity, BtcAllInterstitialAdCallBack allInterstitialAdCallBackAdsLib) {
        try {
            if (SessionaryJava.getSessionaryinstance().getAppSettingApi().getIsShowAds() && !Project_CONST_FORCE_DISABLE_ADS) {
                try {
                    BtcAllInterstitialAdsLib.loadAllInterstitialAds(
                            activity,
                            allInterstitialAdCallBackAdsLib
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
