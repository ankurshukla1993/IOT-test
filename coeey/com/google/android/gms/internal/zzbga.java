package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzq;

public final class zzbga {
    private static Context zzgck;
    private static Boolean zzgcl;

    public static synchronized boolean zzcw(Context context) {
        boolean booleanValue;
        synchronized (zzbga.class) {
            Context applicationContext = context.getApplicationContext();
            if (zzgck == null || zzgcl == null || zzgck != applicationContext) {
                zzgcl = null;
                if (zzq.isAtLeastO()) {
                    zzgcl = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
                } else {
                    try {
                        context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        zzgcl = Boolean.valueOf(true);
                    } catch (ClassNotFoundException e) {
                        zzgcl = Boolean.valueOf(false);
                    }
                }
                zzgck = applicationContext;
                booleanValue = zzgcl.booleanValue();
            } else {
                booleanValue = zzgcl.booleanValue();
            }
        }
        return booleanValue;
    }
}
