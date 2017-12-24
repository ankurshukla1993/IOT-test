package com.facebook.react.modules.core;

import com.facebook.react.bridge.ExecutorToken;

class Timing$Timer {
    private final int mCallbackID;
    private final ExecutorToken mExecutorToken;
    private final int mInterval;
    private final boolean mRepeat;
    private long mTargetTime;

    private Timing$Timer(ExecutorToken executorToken, int callbackID, long initialTargetTime, int duration, boolean repeat) {
        this.mExecutorToken = executorToken;
        this.mCallbackID = callbackID;
        this.mTargetTime = initialTargetTime;
        this.mInterval = duration;
        this.mRepeat = repeat;
    }
}
