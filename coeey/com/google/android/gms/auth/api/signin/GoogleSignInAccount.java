package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount extends zzbej implements ReflectedParcelable {
    public static final Creator<GoogleSignInAccount> CREATOR = new zzb();
    private static zzd zzeds = zzh.zzalu();
    private int versionCode;
    private String zzbte;
    private List<Scope> zzdzr;
    private String zzecc;
    private String zzecd;
    private String zzect;
    private String zzedt;
    private String zzedu;
    private Uri zzedv;
    private String zzedw;
    private long zzedx;
    private String zzedy;

    GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List<Scope> list, String str7, String str8) {
        this.versionCode = i;
        this.zzbte = str;
        this.zzect = str2;
        this.zzedt = str3;
        this.zzedu = str4;
        this.zzedv = uri;
        this.zzedw = str5;
        this.zzedx = j;
        this.zzedy = str6;
        this.zzdzr = list;
        this.zzecc = str7;
        this.zzecd = str8;
    }

    private final JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put(ShareConstants.WEB_DIALOG_PARAM_ID, getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put("email", getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            if (getPhotoUrl() != null) {
                jSONObject.put("photoUrl", getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.zzedx);
            jSONObject.put("obfuscatedIdentifier", this.zzedy);
            JSONArray jSONArray = new JSONArray();
            Scope[] scopeArr = (Scope[]) this.zzdzr.toArray(new Scope[this.zzdzr.size()]);
            Arrays.sort(scopeArr, zza.zzedz);
            for (Scope zzagj : scopeArr) {
                jSONArray.put(zzagj.zzagj());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static GoogleSignInAccount zzen(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Uri uri = null;
        Object optString = jSONObject.optString("photoUrl", null);
        if (!TextUtils.isEmpty(optString)) {
            uri = Uri.parse(optString);
        }
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        Set hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString2 = jSONObject.optString(ShareConstants.WEB_DIALOG_PARAM_ID);
        String optString3 = jSONObject.optString("tokenId", null);
        String optString4 = jSONObject.optString("email", null);
        String optString5 = jSONObject.optString("displayName", null);
        String optString6 = jSONObject.optString("givenName", null);
        String optString7 = jSONObject.optString("familyName", null);
        Long valueOf = Long.valueOf(parseLong);
        GoogleSignInAccount googleSignInAccount = new GoogleSignInAccount(3, optString2, optString3, optString4, optString5, uri, null, (valueOf == null ? Long.valueOf(zzeds.currentTimeMillis() / 1000) : valueOf).longValue(), zzbq.zzgh(jSONObject.getString("obfuscatedIdentifier")), new ArrayList((Collection) zzbq.checkNotNull(hashSet)), optString6, optString7);
        googleSignInAccount.zzedw = jSONObject.optString("serverAuthCode", null);
        return googleSignInAccount;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.zzedy.equals(this.zzedy) && googleSignInAccount.getGrantedScopes().equals(getGrantedScopes());
    }

    @Nullable
    public Account getAccount() {
        return this.zzedt == null ? null : new Account(this.zzedt, "com.google");
    }

    @Nullable
    public String getDisplayName() {
        return this.zzedu;
    }

    @Nullable
    public String getEmail() {
        return this.zzedt;
    }

    @Nullable
    public String getFamilyName() {
        return this.zzecd;
    }

    @Nullable
    public String getGivenName() {
        return this.zzecc;
    }

    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.zzdzr);
    }

    @Nullable
    public String getId() {
        return this.zzbte;
    }

    @Nullable
    public String getIdToken() {
        return this.zzect;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zzedv;
    }

    @Nullable
    public String getServerAuthCode() {
        return this.zzedw;
    }

    public int hashCode() {
        return ((this.zzedy.hashCode() + 527) * 31) + this.zzdzr.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.versionCode);
        zzbem.zza(parcel, 2, getId(), false);
        zzbem.zza(parcel, 3, getIdToken(), false);
        zzbem.zza(parcel, 4, getEmail(), false);
        zzbem.zza(parcel, 5, getDisplayName(), false);
        zzbem.zza(parcel, 6, getPhotoUrl(), i, false);
        zzbem.zza(parcel, 7, getServerAuthCode(), false);
        zzbem.zza(parcel, 8, this.zzedx);
        zzbem.zza(parcel, 9, this.zzedy, false);
        zzbem.zzc(parcel, 10, this.zzdzr, false);
        zzbem.zza(parcel, 11, getGivenName(), false);
        zzbem.zza(parcel, 12, getFamilyName(), false);
        zzbem.zzai(parcel, zze);
    }

    public final boolean zzaan() {
        return zzeds.currentTimeMillis() / 1000 >= this.zzedx - 300;
    }

    @NonNull
    public final String zzaao() {
        return this.zzedy;
    }

    public final String zzaap() {
        JSONObject toJsonObject = toJsonObject();
        toJsonObject.remove("serverAuthCode");
        return toJsonObject.toString();
    }
}
