package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzffb<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zzmhm;

    public zzffb(Iterator<Entry<K, Object>> it) {
        this.zzmhm = it;
    }

    public final boolean hasNext() {
        return this.zzmhm.hasNext();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zzmhm.next();
        return entry.getValue() instanceof zzfey ? new zzffa(entry) : entry;
    }

    public final void remove() {
        this.zzmhm.remove();
    }
}
