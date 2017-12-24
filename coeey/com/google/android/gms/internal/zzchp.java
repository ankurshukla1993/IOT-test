package com.google.android.gms.internal;

final class zzchp implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcho zzjdt;

    zzchp(zzcho com_google_android_gms_internal_zzcho, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    public final void run() {
        this.zzjdt.zzitk.zzazz();
        this.zzjdt.zzitk.zze(this.zzjds);
    }
}
