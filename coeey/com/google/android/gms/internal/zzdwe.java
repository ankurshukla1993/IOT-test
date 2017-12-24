package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.List;

public final class zzdwe extends zzbej {
    public static final Creator<zzdwe> CREATOR = new zzdwf();
    private long mCreationTimestamp;
    private String zzebz;
    private String zzedt;
    private String zzedu;
    private String zzijn;
    private String zzlzn;
    private String zzmbw;
    private boolean zzmbx;
    private zzdwk zzmby;
    private long zzmbz;
    private boolean zzmca;

    public zzdwe() {
        this.zzmby = new zzdwk();
    }

    public zzdwe(String str, String str2, boolean z, String str3, String str4, zzdwk com_google_android_gms_internal_zzdwk, String str5, String str6, long j, long j2, boolean z2) {
        this.zzmbw = str;
        this.zzedt = str2;
        this.zzmbx = z;
        this.zzedu = str3;
        this.zzlzn = str4;
        this.zzmby = com_google_android_gms_internal_zzdwk == null ? new zzdwk() : zzdwk.zza(com_google_android_gms_internal_zzdwk);
        this.zzebz = str5;
        this.zzijn = str6;
        this.mCreationTimestamp = j;
        this.zzmbz = j2;
        this.zzmca = z2;
    }

    public final long getCreationTimestamp() {
        return this.mCreationTimestamp;
    }

    @Nullable
    public final String getDisplayName() {
        return this.zzedu;
    }

    @Nullable
    public final String getEmail() {
        return this.zzedt;
    }

    public final long getLastSignInTimestamp() {
        return this.zzmbz;
    }

    @NonNull
    public final String getLocalId() {
        return this.zzmbw;
    }

    @Nullable
    public final String getPhoneNumber() {
        return this.zzijn;
    }

    @Nullable
    public final Uri getPhotoUri() {
        return !TextUtils.isEmpty(this.zzlzn) ? Uri.parse(this.zzlzn) : null;
    }

    public final boolean isEmailVerified() {
        return this.zzmbx;
    }

    public final boolean isNewUser() {
        return this.zzmca;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzmbw, false);
        zzbem.zza(parcel, 3, this.zzedt, false);
        zzbem.zza(parcel, 4, this.zzmbx);
        zzbem.zza(parcel, 5, this.zzedu, false);
        zzbem.zza(parcel, 6, this.zzlzn, false);
        zzbem.zza(parcel, 7, this.zzmby, i, false);
        zzbem.zza(parcel, 8, this.zzebz, false);
        zzbem.zza(parcel, 9, this.zzijn, false);
        zzbem.zza(parcel, 10, this.mCreationTimestamp);
        zzbem.zza(parcel, 11, this.zzmbz);
        zzbem.zza(parcel, 12, this.zzmca);
        zzbem.zzai(parcel, zze);
    }

    @NonNull
    public final List<zzdwi> zzbqb() {
        return this.zzmby.zzbqb();
    }
}
