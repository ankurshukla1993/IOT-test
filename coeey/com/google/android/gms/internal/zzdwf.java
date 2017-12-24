package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdwf implements Creator<zzdwe> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        String str3 = null;
        String str4 = null;
        zzdwk com_google_android_gms_internal_zzdwk = null;
        String str5 = null;
        String str6 = null;
        long j = 0;
        long j2 = 0;
        boolean z2 = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 4:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 5:
                    str3 = zzbek.zzq(parcel, readInt);
                    break;
                case 6:
                    str4 = zzbek.zzq(parcel, readInt);
                    break;
                case 7:
                    com_google_android_gms_internal_zzdwk = (zzdwk) zzbek.zza(parcel, readInt, zzdwk.CREATOR);
                    break;
                case 8:
                    str5 = zzbek.zzq(parcel, readInt);
                    break;
                case 9:
                    str6 = zzbek.zzq(parcel, readInt);
                    break;
                case 10:
                    j = zzbek.zzi(parcel, readInt);
                    break;
                case 11:
                    j2 = zzbek.zzi(parcel, readInt);
                    break;
                case 12:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzdwe(str, str2, z, str3, str4, com_google_android_gms_internal_zzdwk, str5, str6, j, j2, z2);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdwe[i];
    }
}
