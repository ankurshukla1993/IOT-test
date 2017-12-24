package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.ArrayList;

@DoNotStrip
public class ReadableNativeArray extends NativeArray implements ReadableArray {
    public native ReadableNativeArray getArray(int i);

    public native boolean getBoolean(int i);

    public native double getDouble(int i);

    public native int getInt(int i);

    public native ReadableNativeMap getMap(int i);

    public native String getString(int i);

    public native ReadableType getType(int i);

    public native boolean isNull(int i);

    public native int size();

    static {
        ReactBridge.staticInit();
    }

    protected ReadableNativeArray(HybridData hybridData) {
        super(hybridData);
    }

    public Dynamic getDynamic(int index) {
        return DynamicFromArray.create(this, index);
    }

    public ArrayList<Object> toArrayList() {
        ArrayList<Object> arrayList = new ArrayList();
        for (int i = 0; i < size(); i++) {
            switch (getType(i)) {
                case Null:
                    arrayList.add(null);
                    break;
                case Boolean:
                    arrayList.add(Boolean.valueOf(getBoolean(i)));
                    break;
                case Number:
                    arrayList.add(Double.valueOf(getDouble(i)));
                    break;
                case String:
                    arrayList.add(getString(i));
                    break;
                case Map:
                    arrayList.add(getMap(i).toHashMap());
                    break;
                case Array:
                    arrayList.add(getArray(i).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object at index: " + i + ".");
            }
        }
        return arrayList;
    }
}
