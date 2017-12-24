package com.google.common.collect;

import com.google.common.primitives.Primitives;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class MutableClassToInstanceMap<B> extends ConstrainedMap<Class<? extends B>, B> implements ClassToInstanceMap<B> {
    private static final MapConstraint<Class<?>, Object> VALUE_CAN_BE_CAST_TO_KEY = new C17041();
    private static final long serialVersionUID = 0;

    static class C17041 implements MapConstraint<Class<?>, Object> {
        C17041() {
        }

        public void checkKeyValue(Class<?> key, Object value) {
            MutableClassToInstanceMap.cast(key, value);
        }
    }

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public /* bridge */ /* synthetic */ void putAll(Map x0) {
        super.putAll(x0);
    }

    public static <B> MutableClassToInstanceMap<B> create() {
        return new MutableClassToInstanceMap(new HashMap());
    }

    public static <B> MutableClassToInstanceMap<B> create(Map<Class<? extends B>, B> backingMap) {
        return new MutableClassToInstanceMap(backingMap);
    }

    private MutableClassToInstanceMap(Map<Class<? extends B>, B> delegate) {
        super(delegate, VALUE_CAN_BE_CAST_TO_KEY);
    }

    public <T extends B> T putInstance(Class<T> type, T value) {
        return cast(type, put(type, value));
    }

    public <T extends B> T getInstance(Class<T> type) {
        return cast(type, get(type));
    }

    private static <B, T extends B> T cast(Class<T> type, B value) {
        return Primitives.wrap(type).cast(value);
    }
}
