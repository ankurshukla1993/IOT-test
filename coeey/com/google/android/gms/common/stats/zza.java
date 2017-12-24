package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.util.zzc;
import java.util.Collections;
import java.util.List;

public final class zza {
    private static final Object zzfxo = new Object();
    private static volatile zza zzgad;
    private static boolean zzgae = false;
    private final List<String> zzgaf = Collections.EMPTY_LIST;
    private final List<String> zzgag = Collections.EMPTY_LIST;
    private final List<String> zzgah = Collections.EMPTY_LIST;
    private final List<String> zzgai = Collections.EMPTY_LIST;

    private zza() {
    }

    public static zza zzalq() {
        if (zzgad == null) {
            synchronized (zzfxo) {
                if (zzgad == null) {
                    zzgad = new zza();
                }
            }
        }
        return zzgad;
    }

    public final boolean zza(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        return zza(context, context.getClass().getName(), intent, serviceConnection, i);
    }

    public final boolean zza(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i) {
        ComponentName component = intent.getComponent();
        if (!(component == null ? false : zzc.zzz(context, component.getPackageName()))) {
            return context.bindService(intent, serviceConnection, i);
        }
        Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
        return false;
    }
}
