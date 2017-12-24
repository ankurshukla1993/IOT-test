package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class zzdzf<K, V> extends zzdyr<K, V> {
    private Comparator<K> zzmhd;
    private zzdza<K, V> zzmhu;

    private zzdzf(zzdza<K, V> com_google_android_gms_internal_zzdza_K__V, Comparator<K> comparator) {
        this.zzmhu = com_google_android_gms_internal_zzdza_K__V;
        this.zzmhd = comparator;
    }

    public static <A, B> zzdzf<A, B> zzb(Map<A, B> map, Comparator<A> comparator) {
        return zzdzh.zzc(new ArrayList(map.keySet()), map, zzdys.zzbrl(), comparator);
    }

    private final zzdza<K, V> zzbn(K k) {
        zzdza<K, V> com_google_android_gms_internal_zzdza_K__V = this.zzmhu;
        while (!com_google_android_gms_internal_zzdza_K__V.isEmpty()) {
            int compare = this.zzmhd.compare(k, com_google_android_gms_internal_zzdza_K__V.getKey());
            if (compare < 0) {
                com_google_android_gms_internal_zzdza_K__V = com_google_android_gms_internal_zzdza_K__V.zzbrr();
            } else if (compare == 0) {
                return com_google_android_gms_internal_zzdza_K__V;
            } else {
                com_google_android_gms_internal_zzdza_K__V = com_google_android_gms_internal_zzdza_K__V.zzbrs();
            }
        }
        return null;
    }

    public final boolean containsKey(K k) {
        return zzbn(k) != null;
    }

    public final V get(K k) {
        zzdza zzbn = zzbn(k);
        return zzbn != null ? zzbn.getValue() : null;
    }

    public final Comparator<K> getComparator() {
        return this.zzmhd;
    }

    public final int indexOf(K k) {
        int i = 0;
        zzdza com_google_android_gms_internal_zzdza = this.zzmhu;
        while (!com_google_android_gms_internal_zzdza.isEmpty()) {
            int compare = this.zzmhd.compare(k, com_google_android_gms_internal_zzdza.getKey());
            if (compare == 0) {
                return com_google_android_gms_internal_zzdza.zzbrr().size() + i;
            }
            if (compare < 0) {
                com_google_android_gms_internal_zzdza = com_google_android_gms_internal_zzdza.zzbrr();
            } else {
                i += com_google_android_gms_internal_zzdza.zzbrr().size() + 1;
                com_google_android_gms_internal_zzdza = com_google_android_gms_internal_zzdza.zzbrs();
            }
        }
        return -1;
    }

    public final boolean isEmpty() {
        return this.zzmhu.isEmpty();
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzdyv(this.zzmhu, null, this.zzmhd, false);
    }

    public final int size() {
        return this.zzmhu.size();
    }

    public final void zza(zzdzc<K, V> com_google_android_gms_internal_zzdzc_K__V) {
        this.zzmhu.zza(com_google_android_gms_internal_zzdzc_K__V);
    }

    public final zzdyr<K, V> zzbe(K k) {
        if (!containsKey(k)) {
            return this;
        }
        return new zzdzf(this.zzmhu.zza(k, this.zzmhd).zza(null, null, zzdzb.zzmhp, null, null), this.zzmhd);
    }

    public final Iterator<Entry<K, V>> zzbf(K k) {
        return new zzdyv(this.zzmhu, k, this.zzmhd, false);
    }

    public final K zzbg(K k) {
        zzdza com_google_android_gms_internal_zzdza = this.zzmhu;
        zzdza com_google_android_gms_internal_zzdza2 = null;
        while (!com_google_android_gms_internal_zzdza.isEmpty()) {
            int compare = this.zzmhd.compare(k, com_google_android_gms_internal_zzdza.getKey());
            if (compare == 0) {
                if (com_google_android_gms_internal_zzdza.zzbrr().isEmpty()) {
                    return com_google_android_gms_internal_zzdza2 != null ? com_google_android_gms_internal_zzdza2.getKey() : null;
                } else {
                    com_google_android_gms_internal_zzdza2 = com_google_android_gms_internal_zzdza.zzbrr();
                    while (!com_google_android_gms_internal_zzdza2.zzbrs().isEmpty()) {
                        com_google_android_gms_internal_zzdza2 = com_google_android_gms_internal_zzdza2.zzbrs();
                    }
                    return com_google_android_gms_internal_zzdza2.getKey();
                }
            } else if (compare < 0) {
                com_google_android_gms_internal_zzdza = com_google_android_gms_internal_zzdza.zzbrr();
            } else {
                zzdza com_google_android_gms_internal_zzdza3 = com_google_android_gms_internal_zzdza;
                com_google_android_gms_internal_zzdza = com_google_android_gms_internal_zzdza.zzbrs();
                com_google_android_gms_internal_zzdza2 = com_google_android_gms_internal_zzdza3;
            }
        }
        String valueOf = String.valueOf(k);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Couldn't find predecessor key of non-present key: ").append(valueOf).toString());
    }

    public final K zzbri() {
        return this.zzmhu.zzbrt().getKey();
    }

    public final K zzbrj() {
        return this.zzmhu.zzbru().getKey();
    }

    public final Iterator<Entry<K, V>> zzbrk() {
        return new zzdyv(this.zzmhu, null, this.zzmhd, true);
    }

    public final zzdyr<K, V> zzf(K k, V v) {
        return new zzdzf(this.zzmhu.zza(k, v, this.zzmhd).zza(null, null, zzdzb.zzmhp, null, null), this.zzmhd);
    }
}
