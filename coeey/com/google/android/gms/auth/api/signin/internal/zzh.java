package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzh extends zzl<Status> {
    zzh(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb) throws RemoteException {
        zzd com_google_android_gms_auth_api_signin_internal_zzd = (zzd) com_google_android_gms_common_api_Api_zzb;
        ((zzu) com_google_android_gms_auth_api_signin_internal_zzd.zzakb()).zzb(new zzi(this), com_google_android_gms_auth_api_signin_internal_zzd.zzaax());
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return status;
    }
}
