package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaj implements OnCompleteListener<TResult> {
    private /* synthetic */ TaskCompletionSource zzels;
    private /* synthetic */ zzah zzfnx;

    zzaj(zzah com_google_android_gms_common_api_internal_zzah, TaskCompletionSource taskCompletionSource) {
        this.zzfnx = com_google_android_gms_common_api_internal_zzah;
        this.zzels = taskCompletionSource;
    }

    public final void onComplete(@NonNull Task<TResult> task) {
        this.zzfnx.zzfnv.remove(this.zzels);
    }
}
