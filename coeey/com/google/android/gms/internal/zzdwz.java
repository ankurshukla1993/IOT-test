package com.google.android.gms.internal;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.UserInfo;
import org.json.JSONObject;

public final class zzdwz implements UserInfo {
    @NonNull
    private String zzaua;
    @Nullable
    private String zzedt;
    @Nullable
    private String zzedu;
    @Nullable
    private String zzijn;
    @NonNull
    private String zzlym;
    @Nullable
    private String zzlzn;
    @Nullable
    private Uri zzlzq;
    private boolean zzmbx;
    @Nullable
    private String zzmcf;

    public zzdwz(@NonNull zzdwe com_google_android_gms_internal_zzdwe, @NonNull String str) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzdwe);
        zzbq.zzgh(str);
        this.zzaua = zzbq.zzgh(com_google_android_gms_internal_zzdwe.getLocalId());
        this.zzlym = str;
        this.zzedt = com_google_android_gms_internal_zzdwe.getEmail();
        this.zzedu = com_google_android_gms_internal_zzdwe.getDisplayName();
        Uri photoUri = com_google_android_gms_internal_zzdwe.getPhotoUri();
        if (photoUri != null) {
            this.zzlzn = photoUri.toString();
            this.zzlzq = photoUri;
        }
        this.zzmbx = com_google_android_gms_internal_zzdwe.isEmailVerified();
        this.zzmcf = null;
        this.zzijn = com_google_android_gms_internal_zzdwe.getPhoneNumber();
    }

    public zzdwz(@NonNull zzdwi com_google_android_gms_internal_zzdwi) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzdwi);
        this.zzaua = com_google_android_gms_internal_zzdwi.zzbqd();
        this.zzlym = zzbq.zzgh(com_google_android_gms_internal_zzdwi.getProviderId());
        this.zzedu = com_google_android_gms_internal_zzdwi.getDisplayName();
        Uri photoUri = com_google_android_gms_internal_zzdwi.getPhotoUri();
        if (photoUri != null) {
            this.zzlzn = photoUri.toString();
            this.zzlzq = photoUri;
        }
        this.zzedt = null;
        this.zzijn = com_google_android_gms_internal_zzdwi.getPhoneNumber();
        this.zzmbx = false;
        this.zzmcf = com_google_android_gms_internal_zzdwi.getRawUserInfo();
    }

    private zzdwz(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, boolean z, @Nullable String str7) {
        this.zzaua = str;
        this.zzlym = str2;
        this.zzedt = str3;
        this.zzijn = str4;
        this.zzedu = str5;
        this.zzlzn = str6;
        this.zzmbx = z;
        this.zzmcf = str7;
    }

    @Nullable
    public static zzdwz zzol(@NonNull String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new zzdwz(jSONObject.optString("userId"), jSONObject.optString("providerId"), jSONObject.optString("email"), jSONObject.optString(CooeySQLHelper.COLUMN_PH), jSONObject.optString("displayName"), jSONObject.optString("photoUrl"), jSONObject.optBoolean("isEmailVerified"), jSONObject.optString("rawUserInfo"));
        } catch (Throwable e) {
            Log.d("DefaultAuthUserInfo", "Failed to unpack UserInfo from JSON");
            throw new zzdto(e);
        }
    }

    @Nullable
    public final String getDisplayName() {
        return this.zzedu;
    }

    @Nullable
    public final String getEmail() {
        return this.zzedt;
    }

    @Nullable
    public final String getPhoneNumber() {
        return this.zzijn;
    }

    @Nullable
    public final Uri getPhotoUrl() {
        if (!TextUtils.isEmpty(this.zzlzn) && this.zzlzq == null) {
            this.zzlzq = Uri.parse(this.zzlzn);
        }
        return this.zzlzq;
    }

    @NonNull
    public final String getProviderId() {
        return this.zzlym;
    }

    @Nullable
    public final String getRawUserInfo() {
        return this.zzmcf;
    }

    @NonNull
    public final String getUid() {
        return this.zzaua;
    }

    public final boolean isEmailVerified() {
        return this.zzmbx;
    }

    @Nullable
    public final String zzaat() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("userId", this.zzaua);
            jSONObject.putOpt("providerId", this.zzlym);
            jSONObject.putOpt("displayName", this.zzedu);
            jSONObject.putOpt("photoUrl", this.zzlzn);
            jSONObject.putOpt("email", this.zzedt);
            jSONObject.putOpt(CooeySQLHelper.COLUMN_PH, this.zzijn);
            jSONObject.putOpt("isEmailVerified", Boolean.valueOf(this.zzmbx));
            jSONObject.putOpt("rawUserInfo", this.zzmcf);
            return jSONObject.toString();
        } catch (Throwable e) {
            Log.d("DefaultAuthUserInfo", "Failed to jsonify this object");
            throw new zzdto(e);
        }
    }
}
