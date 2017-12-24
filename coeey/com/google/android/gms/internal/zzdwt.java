package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdwt implements Creator<zzdws> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzd = zzbek.zzd(parcel);
        boolean z2 = false;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str8 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    str7 = zzbek.zzq(parcel, readInt);
                    break;
                case 4:
                    str6 = zzbek.zzq(parcel, readInt);
                    break;
                case 5:
                    str5 = zzbek.zzq(parcel, readInt);
                    break;
                case 6:
                    str4 = zzbek.zzq(parcel, readInt);
                    break;
                case 7:
                    str3 = zzbek.zzq(parcel, readInt);
                    break;
                case 8:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 9:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 10:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 11:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzdws(str8, str7, str6, str5, str4, str3, str2, str, z2, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdws[i];
    }
}
