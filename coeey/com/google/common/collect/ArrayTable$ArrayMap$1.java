package com.google.common.collect;

import com.google.common.collect.ArrayTable.ArrayMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class ArrayTable$ArrayMap$1 extends Maps$EntrySet<K, V> {
    final /* synthetic */ ArrayMap this$0;

    ArrayTable$ArrayMap$1(ArrayMap arrayMap) {
        this.this$0 = arrayMap;
    }

    Map<K, V> map() {
        return this.this$0;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new AbstractIndexedListIterator<Entry<K, V>>(size()) {
            protected Entry<K, V> get(final int index) {
                return new AbstractMapEntry<K, V>() {
                    public K getKey() {
                        return ArrayTable$ArrayMap$1.this.this$0.getKey(index);
                    }

                    public V getValue() {
                        return ArrayTable$ArrayMap$1.this.this$0.getValue(index);
                    }

                    public V setValue(V value) {
                        return ArrayTable$ArrayMap$1.this.this$0.setValue(index, value);
                    }
                };
            }
        };
    }
}
