package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

@Beta
public final class MutableTypeToInstanceMap<B> extends ForwardingMap<TypeToken<? extends B>, B> implements TypeToInstanceMap<B> {
    private final Map<TypeToken<? extends B>, B> backingMap = Maps.newHashMap();

    @Nullable
    public <T extends B> T getInstance(Class<T> type) {
        return trustedGet(TypeToken.of((Class) type));
    }

    @Nullable
    public <T extends B> T putInstance(Class<T> type, @Nullable T value) {
        return trustedPut(TypeToken.of((Class) type), value);
    }

    @Nullable
    public <T extends B> T getInstance(TypeToken<T> type) {
        return trustedGet(type.rejectTypeVariables());
    }

    @Nullable
    public <T extends B> T putInstance(TypeToken<T> type, @Nullable T value) {
        return trustedPut(type.rejectTypeVariables(), value);
    }

    public B put(TypeToken<? extends B> typeToken, B b) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    public void putAll(Map<? extends TypeToken<? extends B>, ? extends B> map) {
        throw new UnsupportedOperationException("Please use putInstance() instead.");
    }

    public Set<Entry<TypeToken<? extends B>, B>> entrySet() {
        return UnmodifiableEntry.transformEntries(super.entrySet());
    }

    protected Map<TypeToken<? extends B>, B> delegate() {
        return this.backingMap;
    }

    @Nullable
    private <T extends B> T trustedPut(TypeToken<T> type, @Nullable T value) {
        return this.backingMap.put(type, value);
    }

    @Nullable
    private <T extends B> T trustedGet(TypeToken<T> type) {
        return this.backingMap.get(type);
    }
}
