package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzca;
import com.google.android.gms.common.util.zzu;
import java.util.Arrays;

public final class FirebaseOptions {
    private final String zzekh;
    private final String zzlwt;
    private final String zzlwu;
    private final String zzlwv;
    private final String zzlww;
    private final String zzlwx;
    private final String zzlwy;

    public static final class Builder {
        private String zzekh;
        private String zzlwt;
        private String zzlwu;
        private String zzlwv;
        private String zzlww;
        private String zzlwx;
        private String zzlwy;

        public Builder(FirebaseOptions firebaseOptions) {
            this.zzekh = firebaseOptions.zzekh;
            this.zzlwt = firebaseOptions.zzlwt;
            this.zzlwu = firebaseOptions.zzlwu;
            this.zzlwv = firebaseOptions.zzlwv;
            this.zzlww = firebaseOptions.zzlww;
            this.zzlwx = firebaseOptions.zzlwx;
            this.zzlwy = firebaseOptions.zzlwy;
        }

        public final FirebaseOptions build() {
            return new FirebaseOptions(this.zzekh, this.zzlwt, this.zzlwu, this.zzlwv, this.zzlww, this.zzlwx, this.zzlwy);
        }

        public final Builder setApiKey(@NonNull String str) {
            this.zzlwt = zzbq.zzh(str, "ApiKey must be set.");
            return this;
        }

        public final Builder setApplicationId(@NonNull String str) {
            this.zzekh = zzbq.zzh(str, "ApplicationId must be set.");
            return this;
        }

        public final Builder setDatabaseUrl(@Nullable String str) {
            this.zzlwu = str;
            return this;
        }

        public final Builder setGcmSenderId(@Nullable String str) {
            this.zzlww = str;
            return this;
        }

        public final Builder setProjectId(@Nullable String str) {
            this.zzlwy = str;
            return this;
        }

        public final Builder setStorageBucket(@Nullable String str) {
            this.zzlwx = str;
            return this;
        }
    }

    private FirebaseOptions(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        zzbq.zza(!zzu.zzgn(str), (Object) "ApplicationId must be set.");
        this.zzekh = str;
        this.zzlwt = str2;
        this.zzlwu = str3;
        this.zzlwv = str4;
        this.zzlww = str5;
        this.zzlwx = str6;
        this.zzlwy = str7;
    }

    public static FirebaseOptions fromResource(Context context) {
        zzca com_google_android_gms_common_internal_zzca = new zzca(context);
        Object string = com_google_android_gms_common_internal_zzca.getString("google_app_id");
        return TextUtils.isEmpty(string) ? null : new FirebaseOptions(string, com_google_android_gms_common_internal_zzca.getString("google_api_key"), com_google_android_gms_common_internal_zzca.getString("firebase_database_url"), com_google_android_gms_common_internal_zzca.getString("ga_trackingId"), com_google_android_gms_common_internal_zzca.getString("gcm_defaultSenderId"), com_google_android_gms_common_internal_zzca.getString("google_storage_bucket"), com_google_android_gms_common_internal_zzca.getString("project_id"));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
        return zzbg.equal(this.zzekh, firebaseOptions.zzekh) && zzbg.equal(this.zzlwt, firebaseOptions.zzlwt) && zzbg.equal(this.zzlwu, firebaseOptions.zzlwu) && zzbg.equal(this.zzlwv, firebaseOptions.zzlwv) && zzbg.equal(this.zzlww, firebaseOptions.zzlww) && zzbg.equal(this.zzlwx, firebaseOptions.zzlwx) && zzbg.equal(this.zzlwy, firebaseOptions.zzlwy);
    }

    public final String getApiKey() {
        return this.zzlwt;
    }

    public final String getApplicationId() {
        return this.zzekh;
    }

    public final String getDatabaseUrl() {
        return this.zzlwu;
    }

    public final String getGcmSenderId() {
        return this.zzlww;
    }

    public final String getProjectId() {
        return this.zzlwy;
    }

    public final String getStorageBucket() {
        return this.zzlwx;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzekh, this.zzlwt, this.zzlwu, this.zzlwv, this.zzlww, this.zzlwx, this.zzlwy});
    }

    public final String toString() {
        return zzbg.zzw(this).zzg("applicationId", this.zzekh).zzg("apiKey", this.zzlwt).zzg("databaseUrl", this.zzlwu).zzg("gcmSenderId", this.zzlww).zzg("storageBucket", this.zzlwx).zzg("projectId", this.zzlwy).toString();
    }
}
