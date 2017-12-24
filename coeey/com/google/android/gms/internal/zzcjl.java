package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;

final class zzcjl implements Runnable {
    private /* synthetic */ String zzijk;
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcfx zzjdx;
    private /* synthetic */ zzcjd zzjfo;
    private /* synthetic */ boolean zzjfr = true;
    private /* synthetic */ boolean zzjfs;

    zzcjl(zzcjd com_google_android_gms_internal_zzcjd, boolean z, boolean z2, zzcfx com_google_android_gms_internal_zzcfx, zzcff com_google_android_gms_internal_zzcff, String str) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjfs = z2;
        this.zzjdx = com_google_android_gms_internal_zzcfx;
        this.zzjds = com_google_android_gms_internal_zzcff;
        this.zzijk = str;
    }

    public final void run() {
        zzcgb zzd = this.zzjfo.zzjfi;
        if (zzd == null) {
            this.zzjfo.zzawm().zzayr().log("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zzjfr) {
            this.zzjfo.zza(zzd, this.zzjfs ? null : this.zzjdx, this.zzjds);
        } else {
            try {
                if (TextUtils.isEmpty(this.zzijk)) {
                    zzd.zza(this.zzjdx, this.zzjds);
                } else {
                    zzd.zza(this.zzjdx, this.zzijk, this.zzjfo.zzawm().zzayy());
                }
            } catch (RemoteException e) {
                this.zzjfo.zzawm().zzayr().zzj("Failed to send event to the service", e);
            }
        }
        this.zzjfo.zzxg();
    }
}
