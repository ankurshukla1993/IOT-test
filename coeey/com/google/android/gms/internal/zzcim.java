package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty;

final class zzcim implements Runnable {
    private /* synthetic */ zzcik zzjeh;
    private /* synthetic */ AppMeasurement$ConditionalUserProperty zzjei;

    zzcim(zzcik com_google_android_gms_internal_zzcik, AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
        this.zzjei = appMeasurement$ConditionalUserProperty;
    }

    public final void run() {
        this.zzjeh.zzb(this.zzjei);
    }
}
