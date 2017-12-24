package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzbek;

public final class zzaa implements Creator<zzz> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        zzc[] com_google_android_gms_common_zzcArr = null;
        int zzd = zzbek.zzd(parcel);
        Account account = null;
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
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
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 5:
                    iBinder = zzbek.zzr(parcel, readInt);
                    break;
                case 6:
                    scopeArr = (Scope[]) zzbek.zzb(parcel, readInt, Scope.CREATOR);
                    break;
                case 7:
                    bundle = zzbek.zzs(parcel, readInt);
                    break;
                case 8:
                    account = (Account) zzbek.zza(parcel, readInt, Account.CREATOR);
                    break;
                case 10:
                    com_google_android_gms_common_zzcArr = (zzc[]) zzbek.zzb(parcel, readInt, zzc.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzz(i3, i2, i, str, iBinder, scopeArr, bundle, account, com_google_android_gms_common_zzcArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzz[i];
    }
}
