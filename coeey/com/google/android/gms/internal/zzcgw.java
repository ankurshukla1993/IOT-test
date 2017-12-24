package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzbq;

public final class zzcgw {
    private final String zzbfo;
    private boolean zzfjj;
    private final boolean zzjap = true;
    private boolean zzjaq;
    private /* synthetic */ zzcgu zzjar;

    public zzcgw(zzcgu com_google_android_gms_internal_zzcgu, String str, boolean z) {
        this.zzjar = com_google_android_gms_internal_zzcgu;
        zzbq.zzgh(str);
        this.zzbfo = str;
    }

    @WorkerThread
    public final boolean get() {
        if (!this.zzjaq) {
            this.zzjaq = true;
            this.zzfjj = this.zzjar.zzayz().getBoolean(this.zzbfo, this.zzjap);
        }
        return this.zzfjj;
    }

    @WorkerThread
    public final void set(boolean z) {
        Editor edit = this.zzjar.zzayz().edit();
        edit.putBoolean(this.zzbfo, z);
        edit.apply();
        this.zzfjj = z;
    }
}
