package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbem;

public class PhoneAuthCredential extends AuthCredential implements Cloneable {
    public static final Creator<PhoneAuthCredential> CREATOR = new zzr();
    private String zzijn;
    private String zzlzg;
    private String zzlzh;
    private boolean zzlzi;
    private boolean zzlzj;
    private String zzlzk;

    PhoneAuthCredential(@Nullable String str, @Nullable String str2, boolean z, @Nullable String str3, boolean z2, @Nullable String str4) {
        boolean z3 = (z && !TextUtils.isEmpty(str3)) || !((TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) && (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)));
        zzbq.checkArgument(z3, "Cannot create PhoneAuthCredential without either verificationProof, sessionInfo, ortemprary proof.");
        this.zzlzg = str;
        this.zzlzh = str2;
        this.zzlzi = z;
        this.zzijn = str3;
        this.zzlzj = z2;
        this.zzlzk = str4;
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new PhoneAuthCredential(this.zzlzg, getSmsCode(), this.zzlzi, this.zzijn, this.zzlzj, this.zzlzk);
    }

    @NonNull
    public String getProvider() {
        return PhoneAuthProvider.PROVIDER_ID;
    }

    @Nullable
    public String getSmsCode() {
        return this.zzlzh;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzlzg, false);
        zzbem.zza(parcel, 2, getSmsCode(), false);
        zzbem.zza(parcel, 3, this.zzlzi);
        zzbem.zza(parcel, 4, this.zzijn, false);
        zzbem.zza(parcel, 5, this.zzlzj);
        zzbem.zza(parcel, 6, this.zzlzk, false);
        zzbem.zzai(parcel, zze);
    }

    public final PhoneAuthCredential zzcd(boolean z) {
        this.zzlzj = false;
        return this;
    }
}
