package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.util.Log;

final class zzctl extends zzctg<String> {
    zzctl(zzctn com_google_android_gms_internal_zzctn, String str, String str2) {
        super(com_google_android_gms_internal_zzctn, str, str2);
    }

    private final String zzc(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getString(this.zzjuj, null);
        } catch (Throwable e) {
            Throwable th = e;
            String str = "PhenotypeFlag";
            String str2 = "Invalid string value in SharedPreferences for ";
            String valueOf = String.valueOf(this.zzjuj);
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return null;
        }
    }

    public final /* synthetic */ Object zzb(SharedPreferences sharedPreferences) {
        return zzc(sharedPreferences);
    }

    public final /* synthetic */ Object zzkn(String str) {
        return str;
    }
}
