package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.AppCall;

public abstract class ResultProcessor {
    private FacebookCallback appCallback;

    public abstract void onSuccess(AppCall appCall, Bundle bundle);

    public ResultProcessor(FacebookCallback callback) {
        this.appCallback = callback;
    }

    public void onCancel(AppCall appCall) {
        if (this.appCallback != null) {
            this.appCallback.onCancel();
        }
    }

    public void onError(AppCall appCall, FacebookException error) {
        if (this.appCallback != null) {
            this.appCallback.onError(error);
        }
    }
}
