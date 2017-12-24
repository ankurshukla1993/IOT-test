package com.facebook.react;

import com.facebook.react.bridge.WritableNativeMap;

public interface JSCConfig {
    public static final JSCConfig EMPTY = new C12721();

    static class C12721 implements JSCConfig {
        C12721() {
        }

        public WritableNativeMap getConfigMap() {
            return new WritableNativeMap();
        }
    }

    WritableNativeMap getConfigMap();
}
