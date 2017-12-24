package com.google.common.collect;

import com.google.common.base.Predicates;
import com.google.common.collect.Maps.FilteredEntryNavigableMap;
import java.util.Collection;
import java.util.NavigableMap;

class Maps$FilteredEntryNavigableMap$1 extends Maps$NavigableKeySet<K, V> {
    final /* synthetic */ FilteredEntryNavigableMap this$0;

    Maps$FilteredEntryNavigableMap$1(FilteredEntryNavigableMap filteredEntryNavigableMap, NavigableMap x0) {
        this.this$0 = filteredEntryNavigableMap;
        super(x0);
    }

    public boolean removeAll(Collection<?> c) {
        return Iterators.removeIf(FilteredEntryNavigableMap.access$700(this.this$0).entrySet().iterator(), Predicates.and(FilteredEntryNavigableMap.access$600(this.this$0), Maps.keyPredicateOnEntries(Predicates.in(c))));
    }

    public boolean retainAll(Collection<?> c) {
        return Iterators.removeIf(FilteredEntryNavigableMap.access$700(this.this$0).entrySet().iterator(), Predicates.and(FilteredEntryNavigableMap.access$600(this.this$0), Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c)))));
    }
}
