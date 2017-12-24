package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class zzab {
    public static boolean DEBUG;
    private static String TAG;

    static class zza {
        public static final boolean zzbi = zzab.DEBUG;
        private final List<zzac> zzbj = new ArrayList();
        private boolean zzbk = false;

        zza() {
        }

        protected final void finalize() throws Throwable {
            if (!this.zzbk) {
                zzc("Request on the loose");
                zzab.zzc("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        public final synchronized void zza(String str, long j) {
            if (this.zzbk) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.zzbj.add(new zzac(str, j, SystemClock.elapsedRealtime()));
        }

        public final synchronized void zzc(String str) {
            long j;
            this.zzbk = true;
            if (this.zzbj.size() == 0) {
                j = 0;
            } else {
                j = ((zzac) this.zzbj.get(this.zzbj.size() - 1)).time - ((zzac) this.zzbj.get(0)).time;
            }
            if (j > 0) {
                long j2 = ((zzac) this.zzbj.get(0)).time;
                zzab.zzb("(%-4d ms) %s", Long.valueOf(j), str);
                j = j2;
                for (zzac com_google_android_gms_internal_zzac : this.zzbj) {
                    zzab.zzb("(+%-4d) [%2d] %s", Long.valueOf(com_google_android_gms_internal_zzac.time - j), Long.valueOf(com_google_android_gms_internal_zzac.zzbl), com_google_android_gms_internal_zzac.name);
                    j = com_google_android_gms_internal_zzac.time;
                }
            }
        }
    }

    static {
        String str = "Volley";
        TAG = str;
        DEBUG = Log.isLoggable(str, 2);
    }

    public static void zza(String str, Object... objArr) {
        if (DEBUG) {
            Log.v(TAG, zzd(str, objArr));
        }
    }

    public static void zza(Throwable th, String str, Object... objArr) {
        Log.e(TAG, zzd(str, objArr), th);
    }

    public static void zzb(String str, Object... objArr) {
        Log.d(TAG, zzd(str, objArr));
    }

    public static void zzc(String str, Object... objArr) {
        Log.e(TAG, zzd(str, objArr));
    }

    private static String zzd(String str, Object... objArr) {
        String methodName;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str2 = "<unknown>";
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClass().equals(zzab.class)) {
                str2 = stackTrace[i].getClassName();
                str2 = str2.substring(str2.lastIndexOf(46) + 1);
                str2 = str2.substring(str2.lastIndexOf(36) + 1);
                methodName = stackTrace[i].getMethodName();
                methodName = new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(methodName).length()).append(str2).append(".").append(methodName).toString();
                break;
            }
        }
        methodName = str2;
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), methodName, str});
    }
}
