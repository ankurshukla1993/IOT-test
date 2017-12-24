package com.google.android.gms.auth.api.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzbp;
import com.google.android.gms.common.api.internal.zzcr;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbfd;
import java.util.HashSet;

public final class zze {
    private static zzbfd zzeez = new zzbfd("GoogleSignInCommon", new String[0]);

    @Nullable
    public static GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        if (intent == null || (!intent.hasExtra("googleSignInStatus") && !intent.hasExtra("googleSignInAccount"))) {
            return null;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        Status status = (Status) intent.getParcelableExtra("googleSignInStatus");
        if (googleSignInAccount != null) {
            status = Status.zzfko;
        }
        return new GoogleSignInResult(googleSignInAccount, status);
    }

    public static Intent zza(Context context, GoogleSignInOptions googleSignInOptions) {
        zzeez.zzb("getSignInIntent()", new Object[0]);
        Parcelable signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, SignInHubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", signInConfiguration);
        intent.putExtra("config", bundle);
        return intent;
    }

    public static OptionalPendingResult<GoogleSignInResult> zza(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions, boolean z) {
        Result googleSignInResult;
        zzeez.zzb("silentSignIn()", new Object[0]);
        zzeez.zzb("getEligibleSavedSignInResult()", new Object[0]);
        zzbq.checkNotNull(googleSignInOptions);
        GoogleSignInOptions zzaaz = zzo.zzbp(context).zzaaz();
        if (zzaaz != null) {
            Account account = zzaaz.getAccount();
            Account account2 = googleSignInOptions.getAccount();
            boolean equals = account == null ? account2 == null : account.equals(account2);
            if (equals && !googleSignInOptions.zzaas() && ((!googleSignInOptions.isIdTokenRequested() || (zzaaz.isIdTokenRequested() && googleSignInOptions.getServerClientId().equals(zzaaz.getServerClientId()))) && new HashSet(zzaaz.zzaar()).containsAll(new HashSet(googleSignInOptions.zzaar())))) {
                GoogleSignInAccount zzaay = zzo.zzbp(context).zzaay();
                if (!(zzaay == null || zzaay.zzaan())) {
                    googleSignInResult = new GoogleSignInResult(zzaay, Status.zzfko);
                    if (googleSignInResult != null) {
                        zzeez.zzb("Eligible saved sign in result found", new Object[0]);
                        return PendingResults.zzb(googleSignInResult, googleApiClient);
                    } else if (z) {
                        return PendingResults.zzb(new GoogleSignInResult(null, new Status(4)), googleApiClient);
                    } else {
                        zzeez.zzb("trySilentSignIn()", new Object[0]);
                        return new zzcr(googleApiClient.zzd(new zzf(googleApiClient, context, googleSignInOptions)));
                    }
                }
            }
        }
        googleSignInResult = null;
        if (googleSignInResult != null) {
            zzeez.zzb("Eligible saved sign in result found", new Object[0]);
            return PendingResults.zzb(googleSignInResult, googleApiClient);
        } else if (z) {
            return PendingResults.zzb(new GoogleSignInResult(null, new Status(4)), googleApiClient);
        } else {
            zzeez.zzb("trySilentSignIn()", new Object[0]);
            return new zzcr(googleApiClient.zzd(new zzf(googleApiClient, context, googleSignInOptions)));
        }
    }

    public static PendingResult<Status> zza(GoogleApiClient googleApiClient, Context context, boolean z) {
        zzeez.zzb("Signing out", new Object[0]);
        zzbo(context);
        return z ? PendingResults.zza(Status.zzfko, googleApiClient) : googleApiClient.zze(new zzh(googleApiClient));
    }

    public static Intent zzb(Context context, GoogleSignInOptions googleSignInOptions) {
        zzeez.zzb("getFallbackSignInIntent()", new Object[0]);
        Intent zza = zza(context, googleSignInOptions);
        zza.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return zza;
    }

    public static PendingResult<Status> zzb(GoogleApiClient googleApiClient, Context context, boolean z) {
        zzeez.zzb("Revoking access", new Object[0]);
        zzbo(context);
        return z ? PendingResults.zza(Status.zzfkt, googleApiClient) : googleApiClient.zze(new zzj(googleApiClient));
    }

    private static void zzbo(Context context) {
        zzo.zzbp(context).clear();
        for (GoogleApiClient zzagf : GoogleApiClient.zzage()) {
            zzagf.zzagf();
        }
        zzbp.zzaif();
    }

    public static Intent zzc(Context context, GoogleSignInOptions googleSignInOptions) {
        zzeez.zzb("getNoImplementationSignInIntent()", new Object[0]);
        Intent zza = zza(context, googleSignInOptions);
        zza.setAction("com.google.android.gms.auth.NO_IMPL");
        return zza;
    }
}
