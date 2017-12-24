package com.google.common.collect;

import java.util.Map;

public final class ImmutableBiMap$Builder<K, V> extends ImmutableMap$Builder<K, V> {
    public ImmutableBiMap$Builder<K, V> put(K key, V value) {
        super.put(key, value);
        return this;
    }

    public ImmutableBiMap$Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
        super.putAll(map);
        return this;
    }

    public ImmutableBiMap<K, V> build() {
        switch (this.size) {
            case 0:
                return ImmutableBiMap.of();
            case 1:
                return ImmutableBiMap.of(this.entries[0].getKey(), this.entries[0].getValue());
            default:
                return new RegularImmutableBiMap(this.size, this.entries);
        }
    }
}
