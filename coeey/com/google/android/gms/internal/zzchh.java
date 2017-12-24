package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class zzchh<V> extends FutureTask<V> implements Comparable<zzchh> {
    private final String zzjbu;
    private /* synthetic */ zzche zzjbv;
    private final long zzjbw = zzche.zzjbt.getAndIncrement();
    final boolean zzjbx;

    zzchh(zzche com_google_android_gms_internal_zzche, Runnable runnable, boolean z, String str) {
        this.zzjbv = com_google_android_gms_internal_zzche;
        super(runnable, null);
        zzbq.checkNotNull(str);
        this.zzjbu = str;
        this.zzjbx = false;
        if (this.zzjbw == Long.MAX_VALUE) {
            com_google_android_gms_internal_zzche.zzawm().zzayr().log("Tasks index overflow");
        }
    }

    zzchh(zzche com_google_android_gms_internal_zzche, Callable<V> callable, boolean z, String str) {
        this.zzjbv = com_google_android_gms_internal_zzche;
        super(callable);
        zzbq.checkNotNull(str);
        this.zzjbu = str;
        this.zzjbx = z;
        if (this.zzjbw == Long.MAX_VALUE) {
            com_google_android_gms_internal_zzche.zzawm().zzayr().log("Tasks index overflow");
        }
    }

    public final /* synthetic */ int compareTo(@NonNull Object obj) {
        zzchh com_google_android_gms_internal_zzchh = (zzchh) obj;
        if (this.zzjbx != com_google_android_gms_internal_zzchh.zzjbx) {
            return this.zzjbx ? -1 : 1;
        } else {
            if (this.zzjbw < com_google_android_gms_internal_zzchh.zzjbw) {
                return -1;
            }
            if (this.zzjbw > com_google_android_gms_internal_zzchh.zzjbw) {
                return 1;
            }
            this.zzjbv.zzawm().zzays().zzj("Two tasks share the same index. index", Long.valueOf(this.zzjbw));
            return 0;
        }
    }

    protected final void setException(Throwable th) {
        this.zzjbv.zzawm().zzayr().zzj(this.zzjbu, th);
        if (th instanceof zzchf) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
