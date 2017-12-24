package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzdzh<A, B, C> {
    private final Map<B, C> values;
    private final List<A> zzmhv;
    private final zzdyu<A, B> zzmhw;
    private zzdze<A, C> zzmhx;
    private zzdze<A, C> zzmhy;

    private zzdzh(List<A> list, Map<B, C> map, zzdyu<A, B> com_google_android_gms_internal_zzdyu_A__B) {
        this.zzmhv = list;
        this.values = map;
        this.zzmhw = com_google_android_gms_internal_zzdyu_A__B;
    }

    private final C zzbo(A a) {
        return this.values.get(this.zzmhw.zzbj(a));
    }

    public static <A, B, C> zzdzf<A, C> zzc(List<A> list, Map<B, C> map, zzdyu<A, B> com_google_android_gms_internal_zzdyu_A__B, Comparator<A> comparator) {
        zzdzh com_google_android_gms_internal_zzdzh = new zzdzh(list, map, com_google_android_gms_internal_zzdyu_A__B);
        Collections.sort(list, comparator);
        Iterator it = new zzdzi(list.size()).iterator();
        int size = list.size();
        while (it.hasNext()) {
            zzdzk com_google_android_gms_internal_zzdzk = (zzdzk) it.next();
            size -= com_google_android_gms_internal_zzdzk.zzmic;
            if (com_google_android_gms_internal_zzdzk.zzmib) {
                com_google_android_gms_internal_zzdzh.zzf(zzdzb.zzmhp, com_google_android_gms_internal_zzdzk.zzmic, size);
            } else {
                com_google_android_gms_internal_zzdzh.zzf(zzdzb.zzmhp, com_google_android_gms_internal_zzdzk.zzmic, size);
                size -= com_google_android_gms_internal_zzdzk.zzmic;
                com_google_android_gms_internal_zzdzh.zzf(zzdzb.zzmho, com_google_android_gms_internal_zzdzk.zzmic, size);
            }
        }
        return new zzdzf(com_google_android_gms_internal_zzdzh.zzmhx == null ? zzdyz.zzbrq() : com_google_android_gms_internal_zzdzh.zzmhx, comparator);
    }

    private final void zzf(int i, int i2, int i3) {
        zzdza zzu = zzu(i3 + 1, i2 - 1);
        Object obj = this.zzmhv.get(i3);
        zzdza com_google_android_gms_internal_zzdzd = i == zzdzb.zzmho ? new zzdzd(obj, zzbo(obj), null, zzu) : new zzdyy(obj, zzbo(obj), null, zzu);
        if (this.zzmhx == null) {
            this.zzmhx = com_google_android_gms_internal_zzdzd;
            this.zzmhy = com_google_android_gms_internal_zzdzd;
            return;
        }
        this.zzmhy.zza(com_google_android_gms_internal_zzdzd);
        this.zzmhy = com_google_android_gms_internal_zzdzd;
    }

    private final zzdza<A, C> zzu(int i, int i2) {
        if (i2 == 0) {
            return zzdyz.zzbrq();
        }
        if (i2 == 1) {
            Object obj = this.zzmhv.get(i);
            return new zzdyy(obj, zzbo(obj), null, null);
        }
        int i3 = i2 / 2;
        int i4 = i + i3;
        zzdza zzu = zzu(i, i3);
        zzdza zzu2 = zzu(i4 + 1, i3);
        obj = this.zzmhv.get(i4);
        return new zzdyy(obj, zzbo(obj), zzu, zzu2);
    }
}
