package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

final class zzasw extends zzm<Result, zzatf> {
    private /* synthetic */ boolean val$enabled;

    zzasw(zzasv com_google_android_gms_internal_zzasv, Api api, GoogleApiClient googleApiClient, boolean z) {
        this.val$enabled = z;
        super(api, googleApiClient);
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb) throws RemoteException {
        ((zzc) ((zzatf) com_google_android_gms_common_api_Api_zzb).zzakb()).zzap(this.val$enabled);
        setResult(new zzatd(Status.zzfko));
    }

    protected final Result zzb(Status status) {
        return new zzatd(status);
    }
}
