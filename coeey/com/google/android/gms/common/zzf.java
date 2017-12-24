package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.zzba;
import com.google.android.gms.common.internal.zzbb;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzf {
    private static zzba zzfie;
    private static final Object zzfif = new Object();
    private static Context zzfig;

    static boolean zza(String str, zzg com_google_android_gms_common_zzg) {
        return zza(str, com_google_android_gms_common_zzg, false);
    }

    private static boolean zza(String str, zzg com_google_android_gms_common_zzg, boolean z) {
        boolean z2 = false;
        if (zzafn()) {
            zzbq.checkNotNull(zzfig);
            try {
                z2 = zzfie.zza(new zzm(str, com_google_android_gms_common_zzg, z), zzn.zzy(zzfig.getPackageManager()));
            } catch (Throwable e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            }
        }
        return z2;
    }

    private static boolean zzafn() {
        boolean z = true;
        if (zzfie == null) {
            zzbq.checkNotNull(zzfig);
            synchronized (zzfif) {
                if (zzfie == null) {
                    try {
                        zzfie = zzbb.zzam(DynamiteModule.zza(zzfig, DynamiteModule.zzguf, "com.google.android.gms.googlecertificates").zzgw("com.google.android.gms.common.GoogleCertificatesImpl"));
                    } catch (Throwable e) {
                        Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", e);
                        z = false;
                    }
                }
            }
        }
        return z;
    }

    static boolean zzb(String str, zzg com_google_android_gms_common_zzg) {
        return zza(str, com_google_android_gms_common_zzg, true);
    }

    static synchronized void zzce(Context context) {
        synchronized (zzf.class) {
            if (zzfig != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                zzfig = context.getApplicationContext();
            }
        }
    }
}
