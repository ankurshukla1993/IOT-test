package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbem;

public class EmailAuthCredential extends AuthCredential {
    public static final Creator<EmailAuthCredential> CREATOR = new zzf();
    private String zzebz;
    private String zzedt;

    EmailAuthCredential(@NonNull String str, @NonNull String str2) {
        this.zzedt = zzbq.zzgh(str);
        this.zzebz = zzbq.zzgh(str2);
    }

    @NonNull
    public final String getEmail() {
        return this.zzedt;
    }

    @NonNull
    public final String getPassword() {
        return this.zzebz;
    }

    @NonNull
    public String getProvider() {
        return "password";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzedt, false);
        zzbem.zza(parcel, 2, this.zzebz, false);
        zzbem.zzai(parcel, zze);
    }
}
