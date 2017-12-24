package com.facebook.react.bridge;

import android.os.AsyncTask;

public abstract class GuardedAsyncTask<Params, Progress> extends AsyncTask<Params, Progress, Void> {
    private final ReactContext mReactContext;

    protected abstract void doInBackgroundGuarded(Params... paramsArr);

    protected GuardedAsyncTask(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    protected final Void doInBackground(Params... params) {
        try {
            doInBackgroundGuarded(params);
        } catch (RuntimeException e) {
            this.mReactContext.handleException(e);
        }
        return null;
    }
}
