package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.Arrays;
import java.util.List;

public class TokenData extends zzbej implements ReflectedParcelable {
    public static final Creator<TokenData> CREATOR = new zzk();
    private int zzdzm;
    private final String zzdzn;
    private final Long zzdzo;
    private final boolean zzdzp;
    private final boolean zzdzq;
    private final List<String> zzdzr;

    TokenData(int i, String str, Long l, boolean z, boolean z2, List<String> list) {
        this.zzdzm = i;
        this.zzdzn = zzbq.zzgh(str);
        this.zzdzo = l;
        this.zzdzp = z;
        this.zzdzq = z2;
        this.zzdzr = list;
    }

    @Nullable
    public static TokenData zzd(Bundle bundle, String str) {
        bundle.setClassLoader(TokenData.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle(str);
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(TokenData.class.getClassLoader());
        return (TokenData) bundle2.getParcelable("TokenData");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TokenData)) {
            return false;
        }
        TokenData tokenData = (TokenData) obj;
        return TextUtils.equals(this.zzdzn, tokenData.zzdzn) && zzbg.equal(this.zzdzo, tokenData.zzdzo) && this.zzdzp == tokenData.zzdzp && this.zzdzq == tokenData.zzdzq && zzbg.equal(this.zzdzr, tokenData.zzdzr);
    }

    public final String getToken() {
        return this.zzdzn;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzdzn, this.zzdzo, Boolean.valueOf(this.zzdzp), Boolean.valueOf(this.zzdzq), this.zzdzr});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.zzdzn, false);
        zzbem.zza(parcel, 3, this.zzdzo, false);
        zzbem.zza(parcel, 4, this.zzdzp);
        zzbem.zza(parcel, 5, this.zzdzq);
        zzbem.zzb(parcel, 6, this.zzdzr, false);
        zzbem.zzai(parcel, zze);
    }
}
