package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcwf implements Creator<zzcwe> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int zzd = zzbek.zzd(parcel);
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 3:
                    intent = (Intent) zzbek.zza(parcel, readInt, Intent.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzcwe(i2, i, intent);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcwe[i];
    }
}
