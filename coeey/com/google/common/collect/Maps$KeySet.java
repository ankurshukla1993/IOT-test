package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;

class Maps$KeySet<K, V> extends ImprovedAbstractSet<K> {
    final Map<K, V> map;

    Maps$KeySet(Map<K, V> map) {
        this.map = (Map) Preconditions.checkNotNull(map);
    }

    Map<K, V> map() {
        return this.map;
    }

    public Iterator<K> iterator() {
        return Maps.keyIterator(map().entrySet().iterator());
    }

    public int size() {
        return map().size();
    }

    public boolean isEmpty() {
        return map().isEmpty();
    }

    public boolean contains(Object o) {
        return map().containsKey(o);
    }

    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }
        map().remove(o);
        return true;
    }

    public void clear() {
        map().clear();
    }
}
