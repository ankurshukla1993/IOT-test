package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzt extends zzee implements zzs {
    public zzt() {
        attachInterface(this, "com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 101:
                zza((GoogleSignInAccount) zzef.zza(parcel, GoogleSignInAccount.CREATOR), (Status) zzef.zza(parcel, Status.CREATOR));
                break;
            case 102:
                zzi((Status) zzef.zza(parcel, Status.CREATOR));
                break;
            case 103:
                zzj((Status) zzef.zza(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
