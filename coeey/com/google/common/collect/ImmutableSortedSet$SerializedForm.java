package com.google.common.collect;

import java.io.Serializable;
import java.util.Comparator;

class ImmutableSortedSet$SerializedForm<E> implements Serializable {
    private static final long serialVersionUID = 0;
    final Comparator<? super E> comparator;
    final Object[] elements;

    public ImmutableSortedSet$SerializedForm(Comparator<? super E> comparator, Object[] elements) {
        this.comparator = comparator;
        this.elements = elements;
    }

    Object readResolve() {
        return new ImmutableSortedSet$Builder(this.comparator).add(this.elements).build();
    }
}
