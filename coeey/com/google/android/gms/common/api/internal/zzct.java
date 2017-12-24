package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzct<A extends zzb, L> {
    private final zzcl<L> zzfry;

    protected zzct(zzcl<L> com_google_android_gms_common_api_internal_zzcl_L) {
        this.zzfry = com_google_android_gms_common_api_internal_zzcl_L;
    }

    public final zzcn<L> zzajc() {
        return this.zzfry.zzajc();
    }

    public final void zzajd() {
        this.zzfry.clear();
    }

    protected abstract void zzb(A a, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException;
}
