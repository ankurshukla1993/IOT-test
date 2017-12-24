package com.facebook.react.bridge;

import android.support.v4.util.Pools.SimplePool;
import javax.annotation.Nullable;

public class DynamicFromArray implements Dynamic {
    private static final SimplePool<DynamicFromArray> sPool = new SimplePool(10);
    @Nullable
    private ReadableArray mArray;
    private int mIndex = -1;

    private DynamicFromArray() {
    }

    public static DynamicFromArray create(ReadableArray array, int index) {
        DynamicFromArray dynamic = (DynamicFromArray) sPool.acquire();
        if (dynamic == null) {
            dynamic = new DynamicFromArray();
        }
        dynamic.mArray = array;
        dynamic.mIndex = index;
        return dynamic;
    }

    public void recycle() {
        this.mArray = null;
        this.mIndex = -1;
        sPool.release(this);
    }

    public boolean isNull() {
        if (this.mArray != null) {
            return this.mArray.isNull(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public boolean asBoolean() {
        if (this.mArray != null) {
            return this.mArray.getBoolean(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public double asDouble() {
        if (this.mArray != null) {
            return this.mArray.getDouble(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public int asInt() {
        if (this.mArray != null) {
            return this.mArray.getInt(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public String asString() {
        if (this.mArray != null) {
            return this.mArray.getString(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableArray asArray() {
        if (this.mArray != null) {
            return this.mArray.getArray(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableMap asMap() {
        if (this.mArray != null) {
            return this.mArray.getMap(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }

    public ReadableType getType() {
        if (this.mArray != null) {
            return this.mArray.getType(this.mIndex);
        }
        throw new IllegalStateException("This dynamic value has been recycled");
    }
}
