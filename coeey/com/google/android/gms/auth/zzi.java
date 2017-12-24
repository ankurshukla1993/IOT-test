package com.google.android.gms.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzavq;
import com.google.android.gms.internal.zzeh;
import java.io.IOException;

final class zzi implements zzj<Boolean> {
    private /* synthetic */ String zzdzk;

    zzi(String str) {
        this.zzdzk = str;
    }

    public final /* synthetic */ Object zzab(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundle = (Bundle) zzd.zzp(zzeh.zza(iBinder).zzo(this.zzdzk));
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzavq zzeu = zzavq.zzeu(string);
        if (zzavq.zzegb.equals(zzeu)) {
            return Boolean.valueOf(true);
        }
        if (zzavq.zza(zzeu)) {
            Object[] objArr = new Object[1];
            String valueOf = String.valueOf(zzeu);
            objArr[0] = new StringBuilder(String.valueOf(valueOf).length() + 31).append("isUserRecoverableError status: ").append(valueOf).toString();
            zzd.zzaaf().zzf("GoogleAuthUtil", objArr);
            throw new UserRecoverableAuthException(string, intent);
        }
        throw new GoogleAuthException(string);
    }
}
