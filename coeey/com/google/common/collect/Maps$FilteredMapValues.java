package com.google.common.collect;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

final class Maps$FilteredMapValues<K, V> extends Maps$Values<K, V> {
    Predicate<? super Entry<K, V>> predicate;
    Map<K, V> unfiltered;

    Maps$FilteredMapValues(Map<K, V> filteredMap, Map<K, V> unfiltered, Predicate<? super Entry<K, V>> predicate) {
        super(filteredMap);
        this.unfiltered = unfiltered;
        this.predicate = predicate;
    }

    public boolean remove(Object o) {
        return Iterables.removeFirstMatching(this.unfiltered.entrySet(), Predicates.and(this.predicate, Maps.valuePredicateOnEntries(Predicates.equalTo(o)))) != null;
    }

    private boolean removeIf(Predicate<? super V> valuePredicate) {
        return Iterables.removeIf(this.unfiltered.entrySet(), Predicates.and(this.predicate, Maps.valuePredicateOnEntries(valuePredicate)));
    }

    public boolean removeAll(Collection<?> collection) {
        return removeIf(Predicates.in(collection));
    }

    public boolean retainAll(Collection<?> collection) {
        return removeIf(Predicates.not(Predicates.in(collection)));
    }

    public Object[] toArray() {
        return Lists.newArrayList(iterator()).toArray();
    }

    public <T> T[] toArray(T[] array) {
        return Lists.newArrayList(iterator()).toArray(array);
    }
}
