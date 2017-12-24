package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.lang.Thread.UncaughtExceptionHandler;

final class zzchg implements UncaughtExceptionHandler {
    private final String zzjbu;
    private /* synthetic */ zzche zzjbv;

    public zzchg(zzche com_google_android_gms_internal_zzche, String str) {
        this.zzjbv = com_google_android_gms_internal_zzche;
        zzbq.checkNotNull(str);
        this.zzjbu = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzjbv.zzawm().zzayr().zzj(this.zzjbu, th);
    }
}
