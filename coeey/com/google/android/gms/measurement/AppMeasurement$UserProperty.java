package com.google.android.gms.measurement;

import com.facebook.AccessToken;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzckn;
import com.google.firebase.analytics.FirebaseAnalytics.UserProperty;

@KeepForSdk
public final class AppMeasurement$UserProperty extends UserProperty {
    @KeepForSdk
    public static final String FIREBASE_LAST_NOTIFICATION = "_ln";
    public static final String[] zzits = new String[]{"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", AccessToken.USER_ID_KEY, "first_open_after_install"};
    public static final String[] zzitt = new String[]{FIREBASE_LAST_NOTIFICATION, "_fot", "_fvt", "_ldl", "_id", "_fi"};

    private AppMeasurement$UserProperty() {
    }

    public static String zzik(String str) {
        return zzckn.zza(str, zzits, zzitt);
    }
}
