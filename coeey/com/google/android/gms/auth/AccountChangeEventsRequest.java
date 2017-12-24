package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public class AccountChangeEventsRequest extends zzbej {
    public static final Creator<AccountChangeEventsRequest> CREATOR = new zzb();
    private int mVersion;
    @Deprecated
    private String zzdyx;
    private int zzdyz;
    private Account zzdzb;

    public AccountChangeEventsRequest() {
        this.mVersion = 1;
    }

    AccountChangeEventsRequest(int i, int i2, String str, Account account) {
        this.mVersion = i;
        this.zzdyz = i2;
        this.zzdyx = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.zzdzb = account;
        } else {
            this.zzdzb = new Account(str, "com.google");
        }
    }

    public Account getAccount() {
        return this.zzdzb;
    }

    @Deprecated
    public String getAccountName() {
        return this.zzdyx;
    }

    public int getEventIndex() {
        return this.zzdyz;
    }

    public AccountChangeEventsRequest setAccount(Account account) {
        this.zzdzb = account;
        return this;
    }

    @Deprecated
    public AccountChangeEventsRequest setAccountName(String str) {
        this.zzdyx = str;
        return this;
    }

    public AccountChangeEventsRequest setEventIndex(int i) {
        this.zzdyz = i;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.mVersion);
        zzbem.zzc(parcel, 2, this.zzdyz);
        zzbem.zza(parcel, 3, this.zzdyx, false);
        zzbem.zza(parcel, 4, this.zzdzb, i, false);
        zzbem.zzai(parcel, zze);
    }
}
