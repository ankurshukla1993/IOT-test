package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.measurement.AppMeasurement$zzb;

final class zzcji implements Runnable {
    private /* synthetic */ zzcjd zzjfo;
    private /* synthetic */ AppMeasurement$zzb zzjfq;

    zzcji(zzcjd com_google_android_gms_internal_zzcjd, AppMeasurement$zzb appMeasurement$zzb) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjfq = appMeasurement$zzb;
    }

    public final void run() {
        zzcgb zzd = this.zzjfo.zzjfi;
        if (zzd == null) {
            this.zzjfo.zzawm().zzayr().log("Failed to send current screen to service");
            return;
        }
        try {
            if (this.zzjfq == null) {
                zzd.zza(0, null, null, this.zzjfo.getContext().getPackageName());
            } else {
                zzd.zza(this.zzjfq.zzitr, this.zzjfq.zzitp, this.zzjfq.zzitq, this.zzjfo.getContext().getPackageName());
            }
            this.zzjfo.zzxg();
        } catch (RemoteException e) {
            this.zzjfo.zzawm().zzayr().zzj("Failed to send current screen to the service", e);
        }
    }
}
