package com.google.android.gms.internal;

import android.os.Bundle;

final class zzcis implements Runnable {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zzijk;
    private /* synthetic */ String zzjdv;
    private /* synthetic */ zzcik zzjeh;
    private /* synthetic */ long zzjem;
    private /* synthetic */ Bundle zzjen;
    private /* synthetic */ boolean zzjeo;
    private /* synthetic */ boolean zzjep;
    private /* synthetic */ boolean zzjeq;

    zzcis(zzcik com_google_android_gms_internal_zzcik, String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
        this.zzjdv = str;
        this.val$name = str2;
        this.zzjem = j;
        this.zzjen = bundle;
        this.zzjeo = z;
        this.zzjep = z2;
        this.zzjeq = z3;
        this.zzijk = str3;
    }

    public final void run() {
        this.zzjeh.zzb(this.zzjdv, this.val$name, this.zzjem, this.zzjen, this.zzjeo, this.zzjep, this.zzjeq, this.zzijk);
    }
}
