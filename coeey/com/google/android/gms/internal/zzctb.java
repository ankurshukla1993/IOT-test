package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzctb implements Creator<zzcsv> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        byte[][] bArr = null;
        int zzd = zzbek.zzd(parcel);
        int[] iArr = null;
        byte[][] bArr2 = null;
        byte[][] bArr3 = null;
        byte[][] bArr4 = null;
        byte[][] bArr5 = null;
        byte[] bArr6 = null;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    bArr6 = zzbek.zzt(parcel, readInt);
                    break;
                case 4:
                    bArr5 = zzbek.zzu(parcel, readInt);
                    break;
                case 5:
                    bArr4 = zzbek.zzu(parcel, readInt);
                    break;
                case 6:
                    bArr3 = zzbek.zzu(parcel, readInt);
                    break;
                case 7:
                    bArr2 = zzbek.zzu(parcel, readInt);
                    break;
                case 8:
                    iArr = zzbek.zzw(parcel, readInt);
                    break;
                case 9:
                    bArr = zzbek.zzu(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzcsv(str, bArr6, bArr5, bArr4, bArr3, bArr2, iArr, bArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcsv[i];
    }
}
