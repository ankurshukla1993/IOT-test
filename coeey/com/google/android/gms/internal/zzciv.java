package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

final class zzciv implements Callable<String> {
    private /* synthetic */ zzcik zzjeh;

    zzciv(zzcik com_google_android_gms_internal_zzcik) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
    }

    public final /* synthetic */ Object call() throws Exception {
        Object zzazb = this.zzjeh.zzawn().zzazb();
        if (zzazb == null) {
            zzcih zzawa = this.zzjeh.zzawa();
            if (zzawa.zzawl().zzazg()) {
                zzawa.zzawm().zzayr().log("Cannot retrieve app instance id from analytics worker thread");
                zzazb = null;
            } else {
                zzawa.zzawl();
                if (zzche.zzas()) {
                    zzawa.zzawm().zzayr().log("Cannot retrieve app instance id from main thread");
                    zzazb = null;
                } else {
                    long elapsedRealtime = zzawa.zzwh().elapsedRealtime();
                    zzazb = zzawa.zzbc(120000);
                    elapsedRealtime = zzawa.zzwh().elapsedRealtime() - elapsedRealtime;
                    if (zzazb == null && elapsedRealtime < 120000) {
                        zzazb = zzawa.zzbc(120000 - elapsedRealtime);
                    }
                }
            }
            if (zzazb == null) {
                throw new TimeoutException();
            }
            this.zzjeh.zzawn().zzjj(zzazb);
        }
        return zzazb;
    }
}
