package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Status;

final class zzai implements zza {
    private /* synthetic */ zzs zzfnw;
    private /* synthetic */ zzah zzfnx;

    zzai(zzah com_google_android_gms_common_api_internal_zzah, zzs com_google_android_gms_common_api_internal_zzs) {
        this.zzfnx = com_google_android_gms_common_api_internal_zzah;
        this.zzfnw = com_google_android_gms_common_api_internal_zzs;
    }

    public final void zzr(Status status) {
        this.zzfnx.zzfnu.remove(this.zzfnw);
    }
}
