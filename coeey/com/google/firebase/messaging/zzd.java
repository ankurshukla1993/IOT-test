package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

final class zzd {
    private static void zzb(Context context, String str, Intent intent) {
        Bundle bundle = new Bundle();
        String stringExtra = intent.getStringExtra("google.c.a.c_id");
        if (stringExtra != null) {
            bundle.putString("_nmid", stringExtra);
        }
        stringExtra = intent.getStringExtra("google.c.a.c_l");
        if (stringExtra != null) {
            bundle.putString("_nmn", stringExtra);
        }
        stringExtra = intent.getStringExtra("from");
        if (stringExtra == null || !stringExtra.startsWith("/topics/")) {
            stringExtra = null;
        }
        if (stringExtra != null) {
            bundle.putString("_nt", stringExtra);
        }
        try {
            bundle.putInt("_nmt", Integer.valueOf(intent.getStringExtra("google.c.a.ts")).intValue());
        } catch (Throwable e) {
            Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", e);
        }
        if (intent.hasExtra("google.c.a.udt")) {
            try {
                bundle.putInt("_ndt", Integer.valueOf(intent.getStringExtra("google.c.a.udt")).intValue());
            } catch (Throwable e2) {
                Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", e2);
            }
        }
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            String valueOf = String.valueOf(bundle);
            Log.d("FirebaseMessaging", new StringBuilder((String.valueOf(str).length() + 22) + String.valueOf(valueOf).length()).append("Sending event=").append(str).append(" params=").append(valueOf).toString());
        }
        AppMeasurement zzcz = zzcz(context);
        if (zzcz != null) {
            zzcz.logEventInternal(AppMeasurement.FCM_ORIGIN, str, bundle);
        } else {
            Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
        }
    }

    private static AppMeasurement zzcz(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }

    public static void zze(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("google.c.a.abt");
        if (stringExtra != null) {
            zzc.zza(context, AppMeasurement.FCM_ORIGIN, Base64.decode(stringExtra, 0), new zzb(), 1);
        }
        zzb(context, "_nr", intent);
    }

    public static void zzf(Context context, Intent intent) {
        if (intent != null) {
            if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(intent.getStringExtra("google.c.a.tc"))) {
                AppMeasurement zzcz = zzcz(context);
                if (Log.isLoggable("FirebaseMessaging", 3)) {
                    Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
                }
                if (zzcz != null) {
                    String stringExtra = intent.getStringExtra("google.c.a.c_id");
                    zzcz.setUserPropertyInternal(AppMeasurement.FCM_ORIGIN, AppMeasurement$UserProperty.FIREBASE_LAST_NOTIFICATION, stringExtra);
                    Bundle bundle = new Bundle();
                    bundle.putString(Param.SOURCE, "Firebase");
                    bundle.putString(Param.MEDIUM, "notification");
                    bundle.putString(Param.CAMPAIGN, stringExtra);
                    zzcz.logEventInternal(AppMeasurement.FCM_ORIGIN, "_cmp", bundle);
                } else {
                    Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
                }
            } else if (Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
            }
        }
        zzb(context, "_no", intent);
    }

    public static void zzg(Context context, Intent intent) {
        zzb(context, "_nd", intent);
    }

    public static void zzh(Context context, Intent intent) {
        zzb(context, "_nf", intent);
    }
}
