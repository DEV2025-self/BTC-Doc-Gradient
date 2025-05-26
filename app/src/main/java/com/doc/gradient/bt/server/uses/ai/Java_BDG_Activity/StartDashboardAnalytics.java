package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static android.Manifest.permission.POST_NOTIFICATIONS;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.BDG_CurrentPointAnimation.BDG_CurrentPointupdate;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.BDG_CurrentPointAnimation.CurrentPointDigits;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.BDG_StartbtnAnimationUtils.BDG_StartbtnPoint;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.BDG_StartbtnAnimationUtils.StartbtnPointDigits;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_CURRENT_PLAN_SHOW;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_INA_PURCHASE_CATEGORY_ID;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_SET_FREE_PLAN;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_SOCKET_URL;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_USER_KEY;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.BDG_Utils;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppChargeBilling.Interface.BDG_BillingUpdatesListener;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.UniversalDialogJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Add_Plan.Addplanreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_GetPlan.GetPlanreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.Allplanreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Plan.BDG_PlanData;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_Socket_Response.MiningDataSocketResponse;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfo.GetUserinforeq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdCallBack;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdsLib;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityStartDashboardAnalyticsBinding;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import dagger.hilt.android.AndroidEntryPoint;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;

@AndroidEntryPoint
public class StartDashboardAnalytics extends BaseCoreParentJava<ActivityStartDashboardAnalyticsBinding> implements BtcAllInterstitialAdCallBack, BDG_BillingUpdatesListener {
    private static final int BDG_COMPUTING_HIS = 8;
    private static final int BDG_COMPUTING = 9;
    private static final int BDG_WALLET = 1;
    private static final int BDG_DASHBOARD = 3;
    private static final int BDG_LEADERS = 4;
    private static final int BDG_USER_DATA = 5;
    private static final int BDG_REFER = 7;
    private static final int BDG_SETTINGS = 13;
    private static final int BDG_SIGN_OUT = 14;
    private final String appNAME = "BTC Mining";
    private final Emitter.Listener userConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(() -> {
                try {
                    JSONObject data = (JSONObject) args[0];
                    miningResSocket = getGsonInstance().fromJson(data.toString(), MiningDataSocketResponse.class);

                    BDG_CurrentPointupdate(
                            String.valueOf(miningResSocket.getMining()),
                            allbindData.currentBalance,
                            digitViews1,
                            StartDashboardAnalytics.this,
                            getResources().getColor(R.color.black)
                    );
                    CurrentPointDigits(String.valueOf(miningResSocket.getMining()), digitViews1);

                    ProjectConstantsJava.Project_CURRENT_POINT = Double.parseDouble(String.valueOf(miningResSocket.getMining()));

                    if (Boolean.TRUE.equals(miningResSocket.getIsMiningStart())) {
                        allbindData.txtConnecting.setVisibility(View.GONE);
                        BDG_StartbtnPoint(
                                miningResSocket.getTimeString(),
                                allbindData.txtStartNow,
                                digitViews2,
                                StartDashboardAnalytics.this,
                                getResources().getColor(R.color.white)
                        );
                        StartbtnPointDigits(miningResSocket.getTimeString(), digitViews2);
                    } else {
                        allbindData.txtConnecting.setVisibility(View.GONE);
                        BDG_StartbtnPoint(
                                getString(R.string.start_mining),
                                allbindData.txtStartNow,
                                digitViews2,
                                StartDashboardAnalytics.this,
                                getResources().getColor(R.color.white)
                        );
                        StartbtnPointDigits(getString(R.string.start_mining), digitViews2);
                    }

                    BigDecimal result1 = new BigDecimal(miningResSocket.getCurrentSpeed());
                    BigDecimal result = result1.divide(new BigDecimal("100000000"), 8, RoundingMode.FLOOR).abs();
                    allbindData.txtPerSce.setText(" +" + result.toPlainString());

                    if (!miningResSocket.getIsMiningStart().equals(isMiningStartSocket)) {
                        isMiningStartSocket = miningResSocket.getIsMiningStart();
                    }

                    if (!miningResSocket.getCurrentSpeed().equals(currentSpeed)) {
                        currentSpeed = miningResSocket.getCurrentSpeed();
                        isPlanAdded = !isPlanAdded;
                    }

                    if (!isPlanAdded) {
                        isPlanAdded = true;
                        getCurrentPlanApi();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    };
    private final String TAG = "StartDashboardAnalytics";
    private final Emitter.Listener userConnect_Start = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(() -> {
                try {
                    JSONObject data = (JSONObject) args[0];
                    // handle data if needed
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    };
    private final Emitter.Listener onDisconnect = args -> {
        // Optional: handle disconnect
    };
    private final Handler handler1 = null;
    private final AutoConnectNetwork autoconnectnetwork = null;
    private final boolean pressedOnce = false;
    private final int Current_plan = 77;
    // For Animations
    private final List<TextView> digitViews1 = new ArrayList<>();
    private final List<TextView> digitViews2 = new ArrayList<>();
    private final Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                allbindData.txtConnecting.setVisibility(View.VISIBLE);
                BDG_StartbtnPoint("", allbindData.txtStartNow, digitViews2, StartDashboardAnalytics.this,
                        getResources().getColor(R.color.white));
                StartbtnPointDigits("", digitViews2);
            });
        }
    };
    private final ArrayList<BDG_PlanData> inAppPlansDataArray = new ArrayList<>();
    private final ArrayList<BDG_PlanData> planDetailsListInApp = new ArrayList<>();
    private final ArrayList<String> productIds = new ArrayList<>();
    private final ArrayList<BDG_PlanData> planDetailsList = new ArrayList<>();
    private final boolean setCurrentPlan = false;
    private final boolean IS_PLAN_EXPIRE = false;
    private AppStateViewModel StartDashboardModel;
    private AutoConnectNetwork autoConnectNetwork = null;
    private boolean isDisable = false;
    private boolean sideMenu = false;
    private int selectType = 0;
    private LinearLayout banner = null;
    private boolean IS_PLAN_EXPIRE_BDG = false;
    private int Current_plan_bdg = 11;
    private String firstName = "";
    private String email = "";
    private Socket mSocket = null;
    private MiningDataSocketResponse miningResSocket = null;
    private Gson gson = null;
    private boolean isMiningStartSocket = false;
    private int currentSpeed = 0;
    private boolean isPlanAdded = false;
    private boolean userDoubleBackToExitPressedOnce = false;
    private boolean IS_IN_INDIA = true;

    @Override
    protected ActivityStartDashboardAnalyticsBinding DataBridge() {
        return ActivityStartDashboardAnalyticsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {
        autoConnectNetwork = new AutoConnectNetwork(this);
        autoConnectNetwork.observe(this, new Observer<Boolean>() {
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
        StartDashboardModel = new ViewModelProvider(this).get(AppStateViewModel.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestNotification();
            }
        }

        AppStatusBarjava(StartDashboardAnalytics.this);

        TextView marqueeText = findViewById(R.id.txtUserName);
        marqueeText.setSelected(true);
        TextView marqueeText1 = findViewById(R.id.txtUserEmail);
        marqueeText1.setSelected(true);
        TextView marqueeText2 = findViewById(R.id.register);
        marqueeText2.setSelected(true);

        try {
            if (SessionaryJava.getSessionaryinstance() != null && SessionaryJava.getSessionaryinstance().getUserInfo() != null
                    && SessionaryJava.getSessionaryinstance().getUserInfo().getFirstName() != null) {
                firstName = SessionaryJava.getSessionaryinstance().getUserInfo().getFirstName();
            }
            if (SessionaryJava.getSessionaryinstance() != null && SessionaryJava.getSessionaryinstance().getUserInfo() != null
                    && SessionaryJava.getSessionaryinstance().getUserInfo().getEmail() != null) {
                email = SessionaryJava.getSessionaryinstance().getUserInfo().getEmail();
            }
            allbindData.txtUserName.setText(firstName);
            allbindData.register.setText("Hello, " + firstName);
            allbindData.txtUserEmail.setText(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (SessionaryJava.getSessionaryinstance() != null &&
                    SessionaryJava.getSessionaryinstance().getLoginApi() != null &&
                    SessionaryJava.getSessionaryinstance().getLoginApi().getUserKey() != null &&
                    !SessionaryJava.getSessionaryinstance().getLoginApi().getUserKey().isEmpty()) {

                Project_USER_KEY = SessionaryJava.getSessionaryinstance().getLoginApi().getUserKey();
            } else {
                userDeleted(StartDashboardAnalytics.this);
            }
        } catch (Exception e) {
            userDeleted(StartDashboardAnalytics.this);
        }


        Planinapp(Project_INA_PURCHASE_CATEGORY_ID);
        Allplan();
        GetUserInfo();
        connectSocketTOServer();
        connectUserSocket();
        prepareInApp();


        BDG_CurrentPointupdate(
                "0.00000000",
                allbindData.currentBalance,
                digitViews1,
                StartDashboardAnalytics.this,
                getResources().getColor(R.color.black)
        );

        CurrentPointDigits("0.00000000", digitViews1);

        allbindData.txtConnecting.setVisibility(View.VISIBLE);

        BDG_StartbtnPoint(
                "",
                allbindData.txtStartNow,
                digitViews2,
                StartDashboardAnalytics.this,
                getResources().getColor(R.color.white)
        );

        StartbtnPointDigits("", digitViews2);

        allbindData.icmenu.setOnClickListener(v -> {
            allbindData.drawer.setVisibility(View.VISIBLE);
            allbindData.icmenu.setClickable(false);
            new Handler().postDelayed(() -> allbindData.icmenu.setClickable(true), 600);
            viewSideMenu(true);
            closefaq();
        });

        allbindData.btnclosebdg.setOnClickListener(v -> {
            allbindData.drawer.setVisibility(View.GONE);
            allbindData.btnclosebdg.setClickable(false);
            new Handler().postDelayed(() -> allbindData.btnclosebdg.setClickable(true), 600);
            sideMenuHide();
            closefaq();
        });

        allbindData.btnstart.setOnClickListener(v -> {
            selectType = BDG_COMPUTING;
            showItemClickAd();
            closefaq();
            if (miningResSocket != null && !miningResSocket.getIsMiningStart()) {
                allbindData.txtConnecting.setVisibility(View.VISIBLE);
                BDG_StartbtnPoint("", allbindData.txtStartNow, digitViews2, StartDashboardAnalytics.this,
                        getResources().getColor(R.color.white));
                StartbtnPointDigits("", digitViews2);
            }
        });

        allbindData.btnbooster.setOnClickListener(v -> {
            startActivity(new Intent(StartDashboardAnalytics.this, CaveMinePulse.class));
            closefaq();
        });


        allbindData.btnreferralbdg.setOnClickListener(v -> {
            selectType = BDG_REFER;
            showItemClickAd();
            closefaq();
            sideMenuHide();
        });

        allbindData.btnDashBoardbdg.setOnClickListener(v -> {
            selectType = BDG_DASHBOARD;
            showItemClickAd();
            closefaq();
            sideMenuHide();
        });

        allbindData.btnLogoutbdg.setOnClickListener(v -> {
            selectType = BDG_SIGN_OUT;
            showItemClickAd();
            closefaq();
            sideMenuHide();
        });

        allbindData.btnleaderboardbdg.setOnClickListener(v -> {
            selectType = BDG_LEADERS;
            showItemClickAd();
            closefaq();
            sideMenuHide();
        });

        allbindData.btnSettingbdg.setOnClickListener(v -> {
            selectType = BDG_SETTINGS;
            showItemClickAd();
            closefaq();
            sideMenuHide();
        });

        allbindData.btnBoosterPlanbdg.setOnClickListener(v -> {
            startActivity(new Intent(StartDashboardAnalytics.this, CaveMinePulse.class));
            sideMenuHide();
            closefaq();
        });

        allbindData.btnwalletbdg.setOnClickListener(v -> {
            selectType = BDG_WALLET;
            showItemClickAd();
            sideMenuHide();
            closefaq();
        });

        allbindData.btnManageProfilebdg.setOnClickListener(v -> {
            selectType = BDG_USER_DATA;
            showItemClickAd();
            sideMenuHide();
            closefaq();
        });

        allbindData.btnhistorybdg.setOnClickListener(v -> {
            selectType = BDG_COMPUTING_HIS;
            showItemClickAd();
            closefaq();
        });

        allbindData.mainfaq.setOnClickListener(v -> {
            if (allbindData.faqmainlay.getVisibility() == View.VISIBLE) {
                allbindData.faqmainlay.setVisibility(View.GONE);
                allbindData.imageView0.setImageResource(R.drawable.ic_dropdown);
                for (int i = 1; i <= 10; i++) {
                    int id1 = getResources().getIdentifier("des" + i, "id", getPackageName());
                    int id2 = getResources().getIdentifier("imageView" + i, "id", getPackageName());
                    TextView view1 = allbindData.getRoot().findViewById(id1);
                    ImageView view2 = allbindData.getRoot().findViewById(id2);
                    FaqcloseAll(view1, view2);
                }
            } else {
                allbindData.faqmainlay.setVisibility(View.VISIBLE);
                allbindData.imageView0.setImageResource(R.drawable.ic_dropup);
            }
        });

        allbindData.btnstartcomputing.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des1, allbindData.imageView1));

        allbindData.btncomputingwork.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des2, allbindData.imageView2));

        allbindData.btnreferafriend.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des3, allbindData.imageView3));

        allbindData.btnmineBTC.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des4, allbindData.imageView4));

        allbindData.btnrealBitcoin.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des5, allbindData.imageView5));

        allbindData.btnpaidcontract.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des6, allbindData.imageView6));

        allbindData.btnspendinganymoney.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des7, allbindData.imageView7));

        allbindData.btndropsuddenly.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des8, allbindData.imageView8));

        allbindData.btnlightningnetwork.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des9, allbindData.imageView9));

        allbindData.btnAPRcalculated.setOnClickListener(v ->
                FaqcheckVisiblity(allbindData.des10, allbindData.imageView10));


    }

    @Override
    protected void ModelSyncObserver() {
        StartDashboardModel.getUserinfoResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    HideSpin();
                    try {
                        try {
                            if (result.data.getMessage() != null) {
                                if ((result.data.getMessage().equals("User not found."))) {
                                    userDeleted(this);
                                    return;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (result.data.getData() != null) {
                            allbindData.register.setText("Hello, " + result.data.getData().getFirstName());
                            allbindData.txtUserName.setText(result.data.getData().getFirstName());
                            allbindData.txtUserEmail.setText(result.data.getData().getEmail());
                            SessionaryJava.getSessionaryinstance().SetUserInfo(result.data);
                        } else {
                            bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case NOMALYU:
                    HideSpin();
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });

        StartDashboardModel.getAllplanResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    HideSpin();
                    try {
                        if (result.data.getMessage() != null) {
                            if ((result.data.getMessage().equals("User not found."))) {
                                userDeleted(this);
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (result.data.getData() != null && result.data.getData() != null) {

                        SessionaryJava.getSessionaryinstance().saveAllPlans(result.data.getData());

                        getCurrentPlanApi(); // Assuming currentPlanApi is a variable or method call

                    } else {
                        HideSpin();
                        Project_CURRENT_PLAN_SHOW = "Free Plan";
                        ProjectConstantsJava.Project_CURRENT_PLAN = Project_SET_FREE_PLAN;
                        allbindData.currentPlanName.setText(Project_CURRENT_PLAN_SHOW);
                        bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    }
                    break;

                case NOMALYU:
                    HideSpin();
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });

        StartDashboardModel.getplanResponse().observe(this, result -> {
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

                    if (result.data.getData() != null) {
                        showCurrentPlan(result.data.getData().getPlanId());
                        try {
                            BigDecimal result1 = new BigDecimal(result.data.getData().getHasRateSpeed());
                            BigDecimal result3 = result1.divide(new BigDecimal("100000000"), 8, RoundingMode.FLOOR)
                                    .abs();
                            BigDecimal secondsInMinute = new BigDecimal(60);
                            BigDecimal minutesInHour = new BigDecimal(60);
                            BigDecimal hoursInDay = new BigDecimal(24);
                            BigDecimal estimatedEarning = result3.multiply(secondsInMinute).multiply(minutesInHour).multiply(hoursInDay);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            setPlanExpireDate(
                                    result.data.getData().getPlanId().toString(),
                                    result.data.getData().getEndTimeTimestamp()
                            );
                            String entTime = result.data.getData().getEndTime();
                            long EndTimeCount = Long.parseLong(entTime);
                            long planExpireTimeDifferent =
                                    TimeUnit.MILLISECONDS.toSeconds(EndTimeCount - System.currentTimeMillis());
                            IS_PLAN_EXPIRE_BDG = planExpireTimeDifferent < 0;

                        } catch (Exception e) {
                            e.printStackTrace();
                            // Assuming FirebaseCrashlytics is available in your Java environment
                            FirebaseCrashlytics.getInstance().recordException(e);
                        }

                        try {
                            Current_plan_bdg = result.data.getData().getPlanId();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Project_CURRENT_PLAN_SHOW = "Free Plan";
                        ProjectConstantsJava.Project_CURRENT_PLAN = Project_SET_FREE_PLAN;
                        allbindData.currentPlanName.setText(Project_CURRENT_PLAN_SHOW);

                        try {
                            if (result.data.getMessage() != null) {
                                if ((result.data.getMessage().equals("User not found."))) {
                                    userDeleted(this);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case NOMALYU:

                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });

        StartDashboardModel.getaddplanResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
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
                        getCurrentPlanApi();
                    }
                    break;

                case NOMALYU:

                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });

        StartDashboardModel.getlogoutResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    HideSpin();
                    try {
                        if (result.data.getMessage() != null) {
                            if ("User not found.".equals(result.data.getMessage())) {
                                userDeleted(this);
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (Boolean.TRUE.equals(result.data.getStatus())) {
                        ProjectConstantsJava.Project_CURRENT_POINT = 0.00000000;
                        SessionaryJava.getSessionaryinstance().setUserLogOut(true);
                        SessionaryJava.getSessionaryinstance().SetLoginApi(null);
                        SessionaryJava.getSessionaryinstance().SetUserInfo(null);
                        try {
                            Toast.makeText(this, "Logout Successfully.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(this, SecureLoginJava.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        try {
                            Toast.makeText(this, "Something went wrong. Please try again later.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
        banner = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(banner, StartDashboardAnalytics.this);
        showInterstitialsAds(StartDashboardAnalytics.this, this);
    }

    private void Planinapp(int category) {
        planDetailsListInApp.clear();
        inAppPlansDataArray.clear();
        try {
            if (!SessionaryJava.getSessionaryinstance().getAllPlan().isEmpty()) {
                for (int j = 0; j < SessionaryJava.getSessionaryinstance().getAllPlan().size(); j++) {
                    if (SessionaryJava.getSessionaryinstance().getAllPlan().get(j).getCategory().equals(String.valueOf(category))) {
                        inAppPlansDataArray.add(SessionaryJava.getSessionaryinstance().getAllPlan().get(j));
                        planDetailsListInApp.add(SessionaryJava.getSessionaryinstance().getAllPlan().get(j));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCurrentPlanApi() {
        StartDashboardModel.GetplanResponseapi(new GetPlanreq(Project_USER_KEY, Project_APP_ID));
    }

    public void Allplan() {
        StartDashboardModel.AllplanResponseapi(new Allplanreq(Project_APP_ID));
    }

    private void GetUserInfo() {
        StartDashboardModel.GetUserInfoApibdg(new GetUserinforeq(Project_USER_KEY, Project_APP_ID));
    }

    private void Logout(boolean result) {
        if (result) {
            StartDashboardModel.LogoutResponseapi(new GetUserinforeq(Project_USER_KEY, Project_APP_ID));
        }
    }

    private void showCurrentPlan(int planId) {
        if (SessionaryJava.getSessionaryinstance().getAllPlan() != null && !SessionaryJava.getSessionaryinstance().getAllPlan().isEmpty()) {
            for (int i = 0; i < SessionaryJava.getSessionaryinstance().getAllPlan().size(); i++) {
                if (SessionaryJava.getSessionaryinstance().getAllPlan().get(i) != null && SessionaryJava.getSessionaryinstance().getAllPlan().get(i).getId() == planId) {
                    Project_CURRENT_PLAN_SHOW = SessionaryJava.getSessionaryinstance().getAllPlan().get(i).getPlanName();
                    allbindData.currentPlanName.setText(Project_CURRENT_PLAN_SHOW);
                    ProjectConstantsJava.Project_CURRENT_PLAN = SessionaryJava.getSessionaryinstance().getAllPlan().get(i).getId();
                    break; // Exit the loop once the plan is found
                }
            }
        }
    }

    public void setPlanExpireDate(String planId, String endTime) {
        try {
            if ("77".equals(planId)) {
                allbindData.expirydates.setVisibility(View.GONE);
            } else {
                allbindData.expirydates.setVisibility(View.VISIBLE);

                String inputDateStr = endTime;
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date inputDate = inputDateFormat.parse(inputDateStr);

                SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMM yyyy");
                String outputDateStr = outputDateFormat.format(inputDate);

                String inputDateStr1 = endTime;
                SimpleDateFormat inputDateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                inputDateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date inputDate1 = inputDateFormat1.parse(inputDateStr1);

                SimpleDateFormat outputTimeFormat = new SimpleDateFormat("hh:mm a");
                outputTimeFormat.setTimeZone(TimeZone.getDefault());
                String outputTimeStr = outputTimeFormat.format(inputDate1);

                allbindData.textExpireDate.setText(outputDateStr + " , " +
                        outputTimeStr.replace("am", "AM").replace("pm", "PM"));
            }
        } catch (Exception e) {
            allbindData.expirydates.setVisibility(View.GONE);
        }
    }

    private void showItemClickAd() {
        BtcAllInterstitialAdsLib.showItemClickAd(StartDashboardAnalytics.this, this);
    }

    @Override
    public void gotoNext() {
        switch (selectType) {
            case BDG_COMPUTING:
                if (miningResSocket != null && !miningResSocket.getIsMiningStart()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.add(Calendar.HOUR, 24);
                    try {
                        UserInteractionStatsJava.reminder(
                                StartDashboardAnalytics.this,
                                calendar.getTimeInMillis(),
                                (int) System.currentTimeMillis(),
                                "Start BTC Mining Now 🚀"
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    startMiningSocket();
                }
                break;

            case BDG_DASHBOARD:
                allbindData.drawer.setVisibility(View.GONE);
                break;

            case BDG_COMPUTING_HIS:
                startActivity(new Intent(StartDashboardAnalytics.this, DayVault.class));
                break;

            case BDG_SETTINGS:
                startActivity(new Intent(StartDashboardAnalytics.this, OptixZonePanel.class));
                break;

            case BDG_WALLET:
                Intent intent2 = new Intent(StartDashboardAnalytics.this, Fund.class);
                intent2.putExtra("CurrentPoint", miningResSocket.getMining());
                startActivity(intent2);
                break;

            case BDG_LEADERS:
                startActivity(new Intent(StartDashboardAnalytics.this, GloryRollRank.class));
                break;

            case BDG_USER_DATA:
                startActivity(new Intent(StartDashboardAnalytics.this, UserConsoleCenter.class));
                break;

            case BDG_SIGN_OUT:
                if (UserInteractionStatsJava.validContext(StartDashboardAnalytics.this)) {
                    FragmentManager fm = getSupportFragmentManager();
                    if (fm != null) {
                        UniversalDialogJava baseDialog = new UniversalDialogJava(
                                STATUS_SESSION_TERMINATED,
                                StartDashboardAnalytics.this,
                                returnValue -> Logout(returnValue)
                        );
                        baseDialog.setStyle(DialogFragment.STYLE_NORMAL,
                                android.R.style.Theme_Light_NoTitleBar_Fullscreen);
                        baseDialog.show(fm, UniversalDialogJava.class.getName());
                    }
                }
                break;

            case BDG_REFER:
                startActivity(new Intent(StartDashboardAnalytics.this, ShareTheBuddy.class));
                break;
        }
    }

    private Handler getHndlrInstance() {
        if (handler1 == null) {
            return new Handler(Looper.getMainLooper());
        } else {
            return handler1;
        }
    }

    private void sideMenuHide() {
        if (!isDisable) {
            viewSideMenu(false);
            isDisable = true;
            getHndlrInstance().postDelayed(() -> isDisable = false, 100);
        }
    }

    private void viewSideMenu(boolean isLayer) {
        Animation showMenu = AnimationUtils.loadAnimation(this, R.anim.show_menu);
        Animation hideMenu = AnimationUtils.loadAnimation(this, R.anim.hide_menu);

        try {
            if (isLayer) {
                sideMenu = true;
                allbindData.drawer.startAnimation(showMenu);
            } else {
                sideMenu = false;
                allbindData.drawer.startAnimation(hideMenu);
                allbindData.drawer.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void FaqcheckVisiblity(View tvData, ImageView arrowView) {
        if (tvData.getVisibility() == View.VISIBLE) {
            tvData.setVisibility(View.GONE);
            arrowView.setImageDrawable(getResources().getDrawable(R.drawable.ic_dropdown));
        } else {
            tvData.setVisibility(View.VISIBLE);
            arrowView.setImageDrawable(getResources().getDrawable(R.drawable.ic_dropup));
        }
    }

    private void FaqcloseAll(View id, ImageView idd) {
        id.setVisibility(View.GONE);
        idd.setImageDrawable(getResources().getDrawable(R.drawable.ic_dropdown));
    }

    public void closefaq() {
        for (int i = 1; i <= 10; i++) {
            int id1 = getResources().getIdentifier("des" + i, "id", getPackageName());
            int id2 = getResources().getIdentifier("imageView" + i, "id", getPackageName());
            TextView view1 = allbindData.getRoot().findViewById(id1);
            ImageView view2 = allbindData.getRoot().findViewById(id2);
            FaqcloseAll(view1, view2);
            allbindData.faqmainlay.setVisibility(View.GONE);
            allbindData.imageView0.setImageDrawable(getResources().getDrawable(R.drawable.ic_dropdown));
        }
    }

    /***************************
     ***** Socket Start *****
     ***************************/

    private Gson getGsonInstance() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .serializeSpecialFloatingPointValues()
                    .create();
        }
        return gson;
    }

    /**
     * Connect socket for mining data
     */
    private void connectSocketTOServer() {
        IO.Options options = new IO.Options();
        options.transports = new String[]{WebSocket.NAME};
        options.secure = true;
        options.forceNew = true;
        options.reconnection = true;
        options.reconnectionDelay = 1000;
        options.reconnectionAttempts = 10;
        options.multiplex = true;


        try {
            mSocket = IO.socket(URI.create(Project_SOCKET_URL), options);
            mSocket.disconnect().connect();
            mSocket.on(Socket.EVENT_CONNECT, onConnect);
            mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect);
            mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnect socket
     */
    private void removeConnection() {
        if (mSocket != null) {
            mSocket.disconnect();
        }
    }

    /**
     * Emit user socket event
     */
    private void connectUserSocket() {
        if (mSocket != null) {
            mSocket.emit(getString(R.string.user_connect), Project_USER_KEY, Project_APP_ID)
                    .on(getString(R.string.mine), userConnect);
        }
    }

    /**
     * Start mining socket
     */
    private void startMiningSocket() {
        if (mSocket != null) {
            mSocket.emit(getString(R.string.mining_start), Project_USER_KEY, Project_APP_ID)
                    .on(getString(R.string.mining_start), userConnect_Start);
        }
    }

    /*******************************************
     ***** IN-App Purchase New Flow Start ******
     *******************************************/

    private void prepareInApp() {
        Log.i(TAG, "prepareInApp: ");
        if (SessionaryJava.getSessionaryinstance().getAllPlan() != null && !SessionaryJava.getSessionaryinstance().getAllPlan().isEmpty()) {
            planDetailsList.clear();
            productIds.clear();

            for (BDG_PlanData planItem : SessionaryJava.getSessionaryinstance().getAllPlan()) {
                if (planItem != null && Integer.parseInt(planItem.getCategory()) == Project_INA_PURCHASE_CATEGORY_ID) {
                    planDetailsList.add(planItem);
                    productIds.add(String.valueOf(planItem.getPlanId()));
                }
            }

            for (BDG_PlanData planData : planDetailsList) {
                planData.setPriceInApp(planData.getPrice());
            }
        }

        if (!productIds.isEmpty()) {
            Log.i(TAG, "onCreate: " + productIds);
            BDG_Utils.getUtilsInstance().setAppName(appNAME)
                    .prepareProductIdList(productIds)
                    .setBillingUpdatesListener(this, this);
        }
    }
    /***************************
     ******* Socket End *******
     ***************************/

    @Override
    public void BDG_onBillingClientRetryFailed(int retryComeFrom) {
        switch (retryComeFrom) {
            case 0:
                Log.i(TAG, ">>> retryComeFrom <<< : QUERY_INVENTORY");
                break;
            case 1:
                Log.i(TAG, ">>> retryComeFrom <<< : QUERY_PURCHASE");
                break;
            case 2:
                Log.i(TAG, "<<< retryComeFrom >>>: NONE");
                break;
        }
    }
    @Override
    public void BDG_onQueryPurchasesFailed(String message) {
        bothsnackBars(allbindData.getRoot(), message, 1);
    }private final Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(() -> {
                try {
                    allbindData.txtConnecting.setVisibility(View.VISIBLE);
                    BDG_StartbtnPoint("", allbindData.txtStartNow, digitViews2, StartDashboardAnalytics.this,
                            getResources().getColor(R.color.white));
                    StartbtnPointDigits("", digitViews2);

                    removeConnection();
                    connectSocketTOServer();
                    connectUserSocket();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    };

    @Override
    public void BDG_onSuccessesPurchasesResponse(Purchase purchase, int plan_id) {
        if (UserInteractionStatsJava.isValidContext(this)) {
            runOnUiThread(() -> {
                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        if (setCurrentPlan) {
                            setAutoPurchaseUser(plan_id);
                            return;
                        }
                        handler.postDelayed(this, 100);
                    }
                };
                handler.postDelayed(r, 100);
            });
        }
    }

    @Override
    public void BDG_onPurchaseFlowLaunchingFailed(String message) {
        Log.i(TAG, ">>> onPurchaseFlowLaunchingFailed <<< : msg : -> " + message);
    }

    @Override
    public void BDG_onConsumeFinished(String token, int result) {
        Log.i(TAG, "onConsumeFinished: ");
    }

    @Override
    public void BDG_onConsumeFailed(String message) {
        Log.i(TAG, "onConsumeFailed: ");
    }

    @Override
    public void BDG_onProductDetailsResponse(ArrayList<List<ProductDetails>> productDetailsList) {
        Log.i(TAG, "onProductDetailsResponse: >>> productDetailsList " + productDetailsList);
        if (productDetailsList != null) {
            for (int i = 0; i < productDetailsList.size(); i++) {
                IS_IN_INDIA = "INR".equals(
                        productDetailsList.get(0).get(i).getSubscriptionOfferDetails().get(0)
                                .getPricingPhases().getPricingPhaseList().get(0).getPriceCurrencyCode()
                );
                Log.i(TAG, "onProductDetailsResponse: >>> IS_IN_INDIA " + IS_IN_INDIA);
            }
            updateUserSubsPriceByCurrency(productDetailsList);
        }
    }

    @Override
    public void BDG_onProductDetailsFailed(String message) {
        Log.i(TAG, "onProductDetailsFailed: " + message);
    }

    @Override
    public void BDG_onMiningPriceChangeConfirmationResult(BillingResult billingResult, SkuDetails skuDetails) {
        Log.i(TAG, "onMiningPriceChangeConfirmationResult: ");
    }

    @Override
    public void BDG_onMiningPriceChangeConfirmationFailed(String message) {
        Log.i(TAG, "onMiningPriceChangeConfirmationFailed: ");
    }

    private void updateUserSubsPriceByCurrency(ArrayList<List<ProductDetails>> productDetailsList) {
        if (productDetailsList != null && productDetailsList.size() == productIds.size()) {
            for (int i = 0; i < productDetailsList.size(); i++) {
                for (BDG_PlanData planData : planDetailsList) {
                    if (planData.getPlanId().equals(productDetailsList.get(i).get(0).getProductId())) {
                        List<ProductDetails.PricingPhase> phases = productDetailsList.get(i).get(0)
                                .getSubscriptionOfferDetails().get(0)
                                .getPricingPhases().getPricingPhaseList();

                        String originalPrice = phases.get(phases.size() - 1).getFormattedPrice();
                        planData.setPriceInApp(originalPrice.replace(".00", ""));
                        break;
                    }
                }
            }
        }
    }

    private void setAutoPurchaseUser(int planIdIndex) {
        try {
            if (!planDetailsList.isEmpty()) {
                BDG_PlanData selectedPlan = planDetailsList.get(planIdIndex);
                if (Current_plan != selectedPlan.getId()) {
                    SetCurrentPlan(selectedPlan.getId());
                } else if (IS_PLAN_EXPIRE) {
                    SetCurrentPlan(selectedPlan.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }

    private void SetCurrentPlan(int planId) {
        StartDashboardModel.AddPlanResponseapi(new Addplanreq(SessionaryJava.getSessionaryinstance().getLoginApi().getUserKey(), Project_APP_ID, planId));
    }

    @Override
    public void BDG_onBillingClientSetupFinished() {
        BDG_BillingUpdatesListener.super.BDG_onBillingClientSetupFinished();
    }

    @Override
    protected void onResume() {
        super.onResume();
        allbindData.currentPlanName.setText(Project_CURRENT_PLAN_SHOW);
        try {
            if (SessionaryJava.getSessionaryinstance() != null && SessionaryJava.getSessionaryinstance().getUserInfo() != null
                    && SessionaryJava.getSessionaryinstance().getUserInfo().getFirstName() != null) {
                firstName = SessionaryJava.getSessionaryinstance().getUserInfo().getFirstName();
            }
            if (SessionaryJava.getSessionaryinstance() != null && SessionaryJava.getSessionaryinstance().getUserInfo() != null
                    && SessionaryJava.getSessionaryinstance().getUserInfo().getEmail() != null) {
                email = SessionaryJava.getSessionaryinstance().getUserInfo().getEmail();
            }
            allbindData.txtUserName.setText(firstName);
            allbindData.register.setText("Hello, " + firstName);
            allbindData.txtUserEmail.setText(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("InlinedApi")
    private void requestNotification() {
        ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean isGranted) {
                if (isGranted) {
                    Toast.makeText(StartDashboardAnalytics.this, "Notification permission granted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        requestPermissionLauncher.launch(POST_NOTIFICATIONS);
    }



    @Override
    public void onBackPressed() {

        if (sideMenu) {
            viewSideMenu(false);
            return;
        }

        if (userDoubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }
        closefaq();
        userDoubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                userDoubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }
}