package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

class Multimaps$MapMultimap<K, V> extends AbstractMultimap<K, V> implements SetMultimap<K, V>, Serializable {
    private static final long serialVersionUID = 7845222491160860175L;
    final Map<K, V> map;

    Multimaps$MapMultimap(Map<K, V> map) {
        this.map = (Map) Preconditions.checkNotNull(map);
    }

    public int size() {
        return this.map.size();
    }

    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    public boolean containsEntry(Object key, Object value) {
        return this.map.entrySet().contains(Maps.immutableEntry(key, value));
    }

    public Set<V> get(final K key) {
        return new ImprovedAbstractSet<V>() {

            class C16941 implements Iterator<V> {
                int f200i;

                C16941() {
                }

                public boolean hasNext() {
                    return this.f200i == 0 && Multimaps$MapMultimap.this.map.containsKey(key);
                }

                public V next() {
                    if (hasNext()) {
                        this.f200i++;
                        return Multimaps$MapMultimap.this.map.get(key);
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    boolean z = true;
                    if (this.f200i != 1) {
                        z = false;
                    }
                    CollectPreconditions.checkRemove(z);
                    this.f200i = -1;
                    Multimaps$MapMultimap.this.map.remove(key);
                }
            }

            public Iterator<V> iterator() {
                return new C16941();
            }

            public int size() {
                return Multimaps$MapMultimap.this.map.containsKey(key) ? 1 : 0;
            }
        };
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

    public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object key, Object value) {
        return this.map.entrySet().remove(Maps.immutableEntry(key, value));
    }

    public Set<V> removeAll(Object key) {
        Set<V> values = new HashSet(2);
        if (this.map.containsKey(key)) {
            values.add(this.map.remove(key));
        }
        return values;
    }

    public void clear() {
        this.map.clear();
    }

    public Set<K> keySet() {
        return this.map.keySet();
    }

    public Collection<V> values() {
        return this.map.values();
    }

    public Set<Entry<K, V>> entries() {
        return this.map.entrySet();
    }

    Iterator<Entry<K, V>> entryIterator() {
        return this.map.entrySet().iterator();
    }

    Map<K, Collection<V>> createAsMap() {
        return new Multimaps$AsMap(this);
    }

    public int hashCode() {
        return this.map.hashCode();
    }
}
