package com.google.android.gms.common.api.internal;

final class zzde implements Runnable {
    private /* synthetic */ String zzao;
    private /* synthetic */ LifecycleCallback zzfrn;
    private /* synthetic */ zzdd zzfsc;

    zzde(zzdd com_google_android_gms_common_api_internal_zzdd, LifecycleCallback lifecycleCallback, String str) {
        this.zzfsc = com_google_android_gms_common_api_internal_zzdd;
        this.zzfrn = lifecycleCallback;
        this.zzao = str;
    }

    public final void run() {
        if (this.zzfsc.zzbzn > 0) {
            this.zzfrn.onCreate(this.zzfsc.zzfrm != null ? this.zzfsc.zzfrm.getBundle(this.zzao) : null);
        }
        if (this.zzfsc.zzbzn >= 2) {
            this.zzfrn.onStart();
        }
        if (this.zzfsc.zzbzn >= 3) {
            this.zzfrn.onResume();
        }
        if (this.zzfsc.zzbzn >= 4) {
            this.zzfrn.onStop();
        }
        if (this.zzfsc.zzbzn >= 5) {
            this.zzfrn.onDestroy();
        }
    }
}
