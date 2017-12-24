package com.google.common.collect;

import java.util.Collection;
import java.util.NavigableSet;
import java.util.SortedSet;

class Maps$5 extends ForwardingNavigableSet<E> {
    final /* synthetic */ NavigableSet val$set;

    Maps$5(NavigableSet navigableSet) {
        this.val$set = navigableSet;
    }

    protected NavigableSet<E> delegate() {
        return this.val$set;
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public SortedSet<E> headSet(E toElement) {
        return Maps.access$300(super.headSet(toElement));
    }

    public SortedSet<E> subSet(E fromElement, E toElement) {
        return Maps.access$300(super.subSet(fromElement, toElement));
    }

    public SortedSet<E> tailSet(E fromElement) {
        return Maps.access$300(super.tailSet(fromElement));
    }

    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return Maps.access$400(super.headSet(toElement, inclusive));
    }

    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return Maps.access$400(super.tailSet(fromElement, inclusive));
    }

    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return Maps.access$400(super.subSet(fromElement, fromInclusive, toElement, toInclusive));
    }

    public NavigableSet<E> descendingSet() {
        return Maps.access$400(super.descendingSet());
    }
}
