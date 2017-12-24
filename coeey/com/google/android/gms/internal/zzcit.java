package com.google.android.gms.internal;

final class zzcit implements Runnable {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zzjdv;
    private /* synthetic */ zzcik zzjeh;
    private /* synthetic */ long zzjem;
    private /* synthetic */ Object zzjer;

    zzcit(zzcik com_google_android_gms_internal_zzcik, String str, String str2, Object obj, long j) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
        this.zzjdv = str;
        this.val$name = str2;
        this.zzjer = obj;
        this.zzjem = j;
    }

    public final void run() {
        this.zzjeh.zza(this.zzjdv, this.val$name, this.zzjer, this.zzjem);
    }
}
