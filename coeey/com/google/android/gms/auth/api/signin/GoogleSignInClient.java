package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.zze;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;

public class GoogleSignInClient extends GoogleApi<GoogleSignInOptions> {
    private static final zza zzeea = new zza();
    private static int zzeeb = zzb.zzeed;

    static class zza implements zzbo<GoogleSignInResult, GoogleSignInAccount> {
        private zza() {
        }

        public final /* synthetic */ Object zzb(Result result) {
            return ((GoogleSignInResult) result).getSignInAccount();
        }
    }

    enum zzb {
        public static final int zzeed = 1;
        public static final int zzeee = 2;
        public static final int zzeef = 3;
        public static final int zzeeg = 4;
        private static final /* synthetic */ int[] zzeeh = new int[]{zzeed, zzeee, zzeef, zzeeg};

        public static int[] m205xd6228de2() {
            return (int[]) zzeeh.clone();
        }
    }

    GoogleSignInClient(@NonNull Activity activity, GoogleSignInOptions googleSignInOptions) {
        super(activity, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, new zzg());
    }

    GoogleSignInClient(@NonNull Context context, GoogleSignInOptions googleSignInOptions) {
        super(context, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, new zzg());
    }

    private final synchronized int zzaaq() {
        if (zzeeb == zzb.zzeed) {
            Context applicationContext = getApplicationContext();
            int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext);
            if (isGooglePlayServicesAvailable == 0) {
                zzeeb = zzb.zzeeg;
            } else if (zze.zza(applicationContext, isGooglePlayServicesAvailable, null) != null || DynamiteModule.zzab(applicationContext, "com.google.android.gms.auth.api.fallback") == 0) {
                zzeeb = zzb.zzeee;
            } else {
                zzeeb = zzb.zzeef;
            }
        }
        return zzeeb;
    }

    @NonNull
    public Intent getSignInIntent() {
        Context applicationContext = getApplicationContext();
        switch (zzc.zzeec[zzaaq() - 1]) {
            case 1:
                return com.google.android.gms.auth.api.signin.internal.zze.zzb(applicationContext, (GoogleSignInOptions) zzafz());
            case 2:
                return com.google.android.gms.auth.api.signin.internal.zze.zza(applicationContext, (GoogleSignInOptions) zzafz());
            default:
                return com.google.android.gms.auth.api.signin.internal.zze.zzc(applicationContext, (GoogleSignInOptions) zzafz());
        }
    }

    public Task<Void> revokeAccess() {
        return zzbj.zzb(com.google.android.gms.auth.api.signin.internal.zze.zzb(zzagb(), getApplicationContext(), zzaaq() == zzb.zzeef));
    }

    public Task<Void> signOut() {
        return zzbj.zzb(com.google.android.gms.auth.api.signin.internal.zze.zza(zzagb(), getApplicationContext(), zzaaq() == zzb.zzeef));
    }

    public Task<GoogleSignInAccount> silentSignIn() {
        return zzbj.zza(com.google.android.gms.auth.api.signin.internal.zze.zza(zzagb(), getApplicationContext(), (GoogleSignInOptions) zzafz(), zzaaq() == zzb.zzeef), zzeea);
    }
}
