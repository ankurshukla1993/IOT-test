package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public final class zzdwi extends zzbej {
    public static final Creator<zzdwi> CREATOR = new zzdwj();
    private String zzedu;
    private String zzijn;
    private String zzlym;
    private String zzlzn;
    private String zzmce;
    private String zzmcf;

    zzdwi(String str, String str2, String str3, String str4, String str5, String str6) {
        this.zzmce = str;
        this.zzedu = str2;
        this.zzlzn = str3;
        this.zzlym = str4;
        this.zzmcf = str5;
        this.zzijn = str6;
    }

    @Nullable
    public final String getDisplayName() {
        return this.zzedu;
    }

    public final String getPhoneNumber() {
        return this.zzijn;
    }

    @Nullable
    public final Uri getPhotoUri() {
        return !TextUtils.isEmpty(this.zzlzn) ? Uri.parse(this.zzlzn) : null;
    }

    public final String getProviderId() {
        return this.zzlym;
    }

    @Nullable
    public final String getRawUserInfo() {
        return this.zzmcf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzmce, false);
        zzbem.zza(parcel, 3, this.zzedu, false);
        zzbem.zza(parcel, 4, this.zzlzn, false);
        zzbem.zza(parcel, 5, this.zzlym, false);
        zzbem.zza(parcel, 6, this.zzmcf, false);
        zzbem.zza(parcel, 7, this.zzijn, false);
        zzbem.zzai(parcel, zze);
    }

    public final String zzbqd() {
        return this.zzmce;
    }
}
