package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ExecutorToken {
    private final HybridData mHybridData;

    @DoNotStrip
    private ExecutorToken(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
