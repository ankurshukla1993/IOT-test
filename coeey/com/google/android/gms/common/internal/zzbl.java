package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

final class zzbl implements zza {
    private /* synthetic */ PendingResult zzfym;
    private /* synthetic */ TaskCompletionSource zzfyn;
    private /* synthetic */ zzbo zzfyo;
    private /* synthetic */ zzbp zzfyp;

    zzbl(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, zzbo com_google_android_gms_common_internal_zzbo, zzbp com_google_android_gms_common_internal_zzbp) {
        this.zzfym = pendingResult;
        this.zzfyn = taskCompletionSource;
        this.zzfyo = com_google_android_gms_common_internal_zzbo;
        this.zzfyp = com_google_android_gms_common_internal_zzbp;
    }

    public final void zzr(Status status) {
        if (status.isSuccess()) {
            this.zzfyn.setResult(this.zzfyo.zzb(this.zzfym.await(0, TimeUnit.MILLISECONDS)));
            return;
        }
        this.zzfyn.setException(this.zzfyp.zzz(status));
    }
}
