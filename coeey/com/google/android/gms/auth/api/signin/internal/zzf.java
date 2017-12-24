package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzf extends zzl<GoogleSignInResult> {
    final /* synthetic */ Context val$context;
    final /* synthetic */ GoogleSignInOptions zzefa;

    zzf(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions) {
        this.val$context = context;
        this.zzefa = googleSignInOptions;
        super(googleApiClient);
    }

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb) throws RemoteException {
        ((zzu) ((zzd) com_google_android_gms_common_api_Api_zzb).zzakb()).zza(new zzg(this), this.zzefa);
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return new GoogleSignInResult(null, status);
    }
}
