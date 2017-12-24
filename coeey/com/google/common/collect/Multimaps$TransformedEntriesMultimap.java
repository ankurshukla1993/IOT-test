package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Multimaps$TransformedEntriesMultimap<K, V1, V2> extends AbstractMultimap<K, V2> {
    final Multimap<K, V1> fromMultimap;
    final Maps$EntryTransformer<? super K, ? super V1, V2> transformer;

    class C16961 implements Maps$EntryTransformer<K, Collection<V1>, Collection<V2>> {
        C16961() {
        }

        public Collection<V2> transformEntry(K key, Collection<V1> value) {
            return Multimaps$TransformedEntriesMultimap.this.transform(key, value);
        }
    }

    Multimaps$TransformedEntriesMultimap(Multimap<K, V1> fromMultimap, Maps$EntryTransformer<? super K, ? super V1, V2> transformer) {
        this.fromMultimap = (Multimap) Preconditions.checkNotNull(fromMultimap);
        this.transformer = (Maps$EntryTransformer) Preconditions.checkNotNull(transformer);
    }

    Collection<V2> transform(K key, Collection<V1> values) {
        Function<? super V1, V2> function = Maps.asValueToValueFunction(this.transformer, key);
        if (values instanceof List) {
            return Lists.transform((List) values, function);
        }
        return Collections2.transform(values, function);
    }

    Map<K, Collection<V2>> createAsMap() {
        return Maps.transformEntries(this.fromMultimap.asMap(), new C16961());
    }

    public void clear() {
        this.fromMultimap.clear();
    }

    public boolean containsKey(Object key) {
        return this.fromMultimap.containsKey(key);
    }

    Iterator<Entry<K, V2>> entryIterator() {
        return Iterators.transform(this.fromMultimap.entries().iterator(), Maps.asEntryToEntryFunction(this.transformer));
    }

    public Collection<V2> get(K key) {
        return transform(key, this.fromMultimap.get(key));
    }

    public boolean isEmpty() {
        return this.fromMultimap.isEmpty();
    }

    public Set<K> keySet() {
        return this.fromMultimap.keySet();
    }

    public Multiset<K> keys() {
        return this.fromMultimap.keys();
    }

    public boolean put(K k, V2 v2) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(K k, Iterable<? extends V2> iterable) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(Multimap<? extends K, ? extends V2> multimap) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object key, Object value) {
        return get(key).remove(value);
    }

    public Collection<V2> removeAll(Object key) {
        return transform(key, this.fromMultimap.removeAll(key));
    }

    public Collection<V2> replaceValues(K k, Iterable<? extends V2> iterable) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.fromMultimap.size();
    }

    Collection<V2> createValues() {
        return Collections2.transform(this.fromMultimap.entries(), Maps.asEntryToValueFunction(this.transformer));
    }
}
