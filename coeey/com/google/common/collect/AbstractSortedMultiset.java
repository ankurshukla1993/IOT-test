package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.SortedMultisets.NavigableElementSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
abstract class AbstractSortedMultiset<E> extends AbstractMultiset<E> implements SortedMultiset<E> {
    @GwtTransient
    final Comparator<? super E> comparator;
    private transient SortedMultiset<E> descendingMultiset;

    abstract Iterator<Entry<E>> descendingEntryIterator();

    AbstractSortedMultiset() {
        this(Ordering.natural());
    }

    AbstractSortedMultiset(Comparator<? super E> comparator) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
    }

    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }

    NavigableSet<E> createElementSet() {
        return new NavigableElementSet(this);
    }

    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    public Entry<E> firstEntry() {
        Iterator<Entry<E>> entryIterator = entryIterator();
        return entryIterator.hasNext() ? (Entry) entryIterator.next() : null;
    }

    public Entry<E> lastEntry() {
        Iterator<Entry<E>> entryIterator = descendingEntryIterator();
        return entryIterator.hasNext() ? (Entry) entryIterator.next() : null;
    }

    public Entry<E> pollFirstEntry() {
        Iterator<Entry<E>> entryIterator = entryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Entry<E> result = (Entry) entryIterator.next();
        result = Multisets.immutableEntry(result.getElement(), result.getCount());
        entryIterator.remove();
        return result;
    }

    public Entry<E> pollLastEntry() {
        Iterator<Entry<E>> entryIterator = descendingEntryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Entry<E> result = (Entry) entryIterator.next();
        result = Multisets.immutableEntry(result.getElement(), result.getCount());
        entryIterator.remove();
        return result;
    }

    public SortedMultiset<E> subMultiset(@Nullable E fromElement, BoundType fromBoundType, @Nullable E toElement, BoundType toBoundType) {
        Preconditions.checkNotNull(fromBoundType);
        Preconditions.checkNotNull(toBoundType);
        return tailMultiset(fromElement, fromBoundType).headMultiset(toElement, toBoundType);
    }

    Iterator<E> descendingIterator() {
        return Multisets.iteratorImpl(descendingMultiset());
    }

    public SortedMultiset<E> descendingMultiset() {
        SortedMultiset<E> sortedMultiset = this.descendingMultiset;
        if (sortedMultiset != null) {
            return sortedMultiset;
        }
        sortedMultiset = createDescendingMultiset();
        this.descendingMultiset = sortedMultiset;
        return sortedMultiset;
    }

    SortedMultiset<E> createDescendingMultiset() {
        return new 1(this);
    }
}
