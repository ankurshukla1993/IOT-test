package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class Response<T extends Result> {
    private T zzfkk;

    protected Response(@NonNull T t) {
        this.zzfkk = t;
    }

    @NonNull
    protected T getResult() {
        return this.zzfkk;
    }

    public void setResult(@NonNull T t) {
        this.zzfkk = t;
    }
}
