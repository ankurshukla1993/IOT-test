package com.google.common.collect;

import com.google.common.primitives.Primitives;
import java.util.Map;
import java.util.Map.Entry;

public final class ImmutableClassToInstanceMap$Builder<B> {
    private final ImmutableMap$Builder<Class<? extends B>, B> mapBuilder = ImmutableMap.builder();

    public <T extends B> ImmutableClassToInstanceMap$Builder<B> put(Class<T> key, T value) {
        this.mapBuilder.put(key, value);
        return this;
    }

    public <T extends B> ImmutableClassToInstanceMap$Builder<B> putAll(Map<? extends Class<? extends T>, ? extends T> map) {
        for (Entry<? extends Class<? extends T>, ? extends T> entry : map.entrySet()) {
            Class<? extends T> type = (Class) entry.getKey();
            this.mapBuilder.put(type, cast(type, entry.getValue()));
        }
        return this;
    }

    private static <B, T extends B> T cast(Class<T> type, B value) {
        return Primitives.wrap(type).cast(value);
    }

    public ImmutableClassToInstanceMap<B> build() {
        return new ImmutableClassToInstanceMap(this.mapBuilder.build(), null);
    }
}
