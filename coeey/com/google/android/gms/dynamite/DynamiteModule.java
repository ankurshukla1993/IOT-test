package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.zze;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class DynamiteModule {
    private static Boolean zzgtw;
    private static zzk zzgtx;
    private static zzm zzgty;
    private static String zzgtz;
    private static final ThreadLocal<zza> zzgua = new ThreadLocal();
    private static final zzi zzgub = new zza();
    public static final zzd zzguc = new zzb();
    private static zzd zzgud = new zzc();
    public static final zzd zzgue = new zzd();
    public static final zzd zzguf = new zze();
    public static final zzd zzgug = new zzf();
    public static final zzd zzguh = new zzg();
    private final Context zzgui;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    static class zza {
        public Cursor zzguj;

        private zza() {
        }
    }

    static class zzb implements zzi {
        private final int zzguk;
        private final int zzgul = 0;

        public zzb(int i, int i2) {
            this.zzguk = i;
        }

        public final int zzab(Context context, String str) {
            return this.zzguk;
        }

        public final int zzc(Context context, String str, boolean z) {
            return 0;
        }
    }

    public static class zzc extends Exception {
        private zzc(String str) {
            super(str);
        }

        private zzc(String str, Throwable th) {
            super(str, th);
        }
    }

    public interface zzd {
        zzj zza(Context context, String str, zzi com_google_android_gms_dynamite_zzi) throws zzc;
    }

    private DynamiteModule(Context context) {
        this.zzgui = (Context) zzbq.checkNotNull(context);
    }

    private static Context zza(Context context, String str, int i, Cursor cursor, zzm com_google_android_gms_dynamite_zzm) {
        try {
            return (Context) zzn.zzx(com_google_android_gms_dynamite_zzm.zza(zzn.zzy(context), str, i, zzn.zzy(cursor)));
        } catch (Exception e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to load DynamiteLoader: ";
            String valueOf = String.valueOf(e.toString());
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
    }

    public static DynamiteModule zza(Context context, zzd com_google_android_gms_dynamite_DynamiteModule_zzd, String str) throws zzc {
        zza com_google_android_gms_dynamite_DynamiteModule_zza = (zza) zzgua.get();
        zza com_google_android_gms_dynamite_DynamiteModule_zza2 = new zza();
        zzgua.set(com_google_android_gms_dynamite_DynamiteModule_zza2);
        zzj zza;
        DynamiteModule zzad;
        try {
            zza = com_google_android_gms_dynamite_DynamiteModule_zzd.zza(context, str, zzgub);
            Log.i("DynamiteModule", new StringBuilder((String.valueOf(str).length() + 68) + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza.zzgum).append(" and remote module ").append(str).append(":").append(zza.zzgun).toString());
            if (zza.zzguo == 0 || ((zza.zzguo == -1 && zza.zzgum == 0) || (zza.zzguo == 1 && zza.zzgun == 0))) {
                throw new zzc("No acceptable module found. Local version is " + zza.zzgum + " and remote version is " + zza.zzgun + ".");
            } else if (zza.zzguo == -1) {
                zzad = zzad(context, str);
                if (com_google_android_gms_dynamite_DynamiteModule_zza2.zzguj != null) {
                    com_google_android_gms_dynamite_DynamiteModule_zza2.zzguj.close();
                }
                zzgua.set(com_google_android_gms_dynamite_DynamiteModule_zza);
                return zzad;
            } else if (zza.zzguo == 1) {
                zzad = zza(context, str, zza.zzgun);
                if (com_google_android_gms_dynamite_DynamiteModule_zza2.zzguj != null) {
                    com_google_android_gms_dynamite_DynamiteModule_zza2.zzguj.close();
                }
                zzgua.set(com_google_android_gms_dynamite_DynamiteModule_zza);
                return zzad;
            } else {
                throw new zzc("VersionPolicy returned invalid code:" + zza.zzguo);
            }
        } catch (Throwable e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to load remote module: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            if (zza.zzgum == 0 || com_google_android_gms_dynamite_DynamiteModule_zzd.zza(context, str, new zzb(zza.zzgum, 0)).zzguo != -1) {
                throw new zzc("Remote load failed. No local fallback found.", e);
            }
            zzad = zzad(context, str);
            if (com_google_android_gms_dynamite_DynamiteModule_zza2.zzguj != null) {
                com_google_android_gms_dynamite_DynamiteModule_zza2.zzguj.close();
            }
            zzgua.set(com_google_android_gms_dynamite_DynamiteModule_zza);
            return zzad;
        } catch (Throwable th) {
            if (com_google_android_gms_dynamite_DynamiteModule_zza2.zzguj != null) {
                com_google_android_gms_dynamite_DynamiteModule_zza2.zzguj.close();
            }
            zzgua.set(com_google_android_gms_dynamite_DynamiteModule_zza);
        }
    }

    private static DynamiteModule zza(Context context, String str, int i) throws zzc {
        synchronized (DynamiteModule.class) {
            Boolean bool = zzgtw;
        }
        if (bool != null) {
            return bool.booleanValue() ? zzc(context, str, i) : zzb(context, str, i);
        } else {
            throw new zzc("Failed to determine which loading route to use.");
        }
    }

    private static void zza(ClassLoader classLoader) throws zzc {
        Throwable e;
        try {
            zzm com_google_android_gms_dynamite_zzm;
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                com_google_android_gms_dynamite_zzm = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzm) {
                    com_google_android_gms_dynamite_zzm = (zzm) queryLocalInterface;
                } else {
                    Object com_google_android_gms_dynamite_zzn = new zzn(iBinder);
                }
            }
            zzgty = com_google_android_gms_dynamite_zzm;
        } catch (ClassNotFoundException e2) {
            e = e2;
            throw new zzc("Failed to instantiate dynamite loader", e);
        } catch (IllegalAccessException e3) {
            e = e3;
            throw new zzc("Failed to instantiate dynamite loader", e);
        } catch (InstantiationException e4) {
            e = e4;
            throw new zzc("Failed to instantiate dynamite loader", e);
        } catch (InvocationTargetException e5) {
            e = e5;
            throw new zzc("Failed to instantiate dynamite loader", e);
        } catch (NoSuchMethodException e6) {
            e = e6;
            throw new zzc("Failed to instantiate dynamite loader", e);
        }
    }

    public static int zzab(Context context, String str) {
        String str2;
        String str3;
        try {
            str2 = "com.google.android.gms.dynamite.descriptors.";
            str3 = "ModuleDescriptor";
            Class loadClass = context.getApplicationContext().getClassLoader().loadClass(new StringBuilder(((String.valueOf(str2).length() + 1) + String.valueOf(str).length()) + String.valueOf(str3).length()).append(str2).append(str).append(".").append(str3).toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            str2 = String.valueOf(declaredField.get(null));
            Log.e("DynamiteModule", new StringBuilder((String.valueOf(str2).length() + 51) + String.valueOf(str).length()).append("Module descriptor id '").append(str2).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            str2 = "DynamiteModule";
            str3 = "Failed to load module descriptor class: ";
            String valueOf = String.valueOf(e2.getMessage());
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return 0;
        }
    }

    public static int zzac(Context context, String str) {
        return zzc(context, str, false);
    }

    private static DynamiteModule zzad(Context context, String str) {
        String str2 = "DynamiteModule";
        String str3 = "Selected local version of ";
        String valueOf = String.valueOf(str);
        Log.i(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws zzc {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        zzk zzdc = zzdc(context);
        if (zzdc == null) {
            throw new zzc("Failed to create IDynamiteLoader.");
        }
        try {
            IObjectWrapper zza = zzdc.zza(zzn.zzy(context), str, i);
            if (zzn.zzx(zza) != null) {
                return new DynamiteModule((Context) zzn.zzx(zza));
            }
            throw new zzc("Failed to load remote module.");
        } catch (Throwable e) {
            throw new zzc("Failed to load remote module.", e);
        }
    }

    public static int zzc(Context context, String str, boolean z) {
        Object e;
        synchronized (DynamiteModule.class) {
            Boolean bool = zzgtw;
            if (bool == null) {
                try {
                    Class loadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                    Field declaredField = loadClass.getDeclaredField("sClassLoader");
                    synchronized (loadClass) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader != null) {
                            if (classLoader == ClassLoader.getSystemClassLoader()) {
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    zza(classLoader);
                                } catch (zzc e2) {
                                }
                                bool = Boolean.TRUE;
                            }
                        } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                            bool = Boolean.FALSE;
                        } else {
                            try {
                                int zze = zze(context, str, z);
                                if (zzgtz == null || zzgtz.isEmpty()) {
                                    return zze;
                                }
                                ClassLoader com_google_android_gms_dynamite_zzh = new zzh(zzgtz, ClassLoader.getSystemClassLoader());
                                zza(com_google_android_gms_dynamite_zzh);
                                declaredField.set(null, com_google_android_gms_dynamite_zzh);
                                zzgtw = Boolean.TRUE;
                                return zze;
                            } catch (zzc e3) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                                zzgtw = bool;
                                if (!bool.booleanValue()) {
                                    try {
                                    } catch (zzc e4) {
                                        String str2 = "DynamiteModule";
                                        String str3 = "Failed to retrieve remote module version: ";
                                        String valueOf = String.valueOf(e4.getMessage());
                                        Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException e5) {
                    e = e5;
                } catch (IllegalAccessException e6) {
                    e = e6;
                } catch (NoSuchFieldException e7) {
                    e = e7;
                }
            }
        }
        valueOf = String.valueOf(e);
        Log.w("DynamiteModule", new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to load module via V2: ").append(valueOf).toString());
        bool = Boolean.FALSE;
        zzgtw = bool;
        return !bool.booleanValue() ? zzd(context, str, z) : zze(context, str, z);
    }

    private static DynamiteModule zzc(Context context, String str, int i) throws zzc {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (DynamiteModule.class) {
            zzm com_google_android_gms_dynamite_zzm = zzgty;
        }
        if (com_google_android_gms_dynamite_zzm == null) {
            throw new zzc("DynamiteLoaderV2 was not cached.");
        }
        zza com_google_android_gms_dynamite_DynamiteModule_zza = (zza) zzgua.get();
        if (com_google_android_gms_dynamite_DynamiteModule_zza == null || com_google_android_gms_dynamite_DynamiteModule_zza.zzguj == null) {
            throw new zzc("No result cursor");
        }
        Context zza = zza(context.getApplicationContext(), str, i, com_google_android_gms_dynamite_DynamiteModule_zza.zzguj, com_google_android_gms_dynamite_zzm);
        if (zza != null) {
            return new DynamiteModule(zza);
        }
        throw new zzc("Failed to get module context");
    }

    private static int zzd(Context context, String str, boolean z) {
        zzk zzdc = zzdc(context);
        if (zzdc == null) {
            return 0;
        }
        try {
            return zzdc.zza(zzn.zzy(context), str, z);
        } catch (RemoteException e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to retrieve remote module version: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return 0;
        }
    }

    private static zzk zzdc(Context context) {
        synchronized (DynamiteModule.class) {
            zzk com_google_android_gms_dynamite_zzk;
            if (zzgtx != null) {
                com_google_android_gms_dynamite_zzk = zzgtx;
                return com_google_android_gms_dynamite_zzk;
            } else if (zze.zzafm().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            } else {
                try {
                    IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                    if (iBinder == null) {
                        com_google_android_gms_dynamite_zzk = null;
                    } else {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                        if (queryLocalInterface instanceof zzk) {
                            com_google_android_gms_dynamite_zzk = (zzk) queryLocalInterface;
                        } else {
                            Object com_google_android_gms_dynamite_zzl = new zzl(iBinder);
                        }
                    }
                    if (com_google_android_gms_dynamite_zzk != null) {
                        zzgtx = com_google_android_gms_dynamite_zzk;
                        return com_google_android_gms_dynamite_zzk;
                    }
                } catch (Exception e) {
                    String str = "DynamiteModule";
                    String str2 = "Failed to load IDynamiteLoader from GmsCore: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    return null;
                }
            }
        }
    }

    private static int zze(Context context, String str, boolean z) throws zzc {
        String str2;
        Throwable e;
        Cursor cursor;
        if (z) {
            try {
                str2 = "api_force_staging";
            } catch (Exception e2) {
                e = e2;
                cursor = null;
                try {
                    if (e instanceof zzc) {
                        throw e;
                    }
                    throw new zzc("V2 version check failed", e);
                } catch (Throwable th) {
                    e = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw e;
            }
        }
        str2 = "api";
        String str3 = "content://com.google.android.gms.chimera/";
        cursor = context.getContentResolver().query(Uri.parse(new StringBuilder(((String.valueOf(str3).length() + 1) + String.valueOf(str2).length()) + String.valueOf(str).length()).append(str3).append(str2).append("/").append(str).toString()), null, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int i = cursor.getInt(0);
                    if (i > 0) {
                        synchronized (DynamiteModule.class) {
                            zzgtz = cursor.getString(2);
                        }
                        zza com_google_android_gms_dynamite_DynamiteModule_zza = (zza) zzgua.get();
                        if (com_google_android_gms_dynamite_DynamiteModule_zza != null && com_google_android_gms_dynamite_DynamiteModule_zza.zzguj == null) {
                            com_google_android_gms_dynamite_DynamiteModule_zza.zzguj = cursor;
                            cursor = null;
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return i;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
        throw new zzc("Failed to connect to dynamite module ContentResolver.");
    }

    public final Context zzapp() {
        return this.zzgui;
    }

    public final IBinder zzgw(String str) throws zzc {
        Throwable e;
        String str2;
        String valueOf;
        try {
            return (IBinder) this.zzgui.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException e2) {
            e = e2;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            throw new zzc(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        } catch (InstantiationException e3) {
            e = e3;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new zzc(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        } catch (IllegalAccessException e4) {
            e = e4;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new zzc(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        }
    }
}
