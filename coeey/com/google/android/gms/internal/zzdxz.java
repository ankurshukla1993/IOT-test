package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.android.gms.measurement.AppMeasurement$Param;
import com.google.firebase.crash.FirebaseCrash.zza;
import java.util.concurrent.ExecutorService;

public final class zzdxz {
    private final AppMeasurement zzmee;

    private zzdxz(AppMeasurement appMeasurement) {
        this.zzmee = appMeasurement;
    }

    @Nullable
    public static zzdxz zzer(Context context) {
        try {
            return new zzdxz(AppMeasurement.getInstance(context));
        } catch (NoClassDefFoundError e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseCrashAnalytics", new StringBuilder(String.valueOf(valueOf).length() + 50).append("Unable to log event, missing measurement library: ").append(valueOf).toString());
            return null;
        }
    }

    public final void zza(@NonNull Context context, @NonNull ExecutorService executorService, @Nullable zza com_google_firebase_crash_FirebaseCrash_zza) {
        this.zzmee.registerOnMeasurementEventListener(new zzdxy(context, executorService, com_google_firebase_crash_FirebaseCrash_zza));
    }

    public final void zza(boolean z, long j) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putInt(AppMeasurement$Param.FATAL, 1);
        } else {
            bundle.putInt(AppMeasurement$Param.FATAL, 0);
        }
        bundle.putLong(AppMeasurement$Param.TIMESTAMP, j);
        this.zzmee.logEventInternal(AppMeasurement.CRASH_ORIGIN, AppMeasurement$Event.APP_EXCEPTION, bundle);
    }
}
