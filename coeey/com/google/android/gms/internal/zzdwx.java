package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.GithubAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import java.util.Map;

public final class zzdwx implements AdditionalUserInfo {
    private final String zzlym;
    private boolean zzmca;
    private final Map<String, Object> zzmcs;

    public zzdwx(@NonNull String str, @NonNull String str2, boolean z) {
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        this.zzlym = str;
        this.zzmcs = zzdxh.zzon(str2);
        this.zzmca = z;
    }

    public zzdwx(boolean z) {
        this.zzmca = z;
        this.zzlym = null;
        this.zzmcs = null;
    }

    public final Map<String, Object> getProfile() {
        return this.zzmcs;
    }

    public final String getProviderId() {
        return this.zzlym;
    }

    public final String getUsername() {
        return GithubAuthProvider.PROVIDER_ID.equals(this.zzlym) ? (String) this.zzmcs.get(Event.LOGIN) : TwitterAuthProvider.PROVIDER_ID.equals(this.zzlym) ? (String) this.zzmcs.get("screen_name") : null;
    }

    public final boolean isNewUser() {
        return this.zzmca;
    }
}
