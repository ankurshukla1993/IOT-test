package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;
import java.util.List;

public final class zzk implements Creator<TokenData> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        List list = null;
        boolean z = false;
        int zzd = zzbek.zzd(parcel);
        boolean z2 = false;
        Long l = null;
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    l = zzbek.zzj(parcel, readInt);
                    break;
                case 4:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 5:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 6:
                    list = zzbek.zzac(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new TokenData(i, str, l, z2, z, list);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new TokenData[i];
    }
}
