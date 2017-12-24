package com.google.common.collect;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

final class TreeRangeMap$AsMapOfRanges extends AbstractMap<Range<K>, V> {
    final /* synthetic */ TreeRangeMap this$0;

    class C17491 extends AbstractSet<Entry<Range<K>, V>> {
        C17491() {
        }

        public Iterator<Entry<Range<K>, V>> iterator() {
            return TreeRangeMap.access$100(TreeRangeMap$AsMapOfRanges.this.this$0).values().iterator();
        }

        public int size() {
            return TreeRangeMap.access$100(TreeRangeMap$AsMapOfRanges.this.this$0).size();
        }
    }

    private TreeRangeMap$AsMapOfRanges(TreeRangeMap treeRangeMap) {
        this.this$0 = treeRangeMap;
    }

    public boolean containsKey(@Nullable Object key) {
        return get(key) != null;
    }

    public V get(@Nullable Object key) {
        if (key instanceof Range) {
            Range<?> range = (Range) key;
            TreeRangeMap$RangeMapEntry<K, V> rangeMapEntry = (TreeRangeMap$RangeMapEntry) TreeRangeMap.access$100(this.this$0).get(range.lowerBound);
            if (rangeMapEntry != null && rangeMapEntry.getKey().equals(range)) {
                return rangeMapEntry.getValue();
            }
        }
        return null;
    }

    public Set<Entry<Range<K>, V>> entrySet() {
        return new C17491();
    }
}
