package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class StandardTable$Row extends Maps$ImprovedAbstractMap<C, V> {
    Map<C, V> backingRowMap;
    final R rowKey;
    final /* synthetic */ StandardTable this$0;

    private final class RowEntrySet extends Maps$EntrySet<C, V> {
        private RowEntrySet() {
        }

        Map<C, V> map() {
            return StandardTable$Row.this;
        }

        public int size() {
            Map<C, V> map = StandardTable$Row.this.backingRowMap();
            return map == null ? 0 : map.size();
        }

        public Iterator<Entry<C, V>> iterator() {
            Map<C, V> map = StandardTable$Row.this.backingRowMap();
            if (map == null) {
                return Iterators.emptyModifiableIterator();
            }
            final Iterator<Entry<C, V>> iterator = map.entrySet().iterator();
            return new Iterator<Entry<C, V>>() {
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                public Entry<C, V> next() {
                    final Entry<C, V> entry = (Entry) iterator.next();
                    return new ForwardingMapEntry<C, V>() {
                        protected Entry<C, V> delegate() {
                            return entry;
                        }

                        public V setValue(V value) {
                            return super.setValue(Preconditions.checkNotNull(value));
                        }

                        public boolean equals(Object object) {
                            return standardEquals(object);
                        }
                    };
                }

                public void remove() {
                    iterator.remove();
                    StandardTable$Row.this.maintainEmptyInvariant();
                }
            };
        }
    }

    StandardTable$Row(StandardTable standardTable, R rowKey) {
        this.this$0 = standardTable;
        this.rowKey = Preconditions.checkNotNull(rowKey);
    }

    Map<C, V> backingRowMap() {
        if (this.backingRowMap != null && (!this.backingRowMap.isEmpty() || !this.this$0.backingMap.containsKey(this.rowKey))) {
            return this.backingRowMap;
        }
        Map<C, V> computeBackingRowMap = computeBackingRowMap();
        this.backingRowMap = computeBackingRowMap;
        return computeBackingRowMap;
    }

    Map<C, V> computeBackingRowMap() {
        return (Map) this.this$0.backingMap.get(this.rowKey);
    }

    void maintainEmptyInvariant() {
        if (backingRowMap() != null && this.backingRowMap.isEmpty()) {
            this.this$0.backingMap.remove(this.rowKey);
            this.backingRowMap = null;
        }
    }

    public boolean containsKey(Object key) {
        Map<C, V> backingRowMap = backingRowMap();
        return (key == null || backingRowMap == null || !Maps.safeContainsKey(backingRowMap, key)) ? false : true;
    }

    public V get(Object key) {
        Map<C, V> backingRowMap = backingRowMap();
        return (key == null || backingRowMap == null) ? null : Maps.safeGet(backingRowMap, key);
    }

    public V put(C key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        if (this.backingRowMap == null || this.backingRowMap.isEmpty()) {
            return this.this$0.put(this.rowKey, key, value);
        }
        return this.backingRowMap.put(key, value);
    }

    public V remove(Object key) {
        Map<C, V> backingRowMap = backingRowMap();
        if (backingRowMap == null) {
            return null;
        }
        V result = Maps.safeRemove(backingRowMap, key);
        maintainEmptyInvariant();
        return result;
    }

    public void clear() {
        Map<C, V> backingRowMap = backingRowMap();
        if (backingRowMap != null) {
            backingRowMap.clear();
        }
        maintainEmptyInvariant();
    }

    protected Set<Entry<C, V>> createEntrySet() {
        return new RowEntrySet();
    }
}
