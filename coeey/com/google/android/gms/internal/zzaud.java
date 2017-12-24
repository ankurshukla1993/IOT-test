package com.google.android.gms.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Status;

public final class zzaud implements CredentialRequestResult {
    private final Status mStatus;
    private final Credential zzedd;

    public zzaud(Status status, Credential credential) {
        this.mStatus = status;
        this.zzedd = credential;
    }

    public static zzaud zzf(Status status) {
        return new zzaud(status, null);
    }

    public final Credential getCredential() {
        return this.zzedd;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}
