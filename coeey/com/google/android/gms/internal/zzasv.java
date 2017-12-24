package com.google.android.gms.internal;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccount;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.auth.account.WorkAccountApi$AddAccountResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzasv implements WorkAccountApi {
    private static final Status zzdzt = new Status(13);

    public final PendingResult<WorkAccountApi$AddAccountResult> addWorkAccount(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.zze(new zzasx(this, WorkAccount.API, googleApiClient, str));
    }

    public final PendingResult<Result> removeWorkAccount(GoogleApiClient googleApiClient, Account account) {
        return googleApiClient.zze(new zzasz(this, WorkAccount.API, googleApiClient, account));
    }

    public final void setWorkAuthenticatorEnabled(GoogleApiClient googleApiClient, boolean z) {
        setWorkAuthenticatorEnabledWithResult(googleApiClient, z);
    }

    public final PendingResult<Result> setWorkAuthenticatorEnabledWithResult(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.zze(new zzasw(this, WorkAccount.API, googleApiClient, z));
    }
}
