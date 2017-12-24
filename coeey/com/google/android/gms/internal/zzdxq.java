package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.util.zzf;
import com.google.firebase.crash.FirebaseCrash.zza;

public final class zzdxq extends zzdxl {
    private final boolean zzmea;

    public zzdxq(@NonNull Context context, @NonNull zza com_google_firebase_crash_FirebaseCrash_zza, boolean z) {
        super(context, com_google_firebase_crash_FirebaseCrash_zza);
        this.zzmea = z;
    }

    @NonNull
    protected final String getErrorMessage() {
        return "Failed to set crash enabled to " + this.zzmea;
    }

    public final void run() {
        Throwable e;
        try {
            zzdxt zzbqq = this.zzmdx.zzbqq();
            if (zzbqq == null) {
                Log.e("FirebaseCrash", "Crash api not available");
            } else {
                zzd(zzbqq);
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

    protected final void zzd(@NonNull zzdxt com_google_android_gms_internal_zzdxt) throws RemoteException {
        com_google_android_gms_internal_zzdxt.zzcg(this.zzmea);
    }
}
