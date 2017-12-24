package com.google.android.gms.internal;

final class zzckf implements Runnable {
    private /* synthetic */ long zzitz;
    private /* synthetic */ zzckc zzjgk;

    zzckf(zzckc com_google_android_gms_internal_zzckc, long j) {
        this.zzjgk = com_google_android_gms_internal_zzckc;
        this.zzitz = j;
    }

    public final void run() {
        this.zzjgk.zzbd(this.zzitz);
    }
}
