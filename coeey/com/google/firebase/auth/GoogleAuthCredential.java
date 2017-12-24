package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzdws;

public class GoogleAuthCredential extends AuthCredential {
    public static final Creator<GoogleAuthCredential> CREATOR = new zzq();
    private final String zzect;
    private final String zzlyn;

    GoogleAuthCredential(@Nullable String str, @Nullable String str2) {
        if (str == null && str2 == null) {
            throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
        }
        this.zzect = zzbb(str, "idToken");
        this.zzlyn = zzbb(str2, "accessToken");
    }

    public static zzdws zza(@NonNull GoogleAuthCredential googleAuthCredential) {
        zzbq.checkNotNull(googleAuthCredential);
        return new zzdws(googleAuthCredential.zzect, googleAuthCredential.zzlyn, googleAuthCredential.getProvider(), null, null);
    }

    private static String zzbb(String str, String str2) {
        if (str == null || !TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException(String.valueOf(str2).concat(" must not be empty"));
    }

    public String getProvider() {
        return GoogleAuthProvider.PROVIDER_ID;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzect, false);
        zzbem.zza(parcel, 2, this.zzlyn, false);
        zzbem.zzai(parcel, zze);
    }
}
