package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public class UserProfileChangeRequest extends zzbej {
    public static final Creator<UserProfileChangeRequest> CREATOR = new zzt();
    private String zzedu;
    private String zzlzn;
    private boolean zzlzo;
    private boolean zzlzp;
    private Uri zzlzq;

    public static class Builder {
        private String zzedu;
        private boolean zzlzo;
        private boolean zzlzp;
        private Uri zzlzq;

        public UserProfileChangeRequest build() {
            return new UserProfileChangeRequest(this.zzedu, this.zzlzq == null ? null : this.zzlzq.toString(), this.zzlzo, this.zzlzp);
        }

        public Builder setDisplayName(@Nullable String str) {
            if (str == null) {
                this.zzlzo = true;
            } else {
                this.zzedu = str;
            }
            return this;
        }

        public Builder setPhotoUri(@Nullable Uri uri) {
            if (uri == null) {
                this.zzlzp = true;
            } else {
                this.zzlzq = uri;
            }
            return this;
        }
    }

    UserProfileChangeRequest(String str, String str2, boolean z, boolean z2) {
        this.zzedu = str;
        this.zzlzn = str2;
        this.zzlzo = z;
        this.zzlzp = z2;
        this.zzlzq = TextUtils.isEmpty(str2) ? null : Uri.parse(str2);
    }

    @Nullable
    public String getDisplayName() {
        return this.zzedu;
    }

    @Nullable
    public Uri getPhotoUri() {
        return this.zzlzq;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, getDisplayName(), false);
        zzbem.zza(parcel, 3, this.zzlzn, false);
        zzbem.zza(parcel, 4, this.zzlzo);
        zzbem.zza(parcel, 5, this.zzlzp);
        zzbem.zzai(parcel, zze);
    }
}
