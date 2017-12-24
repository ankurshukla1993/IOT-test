package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.util.zzf;
import com.google.firebase.crash.FirebaseCrash.zza;

abstract class zzdxl implements Runnable {
    protected final Context mContext;
    protected final zza zzmdx;

    zzdxl(@NonNull Context context, @NonNull zza com_google_firebase_crash_FirebaseCrash_zza) {
        this.zzmdx = com_google_firebase_crash_FirebaseCrash_zza;
        this.mContext = context.getApplicationContext();
    }

    @NonNull
    protected abstract String getErrorMessage();

    public void run() {
        Throwable e;
        try {
            zzdxt zzbqq = this.zzmdx.zzbqq();
            if (zzbqq != null && zzbqq.zzbqp()) {
                zzd(zzbqq);
            } else if (zzbqq != null) {
                Log.e("FirebaseCrash", "Firebase Crash Reporting not enabled");
            } else {
                Log.e("FirebaseCrash", "Crash api not available");
            }
        } catch (RemoteException e2) {
            e = e2;
            zzf.zza(this.mContext, e);
            Log.e("FirebaseCrash", getErrorMessage(), e);
        } catch (RuntimeException e3) {
            e = e3;
            zzf.zza(this.mContext, e);
            Log.e("FirebaseCrash", getErrorMessage(), e);
        }
    }

    protected abstract void zzd(@NonNull zzdxt com_google_android_gms_internal_zzdxt) throws RemoteException;
}
