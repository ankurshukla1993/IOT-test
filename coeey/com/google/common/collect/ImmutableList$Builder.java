package com.google.common.collect;

import java.util.Iterator;

public final class ImmutableList$Builder<E> extends ImmutableCollection$ArrayBasedBuilder<E> {
    public ImmutableList$Builder() {
        this(4);
    }

    ImmutableList$Builder(int capacity) {
        super(capacity);
    }

    public ImmutableList$Builder<E> add(E element) {
        super.add((Object) element);
        return this;
    }

    public ImmutableList$Builder<E> addAll(Iterable<? extends E> elements) {
        super.addAll(elements);
        return this;
    }

    public ImmutableList$Builder<E> add(E... elements) {
        super.add((Object[]) elements);
        return this;
    }

    public ImmutableList$Builder<E> addAll(Iterator<? extends E> elements) {
        super.addAll((Iterator) elements);
        return this;
    }

    public ImmutableList<E> build() {
        return ImmutableList.asImmutableList(this.contents, this.size);
    }
}
