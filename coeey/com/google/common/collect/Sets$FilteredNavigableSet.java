package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets.FilteredSortedSet;
import java.util.Iterator;
import java.util.NavigableSet;
import javax.annotation.Nullable;

@GwtIncompatible("NavigableSet")
class Sets$FilteredNavigableSet<E> extends FilteredSortedSet<E> implements NavigableSet<E> {
    Sets$FilteredNavigableSet(NavigableSet<E> unfiltered, Predicate<? super E> predicate) {
        super(unfiltered, predicate);
    }

    NavigableSet<E> unfiltered() {
        return (NavigableSet) this.unfiltered;
    }

    @Nullable
    public E lower(E e) {
        return Iterators.getNext(headSet(e, false).descendingIterator(), null);
    }

    @Nullable
    public E floor(E e) {
        return Iterators.getNext(headSet(e, true).descendingIterator(), null);
    }

    public E ceiling(E e) {
        return Iterables.getFirst(tailSet(e, true), null);
    }

    public E higher(E e) {
        return Iterables.getFirst(tailSet(e, false), null);
    }

    public E pollFirst() {
        return Iterables.removeFirstMatching(unfiltered(), this.predicate);
    }

    public E pollLast() {
        return Iterables.removeFirstMatching(unfiltered().descendingSet(), this.predicate);
    }

    public NavigableSet<E> descendingSet() {
        return Sets.filter(unfiltered().descendingSet(), this.predicate);
    }

    public Iterator<E> descendingIterator() {
        return Iterators.filter(unfiltered().descendingIterator(), this.predicate);
    }

    public E last() {
        return descendingIterator().next();
    }

    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return Sets.filter(unfiltered().subSet(fromElement, fromInclusive, toElement, toInclusive), this.predicate);
    }

    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return Sets.filter(unfiltered().headSet(toElement, inclusive), this.predicate);
    }

    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return Sets.filter(unfiltered().tailSet(fromElement, inclusive), this.predicate);
    }
}
