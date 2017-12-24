package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;
import com.google.android.gms.internal.zzbel;
import java.util.HashSet;
import java.util.Set;

public final class zzv implements Creator<zzu> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        DeviceMetaData deviceMetaData = null;
        int zzd = zzbek.zzd(parcel);
        Set hashSet = new HashSet();
        PendingIntent pendingIntent = null;
        byte[] bArr = null;
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbek.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    str = zzbek.zzq(parcel, readInt);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    i = zzbek.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    bArr = zzbek.zzt(parcel, readInt);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case 5:
                    PendingIntent pendingIntent2 = (PendingIntent) zzbek.zza(parcel, readInt, PendingIntent.CREATOR);
                    hashSet.add(Integer.valueOf(5));
                    pendingIntent = pendingIntent2;
                    break;
                case 6:
                    DeviceMetaData deviceMetaData2 = (DeviceMetaData) zzbek.zza(parcel, readInt, DeviceMetaData.CREATOR);
                    hashSet.add(Integer.valueOf(6));
                    deviceMetaData = deviceMetaData2;
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == zzd) {
            return new zzu(hashSet, i2, str, i, bArr, pendingIntent, deviceMetaData);
        }
        throw new zzbel("Overread allowed size end=" + zzd, parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzu[i];
    }
}
