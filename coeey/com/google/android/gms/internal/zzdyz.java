package com.google.android.gms.internal;

import java.util.Comparator;

public final class zzdyz<K, V> implements zzdza<K, V> {
    private static final zzdyz zzmhn = new zzdyz();

    private zzdyz() {
    }

    public static <K, V> zzdyz<K, V> zzbrq() {
        return zzmhn;
    }

    public final K getKey() {
        return null;
    }

    public final V getValue() {
        return null;
    }

    public final boolean isEmpty() {
        return true;
    }

    public final int size() {
        return 0;
    }

    public final zzdza<K, V> zza(K k, V v, Integer num, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V2) {
        return this;
    }

    public final zzdza<K, V> zza(K k, V v, Comparator<K> comparator) {
        return new zzdzd(k, v);
    }

    public final zzdza<K, V> zza(K k, Comparator<K> comparator) {
        return this;
    }

    public final void zza(zzdzc<K, V> com_google_android_gms_internal_zzdzc_K__V) {
    }

    public final boolean zzbrp() {
        return false;
    }

    public final zzdza<K, V> zzbrr() {
        return this;
    }

    public final zzdza<K, V> zzbrs() {
        return this;
    }

    public final zzdza<K, V> zzbrt() {
        return this;
    }

    public final zzdza<K, V> zzbru() {
        return this;
    }
}
