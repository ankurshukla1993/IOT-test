package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;

public final class zza implements Creator<ProxyRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int zzd = zzbek.zzd(parcel);
        long j = 0;
        byte[] bArr = null;
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 2:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 3:
                    j = zzbek.zzi(parcel, readInt);
                    break;
                case 4:
                    bArr = zzbek.zzt(parcel, readInt);
                    break;
                case 5:
                    bundle = zzbek.zzs(parcel, readInt);
                    break;
                case 1000:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new ProxyRequest(i2, str, i, j, bArr, bundle);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ProxyRequest[i];
    }
}
