package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.lifecycle.Observer;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivitySafeDataInfoBinding;

public class SafeDataInfo extends BaseCoreParentJava<ActivitySafeDataInfoBinding> {
    private AutoConnectNetwork autoconnectnetwork = null;
    private String SafeDatastatus = "";
    private String SafeDataprivacyPolicy = "";
    private String SafeDatatermsAndCondition = "";
    private String SafeDataDeletionPolicy = "";
    private LinearLayout SafeDatabannerview = null;

    @Override
    protected ActivitySafeDataInfoBinding DataBridge() {
        return ActivitySafeDataInfoBinding.inflate(getLayoutInflater());
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

        if (SessionaryJava.getSessionaryinstance().getAppSettingApi() != null &&
                SessionaryJava.getSessionaryinstance().getAppSettingApi().getPrivacyPolicy() != null &&
                !SessionaryJava.getSessionaryinstance().getAppSettingApi().getPrivacyPolicy().isEmpty()) {
            SafeDataprivacyPolicy = SessionaryJava.getSessionaryinstance().getAppSettingApi().getPrivacyPolicy();
        }

        if (SessionaryJava.getSessionaryinstance().getAppSettingApi() != null &&
                SessionaryJava.getSessionaryinstance().getAppSettingApi().getTermsAndCondition() != null &&
                !SessionaryJava.getSessionaryinstance().getAppSettingApi().getTermsAndCondition().isEmpty()) {
            SafeDatatermsAndCondition = SessionaryJava.getSessionaryinstance().getAppSettingApi().getTermsAndCondition();
        }

        if (SessionaryJava.getSessionaryinstance().getAppSettingApi() != null &&
                SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson() != null &&
                SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getDataDeletionUrl() != null &&
                !SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getDataDeletionUrl().isEmpty()) {
            SafeDataDeletionPolicy = SessionaryJava.getSessionaryinstance().getAppSettingApi().getJson().getDataDeletionUrl();
        }

        SafeDatastatus = null;
        SafeDatastatus = getIntent().getStringExtra("Flag");

        if ("Privacy".equals(SafeDatastatus) && SafeDatastatus != null && !SafeDatastatus.isEmpty()) {
            allbindData.titlebdg.setText("Privacy Policy");
        } else if ("Condition".equals(SafeDatastatus) && SafeDatastatus != null && !SafeDatastatus.isEmpty()) {
            allbindData.titlebdg.setText("Term & Conditions");
        } else if ("DeletePolicy".equals(SafeDatastatus) && SafeDatastatus != null && !SafeDatastatus.isEmpty()) {
            allbindData.titlebdg.setText("Deletion Policy");
        }

        allbindData.webViewbdg.setVisibility(View.GONE);
        allbindData.WebViewProgressBar.setVisibility(View.VISIBLE);

        WebSettings webSettings = allbindData.webViewbdg.getSettings();
        webSettings.setJavaScriptEnabled(true);
        allbindData.webViewbdg.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        allbindData.webViewbdg.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                allbindData.WebViewProgressBar.setVisibility(View.GONE);
                allbindData.webViewbdg.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });

        if (SessionaryJava.getSessionaryinstance().getAppSettingApi() != null &&
                !SessionaryJava.getSessionaryinstance().getAppSettingApi().toString().isEmpty()) {
            if (SafeDataprivacyPolicy != null && !SafeDataprivacyPolicy.isEmpty() && "Privacy".equals(SafeDatastatus)) {
                allbindData.webViewbdg.loadUrl(SafeDataprivacyPolicy);
            } else if (SafeDatatermsAndCondition != null && !SafeDatatermsAndCondition.isEmpty() && "Condition".equals(SafeDatastatus)) {
                allbindData.webViewbdg.loadUrl(SafeDatatermsAndCondition);
            } else if (SafeDataDeletionPolicy != null && !SafeDataDeletionPolicy.isEmpty() && "DeletePolicy".equals(SafeDatastatus)) {
                allbindData.webViewbdg.loadUrl(SafeDataDeletionPolicy);
            }
        }

        allbindData.btnbackbdg.setOnClickListener(v -> finish());

    }

    @Override
    protected void ModelSyncObserver() {

    }

    @Override
    protected void viewAds() {
        SafeDatabannerview = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(SafeDatabannerview, SafeDataInfo.this);
    }
}
