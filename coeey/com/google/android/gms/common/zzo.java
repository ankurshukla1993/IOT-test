package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.C0658R;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzx;
import java.util.concurrent.atomic.AtomicBoolean;

public class zzo {
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 11720000;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    private static boolean zzfip = false;
    private static boolean zzfiq = false;
    private static boolean zzfir = false;
    private static boolean zzfis = false;
    static final AtomicBoolean zzfit = new AtomicBoolean();
    private static final AtomicBoolean zzfiu = new AtomicBoolean();

    zzo() {
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return zze.zzafm().getErrorResolutionPendingIntent(context, i, i2);
    }

    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.getStatusString(i);
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            context.getResources().getString(C0658R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!("com.google.android.gms".equals(context.getPackageName()) || zzfiu.get())) {
            int zzcn = zzbf.zzcn(context);
            if (zzcn == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            } else if (zzcn != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                String str = "com.google.android.gms.version";
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 290).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(GOOGLE_PLAY_SERVICES_VERSION_CODE).append(" but found ").append(zzcn).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"").append(str).append("\" android:value=\"@integer/google_play_services_version\" />").toString());
            }
        }
        int i = (zzi.zzcq(context) || zzi.zzcs(context)) ? 0 : 1;
        PackageInfo packageInfo = null;
        if (i != 0) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (NameNotFoundException e) {
                Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                return 9;
            }
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            zzp.zzcg(context);
            if (i != 0) {
                if (zzp.zza(packageInfo, zzj.zzfil) == null) {
                    Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                    return 9;
                }
                if (zzp.zza(packageInfo2, new zzg[]{zzp.zza(packageInfo, zzj.zzfil)}) == null) {
                    Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                    return 9;
                }
            } else if (zzp.zza(packageInfo2, zzj.zzfil) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (packageInfo2.versionCode / 1000 < GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000) {
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires " + GOOGLE_PLAY_SERVICES_VERSION_CODE + " but found " + packageInfo2.versionCode);
                return 2;
            }
            ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
            if (applicationInfo == null) {
                try {
                    applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                } catch (Throwable e2) {
                    Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", e2);
                    return 1;
                }
            }
            return !applicationInfo.enabled ? 3 : 0;
        } catch (NameNotFoundException e3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            default:
                return false;
        }
    }

    @TargetApi(19)
    @Deprecated
    public static boolean zzb(Context context, int i, String str) {
        return zzx.zzb(context, i, str);
    }

    @Deprecated
    public static void zzbn(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = zze.zzafm().isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable != 0) {
            zze.zzafm();
            Intent zza = zze.zza(context, isGooglePlayServicesAvailable, "e");
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + isGooglePlayServicesAvailable);
            if (zza == null) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", zza);
        }
    }

    @Deprecated
    public static void zzcc(Context context) {
        if (!zzfit.getAndSet(true)) {
            try {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (notificationManager != null) {
                    notificationManager.cancel(10436);
                }
            } catch (SecurityException e) {
            }
        }
    }

    @Deprecated
    public static int zzcd(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return i;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzcf(android.content.Context r7) {
        /*
        r0 = 0;
        r1 = 1;
        r2 = zzfis;
        if (r2 != 0) goto L_0x002d;
    L_0x0006:
        r2 = com.google.android.gms.internal.zzbgc.zzcy(r7);	 Catch:{ NameNotFoundException -> 0x0042 }
        r3 = "com.google.android.gms";
        r4 = 64;
        r2 = r2.getPackageInfo(r3, r4);	 Catch:{ NameNotFoundException -> 0x0042 }
        if (r2 == 0) goto L_0x003e;
    L_0x0014:
        com.google.android.gms.common.zzp.zzcg(r7);	 Catch:{ NameNotFoundException -> 0x0042 }
        r3 = 1;
        r3 = new com.google.android.gms.common.zzg[r3];	 Catch:{ NameNotFoundException -> 0x0042 }
        r4 = 0;
        r5 = com.google.android.gms.common.zzj.zzfil;	 Catch:{ NameNotFoundException -> 0x0042 }
        r6 = 1;
        r5 = r5[r6];	 Catch:{ NameNotFoundException -> 0x0042 }
        r3[r4] = r5;	 Catch:{ NameNotFoundException -> 0x0042 }
        r2 = com.google.android.gms.common.zzp.zza(r2, r3);	 Catch:{ NameNotFoundException -> 0x0042 }
        if (r2 == 0) goto L_0x003e;
    L_0x0028:
        r2 = 1;
        zzfir = r2;	 Catch:{ NameNotFoundException -> 0x0042 }
    L_0x002b:
        zzfis = r1;
    L_0x002d:
        r2 = zzfir;
        if (r2 != 0) goto L_0x003c;
    L_0x0031:
        r2 = "user";
        r3 = android.os.Build.TYPE;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x003d;
    L_0x003c:
        r0 = r1;
    L_0x003d:
        return r0;
    L_0x003e:
        r2 = 0;
        zzfir = r2;	 Catch:{ NameNotFoundException -> 0x0042 }
        goto L_0x002b;
    L_0x0042:
        r2 = move-exception;
        r3 = "GooglePlayServicesUtil";
        r4 = "Cannot find Google Play services package name.";
        android.util.Log.w(r3, r4, r2);	 Catch:{ all -> 0x004d }
        zzfis = r1;
        goto L_0x002d;
    L_0x004d:
        r0 = move-exception;
        zzfis = r1;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzo.zzcf(android.content.Context):boolean");
    }

    @Deprecated
    public static boolean zze(Context context, int i) {
        return i == 18 ? true : i == 1 ? zzv(context, "com.google.android.gms") : false;
    }

    @Deprecated
    public static boolean zzf(Context context, int i) {
        return zzx.zzf(context, i);
    }

    @TargetApi(21)
    static boolean zzv(Context context, String str) {
        boolean equals = str.equals("com.google.android.gms");
        if (zzq.zzamb()) {
            try {
                for (SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                    if (str.equals(appPackageName.getAppPackageName())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (equals) {
                return applicationInfo.enabled;
            }
            if (applicationInfo.enabled) {
                Object obj;
                if (zzq.zzaly()) {
                    Bundle applicationRestrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
                    if (applicationRestrictions != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(applicationRestrictions.getString("restricted_profile"))) {
                        obj = 1;
                        if (obj == null) {
                            return true;
                        }
                    }
                }
                obj = null;
                if (obj == null) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e2) {
            return false;
        }
    }
}
