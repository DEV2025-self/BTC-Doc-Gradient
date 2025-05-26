package com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity;

import static com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.ProjectConstantsJava.Project_SHARE_LINK;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.AutoConnectNetwork;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase.BaseCoreParentJava;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdCallBack;
import com.doc.gradient.bt.server.uses.ai.ads.BtcInterstitialAds.BtcAllInterstitialAdsLib;
import com.doc.gradient.bt.server.uses.ai.databinding.ActivityShareTheBuddyBinding;

public class ShareTheBuddy extends BaseCoreParentJava<ActivityShareTheBuddyBinding> implements BtcAllInterstitialAdCallBack {
    static final int BDG_BTN_SHARE = 3;
    static final int BDG_BTN_HISTORY = 2;
    static final int BDG_WHATSAPP = 4;
    static final int BDG_INSTRAGRAM = 5;
    static final int BDG_FACEBOOK = 6;
    static final int BDG_TELEGRAM = 7;
    int selectionType = 0;
    LinearLayout bannerview = null;
    AutoConnectNetwork autoConnectNetwork = null;

    @Override
    protected ActivityShareTheBuddyBinding DataBridge() {
        return ActivityShareTheBuddyBinding.inflate(getLayoutInflater());
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
        AppStatusBarjava(this);

        try {
            if (SessionaryJava.getSessionaryinstance().getUserInfo() != null) {
                long value = SessionaryJava.getSessionaryinstance().getAppSettingApi().getSenderRewardPoint();
                float floatValue = value / 100000000.0f;
                String formattedValue = String.format("%.8f", floatValue);
                allbindData.refertext1.setText("You and your friend will get " + formattedValue + " free BTC Coin when your friends join BTC mining Community");
                allbindData.ictxtYourReferralCodebdg.setText(SessionaryJava.getSessionaryinstance().getUserInfo().getReferralCode());
            } else {
                allbindData.refertext1.setText("You and your friend will get 0.00000100 free BTC Coin when your friends join BTC mining Community");
            }
        } catch (Exception e) {
            allbindData.refertext1.setText("You and your friend will get 0.00000100  free BTC Coin when your friends join BTC mining Community");
        }


        allbindData.iccopybdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(
                        "Copy",
                        allbindData.ictxtYourReferralCodebdg.getText().toString()
                );
                clipboard.setPrimaryClip(clip);
                if (clip != null) {
                    Toast toast = Toast.makeText(
                            getApplicationContext(),
                            "Referral Code Copy!!", Toast.LENGTH_SHORT
                    );
                    toast.show();
                }
            }
        });

        allbindData.btnbackbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        allbindData.btnhistorybdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectionType = BDG_BTN_HISTORY;
                showUserItemClickAd();
            }
        });

        allbindData.btnwhatsappbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectionType = BDG_WHATSAPP;
                showUserItemClickAd();
            }
        });

        allbindData.btntelegrambdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectionType = BDG_TELEGRAM;
                showUserItemClickAd();
            }
        });

        allbindData.btninstagrambdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectionType = BDG_INSTRAGRAM;
                showUserItemClickAd();
            }
        });

        allbindData.btnFaceBookbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectionType = BDG_FACEBOOK;
                showUserItemClickAd();
            }
        });

        allbindData.btnSharebdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectionType = BDG_BTN_SHARE;
                showUserItemClickAd();
            }
        });
    }

    @Override
    protected void ModelSyncObserver() {

    }

    @Override
    protected void viewAds() {
        bannerview = findViewById(com.doc.gradient.bt.server.uses.ai.ads.R.id.hideView);
        showBannerAds(bannerview, ShareTheBuddy.this);
    }

    private void showUserItemClickAd() {
        BtcAllInterstitialAdsLib.showItemClickAd(this, this);
    }

    @Override
    public void gotoNext() {
        switch (selectionType) {
            case BDG_BTN_HISTORY:
                Intent intent1 = new Intent(this, ShareBuddyVault.class);
                startActivity(intent1);
                break;
            case BDG_BTN_SHARE:
                UserInteractionStatsJava.shareText(this, "", "Share Application", "Share with..", "");
                break;
            case BDG_WHATSAPP:
                shareTextWhatsApp(this, "", "Share Application", "Share with..", "");
                break;
            case BDG_INSTRAGRAM:
                shareTextInstagram(this, "", "Share Application", "Share with..", "");
                break;
            case BDG_FACEBOOK:
                shareTextFacebook(this, "", "Share Application", "Share with..", "");
                break;
            case BDG_TELEGRAM:
                shareTextTelegram(this, "", "Share Application", "Share with..", "");
                break;
        }
    }


    public void shareTextWhatsApp(
            Activity activity,
            String message,
            String subject,
            String chooserTitle,
            String referralCode
    ) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setPackage("com.whatsapp");
            shareIntent.setType("text/plain");
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

            if (message == null || message.isEmpty()) {
                String formattedMessage = String.format(
                        "Download the BTC Mining app to mine BTC coin for your good future.\n\n" +
                                "Use my referral code : %s\n\n" +
                                "Check below link to download the app from Google Play\n\n%s%s",
                        SessionaryJava.getSessionaryinstance().getUserInfo() != null ?
                                SessionaryJava.getSessionaryinstance().getUserInfo().getReferralCode() : "",
                        Project_SHARE_LINK,
                        getPackageName()
                );
                shareIntent.putExtra(Intent.EXTRA_TEXT, formattedMessage);
            } else {
                shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            }

            try {
               startActivity(Intent.createChooser(shareIntent, chooserTitle));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No application found to perform this action.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void shareTextFacebook(
            Activity activity,
            String message,
            String subject,
            String chooserTitle,
            String referralCode
    ) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setPackage("com.facebook.katana");
            shareIntent.setType("text/plain");
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

            if (message == null || message.isEmpty()) {
                String formattedMessage = String.format(
                        "Download the BTC Mining app to mine BTC coin for your good future.\n\n" +
                                "Use my referral code : %s\n\n" +
                                "Check below link to download the app from Google Play\n\n%s%s",
                        SessionaryJava.getSessionaryinstance().getUserInfo() != null ?
                                SessionaryJava.getSessionaryinstance().getUserInfo().getReferralCode() : "",
                        Project_SHARE_LINK,
                        activity.getPackageName()
                );
                shareIntent.putExtra(Intent.EXTRA_TEXT, formattedMessage);
            } else {
                shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            }

            try {
                activity.startActivity(Intent.createChooser(shareIntent, chooserTitle));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(
                        activity,
                        "No application found to perform this action.",
                        Toast.LENGTH_LONG
                ).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void shareTextTelegram(
            Activity activity,
            String message,
            String subject,
            String chooserTitle,
            String referralCode
    ) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setPackage("org.telegram.messenger");
            shareIntent.setType("text/plain");
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

            if (message == null || message.isEmpty()) {
                String formattedMessage = String.format(
                        "Download the BTC Mining app to mine BTC coin for your good future.\n\n" +
                                "Use my referral code : %s\n\n" +
                                "Check below link to download the app from Google Play\n\n%s%s",
                        SessionaryJava.getSessionaryinstance().getUserInfo() != null ?
                                SessionaryJava.getSessionaryinstance().getUserInfo().getReferralCode() : "",
                        Project_SHARE_LINK,
                        activity.getPackageName()
                );
                shareIntent.putExtra(Intent.EXTRA_TEXT, formattedMessage);
            } else {
                shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            }

            try {
                activity.startActivity(Intent.createChooser(shareIntent, chooserTitle));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(
                        activity,
                        "No application found to perform this action.",
                        Toast.LENGTH_LONG
                ).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shareTextInstagram(
            Activity activity,
            String message,
            String subject,
            String chooserTitle,
            String referralCode
    ) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setPackage("com.instagram.android");
            shareIntent.setType("text/plain");
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

            if (message == null || message.isEmpty()) {
                String formattedMessage = String.format(
                        "Download the BTC Mining app to mine BTC coin for your good future.\n\n" +
                                "Use my referral code : %s\n\n" +
                                "Check below link to download the app from Google Play\n\n%s%s",
                        SessionaryJava.getSessionaryinstance().getUserInfo() != null ?
                                SessionaryJava.getSessionaryinstance().getUserInfo().getReferralCode() : "",
                        Project_SHARE_LINK,
                        activity.getPackageName()
                );
                shareIntent.putExtra(Intent.EXTRA_TEXT, formattedMessage);
            } else {
                shareIntent.putExtra(Intent.EXTRA_TEXT, message);
            }

            try {
                activity.startActivity(Intent.createChooser(shareIntent, chooserTitle));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(
                        activity,
                        "No application found to perform this action.",
                        Toast.LENGTH_LONG
                ).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
