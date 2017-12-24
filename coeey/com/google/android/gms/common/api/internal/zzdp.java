package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzdp<A extends zzb, L> {
    private final zzcn<L> zzfrr;

    protected zzdp(zzcn<L> com_google_android_gms_common_api_internal_zzcn_L) {
        this.zzfrr = com_google_android_gms_common_api_internal_zzcn_L;
    }

    public final zzcn<L> zzajc() {
        return this.zzfrr;
    }

    protected abstract void zzc(A a, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException;
}
