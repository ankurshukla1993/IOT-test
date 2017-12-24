package com.facebook.react.bridge;

import android.support.v4.util.Pools.SimplePool;
import javax.annotation.Nullable;

public class DynamicFromMap implements Dynamic {
    private static final SimplePool<DynamicFromMap> sPool = new SimplePool(10);
    @Nullable
    private ReadableMap mMap;
    @Nullable
    private String mName;

    private DynamicFromMap() {
    }

    public static DynamicFromMap create(ReadableMap map, String name) {
        DynamicFromMap dynamic = (DynamicFromMap) sPool.acquire();
        if (dynamic == null) {
            dynamic = new DynamicFromMap();
        }
        dynamic.mMap = map;
        dynamic.mName = name;
        return dynamic;
    }

    public void recycle() {
        this.mMap = null;
        this.mName = null;
        sPool.release(this);
    }

    public boolean isNull() {
        if (this.mMap != null && this.mName != null) {
            return this.mMap.isNull(this.mName);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public boolean asBoolean() {
        if (this.mMap != null && this.mName != null) {
            return this.mMap.getBoolean(this.mName);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public double asDouble() {
        if (this.mMap != null && this.mName != null) {
            return this.mMap.getDouble(this.mName);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public int asInt() {
        if (this.mMap != null && this.mName != null) {
            return this.mMap.getInt(this.mName);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public String asString() {
        if (this.mMap != null && this.mName != null) {
            return this.mMap.getString(this.mName);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableArray asArray() {
        if (this.mMap != null && this.mName != null) {
            return this.mMap.getArray(this.mName);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableMap asMap() {
        if (this.mMap != null && this.mName != null) {
            return this.mMap.getMap(this.mName);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableType getType() {
        if (this.mMap != null && this.mName != null) {
            return this.mMap.getType(this.mName);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }
}
