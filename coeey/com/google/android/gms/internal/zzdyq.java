package com.google.android.gms.internal;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzdyq implements Iterator<Entry<K, V>> {
    private int zzmhe = this.zzmhf;
    private /* synthetic */ int zzmhf;
    private /* synthetic */ boolean zzmhg;
    private /* synthetic */ zzdyp zzmhh;

    zzdyq(zzdyp com_google_android_gms_internal_zzdyp, int i, boolean z) {
        this.zzmhh = com_google_android_gms_internal_zzdyp;
        this.zzmhf = i;
        this.zzmhg = z;
    }

    public final boolean hasNext() {
        return this.zzmhg ? this.zzmhe >= 0 : this.zzmhe < this.zzmhh.zzmhb.length;
    }

    public final /* synthetic */ Object next() {
        Object obj = this.zzmhh.zzmhb[this.zzmhe];
        Object obj2 = this.zzmhh.zzmhc[this.zzmhe];
        this.zzmhe = this.zzmhg ? this.zzmhe - 1 : this.zzmhe + 1;
        return new SimpleImmutableEntry(obj, obj2);
    }

    public final void remove() {
        throw new UnsupportedOperationException("Can't remove elements from ImmutableSortedMap");
    }
}
