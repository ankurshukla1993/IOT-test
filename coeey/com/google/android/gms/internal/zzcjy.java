package com.google.android.gms.internal;

import android.content.Intent;

final /* synthetic */ class zzcjy implements Runnable {
    private final zzcjx zzjga;
    private final int zzjgb;
    private final zzcgj zzjgc;
    private final Intent zzjgd;

    zzcjy(zzcjx com_google_android_gms_internal_zzcjx, int i, zzcgj com_google_android_gms_internal_zzcgj, Intent intent) {
        this.zzjga = com_google_android_gms_internal_zzcjx;
        this.zzjgb = i;
        this.zzjgc = com_google_android_gms_internal_zzcgj;
        this.zzjgd = intent;
    }

    public final void run() {
        this.zzjga.zza(this.zzjgb, this.zzjgc, this.zzjgd);
    }
}
