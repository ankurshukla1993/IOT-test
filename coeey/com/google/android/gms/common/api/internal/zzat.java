package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzj;
import java.lang.ref.WeakReference;

final class zzat implements zzj {
    private final Api<?> zzffv;
    private final boolean zzfmm;
    private final WeakReference<zzar> zzfos;

    public zzat(zzar com_google_android_gms_common_api_internal_zzar, Api<?> api, boolean z) {
        this.zzfos = new WeakReference(com_google_android_gms_common_api_internal_zzar);
        this.zzffv = api;
        this.zzfmm = z;
    }

    public final void zzf(@NonNull ConnectionResult connectionResult) {
        boolean z = false;
        zzar com_google_android_gms_common_api_internal_zzar = (zzar) this.zzfos.get();
        if (com_google_android_gms_common_api_internal_zzar != null) {
            if (Looper.myLooper() == com_google_android_gms_common_api_internal_zzar.zzfob.zzfmo.getLooper()) {
                z = true;
            }
            zzbq.zza(z, (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            com_google_android_gms_common_api_internal_zzar.zzfmy.lock();
            try {
                if (com_google_android_gms_common_api_internal_zzar.zzbt(0)) {
                    if (!connectionResult.isSuccess()) {
                        com_google_android_gms_common_api_internal_zzar.zzb(connectionResult, this.zzffv, this.zzfmm);
                    }
                    if (com_google_android_gms_common_api_internal_zzar.zzahq()) {
                        com_google_android_gms_common_api_internal_zzar.zzahr();
                    }
                    com_google_android_gms_common_api_internal_zzar.zzfmy.unlock();
                }
            } finally {
                com_google_android_gms_common_api_internal_zzar.zzfmy.unlock();
            }
        }
    }
}
