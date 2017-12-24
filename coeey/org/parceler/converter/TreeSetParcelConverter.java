package org.parceler.converter;

import java.util.TreeSet;

public abstract class TreeSetParcelConverter<T> extends CollectionParcelConverter<T, TreeSet<T>> {
    public TreeSet<T> createCollection() {
        return new TreeSet();
    }
}
