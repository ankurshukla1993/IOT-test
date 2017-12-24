package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zze<TResult> implements zzk<TResult> {
    private final Object mLock = new Object();
    private final Executor zzkcc;
    private OnCompleteListener<TResult> zzkrk;

    public zze(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.zzkcc = executor;
        this.zzkrk = onCompleteListener;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzkrk = null;
        }
    }

    public final void onComplete(@NonNull Task<TResult> task) {
        synchronized (this.mLock) {
            if (this.zzkrk == null) {
                return;
            }
            this.zzkcc.execute(new zzf(this, task));
        }
    }
}
