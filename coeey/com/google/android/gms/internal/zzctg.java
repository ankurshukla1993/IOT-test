package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.UserManager;

public abstract class zzctg<T> {
    private static Context zzaif = null;
    private static boolean zzccv = false;
    private static final Object zzjuh = new Object();
    private final zzctn zzjui;
    final String zzjuj;
    private final String zzjuk;
    private final T zzjul;
    private T zzjum;

    private zzctg(zzctn com_google_android_gms_internal_zzctn, String str, T t) {
        this.zzjum = null;
        if (com_google_android_gms_internal_zzctn.zzjuq == null && com_google_android_gms_internal_zzctn.zzjur == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        } else if (com_google_android_gms_internal_zzctn.zzjuq == null || com_google_android_gms_internal_zzctn.zzjur == null) {
            this.zzjui = com_google_android_gms_internal_zzctn;
            this.zzjuk = com_google_android_gms_internal_zzctn.zzkq(str);
            String valueOf = String.valueOf(com_google_android_gms_internal_zzctn.zzjut);
            String valueOf2 = String.valueOf(str);
            this.zzjuj = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            this.zzjul = t;
        } else {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
    }

    private static zzctg<String> zza(zzctn com_google_android_gms_internal_zzctn, String str, String str2) {
        return new zzctl(com_google_android_gms_internal_zzctn, str, str2);
    }

    static <V> V zza(zzctm<V> com_google_android_gms_internal_zzctm_V) {
        V zzbcd;
        long clearCallingIdentity;
        try {
            zzbcd = com_google_android_gms_internal_zzctm_V.zzbcd();
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            zzbcd = com_google_android_gms_internal_zzctm_V.zzbcd();
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
        return zzbcd;
    }

    @TargetApi(24)
    private final T zzbce() {
        if (!((Boolean) zza(zzctj.zzjup)).booleanValue()) {
            if (this.zzjui.zzjur != null) {
                String str = (String) zza(new zzcth(this, zzcss.zza(zzaif.getContentResolver(), this.zzjui.zzjur)));
                if (str != null) {
                    return zzkn(str);
                }
            } else if (this.zzjui.zzjuq != null) {
                if (VERSION.SDK_INT >= 24 && !zzaif.isDeviceProtectedStorage() && !((UserManager) zzaif.getSystemService(UserManager.class)).isUserUnlocked()) {
                    return null;
                }
                SharedPreferences sharedPreferences = zzaif.getSharedPreferences(this.zzjui.zzjuq, 0);
                if (sharedPreferences.contains(this.zzjuj)) {
                    return zzb(sharedPreferences);
                }
            }
        }
        return null;
    }

    private final T zzbcf() {
        if (this.zzjuk != null) {
            String str = (String) zza(new zzcti(this));
            if (str != null) {
                return zzkn(str);
            }
        }
        return null;
    }

    public static void zzdw(Context context) {
        if (zzaif == null) {
            synchronized (zzjuh) {
                if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                zzaif = context;
            }
            zzccv = false;
        }
    }

    public final T get() {
        if (zzaif == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        T zzbcf;
        if (this.zzjui.zzjuv) {
            zzbcf = zzbcf();
            if (zzbcf != null) {
                return zzbcf;
            }
            zzbcf = zzbce();
            if (zzbcf != null) {
                return zzbcf;
            }
        }
        zzbcf = zzbce();
        if (zzbcf != null) {
            return zzbcf;
        }
        zzbcf = zzbcf();
        if (zzbcf != null) {
            return zzbcf;
        }
        return this.zzjul;
    }

    public abstract T zzb(SharedPreferences sharedPreferences);

    final /* synthetic */ String zzbch() {
        return zzdld.zza(zzaif.getContentResolver(), this.zzjuk, null);
    }

    public abstract T zzkn(String str);
}
