package com.doc.gradient.bt.server.uses.ai.Java_BDG_AppSignalReceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import com.android.billingclient.BuildConfig;
import com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity.BrandSplashJava;
import com.doc.gradient.bt.server.uses.ai.R;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.Objects;

public class AppEventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        long time = intent.getLongExtra("time", 0);
        setReminderNotification(context, msg);
        // Calendar calendar = Calendar.getInstance();
        // calendar.setTimeInMillis(time);
        // BTC_AppUtils.setReminder(context,
        //         BTC_AppUtils.getDailyReminderTime(calendar),
        //         (int) System.currentTimeMillis(), msg);
    }

    private void setReminderNotification(Context context, String msg) {
        try {
            Intent intent = new Intent(context, BrandSplashJava.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            int notificationId = (int) System.currentTimeMillis();
            String channelId = BuildConfig.APPLICATION_ID + "_reminder";
            String channelName = "Reminder";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(
                        channelId,
                        channelName,
                        NotificationManager.IMPORTANCE_HIGH
                );
                mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                Objects.requireNonNull(notificationManager).createNotificationChannel(mChannel);
            }

            Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                    .setTicker(msg)
                    .setContentTitle("Your BTC coin Mining Session Ended \uD83E\uDD11")
                    .setContentText(msg)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(icon1)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setVibrate(new long[]{1000, 1000})
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setAutoCancel(true);

            mBuilder.setSmallIcon(getReminderNotificationIcon(context, mBuilder));

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntent(intent);

            PendingIntent resultPendingIntent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_MUTABLE);
            } else {
                resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            }

            mBuilder.setContentIntent(resultPendingIntent);

            Objects.requireNonNull(notificationManager).notify(notificationId, mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }

    private int getReminderNotificationIcon(Context context, NotificationCompat.Builder notificationBuilder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int color = context.getResources().getColor(R.color.transparent_color);
            notificationBuilder.setColor(color);
            return R.mipmap.ic_launcher;
        }
        return R.mipmap.ic_launcher;
    }
}

