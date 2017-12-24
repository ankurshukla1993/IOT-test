package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Iterator;

public class ImmutableSortedMultiset$Builder<E> extends ImmutableMultiset$Builder<E> {
    public ImmutableSortedMultiset$Builder(Comparator<? super E> comparator) {
        super(TreeMultiset.create((Comparator) Preconditions.checkNotNull(comparator)));
    }

    public ImmutableSortedMultiset$Builder<E> add(E element) {
        super.add((Object) element);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> addCopies(E element, int occurrences) {
        super.addCopies(element, occurrences);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> setCount(E element, int count) {
        super.setCount(element, count);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> add(E... elements) {
        super.add((Object[]) elements);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> addAll(Iterable<? extends E> elements) {
        super.addAll((Iterable) elements);
        return this;
    }

    public ImmutableSortedMultiset$Builder<E> addAll(Iterator<? extends E> elements) {
        super.addAll((Iterator) elements);
        return this;
    }

    public ImmutableSortedMultiset<E> build() {
        return ImmutableSortedMultiset.copyOfSorted((SortedMultiset) this.contents);
    }
}
