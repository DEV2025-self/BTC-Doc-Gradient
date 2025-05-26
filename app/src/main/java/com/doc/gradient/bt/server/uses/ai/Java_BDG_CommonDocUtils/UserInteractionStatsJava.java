package com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_AppSignalReceiver.AppEventReceiver;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Sessionary.SessionaryJava;

public class UserInteractionStatsJava {
    private static final String Project_SHARE_LINK = "https://play.google.com/store/apps/details?id=";

    public static void reminder(Context context, long timeInMillis, int noti_id, String msg) {
        try {
            Log.e("Reminder", "Daily Reminder");
            Intent intent = new Intent(context, AppEventReceiver.class);
            intent.putExtra("msg", msg);
            intent.putExtra("time", timeInMillis);

            PendingIntent pendingIntent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                pendingIntent = PendingIntent.getBroadcast(context, noti_id, intent, PendingIntent.FLAG_MUTABLE);
            } else {
                pendingIntent = PendingIntent.getBroadcast(context, noti_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            }

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int calculateOriginalPrice(double discountedPrice, int discountPercentage) {
        double originalPrice = (discountedPrice * 100) / (100 - discountPercentage);
        Log.e("calculateOriginalPrice", "discountedPrice = " + discountedPrice + " / discountPercentage = " + discountPercentage + " / originalPrice = " + originalPrice);
        return (int) originalPrice;
    }


    public static boolean isValidContext(Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return !activity.isDestroyed() && !activity.isFinishing();
            } else {
                return !activity.isFinishing();
            }
        }
        return true;
    }

    public static boolean validContext(Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return !activity.isDestroyed() && !activity.isFinishing();
            } else {
                return !activity.isFinishing();
            }
        }
        return true;
    }

    public static boolean connectedInternet(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if ((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null &&
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) ||
                    (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null &&
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void appShare(Activity activity, String message, String subject, String chooserTitle) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            if (message.equals("")) {
                shareIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        String.format("I'm using *%1$s!* app. Install Now And Earn BTC Free.", "BTC Mining") +
                                "\n\n" + Project_SHARE_LINK + activity.getPackageName()
                );
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

    public static void shareText(Activity activity, String message, String subject, String chooserTitle, String referralCode) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            if (message.equals("")) {
                shareIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Download the BTC Mining app to mine BTC coin for your good future.\n\n" +
                                "Use my referral code : *" + SessionaryJava.getSessionaryinstance().getUserInfo().getReferralCode() + "*\n\n" +
                                "Check below link to download the app from Google Play\n\n" +
                                Project_SHARE_LINK + activity.getPackageName()
                );
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