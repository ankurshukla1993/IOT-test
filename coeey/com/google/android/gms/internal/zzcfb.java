package com.google.android.gms.internal;

final class zzcfb implements Runnable {
    private /* synthetic */ String zzbdq;
    private /* synthetic */ long zzitz;
    private /* synthetic */ zzcfa zziua;

    zzcfb(zzcfa com_google_android_gms_internal_zzcfa, String str, long j) {
        this.zziua = com_google_android_gms_internal_zzcfa;
        this.zzbdq = str;
        this.zzitz = j;
    }

    public final void run() {
        this.zziua.zze(this.zzbdq, this.zzitz);
    }
}
