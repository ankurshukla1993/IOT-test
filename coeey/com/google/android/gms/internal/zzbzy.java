package com.google.android.gms.internal;

public final class zzbzy {
    private static zzbzy zzhga;
    private final zzbzt zzhgb = new zzbzt();
    private final zzbzu zzhgc = new zzbzu();

    static {
        zzbzy com_google_android_gms_internal_zzbzy = new zzbzy();
        synchronized (zzbzy.class) {
            zzhga = com_google_android_gms_internal_zzbzy;
        }
    }

    private zzbzy() {
    }

    private static zzbzy zzaqo() {
        zzbzy com_google_android_gms_internal_zzbzy;
        synchronized (zzbzy.class) {
            com_google_android_gms_internal_zzbzy = zzhga;
        }
        return com_google_android_gms_internal_zzbzy;
    }

    public static zzbzt zzaqp() {
        return zzaqo().zzhgb;
    }

    public static zzbzu zzaqq() {
        return zzaqo().zzhgc;
    }
}
