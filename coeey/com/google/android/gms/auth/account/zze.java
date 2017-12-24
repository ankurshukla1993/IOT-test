package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zze extends zzed implements zzc {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.account.IWorkAccountService");
    }

    public final void zza(zza com_google_android_gms_auth_account_zza, Account account) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_auth_account_zza);
        zzef.zza(zzaz, (Parcelable) account);
        zzb(3, zzaz);
    }

    public final void zza(zza com_google_android_gms_auth_account_zza, String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_auth_account_zza);
        zzaz.writeString(str);
        zzb(2, zzaz);
    }

    public final void zzap(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(1, zzaz);
    }
}
