package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzdws;

public class GithubAuthCredential extends AuthCredential {
    public static final Creator<GithubAuthCredential> CREATOR = new zzp();
    private String zzdzn;

    GithubAuthCredential(@NonNull String str) {
        this.zzdzn = zzbq.zzgh(str);
    }

    public static zzdws zza(@NonNull GithubAuthCredential githubAuthCredential) {
        zzbq.checkNotNull(githubAuthCredential);
        return new zzdws(null, githubAuthCredential.zzdzn, githubAuthCredential.getProvider(), null, null);
    }

    public String getProvider() {
        return GithubAuthProvider.PROVIDER_ID;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzdzn, false);
        zzbem.zzai(parcel, zze);
    }
}
