package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Maps$FilteredEntryMap<K, V> extends Maps$AbstractFilteredMap<K, V> {
    final Set<Entry<K, V>> filteredEntrySet;

    private class EntrySet extends ForwardingSet<Entry<K, V>> {
        private EntrySet() {
        }

        protected Set<Entry<K, V>> delegate() {
            return Maps$FilteredEntryMap.this.filteredEntrySet;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new TransformedIterator<Entry<K, V>, Entry<K, V>>(Maps$FilteredEntryMap.this.filteredEntrySet.iterator()) {
                Entry<K, V> transform(final Entry<K, V> entry) {
                    return new ForwardingMapEntry<K, V>() {
                        protected Entry<K, V> delegate() {
                            return entry;
                        }

                        public V setValue(V newValue) {
                            Preconditions.checkArgument(Maps$FilteredEntryMap.this.apply(getKey(), newValue));
                            return super.setValue(newValue);
                        }
                    };
                }
            };
        }
    }

    class KeySet extends Maps$KeySet<K, V> {
        KeySet() {
            super(Maps$FilteredEntryMap.this);
        }

        public boolean remove(Object o) {
            if (!Maps$FilteredEntryMap.this.containsKey(o)) {
                return false;
            }
            Maps$FilteredEntryMap.this.unfiltered.remove(o);
            return true;
        }

        private boolean removeIf(Predicate<? super K> keyPredicate) {
            return Iterables.removeIf(Maps$FilteredEntryMap.this.unfiltered.entrySet(), Predicates.and(Maps$FilteredEntryMap.this.predicate, Maps.keyPredicateOnEntries(keyPredicate)));
        }

        public boolean removeAll(Collection<?> c) {
            return removeIf(Predicates.in(c));
        }

        public boolean retainAll(Collection<?> c) {
            return removeIf(Predicates.not(Predicates.in(c)));
        }

        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        public <T> T[] toArray(T[] array) {
            return Lists.newArrayList(iterator()).toArray(array);
        }
    }

    Maps$FilteredEntryMap(Map<K, V> unfiltered, Predicate<? super Entry<K, V>> entryPredicate) {
        super(unfiltered, entryPredicate);
        this.filteredEntrySet = Sets.filter(unfiltered.entrySet(), this.predicate);
    }

    protected Set<Entry<K, V>> createEntrySet() {
        return new EntrySet();
    }

    Set<K> createKeySet() {
        return new KeySet();
    }
}
