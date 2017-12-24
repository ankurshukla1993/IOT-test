package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.internal.zzbek;

public final class zzbs implements Creator<zzbr> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        Account account = null;
        int i2 = 0;
        GoogleSignInAccount googleSignInAccount = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    account = (Account) zzbek.zza(parcel, readInt, Account.CREATOR);
                    break;
                case 3:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 4:
                    googleSignInAccount = (GoogleSignInAccount) zzbek.zza(parcel, readInt, GoogleSignInAccount.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzbr(i2, account, i, googleSignInAccount);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbr[i];
    }
}
