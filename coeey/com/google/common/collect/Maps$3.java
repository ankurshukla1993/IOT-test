package com.google.common.collect;

import java.util.Collection;
import java.util.Set;

class Maps$3 extends ForwardingSet<E> {
    final /* synthetic */ Set val$set;

    Maps$3(Set set) {
        this.val$set = set;
    }

    protected Set<E> delegate() {
        return this.val$set;
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }
}
