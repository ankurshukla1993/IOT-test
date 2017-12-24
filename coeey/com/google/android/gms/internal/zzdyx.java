package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzdyx<T> implements Iterator<T> {
    private Iterator<Entry<T, Void>> zzmhm;

    public zzdyx(Iterator<Entry<T, Void>> it) {
        this.zzmhm = it;
    }

    public final boolean hasNext() {
        return this.zzmhm.hasNext();
    }

    public final T next() {
        return ((Entry) this.zzmhm.next()).getKey();
    }

    public final void remove() {
        this.zzmhm.remove();
    }
}
