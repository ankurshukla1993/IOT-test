package com.google.android.gms.internal;

final class zzciq implements Runnable {
    private /* synthetic */ zzcik zzjeh;
    private /* synthetic */ long zzjel;

    zzciq(zzcik com_google_android_gms_internal_zzcik, long j) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
        this.zzjel = j;
    }

    public final void run() {
        this.zzjeh.zzawn().zzjaj.set(this.zzjel);
        this.zzjeh.zzawm().zzayw().zzj("Minimum session duration set", Long.valueOf(this.zzjel));
    }
}
