package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzfdi implements Iterator {
    private final int limit = this.zzpan.size();
    private int position = 0;
    private /* synthetic */ zzfdh zzpan;

    zzfdi(zzfdh com_google_android_gms_internal_zzfdh) {
        this.zzpan = com_google_android_gms_internal_zzfdh;
    }

    private final byte nextByte() {
        try {
            zzfdh com_google_android_gms_internal_zzfdh = this.zzpan;
            int i = this.position;
            this.position = i + 1;
            return com_google_android_gms_internal_zzfdh.zzkd(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
