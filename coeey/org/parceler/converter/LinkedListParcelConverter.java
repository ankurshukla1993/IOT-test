package org.parceler.converter;

import java.util.LinkedList;

public abstract class LinkedListParcelConverter<T> extends CollectionParcelConverter<T, LinkedList<T>> {
    public LinkedList<T> createCollection() {
        return new LinkedList();
    }
}
