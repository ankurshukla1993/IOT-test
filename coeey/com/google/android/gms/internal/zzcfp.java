package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.zzbq;

abstract class zzcfp {
    private static volatile Handler zzdsr;
    private volatile long zzdss;
    private final zzchj zzitk;
    private boolean zziwi = true;
    private final Runnable zzv = new zzcfq(this);

    zzcfp(zzchj com_google_android_gms_internal_zzchj) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzchj);
        this.zzitk = com_google_android_gms_internal_zzchj;
    }

    private final Handler getHandler() {
        if (zzdsr != null) {
            return zzdsr;
        }
        Handler handler;
        synchronized (zzcfp.class) {
            if (zzdsr == null) {
                zzdsr = new Handler(this.zzitk.getContext().getMainLooper());
            }
            handler = zzdsr;
        }
        return handler;
    }

    public final void cancel() {
        this.zzdss = 0;
        getHandler().removeCallbacks(this.zzv);
    }

    public abstract void run();

    public final boolean zzdr() {
        return this.zzdss != 0;
    }

    public final void zzr(long j) {
        cancel();
        if (j >= 0) {
            this.zzdss = this.zzitk.zzwh().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzv, j)) {
                this.zzitk.zzawm().zzayr().zzj("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }
}
