package com.google.android.gms.internal;

final class zzcgt implements Runnable {
    private /* synthetic */ boolean zzizs;
    private /* synthetic */ zzcgs zzizt;

    zzcgt(zzcgs com_google_android_gms_internal_zzcgs, boolean z) {
        this.zzizt = com_google_android_gms_internal_zzcgs;
        this.zzizs = z;
    }

    public final void run() {
        this.zzizt.zzitk.zzbn(this.zzizs);
    }
}
