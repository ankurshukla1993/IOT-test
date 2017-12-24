package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public final class zzbr extends zzbej {
    public static final Creator<zzbr> CREATOR = new zzbs();
    private final Account zzdzb;
    private int zzdzm;
    private final int zzfyr;
    private final GoogleSignInAccount zzfys;

    zzbr(int i, Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this.zzdzm = i;
        this.zzdzb = account;
        this.zzfyr = i2;
        this.zzfys = googleSignInAccount;
    }

    public zzbr(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i, googleSignInAccount);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.zzdzb, i, false);
        zzbem.zzc(parcel, 3, this.zzfyr);
        zzbem.zza(parcel, 4, this.zzfys, i, false);
        zzbem.zzai(parcel, zze);
    }
}
