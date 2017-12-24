package com.google.android.gms.internal;

final class zzcfc implements Runnable {
    private /* synthetic */ String zzbdq;
    private /* synthetic */ long zzitz;
    private /* synthetic */ zzcfa zziua;

    zzcfc(zzcfa com_google_android_gms_internal_zzcfa, String str, long j) {
        this.zziua = com_google_android_gms_internal_zzcfa;
        this.zzbdq = str;
        this.zzitz = j;
    }

    public final void run() {
        this.zziua.zzf(this.zzbdq, this.zzitz);
    }
}
