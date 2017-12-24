package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzdws;

public class zzd extends AuthCredential {
    public static final Creator<zzd> CREATOR = new zze();
    private final String zzect;
    private final String zzlym;
    private final String zzlyn;

    zzd(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        this.zzlym = zzbq.zzh(str, "Must specify a non-empty providerId");
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
        }
        this.zzect = str2;
        this.zzlyn = str3;
    }

    public static zzdws zza(@NonNull zzd com_google_firebase_auth_zzd) {
        zzbq.checkNotNull(com_google_firebase_auth_zzd);
        return new zzdws(com_google_firebase_auth_zzd.zzect, com_google_firebase_auth_zzd.zzlyn, com_google_firebase_auth_zzd.getProvider(), null, null);
    }

    public String getProvider() {
        return this.zzlym;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, getProvider(), false);
        zzbem.zza(parcel, 2, this.zzect, false);
        zzbem.zza(parcel, 3, this.zzlyn, false);
        zzbem.zzai(parcel, zze);
    }
}
