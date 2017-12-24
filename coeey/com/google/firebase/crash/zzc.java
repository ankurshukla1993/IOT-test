package com.google.firebase.crash;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzbzy;
import com.google.android.gms.internal.zzdxt;
import com.google.android.gms.internal.zzdxv;
import com.google.android.gms.internal.zzdxx;
import com.google.android.gms.internal.zzdya;
import com.google.firebase.FirebaseApp;

public final class zzc {
    private final Context mContext;
    private final FirebaseApp zzlyo;
    private String zzmds = null;

    zzc(@NonNull FirebaseApp firebaseApp, @Nullable String str) {
        this.mContext = firebaseApp.getApplicationContext();
        this.zzlyo = firebaseApp;
    }

    @VisibleForTesting
    public final zzdxt zzbqs() {
        Throwable e;
        zzdya.initialize(this.mContext);
        if (((Boolean) zzbzy.zzaqq().zzb(zzdya.zzmef)).booleanValue()) {
            zzdxt zzbqu;
            try {
                zzdxv.zzbqt().zzce(this.mContext);
                zzbqu = zzdxv.zzbqt().zzbqu();
                try {
                    String valueOf = String.valueOf(zzdxv.zzbqt());
                    Log.i("FirebaseCrash", new StringBuilder(String.valueOf(valueOf).length() + 33).append("FirebaseCrash reporting loaded - ").append(valueOf).toString());
                    return zzbqu;
                } catch (zzdxx e2) {
                    e = e2;
                }
            } catch (Throwable e3) {
                Throwable th = e3;
                zzbqu = null;
                e = th;
                Log.e("FirebaseCrash", "Failed to load crash reporting", e);
                zzf.zza(this.mContext, e);
                return zzbqu;
            }
        }
        Log.w("FirebaseCrash", "Crash reporting is disabled");
        return null;
    }
}
