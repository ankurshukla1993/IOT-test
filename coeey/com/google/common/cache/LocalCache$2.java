package com.google.common.cache;

import com.google.common.collect.ImmutableSet;
import java.util.AbstractQueue;
import java.util.Iterator;

class LocalCache$2 extends AbstractQueue<Object> {
    LocalCache$2() {
    }

    public boolean offer(Object o) {
        return true;
    }

    public Object peek() {
        return null;
    }

    public Object poll() {
        return null;
    }

    public int size() {
        return 0;
    }

    public Iterator<Object> iterator() {
        return ImmutableSet.of().iterator();
    }
}
