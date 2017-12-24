package com.google.android.gms.internal;

final class zzcfd implements Runnable {
    private /* synthetic */ long zzitz;
    private /* synthetic */ zzcfa zziua;

    zzcfd(zzcfa com_google_android_gms_internal_zzcfa, long j) {
        this.zziua = com_google_android_gms_internal_zzcfa;
        this.zzitz = j;
    }

    public final void run() {
        this.zziua.zzaj(this.zzitz);
    }
}
