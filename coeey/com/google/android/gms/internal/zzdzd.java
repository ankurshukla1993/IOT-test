package com.google.android.gms.internal;

public final class zzdzd<K, V> extends zzdze<K, V> {
    zzdzd(K k, V v) {
        super(k, v, zzdyz.zzbrq(), zzdyz.zzbrq());
    }

    zzdzd(K k, V v, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V2) {
        super(k, v, com_google_android_gms_internal_zzdza_K__V, com_google_android_gms_internal_zzdza_K__V2);
    }

    public final int size() {
        return (zzbrr().size() + 1) + zzbrs().size();
    }

    protected final zzdze<K, V> zza(K k, V v, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V2) {
        Object key;
        Object value;
        zzdza zzbrr;
        zzdza zzbrs;
        if (k == null) {
            key = getKey();
        }
        if (v == null) {
            value = getValue();
        }
        if (com_google_android_gms_internal_zzdza_K__V == null) {
            zzbrr = zzbrr();
        }
        if (com_google_android_gms_internal_zzdza_K__V2 == null) {
            zzbrs = zzbrs();
        }
        return new zzdzd(key, value, zzbrr, zzbrs);
    }

    protected final int zzbro() {
        return zzdzb.zzmho;
    }

    public final boolean zzbrp() {
        return true;
    }
}
