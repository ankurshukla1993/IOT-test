package com.google.android.gms.auth.api;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzc;
import com.google.android.gms.auth.api.signin.internal.zzd;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzatg;
import com.google.android.gms.internal.zzath;
import com.google.android.gms.internal.zzati;
import com.google.android.gms.internal.zzaue;
import com.google.android.gms.internal.zzaun;
import com.google.android.gms.internal.zzavm;

public final class Auth {
    public static final Api<AuthCredentialsOptions> CREDENTIALS_API = new Api("Auth.CREDENTIALS_API", zzdzz, zzdzw);
    public static final CredentialsApi CredentialsApi = new zzaue();
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API = new Api("Auth.GOOGLE_SIGN_IN_API", zzeab, zzdzy);
    public static final GoogleSignInApi GoogleSignInApi = new zzc();
    @KeepForSdk
    public static final Api<zzf> PROXY_API = zzd.API;
    @KeepForSdk
    public static final ProxyApi ProxyApi = new zzavm();
    public static final zzf<zzaun> zzdzw = new zzf();
    private static zzf<zzati> zzdzx = new zzf();
    public static final zzf<zzd> zzdzy = new zzf();
    private static final zza<zzaun, AuthCredentialsOptions> zzdzz = new zza();
    private static final zza<zzati, NoOptions> zzeaa = new zzb();
    private static final zza<zzd, GoogleSignInOptions> zzeab = new zzc();
    private static Api<NoOptions> zzeac = new Api("Auth.ACCOUNT_STATUS_API", zzeaa, zzdzx);
    private static zzatg zzead = new zzath();

    public static final class AuthCredentialsOptions implements Optional {
        public static final AuthCredentialsOptions zzeae = new AuthCredentialsOptions(new Builder());
        private final String zzeaf = null;
        private final PasswordSpecification zzeag;
        private final boolean zzeah;

        public static class Builder {
            @NonNull
            private PasswordSpecification zzeag = PasswordSpecification.zzecu;
            private Boolean zzeai = Boolean.valueOf(false);

            public Builder forceEnableSaveDialog() {
                this.zzeai = Boolean.valueOf(true);
                return this;
            }
        }

        private AuthCredentialsOptions(Builder builder) {
            this.zzeag = builder.zzeag;
            this.zzeah = builder.zzeai.booleanValue();
        }

        public final Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", null);
            bundle.putParcelable("password_specification", this.zzeag);
            bundle.putBoolean("force_save_dialog", this.zzeah);
            return bundle;
        }

        public final PasswordSpecification zzaah() {
            return this.zzeag;
        }
    }

    private Auth() {
    }
}
