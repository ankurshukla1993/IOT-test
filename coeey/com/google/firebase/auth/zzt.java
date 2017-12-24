package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;

public final class zzt implements Creator<UserProfileChangeRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzd = zzbek.zzd(parcel);
        String str = null;
        String str2 = null;
        boolean z2 = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 4:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 5:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new UserProfileChangeRequest(str2, str, z2, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new UserProfileChangeRequest[i];
    }
}
