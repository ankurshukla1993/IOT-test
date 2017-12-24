package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbdd implements Creator<zzbdc> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        boolean z = false;
        long j = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 2:
                    j = zzbek.zzi(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzbdc(z, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbdc[i];
    }
}
