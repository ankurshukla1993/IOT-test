package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

class Multimaps$Keys<K, V> extends AbstractMultiset<K> {
    final Multimap<K, V> multimap;

    class KeysEntrySet extends Multisets$EntrySet<K> {
        KeysEntrySet() {
        }

        Multiset<K> multiset() {
            return Multimaps$Keys.this;
        }

        public Iterator<Entry<K>> iterator() {
            return Multimaps$Keys.this.entryIterator();
        }

        public int size() {
            return Multimaps$Keys.this.distinctElements();
        }

        public boolean isEmpty() {
            return Multimaps$Keys.this.multimap.isEmpty();
        }

        public boolean contains(@Nullable Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?> entry = (Entry) o;
            Collection<V> collection = (Collection) Multimaps$Keys.this.multimap.asMap().get(entry.getElement());
            if (collection == null || collection.size() != entry.getCount()) {
                return false;
            }
            return true;
        }

        public boolean remove(@Nullable Object o) {
            if (o instanceof Entry) {
                Entry<?> entry = (Entry) o;
                Collection<V> collection = (Collection) Multimaps$Keys.this.multimap.asMap().get(entry.getElement());
                if (collection != null && collection.size() == entry.getCount()) {
                    collection.clear();
                    return true;
                }
            }
            return false;
        }
    }

    Multimaps$Keys(Multimap<K, V> multimap) {
        this.multimap = multimap;
    }

    Iterator<Entry<K>> entryIterator() {
        return new TransformedIterator<Map.Entry<K, Collection<V>>, Entry<K>>(this.multimap.asMap().entrySet().iterator()) {
            Entry<K> transform(final Map.Entry<K, Collection<V>> backingEntry) {
                return new Multisets$AbstractEntry<K>() {
                    public K getElement() {
                        return backingEntry.getKey();
                    }

                    public int getCount() {
                        return ((Collection) backingEntry.getValue()).size();
                    }
                };
            }
        };
    }

    int distinctElements() {
        return this.multimap.asMap().size();
    }

    Set<Entry<K>> createEntrySet() {
        return new KeysEntrySet();
    }

    public boolean contains(@Nullable Object element) {
        return this.multimap.containsKey(element);
    }

    public Iterator<K> iterator() {
        return Maps.keyIterator(this.multimap.entries().iterator());
    }

    public int count(@Nullable Object element) {
        Collection<V> values = (Collection) Maps.safeGet(this.multimap.asMap(), element);
        return values == null ? 0 : values.size();
    }

    public int remove(@Nullable Object element, int occurrences) {
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return count(element);
        }
        Collection<V> values = (Collection) Maps.safeGet(this.multimap.asMap(), element);
        if (values == null) {
            return 0;
        }
        int oldCount = values.size();
        if (occurrences >= oldCount) {
            values.clear();
            return oldCount;
        }
        Iterator<V> iterator = values.iterator();
        for (int i = 0; i < occurrences; i++) {
            iterator.next();
            iterator.remove();
        }
        return oldCount;
    }

    public void clear() {
        this.multimap.clear();
    }

    public Set<K> elementSet() {
        return this.multimap.keySet();
    }
}
