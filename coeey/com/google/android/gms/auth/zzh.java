package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeh;
import java.io.IOException;

final class zzh implements zzj<Bundle> {
    private /* synthetic */ Account zzdzf;

    zzh(Account account) {
        this.zzdzf = account;
    }

    public final /* synthetic */ Object zzab(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        return (Bundle) zzd.zzp(zzeh.zza(iBinder).zza(this.zzdzf));
    }
}
