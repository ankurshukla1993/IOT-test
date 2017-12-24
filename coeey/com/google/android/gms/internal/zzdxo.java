package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.firebase.crash.FirebaseCrash.zza;

public final class zzdxo extends zzdxl {
    private final String zzmdy;

    public zzdxo(@NonNull Context context, @NonNull zza com_google_firebase_crash_FirebaseCrash_zza, @NonNull String str) {
        super(context, com_google_firebase_crash_FirebaseCrash_zza);
        this.zzmdy = str;
    }

    @NonNull
    protected final String getErrorMessage() {
        return "Failed to log message";
    }

    public final /* bridge */ /* synthetic */ void run() {
        super.run();
    }

    protected final void zzd(@NonNull zzdxt com_google_android_gms_internal_zzdxt) throws RemoteException {
        com_google_android_gms_internal_zzdxt.log(this.zzmdy);
    }
}
