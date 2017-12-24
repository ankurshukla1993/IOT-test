package com.google.android.gms.common.api.internal;

final class zzbw implements Runnable {
    private /* synthetic */ zzbv zzfqz;

    zzbw(zzbv com_google_android_gms_common_api_internal_zzbv) {
        this.zzfqz = com_google_android_gms_common_api_internal_zzbv;
    }

    public final void run() {
        this.zzfqz.zzfqx.zzfnb.disconnect();
    }
}
