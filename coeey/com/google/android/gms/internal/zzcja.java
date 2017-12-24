package com.google.android.gms.internal;

import android.os.Bundle;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.google.android.gms.measurement.AppMeasurement$zzb;

final class zzcja implements Runnable {
    private /* synthetic */ boolean zzjfb;
    private /* synthetic */ AppMeasurement$zzb zzjfc;
    private /* synthetic */ zzcjc zzjfd;
    private /* synthetic */ zzciz zzjfe;

    zzcja(zzciz com_google_android_gms_internal_zzciz, boolean z, AppMeasurement$zzb appMeasurement$zzb, zzcjc com_google_android_gms_internal_zzcjc) {
        this.zzjfe = com_google_android_gms_internal_zzciz;
        this.zzjfb = z;
        this.zzjfc = appMeasurement$zzb;
        this.zzjfd = com_google_android_gms_internal_zzcjc;
    }

    public final void run() {
        if (this.zzjfb && this.zzjfe.zzjes != null) {
            this.zzjfe.zza(this.zzjfe.zzjes);
        }
        Object obj = (this.zzjfc != null && this.zzjfc.zzitr == this.zzjfd.zzitr && zzckn.zzas(this.zzjfc.zzitq, this.zzjfd.zzitq) && zzckn.zzas(this.zzjfc.zzitp, this.zzjfd.zzitp)) ? null : 1;
        if (obj != null) {
            Bundle bundle = new Bundle();
            zzciz.zza(this.zzjfd, bundle);
            if (this.zzjfc != null) {
                if (this.zzjfc.zzitp != null) {
                    bundle.putString("_pn", this.zzjfc.zzitp);
                }
                bundle.putString("_pc", this.zzjfc.zzitq);
                bundle.putLong("_pi", this.zzjfc.zzitr);
            }
            this.zzjfe.zzawa().zzc(ReactScrollViewHelper.AUTO, "_vs", bundle);
        }
        this.zzjfe.zzjes = this.zzjfd;
        this.zzjfe.zzawd().zza(this.zzjfd);
    }
}
