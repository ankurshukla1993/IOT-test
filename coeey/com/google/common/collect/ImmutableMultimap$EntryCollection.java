package com.google.common.collect;

import java.util.Map.Entry;

class ImmutableMultimap$EntryCollection<K, V> extends ImmutableCollection<Entry<K, V>> {
    private static final long serialVersionUID = 0;
    final ImmutableMultimap<K, V> multimap;

    ImmutableMultimap$EntryCollection(ImmutableMultimap<K, V> multimap) {
        this.multimap = multimap;
    }

    public UnmodifiableIterator<Entry<K, V>> iterator() {
        return this.multimap.entryIterator();
    }

    boolean isPartialView() {
        return this.multimap.isPartialView();
    }

    public int size() {
        return this.multimap.size();
    }

    public boolean contains(Object object) {
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry<?, ?> entry = (Entry) object;
        return this.multimap.containsEntry(entry.getKey(), entry.getValue());
    }
}
