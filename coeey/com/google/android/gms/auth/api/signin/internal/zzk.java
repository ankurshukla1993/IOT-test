package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzk extends zza {
    private /* synthetic */ zzj zzefd;

    zzk(zzj com_google_android_gms_auth_api_signin_internal_zzj) {
        this.zzefd = com_google_android_gms_auth_api_signin_internal_zzj;
    }

    public final void zzj(Status status) throws RemoteException {
        this.zzefd.setResult(status);
    }
}
