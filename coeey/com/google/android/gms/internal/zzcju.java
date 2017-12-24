package com.google.android.gms.internal;

final class zzcju implements Runnable {
    private /* synthetic */ zzcjr zzjfy;
    private /* synthetic */ zzcgb zzjfz;

    zzcju(zzcjr com_google_android_gms_internal_zzcjr, zzcgb com_google_android_gms_internal_zzcgb) {
        this.zzjfy = com_google_android_gms_internal_zzcjr;
        this.zzjfz = com_google_android_gms_internal_zzcgb;
    }

    public final void run() {
        synchronized (this.zzjfy) {
            this.zzjfy.zzjfv = false;
            if (!this.zzjfy.zzjfo.isConnected()) {
                this.zzjfy.zzjfo.zzawm().zzayw().log("Connected to remote service");
                this.zzjfy.zzjfo.zza(this.zzjfz);
            }
        }
    }
}
