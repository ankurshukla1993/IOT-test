package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzbq;

public final class zzo {
    private static zzo zzeff = null;
    private zzz zzefg;
    private GoogleSignInAccount zzefh = this.zzefg.zzabg();
    private GoogleSignInOptions zzefi = this.zzefg.zzabh();

    private zzo(Context context) {
        this.zzefg = zzz.zzbr(context);
    }

    public static synchronized zzo zzbp(@NonNull Context context) {
        zzo zzbq;
        synchronized (zzo.class) {
            zzbq = zzbq(context.getApplicationContext());
        }
        return zzbq;
    }

    private static synchronized zzo zzbq(Context context) {
        zzo com_google_android_gms_auth_api_signin_internal_zzo;
        synchronized (zzo.class) {
            if (zzeff == null) {
                zzeff = new zzo(context);
            }
            com_google_android_gms_auth_api_signin_internal_zzo = zzeff;
        }
        return com_google_android_gms_auth_api_signin_internal_zzo;
    }

    public final synchronized void clear() {
        this.zzefg.clear();
        this.zzefh = null;
        this.zzefi = null;
    }

    public final synchronized void zza(GoogleSignInOptions googleSignInOptions, GoogleSignInAccount googleSignInAccount) {
        zzz com_google_android_gms_auth_api_signin_internal_zzz = this.zzefg;
        zzbq.checkNotNull(googleSignInAccount);
        zzbq.checkNotNull(googleSignInOptions);
        com_google_android_gms_auth_api_signin_internal_zzz.zzo("defaultGoogleSignInAccount", googleSignInAccount.zzaao());
        com_google_android_gms_auth_api_signin_internal_zzz.zza(googleSignInAccount, googleSignInOptions);
        this.zzefh = googleSignInAccount;
        this.zzefi = googleSignInOptions;
    }

    public final synchronized GoogleSignInAccount zzaay() {
        return this.zzefh;
    }

    public final synchronized GoogleSignInOptions zzaaz() {
        return this.zzefi;
    }
}
