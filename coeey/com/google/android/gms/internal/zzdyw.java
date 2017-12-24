package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class zzdyw<T> implements Iterable<T> {
    private final zzdyr<T, Void> zzmhl;

    private zzdyw(zzdyr<T, Void> com_google_android_gms_internal_zzdyr_T__java_lang_Void) {
        this.zzmhl = com_google_android_gms_internal_zzdyr_T__java_lang_Void;
    }

    public zzdyw(List<T> list, Comparator<T> comparator) {
        this.zzmhl = zzdys.zzb(list, Collections.emptyMap(), zzdys.zzbrl(), comparator);
    }

    public final boolean contains(T t) {
        return this.zzmhl.containsKey(t);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdyw)) {
            return false;
        }
        return this.zzmhl.equals(((zzdyw) obj).zzmhl);
    }

    public final int hashCode() {
        return this.zzmhl.hashCode();
    }

    public final int indexOf(T t) {
        return this.zzmhl.indexOf(t);
    }

    public final boolean isEmpty() {
        return this.zzmhl.isEmpty();
    }

    public final Iterator<T> iterator() {
        return new zzdyx(this.zzmhl.iterator());
    }

    public final int size() {
        return this.zzmhl.size();
    }

    public final Iterator<T> zzbf(T t) {
        return new zzdyx(this.zzmhl.zzbf(t));
    }

    public final zzdyw<T> zzbk(T t) {
        zzdyr zzbe = this.zzmhl.zzbe(t);
        return zzbe == this.zzmhl ? this : new zzdyw(zzbe);
    }

    public final zzdyw<T> zzbl(T t) {
        return new zzdyw(this.zzmhl.zzf(t, null));
    }

    public final T zzbm(T t) {
        return this.zzmhl.zzbg(t);
    }

    public final Iterator<T> zzbrk() {
        return new zzdyx(this.zzmhl.zzbrk());
    }

    public final T zzbrm() {
        return this.zzmhl.zzbri();
    }

    public final T zzbrn() {
        return this.zzmhl.zzbrj();
    }
}
