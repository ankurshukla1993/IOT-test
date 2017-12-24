package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class WritableNativeMap extends ReadableNativeMap implements WritableMap {
    private static native HybridData initHybrid();

    private native void mergeNativeMap(ReadableNativeMap readableNativeMap);

    private native void putNativeArray(String str, WritableNativeArray writableNativeArray);

    private native void putNativeMap(String str, WritableNativeMap writableNativeMap);

    public native void putBoolean(String str, boolean z);

    public native void putDouble(String str, double d);

    public native void putInt(String str, int i);

    public native void putNull(String str);

    public native void putString(String str, String str2);

    static {
        ReactBridge.staticInit();
    }

    public void putMap(String key, WritableMap value) {
        boolean z = value == null || (value instanceof WritableNativeMap);
        Assertions.assertCondition(z, "Illegal type provided");
        putNativeMap(key, (WritableNativeMap) value);
    }

    public void putArray(String key, WritableArray value) {
        boolean z = value == null || (value instanceof WritableNativeArray);
        Assertions.assertCondition(z, "Illegal type provided");
        putNativeArray(key, (WritableNativeArray) value);
    }

    public void merge(ReadableMap source) {
        Assertions.assertCondition(source instanceof ReadableNativeMap, "Illegal type provided");
        mergeNativeMap((ReadableNativeMap) source);
    }

    public WritableNativeMap() {
        super(initHybrid());
    }
}
