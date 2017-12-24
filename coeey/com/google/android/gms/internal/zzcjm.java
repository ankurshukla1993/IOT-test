package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;

final class zzcjm implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcjd zzjfo;
    private /* synthetic */ boolean zzjfr = true;
    private /* synthetic */ boolean zzjfs;
    private /* synthetic */ zzcfi zzjft;
    private /* synthetic */ zzcfi zzjfu;

    zzcjm(zzcjd com_google_android_gms_internal_zzcjd, boolean z, boolean z2, zzcfi com_google_android_gms_internal_zzcfi, zzcff com_google_android_gms_internal_zzcff, zzcfi com_google_android_gms_internal_zzcfi2) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjfs = z2;
        this.zzjft = com_google_android_gms_internal_zzcfi;
        this.zzjds = com_google_android_gms_internal_zzcff;
        this.zzjfu = com_google_android_gms_internal_zzcfi2;
    }

    public final void run() {
        zzcgb zzd = this.zzjfo.zzjfi;
        if (zzd == null) {
            this.zzjfo.zzawm().zzayr().log("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zzjfr) {
            this.zzjfo.zza(zzd, this.zzjfs ? null : this.zzjft, this.zzjds);
        } else {
            try {
                if (TextUtils.isEmpty(this.zzjfu.packageName)) {
                    zzd.zza(this.zzjft, this.zzjds);
                } else {
                    zzd.zzb(this.zzjft);
                }
            } catch (RemoteException e) {
                this.zzjfo.zzawm().zzayr().zzj("Failed to send conditional user property to the service", e);
            }
        }
        this.zzjfo.zzxg();
    }
}
