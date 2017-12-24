package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult> {
    private final zzn<TResult> zzkrs = new zzn();

    @NonNull
    public Task<TResult> getTask() {
        return this.zzkrs;
    }

    public void setException(@NonNull Exception exception) {
        this.zzkrs.setException(exception);
    }

    public void setResult(TResult tResult) {
        this.zzkrs.setResult(tResult);
    }

    public boolean trySetException(@NonNull Exception exception) {
        return this.zzkrs.trySetException(exception);
    }

    public boolean trySetResult(TResult tResult) {
        return this.zzkrs.trySetResult(tResult);
    }
}
