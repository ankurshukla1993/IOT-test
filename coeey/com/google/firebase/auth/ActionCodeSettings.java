package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public class ActionCodeSettings extends zzbej {
    public static final Creator<ActionCodeSettings> CREATOR = new zzb();
    private final String zzad;
    private final String zzlyf;
    private final String zzlyg;
    private final String zzlyh;
    private final boolean zzlyi;
    private final String zzlyj;
    private final boolean zzlyk;
    private String zzlyl;

    public static class Builder {
        private String zzad;
        private String zzlyf;
        private String zzlyh;
        private boolean zzlyi;
        private String zzlyj;
        private boolean zzlyk;

        private Builder() {
            this.zzlyk = false;
        }

        public ActionCodeSettings build() {
            return new ActionCodeSettings();
        }

        public Builder setAndroidPackageName(@NonNull String str, boolean z, @Nullable String str2) {
            this.zzlyh = str;
            this.zzlyi = z;
            this.zzlyj = str2;
            return this;
        }

        public Builder setHandleCodeInApp(boolean z) {
            this.zzlyk = z;
            return this;
        }

        public Builder setIOSBundleId(@NonNull String str) {
            this.zzlyf = str;
            return this;
        }

        public Builder setUrl(@NonNull String str) {
            this.zzad = str;
            return this;
        }
    }

    private ActionCodeSettings(Builder builder) {
        this.zzad = builder.zzad;
        this.zzlyf = builder.zzlyf;
        this.zzlyg = null;
        this.zzlyh = builder.zzlyh;
        this.zzlyi = builder.zzlyi;
        this.zzlyj = builder.zzlyj;
        this.zzlyk = builder.zzlyk;
    }

    ActionCodeSettings(String str, String str2, String str3, String str4, boolean z, String str5, boolean z2, String str6) {
        this.zzad = str;
        this.zzlyf = str2;
        this.zzlyg = str3;
        this.zzlyh = str4;
        this.zzlyi = z;
        this.zzlyj = str5;
        this.zzlyk = z2;
        this.zzlyl = str6;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean canHandleCodeInApp() {
        return this.zzlyk;
    }

    public boolean getAndroidInstallApp() {
        return this.zzlyi;
    }

    public String getAndroidMinimumVersion() {
        return this.zzlyj;
    }

    public String getAndroidPackageName() {
        return this.zzlyh;
    }

    public String getIOSBundle() {
        return this.zzlyf;
    }

    public String getUrl() {
        return this.zzad;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, getUrl(), false);
        zzbem.zza(parcel, 2, getIOSBundle(), false);
        zzbem.zza(parcel, 3, this.zzlyg, false);
        zzbem.zza(parcel, 4, getAndroidPackageName(), false);
        zzbem.zza(parcel, 5, getAndroidInstallApp());
        zzbem.zza(parcel, 6, getAndroidMinimumVersion(), false);
        zzbem.zza(parcel, 7, canHandleCodeInApp());
        zzbem.zza(parcel, 8, this.zzlyl, false);
        zzbem.zzai(parcel, zze);
    }

    public final void zzoe(@NonNull String str) {
        this.zzlyl = str;
    }
}
