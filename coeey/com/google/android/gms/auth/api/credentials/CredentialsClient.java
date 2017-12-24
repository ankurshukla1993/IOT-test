package com.google.android.gms.auth.api.credentials;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.internal.zzaum;
import com.google.android.gms.tasks.Task;

public class CredentialsClient extends GoogleApi<AuthCredentialsOptions> {
    CredentialsClient(@NonNull Activity activity, @NonNull AuthCredentialsOptions authCredentialsOptions) {
        super(activity, Auth.CREDENTIALS_API, authCredentialsOptions, new zzg());
    }

    CredentialsClient(@NonNull Context context, @NonNull AuthCredentialsOptions authCredentialsOptions) {
        super(context, Auth.CREDENTIALS_API, authCredentialsOptions, new zzg());
    }

    public Task<Void> delete(@NonNull Credential credential) {
        return zzbj.zzb(Auth.CredentialsApi.delete(zzagb(), credential));
    }

    public Task<Void> disableAutoSignIn() {
        return zzbj.zzb(Auth.CredentialsApi.disableAutoSignIn(zzagb()));
    }

    public PendingIntent getHintPickerIntent(@NonNull HintRequest hintRequest) {
        return zzaum.zza(getApplicationContext(), (AuthCredentialsOptions) zzafz(), hintRequest);
    }

    public Task<CredentialRequestResponse> request(@NonNull CredentialRequest credentialRequest) {
        return zzbj.zza(Auth.CredentialsApi.request(zzagb(), credentialRequest), new CredentialRequestResponse());
    }

    public Task<Void> save(@NonNull Credential credential) {
        return zzbj.zzb(Auth.CredentialsApi.save(zzagb(), credential));
    }
}
