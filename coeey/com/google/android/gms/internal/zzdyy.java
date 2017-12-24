package com.google.android.gms.internal;

public final class zzdyy<K, V> extends zzdze<K, V> {
    private int size = -1;

    zzdyy(K k, V v, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V2) {
        super(k, v, com_google_android_gms_internal_zzdza_K__V, com_google_android_gms_internal_zzdza_K__V2);
    }

    public final int size() {
        if (this.size == -1) {
            this.size = (zzbrr().size() + 1) + zzbrs().size();
        }
        return this.size;
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
        return new zzdyy(key, value, zzbrr, zzbrs);
    }

    final void zza(zzdza<K, V> com_google_android_gms_internal_zzdza_K__V) {
        if (this.size != -1) {
            throw new IllegalStateException("Can't set left after using size");
        }
        super.zza((zzdza) com_google_android_gms_internal_zzdza_K__V);
    }

    protected final int zzbro() {
        return zzdzb.zzmhp;
    }

    public final boolean zzbrp() {
        return false;
    }
}
