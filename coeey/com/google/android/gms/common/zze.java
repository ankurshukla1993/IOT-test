package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzak;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzbgc;

public class zze {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzo.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zze zzfid = new zze();

    zze() {
    }

    @Nullable
    public static Intent zza(Context context, int i, @Nullable String str) {
        switch (i) {
            case 1:
            case 2:
                return (context == null || !zzi.zzcq(context)) ? zzak.zzt("com.google.android.gms", zzu(context, str)) : zzak.zzalb();
            case 3:
                return zzak.zzgf("com.google.android.gms");
            default:
                return null;
        }
    }

    public static zze zzafm() {
        return zzfid;
    }

    public static void zzcb(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzo.zzbn(context);
    }

    public static void zzcc(Context context) {
        zzo.zzcc(context);
    }

    public static int zzcd(Context context) {
        return zzo.zzcd(context);
    }

    public static boolean zze(Context context, int i) {
        return zzo.zze(context, i);
    }

    private static String zzu(@Nullable Context context, @Nullable String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gcore_");
        stringBuilder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        stringBuilder.append("-");
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        stringBuilder.append("-");
        if (context != null) {
            stringBuilder.append(context.getPackageName());
        }
        stringBuilder.append("-");
        if (context != null) {
            try {
                stringBuilder.append(zzbgc.zzcy(context).getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
            }
        }
        return stringBuilder.toString();
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return zza(context, i, i2, null);
    }

    public String getErrorString(int i) {
        return zzo.getErrorString(i);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        int isGooglePlayServicesAvailable = zzo.isGooglePlayServicesAvailable(context);
        return zzo.zze(context, isGooglePlayServicesAvailable) ? 18 : isGooglePlayServicesAvailable;
    }

    public boolean isUserResolvableError(int i) {
        return zzo.isUserRecoverableError(i);
    }

    @Nullable
    public final PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        Intent zza = zza(context, i, str);
        return zza == null ? null : PendingIntent.getActivity(context, i2, zza, 268435456);
    }

    @Nullable
    @Deprecated
    public final Intent zzbp(int i) {
        return zza(null, i, null);
    }
}
