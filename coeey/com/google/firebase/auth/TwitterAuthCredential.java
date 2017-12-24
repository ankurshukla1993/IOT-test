package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzdws;

public class TwitterAuthCredential extends AuthCredential {
    public static final Creator<TwitterAuthCredential> CREATOR = new zzs();
    private String zzdzn;
    private String zzlzm;

    TwitterAuthCredential(@NonNull String str, @NonNull String str2) {
        this.zzdzn = zzbq.zzgh(str);
        this.zzlzm = zzbq.zzgh(str2);
    }

    public static zzdws zza(@NonNull TwitterAuthCredential twitterAuthCredential) {
        zzbq.checkNotNull(twitterAuthCredential);
        return new zzdws(null, twitterAuthCredential.zzdzn, twitterAuthCredential.getProvider(), null, twitterAuthCredential.zzlzm);
    }

    public String getProvider() {
        return TwitterAuthProvider.PROVIDER_ID;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzdzn, false);
        zzbem.zza(parcel, 2, this.zzlzm, false);
        zzbem.zzai(parcel, zze);
    }
}
