package com.google.android.gms.internal;

final class zzchz implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcho zzjdt;
    private /* synthetic */ zzcfx zzjdx;

    zzchz(zzcho com_google_android_gms_internal_zzcho, zzcfx com_google_android_gms_internal_zzcfx, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjdx = com_google_android_gms_internal_zzcfx;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    public final void run() {
        this.zzjdt.zzitk.zzazz();
        this.zzjdt.zzitk.zzb(this.zzjdx, this.zzjds);
    }
}
