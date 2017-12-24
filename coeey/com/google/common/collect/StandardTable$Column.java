package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class StandardTable$Column extends Maps$ImprovedAbstractMap<R, V> {
    final C columnKey;
    final /* synthetic */ StandardTable this$0;

    private class EntrySet extends ImprovedAbstractSet<Entry<R, V>> {
        private EntrySet() {
        }

        public Iterator<Entry<R, V>> iterator() {
            return new EntrySetIterator();
        }

        public int size() {
            int size = 0;
            for (Map<C, V> map : StandardTable$Column.this.this$0.backingMap.values()) {
                if (map.containsKey(StandardTable$Column.this.columnKey)) {
                    size++;
                }
            }
            return size;
        }

        public boolean isEmpty() {
            return !StandardTable$Column.this.this$0.containsColumn(StandardTable$Column.this.columnKey);
        }

        public void clear() {
            StandardTable$Column.this.removeFromColumnIf(Predicates.alwaysTrue());
        }

        public boolean contains(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> entry = (Entry) o;
            return StandardTable.access$400(StandardTable$Column.this.this$0, entry.getKey(), StandardTable$Column.this.columnKey, entry.getValue());
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry<?, ?> entry = (Entry) obj;
            return StandardTable.access$500(StandardTable$Column.this.this$0, entry.getKey(), StandardTable$Column.this.columnKey, entry.getValue());
        }

        public boolean retainAll(Collection<?> c) {
            return StandardTable$Column.this.removeFromColumnIf(Predicates.not(Predicates.in(c)));
        }
    }

    private class EntrySetIterator extends AbstractIterator<Entry<R, V>> {
        final Iterator<Entry<R, Map<C, V>>> iterator;

        private EntrySetIterator() {
            this.iterator = StandardTable$Column.this.this$0.backingMap.entrySet().iterator();
        }

        protected Entry<R, V> computeNext() {
            while (this.iterator.hasNext()) {
                final Entry<R, Map<C, V>> entry = (Entry) this.iterator.next();
                if (((Map) entry.getValue()).containsKey(StandardTable$Column.this.columnKey)) {
                    return new AbstractMapEntry<R, V>() {
                        public R getKey() {
                            return entry.getKey();
                        }

                        public V getValue() {
                            return ((Map) entry.getValue()).get(StandardTable$Column.this.columnKey);
                        }

                        public V setValue(V value) {
                            return ((Map) entry.getValue()).put(StandardTable$Column.this.columnKey, Preconditions.checkNotNull(value));
                        }
                    };
                }
            }
            return (Entry) endOfData();
        }
    }

    private class KeySet extends Maps$KeySet<R, V> {
        KeySet() {
            super(StandardTable$Column.this);
        }

        public boolean contains(Object obj) {
            return StandardTable$Column.this.this$0.contains(obj, StandardTable$Column.this.columnKey);
        }

        public boolean remove(Object obj) {
            return StandardTable$Column.this.this$0.remove(obj, StandardTable$Column.this.columnKey) != null;
        }

        public boolean retainAll(Collection<?> c) {
            return StandardTable$Column.this.removeFromColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
        }
    }

    private class Values extends Maps$Values<R, V> {
        Values() {
            super(StandardTable$Column.this);
        }

        public boolean remove(Object obj) {
            return obj != null && StandardTable$Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
        }

        public boolean removeAll(Collection<?> c) {
            return StandardTable$Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.in(c)));
        }

        public boolean retainAll(Collection<?> c) {
            return StandardTable$Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c))));
        }
    }

    StandardTable$Column(StandardTable standardTable, C columnKey) {
        this.this$0 = standardTable;
        this.columnKey = Preconditions.checkNotNull(columnKey);
    }

    public V put(R key, V value) {
        return this.this$0.put(key, this.columnKey, value);
    }

    public V get(Object key) {
        return this.this$0.get(key, this.columnKey);
    }

    public boolean containsKey(Object key) {
        return this.this$0.contains(key, this.columnKey);
    }

    public V remove(Object key) {
        return this.this$0.remove(key, this.columnKey);
    }

    boolean removeFromColumnIf(Predicate<? super Entry<R, V>> predicate) {
        boolean changed = false;
        Iterator<Entry<R, Map<C, V>>> iterator = this.this$0.backingMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<R, Map<C, V>> entry = (Entry) iterator.next();
            Map<C, V> map = (Map) entry.getValue();
            V value = map.get(this.columnKey);
            if (value != null && predicate.apply(Maps.immutableEntry(entry.getKey(), value))) {
                map.remove(this.columnKey);
                changed = true;
                if (map.isEmpty()) {
                    iterator.remove();
                }
            }
        }
        return changed;
    }

    Set<Entry<R, V>> createEntrySet() {
        return new EntrySet();
    }

    Set<R> createKeySet() {
        return new KeySet();
    }

    Collection<V> createValues() {
        return new Values();
    }
}
