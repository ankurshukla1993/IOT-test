package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Map.Entry;
import javax.annotation.Nullable;

abstract class Multimaps$Entries<K, V> extends AbstractCollection<Entry<K, V>> {
    abstract Multimap<K, V> multimap();

    Multimaps$Entries() {
    }

    public int size() {
        return multimap().size();
    }

    public boolean contains(@Nullable Object o) {
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry<?, ?> entry = (Entry) o;
        return multimap().containsEntry(entry.getKey(), entry.getValue());
    }

    public boolean remove(@Nullable Object o) {
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry<?, ?> entry = (Entry) o;
        return multimap().remove(entry.getKey(), entry.getValue());
    }

    public void clear() {
        multimap().clear();
    }
}
