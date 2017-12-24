package com.google.firebase.messaging;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzfhj;
import com.google.android.gms.internal.zzfil;
import com.google.android.gms.internal.zzfim;
import com.google.android.gms.measurement.AppMeasurement;
import humanize.util.Constants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class zzc {
    private static Bundle zza(@NonNull zzfim com_google_android_gms_internal_zzfim) {
        return zzaz(com_google_android_gms_internal_zzfim.zzpld, com_google_android_gms_internal_zzfim.zzple);
    }

    @Nullable
    private static Object zza(@NonNull zzfim com_google_android_gms_internal_zzfim, @NonNull String str, @NonNull zzb com_google_firebase_messaging_zzb) {
        Object newInstance;
        Throwable e;
        Object obj = null;
        try {
            Class cls = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            Bundle zza = zza(com_google_android_gms_internal_zzfim);
            newInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            try {
                cls.getField("mOrigin").set(newInstance, str);
                cls.getField("mCreationTimestamp").set(newInstance, Long.valueOf(com_google_android_gms_internal_zzfim.zzplf));
                cls.getField("mName").set(newInstance, com_google_android_gms_internal_zzfim.zzpld);
                cls.getField("mValue").set(newInstance, com_google_android_gms_internal_zzfim.zzple);
                if (!TextUtils.isEmpty(com_google_android_gms_internal_zzfim.zzplg)) {
                    obj = com_google_android_gms_internal_zzfim.zzplg;
                }
                cls.getField("mTriggerEventName").set(newInstance, obj);
                cls.getField("mTimedOutEventName").set(newInstance, !TextUtils.isEmpty(com_google_android_gms_internal_zzfim.zzpll) ? com_google_android_gms_internal_zzfim.zzpll : com_google_firebase_messaging_zzb.zzbpd());
                cls.getField("mTimedOutEventParams").set(newInstance, zza);
                cls.getField("mTriggerTimeout").set(newInstance, Long.valueOf(com_google_android_gms_internal_zzfim.zzplh));
                cls.getField("mTriggeredEventName").set(newInstance, !TextUtils.isEmpty(com_google_android_gms_internal_zzfim.zzplj) ? com_google_android_gms_internal_zzfim.zzplj : com_google_firebase_messaging_zzb.zzbpc());
                cls.getField("mTriggeredEventParams").set(newInstance, zza);
                cls.getField("mTimeToLive").set(newInstance, Long.valueOf(com_google_android_gms_internal_zzfim.zzgew));
                cls.getField("mExpiredEventName").set(newInstance, !TextUtils.isEmpty(com_google_android_gms_internal_zzfim.zzplm) ? com_google_android_gms_internal_zzfim.zzplm : com_google_firebase_messaging_zzb.zzbpe());
                cls.getField("mExpiredEventParams").set(newInstance, zza);
            } catch (Exception e2) {
                e = e2;
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                return newInstance;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            newInstance = null;
            e = th;
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return newInstance;
        }
        return newInstance;
    }

    private static String zza(@Nullable zzfim com_google_android_gms_internal_zzfim, @NonNull zzb com_google_firebase_messaging_zzb) {
        return (com_google_android_gms_internal_zzfim == null || TextUtils.isEmpty(com_google_android_gms_internal_zzfim.zzplk)) ? com_google_firebase_messaging_zzb.zzbpf() : com_google_android_gms_internal_zzfim.zzplk;
    }

    private static List<Object> zza(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        List<Object> list;
        ArrayList arrayList = new ArrayList();
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getConditionalUserProperties", new Class[]{String.class, String.class});
            declaredMethod.setAccessible(true);
            list = (List) declaredMethod.invoke(appMeasurement, new Object[]{str, ""});
        } catch (Throwable e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            Object obj = arrayList;
        }
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(str).length() + 55).append("Number of currently set _Es for origin: ").append(str).append(" is ").append(list.size()).toString());
        }
        return list;
    }

    private static void zza(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str5 = "FirebaseAbtUtil";
            String str6 = "_CE(experimentId) called by ";
            String valueOf = String.valueOf(str);
            Log.v(str5, valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6));
        }
        if (zzeq(context)) {
            AppMeasurement zzcz = zzcz(context);
            try {
                Method declaredMethod = AppMeasurement.class.getDeclaredMethod("clearConditionalUserProperty", new Class[]{String.class, String.class, Bundle.class});
                declaredMethod.setAccessible(true);
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 17) + String.valueOf(str3).length()).append("Clearing _E: [").append(str2).append(", ").append(str3).append("]").toString());
                }
                declaredMethod.invoke(zzcz, new Object[]{str2, str4, zzaz(str2, str3)});
            } catch (Throwable e) {
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            }
        }
    }

    public static void zza(@NonNull Context context, @NonNull String str, @NonNull byte[] bArr, @NonNull zzb com_google_firebase_messaging_zzb, int i) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str2 = "FirebaseAbtUtil";
            String str3 = "_SE called by ";
            String valueOf = String.valueOf(str);
            Log.v(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        if (zzeq(context)) {
            AppMeasurement zzcz = zzcz(context);
            zzfim zzal = zzal(bArr);
            if (zzal != null) {
                try {
                    Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                    Object obj = null;
                    for (Object next : zza(zzcz, str)) {
                        Object next2;
                        String zzaz = zzaz(next2);
                        String zzba = zzba(next2);
                        long longValue = ((Long) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mCreationTimestamp").get(next2)).longValue();
                        if (zzal.zzpld.equals(zzaz) && zzal.zzple.equals(zzba)) {
                            if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzaz).length() + 23) + String.valueOf(zzba).length()).append("_E is already set. [").append(zzaz).append(", ").append(zzba).append("]").toString());
                            }
                            obj = 1;
                        } else {
                            next2 = null;
                            zzfil[] com_google_android_gms_internal_zzfilArr = zzal.zzplo;
                            int length = com_google_android_gms_internal_zzfilArr.length;
                            int i2 = 0;
                            while (i2 < length) {
                                if (com_google_android_gms_internal_zzfilArr[i2].zzpld.equals(zzaz)) {
                                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                        Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzaz).length() + 33) + String.valueOf(zzba).length()).append("_E is found in the _OE list. [").append(zzaz).append(", ").append(zzba).append("]").toString());
                                    }
                                    next2 = 1;
                                    if (next2 != null) {
                                        continue;
                                    } else if (zzal.zzplf > longValue) {
                                        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                            Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzaz).length() + 115) + String.valueOf(zzba).length()).append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [").append(zzaz).append(", ").append(zzba).append("]").toString());
                                        }
                                        zza(context, str, zzaz, zzba, zza(zzal, com_google_firebase_messaging_zzb));
                                    } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                        Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzaz).length() + 109) + String.valueOf(zzba).length()).append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [").append(zzaz).append(", ").append(zzba).append("]").toString());
                                    }
                                } else {
                                    i2++;
                                }
                            }
                            if (next2 != null) {
                                continue;
                            } else if (zzal.zzplf > longValue) {
                                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzaz).length() + 115) + String.valueOf(zzba).length()).append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [").append(zzaz).append(", ").append(zzba).append("]").toString());
                                }
                                zza(context, str, zzaz, zzba, zza(zzal, com_google_firebase_messaging_zzb));
                            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                                Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzaz).length() + 109) + String.valueOf(zzba).length()).append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [").append(zzaz).append(", ").append(zzba).append("]").toString());
                            }
                        }
                    }
                    if (obj == null) {
                        zza(zzcz, context, str, zzal, com_google_firebase_messaging_zzb, 1);
                    } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        str2 = zzal.zzpld;
                        str3 = zzal.zzple;
                        Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 44) + String.valueOf(str3).length()).append("_E is already set. Not setting it again [").append(str2).append(", ").append(str3).append("]").toString());
                    }
                } catch (Throwable e) {
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                }
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                Log.v("FirebaseAbtUtil", "_SE failed; either _P was not set, or we couldn't deserialize the _P.");
            }
        }
    }

    private static void zza(@NonNull AppMeasurement appMeasurement, @NonNull Context context, @NonNull String str, @NonNull zzfim com_google_android_gms_internal_zzfim, @NonNull zzb com_google_firebase_messaging_zzb, int i) {
        if (Log.isLoggable("FirebaseAbtUtil", 2)) {
            String str2 = com_google_android_gms_internal_zzfim.zzpld;
            String str3 = com_google_android_gms_internal_zzfim.zzple;
            Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 7) + String.valueOf(str3).length()).append("_SEI: ").append(str2).append(Constants.SPACE).append(str3).toString());
        }
        try {
            String zzaz;
            Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            List zza = zza(appMeasurement, str);
            if (zza(appMeasurement, str).size() >= zzb(appMeasurement, str)) {
                if ((com_google_android_gms_internal_zzfim.zzpln != 0 ? com_google_android_gms_internal_zzfim.zzpln : 1) == 1) {
                    Object obj = zza.get(0);
                    zzaz = zzaz(obj);
                    String zzba = zzba(obj);
                    if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                        Log.v("FirebaseAbtUtil", new StringBuilder(String.valueOf(zzaz).length() + 38).append("Clearing _E due to overflow policy: [").append(zzaz).append("]").toString());
                    }
                    zza(context, str, zzaz, zzba, zza(com_google_android_gms_internal_zzfim, com_google_firebase_messaging_zzb));
                } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    zzaz = com_google_android_gms_internal_zzfim.zzpld;
                    str2 = com_google_android_gms_internal_zzfim.zzple;
                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzaz).length() + 44) + String.valueOf(str2).length()).append("_E won't be set due to overflow policy. [").append(zzaz).append(", ").append(str2).append("]").toString());
                    return;
                } else {
                    return;
                }
            }
            for (Object next : zza) {
                str2 = zzaz(next);
                zzaz = zzba(next);
                if (str2.equals(com_google_android_gms_internal_zzfim.zzpld) && !zzaz.equals(com_google_android_gms_internal_zzfim.zzple) && Log.isLoggable("FirebaseAbtUtil", 2)) {
                    Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(str2).length() + 77) + String.valueOf(zzaz).length()).append("Clearing _E, as only one _V of the same _E can be set atany given time: [").append(str2).append(", ").append(zzaz).append("].").toString());
                    zza(context, str, str2, zzaz, zza(com_google_android_gms_internal_zzfim, com_google_firebase_messaging_zzb));
                }
            }
            if (zza(com_google_android_gms_internal_zzfim, str, com_google_firebase_messaging_zzb) != null) {
                if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                    str2 = com_google_android_gms_internal_zzfim.zzpld;
                    str3 = com_google_android_gms_internal_zzfim.zzple;
                    String str4 = com_google_android_gms_internal_zzfim.zzplg;
                    Log.v("FirebaseAbtUtil", new StringBuilder(((String.valueOf(str2).length() + 27) + String.valueOf(str3).length()) + String.valueOf(str4).length()).append("Setting _CUP for _E: [").append(str2).append(", ").append(str3).append(", ").append(str4).append("]").toString());
                }
                try {
                    Method declaredMethod = AppMeasurement.class.getDeclaredMethod("setConditionalUserProperty", new Class[]{Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty")});
                    declaredMethod.setAccessible(true);
                    appMeasurement.logEventInternal(str, !TextUtils.isEmpty(com_google_android_gms_internal_zzfim.zzpli) ? com_google_android_gms_internal_zzfim.zzpli : com_google_firebase_messaging_zzb.zzbpb(), zza(com_google_android_gms_internal_zzfim));
                    declaredMethod.invoke(appMeasurement, new Object[]{next});
                } catch (Throwable e) {
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
                }
            } else if (Log.isLoggable("FirebaseAbtUtil", 2)) {
                zzaz = com_google_android_gms_internal_zzfim.zzpld;
                str2 = com_google_android_gms_internal_zzfim.zzple;
                Log.v("FirebaseAbtUtil", new StringBuilder((String.valueOf(zzaz).length() + 42) + String.valueOf(str2).length()).append("Could not create _CUP for: [").append(zzaz).append(", ").append(str2).append("]. Skipping.").toString());
            }
        } catch (Throwable e2) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e2);
        }
    }

    @Nullable
    private static zzfim zzal(@NonNull byte[] bArr) {
        try {
            return zzfim.zzbh(bArr);
        } catch (zzfhj e) {
            return null;
        }
    }

    private static Bundle zzaz(@NonNull String str, @NonNull String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        return bundle;
    }

    private static String zzaz(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mName").get(obj);
    }

    private static int zzb(@NonNull AppMeasurement appMeasurement, @NonNull String str) {
        try {
            Method declaredMethod = AppMeasurement.class.getDeclaredMethod("getMaxUserProperties", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(appMeasurement, new Object[]{str})).intValue();
        } catch (Throwable e) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", e);
            return 20;
        }
    }

    private static String zzba(@NonNull Object obj) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String) Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mValue").get(obj);
    }

    @Nullable
    private static AppMeasurement zzcz(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }

    private static boolean zzeq(Context context) {
        if (zzcz(context) != null) {
            try {
                Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                return true;
            } catch (ClassNotFoundException e) {
                if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
                    return false;
                }
                Log.v("FirebaseAbtUtil", "Firebase Analytics library is missing support for abt. Please update to a more recent version.");
                return false;
            }
        } else if (!Log.isLoggable("FirebaseAbtUtil", 2)) {
            return false;
        } else {
            Log.v("FirebaseAbtUtil", "Firebase Analytics not available");
            return false;
        }
    }
}
