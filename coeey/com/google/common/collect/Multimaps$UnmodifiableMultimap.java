package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Multimaps$UnmodifiableMultimap<K, V> extends ForwardingMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 0;
    final Multimap<K, V> delegate;
    transient Collection<Entry<K, V>> entries;
    transient Set<K> keySet;
    transient Multiset<K> keys;
    transient Map<K, Collection<V>> map;
    transient Collection<V> values;

    class C16971 implements Function<Collection<V>, Collection<V>> {
        C16971() {
        }

        public Collection<V> apply(Collection<V> collection) {
            return Multimaps.access$000(collection);
        }
    }

    Multimaps$UnmodifiableMultimap(Multimap<K, V> delegate) {
        this.delegate = (Multimap) Preconditions.checkNotNull(delegate);
    }

    protected Multimap<K, V> delegate() {
        return this.delegate;
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> result = this.map;
        if (result != null) {
            return result;
        }
        result = Collections.unmodifiableMap(Maps.transformValues(this.delegate.asMap(), new C16971()));
        this.map = result;
        return result;
    }

    public Collection<Entry<K, V>> entries() {
        Collection<Entry<K, V>> result = this.entries;
        if (result != null) {
            return result;
        }
        result = Multimaps.access$100(this.delegate.entries());
        this.entries = result;
        return result;
    }

    public Collection<V> get(K key) {
        return Multimaps.access$000(this.delegate.get(key));
    }

    public Multiset<K> keys() {
        Multiset<K> result = this.keys;
        if (result != null) {
            return result;
        }
        result = Multisets.unmodifiableMultiset(this.delegate.keys());
        this.keys = result;
        return result;
    }

    public Set<K> keySet() {
        Set<K> result = this.keySet;
        if (result != null) {
            return result;
        }
        result = Collections.unmodifiableSet(this.delegate.keySet());
        this.keySet = result;
        return result;
    }

    public boolean put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    public Collection<V> removeAll(Object key) {
        throw new UnsupportedOperationException();
    }

    public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public Collection<V> values() {
        Collection<V> result = this.values;
        if (result != null) {
            return result;
        }
        result = Collections.unmodifiableCollection(this.delegate.values());
        this.values = result;
        return result;
    }
}
