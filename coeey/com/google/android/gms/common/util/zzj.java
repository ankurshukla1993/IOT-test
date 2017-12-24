package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.facebook.yoga.YogaConstants;

public final class zzj {
    private static IntentFilter zzgbr = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzgbs;
    private static float zzgbt = YogaConstants.UNDEFINED;

    @TargetApi(20)
    public static int zzct(Context context) {
        int i = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, zzgbr);
        int i2 = ((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0 ? 1 : 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        int i3 = (zzq.zzama() ? powerManager.isInteractive() : powerManager.isScreenOn() ? 1 : 0) << 1;
        if (i2 == 0) {
            i = 0;
        }
        return i3 | i;
    }

    public static synchronized float zzcu(Context context) {
        float f;
        synchronized (zzj.class) {
            if (SystemClock.elapsedRealtime() - zzgbs >= 60000 || Float.isNaN(zzgbt)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver(null, zzgbr);
                if (registerReceiver != null) {
                    zzgbt = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzgbs = SystemClock.elapsedRealtime();
                f = zzgbt;
            } else {
                f = zzgbt;
            }
        }
        return f;
    }
}
