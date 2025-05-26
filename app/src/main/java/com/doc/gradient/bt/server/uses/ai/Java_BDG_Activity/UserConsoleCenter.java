package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_APP_ID;
import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_USER_KEY;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModelProvider;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.MainViewModel.AppStateViewModel;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfo.GetUserinforeq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Responce_Class.BDG_UserInfoEdit.Getusereditreq;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdCallBack;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdsLib;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityUserConsoleCenterBinding;
import com.yesterselga.countrypicker.CountryPicker;
import com.yesterselga.countrypicker.CountryPickerListener;
import com.yesterselga.countrypicker.Theme;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserConsoleCenter extends BaseCoreParentJava<ActivityUserConsoleCenterBinding> implements BtcAllInterstitialAdCallBack {

    private static final int BDG_UPDATE = 1;
    private AutoConnectNetwork autoconnectnetwork = null;
    private LinearLayout hideView = null;
    private int selectType = 0;

    private AppStateViewModel vModel;

    @Override
    protected ActivityUserConsoleCenterBinding DataBridge() {
        return ActivityUserConsoleCenterBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void NetBridge() {
        autoconnectnetwork = new AutoConnectNetwork(this);
        autoconnectnetwork.observe(UserConsoleCenter.this, hasConnection -> {
            if (!hasConnection) {
                noDataDialog();
            } else {
                hideDataDialog();
                getUserInfo();
            }
        });

    }

    @Override
    protected void RecallBindingProcess() {
        vModel = new ViewModelProvider(this).get(AppStateViewModel.class);
        AppStatusBarjava(this);
        ShowSpin(this);

        allbindData.loader.loop(true);
        allbindData.loader.setAnimation(R.raw.loader);
        allbindData.loader.playAnimation();

        allbindData.btnbackbdg.setOnClickListener(v -> finish());

        allbindData.btnUpdatebdg.setOnClickListener(v -> {
            selectType = BDG_UPDATE;
            showUserItemClickAd();
            View view = getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        allbindData.countrylay.setOnClickListener(v -> {
            try {
                CountryPicker picker = CountryPicker.newInstance("Select Country", Theme.DARK);
                picker.setListener(new CountryPickerListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                        allbindData.Countrytext.setText(name);
                        picker.dismiss();
                    }
                });
                picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void ModelSyncObserver() {
        vModel.getUserinfoResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    HideSpin();
                    try {
                        if (result.data.getData() != null && result.data.getMessage() != null) {
                            if (result.data.getMessage().equals("User not found.")) {
                                userDeleted(this);
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (result.data != null && result.data.getData() != null) {
                        allbindData.FullNameedittext.setText(result.data.getData().getFirstName());
                        allbindData.Emailidedittext.setText(result.data.getData().getEmail());
                        allbindData.Countrytext.setText(result.data.getData().getCountry());
                        SessionaryJava.getSessionaryinstance().SetUserInfo(result.data);
                    } else {
                        bothsnackBars(allbindData.getRoot(), "Something Went to Wrong", 1);
                    }
                    break;

                case NOMALYU:
                    HideSpin();
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });


        vModel.getUserInfoEditResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:
                    allbindData.btnUpdatebdg.setVisibility(View.GONE);
                    allbindData.loader.setVisibility(View.VISIBLE);
                    break;
                case MALYU:
                    HideSpin();
                    allbindData.btnUpdatebdg.setVisibility(View.VISIBLE);
                    allbindData.loader.setVisibility(View.GONE);
                    if (result.data != null && result.data.getData() != null) {
                        bothsnackBars(allbindData.getRoot(), "Your Information is Updated", 2);
                        getUserInfo1();
                        allbindData.FullNameedittext.setText(result.data.getData().getFirstName());
                        allbindData.Emailidedittext.setText(result.data.getData().getEmail());
                        allbindData.Countrytext.setText(result.data.getData().getCountry());
                    } else {
                        bothsnackBars(allbindData.getRoot(), "Something Went to Wrong", 1);
                    }
                    break;

                case NOMALYU:
                    HideSpin();
                    allbindData.btnUpdatebdg.setVisibility(View.VISIBLE);
                    allbindData.loader.setVisibility(View.GONE);
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });
    }

    @Override
    protected void viewAds() {
        hideView = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(hideView, UserConsoleCenter.this);
        showInterstitialsAds(UserConsoleCenter.this, this);
    }

    private void showUserItemClickAd() {
        BtcAllInterstitialAdsLib.showItemClickAd(UserConsoleCenter.this, this);
    }

    @Override
    public void gotoNext() {
        getUserInfoEdit();
    }

    private void getUserInfo1() {
        vModel.GetUserInfoApibdg(new GetUserinforeq(Project_USER_KEY, Project_APP_ID));

        vModel.getUserinfoResponse().observe(this, result -> {
            if (result == null)
                return;
            switch (result.status) {
                case RAHJOVO:

                    break;
                case MALYU:
                    try {
                        if (result.data != null && result.data.getMessage() != null) {
                            if (result.data.getMessage().equals("User not found.")) {
                                userDeleted(this);
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (result.data != null && result.data.getData() != null) {
                        SessionaryJava.getSessionaryinstance().SetUserInfo(result.data);
                    }
                    break;

                case NOMALYU:
                    bothsnackBars(allbindData.getRoot(), "Something went wrong please try again", 1);
                    break;
            }
        });
    }

    private void getUserInfo() {
        vModel.GetUserInfoApibdg(new GetUserinforeq(Project_USER_KEY, Project_APP_ID));
    }

    private void getUserInfoEdit() {
        String userName = allbindData.FullNameedittext.getText().toString().trim();
        String Countrytext = allbindData.Countrytext.getText().toString().trim();
        if (userName.isEmpty()) {
            bothsnackBars(allbindData.getRoot(), "Please Enter Name", 1);
        } else {
            vModel.UserInfoEditApi(new Getusereditreq(Project_USER_KEY, userName, Project_APP_ID,Countrytext));
        }
    }
}