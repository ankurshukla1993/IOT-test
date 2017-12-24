package com.google.common.collect;

import java.util.Map.Entry;

class Maps$6 extends AbstractMapEntry<K, V> {
    final /* synthetic */ Entry val$entry;

    Maps$6(Entry entry) {
        this.val$entry = entry;
    }

    public K getKey() {
        return this.val$entry.getKey();
    }

    public V getValue() {
        return this.val$entry.getValue();
    }
}
