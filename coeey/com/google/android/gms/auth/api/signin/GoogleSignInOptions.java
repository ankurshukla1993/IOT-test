package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.auth.api.signin.internal.zzp;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends zzbej implements Optional, ReflectedParcelable {
    public static final Creator<GoogleSignInOptions> CREATOR = new zze();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(SCOPE_GAMES, new Scope[0]).build();
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    private static Scope SCOPE_GAMES = new Scope(Scopes.GAMES);
    public static final Scope zzeei = new Scope(Scopes.PROFILE);
    public static final Scope zzeej = new Scope("email");
    public static final Scope zzeek = new Scope("openid");
    private static Comparator<Scope> zzeer = new zzd();
    private int versionCode;
    private Account zzdzb;
    private boolean zzecm;
    private String zzecn;
    private final ArrayList<Scope> zzeel;
    private final boolean zzeem;
    private final boolean zzeen;
    private String zzeeo;
    private ArrayList<zzn> zzeep;
    private Map<Integer, zzn> zzeeq;

    public static final class Builder {
        private Account zzdzb;
        private boolean zzecm;
        private String zzecn;
        private boolean zzeem;
        private boolean zzeen;
        private String zzeeo;
        private Set<Scope> zzees = new HashSet();
        private Map<Integer, zzn> zzeet = new HashMap();

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            zzbq.checkNotNull(googleSignInOptions);
            this.zzees = new HashSet(googleSignInOptions.zzeel);
            this.zzeem = googleSignInOptions.zzeem;
            this.zzeen = googleSignInOptions.zzeen;
            this.zzecm = googleSignInOptions.zzecm;
            this.zzecn = googleSignInOptions.zzecn;
            this.zzdzb = googleSignInOptions.zzdzb;
            this.zzeeo = googleSignInOptions.zzeeo;
            this.zzeet = GoogleSignInOptions.zzw(googleSignInOptions.zzeep);
        }

        private final String zzep(String str) {
            zzbq.zzgh(str);
            boolean z = this.zzecn == null || this.zzecn.equals(str);
            zzbq.checkArgument(z, "two different server client ids provided");
            return str;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (this.zzeet.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                throw new IllegalStateException("Only one extension per type may be added");
            }
            if (googleSignInOptionsExtension.getImpliedScopes() != null) {
                this.zzees.addAll(googleSignInOptionsExtension.getImpliedScopes());
            }
            this.zzeet.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new zzn(googleSignInOptionsExtension));
            return this;
        }

        public final GoogleSignInOptions build() {
            if (this.zzecm && (this.zzdzb == null || !this.zzees.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(new ArrayList(this.zzees), this.zzdzb, this.zzecm, this.zzeem, this.zzeen, this.zzecn, this.zzeeo, this.zzeet);
        }

        public final Builder requestEmail() {
            this.zzees.add(GoogleSignInOptions.zzeej);
            return this;
        }

        public final Builder requestId() {
            this.zzees.add(GoogleSignInOptions.zzeek);
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zzecm = true;
            this.zzecn = zzep(str);
            return this;
        }

        public final Builder requestProfile() {
            this.zzees.add(GoogleSignInOptions.zzeei);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.zzees.add(scope);
            this.zzees.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder requestServerAuthCode(String str, boolean z) {
            this.zzeem = true;
            this.zzecn = zzep(str);
            this.zzeen = z;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zzdzb = new Account(zzbq.zzgh(str), "com.google");
            return this;
        }

        public final Builder setHostedDomain(String str) {
            this.zzeeo = zzbq.zzgh(str);
            return this;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList<zzn> arrayList2) {
        this(i, (ArrayList) arrayList, account, z, z2, z3, str, str2, zzw(arrayList2));
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, zzn> map) {
        this.versionCode = i;
        this.zzeel = arrayList;
        this.zzdzb = account;
        this.zzecm = z;
        this.zzeem = z2;
        this.zzeen = z3;
        this.zzecn = str;
        this.zzeeo = str2;
        this.zzeep = new ArrayList(map.values());
        this.zzeeq = map;
    }

    private final JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzeel, zzeer);
            ArrayList arrayList = this.zzeel;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                jSONArray.put(((Scope) obj).zzagj());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.zzdzb != null) {
                jSONObject.put("accountName", this.zzdzb.name);
            }
            jSONObject.put("idTokenRequested", this.zzecm);
            jSONObject.put("forceCodeForRefreshToken", this.zzeen);
            jSONObject.put("serverAuthRequested", this.zzeem);
            if (!TextUtils.isEmpty(this.zzecn)) {
                jSONObject.put("serverClientId", this.zzecn);
            }
            if (!TextUtils.isEmpty(this.zzeeo)) {
                jSONObject.put("hostedDomain", this.zzeeo);
            }
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static GoogleSignInOptions zzeo(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Collection hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        Object optString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(3, new ArrayList(hashSet), !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null), new HashMap());
    }

    private static Map<Integer, zzn> zzw(@Nullable List<zzn> list) {
        Map<Integer, zzn> hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        for (zzn com_google_android_gms_auth_api_signin_internal_zzn : list) {
            hashMap.put(Integer.valueOf(com_google_android_gms_auth_api_signin_internal_zzn.getType()), com_google_android_gms_auth_api_signin_internal_zzn);
        }
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zzeep.size() > 0 || googleSignInOptions.zzeep.size() > 0 || this.zzeel.size() != googleSignInOptions.zzaar().size() || !this.zzeel.containsAll(googleSignInOptions.zzaar())) {
                return false;
            }
            if (this.zzdzb == null) {
                if (googleSignInOptions.zzdzb != null) {
                    return false;
                }
            } else if (!this.zzdzb.equals(googleSignInOptions.zzdzb)) {
                return false;
            }
            if (TextUtils.isEmpty(this.zzecn)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzecn)) {
                    return false;
                }
            } else if (!this.zzecn.equals(googleSignInOptions.zzecn)) {
                return false;
            }
            return this.zzeen == googleSignInOptions.zzeen && this.zzecm == googleSignInOptions.zzecm && this.zzeem == googleSignInOptions.zzeem;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public final Account getAccount() {
        return this.zzdzb;
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.zzeel.toArray(new Scope[this.zzeel.size()]);
    }

    public final String getServerClientId() {
        return this.zzecn;
    }

    public int hashCode() {
        List arrayList = new ArrayList();
        ArrayList arrayList2 = this.zzeel;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            arrayList.add(((Scope) obj).zzagj());
        }
        Collections.sort(arrayList);
        return new zzp().zzr(arrayList).zzr(this.zzdzb).zzr(this.zzecn).zzaq(this.zzeen).zzaq(this.zzecm).zzaq(this.zzeem).zzaba();
    }

    public final boolean isIdTokenRequested() {
        return this.zzecm;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.versionCode);
        zzbem.zzc(parcel, 2, zzaar(), false);
        zzbem.zza(parcel, 3, this.zzdzb, i, false);
        zzbem.zza(parcel, 4, this.zzecm);
        zzbem.zza(parcel, 5, this.zzeem);
        zzbem.zza(parcel, 6, this.zzeen);
        zzbem.zza(parcel, 7, this.zzecn, false);
        zzbem.zza(parcel, 8, this.zzeeo, false);
        zzbem.zzc(parcel, 9, this.zzeep, false);
        zzbem.zzai(parcel, zze);
    }

    public final ArrayList<Scope> zzaar() {
        return new ArrayList(this.zzeel);
    }

    public final boolean zzaas() {
        return this.zzeem;
    }

    public final String zzaat() {
        return toJsonObject().toString();
    }
}
