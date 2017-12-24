package com.google.android.gms.internal;

import android.content.ComponentName;

final class zzcjv implements Runnable {
    private /* synthetic */ zzcjr zzjfy;

    zzcjv(zzcjr com_google_android_gms_internal_zzcjr) {
        this.zzjfy = com_google_android_gms_internal_zzcjr;
    }

    public final void run() {
        this.zzjfy.zzjfo.onServiceDisconnected(new ComponentName(this.zzjfy.zzjfo.getContext(), "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
