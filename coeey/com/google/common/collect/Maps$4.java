package com.google.common.collect;

import java.util.Collection;
import java.util.SortedSet;

class Maps$4 extends ForwardingSortedSet<E> {
    final /* synthetic */ SortedSet val$set;

    Maps$4(SortedSet sortedSet) {
        this.val$set = sortedSet;
    }

    protected SortedSet<E> delegate() {
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
}
