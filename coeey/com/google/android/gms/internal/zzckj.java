package com.google.android.gms.internal;

import android.content.Intent;

final class zzckj extends zzcfp {
    private /* synthetic */ zzcki zzjgm;

    zzckj(zzcki com_google_android_gms_internal_zzcki, zzchj com_google_android_gms_internal_zzchj) {
        this.zzjgm = com_google_android_gms_internal_zzcki;
        super(com_google_android_gms_internal_zzchj);
    }

    public final void run() {
        this.zzjgm.cancel();
        this.zzjgm.zzawm().zzayx().log("Sending upload intent from DelayedRunnable");
        Intent className = new Intent().setClassName(this.zzjgm.getContext(), "com.google.android.gms.measurement.AppMeasurementReceiver");
        className.setAction("com.google.android.gms.measurement.UPLOAD");
        this.zzjgm.getContext().sendBroadcast(className);
    }
}
