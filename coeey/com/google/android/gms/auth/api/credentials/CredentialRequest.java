package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class CredentialRequest extends zzbej {
    public static final Creator<CredentialRequest> CREATOR = new zze();
    private int zzdzm;
    private final boolean zzeci;
    private final String[] zzecj;
    private final CredentialPickerConfig zzeck;
    private final CredentialPickerConfig zzecl;
    private final boolean zzecm;
    private final String zzecn;
    private final String zzeco;
    private final boolean zzecp;

    public static final class Builder {
        private boolean zzeci;
        private String[] zzecj;
        private CredentialPickerConfig zzeck;
        private CredentialPickerConfig zzecl;
        private boolean zzecm = false;
        @Nullable
        private String zzecn = null;
        @Nullable
        private String zzeco;
        private boolean zzecp = false;

        public final CredentialRequest build() {
            if (this.zzecj == null) {
                this.zzecj = new String[0];
            }
            if (this.zzeci || this.zzecj.length != 0) {
                return new CredentialRequest();
            }
            throw new IllegalStateException("At least one authentication method must be specified");
        }

        public final Builder setAccountTypes(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this.zzecj = strArr;
            return this;
        }

        public final Builder setCredentialHintPickerConfig(CredentialPickerConfig credentialPickerConfig) {
            this.zzecl = credentialPickerConfig;
            return this;
        }

        public final Builder setCredentialPickerConfig(CredentialPickerConfig credentialPickerConfig) {
            this.zzeck = credentialPickerConfig;
            return this;
        }

        public final Builder setIdTokenNonce(@Nullable String str) {
            this.zzeco = str;
            return this;
        }

        public final Builder setIdTokenRequested(boolean z) {
            this.zzecm = z;
            return this;
        }

        public final Builder setPasswordLoginSupported(boolean z) {
            this.zzeci = z;
            return this;
        }

        public final Builder setServerClientId(@Nullable String str) {
            this.zzecn = str;
            return this;
        }

        @Deprecated
        public final Builder setSupportsPasswordLogin(boolean z) {
            return setPasswordLoginSupported(z);
        }
    }

    CredentialRequest(int i, boolean z, String[] strArr, CredentialPickerConfig credentialPickerConfig, CredentialPickerConfig credentialPickerConfig2, boolean z2, String str, String str2, boolean z3) {
        this.zzdzm = i;
        this.zzeci = z;
        this.zzecj = (String[]) zzbq.checkNotNull(strArr);
        if (credentialPickerConfig == null) {
            credentialPickerConfig = new com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Builder().build();
        }
        this.zzeck = credentialPickerConfig;
        if (credentialPickerConfig2 == null) {
            credentialPickerConfig2 = new com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Builder().build();
        }
        this.zzecl = credentialPickerConfig2;
        if (i < 3) {
            this.zzecm = true;
            this.zzecn = null;
            this.zzeco = null;
        } else {
            this.zzecm = z2;
            this.zzecn = str;
            this.zzeco = str2;
        }
        this.zzecp = z3;
    }

    private CredentialRequest(Builder builder) {
        this(4, builder.zzeci, builder.zzecj, builder.zzeck, builder.zzecl, builder.zzecm, builder.zzecn, builder.zzeco, false);
    }

    @NonNull
    public final String[] getAccountTypes() {
        return this.zzecj;
    }

    @NonNull
    public final Set<String> getAccountTypesSet() {
        return new HashSet(Arrays.asList(this.zzecj));
    }

    @NonNull
    public final CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzecl;
    }

    @NonNull
    public final CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzeck;
    }

    @Nullable
    public final String getIdTokenNonce() {
        return this.zzeco;
    }

    @Nullable
    public final String getServerClientId() {
        return this.zzecn;
    }

    @Deprecated
    public final boolean getSupportsPasswordLogin() {
        return isPasswordLoginSupported();
    }

    public final boolean isIdTokenRequested() {
        return this.zzecm;
    }

    public final boolean isPasswordLoginSupported() {
        return this.zzeci;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, isPasswordLoginSupported());
        zzbem.zza(parcel, 2, getAccountTypes(), false);
        zzbem.zza(parcel, 3, getCredentialPickerConfig(), i, false);
        zzbem.zza(parcel, 4, getCredentialHintPickerConfig(), i, false);
        zzbem.zza(parcel, 5, isIdTokenRequested());
        zzbem.zza(parcel, 6, getServerClientId(), false);
        zzbem.zza(parcel, 7, getIdTokenNonce(), false);
        zzbem.zzc(parcel, 1000, this.zzdzm);
        zzbem.zza(parcel, 8, this.zzecp);
        zzbem.zzai(parcel, zze);
    }
}
