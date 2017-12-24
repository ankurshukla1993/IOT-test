package com.google.common.collect;

import java.util.Collection;
import java.util.LinkedHashMap;

class ImmutableSetMultimap$BuilderMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
    private static final long serialVersionUID = 0;

    ImmutableSetMultimap$BuilderMultimap() {
        super(new LinkedHashMap());
    }

    Collection<V> createCollection() {
        return Sets.newLinkedHashSet();
    }
}
