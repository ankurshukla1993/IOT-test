package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zzcss {
    private static String[] zzhth = new String[]{"key", "value"};
    private static final ConcurrentHashMap<Uri, zzcss> zzjtm = new ConcurrentHashMap();
    private final Uri uri;
    private final ContentResolver zzjtn;
    private final ContentObserver zzjto;
    private final Object zzjtp = new Object();
    private volatile Map<String, String> zzjtq;

    private zzcss(ContentResolver contentResolver, Uri uri) {
        this.zzjtn = contentResolver;
        this.uri = uri;
        this.zzjto = new zzcsu(this, null);
    }

    public static zzcss zza(ContentResolver contentResolver, Uri uri) {
        zzcss com_google_android_gms_internal_zzcss = (zzcss) zzjtm.get(uri);
        if (com_google_android_gms_internal_zzcss != null) {
            return com_google_android_gms_internal_zzcss;
        }
        zzcss com_google_android_gms_internal_zzcss2 = new zzcss(contentResolver, uri);
        com_google_android_gms_internal_zzcss = (zzcss) zzjtm.putIfAbsent(uri, com_google_android_gms_internal_zzcss2);
        if (com_google_android_gms_internal_zzcss != null) {
            return com_google_android_gms_internal_zzcss;
        }
        com_google_android_gms_internal_zzcss2.zzjtn.registerContentObserver(com_google_android_gms_internal_zzcss2.uri, false, com_google_android_gms_internal_zzcss2.zzjto);
        return com_google_android_gms_internal_zzcss2;
    }

    private final Map<String, String> zzbcb() {
        Map<String, String> hashMap = new HashMap();
        Cursor query = this.zzjtn.query(this.uri, zzhth, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    hashMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return hashMap;
    }

    public final Map<String, String> zzbca() {
        Map<String, String> zzbcb = ((Boolean) zzctg.zza(new zzcst(this))).booleanValue() ? zzbcb() : this.zzjtq;
        if (zzbcb == null) {
            synchronized (this.zzjtp) {
                zzbcb = this.zzjtq;
                if (zzbcb == null) {
                    zzbcb = zzbcb();
                    this.zzjtq = zzbcb;
                }
            }
        }
        return zzbcb;
    }

    final /* synthetic */ Boolean zzbcc() {
        return Boolean.valueOf(zzdld.zza(this.zzjtn, "gms:phenotype:phenotype_flag:debug_disable_caching", false));
    }
}
