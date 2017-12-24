package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffe<K, V> {
    private final V value;
    private final K zzmhr;
    private final zzffg<K, V> zzpcz;

    private zzffe(zzfgr com_google_android_gms_internal_zzfgr, K k, zzfgr com_google_android_gms_internal_zzfgr2, V v) {
        this.zzpcz = new zzffg(com_google_android_gms_internal_zzfgr, k, com_google_android_gms_internal_zzfgr2, v);
        this.zzmhr = k;
        this.value = v;
    }

    private static <K, V> int zza(zzffg<K, V> com_google_android_gms_internal_zzffg_K__V, K k, V v) {
        return zzfeb.zza(com_google_android_gms_internal_zzffg_K__V.zzpda, 1, (Object) k) + zzfeb.zza(com_google_android_gms_internal_zzffg_K__V.zzpdc, 2, (Object) v);
    }

    public static <K, V> zzffe<K, V> zza(zzfgr com_google_android_gms_internal_zzfgr, K k, zzfgr com_google_android_gms_internal_zzfgr2, V v) {
        return new zzffe(com_google_android_gms_internal_zzfgr, k, com_google_android_gms_internal_zzfgr2, v);
    }

    private static <T> T zza(zzfdq com_google_android_gms_internal_zzfdq, zzfea com_google_android_gms_internal_zzfea, zzfgr com_google_android_gms_internal_zzfgr, T t) throws IOException {
        switch (zzfff.zzpbr[com_google_android_gms_internal_zzfgr.ordinal()]) {
            case 1:
                zzffj zzcvg = ((zzffi) t).zzcvg();
                com_google_android_gms_internal_zzfdq.zza(zzcvg, com_google_android_gms_internal_zzfea);
                return zzcvg.zzcvl();
            case 2:
                return Integer.valueOf(com_google_android_gms_internal_zzfdq.zzcuc());
            case 3:
                throw new RuntimeException("Groups are not allowed in maps.");
            default:
                return zzfeb.zza(com_google_android_gms_internal_zzfdq, com_google_android_gms_internal_zzfgr, true);
        }
    }

    public final void zza(zzfdv com_google_android_gms_internal_zzfdv, int i, K k, V v) throws IOException {
        com_google_android_gms_internal_zzfdv.zzz(i, 2);
        com_google_android_gms_internal_zzfdv.zzkt(zza(this.zzpcz, (Object) k, (Object) v));
        zzffg com_google_android_gms_internal_zzffg = this.zzpcz;
        zzfeb.zza(com_google_android_gms_internal_zzfdv, com_google_android_gms_internal_zzffg.zzpda, 1, k);
        zzfeb.zza(com_google_android_gms_internal_zzfdv, com_google_android_gms_internal_zzffg.zzpdc, 2, v);
    }

    public final void zza(zzffh<K, V> com_google_android_gms_internal_zzffh_K__V, zzfdq com_google_android_gms_internal_zzfdq, zzfea com_google_android_gms_internal_zzfea) throws IOException {
        int zzki = com_google_android_gms_internal_zzfdq.zzki(com_google_android_gms_internal_zzfdq.zzcuh());
        Object obj = this.zzpcz.zzpdb;
        Object obj2 = this.zzpcz.zzjul;
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfdq.zzcts();
            if (zzcts == 0) {
                break;
            } else if (zzcts == (this.zzpcz.zzpda.zzcxd() | 8)) {
                obj = zza(com_google_android_gms_internal_zzfdq, com_google_android_gms_internal_zzfea, this.zzpcz.zzpda, obj);
            } else if (zzcts == (this.zzpcz.zzpdc.zzcxd() | 16)) {
                obj2 = zza(com_google_android_gms_internal_zzfdq, com_google_android_gms_internal_zzfea, this.zzpcz.zzpdc, obj2);
            } else if (!com_google_android_gms_internal_zzfdq.zzkg(zzcts)) {
                break;
            }
        }
        com_google_android_gms_internal_zzfdq.zzkf(0);
        com_google_android_gms_internal_zzfdq.zzkj(zzki);
        com_google_android_gms_internal_zzffh_K__V.put(obj, obj2);
    }

    public final int zzb(int i, K k, V v) {
        return zzfdv.zzkw(i) + zzfdv.zzld(zza(this.zzpcz, (Object) k, (Object) v));
    }
}
