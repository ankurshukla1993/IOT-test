package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public final class HintRequest extends zzbej implements ReflectedParcelable {
    public static final Creator<HintRequest> CREATOR = new zzg();
    private int zzdzm;
    private final String[] zzecj;
    private final boolean zzecm;
    private final String zzecn;
    private final String zzeco;
    private final CredentialPickerConfig zzecq;
    private final boolean zzecr;
    private final boolean zzecs;

    public static final class Builder {
        private String[] zzecj;
        private boolean zzecm = false;
        private String zzecn;
        private String zzeco;
        private CredentialPickerConfig zzecq = new com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Builder().build();
        private boolean zzecr;
        private boolean zzecs;

        public final HintRequest build() {
            if (this.zzecj == null) {
                this.zzecj = new String[0];
            }
            if (this.zzecr || this.zzecs || this.zzecj.length != 0) {
                return new HintRequest();
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

        public final Builder setEmailAddressIdentifierSupported(boolean z) {
            this.zzecr = z;
            return this;
        }

        public final Builder setHintPickerConfig(@NonNull CredentialPickerConfig credentialPickerConfig) {
            this.zzecq = (CredentialPickerConfig) zzbq.checkNotNull(credentialPickerConfig);
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

        public final Builder setPhoneNumberIdentifierSupported(boolean z) {
            this.zzecs = z;
            return this;
        }

        public final Builder setServerClientId(@Nullable String str) {
            this.zzecn = str;
            return this;
        }
    }

    HintRequest(int i, CredentialPickerConfig credentialPickerConfig, boolean z, boolean z2, String[] strArr, boolean z3, String str, String str2) {
        this.zzdzm = i;
        this.zzecq = (CredentialPickerConfig) zzbq.checkNotNull(credentialPickerConfig);
        this.zzecr = z;
        this.zzecs = z2;
        this.zzecj = (String[]) zzbq.checkNotNull(strArr);
        if (this.zzdzm < 2) {
            this.zzecm = true;
            this.zzecn = null;
            this.zzeco = null;
            return;
        }
        this.zzecm = z3;
        this.zzecn = str;
        this.zzeco = str2;
    }

    private HintRequest(Builder builder) {
        this(2, builder.zzecq, builder.zzecr, builder.zzecs, builder.zzecj, builder.zzecm, builder.zzecn, builder.zzeco);
    }

    @NonNull
    public final String[] getAccountTypes() {
        return this.zzecj;
    }

    @NonNull
    public final CredentialPickerConfig getHintPickerConfig() {
        return this.zzecq;
    }

    @Nullable
    public final String getIdTokenNonce() {
        return this.zzeco;
    }

    @Nullable
    public final String getServerClientId() {
        return this.zzecn;
    }

    public final boolean isEmailAddressIdentifierSupported() {
        return this.zzecr;
    }

    public final boolean isIdTokenRequested() {
        return this.zzecm;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, getHintPickerConfig(), i, false);
        zzbem.zza(parcel, 2, isEmailAddressIdentifierSupported());
        zzbem.zza(parcel, 3, this.zzecs);
        zzbem.zza(parcel, 4, getAccountTypes(), false);
        zzbem.zza(parcel, 5, isIdTokenRequested());
        zzbem.zza(parcel, 6, getServerClientId(), false);
        zzbem.zza(parcel, 7, getIdTokenNonce(), false);
        zzbem.zzc(parcel, 1000, this.zzdzm);
        zzbem.zzai(parcel, zze);
    }
}
