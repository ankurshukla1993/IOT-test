package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.RemoteException;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

final class zzasz extends zzm<Result, zzatf> {
    private /* synthetic */ Account zzdzf;

    zzasz(zzasv com_google_android_gms_internal_zzasv, Api api, GoogleApiClient googleApiClient, Account account) {
        this.zzdzf = account;
        super(api, googleApiClient);
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb) throws RemoteException {
        ((zzc) ((zzatf) com_google_android_gms_common_api_Api_zzb).zzakb()).zza(new zzata(this), this.zzdzf);
    }

    protected final Result zzb(Status status) {
        return new zzate(status);
    }
}
