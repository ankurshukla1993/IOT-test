package com.evernote.android.job.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.v4.net.ConnectivityManagerCompat;
import com.evernote.android.job.JobRequest.NetworkType;

public final class Device {
    private Device() {
    }

    @TargetApi(17)
    public static boolean isCharging(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intent == null) {
            return false;
        }
        int plugged = intent.getIntExtra("plugged", 0);
        if (plugged == 1 || plugged == 2 || (VERSION.SDK_INT >= 17 && plugged == 4)) {
            return true;
        }
        return false;
    }

    public static boolean isIdle(Context context) {
        boolean z = false;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (VERSION.SDK_INT >= 23) {
            if (powerManager.isDeviceIdleMode() || !powerManager.isInteractive()) {
                z = true;
            }
            return z;
        } else if (VERSION.SDK_INT >= 20) {
            if (powerManager.isInteractive()) {
                return false;
            }
            return true;
        } else if (powerManager.isScreenOn()) {
            return false;
        } else {
            return true;
        }
    }

    @NonNull
    public static NetworkType getNetworkType(@NonNull Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            return NetworkType.ANY;
        }
        if (!ConnectivityManagerCompat.isActiveNetworkMetered(connectivityManager)) {
            return NetworkType.UNMETERED;
        }
        if (networkInfo.isRoaming()) {
            return NetworkType.CONNECTED;
        }
        return NetworkType.NOT_ROAMING;
    }
}
