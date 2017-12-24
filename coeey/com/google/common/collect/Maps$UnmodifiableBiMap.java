package com.google.common.collect;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

class Maps$UnmodifiableBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
    private static final long serialVersionUID = 0;
    final BiMap<? extends K, ? extends V> delegate;
    BiMap<V, K> inverse;
    final Map<K, V> unmodifiableMap;
    transient Set<V> values;

    Maps$UnmodifiableBiMap(BiMap<? extends K, ? extends V> delegate, @Nullable BiMap<V, K> inverse) {
        this.unmodifiableMap = Collections.unmodifiableMap(delegate);
        this.delegate = delegate;
        this.inverse = inverse;
    }

    protected Map<K, V> delegate() {
        return this.unmodifiableMap;
    }

    public V forcePut(K k, V v) {
        throw new UnsupportedOperationException();
    }

    public BiMap<V, K> inverse() {
        BiMap<V, K> biMap = this.inverse;
        if (biMap != null) {
            return biMap;
        }
        biMap = new Maps$UnmodifiableBiMap(this.delegate.inverse(), this);
        this.inverse = biMap;
        return biMap;
    }

    public Set<V> values() {
        Set<V> set = this.values;
        if (set != null) {
            return set;
        }
        set = Collections.unmodifiableSet(this.delegate.values());
        this.values = set;
        return set;
    }
}
