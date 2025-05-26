package com.doc.gradient.bt.server.uses.ai.Java_BDG_Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_Activity.StartDashboardAnalytics;
import com.doc.gradient.bt.server.uses.ai.R;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationAavi extends FirebaseMessagingService {
    private static final String TAG = NotificationAavi.class.getSimpleName();
    private Bitmap icon;

    @Override
    public void onNewToken(String refreshedToken) {
        super.onNewToken(refreshedToken);
        Log.i(TAG, "onNewToken: refreshedToken: " + refreshedToken);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i(TAG, "onMessageReceived: ");
        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Log.d(TAG, "MessageData -> " + remoteMessage.getData());

        if (remoteMessage != null && remoteMessage.getNotification() != null &&
                remoteMessage.getNotification().getBody() != null) {
            String msgTitle = remoteMessage.getNotification().getTitle();
            String msgBody = remoteMessage.getNotification().getBody();
            Log.d(TAG, "NotificationAavi Message Title " + msgTitle);
            Log.d(TAG, "NotificationAavi Message Body: " + msgBody);
            showNotification(msgTitle, msgBody);
        }
    }

    private void showNotification(String title, String messageBody) {
        try {
            Log.e(TAG, "sendNotification()");
            Intent intent = new Intent(this, StartDashboardAnalytics.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, createNotificationChannel(this))
                            .setContentTitle(title)
                            .setContentText(messageBody)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setColor(ContextCompat.getColor(this, R.color.transparent_color))
                            .setAutoCancel(true)
                            .setVibrate(new long[]{1000, 1000})
                            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setContentIntent(pendingIntent);

            if (icon != null) {
                notificationBuilder.setLargeIcon(icon);
            }

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }

    public String createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = context.getString(R.string.default_notification_channel_id);
            CharSequence channelName = "Application_name";
            String channelDescription = "Application_name Alert";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel =
                    new NotificationChannel(channelId, channelName, channelImportance);
            notificationChannel.setDescription(channelDescription);
            notificationChannel.enableVibration(true);

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            return channelId;
        } else {
            return context.getString(R.string.default_notification_channel_id);
        }
    }
}
