package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzdwq extends zzbej {
    public static final Creator<zzdwq> CREATOR = new zzdwr();
    private int zzdzm;
    private List<String> zzmcm;

    public zzdwq() {
        this(null);
    }

    zzdwq(int i, List<String> list) {
        this.zzdzm = i;
        if (list == null || list.isEmpty()) {
            this.zzmcm = Collections.emptyList();
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Object obj = (String) list.get(i2);
            if (TextUtils.isEmpty(obj)) {
                obj = null;
            }
            list.set(i2, obj);
        }
        this.zzmcm = Collections.unmodifiableList(list);
    }

    private zzdwq(@Nullable List<String> list) {
        this.zzdzm = 1;
        this.zzmcm = new ArrayList();
        if (list != null && !list.isEmpty()) {
            this.zzmcm.addAll(list);
        }
    }

    public static zzdwq zza(zzdwq com_google_android_gms_internal_zzdwq) {
        return new zzdwq(com_google_android_gms_internal_zzdwq != null ? com_google_android_gms_internal_zzdwq.zzmcm : null);
    }

    public static zzdwq zzbqh() {
        return new zzdwq(null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zzb(parcel, 2, this.zzmcm, false);
        zzbem.zzai(parcel, zze);
    }

    public final List<String> zzbqg() {
        return this.zzmcm;
    }
}
