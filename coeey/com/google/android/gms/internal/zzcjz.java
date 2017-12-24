package com.google.android.gms.internal;

import android.app.job.JobParameters;

final /* synthetic */ class zzcjz implements Runnable {
    private final zzcjx zzjga;
    private final zzcgj zzjge;
    private final JobParameters zzjgf;

    zzcjz(zzcjx com_google_android_gms_internal_zzcjx, zzcgj com_google_android_gms_internal_zzcgj, JobParameters jobParameters) {
        this.zzjga = com_google_android_gms_internal_zzcjx;
        this.zzjge = com_google_android_gms_internal_zzcgj;
        this.zzjgf = jobParameters;
    }

    public final void run() {
        this.zzjga.zza(this.zzjge, this.zzjgf);
    }
}
