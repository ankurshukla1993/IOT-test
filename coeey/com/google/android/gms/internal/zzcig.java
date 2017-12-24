package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement$zzb;

final class zzcig implements Runnable {
    private /* synthetic */ String zzijk;
    private /* synthetic */ zzcho zzjdt;
    private /* synthetic */ String zzjdz;
    private /* synthetic */ String zzjea;
    private /* synthetic */ long zzjeb;

    zzcig(zzcho com_google_android_gms_internal_zzcho, String str, String str2, String str3, long j) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjdz = str;
        this.zzijk = str2;
        this.zzjea = str3;
        this.zzjeb = j;
    }

    public final void run() {
        if (this.zzjdz == null) {
            this.zzjdt.zzitk.zzawe().zza(this.zzijk, null);
            return;
        }
        AppMeasurement$zzb appMeasurement$zzb = new AppMeasurement$zzb();
        appMeasurement$zzb.zzitp = this.zzjea;
        appMeasurement$zzb.zzitq = this.zzjdz;
        appMeasurement$zzb.zzitr = this.zzjeb;
        this.zzjdt.zzitk.zzawe().zza(this.zzijk, appMeasurement$zzb);
    }
}
