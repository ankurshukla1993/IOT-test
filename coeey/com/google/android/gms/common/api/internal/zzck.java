package com.google.android.gms.common.api.internal;

final class zzck implements Runnable {
    private /* synthetic */ String zzao;
    private /* synthetic */ LifecycleCallback zzfrn;
    private /* synthetic */ zzcj zzfro;

    zzck(zzcj com_google_android_gms_common_api_internal_zzcj, LifecycleCallback lifecycleCallback, String str) {
        this.zzfro = com_google_android_gms_common_api_internal_zzcj;
        this.zzfrn = lifecycleCallback;
        this.zzao = str;
    }

    public final void run() {
        if (this.zzfro.zzbzn > 0) {
            this.zzfrn.onCreate(this.zzfro.zzfrm != null ? this.zzfro.zzfrm.getBundle(this.zzao) : null);
        }
        if (this.zzfro.zzbzn >= 2) {
            this.zzfrn.onStart();
        }
        if (this.zzfro.zzbzn >= 3) {
            this.zzfrn.onResume();
        }
        if (this.zzfro.zzbzn >= 4) {
            this.zzfrn.onStop();
        }
        if (this.zzfro.zzbzn >= 5) {
            this.zzfrn.onDestroy();
        }
    }
}
