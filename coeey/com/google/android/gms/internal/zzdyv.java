package com.google.android.gms.internal;

import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Stack;

public final class zzdyv<K, V> implements Iterator<Entry<K, V>> {
    private final Stack<zzdze<K, V>> zzmhj = new Stack();
    private final boolean zzmhk;

    zzdyv(zzdza<K, V> com_google_android_gms_internal_zzdza_K__V, K k, Comparator<K> comparator, boolean z) {
        this.zzmhk = z;
        zzdza com_google_android_gms_internal_zzdza = com_google_android_gms_internal_zzdza_K__V;
        while (!com_google_android_gms_internal_zzdza.isEmpty()) {
            int compare = k != null ? z ? comparator.compare(k, com_google_android_gms_internal_zzdza.getKey()) : comparator.compare(com_google_android_gms_internal_zzdza.getKey(), k) : 1;
            if (compare < 0) {
                if (!z) {
                    com_google_android_gms_internal_zzdza = com_google_android_gms_internal_zzdza.zzbrs();
                }
            } else if (compare == 0) {
                this.zzmhj.push((zzdze) com_google_android_gms_internal_zzdza);
                return;
            } else {
                this.zzmhj.push((zzdze) com_google_android_gms_internal_zzdza);
                if (z) {
                    com_google_android_gms_internal_zzdza = com_google_android_gms_internal_zzdza.zzbrs();
                }
            }
            com_google_android_gms_internal_zzdza = com_google_android_gms_internal_zzdza.zzbrr();
        }
    }

    private final Entry<K, V> next() {
        try {
            zzdze com_google_android_gms_internal_zzdze = (zzdze) this.zzmhj.pop();
            Entry simpleEntry = new SimpleEntry(com_google_android_gms_internal_zzdze.getKey(), com_google_android_gms_internal_zzdze.getValue());
            zzdza zzbrr;
            if (this.zzmhk) {
                for (zzbrr = com_google_android_gms_internal_zzdze.zzbrr(); !zzbrr.isEmpty(); zzbrr = zzbrr.zzbrs()) {
                    this.zzmhj.push((zzdze) zzbrr);
                }
            } else {
                for (zzbrr = com_google_android_gms_internal_zzdze.zzbrs(); !zzbrr.isEmpty(); zzbrr = zzbrr.zzbrr()) {
                    this.zzmhj.push((zzdze) zzbrr);
                }
            }
            return simpleEntry;
        } catch (EmptyStackException e) {
            throw new NoSuchElementException();
        }
    }

    public final boolean hasNext() {
        return this.zzmhj.size() > 0;
    }

    public final void remove() {
        throw new UnsupportedOperationException("remove called on immutable collection");
    }
}
