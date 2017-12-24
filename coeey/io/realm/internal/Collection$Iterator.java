package io.realm.internal;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class Collection$Iterator<T> implements Iterator<T> {
    Collection iteratorCollection;
    protected int pos = -1;

    protected abstract T convertRowToObject(UncheckedRow uncheckedRow);

    public Collection$Iterator(Collection collection) {
        if (Collection.access$000(collection).isClosed()) {
            throw new IllegalStateException("This Realm instance has already been closed, making it unusable.");
        }
        this.iteratorCollection = collection;
        if (!Collection.access$100(collection)) {
            if (Collection.access$000(collection).isInTransaction()) {
                detach();
            } else {
                Collection.access$000(this.iteratorCollection).addIterator(this);
            }
        }
    }

    public boolean hasNext() {
        checkValid();
        return ((long) (this.pos + 1)) < this.iteratorCollection.size();
    }

    public T next() {
        checkValid();
        this.pos++;
        if (((long) this.pos) < this.iteratorCollection.size()) {
            return get(this.pos);
        }
        throw new NoSuchElementException("Cannot access index " + this.pos + " when size is " + this.iteratorCollection.size() + ". Remember to check hasNext() before using next().");
    }

    @Deprecated
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported by RealmResults iterators.");
    }

    void detach() {
        this.iteratorCollection = this.iteratorCollection.createSnapshot();
    }

    void invalidate() {
        this.iteratorCollection = null;
    }

    void checkValid() {
        if (this.iteratorCollection == null) {
            throw new ConcurrentModificationException("No outside changes to a Realm is allowed while iterating a living Realm collection.");
        }
    }

    T get(int pos) {
        return convertRowToObject(this.iteratorCollection.getUncheckedRow(pos));
    }
}
