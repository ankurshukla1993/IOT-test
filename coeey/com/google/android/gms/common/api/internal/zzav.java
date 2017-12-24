package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zzav extends zzbm {
    private /* synthetic */ ConnectionResult zzfou;
    private /* synthetic */ zzau zzfov;

    zzav(zzau com_google_android_gms_common_api_internal_zzau, zzbk com_google_android_gms_common_api_internal_zzbk, ConnectionResult connectionResult) {
        this.zzfov = com_google_android_gms_common_api_internal_zzau;
        this.zzfou = connectionResult;
        super(com_google_android_gms_common_api_internal_zzbk);
    }

    public final void zzahp() {
        this.zzfov.zzfor.zze(this.zzfou);
    }
}
