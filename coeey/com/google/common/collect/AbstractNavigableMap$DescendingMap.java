package com.google.common.collect;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NavigableMap;

final class AbstractNavigableMap$DescendingMap extends Maps$DescendingMap<K, V> {
    final /* synthetic */ AbstractNavigableMap this$0;

    private AbstractNavigableMap$DescendingMap(AbstractNavigableMap abstractNavigableMap) {
        this.this$0 = abstractNavigableMap;
    }

    NavigableMap<K, V> forward() {
        return this.this$0;
    }

    Iterator<Entry<K, V>> entryIterator() {
        return this.this$0.descendingEntryIterator();
    }
}
