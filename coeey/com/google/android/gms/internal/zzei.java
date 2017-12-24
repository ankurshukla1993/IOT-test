package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public final class zzei extends zzed implements zzeg {
    zzei(IBinder iBinder) {
        super(iBinder, "com.google.android.auth.IAuthManagerService");
    }

    public final Bundle zza(Account account) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) account);
        Parcel zza = zza(7, zzaz);
        Bundle bundle = (Bundle) zzef.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle;
    }

    public final Bundle zza(Account account, String str, Bundle bundle) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) account);
        zzaz.writeString(str);
        zzef.zza(zzaz, (Parcelable) bundle);
        Parcel zza = zza(5, zzaz);
        Bundle bundle2 = (Bundle) zzef.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle2;
    }

    public final Bundle zza(String str, Bundle bundle) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (Parcelable) bundle);
        Parcel zza = zza(2, zzaz);
        Bundle bundle2 = (Bundle) zzef.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle2;
    }

    public final AccountChangeEventsResponse zza(AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) accountChangeEventsRequest);
        Parcel zza = zza(3, zzaz);
        AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) zzef.zza(zza, AccountChangeEventsResponse.CREATOR);
        zza.recycle();
        return accountChangeEventsResponse;
    }

    public final Bundle zzo(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        Parcel zza = zza(8, zzaz);
        Bundle bundle = (Bundle) zzef.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle;
    }
}
