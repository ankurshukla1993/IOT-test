package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzaui extends zzaul<Status> {
    private /* synthetic */ Credential zzedg;

    zzaui(zzaue com_google_android_gms_internal_zzaue, GoogleApiClient googleApiClient, Credential credential) {
        this.zzedg = credential;
        super(googleApiClient);
    }

    protected final void zza(Context context, zzaus com_google_android_gms_internal_zzaus) throws RemoteException {
        com_google_android_gms_internal_zzaus.zza(new zzauk(this), new zzauo(this.zzedg));
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return status;
    }
}
