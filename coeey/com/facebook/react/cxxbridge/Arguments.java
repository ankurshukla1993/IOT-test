package com.facebook.react.cxxbridge;

import android.os.Bundle;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Arguments {
    private static Object makeNativeObject(Object object) {
        if (object == null) {
            return null;
        }
        if ((object instanceof Float) || (object instanceof Long) || (object instanceof Byte) || (object instanceof Short)) {
            return new Double(((Number) object).doubleValue());
        }
        if (object.getClass().isArray()) {
            return makeNativeArray(object);
        }
        if (object instanceof List) {
            return makeNativeArray((List) object);
        }
        if (object instanceof Map) {
            return makeNativeMap((Map) object);
        }
        if (object instanceof Bundle) {
            return makeNativeMap((Bundle) object);
        }
        return object;
    }

    public static WritableNativeArray makeNativeArray(List objects) {
        WritableNativeArray nativeArray = new WritableNativeArray();
        if (objects != null) {
            for (Object elem : objects) {
                Object elem2 = makeNativeObject(elem2);
                if (elem2 == null) {
                    nativeArray.pushNull();
                } else if (elem2 instanceof Boolean) {
                    nativeArray.pushBoolean(((Boolean) elem2).booleanValue());
                } else if (elem2 instanceof Integer) {
                    nativeArray.pushInt(((Integer) elem2).intValue());
                } else if (elem2 instanceof Double) {
                    nativeArray.pushDouble(((Double) elem2).doubleValue());
                } else if (elem2 instanceof String) {
                    nativeArray.pushString((String) elem2);
                } else if (elem2 instanceof WritableNativeArray) {
                    nativeArray.pushArray((WritableNativeArray) elem2);
                } else if (elem2 instanceof WritableNativeMap) {
                    nativeArray.pushMap((WritableNativeMap) elem2);
                } else {
                    throw new IllegalArgumentException("Could not convert " + elem2.getClass());
                }
            }
        }
        return nativeArray;
    }

    public static <T> WritableNativeArray makeNativeArray(final Object objects) {
        if (objects == null) {
            return new WritableNativeArray();
        }
        return makeNativeArray(new AbstractList() {
            public int size() {
                return Array.getLength(objects);
            }

            public Object get(int index) {
                return Array.get(objects, index);
            }
        });
    }

    private static void addEntry(WritableNativeMap nativeMap, String key, Object value) {
        value = makeNativeObject(value);
        if (value == null) {
            nativeMap.putNull(key);
        } else if (value instanceof Boolean) {
            nativeMap.putBoolean(key, ((Boolean) value).booleanValue());
        } else if (value instanceof Integer) {
            nativeMap.putInt(key, ((Integer) value).intValue());
        } else if (value instanceof Number) {
            nativeMap.putDouble(key, ((Number) value).doubleValue());
        } else if (value instanceof String) {
            nativeMap.putString(key, (String) value);
        } else if (value instanceof WritableNativeArray) {
            nativeMap.putArray(key, (WritableNativeArray) value);
        } else if (value instanceof WritableNativeMap) {
            nativeMap.putMap(key, (WritableNativeMap) value);
        } else {
            throw new IllegalArgumentException("Could not convert " + value.getClass());
        }
    }

    public static WritableNativeMap makeNativeMap(Map<String, Object> objects) {
        WritableNativeMap nativeMap = new WritableNativeMap();
        if (objects != null) {
            for (Entry<String, Object> entry : objects.entrySet()) {
                addEntry(nativeMap, (String) entry.getKey(), entry.getValue());
            }
        }
        return nativeMap;
    }

    public static WritableNativeMap makeNativeMap(Bundle bundle) {
        WritableNativeMap nativeMap = new WritableNativeMap();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                addEntry(nativeMap, key, bundle.get(key));
            }
        }
        return nativeMap;
    }
}
