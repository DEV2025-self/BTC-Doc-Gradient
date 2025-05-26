package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_RATE_APP_LINK;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_USER_KEY;

import android.content.Intent;
import android.net.Uri;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.UniversalDialogJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_DeleteUser.Deletereq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdCallBack;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdsLib;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityOptixZonePanelBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OptixZonePanel extends BaseCoreParentJava<ActivityOptixZonePanelBinding> implements BtcAllInterstitialAdCallBack {

    static final int HELP_REACH_OUT = 4;
    static final int LEGAL_PRIVACY_DOC = 7;
    static final int SPREAD_APP = 8;
    static final int STAR_DROP = 9;
    static final int ACCOUNT_REMOVAL = 10;
    AppStateViewModel OptixvModel;
    String OptixfirstName = "";
    String Optixemail = "";
    LinearLayout Optixbanner = null;
    int OptixselectType = 0;
    AutoConnectNetwork autoconnectnetwork = null;

    @Override
    protected ActivityOptixZonePanelBinding DataBridge() {
        return ActivityOptixZonePanelBinding.inflate(getLayoutInflater());
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
        OptixvModel = new ViewModelProvider(this).get(AppStateViewModel.class);
        AppStatusBarjava(this);
        try {
            if (SessionaryJava.getSessionaryinstance() != null && SessionaryJava.getSessionaryinstance().getUserInfo() != null
                    && SessionaryJava.getSessionaryinstance().getUserInfo().getFirstName() != null) {
                OptixfirstName = SessionaryJava.getSessionaryinstance().getUserInfo().getFirstName();
            }
            if (SessionaryJava.getSessionaryinstance() != null && SessionaryJava.getSessionaryinstance().getUserInfo() != null
                    && SessionaryJava.getSessionaryinstance().getUserInfo().getEmail() != null) {
                Optixemail = SessionaryJava.getSessionaryinstance().getUserInfo().getEmail();
            }
            allbindData.txtUserName.setText(OptixfirstName);
            allbindData.txtUserEmail.setText(Optixemail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView marqueeText = findViewById(R.id.txtUserName);
        marqueeText.setSelected(true);
        TextView marqueeText1 = findViewById(R.id.txtUserEmail);
        marqueeText1.setSelected(true);

        allbindData.btnfaqsbdg.setOnClickListener(v -> {
            OptixselectType = HELP_REACH_OUT;
            showItemClickAd();
        });
        allbindData.btnPrivacyPolicybdg.setOnClickListener(v -> {
            OptixselectType = LEGAL_PRIVACY_DOC;
            showItemClickAd();
        });
        allbindData.btnShareAppbdg.setOnClickListener(v -> {
            OptixselectType = SPREAD_APP;
            showItemClickAd();
        });
        allbindData.btnSRateAppbdg.setOnClickListener(v -> {
            OptixselectType = STAR_DROP;
            showItemClickAd();
        });
        allbindData.btnDeleteAccountbdg.setOnClickListener(v -> {
            OptixselectType = ACCOUNT_REMOVAL;
            showItemClickAd();
        });
        allbindData.btnbackbdg.setOnClickListener(v -> finish());
    }

    @Override
    protected void ModelSyncObserver() {
        OptixvModel.GetDeleteUserbdg().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
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

                    if (result.data != null && Boolean.TRUE.equals(result.data.getStatus())) {
                        ProjectConstantsJava.Project_CURRENT_POINT = 0.00000000;
                        try {
                            Toast.makeText(
                                    this,
                                    "User Account Delete SuccessFully",
                                    Toast.LENGTH_SHORT
                            ).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ProjectConstantsJava.Project_CURRENT_POINT = 0.00000000;
                        SessionaryJava.getSessionaryinstance().setUserLogOut(true);
                        SessionaryJava.getSessionaryinstance().SetLoginApi(null);
                        SessionaryJava.getSessionaryinstance().SetUserInfo(null);
                        Intent intent = new Intent(this, SecureLoginJava.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        try {
                            Toast.makeText(
                                    this,
                                    result.data.getMessage(),
                                    Toast.LENGTH_SHORT
                            ).show();
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
        Optixbanner = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(Optixbanner, OptixZonePanel.this);
        showInterstitialsAds(OptixZonePanel.this, this);
    }

    @Override
    public void gotoNext() {
        switch (OptixselectType) {
            case SPREAD_APP:
                UserInteractionStatsJava.appShare(
                        this,
                        "",
                        "Share BaseClass",
                        "Share with.."
                );
                break;

            case STAR_DROP:
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(Project_RATE_APP_LINK + getPackageName()));
                startActivity(i1);
                break;

            case HELP_REACH_OUT:
                startActivity(new Intent(this, ClarityQuest.class));
                break;

            case ACCOUNT_REMOVAL:
                if (UserInteractionStatsJava.validContext(OptixZonePanel.this)) {
                    FragmentManager fm = getSupportFragmentManager();
                    if (fm != null) {
                        UniversalDialogJava deleteDialog = new UniversalDialogJava(STATUS_ACCOUNT_REMOVED, OptixZonePanel.this, returnValue -> {
                            Delete(returnValue);
                        });
                        deleteDialog.setStyle(
                                DialogFragment.STYLE_NORMAL,
                                android.R.style.Theme_Light_NoTitleBar_Fullscreen
                        );
                        deleteDialog.show(fm, UniversalDialogJava.class.getName());
                    }
                }
                break;

            case LEGAL_PRIVACY_DOC:
                Intent intent = new Intent(this, SafeDataInfo.class);
                intent.putExtra("Flag", "Privacy");
                startActivity(intent);
                break;
        }
    }

    private void showItemClickAd() {
        BtcAllInterstitialAdsLib.showItemClickAd(OptixZonePanel.this, this);
    }

    public void Delete(boolean result1) {
        if (result1) {
            OptixvModel.GetDeleteUserApibdg(new Deletereq(Project_USER_KEY, Project_APP_ID));
        }
    }
}
