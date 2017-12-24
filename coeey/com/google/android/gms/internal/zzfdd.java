package com.google.android.gms.internal;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzfdd<E> extends AbstractList<E> implements zzfev<E> {
    private boolean zzpah = true;

    zzfdd() {
    }

    public void add(int i, E e) {
        zzcti();
        super.add(i, e);
    }

    public boolean add(E e) {
        zzcti();
        return super.add(e);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzcti();
        return super.addAll(i, collection);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzcti();
        return super.addAll(collection);
    }

    public void clear() {
        zzcti();
        super.clear();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < size(); i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public E remove(int i) {
        zzcti();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzcti();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzcti();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzcti();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzcti();
        return super.set(i, e);
    }

    public final void zzbim() {
        this.zzpah = false;
    }

    public final boolean zzcth() {
        return this.zzpah;
    }

    protected final void zzcti() {
        if (!this.zzpah) {
            throw new UnsupportedOperationException();
        }
    }
}
