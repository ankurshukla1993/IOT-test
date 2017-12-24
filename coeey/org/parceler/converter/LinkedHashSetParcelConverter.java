package org.parceler.converter;

import java.util.LinkedHashSet;

public abstract class LinkedHashSetParcelConverter<T> extends CollectionParcelConverter<T, LinkedHashSet<T>> {
    public LinkedHashSet<T> createCollection() {
        return new LinkedHashSet();
    }
}
