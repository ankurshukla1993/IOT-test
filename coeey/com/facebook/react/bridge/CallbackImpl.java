package com.facebook.react.bridge;

public final class CallbackImpl implements Callback {
    private final int mCallbackId;
    private final CatalystInstance mCatalystInstance;
    private final ExecutorToken mExecutorToken;
    private boolean mInvoked = false;

    public CallbackImpl(CatalystInstance bridge, ExecutorToken executorToken, int callbackId) {
        this.mCatalystInstance = bridge;
        this.mExecutorToken = executorToken;
        this.mCallbackId = callbackId;
    }

    public void invoke(Object... args) {
        if (this.mInvoked) {
            throw new RuntimeException("Illegal callback invocation from native module. This callback type only permits a single invocation from native code.");
        }
        this.mCatalystInstance.invokeCallback(this.mExecutorToken, this.mCallbackId, Arguments.fromJavaArgs(args));
        this.mInvoked = true;
    }
}
