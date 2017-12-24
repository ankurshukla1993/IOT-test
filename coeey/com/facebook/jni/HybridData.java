package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class HybridData {
    @DoNotStrip
    private long mNativePointer = 0;

    public native void resetNative();

    static {
        SoLoader.loadLibrary("fb");
    }

    protected void finalize() throws Throwable {
        resetNative();
        super.finalize();
    }

    public boolean isValid() {
        return this.mNativePointer != 0;
    }
}
