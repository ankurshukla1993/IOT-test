package com.facebook.react.bridge;

import javax.annotation.Nullable;

public class PromiseImpl implements Promise {
    private static final String DEFAULT_ERROR = "EUNSPECIFIED";
    @Nullable
    private Callback mReject;
    @Nullable
    private Callback mResolve;

    public PromiseImpl(@Nullable Callback resolve, @Nullable Callback reject) {
        this.mResolve = resolve;
        this.mReject = reject;
    }

    public void resolve(Object value) {
        if (this.mResolve != null) {
            this.mResolve.invoke(new Object[]{value});
        }
    }

    public void reject(String code, String message) {
        reject(code, message, null);
    }

    @Deprecated
    public void reject(String message) {
        reject(DEFAULT_ERROR, message, null);
    }

    public void reject(String code, Throwable e) {
        reject(code, e.getMessage(), e);
    }

    public void reject(Throwable e) {
        reject(DEFAULT_ERROR, e.getMessage(), e);
    }

    public void reject(String code, String message, @Nullable Throwable e) {
        if (this.mReject != null) {
            if (code == null) {
                code = DEFAULT_ERROR;
            }
            WritableNativeMap errorInfo = new WritableNativeMap();
            errorInfo.putString("code", code);
            errorInfo.putString("message", message);
            this.mReject.invoke(new Object[]{errorInfo});
        }
    }
}
