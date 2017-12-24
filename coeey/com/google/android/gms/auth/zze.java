package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzavq;
import com.google.android.gms.internal.zzeh;
import java.io.IOException;

final class zze implements zzj<TokenData> {
    private /* synthetic */ Bundle val$options;
    private /* synthetic */ Account zzdzf;
    private /* synthetic */ String zzdzg;

    zze(Account account, String str, Bundle bundle) {
        this.zzdzf = account;
        this.zzdzg = str;
        this.val$options = bundle;
    }

    public final /* synthetic */ Object zzab(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundle = (Bundle) zzd.zzp(zzeh.zza(iBinder).zza(this.zzdzf, this.zzdzg, this.val$options));
        TokenData zzd = TokenData.zzd(bundle, "tokenDetails");
        if (zzd != null) {
            return zzd;
        }
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzavq zzeu = zzavq.zzeu(string);
        if (zzavq.zza(zzeu)) {
            Object[] objArr = new Object[1];
            String valueOf = String.valueOf(zzeu);
            objArr[0] = new StringBuilder(String.valueOf(valueOf).length() + 31).append("isUserRecoverableError status: ").append(valueOf).toString();
            zzd.zzaaf().zzf("GoogleAuthUtil", objArr);
            throw new UserRecoverableAuthException(string, intent);
        }
        int i = (zzavq.zzegd.equals(zzeu) || zzavq.zzege.equals(zzeu)) ? 1 : 0;
        if (i != 0) {
            throw new IOException(string);
        }
        throw new GoogleAuthException(string);
    }
}
