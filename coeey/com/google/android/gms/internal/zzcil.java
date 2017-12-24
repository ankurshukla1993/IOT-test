package com.google.android.gms.internal;

final class zzcil implements Runnable {
    private /* synthetic */ boolean val$enabled;
    private /* synthetic */ zzcik zzjeh;

    zzcil(zzcik com_google_android_gms_internal_zzcik, boolean z) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
        this.val$enabled = z;
    }

    public final void run() {
        this.zzjeh.zzbo(this.val$enabled);
    }
}
