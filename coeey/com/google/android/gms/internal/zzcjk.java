package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzcjk implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcjd zzjfo;

    zzcjk(zzcjd com_google_android_gms_internal_zzcjd, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    public final void run() {
        zzcgb zzd = this.zzjfo.zzjfi;
        if (zzd == null) {
            this.zzjfo.zzawm().zzayr().log("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzd.zzb(this.zzjds);
            this.zzjfo.zzxg();
        } catch (RemoteException e) {
            this.zzjfo.zzawm().zzayr().zzj("Failed to send measurementEnabled to the service", e);
        }
    }
}
