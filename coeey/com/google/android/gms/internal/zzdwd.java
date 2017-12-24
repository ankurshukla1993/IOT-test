package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdwd implements Creator<zzdwc> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        zzdwq com_google_android_gms_internal_zzdwq = null;
        int zzd = zzbek.zzd(parcel);
        String str = null;
        boolean z2 = false;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 4:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 5:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 6:
                    com_google_android_gms_internal_zzdwq = (zzdwq) zzbek.zza(parcel, readInt, zzdwq.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzdwc(str2, z2, str, z, com_google_android_gms_internal_zzdwq);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdwc[i];
    }
}
