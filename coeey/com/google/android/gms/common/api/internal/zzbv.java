package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.zzp;

final class zzbv implements zzp {
    final /* synthetic */ zzbr zzfqx;

    zzbv(zzbr com_google_android_gms_common_api_internal_zzbr) {
        this.zzfqx = com_google_android_gms_common_api_internal_zzbr;
    }

    public final void zzait() {
        this.zzfqx.zzfqo.mHandler.post(new zzbw(this));
    }
}
