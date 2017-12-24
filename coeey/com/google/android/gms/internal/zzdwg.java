package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.util.Log;
import com.facebook.AccessToken;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzh;
import org.json.JSONObject;

public final class zzdwg extends zzbej {
    public static final Creator<zzdwg> CREATOR = new zzdwh();
    private String zzlyn;
    private String zzmae;
    private Long zzmcb;
    private String zzmcc;
    private Long zzmcd;

    public zzdwg() {
        this.zzmcd = Long.valueOf(System.currentTimeMillis());
    }

    zzdwg(String str, String str2, Long l, String str3, Long l2) {
        this.zzmae = str;
        this.zzlyn = str2;
        this.zzmcb = l;
        this.zzmcc = str3;
        this.zzmcd = l2;
    }

    public static zzdwg zzok(@NonNull String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            zzdwg com_google_android_gms_internal_zzdwg = new zzdwg();
            com_google_android_gms_internal_zzdwg.zzmae = jSONObject.optString("refresh_token", null);
            com_google_android_gms_internal_zzdwg.zzlyn = jSONObject.optString("access_token", null);
            com_google_android_gms_internal_zzdwg.zzmcb = Long.valueOf(jSONObject.optLong(AccessToken.EXPIRES_IN_KEY));
            com_google_android_gms_internal_zzdwg.zzmcc = jSONObject.optString("token_type", null);
            com_google_android_gms_internal_zzdwg.zzmcd = Long.valueOf(jSONObject.optLong("issued_at"));
            return com_google_android_gms_internal_zzdwg;
        } catch (Throwable e) {
            Log.d("GetTokenResponse", "Failed to read GetTokenResponse from JSONObject");
            throw new zzdto(e);
        }
    }

    public final String getAccessToken() {
        return this.zzlyn;
    }

    public final boolean isValid() {
        return zzh.zzalu().currentTimeMillis() + 300000 < this.zzmcd.longValue() + (this.zzmcb.longValue() * 1000);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzmae, false);
        zzbem.zza(parcel, 3, this.zzlyn, false);
        zzbem.zza(parcel, 4, Long.valueOf(this.zzmcb == null ? 0 : this.zzmcb.longValue()), false);
        zzbem.zza(parcel, 5, this.zzmcc, false);
        zzbem.zza(parcel, 6, Long.valueOf(this.zzmcd.longValue()), false);
        zzbem.zzai(parcel, zze);
    }

    public final String zzaat() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("refresh_token", this.zzmae);
            jSONObject.put("access_token", this.zzlyn);
            jSONObject.put(AccessToken.EXPIRES_IN_KEY, this.zzmcb);
            jSONObject.put("token_type", this.zzmcc);
            jSONObject.put("issued_at", this.zzmcd);
            return jSONObject.toString();
        } catch (Throwable e) {
            Log.d("GetTokenResponse", "Failed to convert GetTokenResponse to JSON");
            throw new zzdto(e);
        }
    }

    public final String zzbqc() {
        return this.zzmae;
    }

    public final void zzoj(@NonNull String str) {
        this.zzmae = zzbq.zzgh(str);
    }
}
