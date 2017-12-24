package com.google.android.gms.internal;

final class zzcic implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcho zzjdt;
    private /* synthetic */ zzckk zzjdy;

    zzcic(zzcho com_google_android_gms_internal_zzcho, zzckk com_google_android_gms_internal_zzckk, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjdy = com_google_android_gms_internal_zzckk;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    public final void run() {
        this.zzjdt.zzitk.zzazz();
        this.zzjdt.zzitk.zzc(this.zzjdy, this.zzjds);
    }
}
