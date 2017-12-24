package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;

public final class zzw implements Creator<DeviceMetaData> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzd = zzbek.zzd(parcel);
        long j = 0;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 3:
                    j = zzbek.zzi(parcel, readInt);
                    break;
                case 4:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new DeviceMetaData(i, z2, j, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new DeviceMetaData[i];
    }
}
