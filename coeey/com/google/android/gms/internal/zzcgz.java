package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzbq;

public final class zzcgz {
    private String mValue;
    private final String zzbfo;
    private boolean zzjaq;
    private /* synthetic */ zzcgu zzjar;
    private final String zzjaw = null;

    public zzcgz(zzcgu com_google_android_gms_internal_zzcgu, String str, String str2) {
        this.zzjar = com_google_android_gms_internal_zzcgu;
        zzbq.zzgh(str);
        this.zzbfo = str;
    }

    @WorkerThread
    public final String zzazf() {
        if (!this.zzjaq) {
            this.zzjaq = true;
            this.mValue = this.zzjar.zzayz().getString(this.zzbfo, null);
        }
        return this.mValue;
    }

    @WorkerThread
    public final void zzjk(String str) {
        if (!zzckn.zzas(str, this.mValue)) {
            Editor edit = this.zzjar.zzayz().edit();
            edit.putString(this.zzbfo, str);
            edit.apply();
            this.mValue = str;
        }
    }
}
