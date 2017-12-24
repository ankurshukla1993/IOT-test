package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzbq;

public final class zzcgx {
    private final String zzbfo;
    private long zzdot;
    private boolean zzjaq;
    private /* synthetic */ zzcgu zzjar;
    private final long zzjas;

    public zzcgx(zzcgu com_google_android_gms_internal_zzcgu, String str, long j) {
        this.zzjar = com_google_android_gms_internal_zzcgu;
        zzbq.zzgh(str);
        this.zzbfo = str;
        this.zzjas = j;
    }

    @WorkerThread
    public final long get() {
        if (!this.zzjaq) {
            this.zzjaq = true;
            this.zzdot = this.zzjar.zzayz().getLong(this.zzbfo, this.zzjas);
        }
        return this.zzdot;
    }

    @WorkerThread
    public final void set(long j) {
        Editor edit = this.zzjar.zzayz().edit();
        edit.putLong(this.zzbfo, j);
        edit.apply();
        this.zzdot = j;
    }
}
