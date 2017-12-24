package com.google.android.gms.common.api.internal;

import android.support.annotation.BinderThread;
import com.google.android.gms.internal.zzcwg;
import com.google.android.gms.internal.zzcwo;
import java.lang.ref.WeakReference;

final class zzay extends zzcwg {
    private final WeakReference<zzar> zzfos;

    zzay(zzar com_google_android_gms_common_api_internal_zzar) {
        this.zzfos = new WeakReference(com_google_android_gms_common_api_internal_zzar);
    }

    @BinderThread
    public final void zzb(zzcwo com_google_android_gms_internal_zzcwo) {
        zzar com_google_android_gms_common_api_internal_zzar = (zzar) this.zzfos.get();
        if (com_google_android_gms_common_api_internal_zzar != null) {
            com_google_android_gms_common_api_internal_zzar.zzfob.zza(new zzaz(this, com_google_android_gms_common_api_internal_zzar, com_google_android_gms_common_api_internal_zzar, com_google_android_gms_internal_zzcwo));
        }
    }
}
