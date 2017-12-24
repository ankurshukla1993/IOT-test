package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.HashMap;

@DoNotStrip
public class ReadableNativeMap extends NativeMap implements ReadableMap {

    @DoNotStrip
    private static class ReadableNativeMapKeySetIterator implements ReadableMapKeySetIterator {
        @DoNotStrip
        private final HybridData mHybridData;
        @DoNotStrip
        private final ReadableNativeMap mMap;

        private static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

        public native boolean hasNextKey();

        public native String nextKey();

        public ReadableNativeMapKeySetIterator(ReadableNativeMap readableNativeMap) {
            this.mMap = readableNativeMap;
            this.mHybridData = initHybrid(readableNativeMap);
        }
    }

    public native ReadableNativeArray getArray(String str);

    public native boolean getBoolean(String str);

    public native double getDouble(String str);

    public native int getInt(String str);

    public native ReadableNativeMap getMap(String str);

    public native String getString(String str);

    public native ReadableType getType(String str);

    public native boolean hasKey(String str);

    public native boolean isNull(String str);

    static {
        ReactBridge.staticInit();
    }

    protected ReadableNativeMap(HybridData hybridData) {
        super(hybridData);
    }

    public Dynamic getDynamic(String name) {
        return DynamicFromMap.create(this, name);
    }

    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableNativeMapKeySetIterator(this);
    }

    public HashMap<String, Object> toHashMap() {
        ReadableMapKeySetIterator iterator = keySetIterator();
        HashMap<String, Object> hashMap = new HashMap();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (getType(key)) {
                case Null:
                    hashMap.put(key, null);
                    break;
                case Boolean:
                    hashMap.put(key, Boolean.valueOf(getBoolean(key)));
                    break;
                case Number:
                    hashMap.put(key, Double.valueOf(getDouble(key)));
                    break;
                case String:
                    hashMap.put(key, getString(key));
                    break;
                case Map:
                    hashMap.put(key, getMap(key).toHashMap());
                    break;
                case Array:
                    hashMap.put(key, getArray(key).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + key + ".");
            }
        }
        return hashMap;
    }
}
