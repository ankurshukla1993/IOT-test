package com.google.android.gms.common.api.internal;

abstract class zzbm {
    private final zzbk zzfqb;

    protected zzbm(zzbk com_google_android_gms_common_api_internal_zzbk) {
        this.zzfqb = com_google_android_gms_common_api_internal_zzbk;
    }

    protected abstract void zzahp();

    public final void zzc(zzbl com_google_android_gms_common_api_internal_zzbl) {
        com_google_android_gms_common_api_internal_zzbl.zzfmy.lock();
        try {
            if (com_google_android_gms_common_api_internal_zzbl.zzfpx == this.zzfqb) {
                zzahp();
                com_google_android_gms_common_api_internal_zzbl.zzfmy.unlock();
            }
        } finally {
            com_google_android_gms_common_api_internal_zzbl.zzfmy.unlock();
        }
    }
}
