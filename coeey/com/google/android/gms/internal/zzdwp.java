package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdwp implements Creator<zzdwo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzbek.zzd(parcel);
        long j = 0;
        boolean z = false;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 2:
                    j = zzbek.zzi(parcel, readInt);
                    break;
                case 3:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 4:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzdwo(str2, j, z, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdwo[i];
    }
}
