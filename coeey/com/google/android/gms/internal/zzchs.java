package com.google.android.gms.internal;

final class zzchs implements Runnable {
    private /* synthetic */ zzcho zzjdt;
    private /* synthetic */ zzcfi zzjdu;

    zzchs(zzcho com_google_android_gms_internal_zzcho, zzcfi com_google_android_gms_internal_zzcfi) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjdu = com_google_android_gms_internal_zzcfi;
    }

    public final void run() {
        this.zzjdt.zzitk.zzazz();
        this.zzjdt.zzitk.zze(this.zzjdu);
    }
}
