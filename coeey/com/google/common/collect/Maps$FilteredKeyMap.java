package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Maps$FilteredKeyMap<K, V> extends Maps$AbstractFilteredMap<K, V> {
    Predicate<? super K> keyPredicate;

    Maps$FilteredKeyMap(Map<K, V> unfiltered, Predicate<? super K> keyPredicate, Predicate<? super Entry<K, V>> entryPredicate) {
        super(unfiltered, entryPredicate);
        this.keyPredicate = keyPredicate;
    }

    protected Set<Entry<K, V>> createEntrySet() {
        return Sets.filter(this.unfiltered.entrySet(), this.predicate);
    }

    Set<K> createKeySet() {
        return Sets.filter(this.unfiltered.keySet(), this.keyPredicate);
    }

    public boolean containsKey(Object key) {
        return this.unfiltered.containsKey(key) && this.keyPredicate.apply(key);
    }
}
