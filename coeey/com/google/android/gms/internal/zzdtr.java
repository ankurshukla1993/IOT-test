package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import java.util.Map;

final class zzdtr implements zzdtv {
    private final int zzlzz;
    private final int zzmaa;
    private final Map<String, Integer> zzmab;
    private final boolean zzmac;

    public zzdtr(int i, int i2, @NonNull Map<String, Integer> map, boolean z) {
        this.zzlzz = i;
        this.zzmaa = i2;
        this.zzmab = (Map) zzbq.checkNotNull(map);
        this.zzmac = z;
    }

    public final boolean zzd(zzdtu com_google_android_gms_internal_zzdtu) {
        if (!this.zzmac) {
            return true;
        }
        if (this.zzmaa <= this.zzlzz) {
            return false;
        }
        Integer num = (Integer) this.zzmab.get(com_google_android_gms_internal_zzdtu.zzbps());
        return num == null ? false : num.intValue() > this.zzlzz;
    }
}
