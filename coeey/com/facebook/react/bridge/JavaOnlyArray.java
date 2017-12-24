package com.facebook.react.bridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaOnlyArray implements ReadableArray, WritableArray {
    private final List mBackingList;

    public static JavaOnlyArray from(List list) {
        return new JavaOnlyArray(list);
    }

    public static JavaOnlyArray of(Object... values) {
        return new JavaOnlyArray(values);
    }

    private JavaOnlyArray(Object... values) {
        this.mBackingList = Arrays.asList(values);
    }

    private JavaOnlyArray(List list) {
        this.mBackingList = new ArrayList(list);
    }

    public JavaOnlyArray() {
        this.mBackingList = new ArrayList();
    }

    public int size() {
        return this.mBackingList.size();
    }

    public boolean isNull(int index) {
        return this.mBackingList.get(index) == null;
    }

    public double getDouble(int index) {
        return ((Double) this.mBackingList.get(index)).doubleValue();
    }

    public int getInt(int index) {
        return ((Integer) this.mBackingList.get(index)).intValue();
    }

    public String getString(int index) {
        return (String) this.mBackingList.get(index);
    }

    public JavaOnlyArray getArray(int index) {
        return (JavaOnlyArray) this.mBackingList.get(index);
    }

    public boolean getBoolean(int index) {
        return ((Boolean) this.mBackingList.get(index)).booleanValue();
    }

    public JavaOnlyMap getMap(int index) {
        return (JavaOnlyMap) this.mBackingList.get(index);
    }

    public Dynamic getDynamic(int index) {
        return DynamicFromArray.create(this, index);
    }

    public ReadableType getType(int index) {
        Object object = this.mBackingList.get(index);
        if (object == null) {
            return ReadableType.Null;
        }
        if (object instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if ((object instanceof Double) || (object instanceof Float) || (object instanceof Integer)) {
            return ReadableType.Number;
        }
        if (object instanceof String) {
            return ReadableType.String;
        }
        if (object instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (object instanceof ReadableMap) {
            return ReadableType.Map;
        }
        return null;
    }

    public void pushBoolean(boolean value) {
        this.mBackingList.add(Boolean.valueOf(value));
    }

    public void pushDouble(double value) {
        this.mBackingList.add(Double.valueOf(value));
    }

    public void pushInt(int value) {
        this.mBackingList.add(Integer.valueOf(value));
    }

    public void pushString(String value) {
        this.mBackingList.add(value);
    }

    public void pushArray(WritableArray array) {
        this.mBackingList.add(array);
    }

    public void pushMap(WritableMap map) {
        this.mBackingList.add(map);
    }

    public void pushNull() {
        this.mBackingList.add(null);
    }

    public String toString() {
        return this.mBackingList.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JavaOnlyArray that = (JavaOnlyArray) o;
        if (this.mBackingList != null) {
            if (this.mBackingList.equals(that.mBackingList)) {
                return true;
            }
        } else if (that.mBackingList == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.mBackingList != null ? this.mBackingList.hashCode() : 0;
    }
}
