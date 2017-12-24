package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

final class zzbj extends zzcb {
    private WeakReference<zzbd> zzfpt;

    zzbj(zzbd com_google_android_gms_common_api_internal_zzbd) {
        this.zzfpt = new WeakReference(com_google_android_gms_common_api_internal_zzbd);
    }

    public final void zzagu() {
        zzbd com_google_android_gms_common_api_internal_zzbd = (zzbd) this.zzfpt.get();
        if (com_google_android_gms_common_api_internal_zzbd != null) {
            com_google_android_gms_common_api_internal_zzbd.resume();
        }
    }
}
