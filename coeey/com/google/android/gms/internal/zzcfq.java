package com.google.android.gms.internal;

import android.os.Looper;

final class zzcfq implements Runnable {
    private /* synthetic */ zzcfp zziwj;

    zzcfq(zzcfp com_google_android_gms_internal_zzcfp) {
        this.zziwj = com_google_android_gms_internal_zzcfp;
    }

    public final void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.zziwj.zzitk.zzawl().zzg(this);
            return;
        }
        boolean zzdr = this.zziwj.zzdr();
        this.zziwj.zzdss = 0;
        if (zzdr && this.zziwj.zziwi) {
            this.zziwj.run();
        }
    }
}
