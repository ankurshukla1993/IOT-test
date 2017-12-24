package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.Iterator;
import javax.annotation.Nullable;

final class ImmutableMultimap$Values<K, V> extends ImmutableCollection<V> {
    private static final long serialVersionUID = 0;
    private final transient ImmutableMultimap<K, V> multimap;

    ImmutableMultimap$Values(ImmutableMultimap<K, V> multimap) {
        this.multimap = multimap;
    }

    public boolean contains(@Nullable Object object) {
        return this.multimap.containsValue(object);
    }

    public UnmodifiableIterator<V> iterator() {
        return this.multimap.valueIterator();
    }

    @GwtIncompatible("not present in emulated superclass")
    int copyIntoArray(Object[] dst, int offset) {
        Iterator i$ = this.multimap.map.values().iterator();
        while (i$.hasNext()) {
            offset = ((ImmutableCollection) i$.next()).copyIntoArray(dst, offset);
        }
        return offset;
    }

    public int size() {
        return this.multimap.size();
    }

    boolean isPartialView() {
        return true;
    }
}
