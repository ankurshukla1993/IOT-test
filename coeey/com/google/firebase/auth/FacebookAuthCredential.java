package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzdws;

public class FacebookAuthCredential extends AuthCredential {
    public static final Creator<FacebookAuthCredential> CREATOR = new zzg();
    private final String zzlyn;

    FacebookAuthCredential(@NonNull String str) {
        this.zzlyn = zzbq.zzgh(str);
    }

    public static zzdws zza(@NonNull FacebookAuthCredential facebookAuthCredential) {
        zzbq.checkNotNull(facebookAuthCredential);
        return new zzdws(null, facebookAuthCredential.zzlyn, facebookAuthCredential.getProvider(), null, null);
    }

    public String getProvider() {
        return FacebookAuthProvider.PROVIDER_ID;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzlyn, false);
        zzbem.zzai(parcel, zze);
    }
}
