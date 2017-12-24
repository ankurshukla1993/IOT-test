package com.google.android.gms.measurement;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzckn;
import com.google.firebase.analytics.FirebaseAnalytics.Event;

@KeepForSdk
public final class AppMeasurement$Event extends Event {
    @KeepForSdk
    public static final String APP_EXCEPTION = "_ae";
    public static final String[] zzitl = new String[]{"app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "screen_view", "firebase_extra_parameter"};
    public static final String[] zzitm = new String[]{"_cd", APP_EXCEPTION, "_ui", "_ug", "_in", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", "_vs", "_ep"};

    private AppMeasurement$Event() {
    }

    public static String zzik(String str) {
        return zzckn.zza(str, zzitl, zzitm);
    }
}
