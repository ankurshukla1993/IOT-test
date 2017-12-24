package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdwj implements Creator<zzdwi> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzbek.zzd(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str6 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    str5 = zzbek.zzq(parcel, readInt);
                    break;
                case 4:
                    str4 = zzbek.zzq(parcel, readInt);
                    break;
                case 5:
                    str3 = zzbek.zzq(parcel, readInt);
                    break;
                case 6:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 7:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzdwi(str6, str5, str4, str3, str2, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdwi[i];
    }
}
