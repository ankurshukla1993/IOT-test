package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet.Builder;
import java.util.Comparator;
import java.util.Iterator;

public final class ImmutableSortedSet$Builder<E> extends Builder<E> {
    private final Comparator<? super E> comparator;

    public ImmutableSortedSet$Builder(Comparator<? super E> comparator) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
    }

    public ImmutableSortedSet$Builder<E> add(E element) {
        super.add((Object) element);
        return this;
    }

    public ImmutableSortedSet$Builder<E> add(E... elements) {
        super.add((Object[]) elements);
        return this;
    }

    public ImmutableSortedSet$Builder<E> addAll(Iterable<? extends E> elements) {
        super.addAll((Iterable) elements);
        return this;
    }

    public ImmutableSortedSet$Builder<E> addAll(Iterator<? extends E> elements) {
        super.addAll((Iterator) elements);
        return this;
    }

    public ImmutableSortedSet<E> build() {
        ImmutableSortedSet<E> result = ImmutableSortedSet.construct(this.comparator, this.size, (Object[]) this.contents);
        this.size = result.size();
        return result;
    }
}
