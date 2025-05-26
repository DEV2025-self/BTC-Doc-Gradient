package com.doc.gradient.bt.server.uses.ai.Java_BDG_MainBase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.lifecycle.LiveData;

public class AutoConnectNetwork  extends LiveData<Boolean> {

    private final BroadcastReceiver broadcastReceiver;
    private final ConnectivityManager connectManager;
    private final Context context;
    private final NetworkCallbackbtc networkCallbackbtc;
    private final IntentFilter intentFilterx;

    public AutoConnectNetwork(Context context) {
        if (context == null) throw new NullPointerException("context cannot be null");
        this.context = context;
        intentFilterx = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        connectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        networkCallbackbtc = new NetworkCallbackbtc(this);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context2, Intent intent) {
                updateConnection();
            }
        };
    }

    @Override
    protected void onActive() {
        super.onActive();
        updateConnection();
        if (Build.VERSION.SDK_INT >= 24) {
            connectManager.registerDefaultNetworkCallback(networkCallbackbtc);
        } else {
            connectManager.registerNetworkCallback(
                    new NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build(), networkCallbackbtc);
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        connectManager.unregisterNetworkCallback(networkCallbackbtc);
    }

    public void updateConnection() {
        NetworkInfo activeNetworkInfo = connectManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        postValue(isConnected);
    }

    public static class NetworkCallbackbtc extends ConnectivityManager.NetworkCallback {

        private final AutoConnectNetwork liveDatabtc;

        public NetworkCallbackbtc(AutoConnectNetwork liveDatabtc) {
            this.liveDatabtc = liveDatabtc;
        }

        @Override
        public void onAvailable(Network network) {
            super.onAvailable(network);
            liveDatabtc.postValue(true);
        }

        @Override
        public void onLost(Network network) {
            super.onLost(network);
            liveDatabtc.postValue(false);
        }
    }
}