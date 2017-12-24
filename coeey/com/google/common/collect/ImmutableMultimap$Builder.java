package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public class ImmutableMultimap$Builder<K, V> {
    Multimap<K, V> builderMultimap = new ImmutableMultimap$BuilderMultimap();
    Comparator<? super K> keyComparator;
    Comparator<? super V> valueComparator;

    public ImmutableMultimap$Builder<K, V> put(K key, V value) {
        CollectPreconditions.checkEntryNotNull(key, value);
        this.builderMultimap.put(key, value);
        return this;
    }

    public ImmutableMultimap$Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
        return put(entry.getKey(), entry.getValue());
    }

    public ImmutableMultimap$Builder<K, V> putAll(K key, Iterable<? extends V> values) {
        if (key == null) {
            String str = "null key in entry: null=";
            String valueOf = String.valueOf(Iterables.toString(values));
            throw new NullPointerException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        Collection<V> valueList = this.builderMultimap.get(key);
        for (V value : values) {
            CollectPreconditions.checkEntryNotNull(key, value);
            valueList.add(value);
        }
        return this;
    }

    public ImmutableMultimap$Builder<K, V> putAll(K key, V... values) {
        return putAll((Object) key, Arrays.asList(values));
    }

    public ImmutableMultimap$Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
        for (Entry<? extends K, ? extends Collection<? extends V>> entry : multimap.asMap().entrySet()) {
            putAll(entry.getKey(), (Iterable) entry.getValue());
        }
        return this;
    }

    public ImmutableMultimap$Builder<K, V> orderKeysBy(Comparator<? super K> keyComparator) {
        this.keyComparator = (Comparator) Preconditions.checkNotNull(keyComparator);
        return this;
    }

    public ImmutableMultimap$Builder<K, V> orderValuesBy(Comparator<? super V> valueComparator) {
        this.valueComparator = (Comparator) Preconditions.checkNotNull(valueComparator);
        return this;
    }

    public ImmutableMultimap<K, V> build() {
        if (this.valueComparator != null) {
            for (Collection<V> values : this.builderMultimap.asMap().values()) {
                Collections.sort((List) values, this.valueComparator);
            }
        }
        if (this.keyComparator != null) {
            Multimap<K, V> sortedCopy = new ImmutableMultimap$BuilderMultimap();
            List<Entry<K, Collection<V>>> entries = Lists.newArrayList(this.builderMultimap.asMap().entrySet());
            Collections.sort(entries, Ordering.from(this.keyComparator).onKeys());
            for (Entry<K, Collection<V>> entry : entries) {
                sortedCopy.putAll(entry.getKey(), (Iterable) entry.getValue());
            }
            this.builderMultimap = sortedCopy;
        }
        return ImmutableMultimap.copyOf(this.builderMultimap);
    }
}
