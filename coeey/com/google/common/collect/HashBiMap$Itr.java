package com.google.common.collect;

import com.google.common.collect.HashBiMap.BiEntry;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class HashBiMap$Itr<T> implements Iterator<T> {
    int expectedModCount = HashBiMap.access$000(this.this$0);
    BiEntry<K, V> next = null;
    int nextBucket = 0;
    final /* synthetic */ HashBiMap this$0;
    BiEntry<K, V> toRemove = null;

    abstract T output(BiEntry<K, V> biEntry);

    HashBiMap$Itr(HashBiMap hashBiMap) {
        this.this$0 = hashBiMap;
    }

    private void checkForConcurrentModification() {
        if (HashBiMap.access$000(this.this$0) != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean hasNext() {
        checkForConcurrentModification();
        if (this.next != null) {
            return true;
        }
        while (this.nextBucket < HashBiMap.access$100(this.this$0).length) {
            if (HashBiMap.access$100(this.this$0)[this.nextBucket] != null) {
                BiEntry[] access$100 = HashBiMap.access$100(this.this$0);
                int i = this.nextBucket;
                this.nextBucket = i + 1;
                this.next = access$100[i];
                return true;
            }
            this.nextBucket++;
        }
        return false;
    }

    public T next() {
        checkForConcurrentModification();
        if (hasNext()) {
            BiEntry<K, V> entry = this.next;
            this.next = entry.nextInKToVBucket;
            this.toRemove = entry;
            return output(entry);
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        checkForConcurrentModification();
        CollectPreconditions.checkRemove(this.toRemove != null);
        HashBiMap.access$200(this.this$0, this.toRemove);
        this.expectedModCount = HashBiMap.access$000(this.this$0);
        this.toRemove = null;
    }
}
