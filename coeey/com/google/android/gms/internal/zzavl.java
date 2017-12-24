package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult;
import com.google.android.gms.auth.api.zzd;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzavl extends zzm<ProxyResult, zzaux> {
    public zzavl(GoogleApiClient googleApiClient) {
        super(zzd.API, googleApiClient);
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((ProxyResult) obj);
    }

    protected abstract void zza(Context context, zzava com_google_android_gms_internal_zzava) throws RemoteException;

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb) throws RemoteException {
        zzaux com_google_android_gms_internal_zzaux = (zzaux) com_google_android_gms_common_api_Api_zzb;
        zza(com_google_android_gms_internal_zzaux.getContext(), (zzava) com_google_android_gms_internal_zzaux.zzakb());
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return new zzavp(status);
    }
}
