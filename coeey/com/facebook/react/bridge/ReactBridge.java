package com.facebook.react.bridge;

import com.facebook.soloader.SoLoader;

public class ReactBridge {
    private static final String REACT_NATIVE_LIB = "reactnativejni";
    private static final String XREACT_NATIVE_LIB = "reactnativejnifb";

    static {
        staticInit();
    }

    public static void staticInit() {
        SoLoader.loadLibrary(REACT_NATIVE_LIB);
        SoLoader.loadLibrary(XREACT_NATIVE_LIB);
    }
}
