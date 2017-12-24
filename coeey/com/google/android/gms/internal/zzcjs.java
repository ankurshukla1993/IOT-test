package com.google.android.gms.internal;

final class zzcjs implements Runnable {
    private /* synthetic */ zzcgb zzjfx;
    private /* synthetic */ zzcjr zzjfy;

    zzcjs(zzcjr com_google_android_gms_internal_zzcjr, zzcgb com_google_android_gms_internal_zzcgb) {
        this.zzjfy = com_google_android_gms_internal_zzcjr;
        this.zzjfx = com_google_android_gms_internal_zzcgb;
    }

    public final void run() {
        synchronized (this.zzjfy) {
            this.zzjfy.zzjfv = false;
            if (!this.zzjfy.zzjfo.isConnected()) {
                this.zzjfy.zzjfo.zzawm().zzayx().log("Connected to service");
                this.zzjfy.zzjfo.zza(this.zzjfx);
            }
        }
    }
}
