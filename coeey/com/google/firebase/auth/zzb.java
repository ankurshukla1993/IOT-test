package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;

public final class zzb implements Creator<ActionCodeSettings> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzd = zzbek.zzd(parcel);
        String str2 = null;
        boolean z2 = false;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str6 = zzbek.zzq(parcel, readInt);
                    break;
                case 2:
                    str5 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    str4 = zzbek.zzq(parcel, readInt);
                    break;
                case 4:
                    str3 = zzbek.zzq(parcel, readInt);
                    break;
                case 5:
                    z2 = zzbek.zzc(parcel, readInt);
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
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new ActionCodeSettings(str6, str5, str4, str3, z2, str2, z, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ActionCodeSettings[i];
    }
}
