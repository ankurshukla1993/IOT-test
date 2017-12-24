package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzcjh implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcjd zzjfo;

    zzcjh(zzcjd com_google_android_gms_internal_zzcjd, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    public final void run() {
        zzcgb zzd = this.zzjfo.zzjfi;
        if (zzd == null) {
            this.zzjfo.zzawm().zzayr().log("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzd.zza(this.zzjds);
            this.zzjfo.zza(zzd, null, this.zzjds);
            this.zzjfo.zzxg();
        } catch (RemoteException e) {
            this.zzjfo.zzawm().zzayr().zzj("Failed to send app launch to the service", e);
        }
    }
}
