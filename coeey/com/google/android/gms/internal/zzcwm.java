package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbr;

public final class zzcwm extends zzbej {
    public static final Creator<zzcwm> CREATOR = new zzcwn();
    private int zzdzm;
    private zzbr zzjzi;

    zzcwm(int i, zzbr com_google_android_gms_common_internal_zzbr) {
        this.zzdzm = i;
        this.zzjzi = com_google_android_gms_common_internal_zzbr;
    }

    public zzcwm(zzbr com_google_android_gms_common_internal_zzbr) {
        this(1, com_google_android_gms_common_internal_zzbr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.zzjzi, i, false);
        zzbem.zzai(parcel, zze);
    }
}
