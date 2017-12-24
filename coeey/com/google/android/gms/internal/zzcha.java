package com.google.android.gms.internal;

import android.content.BroadcastReceiver.PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzbq;

public final class zzcha {
    private final zzchc zzjax;

    public zzcha(zzchc com_google_android_gms_internal_zzchc) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzchc);
        this.zzjax = com_google_android_gms_internal_zzchc;
    }

    public static boolean zzbi(Context context) {
        zzbq.checkNotNull(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 2);
            return receiverInfo != null && receiverInfo.enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        zzchj zzdu = zzchj.zzdu(context);
        zzcgj zzawm = zzdu.zzawm();
        if (intent == null) {
            zzawm.zzayt().log("Receiver called with null intent");
            return;
        }
        String action = intent.getAction();
        zzawm.zzayx().zzj("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzawm.zzayx().log("Starting wakeful intent.");
            this.zzjax.doStartService(context, className);
        } else if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            PendingResult doGoAsync = this.zzjax.doGoAsync();
            action = intent.getStringExtra("referrer");
            if (action == null) {
                zzawm.zzayx().log("Install referrer extras are null");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                    return;
                }
                return;
            }
            zzawm.zzayv().zzj("Install referrer extras are", action);
            if (!action.contains("?")) {
                String str = "?";
                action = String.valueOf(action);
                action = action.length() != 0 ? str.concat(action) : new String(str);
            }
            Bundle zzp = zzdu.zzawi().zzp(Uri.parse(action));
            if (zzp == null) {
                zzawm.zzayx().log("No campaign defined in install referrer broadcast");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                    return;
                }
                return;
            }
            long longExtra = 1000 * intent.getLongExtra("referrer_timestamp_seconds", 0);
            if (longExtra == 0) {
                zzawm.zzayt().log("Install referrer is missing timestamp");
            }
            zzdu.zzawl().zzg(new zzchb(this, zzdu, longExtra, zzp, context, zzawm, doGoAsync));
        }
    }
}
