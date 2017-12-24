package org.parceler.converter;

import java.util.HashSet;

public abstract class HashSetParcelConverter<T> extends CollectionParcelConverter<T, HashSet<T>> {
    public HashSet<T> createCollection() {
        return new HashSet();
    }
}
