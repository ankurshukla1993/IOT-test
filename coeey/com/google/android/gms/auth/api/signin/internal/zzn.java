package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public final class zzn extends zzbej {
    public static final Creator<zzn> CREATOR = new zzm();
    private Bundle mBundle;
    private int versionCode;
    private int zzefe;

    zzn(int i, int i2, Bundle bundle) {
        this.versionCode = i;
        this.zzefe = i2;
        this.mBundle = bundle;
    }

    public zzn(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        this(1, googleSignInOptionsExtension.getExtensionType(), googleSignInOptionsExtension.toBundle());
    }

    public final int getType() {
        return this.zzefe;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.versionCode);
        zzbem.zzc(parcel, 2, this.zzefe);
        zzbem.zza(parcel, 3, this.mBundle, false);
        zzbem.zzai(parcel, zze);
    }
}
