package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgc;

public class zzp {
    private static zzp zzfiv;
    private final Context mContext;

    private zzp(Context context) {
        this.mContext = context.getApplicationContext();
    }

    static zzg zza(PackageInfo packageInfo, zzg... com_google_android_gms_common_zzgArr) {
        int i = 0;
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzh com_google_android_gms_common_zzh = new zzh(packageInfo.signatures[0].toByteArray());
        while (i < com_google_android_gms_common_zzgArr.length) {
            if (com_google_android_gms_common_zzgArr[i].equals(com_google_android_gms_common_zzh)) {
                return com_google_android_gms_common_zzgArr[i];
            }
            i++;
        }
        return null;
    }

    private static boolean zza(PackageInfo packageInfo, boolean z) {
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            zzg zza;
            if (z) {
                zza = zza(packageInfo, zzj.zzfil);
            } else {
                zza = zza(packageInfo, zzj.zzfil[0]);
            }
            if (zza != null) {
                return true;
            }
        }
        return false;
    }

    private static boolean zzb(PackageInfo packageInfo, boolean z) {
        boolean z2 = false;
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
        } else {
            zzg com_google_android_gms_common_zzh = new zzh(packageInfo.signatures[0].toByteArray());
            String str = packageInfo.packageName;
            z2 = z ? zzf.zzb(str, com_google_android_gms_common_zzh) : zzf.zza(str, com_google_android_gms_common_zzh);
            if (!z2) {
                Log.d("GoogleSignatureVerifier", "Cert not in list. atk=" + z);
            }
        }
        return z2;
    }

    public static zzp zzcg(Context context) {
        zzbq.checkNotNull(context);
        synchronized (zzp.class) {
            if (zzfiv == null) {
                zzf.zzce(context);
                zzfiv = new zzp(context);
            }
        }
        return zzfiv;
    }

    private final boolean zzft(String str) {
        try {
            PackageInfo packageInfo = zzbgc.zzcy(this.mContext).getPackageInfo(str, 64);
            if (packageInfo == null) {
                return false;
            }
            if (zzo.zzcf(this.mContext)) {
                return zzb(packageInfo, true);
            }
            boolean zzb = zzb(packageInfo, false);
            if (!zzb && zzb(packageInfo, true)) {
                Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
            }
            return zzb;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public final boolean zza(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (!zza(packageInfo, true)) {
            return false;
        }
        if (zzo.zzcf(this.mContext)) {
            return true;
        }
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        return false;
    }

    public final boolean zzbq(int i) {
        String[] packagesForUid = zzbgc.zzcy(this.mContext).getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            return false;
        }
        for (String zzft : packagesForUid) {
            if (zzft(zzft)) {
                return true;
            }
        }
        return false;
    }
}
