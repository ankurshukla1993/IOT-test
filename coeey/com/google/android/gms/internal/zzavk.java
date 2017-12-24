package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.common.api.internal.zzdg;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzavk extends zzdf<zzavg, Void> {
    private TaskCompletionSource<Void> zzeay;

    private zzavk() {
    }

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzavg com_google_android_gms_internal_zzavg = (zzavg) com_google_android_gms_common_api_Api_zzb;
        this.zzeay = taskCompletionSource;
        zza((zzavc) com_google_android_gms_internal_zzavg.zzakb());
    }

    protected abstract void zza(zzavc com_google_android_gms_internal_zzavc) throws RemoteException;

    protected final void zzh(Status status) {
        zzdg.zza(status, null, this.zzeay);
    }
}
