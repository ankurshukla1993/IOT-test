package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbek;

public final class zzbw implements Creator<zzbv> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int zzd = zzbek.zzd(parcel);
        Scope[] scopeArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i3 = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                case 3:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 4:
                    scopeArr = (Scope[]) zzbek.zzb(parcel, readInt, Scope.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzbv(i3, i2, i, scopeArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbv[i];
    }
}
