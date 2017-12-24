package com.facebook.imagepipeline.request;

public abstract class BaseRepeatedPostProcessor extends BasePostprocessor implements RepeatedPostprocessor {
    private RepeatedPostprocessorRunner mCallback;

    public synchronized void setCallback(RepeatedPostprocessorRunner runner) {
        this.mCallback = runner;
    }

    private synchronized RepeatedPostprocessorRunner getCallback() {
        return this.mCallback;
    }

    public void update() {
        RepeatedPostprocessorRunner callback = getCallback();
        if (callback != null) {
            callback.update();
        }
    }
}
