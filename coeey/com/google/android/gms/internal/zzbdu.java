package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbdu implements Creator<zzbdt> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int zzd = zzbek.zzd(parcel);
        boolean z = true;
        boolean z2 = false;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        int i3 = 0;
        String str4 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str4 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    i3 = zzbek.zzg(parcel, readInt);
                    break;
                case 4:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                case 5:
                    str3 = zzbek.zzq(parcel, readInt);
                    break;
                case 6:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 7:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 8:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 9:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 10:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzbdt(str4, i3, i2, str3, str2, z, str, z2, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbdt[i];
    }
}
