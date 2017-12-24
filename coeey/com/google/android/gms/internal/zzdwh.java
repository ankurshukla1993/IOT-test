package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdwh implements Creator<zzdwg> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Long l = null;
        int zzd = zzbek.zzd(parcel);
        String str = null;
        Long l2 = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str3 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 4:
                    l2 = zzbek.zzj(parcel, readInt);
                    break;
                case 5:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 6:
                    l = zzbek.zzj(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzdwg(str3, str2, l2, str, l);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdwg[i];
    }
}
