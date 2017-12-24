package com.google.common.collect;

import java.util.Map.Entry;
import javax.annotation.Nullable;

final class ImmutableSetMultimap$EntrySet<K, V> extends ImmutableSet<Entry<K, V>> {
    private final transient ImmutableSetMultimap<K, V> multimap;

    ImmutableSetMultimap$EntrySet(ImmutableSetMultimap<K, V> multimap) {
        this.multimap = multimap;
    }

    public boolean contains(@Nullable Object object) {
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry<?, ?> entry = (Entry) object;
        return this.multimap.containsEntry(entry.getKey(), entry.getValue());
    }

    public int size() {
        return this.multimap.size();
    }

    public UnmodifiableIterator<Entry<K, V>> iterator() {
        return this.multimap.entryIterator();
    }

    boolean isPartialView() {
        return false;
    }
}
