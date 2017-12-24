package io.realm.internal;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class Collection$ListIterator<T> extends Collection$Iterator<T> implements ListIterator<T> {
    public Collection$ListIterator(Collection collection, int start) {
        super(collection);
        if (start < 0 || ((long) start) > this.iteratorCollection.size()) {
            throw new IndexOutOfBoundsException("Starting location must be a valid index: [0, " + (this.iteratorCollection.size() - 1) + "]. Yours was " + start);
        }
        this.pos = start - 1;
    }

    @Deprecated
    public void add(T t) {
        throw new UnsupportedOperationException("Adding an element is not supported. Use Realm.createObject() instead.");
    }

    public boolean hasPrevious() {
        checkValid();
        return this.pos >= 0;
    }

    public int nextIndex() {
        checkValid();
        return this.pos + 1;
    }

    public T previous() {
        checkValid();
        try {
            T obj = get(this.pos);
            this.pos--;
            return obj;
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("Cannot access index less than zero. This was " + this.pos + ". Remember to check hasPrevious() before using previous().");
        }
    }

    public int previousIndex() {
        checkValid();
        return this.pos;
    }

    @Deprecated
    public void set(T t) {
        throw new UnsupportedOperationException("Replacing and element is not supported.");
    }
}
