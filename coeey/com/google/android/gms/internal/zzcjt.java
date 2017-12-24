package com.google.android.gms.internal;

import android.content.ComponentName;

final class zzcjt implements Runnable {
    private /* synthetic */ ComponentName val$name;
    private /* synthetic */ zzcjr zzjfy;

    zzcjt(zzcjr com_google_android_gms_internal_zzcjr, ComponentName componentName) {
        this.zzjfy = com_google_android_gms_internal_zzcjr;
        this.val$name = componentName;
    }

    public final void run() {
        this.zzjfy.zzjfo.onServiceDisconnected(this.val$name);
    }
}
