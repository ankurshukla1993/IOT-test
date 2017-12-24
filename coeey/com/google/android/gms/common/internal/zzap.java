package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzap extends zzed implements zzan {
    zzap(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
    }

    public final Account getAccount() throws RemoteException {
        Parcel zza = zza(2, zzaz());
        Account account = (Account) zzef.zza(zza, Account.CREATOR);
        zza.recycle();
        return account;
    }
}
