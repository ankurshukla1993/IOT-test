package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;

public final class zzaue implements CredentialsApi {
    public final PendingResult<Status> delete(GoogleApiClient googleApiClient, Credential credential) {
        return googleApiClient.zze(new zzaui(this, googleApiClient, credential));
    }

    public final PendingResult<Status> disableAutoSignIn(GoogleApiClient googleApiClient) {
        return googleApiClient.zze(new zzauj(this, googleApiClient));
    }

    public final PendingIntent getHintPickerIntent(GoogleApiClient googleApiClient, HintRequest hintRequest) {
        zzbq.checkArgument(googleApiClient.zza(Auth.CREDENTIALS_API), "Auth.CREDENTIALS_API must be added to GoogleApiClient to use this API");
        return zzaum.zza(googleApiClient.getContext(), ((zzaun) googleApiClient.zza(Auth.zzdzw)).zzaal(), hintRequest);
    }

    public final PendingResult<CredentialRequestResult> request(GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        return googleApiClient.zzd(new zzauf(this, googleApiClient, credentialRequest));
    }

    public final PendingResult<Status> save(GoogleApiClient googleApiClient, Credential credential) {
        return googleApiClient.zze(new zzauh(this, googleApiClient, credential));
    }
}
