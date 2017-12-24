package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zzbu implements Runnable {
    private /* synthetic */ zzbr zzfqx;
    private /* synthetic */ ConnectionResult zzfqy;

    zzbu(zzbr com_google_android_gms_common_api_internal_zzbr, ConnectionResult connectionResult) {
        this.zzfqx = com_google_android_gms_common_api_internal_zzbr;
        this.zzfqy = connectionResult;
    }

    public final void run() {
        this.zzfqx.onConnectionFailed(this.zzfqy);
    }
}
