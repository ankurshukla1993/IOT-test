package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.dynamic.zzn;
import com.google.firebase.crash.FirebaseCrash.zza;

public final class zzdxp extends zzdxl {
    private final Throwable zzdcz;
    private final zzdxz zzmdm;

    public zzdxp(@NonNull Context context, @NonNull zza com_google_firebase_crash_FirebaseCrash_zza, @NonNull Throwable th, @Nullable zzdxz com_google_android_gms_internal_zzdxz) {
        super(context, com_google_firebase_crash_FirebaseCrash_zza);
        this.zzdcz = th;
        this.zzmdm = com_google_android_gms_internal_zzdxz;
    }

    @NonNull
    protected final String getErrorMessage() {
        return "Failed to report uncaught exception";
    }

    public final /* bridge */ /* synthetic */ void run() {
        super.run();
    }

    protected final void zzd(@NonNull zzdxt com_google_android_gms_internal_zzdxt) throws RemoteException {
        if (this.zzmdm != null) {
            this.zzmdm.zza(true, System.currentTimeMillis());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Log.w("FirebaseCrash", "Failed to wait for analytics event to be logged");
                return;
            }
        }
        com_google_android_gms_internal_zzdxt.zzaf(zzn.zzy(this.zzdcz));
    }
}
