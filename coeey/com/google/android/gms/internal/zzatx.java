package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzatx implements Creator<zzatw> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzatw(i, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzatw[i];
    }
}
