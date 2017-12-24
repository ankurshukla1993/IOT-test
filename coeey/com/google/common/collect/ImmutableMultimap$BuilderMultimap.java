package com.google.common.collect;

import java.util.Collection;
import java.util.LinkedHashMap;

class ImmutableMultimap$BuilderMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
    private static final long serialVersionUID = 0;

    ImmutableMultimap$BuilderMultimap() {
        super(new LinkedHashMap());
    }

    Collection<V> createCollection() {
        return Lists.newArrayList();
    }
}
