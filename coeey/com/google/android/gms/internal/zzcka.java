package com.google.android.gms.internal;

final class zzcka implements Runnable {
    private /* synthetic */ zzchj zzjay;
    private /* synthetic */ Runnable zzjgg;

    zzcka(zzcjx com_google_android_gms_internal_zzcjx, zzchj com_google_android_gms_internal_zzchj, Runnable runnable) {
        this.zzjay = com_google_android_gms_internal_zzchj;
        this.zzjgg = runnable;
    }

    public final void run() {
        this.zzjay.zzazz();
        this.zzjay.zzi(this.zzjgg);
        this.zzjay.zzazv();
    }
}
