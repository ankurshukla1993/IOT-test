package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Collection;
import java.util.Iterator;

abstract class Multisets$ElementSet<E> extends ImprovedAbstractSet<E> {
    abstract Multiset<E> multiset();

    Multisets$ElementSet() {
    }

    public void clear() {
        multiset().clear();
    }

    public boolean contains(Object o) {
        return multiset().contains(o);
    }

    public boolean containsAll(Collection<?> c) {
        return multiset().containsAll(c);
    }

    public boolean isEmpty() {
        return multiset().isEmpty();
    }

    public Iterator<E> iterator() {
        return new TransformedIterator<Entry<E>, E>(multiset().entrySet().iterator()) {
            E transform(Entry<E> entry) {
                return entry.getElement();
            }
        };
    }

    public boolean remove(Object o) {
        int count = multiset().count(o);
        if (count <= 0) {
            return false;
        }
        multiset().remove(o, count);
        return true;
    }

    public int size() {
        return multiset().entrySet().size();
    }
}
