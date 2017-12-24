package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;

public class ImmutableMultiset$Builder<E> extends ImmutableCollection$Builder<E> {
    final Multiset<E> contents;

    public ImmutableMultiset$Builder() {
        this(LinkedHashMultiset.create());
    }

    ImmutableMultiset$Builder(Multiset<E> contents) {
        this.contents = contents;
    }

    public ImmutableMultiset$Builder<E> add(E element) {
        this.contents.add(Preconditions.checkNotNull(element));
        return this;
    }

    public ImmutableMultiset$Builder<E> addCopies(E element, int occurrences) {
        this.contents.add(Preconditions.checkNotNull(element), occurrences);
        return this;
    }

    public ImmutableMultiset$Builder<E> setCount(E element, int count) {
        this.contents.setCount(Preconditions.checkNotNull(element), count);
        return this;
    }

    public ImmutableMultiset$Builder<E> add(E... elements) {
        super.add((Object[]) elements);
        return this;
    }

    public ImmutableMultiset$Builder<E> addAll(Iterable<? extends E> elements) {
        if (elements instanceof Multiset) {
            for (Entry<? extends E> entry : Multisets.cast(elements).entrySet()) {
                addCopies(entry.getElement(), entry.getCount());
            }
        } else {
            super.addAll((Iterable) elements);
        }
        return this;
    }

    public ImmutableMultiset$Builder<E> addAll(Iterator<? extends E> elements) {
        super.addAll((Iterator) elements);
        return this;
    }

    public ImmutableMultiset<E> build() {
        return ImmutableMultiset.copyOf(this.contents);
    }
}
