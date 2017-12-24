package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zze<TResult> extends zza {
    private final TaskCompletionSource<TResult> zzeay;
    private final zzdf<zzb, TResult> zzfky;
    private final zzdb zzfkz;

    public zze(int i, zzdf<zzb, TResult> com_google_android_gms_common_api_internal_zzdf_com_google_android_gms_common_api_Api_zzb__TResult, TaskCompletionSource<TResult> taskCompletionSource, zzdb com_google_android_gms_common_api_internal_zzdb) {
        super(i);
        this.zzeay = taskCompletionSource;
        this.zzfky = com_google_android_gms_common_api_internal_zzdf_com_google_android_gms_common_api_Api_zzb__TResult;
        this.zzfkz = com_google_android_gms_common_api_internal_zzdb;
    }

    public final void zza(@NonNull zzah com_google_android_gms_common_api_internal_zzah, boolean z) {
        com_google_android_gms_common_api_internal_zzah.zza(this.zzeay, z);
    }

    public final void zza(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws DeadObjectException {
        try {
            this.zzfky.zza(com_google_android_gms_common_api_internal_zzbr_.zzahd(), this.zzeay);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            zzs(zza.zza(e2));
        }
    }

    public final void zzs(@NonNull Status status) {
        this.zzeay.trySetException(this.zzfkz.zzt(status));
    }
}
