package io.realm;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class RealmList$RealmItr implements Iterator<E> {
    int cursor;
    int expectedModCount;
    int lastRet;
    final /* synthetic */ RealmList this$0;

    private RealmList$RealmItr(RealmList realmList) {
        this.this$0 = realmList;
        this.cursor = 0;
        this.lastRet = -1;
        this.expectedModCount = RealmList.access$100(this.this$0);
    }

    public boolean hasNext() {
        RealmList.access$200(this.this$0);
        checkConcurrentModification();
        return this.cursor != this.this$0.size();
    }

    public E next() {
        RealmList.access$200(this.this$0);
        checkConcurrentModification();
        int i = this.cursor;
        try {
            E next = this.this$0.get(i);
            this.lastRet = i;
            this.cursor = i + 1;
            return next;
        } catch (IndexOutOfBoundsException e) {
            checkConcurrentModification();
            throw new NoSuchElementException("Cannot access index " + i + " when size is " + this.this$0.size() + ". Remember to check hasNext() before using next().");
        }
    }

    public void remove() {
        RealmList.access$200(this.this$0);
        if (this.lastRet < 0) {
            throw new IllegalStateException("Cannot call remove() twice. Must call next() in between.");
        }
        checkConcurrentModification();
        try {
            this.this$0.remove(this.lastRet);
            if (this.lastRet < this.cursor) {
                this.cursor--;
            }
            this.lastRet = -1;
            this.expectedModCount = RealmList.access$300(this.this$0);
        } catch (IndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    final void checkConcurrentModification() {
        if (RealmList.access$400(this.this$0) != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
}
