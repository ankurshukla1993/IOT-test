package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CredentialPickerConfig extends zzbej implements ReflectedParcelable {
    public static final Creator<CredentialPickerConfig> CREATOR = new zzc();
    private final boolean mShowCancelButton;
    private int zzdzm;
    private final boolean zzece;
    @Deprecated
    private final boolean zzecf;
    private final int zzecg;

    public static class Builder {
        private boolean mShowCancelButton = true;
        private boolean zzece = false;
        private int zzech = 1;

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig();
        }

        @Deprecated
        public Builder setForNewAccount(boolean z) {
            this.zzech = z ? 3 : 1;
            return this;
        }

        public Builder setPrompt(int i) {
            this.zzech = i;
            return this;
        }

        public Builder setShowAddAccountButton(boolean z) {
            this.zzece = z;
            return this;
        }

        public Builder setShowCancelButton(boolean z) {
            this.mShowCancelButton = z;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Prompt {
        public static final int CONTINUE = 1;
        public static final int SIGN_IN = 2;
        public static final int SIGN_UP = 3;
    }

    CredentialPickerConfig(int i, boolean z, boolean z2, boolean z3, int i2) {
        int i3 = 3;
        boolean z4 = true;
        this.zzdzm = i;
        this.zzece = z;
        this.mShowCancelButton = z2;
        if (i < 2) {
            this.zzecf = z3;
            if (!z3) {
                i3 = 1;
            }
            this.zzecg = i3;
            return;
        }
        if (i2 != 3) {
            z4 = false;
        }
        this.zzecf = z4;
        this.zzecg = i2;
    }

    private CredentialPickerConfig(Builder builder) {
        this(2, builder.zzece, builder.mShowCancelButton, false, builder.zzech);
    }

    @Deprecated
    public final boolean isForNewAccount() {
        return this.zzecg == 3;
    }

    public final boolean shouldShowAddAccountButton() {
        return this.zzece;
    }

    public final boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, shouldShowAddAccountButton());
        zzbem.zza(parcel, 2, shouldShowCancelButton());
        zzbem.zza(parcel, 3, isForNewAccount());
        zzbem.zzc(parcel, 4, this.zzecg);
        zzbem.zzc(parcel, 1000, this.zzdzm);
        zzbem.zzai(parcel, zze);
    }
}
