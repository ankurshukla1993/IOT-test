package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement$OnEventListener;
import com.google.firebase.crash.FirebaseCrash.zza;
import java.util.concurrent.ExecutorService;

final class zzdxy implements AppMeasurement$OnEventListener {
    private final Context mContext;
    private final zza zzmdx;
    private final ExecutorService zzmed;

    public zzdxy(@NonNull Context context, @NonNull ExecutorService executorService, @Nullable zza com_google_firebase_crash_FirebaseCrash_zza) {
        this.mContext = context.getApplicationContext();
        this.zzmed = executorService;
        this.zzmdx = com_google_firebase_crash_FirebaseCrash_zza;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        if (str != null && !str.equals(AppMeasurement.CRASH_ORIGIN) && this.zzmdx != null) {
            this.zzmed.submit(new zzdxm(this.mContext, this.zzmdx, str2, j, bundle));
        }
    }
}
