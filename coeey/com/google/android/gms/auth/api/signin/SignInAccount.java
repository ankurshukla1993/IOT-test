package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public class SignInAccount extends zzbej implements ReflectedParcelable {
    public static final Creator<SignInAccount> CREATOR = new zzf();
    @Deprecated
    private String zzaua;
    @Deprecated
    private String zzedt;
    private GoogleSignInAccount zzeev;

    SignInAccount(String str, GoogleSignInAccount googleSignInAccount, String str2) {
        this.zzeev = googleSignInAccount;
        this.zzedt = zzbq.zzh(str, "8.3 and 8.4 SDKs require non-null email");
        this.zzaua = zzbq.zzh(str2, "8.3 and 8.4 SDKs require non-null userId");
    }

    public final GoogleSignInAccount getGoogleSignInAccount() {
        return this.zzeev;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 4, this.zzedt, false);
        zzbem.zza(parcel, 7, this.zzeev, i, false);
        zzbem.zza(parcel, 8, this.zzaua, false);
        zzbem.zzai(parcel, zze);
    }
}
