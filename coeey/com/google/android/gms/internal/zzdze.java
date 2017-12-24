package com.google.android.gms.internal;

import java.util.Comparator;

public abstract class zzdze<K, V> implements zzdza<K, V> {
    private final V value;
    private final K zzmhr;
    private zzdza<K, V> zzmhs;
    private final zzdza<K, V> zzmht;

    zzdze(K k, V v, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V2) {
        zzdza zzbrq;
        zzdza zzbrq2;
        this.zzmhr = k;
        this.value = v;
        if (com_google_android_gms_internal_zzdza_K__V == null) {
            zzbrq = zzdyz.zzbrq();
        }
        this.zzmhs = zzbrq;
        if (com_google_android_gms_internal_zzdza_K__V2 == null) {
            zzbrq2 = zzdyz.zzbrq();
        }
        this.zzmht = zzbrq2;
    }

    private static int zzb(zzdza com_google_android_gms_internal_zzdza) {
        return com_google_android_gms_internal_zzdza.zzbrp() ? zzdzb.zzmhp : zzdzb.zzmho;
    }

    private final zzdze<K, V> zzb(K k, V v, Integer num, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V2) {
        zzdza com_google_android_gms_internal_zzdza;
        zzdza com_google_android_gms_internal_zzdza2;
        Object obj = this.zzmhr;
        Object obj2 = this.value;
        if (com_google_android_gms_internal_zzdza_K__V == null) {
            com_google_android_gms_internal_zzdza = this.zzmhs;
        }
        if (com_google_android_gms_internal_zzdza_K__V2 == null) {
            com_google_android_gms_internal_zzdza2 = this.zzmht;
        }
        return num == zzdzb.zzmho ? new zzdzd(obj, obj2, com_google_android_gms_internal_zzdza, com_google_android_gms_internal_zzdza2) : new zzdyy(obj, obj2, com_google_android_gms_internal_zzdza, com_google_android_gms_internal_zzdza2);
    }

    private final zzdza<K, V> zzbrv() {
        if (this.zzmhs.isEmpty()) {
            return zzdyz.zzbrq();
        }
        if (!(this.zzmhs.zzbrp() || this.zzmhs.zzbrr().zzbrp())) {
            this = zzbrw();
        }
        return zza(null, null, ((zzdze) this.zzmhs).zzbrv(), null).zzbrx();
    }

    private final zzdze<K, V> zzbrw() {
        zzdze<K, V> zzbsa = zzbsa();
        return zzbsa.zzmht.zzbrr().zzbrp() ? zzbsa.zza(null, null, null, ((zzdze) zzbsa.zzmht).zzbrz()).zzbry().zzbsa() : zzbsa;
    }

    private final zzdze<K, V> zzbrx() {
        zzdze<K, V> zzbry;
        if (this.zzmht.zzbrp() && !this.zzmhs.zzbrp()) {
            zzbry = zzbry();
        }
        if (zzbry.zzmhs.zzbrp() && ((zzdze) zzbry.zzmhs).zzmhs.zzbrp()) {
            zzbry = zzbry.zzbrz();
        }
        return (zzbry.zzmhs.zzbrp() && zzbry.zzmht.zzbrp()) ? zzbry.zzbsa() : zzbry;
    }

    private final zzdze<K, V> zzbry() {
        return (zzdze) this.zzmht.zza(null, null, zzbro(), zzb(null, null, zzdzb.zzmho, null, ((zzdze) this.zzmht).zzmhs), null);
    }

    private final zzdze<K, V> zzbrz() {
        return (zzdze) this.zzmhs.zza(null, null, zzbro(), null, zzb(null, null, zzdzb.zzmho, ((zzdze) this.zzmhs).zzmht, null));
    }

    private final zzdze<K, V> zzbsa() {
        return zzb(null, null, zzb(this), this.zzmhs.zza(null, null, zzb(this.zzmhs), null, null), this.zzmht.zza(null, null, zzb(this.zzmht), null, null));
    }

    public final K getKey() {
        return this.zzmhr;
    }

    public final V getValue() {
        return this.value;
    }

    public final boolean isEmpty() {
        return false;
    }

    public final /* synthetic */ zzdza zza(Object obj, Object obj2, int i, zzdza com_google_android_gms_internal_zzdza, zzdza com_google_android_gms_internal_zzdza2) {
        return zzb(null, null, i, com_google_android_gms_internal_zzdza, com_google_android_gms_internal_zzdza2);
    }

    public final zzdza<K, V> zza(K k, V v, Comparator<K> comparator) {
        int compare = comparator.compare(k, this.zzmhr);
        zzdze zza = compare < 0 ? zza(null, null, this.zzmhs.zza(k, v, comparator), null) : compare == 0 ? zza(k, v, null, null) : zza(null, null, null, this.zzmht.zza(k, v, comparator));
        return zza.zzbrx();
    }

    public final zzdza<K, V> zza(K k, Comparator<K> comparator) {
        zzdze zza;
        if (comparator.compare(k, this.zzmhr) < 0) {
            if (!(this.zzmhs.isEmpty() || this.zzmhs.zzbrp() || ((zzdze) this.zzmhs).zzmhs.zzbrp())) {
                this = zzbrw();
            }
            zza = zza(null, null, this.zzmhs.zza(k, comparator), null);
        } else {
            if (this.zzmhs.zzbrp()) {
                this = zzbrz();
            }
            if (!(this.zzmht.isEmpty() || this.zzmht.zzbrp() || ((zzdze) this.zzmht).zzmhs.zzbrp())) {
                zza = zzbsa();
                if (zza.zzmhs.zzbrr().zzbrp()) {
                    zza = zza.zzbrz().zzbsa();
                }
                this = zza;
            }
            if (comparator.compare(k, this.zzmhr) == 0) {
                if (this.zzmht.isEmpty()) {
                    return zzdyz.zzbrq();
                }
                zzdza zzbrt = this.zzmht.zzbrt();
                this = zza(zzbrt.getKey(), zzbrt.getValue(), null, ((zzdze) this.zzmht).zzbrv());
            }
            zza = zza(null, null, null, this.zzmht.zza(k, comparator));
        }
        return zza.zzbrx();
    }

    protected abstract zzdze<K, V> zza(K k, V v, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V, zzdza<K, V> com_google_android_gms_internal_zzdza_K__V2);

    void zza(zzdza<K, V> com_google_android_gms_internal_zzdza_K__V) {
        this.zzmhs = com_google_android_gms_internal_zzdza_K__V;
    }

    public final void zza(zzdzc<K, V> com_google_android_gms_internal_zzdzc_K__V) {
        this.zzmhs.zza(com_google_android_gms_internal_zzdzc_K__V);
        com_google_android_gms_internal_zzdzc_K__V.zzg(this.zzmhr, this.value);
        this.zzmht.zza(com_google_android_gms_internal_zzdzc_K__V);
    }

    protected abstract int zzbro();

    public final zzdza<K, V> zzbrr() {
        return this.zzmhs;
    }

    public final zzdza<K, V> zzbrs() {
        return this.zzmht;
    }

    public final zzdza<K, V> zzbrt() {
        return this.zzmhs.isEmpty() ? this : this.zzmhs.zzbrt();
    }

    public final zzdza<K, V> zzbru() {
        return this.zzmht.isEmpty() ? this : this.zzmht.zzbru();
    }
}
