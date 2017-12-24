package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzauf extends zzaul<CredentialRequestResult> {
    private /* synthetic */ CredentialRequest zzede;

    zzauf(zzaue com_google_android_gms_internal_zzaue, GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        this.zzede = credentialRequest;
        super(googleApiClient);
    }

    protected final void zza(Context context, zzaus com_google_android_gms_internal_zzaus) throws RemoteException {
        com_google_android_gms_internal_zzaus.zza(new zzaug(this), this.zzede);
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return zzaud.zzf(status);
    }
}
