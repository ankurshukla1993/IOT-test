package com.google.android.gms.internal;

final class zzcia implements Runnable {
    private /* synthetic */ String zzijk;
    private /* synthetic */ zzcho zzjdt;
    private /* synthetic */ zzcfx zzjdx;

    zzcia(zzcho com_google_android_gms_internal_zzcho, zzcfx com_google_android_gms_internal_zzcfx, String str) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjdx = com_google_android_gms_internal_zzcfx;
        this.zzijk = str;
    }

    public final void run() {
        this.zzjdt.zzitk.zzazz();
        this.zzjdt.zzitk.zzb(this.zzjdx, this.zzijk);
    }
}
