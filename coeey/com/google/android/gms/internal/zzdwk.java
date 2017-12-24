package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class zzdwk extends zzbej {
    public static final Creator<zzdwk> CREATOR = new zzdwl();
    private List<zzdwi> zzmcg;

    public zzdwk() {
        this.zzmcg = new ArrayList();
    }

    zzdwk(List<zzdwi> list) {
        if (list == null || list.isEmpty()) {
            this.zzmcg = Collections.emptyList();
        } else {
            this.zzmcg = Collections.unmodifiableList(list);
        }
    }

    public static zzdwk zza(zzdwk com_google_android_gms_internal_zzdwk) {
        Collection collection = com_google_android_gms_internal_zzdwk.zzmcg;
        zzdwk com_google_android_gms_internal_zzdwk2 = new zzdwk();
        if (collection != null) {
            com_google_android_gms_internal_zzdwk2.zzmcg.addAll(collection);
        }
        return com_google_android_gms_internal_zzdwk2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 2, this.zzmcg, false);
        zzbem.zzai(parcel, zze);
    }

    public final List<zzdwi> zzbqb() {
        return this.zzmcg;
    }
}
