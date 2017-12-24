package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;

public final class zzb implements Creator<AccountChangeEventsRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Account account = null;
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 3:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 4:
                    account = (Account) zzbek.zza(parcel, readInt, Account.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new AccountChangeEventsRequest(i2, i, str, account);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new AccountChangeEventsRequest[i];
    }
}
