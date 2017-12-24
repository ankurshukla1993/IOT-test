package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzc<A extends zzm<? extends Result, zzb>> extends zza {
    private A zzfkv;

    public zzc(int i, A a) {
        super(i);
        this.zzfkv = a;
    }

    public final void zza(@NonNull zzah com_google_android_gms_common_api_internal_zzah, boolean z) {
        com_google_android_gms_common_api_internal_zzah.zza(this.zzfkv, z);
    }

    public final void zza(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws DeadObjectException {
        this.zzfkv.zzb(com_google_android_gms_common_api_internal_zzbr_.zzahd());
    }

    public final void zzs(@NonNull Status status) {
        this.zzfkv.zzu(status);
    }
}
