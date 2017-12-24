package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;

public final class zzdws extends zzbej {
    public static final Creator<zzdws> CREATOR = new zzdwt();
    private String zzbwc;
    private String zzect;
    @Nullable
    private String zzedt;
    private String zzlym;
    private String zzlyn;
    private boolean zzlzj;
    private String zzmcn;
    private String zzmco;
    private String zzmcp;
    private boolean zzmcq;

    public zzdws() {
        this.zzmcq = true;
        this.zzlzj = true;
    }

    public zzdws(@Nullable String str, @Nullable String str2, String str3, @Nullable String str4, @Nullable String str5) {
        this.zzmcn = "http://localhost";
        this.zzect = str;
        this.zzlyn = str2;
        this.zzmcp = str5;
        this.zzmcq = true;
        if (TextUtils.isEmpty(this.zzect) && TextUtils.isEmpty(this.zzlyn)) {
            throw new IllegalArgumentException("Both idToken, and accessToken cannot be null");
        }
        this.zzlym = zzbq.zzgh(str3);
        this.zzedt = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(this.zzect)) {
            stringBuilder.append("id_token=").append(this.zzect).append("&");
        }
        if (!TextUtils.isEmpty(this.zzlyn)) {
            stringBuilder.append("access_token=").append(this.zzlyn).append("&");
        }
        if (!TextUtils.isEmpty(this.zzedt)) {
            stringBuilder.append("identifier=").append(this.zzedt).append("&");
        }
        if (!TextUtils.isEmpty(this.zzmcp)) {
            stringBuilder.append("oauth_token_secret=").append(this.zzmcp).append("&");
        }
        stringBuilder.append("providerId=").append(this.zzlym);
        this.zzbwc = stringBuilder.toString();
        this.zzlzj = true;
    }

    zzdws(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2) {
        this.zzmcn = str;
        this.zzmco = str2;
        this.zzect = str3;
        this.zzlyn = str4;
        this.zzlym = str5;
        this.zzedt = str6;
        this.zzbwc = str7;
        this.zzmcp = str8;
        this.zzmcq = z;
        this.zzlzj = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzmcn, false);
        zzbem.zza(parcel, 3, this.zzmco, false);
        zzbem.zza(parcel, 4, this.zzect, false);
        zzbem.zza(parcel, 5, this.zzlyn, false);
        zzbem.zza(parcel, 6, this.zzlym, false);
        zzbem.zza(parcel, 7, this.zzedt, false);
        zzbem.zza(parcel, 8, this.zzbwc, false);
        zzbem.zza(parcel, 9, this.zzmcp, false);
        zzbem.zza(parcel, 10, this.zzmcq);
        zzbem.zza(parcel, 11, this.zzlzj);
        zzbem.zzai(parcel, zze);
    }

    public final zzdws zzce(boolean z) {
        this.zzlzj = false;
        return this;
    }
}
