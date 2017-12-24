package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.account.WorkAccountApi$AddAccountResult;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

final class zzasx extends zzm<WorkAccountApi$AddAccountResult, zzatf> {
    private /* synthetic */ String zzdzh;

    zzasx(zzasv com_google_android_gms_internal_zzasv, Api api, GoogleApiClient googleApiClient, String str) {
        this.zzdzh = str;
        super(api, googleApiClient);
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((WorkAccountApi$AddAccountResult) obj);
    }

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb) throws RemoteException {
        ((zzc) ((zzatf) com_google_android_gms_common_api_Api_zzb).zzakb()).zza(new zzasy(this), this.zzdzh);
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return new zzatc(status, null);
    }
}
