package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzfeq extends zzfdd<Integer> implements zzfeu, zzffn, RandomAccess {
    private static final zzfeq zzpcq;
    private int size;
    private int[] zzpcr;

    static {
        zzfdd com_google_android_gms_internal_zzfeq = new zzfeq();
        zzpcq = com_google_android_gms_internal_zzfeq;
        com_google_android_gms_internal_zzfeq.zzbim();
    }

    zzfeq() {
        this(new int[10], 0);
    }

    private zzfeq(int[] iArr, int i) {
        this.zzpcr = iArr;
        this.size = i;
    }

    private final void zzai(int i, int i2) {
        zzcti();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzlm(i));
        }
        if (this.size < this.zzpcr.length) {
            System.arraycopy(this.zzpcr, i, this.zzpcr, i + 1, this.size - i);
        } else {
            Object obj = new int[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzpcr, 0, obj, 0, i);
            System.arraycopy(this.zzpcr, i, obj, i + 1, this.size - i);
            this.zzpcr = obj;
        }
        this.zzpcr[i] = i2;
        this.size++;
        this.modCount++;
    }

    public static zzfeq zzcvq() {
        return zzpcq;
    }

    private final void zzll(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzlm(i));
        }
    }

    private final String zzlm(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzai(i, ((Integer) obj).intValue());
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzcti();
        zzfer.checkNotNull(collection);
        if (!(collection instanceof zzfeq)) {
            return super.addAll(collection);
        }
        zzfeq com_google_android_gms_internal_zzfeq = (zzfeq) collection;
        if (com_google_android_gms_internal_zzfeq.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < com_google_android_gms_internal_zzfeq.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + com_google_android_gms_internal_zzfeq.size;
        if (i > this.zzpcr.length) {
            this.zzpcr = Arrays.copyOf(this.zzpcr, i);
        }
        System.arraycopy(com_google_android_gms_internal_zzfeq.zzpcr, 0, this.zzpcr, this.size, com_google_android_gms_internal_zzfeq.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfeq)) {
            return super.equals(obj);
        }
        zzfeq com_google_android_gms_internal_zzfeq = (zzfeq) obj;
        if (this.size != com_google_android_gms_internal_zzfeq.size) {
            return false;
        }
        int[] iArr = com_google_android_gms_internal_zzfeq.zzpcr;
        for (int i = 0; i < this.size; i++) {
            if (this.zzpcr[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    public final int getInt(int i) {
        zzll(i);
        return this.zzpcr[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzpcr[i2];
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzcti();
        zzll(i);
        int i2 = this.zzpcr[i];
        System.arraycopy(this.zzpcr, i + 1, this.zzpcr, i, this.size - i);
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final boolean remove(Object obj) {
        zzcti();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzpcr[i]))) {
                System.arraycopy(this.zzpcr, i + 1, this.zzpcr, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzcti();
        zzll(i);
        int i2 = this.zzpcr[i];
        this.zzpcr[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final int size() {
        return this.size;
    }

    public final zzfeu zzlj(int i) {
        if (i >= this.size) {
            return new zzfeq(Arrays.copyOf(this.zzpcr, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzlk(int i) {
        zzai(this.size, i);
    }

    public final /* synthetic */ zzfev zzln(int i) {
        return zzlj(i);
    }
}
