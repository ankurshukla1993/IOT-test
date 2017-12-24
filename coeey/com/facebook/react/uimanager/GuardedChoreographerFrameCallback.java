package com.facebook.react.uimanager;

import android.view.Choreographer.FrameCallback;
import com.facebook.react.bridge.ReactContext;

public abstract class GuardedChoreographerFrameCallback implements FrameCallback {
    private final ReactContext mReactContext;

    protected abstract void doFrameGuarded(long j);

    protected GuardedChoreographerFrameCallback(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    public final void doFrame(long frameTimeNanos) {
        try {
            doFrameGuarded(frameTimeNanos);
        } catch (RuntimeException e) {
            this.mReactContext.handleException(e);
        }
    }
}
