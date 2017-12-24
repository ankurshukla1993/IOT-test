package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class NativeRunnable implements Runnable {
    private final HybridData mHybridData;

    public native void run();

    private NativeRunnable(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
