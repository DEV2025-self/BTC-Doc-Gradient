package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static android.view.View.GONE;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.Allplanreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.ads.BtcManager.BtcConfigManagerAds;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityBrandSplashBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.HashMap;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BrandSplashJava extends BaseCoreParentJava<ActivityBrandSplashBinding> {
    public static final int CODE = 22;
    AppStateViewModel splashvModel;
    CountDownTimer brandCountDownTimer = null;
    boolean brandIsSettingAPI = false;
    AppUpdateManager brandUpdateManager = null;
    int BRAND_TYPE_SUPPORTED = -1;
    InstallStateUpdatedListener listener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState state) {
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                bothsnackBars(allbindData.getRoot(), "App Update All Most Done", 2);

                if (brandCountDownTimer != null && BRAND_TYPE_SUPPORTED == AppUpdateType.FLEXIBLE) {
                    brandIsSettingAPI = true;
                    brandCountDownTimer.onFinish();
                }
            }
        }
    };
    private int brandCountForAds = 3;
    private int brandCount = 0;
    private AutoConnectNetwork autoConnectNetwork = null;

    @NonNull
    @Override
    public ActivityBrandSplashBinding DataBridge() {
        return ActivityBrandSplashBinding.inflate(getLayoutInflater());
    }

    @Override
    public void NetBridge() {
        autoConnectNetwork = new AutoConnectNetwork(this);
        autoConnectNetwork.observe(BrandSplashJava.this, new Observer<Boolean>() {
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
    public void RecallBindingProcess() {

        splashvModel = new ViewModelProvider(this).get(AppStateViewModel.class);

        AppStatusBarjava(this);

        allbindData.loader.setVisibility(View.VISIBLE);
        allbindData.loader.loop(true);
        allbindData.loader.setAnimation(R.raw.loader);
        allbindData.loader.playAnimation();

        callSettingApi();
        callAllPlanss();

        brandCountDownTimer = new CountDownTimer(50000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 10000 && (BRAND_TYPE_SUPPORTED == -1 || BRAND_TYPE_SUPPORTED == AppUpdateType.IMMEDIATE)) {
                    if (UserInteractionStatsJava.isValidContext(getApplicationContext())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BrandSplashJava.this);
                        builder.setMessage("Something went to wrong please try again later.").setTitle("Attention").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();

                        if (brandCountDownTimer != null) {
                            brandCountDownTimer.cancel();
                        }
                    }
                }

                if (brandIsSettingAPI && millisUntilFinished < 46500 && brandCountDownTimer != null) {
                    onFinish();  // Manually calling onFinish
                }
            }

            @Override
            public void onFinish() {
                try {
                    if (brandCountDownTimer != null) {
                        brandCountDownTimer.cancel();
                        brandCountDownTimer = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (brandIsSettingAPI) {
                    SessionaryJava session = SessionaryJava.getSessionaryinstance();
                    if (session != null && (session.getShowAdsCount() == 0 || session.getShowAdsCount() == brandCountForAds)) {

                        brandCount = 0;
                        brandCount++;
                        SessionaryJava.getSessionaryinstance().setShowAdsCount(brandCount);

                        if (!session.getUserLogout()) {
                            startActivity(new Intent(BrandSplashJava.this, StartDashboardAnalytics.class));
                        } else {
                            startActivity(new Intent(BrandSplashJava.this, SecureLoginJava.class));
                        }
                        finish();

                    } else {
                        brandCount++;
                        SessionaryJava.getSessionaryinstance().setShowAdsCount(brandCount);


                        if (!session.getUserLogout()) {
                            startActivity(new Intent(BrandSplashJava.this, StartDashboardAnalytics.class));
                        } else {
                            startActivity(new Intent(BrandSplashJava.this, SecureLoginJava.class));
                        }
                        finish();
                    }
                }
            }
        }.start();
    }

    @Override
    public void ModelSyncObserver() {


        splashvModel.settingResponse.observe(this, result -> {
            if (result == null) return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:

                    if (result.data != null && result.data.getData() != null) {
                        if (SessionaryJava.getSessionaryinstance() != null) {
                            SessionaryJava.getSessionaryinstance().SetsettingsClient(result.data);
                        }

                        try {
                            if (result.data.getData().getJson() != null && result.data.getData().getJson().getAdsCount() != null) {
                                brandCountForAds = Integer.parseInt(result.data.getData().getJson().getAdsCount());
                            } else {
                                brandCountForAds = 3;
                            }

                            if (SessionaryJava.getSessionaryinstance() != null) {
                                brandCount = SessionaryJava.getSessionaryinstance().getShowAdsCount();
                            } else {
                                brandCount = 0;
                            }
                        } catch (NumberFormatException e) {
                            brandCountForAds = 3;
                            brandCount = 0;
                        }

                        intAllAds();

                        if (Boolean.TRUE.equals(result.data.getData().getAppUpdate())) {
                            checkInAppUpdate();
                        } else {
                            brandIsSettingAPI = true;
                        }


                    } else {
                        FirebaseCrashlytics.getInstance().recordException(new Exception("AppSetting Response Getting Null"));
                        bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    }
                    break;

                case NOMALYU:
                    allbindData.loader.setVisibility(GONE);
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });

        splashvModel.getAllplanResponse().observe(this, result -> {
            if (result == null) return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    if (result.data.getData() != null && result.data.getData() != null) {
                        SessionaryJava.getSessionaryinstance().saveAllPlans(result.data.getData());
                    }
                    break;

                case NOMALYU:

                    break;
            }
        });

    }

    @Override
    public void viewAds() {

    }

    void callSettingApi() {
        allbindData.loader.setVisibility(View.VISIBLE);
        HashMap<String, String> fields = new HashMap<>();
        fields.put("app_id", "3");
        splashvModel.dataAppSetting(fields);

    }

    void callAllPlanss() {
        splashvModel.AllplanResponseapi(new Allplanreq(Project_APP_ID));
    }

    private void intAllAds() {
        BtcConfigManagerAds.getSessionaryinstance().initObAdMobConfigManager(getApplicationContext());

        BtcConfigManagerAds.getSessionaryinstance().setFbInterstitialAdId(Objects.requireNonNull(SessionaryJava.getSessionaryinstance().getAppSettingApi().getFbInterstitialAd())).setFbNativeAdId(Objects.requireNonNull(SessionaryJava.getSessionaryinstance().getAppSettingApi().getFbNativeAd()))
                .setFbNativeBannerAdId(Objects.requireNonNull(SessionaryJava.getSessionaryinstance().getAppSettingApi().getFbNativeBannerAd()))
                .setFbNativeBanner250Id(Objects.requireNonNull(SessionaryJava.getSessionaryinstance().getAppSettingApi().getFbMediumRectangle250()))
                .setAdmobBannerAdId(Objects.requireNonNull(SessionaryJava.getSessionaryinstance().getAppSettingApi().getAdmobBannerAd()))
                .setAdmobNativeAdId(Objects.requireNonNull(SessionaryJava.getSessionaryinstance().getAppSettingApi().getAdmobNativeAd()))
                .setAdmobInterstitialAdId(Objects.requireNonNull(SessionaryJava.getSessionaryinstance().getAppSettingApi().getAdmobInterstitialAd()))
                .setAdmobRewordAdsId(Objects.requireNonNull(SessionaryJava.getSessionaryinstance().getAppSettingApi().getAdmobRewardedVideoAd()))
                .settingAdsCount(Integer.parseInt(Objects.requireNonNull(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getAdsCount())))
                .setAdMobCount(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getAdMobCount())

                .setIsAdsShow(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getIsShowAds()))
                .setIsTestAds(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getIsTestAd()))
                .setIsFaceBook(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getFacebookAds()))
                .setIsAdmob(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getAdmobAdsId()))

                .setIsFbBanner(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getFbBanner()))
                .setIsFbNative(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getFbNative()))
                .setIsFbSmallNative(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getFbSmallNative()))
                .setIsFbInterstitial(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getFbInterstitial()))
                .setIsFb250rectangle(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getFb250rectangle()))
                .setIsAdmobBanner(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getAdmobBanner()))
                .setIsAdmobNative(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getAdmobNative()))
                .setIsAdmobSmallNative(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getAdmobSmallNative()))
                .setIsAdmobInterstitial(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getAdmobInterstitial()))
                .setIsAdmobRewordAds(Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getAdmobRewordAds()));
    }

    private void checkInAppUpdate() {
        brandUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> appUpdateInfoTask = brandUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                try {
                    boolean isImmediateAllowed = appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE) && SessionaryJava.getSessionaryinstance() != null && SessionaryJava.getSessionaryinstance().getAppSettingApi() != null && Boolean.TRUE.equals(SessionaryJava.getSessionaryinstance().getAppSettingApi().getAppUpdateTypeImmediate());

                    boolean isFlexibleAllowed = appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE);

                    if (isImmediateAllowed) {
                        BRAND_TYPE_SUPPORTED = AppUpdateType.IMMEDIATE;
                        try {
                            brandUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, BrandSplashJava.this, CODE);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                    } else if (isFlexibleAllowed) {
                        BRAND_TYPE_SUPPORTED = AppUpdateType.FLEXIBLE;
                        try {
                            brandUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, BrandSplashJava.this, CODE);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                    } else {
                        BRAND_TYPE_SUPPORTED = -1;
                        brandIsSettingAPI = true;
                    }
                } catch (Exception e) {
                    FirebaseCrashlytics.getInstance().recordException(e);
                    BRAND_TYPE_SUPPORTED = -1;
                    brandIsSettingAPI = true;
                }
            }
        });

        appUpdateInfoTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                FirebaseCrashlytics.getInstance().recordException(new Exception("AppUpdateManager.addOnFailureListener", e));
                BRAND_TYPE_SUPPORTED = -1;
                brandIsSettingAPI = true;
            }
        });

        if (brandUpdateManager != null) {
            brandUpdateManager.registerListener(listener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE) {
            if (resultCode == RESULT_CANCELED) {
                bothsnackBars(allbindData.getRoot(), "Please update your application. Otherwise its didn't work.", 1);

                if (brandCountDownTimer != null && BRAND_TYPE_SUPPORTED == AppUpdateType.FLEXIBLE) {
                    brandIsSettingAPI = true;
                    brandCountDownTimer.onFinish();
                }

            } else if (resultCode == RESULT_OK) {
                bothsnackBars(allbindData.getRoot(), "Downloading...", 2);

            } else {
                bothsnackBars(allbindData.getRoot(), "Update Failed!", 1);
                checkInAppUpdate();
            }

        } else {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (brandCountDownTimer != null) {
            brandCountDownTimer.cancel();
            brandCountDownTimer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (brandUpdateManager != null) {
            brandUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
                @Override
                public void onSuccess(AppUpdateInfo appUpdateInfo) {
                    if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                        bothsnackBars(allbindData.getRoot(), "App Update All Most Done", 2);
                    }
                }
            });
        }
    }

}