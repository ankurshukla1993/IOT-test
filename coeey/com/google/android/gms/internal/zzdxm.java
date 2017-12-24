package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.firebase.crash.FirebaseCrash.zza;

public final class zzdxm extends zzdxl {
    private final long zzffr;
    private final String zzmdy;
    private final Bundle zzmdz;

    public zzdxm(@NonNull Context context, @NonNull zza com_google_firebase_crash_FirebaseCrash_zza, @NonNull String str, long j, @NonNull Bundle bundle) {
        super(context, com_google_firebase_crash_FirebaseCrash_zza);
        this.zzmdy = str;
        this.zzffr = j;
        this.zzmdz = bundle;
    }

    @NonNull
    protected final String getErrorMessage() {
        return "Failed to log analytics event";
    }

    public final /* bridge */ /* synthetic */ void run() {
        super.run();
    }

    protected final void zzd(@NonNull zzdxt com_google_android_gms_internal_zzdxt) throws RemoteException {
        com_google_android_gms_internal_zzdxt.zza(this.zzmdy, this.zzffr, this.zzmdz);
    }
}
