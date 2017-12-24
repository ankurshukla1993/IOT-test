package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzdld {
    private static Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static Uri zzljr = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    private static Pattern zzljs = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    private static Pattern zzljt = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean zzlju = new AtomicBoolean();
    private static HashMap<String, String> zzljv;
    private static HashMap<String, Boolean> zzljw = new HashMap();
    private static HashMap<String, Integer> zzljx = new HashMap();
    private static HashMap<String, Long> zzljy = new HashMap();
    private static HashMap<String, Float> zzljz = new HashMap();
    private static Object zzlka;
    private static boolean zzlkb;
    private static String[] zzlkc = new String[0];

    public static long getLong(ContentResolver contentResolver, String str, long j) {
        Object zzb = zzb(contentResolver);
        Long l = (Long) zza(zzljy, str, Long.valueOf(0));
        if (l != null) {
            return l.longValue();
        }
        Object obj;
        long j2;
        String zza = zza(contentResolver, str, null);
        if (zza == null) {
            obj = l;
            j2 = 0;
        } else {
            Long valueOf;
            try {
                long parseLong = Long.parseLong(zza);
                valueOf = Long.valueOf(parseLong);
                j2 = parseLong;
            } catch (NumberFormatException e) {
                valueOf = l;
                j2 = 0;
            }
        }
        zza(zzb, zzljy, str, obj);
        return j2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T zza(java.util.HashMap<java.lang.String, T> r2, java.lang.String r3, T r4) {
        /*
        r1 = com.google.android.gms.internal.zzdld.class;
        monitor-enter(r1);
        r0 = r2.containsKey(r3);	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0013;
    L_0x0009:
        r0 = r2.get(r3);	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = r4;
        goto L_0x000f;
    L_0x0013:
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        r0 = 0;
        goto L_0x0010;
    L_0x0016:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdld.zza(java.util.HashMap, java.lang.String, java.lang.Object):T");
    }

    public static String zza(ContentResolver contentResolver, String str, String str2) {
        String str3 = null;
        synchronized (zzdld.class) {
            zza(contentResolver);
            Object obj = zzlka;
            String str4;
            if (zzljv.containsKey(str)) {
                str4 = (String) zzljv.get(str);
                if (str4 != null) {
                    str3 = str4;
                }
            } else {
                String[] strArr = zzlkc;
                int length = strArr.length;
                int i = 0;
                while (i < length) {
                    if (str.startsWith(strArr[i])) {
                        if (!zzlkb || zzljv.isEmpty()) {
                            zzljv.putAll(zza(contentResolver, zzlkc));
                            zzlkb = true;
                            if (zzljv.containsKey(str)) {
                                str4 = (String) zzljv.get(str);
                                if (str4 != null) {
                                    str3 = str4;
                                }
                            }
                        }
                    } else {
                        i++;
                    }
                }
                Cursor query = contentResolver.query(CONTENT_URI, null, null, new String[]{str}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            str4 = query.getString(1);
                            if (str4 != null && str4.equals(null)) {
                                str4 = null;
                            }
                            zza(obj, str, str4);
                            if (str4 != null) {
                                str3 = str4;
                            }
                            if (query != null) {
                                query.close();
                            }
                        }
                    } catch (Throwable th) {
                        if (query != null) {
                            query.close();
                        }
                    }
                }
                zza(obj, str, null);
                if (query != null) {
                    query.close();
                }
            }
        }
        return str3;
    }

    private static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzljr, null, null, strArr, null);
        Map<String, String> treeMap = new TreeMap();
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    treeMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return treeMap;
    }

    private static void zza(ContentResolver contentResolver) {
        if (zzljv == null) {
            zzlju.set(false);
            zzljv = new HashMap();
            zzlka = new Object();
            zzlkb = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new zzdle(null));
        } else if (zzlju.getAndSet(false)) {
            zzljv.clear();
            zzljw.clear();
            zzljx.clear();
            zzljy.clear();
            zzljz.clear();
            zzlka = new Object();
            zzlkb = false;
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzdld.class) {
            if (obj == zzlka) {
                zzljv.put(str, str2);
            }
        }
    }

    private static <T> void zza(Object obj, HashMap<String, T> hashMap, String str, T t) {
        synchronized (zzdld.class) {
            if (obj == zzlka) {
                hashMap.put(str, t);
                zzljv.remove(str);
            }
        }
    }

    public static boolean zza(ContentResolver contentResolver, String str, boolean z) {
        Object zzb = zzb(contentResolver);
        Boolean bool = (Boolean) zza(zzljw, str, Boolean.valueOf(false));
        if (bool != null) {
            return bool.booleanValue();
        }
        Object obj;
        boolean z2;
        Object zza = zza(contentResolver, str, null);
        if (zza == null || zza.equals("")) {
            obj = bool;
            z2 = false;
        } else if (zzljs.matcher(zza).matches()) {
            r1 = Boolean.valueOf(true);
            z2 = true;
        } else if (zzljt.matcher(zza).matches()) {
            r1 = Boolean.valueOf(false);
            z2 = false;
        } else {
            Log.w("Gservices", "attempt to read gservices key " + str + " (value \"" + zza + "\") as boolean");
            r1 = bool;
            z2 = false;
        }
        zza(zzb, zzljw, str, obj);
        return z2;
    }

    private static Object zzb(ContentResolver contentResolver) {
        Object obj;
        synchronized (zzdld.class) {
            zza(contentResolver);
            obj = zzlka;
        }
        return obj;
    }
}
