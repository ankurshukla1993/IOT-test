package com.google.android.gms.common.api.internal;

final class zzbq implements zzl {
    private /* synthetic */ zzbp zzfqo;

    zzbq(zzbp com_google_android_gms_common_api_internal_zzbp) {
        this.zzfqo = com_google_android_gms_common_api_internal_zzbp;
    }

    public final void zzbe(boolean z) {
        this.zzfqo.mHandler.sendMessage(this.zzfqo.mHandler.obtainMessage(1, Boolean.valueOf(z)));
    }
}
