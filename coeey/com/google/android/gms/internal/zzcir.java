package com.google.android.gms.internal;

final class zzcir implements Runnable {
    private /* synthetic */ zzcik zzjeh;
    private /* synthetic */ long zzjel;

    zzcir(zzcik com_google_android_gms_internal_zzcik, long j) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
        this.zzjel = j;
    }

    public final void run() {
        this.zzjeh.zzawn().zzjak.set(this.zzjel);
        this.zzjeh.zzawm().zzayw().zzj("Session timeout duration set", Long.valueOf(this.zzjel));
    }
}
