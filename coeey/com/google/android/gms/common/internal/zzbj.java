package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbj {
    private static final zzbp zzfyl = new zzbk();

    public static <R extends Result, T extends Response<R>> Task<T> zza(PendingResult<R> pendingResult, T t) {
        return zza((PendingResult) pendingResult, new zzbm(t));
    }

    public static <R extends Result, T> Task<T> zza(PendingResult<R> pendingResult, zzbo<R, T> com_google_android_gms_common_internal_zzbo_R__T) {
        zzbp com_google_android_gms_common_internal_zzbp = zzfyl;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.zza(new zzbl(pendingResult, taskCompletionSource, com_google_android_gms_common_internal_zzbo_R__T, com_google_android_gms_common_internal_zzbp));
        return taskCompletionSource.getTask();
    }

    public static <R extends Result> Task<Void> zzb(PendingResult<R> pendingResult) {
        return zza((PendingResult) pendingResult, new zzbn());
    }
}
