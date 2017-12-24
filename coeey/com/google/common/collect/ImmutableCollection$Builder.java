package com.google.common.collect;

import java.util.Iterator;

public abstract class ImmutableCollection$Builder<E> {
    static final int DEFAULT_INITIAL_CAPACITY = 4;

    public abstract ImmutableCollection$Builder<E> add(E e);

    public abstract ImmutableCollection<E> build();

    static int expandedCapacity(int oldCapacity, int minCapacity) {
        if (minCapacity < 0) {
            throw new AssertionError("cannot store more than MAX_VALUE elements");
        }
        int newCapacity = ((oldCapacity >> 1) + oldCapacity) + 1;
        if (newCapacity < minCapacity) {
            newCapacity = Integer.highestOneBit(minCapacity - 1) << 1;
        }
        if (newCapacity < 0) {
            return Integer.MAX_VALUE;
        }
        return newCapacity;
    }

    ImmutableCollection$Builder() {
    }

    public ImmutableCollection$Builder<E> add(E... elements) {
        for (Object element : elements) {
            add(element);
        }
        return this;
    }

    public ImmutableCollection$Builder<E> addAll(Iterable<? extends E> elements) {
        for (Object element : elements) {
            add(element);
        }
        return this;
    }

    public ImmutableCollection$Builder<E> addAll(Iterator<? extends E> elements) {
        while (elements.hasNext()) {
            add(elements.next());
        }
        return this;
    }
}
