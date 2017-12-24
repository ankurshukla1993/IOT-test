package com.google.common.collect;

import javax.annotation.Nullable;

final class MapMaker$RemovalNotification<K, V> extends ImmutableEntry<K, V> {
    private static final long serialVersionUID = 0;
    private final MapMaker$RemovalCause cause;

    MapMaker$RemovalNotification(@Nullable K key, @Nullable V value, MapMaker$RemovalCause cause) {
        super(key, value);
        this.cause = cause;
    }

    public MapMaker$RemovalCause getCause() {
        return this.cause;
    }

    public boolean wasEvicted() {
        return this.cause.wasEvicted();
    }
}
