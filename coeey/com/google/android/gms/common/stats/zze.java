package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzj;
import java.util.List;

public final class zze {
    private static boolean zzgae = false;
    private static zze zzgbe = new zze();
    private static Boolean zzgbf;

    public static void zza(Context context, String str, int i, String str2, String str3, String str4, int i2, List<String> list) {
        zza(context, str, 8, str2, str3, str4, i2, list, 0);
    }

    public static void zza(Context context, String str, int i, String str2, String str3, String str4, int i2, List<String> list, long j) {
        if (zzgbf == null) {
            zzgbf = Boolean.valueOf(false);
        }
        if (!zzgbf.booleanValue()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            String str5 = "WakeLockTracker";
            String str6 = "missing wakeLock key. ";
            String valueOf = String.valueOf(str);
            Log.e(str5, valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6));
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (7 == i || 8 == i || 10 == i || 11 == i) {
            List list2;
            if (list == null || list.size() != 1) {
                List<String> list3 = list;
            } else {
                if ("com.google.android.gms".equals(list.get(0))) {
                    list = null;
                }
                list2 = list;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            int zzct = zzj.zzct(context);
            String packageName = context.getPackageName();
            if ("com.google.android.gms".equals(packageName)) {
                packageName = null;
            }
            try {
                context.startService(new Intent().setComponent(zzb.zzgaj).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", new WakeLockEvent(currentTimeMillis, i, str2, i2, list2, str, elapsedRealtime, zzct, str3, packageName, zzj.zzcu(context), j, str4)));
            } catch (Throwable e) {
                Log.wtf("WakeLockTracker", e);
            }
        }
    }

    public static zze zzalt() {
        return zzgbe;
    }
}
