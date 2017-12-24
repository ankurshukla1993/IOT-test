package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

class Multisets$UnmodifiableMultiset<E> extends ForwardingMultiset<E> implements Serializable {
    private static final long serialVersionUID = 0;
    final Multiset<? extends E> delegate;
    transient Set<E> elementSet;
    transient Set<Entry<E>> entrySet;

    Multisets$UnmodifiableMultiset(Multiset<? extends E> delegate) {
        this.delegate = delegate;
    }

    protected Multiset<E> delegate() {
        return this.delegate;
    }

    Set<E> createElementSet() {
        return Collections.unmodifiableSet(this.delegate.elementSet());
    }

    public Set<E> elementSet() {
        Set<E> set = this.elementSet;
        if (set != null) {
            return set;
        }
        set = createElementSet();
        this.elementSet = set;
        return set;
    }

    public Set<Entry<E>> entrySet() {
        Set<Entry<E>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        set = Collections.unmodifiableSet(this.delegate.entrySet());
        this.entrySet = set;
        return set;
    }

    public Iterator<E> iterator() {
        return Iterators.unmodifiableIterator(this.delegate.iterator());
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public int add(E e, int occurences) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object element) {
        throw new UnsupportedOperationException();
    }

    public int remove(Object element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public int setCount(E e, int count) {
        throw new UnsupportedOperationException();
    }

    public boolean setCount(E e, int oldCount, int newCount) {
        throw new UnsupportedOperationException();
    }
}
