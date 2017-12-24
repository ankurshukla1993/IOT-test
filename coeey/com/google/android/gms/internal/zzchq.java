package com.google.android.gms.internal;

final class zzchq implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcho zzjdt;
    private /* synthetic */ zzcfi zzjdu;

    zzchq(zzcho com_google_android_gms_internal_zzcho, zzcfi com_google_android_gms_internal_zzcfi, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjdu = com_google_android_gms_internal_zzcfi;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    public final void run() {
        this.zzjdt.zzitk.zzazz();
        this.zzjdt.zzitk.zzc(this.zzjdu, this.zzjds);
    }
}
