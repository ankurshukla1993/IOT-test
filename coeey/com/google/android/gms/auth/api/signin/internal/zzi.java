package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzi extends zza {
    private /* synthetic */ zzh zzefc;

    zzi(zzh com_google_android_gms_auth_api_signin_internal_zzh) {
        this.zzefc = com_google_android_gms_auth_api_signin_internal_zzh;
    }

    public final void zzi(Status status) throws RemoteException {
        this.zzefc.setResult(status);
    }
}
