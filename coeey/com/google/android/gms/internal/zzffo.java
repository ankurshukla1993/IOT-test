package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

final class zzffo<E> extends zzfdd<E> {
    private static final zzffo<Object> zzpde;
    private final List<E> zzpdf;

    static {
        zzfdd com_google_android_gms_internal_zzffo = new zzffo();
        zzpde = com_google_android_gms_internal_zzffo;
        com_google_android_gms_internal_zzffo.zzbim();
    }

    zzffo() {
        this(new ArrayList(10));
    }

    private zzffo(List<E> list) {
        this.zzpdf = list;
    }

    public static <E> zzffo<E> zzcwf() {
        return zzpde;
    }

    public final void add(int i, E e) {
        zzcti();
        this.zzpdf.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzpdf.get(i);
    }

    public final E remove(int i) {
        zzcti();
        E remove = this.zzpdf.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzcti();
        E e2 = this.zzpdf.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzpdf.size();
    }

    public final /* synthetic */ zzfev zzln(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        List arrayList = new ArrayList(i);
        arrayList.addAll(this.zzpdf);
        return new zzffo(arrayList);
    }
}
