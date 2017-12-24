package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzb<T> extends zza {
    protected final TaskCompletionSource<T> zzeay;

    public zzb(int i, TaskCompletionSource<T> taskCompletionSource) {
        super(i);
        this.zzeay = taskCompletionSource;
    }

    public void zza(@NonNull zzah com_google_android_gms_common_api_internal_zzah, boolean z) {
    }

    public final void zza(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws DeadObjectException {
        try {
            zzb(com_google_android_gms_common_api_internal_zzbr_);
        } catch (RemoteException e) {
            zzs(zza.zza(e));
            throw e;
        } catch (RemoteException e2) {
            zzs(zza.zza(e2));
        }
    }

    protected abstract void zzb(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws RemoteException;

    public void zzs(@NonNull Status status) {
        this.zzeay.trySetException(new ApiException(status));
    }
}
