package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.C0658R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzca;

@Deprecated
public final class zzcc {
    private static final Object sLock = new Object();
    private static zzcc zzfre;
    private final String mAppId;
    private final Status zzfrf;
    private final boolean zzfrg;
    private final boolean zzfrh;

    private zzcc(Context context) {
        boolean z = true;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(C0658R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z2 = resources.getInteger(identifier) != 0;
            if (z2) {
                z = false;
            }
            this.zzfrh = z;
            z = z2;
        } else {
            this.zzfrh = false;
        }
        this.zzfrg = z;
        Object zzcm = zzbf.zzcm(context);
        if (zzcm == null) {
            zzcm = new zzca(context).getString("google_app_id");
        }
        if (TextUtils.isEmpty(zzcm)) {
            this.zzfrf = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.mAppId = null;
            return;
        }
        this.mAppId = zzcm;
        this.zzfrf = Status.zzfko;
    }

    public static String zzaiv() {
        return zzfu("getGoogleAppId").mAppId;
    }

    public static boolean zzaiw() {
        return zzfu("isMeasurementExplicitlyDisabled").zzfrh;
    }

    public static Status zzci(Context context) {
        Status status;
        zzbq.checkNotNull(context, "Context must not be null.");
        synchronized (sLock) {
            if (zzfre == null) {
                zzfre = new zzcc(context);
            }
            status = zzfre.zzfrf;
        }
        return status;
    }

    private static zzcc zzfu(String str) {
        zzcc com_google_android_gms_common_api_internal_zzcc;
        synchronized (sLock) {
            if (zzfre == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            com_google_android_gms_common_api_internal_zzcc = zzfre;
        }
        return com_google_android_gms_common_api_internal_zzcc;
    }
}
