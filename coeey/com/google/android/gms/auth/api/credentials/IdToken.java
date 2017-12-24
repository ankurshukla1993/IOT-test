package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public final class IdToken extends zzbej implements ReflectedParcelable {
    public static final Creator<IdToken> CREATOR = new zzh();
    @NonNull
    private final String zzebn;
    @NonNull
    private final String zzect;

    public IdToken(@NonNull String str, @NonNull String str2) {
        boolean z = true;
        zzbq.checkArgument(!TextUtils.isEmpty(str), "account type string cannot be null or empty");
        if (TextUtils.isEmpty(str2)) {
            z = false;
        }
        zzbq.checkArgument(z, "id token string cannot be null or empty");
        this.zzebn = str;
        this.zzect = str2;
    }

    @NonNull
    public final String getAccountType() {
        return this.zzebn;
    }

    @NonNull
    public final String getIdToken() {
        return this.zzect;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, getAccountType(), false);
        zzbem.zza(parcel, 2, getIdToken(), false);
        zzbem.zzai(parcel, zze);
    }
}
