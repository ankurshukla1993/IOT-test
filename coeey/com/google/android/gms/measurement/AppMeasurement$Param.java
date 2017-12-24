package com.google.android.gms.measurement;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzckn;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

@KeepForSdk
public final class AppMeasurement$Param extends Param {
    @KeepForSdk
    public static final String FATAL = "fatal";
    @KeepForSdk
    public static final String TIMESTAMP = "timestamp";
    public static final String[] zzitn = new String[]{"firebase_conversion", "engagement_time_msec", "exposure_time", "ad_event_id", "ad_unit_id", "firebase_error", "firebase_error_value", "firebase_error_length", "firebase_event_origin", "firebase_screen", "firebase_screen_class", "firebase_screen_id", "firebase_previous_screen", "firebase_previous_class", "firebase_previous_id", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic", "update_with_analytics", "previous_first_open_count", "system_app", "system_app_update", "previous_install_count", "firebase_event_id", "firebase_extra_params_ct", "firebase_group_name", "firebase_list_length", "firebase_index", "firebase_event_name"};
    public static final String[] zzito = new String[]{"_c", "_et", "_xt", "_aeid", "_ai", "_err", "_ev", "_el", "_o", "_sn", "_sc", "_si", "_pn", "_pc", "_pi", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt", "_uwa", "_pfo", "_sys", "_sysu", "_pin", "_eid", "_epc", "_gn", "_ll", "_i", "_en"};

    private AppMeasurement$Param() {
    }

    public static String zzik(String str) {
        return zzckn.zza(str, zzitn, zzito);
    }
}
