package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzg<TResult> implements zzk<TResult> {
    private final Object mLock = new Object();
    private final Executor zzkcc;
    private OnFailureListener zzkrm;

    public zzg(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzkcc = executor;
        this.zzkrm = onFailureListener;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzkrm = null;
        }
    }

    public final void onComplete(@NonNull Task<TResult> task) {
        if (!task.isSuccessful()) {
            synchronized (this.mLock) {
                if (this.zzkrm == null) {
                    return;
                }
                this.zzkcc.execute(new zzh(this, task));
            }
        }
    }
}
