package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzdyp<K, V> extends zzdyr<K, V> {
    private final K[] zzmhb;
    private final V[] zzmhc;
    private final Comparator<K> zzmhd;

    public zzdyp(Comparator<K> comparator) {
        this.zzmhb = new Object[0];
        this.zzmhc = new Object[0];
        this.zzmhd = comparator;
    }

    private zzdyp(Comparator<K> comparator, K[] kArr, V[] vArr) {
        this.zzmhb = kArr;
        this.zzmhc = vArr;
        this.zzmhd = comparator;
    }

    public static <A, B, C> zzdyp<A, C> zza(List<A> list, Map<B, C> map, zzdyu<A, B> com_google_android_gms_internal_zzdyu_A__B, Comparator<A> comparator) {
        Collections.sort(list, comparator);
        int size = list.size();
        Object[] objArr = new Object[size];
        Object[] objArr2 = new Object[size];
        size = 0;
        for (Object next : list) {
            objArr[size] = next;
            objArr2[size] = map.get(com_google_android_gms_internal_zzdyu_A__B.zzbj(next));
            size++;
        }
        return new zzdyp(comparator, objArr, objArr2);
    }

    private static <T> T[] zza(T[] tArr, int i) {
        int length = tArr.length - 1;
        Object obj = new Object[length];
        System.arraycopy(tArr, 0, obj, 0, i);
        System.arraycopy(tArr, i + 1, obj, i, length - i);
        return obj;
    }

    private static <T> T[] zza(T[] tArr, int i, T t) {
        int length = tArr.length + 1;
        Object obj = new Object[length];
        System.arraycopy(tArr, 0, obj, 0, i);
        obj[i] = t;
        System.arraycopy(tArr, i, obj, i + 1, (length - i) - 1);
        return obj;
    }

    private static <T> T[] zzb(T[] tArr, int i, T t) {
        int length = tArr.length;
        Object obj = new Object[length];
        System.arraycopy(tArr, 0, obj, 0, length);
        obj[i] = t;
        return obj;
    }

    private final int zzbh(K k) {
        int i = 0;
        while (i < this.zzmhb.length && this.zzmhd.compare(this.zzmhb[i], k) < 0) {
            i++;
        }
        return i;
    }

    private final int zzbi(K k) {
        int i = 0;
        Object[] objArr = this.zzmhb;
        int length = objArr.length;
        int i2 = 0;
        while (i2 < length) {
            if (this.zzmhd.compare(k, objArr[i2]) == 0) {
                return i;
            }
            i2++;
            i++;
        }
        return -1;
    }

    private final Iterator<Entry<K, V>> zzj(int i, boolean z) {
        return new zzdyq(this, i, z);
    }

    public final boolean containsKey(K k) {
        return zzbi(k) != -1;
    }

    public final V get(K k) {
        int zzbi = zzbi(k);
        return zzbi != -1 ? this.zzmhc[zzbi] : null;
    }

    public final Comparator<K> getComparator() {
        return this.zzmhd;
    }

    public final int indexOf(K k) {
        return zzbi(k);
    }

    public final boolean isEmpty() {
        return this.zzmhb.length == 0;
    }

    public final Iterator<Entry<K, V>> iterator() {
        return zzj(0, false);
    }

    public final int size() {
        return this.zzmhb.length;
    }

    public final void zza(zzdzc<K, V> com_google_android_gms_internal_zzdzc_K__V) {
        for (int i = 0; i < this.zzmhb.length; i++) {
            com_google_android_gms_internal_zzdzc_K__V.zzg(this.zzmhb[i], this.zzmhc[i]);
        }
    }

    public final zzdyr<K, V> zzbe(K k) {
        int zzbi = zzbi(k);
        if (zzbi == -1) {
            return this;
        }
        return new zzdyp(this.zzmhd, zza(this.zzmhb, zzbi), zza(this.zzmhc, zzbi));
    }

    public final Iterator<Entry<K, V>> zzbf(K k) {
        return zzj(zzbh(k), false);
    }

    public final K zzbg(K k) {
        int zzbi = zzbi(k);
        if (zzbi != -1) {
            return zzbi > 0 ? this.zzmhb[zzbi - 1] : null;
        } else {
            throw new IllegalArgumentException("Can't find predecessor of nonexistent key");
        }
    }

    public final K zzbri() {
        return this.zzmhb.length > 0 ? this.zzmhb[0] : null;
    }

    public final K zzbrj() {
        return this.zzmhb.length > 0 ? this.zzmhb[this.zzmhb.length - 1] : null;
    }

    public final Iterator<Entry<K, V>> zzbrk() {
        return zzj(this.zzmhb.length - 1, true);
    }

    public final zzdyr<K, V> zzf(K k, V v) {
        int zzbi = zzbi(k);
        if (zzbi != -1) {
            if (this.zzmhb[zzbi] == k && this.zzmhc[zzbi] == v) {
                return this;
            }
            return new zzdyp(this.zzmhd, zzb(this.zzmhb, zzbi, k), zzb(this.zzmhc, zzbi, v));
        } else if (this.zzmhb.length > 25) {
            Map hashMap = new HashMap(this.zzmhb.length + 1);
            for (zzbi = 0; zzbi < this.zzmhb.length; zzbi++) {
                hashMap.put(this.zzmhb[zzbi], this.zzmhc[zzbi]);
            }
            hashMap.put(k, v);
            return zzdzf.zzb(hashMap, this.zzmhd);
        } else {
            zzbi = zzbh(k);
            return new zzdyp(this.zzmhd, zza(this.zzmhb, zzbi, k), zza(this.zzmhc, zzbi, v));
        }
    }
}
