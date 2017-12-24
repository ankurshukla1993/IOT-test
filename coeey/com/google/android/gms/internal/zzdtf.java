package com.google.android.gms.internal;

import java.io.PrintStream;
import java.io.PrintWriter;

public final class zzdtf {
    private static zzdtg zzlvz;

    static final class zza extends zzdtg {
        zza() {
        }

        public final void zza(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }

        public final void zza(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    static {
        zzdtg com_google_android_gms_internal_zzdtj;
        try {
            Integer zzbov = zzbov();
            if (zzbov == null || zzbov.intValue() < 19) {
                com_google_android_gms_internal_zzdtj = (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? 1 : null) != null ? new zzdtj() : new zza();
                zzlvz = com_google_android_gms_internal_zzdtj;
            }
            com_google_android_gms_internal_zzdtj = new zzdtk();
            zzlvz = com_google_android_gms_internal_zzdtj;
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            th.printStackTrace(System.err);
            com_google_android_gms_internal_zzdtj = new zza();
        }
    }

    public static void zza(Throwable th, PrintStream printStream) {
        zzlvz.zza(th, printStream);
    }

    public static void zza(Throwable th, PrintWriter printWriter) {
        zzlvz.zza(th, printWriter);
    }

    private static Integer zzbov() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
