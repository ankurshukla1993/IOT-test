package com.google.common.collect;

import java.util.Map.Entry;

class Maps$1 extends UnmodifiableIterator<V> {
    final /* synthetic */ UnmodifiableIterator val$entryIterator;

    Maps$1(UnmodifiableIterator unmodifiableIterator) {
        this.val$entryIterator = unmodifiableIterator;
    }

    public boolean hasNext() {
        return this.val$entryIterator.hasNext();
    }

    public V next() {
        return ((Entry) this.val$entryIterator.next()).getValue();
    }
}
