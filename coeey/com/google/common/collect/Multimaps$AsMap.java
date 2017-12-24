package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class Multimaps$AsMap<K, V> extends Maps$ImprovedAbstractMap<K, Collection<V>> {
    private final Multimap<K, V> multimap;

    class EntrySet extends Maps$EntrySet<K, Collection<V>> {

        class C16911 implements Function<K, Collection<V>> {
            C16911() {
            }

            public Collection<V> apply(K key) {
                return Multimaps$AsMap.this.multimap.get(key);
            }
        }

        EntrySet() {
        }

        Map<K, Collection<V>> map() {
            return Multimaps$AsMap.this;
        }

        public Iterator<Entry<K, Collection<V>>> iterator() {
            return Maps.asMapEntryIterator(Multimaps$AsMap.this.multimap.keySet(), new C16911());
        }

        public boolean remove(Object o) {
            if (!contains(o)) {
                return false;
            }
            Multimaps$AsMap.this.removeValuesForKey(((Entry) o).getKey());
            return true;
        }
    }

    Multimaps$AsMap(Multimap<K, V> multimap) {
        this.multimap = (Multimap) Preconditions.checkNotNull(multimap);
    }

    public int size() {
        return this.multimap.keySet().size();
    }

    protected Set<Entry<K, Collection<V>>> createEntrySet() {
        return new EntrySet();
    }

    void removeValuesForKey(Object key) {
        this.multimap.keySet().remove(key);
    }

    public Collection<V> get(Object key) {
        return containsKey(key) ? this.multimap.get(key) : null;
    }

    public Collection<V> remove(Object key) {
        return containsKey(key) ? this.multimap.removeAll(key) : null;
    }

    public Set<K> keySet() {
        return this.multimap.keySet();
    }

    public boolean isEmpty() {
        return this.multimap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return this.multimap.containsKey(key);
    }

    public void clear() {
        this.multimap.clear();
    }
}
