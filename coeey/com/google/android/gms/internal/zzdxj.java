package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class zzdxj implements Executor {
    private static zzdxj zzmdh = new zzdxj();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private zzdxj() {
    }

    public static zzdxj zzbql() {
        return zzmdh;
    }

    public final void execute(@NonNull Runnable runnable) {
        this.mHandler.post(runnable);
    }
}
