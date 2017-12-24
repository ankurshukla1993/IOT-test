package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import java.io.File;

public final class zzar {
    public static zzs zza(Context context, zzam com_google_android_gms_internal_zzam) {
        zzam com_google_android_gms_internal_zzan;
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = new StringBuilder(String.valueOf(packageName).length() + 12).append(packageName).append("/").append(context.getPackageManager().getPackageInfo(packageName, 0).versionCode).toString();
        } catch (NameNotFoundException e) {
        }
        if (VERSION.SDK_INT >= 9) {
            com_google_android_gms_internal_zzan = new zzan();
        } else {
            Object com_google_android_gms_internal_zzaj = new zzaj(AndroidHttpClient.newInstance(str));
        }
        zzs com_google_android_gms_internal_zzs = new zzs(new zzag(file), new zzad(com_google_android_gms_internal_zzan));
        com_google_android_gms_internal_zzs.start();
        return com_google_android_gms_internal_zzs;
    }
}
