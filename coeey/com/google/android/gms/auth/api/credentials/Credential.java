package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Credential extends zzbej implements ReflectedParcelable {
    public static final Creator<Credential> CREATOR = new zza();
    public static final String EXTRA_KEY = "com.google.android.gms.credentials.Credential";
    @Nullable
    private final String mName;
    private final String zzbte;
    @Nullable
    private final String zzebn;
    @Nullable
    private final Uri zzebx;
    private final List<IdToken> zzeby;
    @Nullable
    private final String zzebz;
    @Nullable
    private final String zzeca;
    @Nullable
    private final String zzecb;
    @Nullable
    private final String zzecc;
    @Nullable
    private final String zzecd;

    public static class Builder {
        private String mName;
        private final String zzbte;
        private String zzebn;
        private Uri zzebx;
        private List<IdToken> zzeby;
        private String zzebz;
        private String zzeca;
        private String zzecb;
        private String zzecc;
        private String zzecd;

        public Builder(Credential credential) {
            this.zzbte = credential.zzbte;
            this.mName = credential.mName;
            this.zzebx = credential.zzebx;
            this.zzeby = credential.zzeby;
            this.zzebz = credential.zzebz;
            this.zzebn = credential.zzebn;
            this.zzeca = credential.zzeca;
            this.zzecb = credential.zzecb;
            this.zzecc = credential.zzecc;
            this.zzecd = credential.zzecd;
        }

        public Builder(String str) {
            this.zzbte = str;
        }

        public Credential build() {
            return new Credential(this.zzbte, this.mName, this.zzebx, this.zzeby, this.zzebz, this.zzebn, this.zzeca, this.zzecb, this.zzecc, this.zzecd);
        }

        public Builder setAccountType(String str) {
            this.zzebn = str;
            return this;
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setPassword(String str) {
            this.zzebz = str;
            return this;
        }

        public Builder setProfilePictureUri(Uri uri) {
            this.zzebx = uri;
            return this;
        }
    }

    Credential(String str, String str2, Uri uri, List<IdToken> list, String str3, String str4, String str5, String str6, String str7, String str8) {
        String trim = ((String) zzbq.checkNotNull(str, "credential identifier cannot be null")).trim();
        zzbq.zzh(trim, "credential identifier cannot be empty");
        if (str3 == null || !TextUtils.isEmpty(str3)) {
            if (str4 != null) {
                boolean z;
                if (!TextUtils.isEmpty(str4)) {
                    Uri parse = Uri.parse(str4);
                    if (!parse.isAbsolute() || !parse.isHierarchical() || TextUtils.isEmpty(parse.getScheme()) || TextUtils.isEmpty(parse.getAuthority())) {
                        z = false;
                        if (!Boolean.valueOf(z).booleanValue()) {
                            throw new IllegalArgumentException("Account type must be a valid Http/Https URI");
                        }
                    } else if (UriUtil.HTTP_SCHEME.equalsIgnoreCase(parse.getScheme()) || UriUtil.HTTPS_SCHEME.equalsIgnoreCase(parse.getScheme())) {
                        z = true;
                        if (Boolean.valueOf(z).booleanValue()) {
                            throw new IllegalArgumentException("Account type must be a valid Http/Https URI");
                        }
                    }
                }
                z = false;
                if (Boolean.valueOf(z).booleanValue()) {
                    throw new IllegalArgumentException("Account type must be a valid Http/Https URI");
                }
            }
            if (TextUtils.isEmpty(str4) || TextUtils.isEmpty(str3)) {
                if (str2 != null && TextUtils.isEmpty(str2.trim())) {
                    str2 = null;
                }
                this.mName = str2;
                this.zzebx = uri;
                this.zzeby = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
                this.zzbte = trim;
                this.zzebz = str3;
                this.zzebn = str4;
                this.zzeca = str5;
                this.zzecb = str6;
                this.zzecc = str7;
                this.zzecd = str8;
                return;
            }
            throw new IllegalArgumentException("Password and AccountType are mutually exclusive");
        }
        throw new IllegalArgumentException("Password must not be empty if set");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Credential)) {
            return false;
        }
        Credential credential = (Credential) obj;
        return TextUtils.equals(this.zzbte, credential.zzbte) && TextUtils.equals(this.mName, credential.mName) && zzbg.equal(this.zzebx, credential.zzebx) && TextUtils.equals(this.zzebz, credential.zzebz) && TextUtils.equals(this.zzebn, credential.zzebn) && TextUtils.equals(this.zzeca, credential.zzeca);
    }

    @Nullable
    public String getAccountType() {
        return this.zzebn;
    }

    @Nullable
    public String getFamilyName() {
        return this.zzecd;
    }

    @Nullable
    public String getGeneratedPassword() {
        return this.zzeca;
    }

    @Nullable
    public String getGivenName() {
        return this.zzecc;
    }

    public String getId() {
        return this.zzbte;
    }

    public List<IdToken> getIdTokens() {
        return this.zzeby;
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    @Nullable
    public String getPassword() {
        return this.zzebz;
    }

    @Nullable
    public Uri getProfilePictureUri() {
        return this.zzebx;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzbte, this.mName, this.zzebx, this.zzebz, this.zzebn, this.zzeca});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, getId(), false);
        zzbem.zza(parcel, 2, getName(), false);
        zzbem.zza(parcel, 3, getProfilePictureUri(), i, false);
        zzbem.zzc(parcel, 4, getIdTokens(), false);
        zzbem.zza(parcel, 5, getPassword(), false);
        zzbem.zza(parcel, 6, getAccountType(), false);
        zzbem.zza(parcel, 7, getGeneratedPassword(), false);
        zzbem.zza(parcel, 8, this.zzecb, false);
        zzbem.zza(parcel, 9, getGivenName(), false);
        zzbem.zza(parcel, 10, getFamilyName(), false);
        zzbem.zzai(parcel, zze);
    }
}
