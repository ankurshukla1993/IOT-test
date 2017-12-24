package com.google.android.gms.internal;

final class zzcjp implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzckk zzjdy;
    private /* synthetic */ zzcjd zzjfo;
    private /* synthetic */ boolean zzjfs;

    zzcjp(zzcjd com_google_android_gms_internal_zzcjd, boolean z, zzckk com_google_android_gms_internal_zzckk, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjfs = z;
        this.zzjdy = com_google_android_gms_internal_zzckk;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    public final void run() {
        zzcgb zzd = this.zzjfo.zzjfi;
        if (zzd == null) {
            this.zzjfo.zzawm().zzayr().log("Discarding data. Failed to set user attribute");
            return;
        }
        this.zzjfo.zza(zzd, this.zzjfs ? null : this.zzjdy, this.zzjds);
        this.zzjfo.zzxg();
    }
}
