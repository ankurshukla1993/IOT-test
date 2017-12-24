package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty;

final class zzcin implements Runnable {
    private /* synthetic */ zzcik zzjeh;
    private /* synthetic */ AppMeasurement$ConditionalUserProperty zzjei;

    zzcin(zzcik com_google_android_gms_internal_zzcik, AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
        this.zzjei = appMeasurement$ConditionalUserProperty;
    }

    public final void run() {
        this.zzjeh.zzc(this.zzjei);
    }
}
