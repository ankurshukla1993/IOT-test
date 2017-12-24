package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

@GwtCompatible
abstract class Maps$ImprovedAbstractMap<K, V> extends AbstractMap<K, V> {
    private transient Set<Entry<K, V>> entrySet;
    private transient Set<K> keySet;
    private transient Collection<V> values;

    abstract Set<Entry<K, V>> createEntrySet();

    Maps$ImprovedAbstractMap() {
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        set = createEntrySet();
        this.entrySet = set;
        return set;
    }

    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        set = createKeySet();
        this.keySet = set;
        return set;
    }

    Set<K> createKeySet() {
        return new Maps$KeySet(this);
    }

    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        collection = createValues();
        this.values = collection;
        return collection;
    }

    Collection<V> createValues() {
        return new Maps$Values(this);
    }
}
