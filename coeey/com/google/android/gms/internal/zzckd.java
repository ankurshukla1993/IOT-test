package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.WorkerThread;
import com.facebook.react.views.scroll.ReactScrollViewHelper;

final class zzckd extends zzcfp {
    private /* synthetic */ zzckc zzjgk;

    zzckd(zzckc com_google_android_gms_internal_zzckc, zzchj com_google_android_gms_internal_zzchj) {
        this.zzjgk = com_google_android_gms_internal_zzckc;
        super(com_google_android_gms_internal_zzchj);
    }

    @WorkerThread
    public final void run() {
        zzcih com_google_android_gms_internal_zzcih = this.zzjgk;
        com_google_android_gms_internal_zzcih.zzut();
        com_google_android_gms_internal_zzcih.zzawm().zzayx().zzj("Session started, time", Long.valueOf(com_google_android_gms_internal_zzcih.zzwh().elapsedRealtime()));
        com_google_android_gms_internal_zzcih.zzawn().zzjal.set(false);
        com_google_android_gms_internal_zzcih.zzawa().zzc(ReactScrollViewHelper.AUTO, "_s", new Bundle());
        com_google_android_gms_internal_zzcih.zzawn().zzjam.set(com_google_android_gms_internal_zzcih.zzwh().currentTimeMillis());
    }
}
