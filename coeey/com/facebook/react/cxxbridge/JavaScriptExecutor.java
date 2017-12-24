package com.facebook.react.cxxbridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public abstract class JavaScriptExecutor {
    private final HybridData mHybridData;

    public interface Factory {
        JavaScriptExecutor create() throws Exception;
    }

    protected JavaScriptExecutor(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public void close() {
        this.mHybridData.resetNative();
    }
}
