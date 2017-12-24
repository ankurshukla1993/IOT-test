package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzdxi {
    private Context mContext;
    private SharedPreferences zzbfu = this.mContext.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", new Object[]{this.zzmdg}), 0);
    private zzbfd zzeez = new zzbfd("StorageHelpers", new String[0]);
    private String zzmdg;

    public zzdxi(@NonNull Context context, @NonNull String str) {
        zzbq.checkNotNull(context);
        this.zzmdg = zzbq.zzgh(str);
        this.mContext = context.getApplicationContext();
    }

    private final zzdxb zzaa(@NonNull JSONObject jSONObject) {
        Throwable e;
        try {
            Object string = jSONObject.getString("cachedTokenState");
            String string2 = jSONObject.getString("applicationName");
            boolean z = jSONObject.getBoolean("anonymous");
            String str = "2";
            String string3 = jSONObject.getString(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
            String str2 = string3 != null ? string3 : str;
            JSONArray jSONArray = jSONObject.getJSONArray("userInfos");
            int length = jSONArray.length();
            List arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(zzdwz.zzol(jSONArray.getString(i)));
            }
            FirebaseUser com_google_android_gms_internal_zzdxb = new zzdxb(FirebaseApp.getInstance(string2), arrayList);
            if (!TextUtils.isEmpty(string)) {
                com_google_android_gms_internal_zzdxb.zza(zzdwg.zzok(string));
            }
            ((zzdxb) com_google_android_gms_internal_zzdxb.zzcc(z)).zzom(str2);
            return com_google_android_gms_internal_zzdxb;
        } catch (JSONException e2) {
            e = e2;
            this.zzeez.zze(e);
            return null;
        } catch (ArrayIndexOutOfBoundsException e3) {
            e = e3;
            this.zzeez.zze(e);
            return null;
        } catch (IllegalArgumentException e4) {
            e = e4;
            this.zzeez.zze(e);
            return null;
        } catch (zzdto e5) {
            e = e5;
            this.zzeez.zze(e);
            return null;
        }
    }

    @Nullable
    private final String zzh(@NonNull FirebaseUser firebaseUser) {
        JSONObject jSONObject = new JSONObject();
        if (!zzdxb.class.isAssignableFrom(firebaseUser.getClass())) {
            return null;
        }
        zzdxb com_google_android_gms_internal_zzdxb = (zzdxb) firebaseUser;
        try {
            jSONObject.put("cachedTokenState", com_google_android_gms_internal_zzdxb.zzbpo());
            jSONObject.put("applicationName", com_google_android_gms_internal_zzdxb.zzbpm().getName());
            jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
            if (com_google_android_gms_internal_zzdxb.zzbqi() != null) {
                JSONArray jSONArray = new JSONArray();
                List zzbqi = com_google_android_gms_internal_zzdxb.zzbqi();
                for (int i = 0; i < zzbqi.size(); i++) {
                    jSONArray.put(((zzdwz) zzbqi.get(i)).zzaat());
                }
                jSONObject.put("userInfos", jSONArray);
            }
            jSONObject.put("anonymous", com_google_android_gms_internal_zzdxb.isAnonymous());
            jSONObject.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, "2");
            return jSONObject.toString();
        } catch (Throwable e) {
            this.zzeez.zza("Failed to turn object into JSON", e, new Object[0]);
            throw new zzdto(e);
        }
    }

    public final void clear(String str) {
        this.zzbfu.edit().remove(str).apply();
    }

    public final void zza(@NonNull FirebaseUser firebaseUser, @NonNull zzdwg com_google_android_gms_internal_zzdwg) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(com_google_android_gms_internal_zzdwg);
        this.zzbfu.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), com_google_android_gms_internal_zzdwg.zzaat()).apply();
    }

    @Nullable
    public final FirebaseUser zzbqk() {
        FirebaseUser firebaseUser = null;
        Object string = this.zzbfu.getString("com.google.firebase.auth.FIREBASE_USER", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                if (jSONObject.has("type")) {
                    if ("com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString("type"))) {
                        firebaseUser = zzaa(jSONObject);
                    }
                }
            } catch (Exception e) {
            }
        }
        return firebaseUser;
    }

    public final void zzf(@NonNull FirebaseUser firebaseUser) {
        zzbq.checkNotNull(firebaseUser);
        Object zzh = zzh(firebaseUser);
        if (!TextUtils.isEmpty(zzh)) {
            this.zzbfu.edit().putString("com.google.firebase.auth.FIREBASE_USER", zzh).apply();
        }
    }

    public final zzdwg zzg(@NonNull FirebaseUser firebaseUser) {
        zzbq.checkNotNull(firebaseUser);
        String string = this.zzbfu.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), null);
        return string != null ? zzdwg.zzok(string) : null;
    }
}
