package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class zzk {
    private static zzk zznud;
    private final Context zzaif;
    private final ScheduledExecutorService zznue;
    private zzm zznuf = new zzm();
    private int zznug = 1;

    @VisibleForTesting
    private zzk(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zznue = scheduledExecutorService;
        this.zzaif = context.getApplicationContext();
    }

    private final synchronized <T> Task<T> zza(zzt<T> com_google_firebase_iid_zzt_T) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(com_google_firebase_iid_zzt_T);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(valueOf).length() + 9).append("Queueing ").append(valueOf).toString());
        }
        if (!this.zznuf.zzb(com_google_firebase_iid_zzt_T)) {
            this.zznuf = new zzm();
            this.zznuf.zzb(com_google_firebase_iid_zzt_T);
        }
        return com_google_firebase_iid_zzt_T.zzgow.getTask();
    }

    private final synchronized int zzchf() {
        int i;
        i = this.zznug;
        this.zznug = i + 1;
        return i;
    }

    public static synchronized zzk zzet(Context context) {
        zzk com_google_firebase_iid_zzk;
        synchronized (zzk.class) {
            if (zznud == null) {
                zznud = new zzk(context, Executors.newSingleThreadScheduledExecutor());
            }
            com_google_firebase_iid_zzk = zznud;
        }
        return com_google_firebase_iid_zzk;
    }

    public final Task<Void> zzh(int i, Bundle bundle) {
        return zza(new zzs(zzchf(), 2, bundle));
    }

    public final Task<Bundle> zzi(int i, Bundle bundle) {
        return zza(new zzv(zzchf(), 1, bundle));
    }
}
