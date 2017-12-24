package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.List;

public final class zzdwc extends zzbej {
    public static final Creator<zzdwc> CREATOR = new zzdwd();
    private boolean mRegistered;
    private String zzlym;
    private String zzmbt;
    private boolean zzmbu;
    private zzdwq zzmbv;

    public zzdwc() {
        this.zzmbv = zzdwq.zzbqh();
    }

    public zzdwc(String str, boolean z, String str2, boolean z2, zzdwq com_google_android_gms_internal_zzdwq) {
        this.zzmbt = str;
        this.mRegistered = z;
        this.zzlym = str2;
        this.zzmbu = z2;
        this.zzmbv = com_google_android_gms_internal_zzdwq == null ? zzdwq.zzbqh() : zzdwq.zza(com_google_android_gms_internal_zzdwq);
    }

    @Nullable
    public final List<String> getAllProviders() {
        return this.zzmbv.zzbqg();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzmbt, false);
        zzbem.zza(parcel, 3, this.mRegistered);
        zzbem.zza(parcel, 4, this.zzlym, false);
        zzbem.zza(parcel, 5, this.zzmbu);
        zzbem.zza(parcel, 6, this.zzmbv, i, false);
        zzbem.zzai(parcel, zze);
    }
}
