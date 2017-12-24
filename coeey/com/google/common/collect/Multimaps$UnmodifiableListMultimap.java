package com.google.common.collect;

import java.util.Collections;
import java.util.List;

class Multimaps$UnmodifiableListMultimap<K, V> extends Multimaps$UnmodifiableMultimap<K, V> implements ListMultimap<K, V> {
    private static final long serialVersionUID = 0;

    Multimaps$UnmodifiableListMultimap(ListMultimap<K, V> delegate) {
        super(delegate);
    }

    public ListMultimap<K, V> delegate() {
        return (ListMultimap) super.delegate();
    }

    public List<V> get(K key) {
        return Collections.unmodifiableList(delegate().get(key));
    }

    public List<V> removeAll(Object key) {
        throw new UnsupportedOperationException();
    }

    public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }
}
