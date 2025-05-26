package com.doc.gradient.bt.server.uses.ai.Java_BDG_AppSignalReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_CommonDocUtils.UserInteractionStatsJava;

import java.util.Calendar;

public class StartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Alarm manager is turned off when device gets rebooted, that's why this class starts our service
        // when device gets rebooted
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis() + 5000);

            UserInteractionStatsJava.reminder(
                    context,
                    calendar.getTimeInMillis(),
                    (int) System.currentTimeMillis(),
                    "Please Start New Mining Session 🚀 Earn BTC coin Now."
            );
        }
    }
}
