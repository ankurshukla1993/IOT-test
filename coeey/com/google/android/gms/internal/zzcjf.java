package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzcjf implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcjd zzjfo;

    zzcjf(zzcjd com_google_android_gms_internal_zzcjd, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    public final void run() {
        zzcgb zzd = this.zzjfo.zzjfi;
        if (zzd == null) {
            this.zzjfo.zzawm().zzayr().log("Failed to reset data on the service; null service");
            return;
        }
        try {
            zzd.zzd(this.zzjds);
        } catch (RemoteException e) {
            this.zzjfo.zzawm().zzayr().zzj("Failed to reset data on the service", e);
        }
        this.zzjfo.zzxg();
    }
}
