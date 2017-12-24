package io.realm;

import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class RealmList$RealmListItr extends RealmList$RealmItr implements ListIterator<E> {
    final /* synthetic */ RealmList this$0;

    RealmList$RealmListItr(RealmList realmList, int index) {
        this.this$0 = realmList;
        super(realmList);
        if (index < 0 || index > realmList.size()) {
            throw new IndexOutOfBoundsException("Starting location must be a valid index: [0, " + (realmList.size() - 1) + "]. Index was " + index);
        }
        this.cursor = index;
    }

    public boolean hasPrevious() {
        return this.cursor != 0;
    }

    public E previous() {
        checkConcurrentModification();
        int i = this.cursor - 1;
        try {
            E previous = this.this$0.get(i);
            this.cursor = i;
            this.lastRet = i;
            return previous;
        } catch (IndexOutOfBoundsException e) {
            checkConcurrentModification();
            throw new NoSuchElementException("Cannot access index less than zero. This was " + i + ". Remember to check hasPrevious() before using previous().");
        }
    }

    public int nextIndex() {
        return this.cursor;
    }

    public int previousIndex() {
        return this.cursor - 1;
    }

    public void set(E e) {
        this.this$0.realm.checkIfValid();
        if (this.lastRet < 0) {
            throw new IllegalStateException();
        }
        checkConcurrentModification();
        try {
            this.this$0.set(this.lastRet, e);
            this.expectedModCount = RealmList.access$500(this.this$0);
        } catch (IndexOutOfBoundsException e2) {
            throw new ConcurrentModificationException();
        }
    }

    public void add(E e) {
        this.this$0.realm.checkIfValid();
        checkConcurrentModification();
        try {
            int i = this.cursor;
            this.this$0.add(i, e);
            this.lastRet = -1;
            this.cursor = i + 1;
            this.expectedModCount = RealmList.access$600(this.this$0);
        } catch (IndexOutOfBoundsException e2) {
            throw new ConcurrentModificationException();
        }
    }
}
