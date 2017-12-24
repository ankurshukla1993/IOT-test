package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public final class SignInConfiguration extends zzbej implements ReflectedParcelable {
    public static final Creator<SignInConfiguration> CREATOR = new zzx();
    private final String zzefl;
    private GoogleSignInOptions zzefm;

    public SignInConfiguration(String str, GoogleSignInOptions googleSignInOptions) {
        this.zzefl = zzbq.zzgh(str);
        this.zzefm = googleSignInOptions;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
            if (!this.zzefl.equals(signInConfiguration.zzefl)) {
                return false;
            }
            if (this.zzefm == null) {
                if (signInConfiguration.zzefm != null) {
                    return false;
                }
            } else if (!this.zzefm.equals(signInConfiguration.zzefm)) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public final int hashCode() {
        return new zzp().zzr(this.zzefl).zzr(this.zzefm).zzaba();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzefl, false);
        zzbem.zza(parcel, 5, this.zzefm, i, false);
        zzbem.zzai(parcel, zze);
    }

    public final GoogleSignInOptions zzabe() {
        return this.zzefm;
    }
}
