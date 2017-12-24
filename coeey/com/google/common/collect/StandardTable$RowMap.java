package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class StandardTable$RowMap extends Maps$ImprovedAbstractMap<R, Map<C, V>> {
    final /* synthetic */ StandardTable this$0;

    class EntrySet extends StandardTable$TableSet<Entry<R, Map<C, V>>> {

        class C17351 implements Function<R, Map<C, V>> {
            C17351() {
            }

            public Map<C, V> apply(R rowKey) {
                return StandardTable$RowMap.this.this$0.row(rowKey);
            }
        }

        EntrySet() {
            super(StandardTable$RowMap.this.this$0);
        }

        public Iterator<Entry<R, Map<C, V>>> iterator() {
            return Maps.asMapEntryIterator(StandardTable$RowMap.this.this$0.backingMap.keySet(), new C17351());
        }

        public int size() {
            return StandardTable$RowMap.this.this$0.backingMap.size();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry<?, ?> entry = (Entry) obj;
            if (entry.getKey() != null && (entry.getValue() instanceof Map) && Collections2.safeContains(StandardTable$RowMap.this.this$0.backingMap.entrySet(), entry)) {
                return true;
            }
            return false;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry<?, ?> entry = (Entry) obj;
            if (entry.getKey() != null && (entry.getValue() instanceof Map) && StandardTable$RowMap.this.this$0.backingMap.entrySet().remove(entry)) {
                return true;
            }
            return false;
        }
    }

    StandardTable$RowMap(StandardTable standardTable) {
        this.this$0 = standardTable;
    }

    public boolean containsKey(Object key) {
        return this.this$0.containsRow(key);
    }

    public Map<C, V> get(Object key) {
        return this.this$0.containsRow(key) ? this.this$0.row(key) : null;
    }

    public Map<C, V> remove(Object key) {
        return key == null ? null : (Map) this.this$0.backingMap.remove(key);
    }

    protected Set<Entry<R, Map<C, V>>> createEntrySet() {
        return new EntrySet();
    }
}
