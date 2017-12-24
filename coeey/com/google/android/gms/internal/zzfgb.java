package com.google.android.gms.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzfgb extends AbstractSet<Entry<K, V>> {
    private /* synthetic */ zzffu zzped;

    private zzfgb(zzffu com_google_android_gms_internal_zzffu) {
        this.zzped = com_google_android_gms_internal_zzffu;
    }

    public final /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzped.zza((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public final void clear() {
        this.zzped.clear();
    }

    public final boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zzped.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzfga(this.zzped, null);
    }

    public final boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzped.remove(entry.getKey());
        return true;
    }

    public final int size() {
        return this.zzped.size();
    }
}
