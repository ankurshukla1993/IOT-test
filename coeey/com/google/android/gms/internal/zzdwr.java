package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

public final class zzdwr implements Creator<zzdwq> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    list = zzbek.zzac(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzdwq(i, list);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdwq[i];
    }
}
