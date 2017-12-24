package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Maps$TransformedEntriesMap<K, V1, V2> extends Maps$ImprovedAbstractMap<K, V2> {
    final Map<K, V1> fromMap;
    final Maps$EntryTransformer<? super K, ? super V1, V2> transformer;

    class C16781 extends Maps$EntrySet<K, V2> {
        C16781() {
        }

        Map<K, V2> map() {
            return Maps$TransformedEntriesMap.this;
        }

        public Iterator<Entry<K, V2>> iterator() {
            return Iterators.transform(Maps$TransformedEntriesMap.this.fromMap.entrySet().iterator(), Maps.asEntryToEntryFunction(Maps$TransformedEntriesMap.this.transformer));
        }
    }

    Maps$TransformedEntriesMap(Map<K, V1> fromMap, Maps$EntryTransformer<? super K, ? super V1, V2> transformer) {
        this.fromMap = (Map) Preconditions.checkNotNull(fromMap);
        this.transformer = (Maps$EntryTransformer) Preconditions.checkNotNull(transformer);
    }

    public int size() {
        return this.fromMap.size();
    }

    public boolean containsKey(Object key) {
        return this.fromMap.containsKey(key);
    }

    public V2 get(Object key) {
        V1 value = this.fromMap.get(key);
        return (value != null || this.fromMap.containsKey(key)) ? this.transformer.transformEntry(key, value) : null;
    }

    public V2 remove(Object key) {
        return this.fromMap.containsKey(key) ? this.transformer.transformEntry(key, this.fromMap.remove(key)) : null;
    }

    public void clear() {
        this.fromMap.clear();
    }

    public Set<K> keySet() {
        return this.fromMap.keySet();
    }

    protected Set<Entry<K, V2>> createEntrySet() {
        return new C16781();
    }
}
