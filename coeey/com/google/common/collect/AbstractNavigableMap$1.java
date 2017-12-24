package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class AbstractNavigableMap$1 extends Maps$EntrySet<K, V> {
    final /* synthetic */ AbstractNavigableMap this$0;

    AbstractNavigableMap$1(AbstractNavigableMap abstractNavigableMap) {
        this.this$0 = abstractNavigableMap;
    }

    Map<K, V> map() {
        return this.this$0;
    }

    public Iterator<Entry<K, V>> iterator() {
        return this.this$0.entryIterator();
    }
}
