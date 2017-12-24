package com.google.android.gms.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.measurement.AppMeasurement$Param;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zzcfl extends zzcii {
    private static final String[] zzivu = new String[]{"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    private static final String[] zzivv = new String[]{Param.ORIGIN, "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzivw = new String[]{"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;"};
    private static final String[] zzivx = new String[]{"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzivy = new String[]{"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;"};
    private static final String[] zzivz = new String[]{"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzcfo zziwa = new zzcfo(this, getContext(), "google_app_measurement.db");
    private final zzckh zziwb = new zzckh(zzwh());

    zzcfl(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzawm().zzayr().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @WorkerThread
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzawm().zzayr().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzawm().zzayr().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzawm().zzayr().zzj("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        zzbq.zzgh(str);
        zzbq.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    static void zza(zzcgj com_google_android_gms_internal_zzcgj, SQLiteDatabase sQLiteDatabase) {
        if (com_google_android_gms_internal_zzcgj == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            com_google_android_gms_internal_zzcgj.zzayt().log("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            com_google_android_gms_internal_zzcgj.zzayt().log("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            com_google_android_gms_internal_zzcgj.zzayt().log("Failed to turn on database read permission for owner");
        }
        if (!file.setWritable(true, true)) {
            com_google_android_gms_internal_zzcgj.zzayt().log("Failed to turn on database write permission for owner");
        }
    }

    @WorkerThread
    static void zza(zzcgj com_google_android_gms_internal_zzcgj, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (com_google_android_gms_internal_zzcgj == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(com_google_android_gms_internal_zzcgj, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        try {
            zza(com_google_android_gms_internal_zzcgj, sQLiteDatabase, str, str3, strArr);
        } catch (SQLiteException e) {
            com_google_android_gms_internal_zzcgj.zzayr().zzj("Failed to verify columns on table that was just created", str);
            throw e;
        }
    }

    @WorkerThread
    private static void zza(zzcgj com_google_android_gms_internal_zzcgj, SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) throws SQLiteException {
        int i = 0;
        if (com_google_android_gms_internal_zzcgj == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Iterable zzb = zzb(sQLiteDatabase, str);
        String[] split = str2.split(",");
        int length = split.length;
        int i2 = 0;
        while (i2 < length) {
            String str3 = split[i2];
            if (zzb.remove(str3)) {
                i2++;
            } else {
                throw new SQLiteException(new StringBuilder((String.valueOf(str).length() + 35) + String.valueOf(str3).length()).append("Table ").append(str).append(" is missing required column: ").append(str3).toString());
            }
        }
        if (strArr != null) {
            while (i < strArr.length) {
                if (!zzb.remove(strArr[i])) {
                    sQLiteDatabase.execSQL(strArr[i + 1]);
                }
                i += 2;
            }
        }
        if (!zzb.isEmpty()) {
            com_google_android_gms_internal_zzcgj.zzayt().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb));
        }
    }

    @WorkerThread
    private static boolean zza(zzcgj com_google_android_gms_internal_zzcgj, SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query;
        Object e;
        Throwable th;
        Cursor cursor = null;
        if (com_google_android_gms_internal_zzcgj == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            try {
                boolean moveToFirst = query.moveToFirst();
                if (query == null) {
                    return moveToFirst;
                }
                query.close();
                return moveToFirst;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    com_google_android_gms_internal_zzcgj.zzayt().zze("Error querying for table", str, e);
                    if (query != null) {
                        query.close();
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            com_google_android_gms_internal_zzcgj.zzayt().zze("Error querying for table", str, e);
            if (query != null) {
                query.close();
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzckp com_google_android_gms_internal_zzckp) {
        zzwu();
        zzut();
        zzbq.zzgh(str);
        zzbq.checkNotNull(com_google_android_gms_internal_zzckp);
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzckp.zzjhc)) {
            zzawm().zzayt().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzcgj.zzje(str), Integer.valueOf(i), String.valueOf(com_google_android_gms_internal_zzckp.zzjhb));
            return false;
        }
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzckp.zzhl()];
            zzfhc zzo = zzfhc.zzo(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzckp.zza(zzo);
            zzo.zzcus();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", com_google_android_gms_internal_zzckp.zzjhb);
            contentValues.put("event_name", com_google_android_gms_internal_zzckp.zzjhc);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzawm().zzayr().zzj("Failed to insert event filter (got -1). appId", zzcgj.zzje(str));
                }
                return true;
            } catch (SQLiteException e) {
                zzawm().zzayr().zze("Error storing event filter. appId", zzcgj.zzje(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzawm().zzayr().zze("Configuration loss. Failed to serialize event filter. appId", zzcgj.zzje(str), e2);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzcks com_google_android_gms_internal_zzcks) {
        zzwu();
        zzut();
        zzbq.zzgh(str);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcks);
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzcks.zzjhr)) {
            zzawm().zzayt().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzcgj.zzje(str), Integer.valueOf(i), String.valueOf(com_google_android_gms_internal_zzcks.zzjhb));
            return false;
        }
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzcks.zzhl()];
            zzfhc zzo = zzfhc.zzo(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzcks.zza(zzo);
            zzo.zzcus();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", com_google_android_gms_internal_zzcks.zzjhb);
            contentValues.put("property_name", com_google_android_gms_internal_zzcks.zzjhr);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzawm().zzayr().zzj("Failed to insert property filter (got -1). appId", zzcgj.zzje(str));
                return false;
            } catch (SQLiteException e) {
                zzawm().zzayr().zze("Error storing property filter. appId", zzcgj.zzje(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzawm().zzayr().zze("Configuration loss. Failed to serialize property filter. appId", zzcgj.zzje(str), e2);
            return false;
        }
    }

    private final boolean zzayb() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }

    @WorkerThread
    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzawm().zzayr().zze("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @WorkerThread
    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        Set<String> hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder(String.valueOf(str).length() + 22).append("SELECT * FROM ").append(str).append(" LIMIT 0").toString(), null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    private final boolean zzc(String str, List<Integer> list) {
        zzbq.zzgh(str);
        zzwu();
        zzut();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            if (zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str}) <= ((long) Math.max(0, Math.min(CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE, zzawo().zzb(str, zzcfz.zziyn))))) {
                return false;
            }
            Iterable arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = (Integer) list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            join = new StringBuilder(String.valueOf(join).length() + 2).append("(").append(join).append(")").toString();
            return writableDatabase.delete("audience_filter_values", new StringBuilder(String.valueOf(join).length() + 140).append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ").append(join).append(" order by rowid desc limit -1 offset ?)").toString(), new String[]{str, Integer.toString(r5)}) > 0;
        } catch (SQLiteException e) {
            zzawm().zzayr().zze("Database error querying filters. appId", zzcgj.zzje(str), e);
            return false;
        }
    }

    @WorkerThread
    public final void beginTransaction() {
        zzwu();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public final void endTransaction() {
        zzwu();
        getWritableDatabase().endTransaction();
    }

    @WorkerThread
    final SQLiteDatabase getWritableDatabase() {
        zzut();
        try {
            return this.zziwa.getWritableDatabase();
        } catch (SQLiteException e) {
            zzawm().zzayt().zzj("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public final void setTransactionSuccessful() {
        zzwu();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(zzclb com_google_android_gms_internal_zzclb) throws IOException {
        zzut();
        zzwu();
        zzbq.checkNotNull(com_google_android_gms_internal_zzclb);
        zzbq.zzgh(com_google_android_gms_internal_zzclb.zzch);
        try {
            long j;
            Object obj = new byte[com_google_android_gms_internal_zzclb.zzhl()];
            zzfhc zzo = zzfhc.zzo(obj, 0, obj.length);
            com_google_android_gms_internal_zzclb.zza(zzo);
            zzo.zzcus();
            zzcih zzawi = zzawi();
            zzbq.checkNotNull(obj);
            zzawi.zzut();
            MessageDigest zzed = zzckn.zzed(CommonUtils.MD5_INSTANCE);
            if (zzed == null) {
                zzawi.zzawm().zzayr().log("Failed to get MD5");
                j = 0;
            } else {
                j = zzckn.zzr(zzed.digest(obj));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", com_google_android_gms_internal_zzclb.zzch);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("metadata", obj);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return j;
            } catch (SQLiteException e) {
                zzawm().zzayr().zze("Error storing raw event metadata. appId", zzcgj.zzje(com_google_android_gms_internal_zzclb.zzch), e);
                throw e;
            }
        } catch (IOException e2) {
            zzawm().zzayr().zze("Data loss. Failed to serialize event metadata. appId", zzcgj.zzje(com_google_android_gms_internal_zzclb.zzch), e2);
            throw e2;
        }
    }

    @WorkerThread
    public final zzcfm zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Cursor query;
        Object e;
        Throwable th;
        zzbq.zzgh(str);
        zzut();
        zzwu();
        String[] strArr = new String[]{str};
        zzcfm com_google_android_gms_internal_zzcfm = new zzcfm();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            query = writableDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    if (query.getLong(0) == j) {
                        com_google_android_gms_internal_zzcfm.zziwd = query.getLong(1);
                        com_google_android_gms_internal_zzcfm.zziwc = query.getLong(2);
                        com_google_android_gms_internal_zzcfm.zziwe = query.getLong(3);
                        com_google_android_gms_internal_zzcfm.zziwf = query.getLong(4);
                        com_google_android_gms_internal_zzcfm.zziwg = query.getLong(5);
                    }
                    if (z) {
                        com_google_android_gms_internal_zzcfm.zziwd++;
                    }
                    if (z2) {
                        com_google_android_gms_internal_zzcfm.zziwc++;
                    }
                    if (z3) {
                        com_google_android_gms_internal_zzcfm.zziwe++;
                    }
                    if (z4) {
                        com_google_android_gms_internal_zzcfm.zziwf++;
                    }
                    if (z5) {
                        com_google_android_gms_internal_zzcfm.zziwg++;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("day", Long.valueOf(j));
                    contentValues.put("daily_public_events_count", Long.valueOf(com_google_android_gms_internal_zzcfm.zziwc));
                    contentValues.put("daily_events_count", Long.valueOf(com_google_android_gms_internal_zzcfm.zziwd));
                    contentValues.put("daily_conversions_count", Long.valueOf(com_google_android_gms_internal_zzcfm.zziwe));
                    contentValues.put("daily_error_events_count", Long.valueOf(com_google_android_gms_internal_zzcfm.zziwf));
                    contentValues.put("daily_realtime_events_count", Long.valueOf(com_google_android_gms_internal_zzcfm.zziwg));
                    writableDatabase.update("apps", contentValues, "app_id=?", strArr);
                    if (query != null) {
                        query.close();
                    }
                    return com_google_android_gms_internal_zzcfm;
                }
                zzawm().zzayt().zzj("Not updating daily counts, app is not known. appId", zzcgj.zzje(str));
                if (query != null) {
                    query.close();
                }
                return com_google_android_gms_internal_zzcfm;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzawm().zzayr().zze("Error updating daily counts. appId", zzcgj.zzje(str), e);
                    if (query != null) {
                        query.close();
                    }
                    return com_google_android_gms_internal_zzcfm;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            zzawm().zzayr().zze("Error updating daily counts. appId", zzcgj.zzje(str), e);
            if (query != null) {
                query.close();
            }
            return com_google_android_gms_internal_zzcfm;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final void zza(zzcfe com_google_android_gms_internal_zzcfe) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfe);
        zzut();
        zzwu();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", com_google_android_gms_internal_zzcfe.getAppId());
        contentValues.put("app_instance_id", com_google_android_gms_internal_zzcfe.getAppInstanceId());
        contentValues.put("gmp_app_id", com_google_android_gms_internal_zzcfe.getGmpAppId());
        contentValues.put("resettable_device_id_hash", com_google_android_gms_internal_zzcfe.zzawq());
        contentValues.put("last_bundle_index", Long.valueOf(com_google_android_gms_internal_zzcfe.zzawz()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaws()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(com_google_android_gms_internal_zzcfe.zzawt()));
        contentValues.put("app_version", com_google_android_gms_internal_zzcfe.zzuy());
        contentValues.put("app_store", com_google_android_gms_internal_zzcfe.zzawv());
        contentValues.put("gmp_version", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaww()));
        contentValues.put("dev_cert_hash", Long.valueOf(com_google_android_gms_internal_zzcfe.zzawx()));
        contentValues.put("measurement_enabled", Boolean.valueOf(com_google_android_gms_internal_zzcfe.zzawy()));
        contentValues.put("day", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaxd()));
        contentValues.put("daily_public_events_count", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaxe()));
        contentValues.put("daily_events_count", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaxf()));
        contentValues.put("daily_conversions_count", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaxg()));
        contentValues.put("config_fetched_time", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaxa()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaxb()));
        contentValues.put("app_version_int", Long.valueOf(com_google_android_gms_internal_zzcfe.zzawu()));
        contentValues.put("firebase_instance_id", com_google_android_gms_internal_zzcfe.zzawr());
        contentValues.put("daily_error_events_count", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaxi()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaxh()));
        contentValues.put("health_monitor_sample", com_google_android_gms_internal_zzcfe.zzaxj());
        contentValues.put("android_id", Long.valueOf(com_google_android_gms_internal_zzcfe.zzaxl()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(com_google_android_gms_internal_zzcfe.zzaxm()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{com_google_android_gms_internal_zzcfe.getAppId()})) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzawm().zzayr().zzj("Failed to insert/update app (got -1). appId", zzcgj.zzje(com_google_android_gms_internal_zzcfe.getAppId()));
            }
        } catch (SQLiteException e) {
            zzawm().zzayr().zze("Error storing app. appId", zzcgj.zzje(com_google_android_gms_internal_zzcfe.getAppId()), e);
        }
    }

    @WorkerThread
    public final void zza(zzcft com_google_android_gms_internal_zzcft) {
        Long l = null;
        zzbq.checkNotNull(com_google_android_gms_internal_zzcft);
        zzut();
        zzwu();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", com_google_android_gms_internal_zzcft.mAppId);
        contentValues.put("name", com_google_android_gms_internal_zzcft.mName);
        contentValues.put("lifetime_count", Long.valueOf(com_google_android_gms_internal_zzcft.zziwp));
        contentValues.put("current_bundle_count", Long.valueOf(com_google_android_gms_internal_zzcft.zziwq));
        contentValues.put("last_fire_timestamp", Long.valueOf(com_google_android_gms_internal_zzcft.zziwr));
        contentValues.put("last_bundled_timestamp", Long.valueOf(com_google_android_gms_internal_zzcft.zziws));
        contentValues.put("last_sampled_complex_event_id", com_google_android_gms_internal_zzcft.zziwt);
        contentValues.put("last_sampling_rate", com_google_android_gms_internal_zzcft.zziwu);
        if (com_google_android_gms_internal_zzcft.zziwv != null && com_google_android_gms_internal_zzcft.zziwv.booleanValue()) {
            l = Long.valueOf(1);
        }
        contentValues.put("last_exempt_from_sampling", l);
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzawm().zzayr().zzj("Failed to insert/update event aggregates (got -1). appId", zzcgj.zzje(com_google_android_gms_internal_zzcft.mAppId));
            }
        } catch (SQLiteException e) {
            zzawm().zzayr().zze("Error storing event aggregates. appId", zzcgj.zzje(com_google_android_gms_internal_zzcft.mAppId), e);
        }
    }

    @WorkerThread
    final void zza(String str, zzcko[] com_google_android_gms_internal_zzckoArr) {
        int i = 0;
        zzwu();
        zzut();
        zzbq.zzgh(str);
        zzbq.checkNotNull(com_google_android_gms_internal_zzckoArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            int i2;
            zzwu();
            zzut();
            zzbq.zzgh(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzcko com_google_android_gms_internal_zzcko : com_google_android_gms_internal_zzckoArr) {
                zzwu();
                zzut();
                zzbq.zzgh(str);
                zzbq.checkNotNull(com_google_android_gms_internal_zzcko);
                zzbq.checkNotNull(com_google_android_gms_internal_zzcko.zzjgz);
                zzbq.checkNotNull(com_google_android_gms_internal_zzcko.zzjgy);
                if (com_google_android_gms_internal_zzcko.zzjgx == null) {
                    zzawm().zzayt().zzj("Audience with no ID. appId", zzcgj.zzje(str));
                } else {
                    int intValue = com_google_android_gms_internal_zzcko.zzjgx.intValue();
                    for (zzckp com_google_android_gms_internal_zzckp : com_google_android_gms_internal_zzcko.zzjgz) {
                        if (com_google_android_gms_internal_zzckp.zzjhb == null) {
                            zzawm().zzayt().zze("Event filter with no ID. Audience definition ignored. appId, audienceId", zzcgj.zzje(str), com_google_android_gms_internal_zzcko.zzjgx);
                            break;
                        }
                    }
                    for (zzcks com_google_android_gms_internal_zzcks : com_google_android_gms_internal_zzcko.zzjgy) {
                        if (com_google_android_gms_internal_zzcks.zzjhb == null) {
                            zzawm().zzayt().zze("Property filter with no ID. Audience definition ignored. appId, audienceId", zzcgj.zzje(str), com_google_android_gms_internal_zzcko.zzjgx);
                            break;
                        }
                    }
                    for (zzckp com_google_android_gms_internal_zzckp2 : com_google_android_gms_internal_zzcko.zzjgz) {
                        if (!zza(str, intValue, com_google_android_gms_internal_zzckp2)) {
                            i2 = 0;
                            break;
                        }
                    }
                    i2 = 1;
                    if (i2 != 0) {
                        for (zzcks com_google_android_gms_internal_zzcks2 : com_google_android_gms_internal_zzcko.zzjgy) {
                            if (!zza(str, intValue, com_google_android_gms_internal_zzcks2)) {
                                i2 = 0;
                                break;
                            }
                        }
                    }
                    if (i2 == 0) {
                        zzwu();
                        zzut();
                        zzbq.zzgh(str);
                        SQLiteDatabase writableDatabase3 = getWritableDatabase();
                        writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                        writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                    }
                }
            }
            List arrayList = new ArrayList();
            i2 = com_google_android_gms_internal_zzckoArr.length;
            while (i < i2) {
                arrayList.add(com_google_android_gms_internal_zzckoArr[i].zzjgx);
                i++;
            }
            zzc(str, arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    public final boolean zza(zzcfi com_google_android_gms_internal_zzcfi) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi);
        zzut();
        zzwu();
        if (zzag(com_google_android_gms_internal_zzcfi.packageName, com_google_android_gms_internal_zzcfi.zzivl.name) == null) {
            if (zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{com_google_android_gms_internal_zzcfi.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", com_google_android_gms_internal_zzcfi.packageName);
        contentValues.put(Param.ORIGIN, com_google_android_gms_internal_zzcfi.zzivk);
        contentValues.put("name", com_google_android_gms_internal_zzcfi.zzivl.name);
        zza(contentValues, "value", com_google_android_gms_internal_zzcfi.zzivl.getValue());
        contentValues.put(AppStateModule.APP_STATE_ACTIVE, Boolean.valueOf(com_google_android_gms_internal_zzcfi.zzivn));
        contentValues.put("trigger_event_name", com_google_android_gms_internal_zzcfi.zzivo);
        contentValues.put("trigger_timeout", Long.valueOf(com_google_android_gms_internal_zzcfi.zzivq));
        zzawi();
        contentValues.put("timed_out_event", zzckn.zza(com_google_android_gms_internal_zzcfi.zzivp));
        contentValues.put("creation_timestamp", Long.valueOf(com_google_android_gms_internal_zzcfi.zzivm));
        zzawi();
        contentValues.put("triggered_event", zzckn.zza(com_google_android_gms_internal_zzcfi.zzivr));
        contentValues.put("triggered_timestamp", Long.valueOf(com_google_android_gms_internal_zzcfi.zzivl.zzjgn));
        contentValues.put("time_to_live", Long.valueOf(com_google_android_gms_internal_zzcfi.zzivs));
        zzawi();
        contentValues.put("expired_event", zzckn.zza(com_google_android_gms_internal_zzcfi.zzivt));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzawm().zzayr().zzj("Failed to insert/update conditional user property (got -1)", zzcgj.zzje(com_google_android_gms_internal_zzcfi.packageName));
            }
        } catch (SQLiteException e) {
            zzawm().zzayr().zze("Error storing conditional user property", zzcgj.zzje(com_google_android_gms_internal_zzcfi.packageName), e);
        }
        return true;
    }

    public final boolean zza(zzcfs com_google_android_gms_internal_zzcfs, long j, boolean z) {
        zzut();
        zzwu();
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfs);
        zzbq.zzgh(com_google_android_gms_internal_zzcfs.mAppId);
        zzfhk com_google_android_gms_internal_zzcky = new zzcky();
        com_google_android_gms_internal_zzcky.zzjio = Long.valueOf(com_google_android_gms_internal_zzcfs.zziwn);
        com_google_android_gms_internal_zzcky.zzjim = new zzckz[com_google_android_gms_internal_zzcfs.zziwo.size()];
        Iterator it = com_google_android_gms_internal_zzcfs.zziwo.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            zzckz com_google_android_gms_internal_zzckz = new zzckz();
            int i2 = i + 1;
            com_google_android_gms_internal_zzcky.zzjim[i] = com_google_android_gms_internal_zzckz;
            com_google_android_gms_internal_zzckz.name = str;
            zzawi().zza(com_google_android_gms_internal_zzckz, com_google_android_gms_internal_zzcfs.zziwo.get(str));
            i = i2;
        }
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzcky.zzhl()];
            zzfhc zzo = zzfhc.zzo(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzcky.zza(zzo);
            zzo.zzcus();
            zzawm().zzayx().zze("Saving event, name, data size", zzawh().zzjb(com_google_android_gms_internal_zzcfs.mName), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", com_google_android_gms_internal_zzcfs.mAppId);
            contentValues.put("name", com_google_android_gms_internal_zzcfs.mName);
            contentValues.put(AppMeasurement$Param.TIMESTAMP, Long.valueOf(com_google_android_gms_internal_zzcfs.zzffr));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                zzawm().zzayr().zzj("Failed to insert raw event (got -1). appId", zzcgj.zzje(com_google_android_gms_internal_zzcfs.mAppId));
                return false;
            } catch (SQLiteException e) {
                zzawm().zzayr().zze("Error storing raw event. appId", zzcgj.zzje(com_google_android_gms_internal_zzcfs.mAppId), e);
                return false;
            }
        } catch (IOException e2) {
            zzawm().zzayr().zze("Data loss. Failed to serialize event params/data. appId", zzcgj.zzje(com_google_android_gms_internal_zzcfs.mAppId), e2);
            return false;
        }
    }

    @WorkerThread
    public final boolean zza(zzckm com_google_android_gms_internal_zzckm) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzckm);
        zzut();
        zzwu();
        if (zzag(com_google_android_gms_internal_zzckm.mAppId, com_google_android_gms_internal_zzckm.mName) == null) {
            if (zzckn.zzjt(com_google_android_gms_internal_zzckm.mName)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{com_google_android_gms_internal_zzckm.mAppId}) >= 25) {
                    return false;
                }
            }
            if (zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{com_google_android_gms_internal_zzckm.mAppId, com_google_android_gms_internal_zzckm.mOrigin}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", com_google_android_gms_internal_zzckm.mAppId);
        contentValues.put(Param.ORIGIN, com_google_android_gms_internal_zzckm.mOrigin);
        contentValues.put("name", com_google_android_gms_internal_zzckm.mName);
        contentValues.put("set_timestamp", Long.valueOf(com_google_android_gms_internal_zzckm.zzjgr));
        zza(contentValues, "value", com_google_android_gms_internal_zzckm.mValue);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzawm().zzayr().zzj("Failed to insert/update user property (got -1). appId", zzcgj.zzje(com_google_android_gms_internal_zzckm.mAppId));
            }
        } catch (SQLiteException e) {
            zzawm().zzayr().zze("Error storing user property. appId", zzcgj.zzje(com_google_android_gms_internal_zzckm.mAppId), e);
        }
        return true;
    }

    @WorkerThread
    public final boolean zza(zzclb com_google_android_gms_internal_zzclb, boolean z) {
        zzut();
        zzwu();
        zzbq.checkNotNull(com_google_android_gms_internal_zzclb);
        zzbq.zzgh(com_google_android_gms_internal_zzclb.zzch);
        zzbq.checkNotNull(com_google_android_gms_internal_zzclb.zzjiy);
        zzaxv();
        long currentTimeMillis = zzwh().currentTimeMillis();
        if (com_google_android_gms_internal_zzclb.zzjiy.longValue() < currentTimeMillis - zzcfk.zzaxp() || com_google_android_gms_internal_zzclb.zzjiy.longValue() > zzcfk.zzaxp() + currentTimeMillis) {
            zzawm().zzayt().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzcgj.zzje(com_google_android_gms_internal_zzclb.zzch), Long.valueOf(currentTimeMillis), com_google_android_gms_internal_zzclb.zzjiy);
        }
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzclb.zzhl()];
            zzfhc zzo = zzfhc.zzo(bArr, 0, bArr.length);
            com_google_android_gms_internal_zzclb.zza(zzo);
            zzo.zzcus();
            bArr = zzawi().zzp(bArr);
            zzawm().zzayx().zzj("Saving bundle, size", Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", com_google_android_gms_internal_zzclb.zzch);
            contentValues.put("bundle_end_timestamp", com_google_android_gms_internal_zzclb.zzjiy);
            contentValues.put("data", bArr);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzawm().zzayr().zzj("Failed to insert bundle (got -1). appId", zzcgj.zzje(com_google_android_gms_internal_zzclb.zzch));
                return false;
            } catch (SQLiteException e) {
                zzawm().zzayr().zze("Error storing bundle. appId", zzcgj.zzje(com_google_android_gms_internal_zzclb.zzch), e);
                return false;
            }
        } catch (IOException e2) {
            zzawm().zzayr().zze("Data loss. Failed to serialize bundle. appId", zzcgj.zzje(com_google_android_gms_internal_zzclb.zzch), e2);
            return false;
        }
    }

    @WorkerThread
    public final zzcft zzae(String str, String str2) {
        Object e;
        Cursor cursor;
        Throwable th;
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        zzut();
        zzwu();
        Cursor query;
        try {
            query = getWritableDatabase().query("events", new String[]{"lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    long j = query.getLong(0);
                    long j2 = query.getLong(1);
                    long j3 = query.getLong(2);
                    long j4 = query.isNull(3) ? 0 : query.getLong(3);
                    Long valueOf = query.isNull(4) ? null : Long.valueOf(query.getLong(4));
                    Long valueOf2 = query.isNull(5) ? null : Long.valueOf(query.getLong(5));
                    Boolean bool = null;
                    if (!query.isNull(6)) {
                        bool = Boolean.valueOf(query.getLong(6) == 1);
                    }
                    zzcft com_google_android_gms_internal_zzcft = new zzcft(str, str2, j, j2, j3, j4, valueOf, valueOf2, bool);
                    if (query.moveToNext()) {
                        zzawm().zzayr().zzj("Got multiple records for event aggregates, expected one. appId", zzcgj.zzje(str));
                    }
                    if (query == null) {
                        return com_google_android_gms_internal_zzcft;
                    }
                    query.close();
                    return com_google_android_gms_internal_zzcft;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
                try {
                    zzawm().zzayr().zzd("Error querying events. appId", zzcgj.zzje(str), zzawh().zzjb(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    query = cursor;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzawm().zzayr().zzd("Error querying events. appId", zzcgj.zzje(str), zzawh().zzjb(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final void zzaf(String str, String str2) {
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        zzut();
        zzwu();
        try {
            zzawm().zzayx().zzj("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzawm().zzayr().zzd("Error deleting user attribute. appId", zzcgj.zzje(str), zzawh().zzjd(str2), e);
        }
    }

    @WorkerThread
    public final zzckm zzag(String str, String str2) {
        Object e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        zzut();
        zzwu();
        try {
            Cursor query = getWritableDatabase().query("user_attributes", new String[]{"set_timestamp", "value", Param.ORIGIN}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    String str3 = str;
                    zzckm com_google_android_gms_internal_zzckm = new zzckm(str3, query.getString(2), str2, query.getLong(0), zza(query, 1));
                    if (query.moveToNext()) {
                        zzawm().zzayr().zzj("Got multiple records for user property, expected one. appId", zzcgj.zzje(str));
                    }
                    if (query == null) {
                        return com_google_android_gms_internal_zzckm;
                    }
                    query.close();
                    return com_google_android_gms_internal_zzckm;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
                try {
                    zzawm().zzayr().zzd("Error querying user property. appId", zzcgj.zzje(str), zzawh().zzjd(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzawm().zzayr().zzd("Error querying user property. appId", zzcgj.zzje(str), zzawh().zzjd(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final void zzag(List<Long> list) {
        zzbq.checkNotNull(list);
        zzut();
        zzwu();
        StringBuilder stringBuilder = new StringBuilder("rowid in (");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(((Long) list.get(i)).longValue());
        }
        stringBuilder.append(")");
        int delete = getWritableDatabase().delete("raw_events", stringBuilder.toString(), null);
        if (delete != list.size()) {
            zzawm().zzayr().zze("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
        }
    }

    @WorkerThread
    public final zzcfi zzah(String str, String str2) {
        Object e;
        Cursor cursor;
        Throwable th;
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        zzut();
        zzwu();
        Cursor query;
        try {
            query = getWritableDatabase().query("conditional_properties", new String[]{Param.ORIGIN, "value", AppStateModule.APP_STATE_ACTIVE, "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, "app_id=? and name=?", new String[]{str, str2}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    Object zza = zza(query, 1);
                    boolean z = query.getInt(2) != 0;
                    String string2 = query.getString(3);
                    long j = query.getLong(4);
                    zzcfx com_google_android_gms_internal_zzcfx = (zzcfx) zzawi().zzb(query.getBlob(5), zzcfx.CREATOR);
                    long j2 = query.getLong(6);
                    zzcfx com_google_android_gms_internal_zzcfx2 = (zzcfx) zzawi().zzb(query.getBlob(7), zzcfx.CREATOR);
                    long j3 = query.getLong(8);
                    zzcfi com_google_android_gms_internal_zzcfi = new zzcfi(str, string, new zzckk(str2, j3, zza, string), j2, z, string2, com_google_android_gms_internal_zzcfx, j, com_google_android_gms_internal_zzcfx2, query.getLong(9), (zzcfx) zzawi().zzb(query.getBlob(10), zzcfx.CREATOR));
                    if (query.moveToNext()) {
                        zzawm().zzayr().zze("Got multiple records for conditional property, expected one", zzcgj.zzje(str), zzawh().zzjd(str2));
                    }
                    if (query == null) {
                        return com_google_android_gms_internal_zzcfi;
                    }
                    query.close();
                    return com_google_android_gms_internal_zzcfi;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
                try {
                    zzawm().zzayr().zzd("Error querying conditional property", zzcgj.zzje(str), zzawh().zzjd(str2), e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    query = cursor;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            zzawm().zzayr().zzd("Error querying conditional property", zzcgj.zzje(str), zzawh().zzjd(str2), e);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final int zzai(String str, String str2) {
        int i = 0;
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        zzut();
        zzwu();
        try {
            i = getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzawm().zzayr().zzd("Error deleting conditional property", zzcgj.zzje(str), zzawh().zzjd(str2), e);
        }
        return i;
    }

    final Map<Integer, List<zzckp>> zzaj(String str, String str2) {
        Object e;
        Throwable th;
        zzwu();
        zzut();
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        Map<Integer, List<zzckp>> arrayMap = new ArrayMap();
        Cursor query;
        try {
            query = getWritableDatabase().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, null, null, null);
            if (query.moveToFirst()) {
                do {
                    try {
                        byte[] blob = query.getBlob(1);
                        zzfhb zzn = zzfhb.zzn(blob, 0, blob.length);
                        zzfhk com_google_android_gms_internal_zzckp = new zzckp();
                        try {
                            com_google_android_gms_internal_zzckp.zza(zzn);
                            int i = query.getInt(0);
                            List list = (List) arrayMap.get(Integer.valueOf(i));
                            if (list == null) {
                                list = new ArrayList();
                                arrayMap.put(Integer.valueOf(i), list);
                            }
                            list.add(com_google_android_gms_internal_zzckp);
                        } catch (IOException e2) {
                            zzawm().zzayr().zze("Failed to merge filter. appId", zzcgj.zzje(str), e2);
                        }
                    } catch (SQLiteException e3) {
                        e = e3;
                    }
                } while (query.moveToNext());
                if (query != null) {
                    query.close();
                }
                return arrayMap;
            }
            Map<Integer, List<zzckp>> emptyMap = Collections.emptyMap();
            if (query == null) {
                return emptyMap;
            }
            query.close();
            return emptyMap;
        } catch (SQLiteException e4) {
            e = e4;
            query = null;
            try {
                zzawm().zzayr().zze("Database error querying filters. appId", zzcgj.zzje(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    final Map<Integer, List<zzcks>> zzak(String str, String str2) {
        Object e;
        Throwable th;
        zzwu();
        zzut();
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        Map<Integer, List<zzcks>> arrayMap = new ArrayMap();
        Cursor query;
        try {
            query = getWritableDatabase().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, null, null, null);
            if (query.moveToFirst()) {
                do {
                    try {
                        byte[] blob = query.getBlob(1);
                        zzfhb zzn = zzfhb.zzn(blob, 0, blob.length);
                        zzfhk com_google_android_gms_internal_zzcks = new zzcks();
                        try {
                            com_google_android_gms_internal_zzcks.zza(zzn);
                            int i = query.getInt(0);
                            List list = (List) arrayMap.get(Integer.valueOf(i));
                            if (list == null) {
                                list = new ArrayList();
                                arrayMap.put(Integer.valueOf(i), list);
                            }
                            list.add(com_google_android_gms_internal_zzcks);
                        } catch (IOException e2) {
                            zzawm().zzayr().zze("Failed to merge filter", zzcgj.zzje(str), e2);
                        }
                    } catch (SQLiteException e3) {
                        e = e3;
                    }
                } while (query.moveToNext());
                if (query != null) {
                    query.close();
                }
                return arrayMap;
            }
            Map<Integer, List<zzcks>> emptyMap = Collections.emptyMap();
            if (query == null) {
                return emptyMap;
            }
            query.close();
            return emptyMap;
        } catch (SQLiteException e4) {
            e = e4;
            query = null;
            try {
                zzawm().zzayr().zze("Database error querying filters. appId", zzcgj.zzje(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    protected final long zzal(String str, String str2) {
        Object e;
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        zzut();
        zzwu();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        long zza;
        try {
            zza = zza(new StringBuilder(String.valueOf(str2).length() + 32).append("select ").append(str2).append(" from app2 where app_id=?").toString(), new String[]{str}, -1);
            if (zza == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", Integer.valueOf(0));
                contentValues.put("previous_install_count", Integer.valueOf(0));
                if (writableDatabase.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                    zzawm().zzayr().zze("Failed to insert column (got -1). appId", zzcgj.zzje(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                zza = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + zza));
                if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzawm().zzayr().zze("Failed to update column (got 0). appId", zzcgj.zzje(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return zza;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzawm().zzayr().zzd("Error inserting column. appId", zzcgj.zzje(str), str2, e);
                    return zza;
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            zza = 0;
            zzawm().zzayr().zzd("Error inserting column. appId", zzcgj.zzje(str), str2, e);
            return zza;
        }
    }

    protected final boolean zzaxn() {
        return false;
    }

    @WorkerThread
    public final String zzaxt() {
        Cursor rawQuery;
        Object e;
        Throwable th;
        String str = null;
        try {
            rawQuery = getWritableDatabase().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
            try {
                if (rawQuery.moveToFirst()) {
                    str = rawQuery.getString(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzawm().zzayr().zzj("Database error getting next bundle app id", e);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            rawQuery = null;
            zzawm().zzayr().zzj("Database error getting next bundle app id", e);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return str;
        } catch (Throwable th3) {
            rawQuery = null;
            th = th3;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return str;
    }

    public final boolean zzaxu() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    @WorkerThread
    final void zzaxv() {
        zzut();
        zzwu();
        if (zzayb()) {
            long j = zzawn().zzizz.get();
            long elapsedRealtime = zzwh().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > ((Long) zzcfz.zziyg.get()).longValue()) {
                zzawn().zzizz.set(elapsedRealtime);
                zzut();
                zzwu();
                if (zzayb()) {
                    int delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzwh().currentTimeMillis()), String.valueOf(zzcfk.zzaxp())});
                    if (delete > 0) {
                        zzawm().zzayx().zzj("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    @WorkerThread
    public final long zzaxw() {
        return zza("select max(bundle_end_timestamp) from queue", null, 0);
    }

    @WorkerThread
    public final long zzaxx() {
        return zza("select max(timestamp) from raw_events", null, 0);
    }

    public final boolean zzaxy() {
        return zzb("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzaxz() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    public final long zzaya() {
        long j = -1;
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException e) {
            zzawm().zzayr().zzj("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return j;
    }

    public final String zzaz(long j) {
        Cursor rawQuery;
        Object e;
        Throwable th;
        String str = null;
        zzut();
        zzwu();
        try {
            rawQuery = getWritableDatabase().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(j)});
            try {
                if (rawQuery.moveToFirst()) {
                    str = rawQuery.getString(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else {
                    zzawm().zzayx().log("No expired configs for apps with pending events");
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzawm().zzayr().zzj("Error selecting expired configs", e);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            rawQuery = str;
            zzawm().zzayr().zzj("Error selecting expired configs", e);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return str;
        } catch (Throwable th3) {
            rawQuery = str;
            th = th3;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        return str;
    }

    public final List<zzcfi> zzc(String str, String[] strArr) {
        Cursor query;
        Object e;
        Cursor cursor;
        Throwable th;
        zzut();
        zzwu();
        List<zzcfi> arrayList = new ArrayList();
        try {
            query = getWritableDatabase().query("conditional_properties", new String[]{"app_id", Param.ORIGIN, "name", "value", AppStateModule.APP_STATE_ACTIVE, "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, str, strArr, null, null, "rowid", "1001");
            try {
                if (query.moveToFirst()) {
                    do {
                        if (arrayList.size() >= 1000) {
                            zzawm().zzayr().zzj("Read more than the max allowed conditional properties, ignoring extra", Integer.valueOf(1000));
                            break;
                        }
                        String string = query.getString(0);
                        String string2 = query.getString(1);
                        String string3 = query.getString(2);
                        Object zza = zza(query, 3);
                        boolean z = query.getInt(4) != 0;
                        String string4 = query.getString(5);
                        long j = query.getLong(6);
                        zzcfx com_google_android_gms_internal_zzcfx = (zzcfx) zzawi().zzb(query.getBlob(7), zzcfx.CREATOR);
                        long j2 = query.getLong(8);
                        zzcfx com_google_android_gms_internal_zzcfx2 = (zzcfx) zzawi().zzb(query.getBlob(9), zzcfx.CREATOR);
                        long j3 = query.getLong(10);
                        List<zzcfi> list = arrayList;
                        list.add(new zzcfi(string, string2, new zzckk(string3, j3, zza, string2), j2, z, string4, com_google_android_gms_internal_zzcfx, j, com_google_android_gms_internal_zzcfx2, query.getLong(11), (zzcfx) zzawi().zzb(query.getBlob(12), zzcfx.CREATOR)));
                    } while (query.moveToNext());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            try {
                zzawm().zzayr().zzj("Error querying conditional user property value", e);
                List<zzcfi> emptyList = Collections.emptyList();
                if (cursor == null) {
                    return emptyList;
                }
                cursor.close();
                return emptyList;
            } catch (Throwable th3) {
                th = th3;
                query = cursor;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List<zzckm> zzg(String str, String str2, String str3) {
        Object e;
        Cursor cursor;
        Object obj;
        Throwable th;
        Cursor cursor2 = null;
        zzbq.zzgh(str);
        zzut();
        zzwu();
        List<zzckm> arrayList = new ArrayList();
        try {
            List arrayList2 = new ArrayList(3);
            arrayList2.add(str);
            StringBuilder stringBuilder = new StringBuilder("app_id=?");
            if (!TextUtils.isEmpty(str2)) {
                arrayList2.add(str2);
                stringBuilder.append(" and origin=?");
            }
            if (!TextUtils.isEmpty(str3)) {
                arrayList2.add(String.valueOf(str3).concat("*"));
                stringBuilder.append(" and name glob ?");
            }
            String[] strArr = new String[]{"name", "set_timestamp", "value", Param.ORIGIN};
            Cursor query = getWritableDatabase().query("user_attributes", strArr, stringBuilder.toString(), (String[]) arrayList2.toArray(new String[arrayList2.size()]), null, null, "rowid", "1001");
            try {
                if (query.moveToFirst()) {
                    while (arrayList.size() < 1000) {
                        String string = query.getString(0);
                        long j = query.getLong(1);
                        Object zza = zza(query, 2);
                        String string2 = query.getString(3);
                        if (zza == null) {
                            try {
                                zzawm().zzayr().zzd("(2)Read invalid user property value, ignoring it", zzcgj.zzje(str), string2, str3);
                            } catch (SQLiteException e2) {
                                e = e2;
                                cursor = query;
                                obj = string2;
                            } catch (Throwable th2) {
                                th = th2;
                                cursor2 = query;
                            }
                        } else {
                            arrayList.add(new zzckm(str, string2, string, j, zza));
                        }
                        if (!query.moveToNext()) {
                            break;
                        }
                        obj = string2;
                    }
                    zzawm().zzayr().zzj("Read more than the max allowed user properties, ignoring excess", Integer.valueOf(1000));
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e3) {
                e = e3;
                cursor = query;
            } catch (Throwable th22) {
                th = th22;
                cursor2 = query;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            try {
                zzawm().zzayr().zzd("(2)Error querying user properties", zzcgj.zzje(str), obj, e);
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List<zzcfi> zzh(String str, String str2, String str3) {
        zzbq.zzgh(str);
        zzut();
        zzwu();
        List arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder stringBuilder = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            stringBuilder.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            stringBuilder.append(" and name glob ?");
        }
        return zzc(stringBuilder.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    @WorkerThread
    public final List<zzckm> zziu(String str) {
        Object e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        zzbq.zzgh(str);
        zzut();
        zzwu();
        List<zzckm> arrayList = new ArrayList();
        try {
            Cursor query = getWritableDatabase().query("user_attributes", new String[]{"name", Param.ORIGIN, "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        String string2 = query.getString(1);
                        if (string2 == null) {
                            string2 = "";
                        }
                        long j = query.getLong(2);
                        Object zza = zza(query, 3);
                        if (zza == null) {
                            zzawm().zzayr().zzj("Read invalid user property value, ignoring it. appId", zzcgj.zzje(str));
                        } else {
                            arrayList.add(new zzckm(str, string2, string, j, zza));
                        }
                    } while (query.moveToNext());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
                cursor2 = query;
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor = null;
            try {
                zzawm().zzayr().zze("Error querying user properties. appId", zzcgj.zzje(str), e);
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final zzcfe zziv(String str) {
        Object e;
        Throwable th;
        zzbq.zzgh(str);
        zzut();
        zzwu();
        Cursor query;
        try {
            query = getWritableDatabase().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    zzcfe com_google_android_gms_internal_zzcfe = new zzcfe(this.zzitk, str);
                    com_google_android_gms_internal_zzcfe.zzil(query.getString(0));
                    com_google_android_gms_internal_zzcfe.zzim(query.getString(1));
                    com_google_android_gms_internal_zzcfe.zzin(query.getString(2));
                    com_google_android_gms_internal_zzcfe.zzap(query.getLong(3));
                    com_google_android_gms_internal_zzcfe.zzak(query.getLong(4));
                    com_google_android_gms_internal_zzcfe.zzal(query.getLong(5));
                    com_google_android_gms_internal_zzcfe.setAppVersion(query.getString(6));
                    com_google_android_gms_internal_zzcfe.zzip(query.getString(7));
                    com_google_android_gms_internal_zzcfe.zzan(query.getLong(8));
                    com_google_android_gms_internal_zzcfe.zzao(query.getLong(9));
                    boolean z = query.isNull(10) || query.getInt(10) != 0;
                    com_google_android_gms_internal_zzcfe.setMeasurementEnabled(z);
                    com_google_android_gms_internal_zzcfe.zzas(query.getLong(11));
                    com_google_android_gms_internal_zzcfe.zzat(query.getLong(12));
                    com_google_android_gms_internal_zzcfe.zzau(query.getLong(13));
                    com_google_android_gms_internal_zzcfe.zzav(query.getLong(14));
                    com_google_android_gms_internal_zzcfe.zzaq(query.getLong(15));
                    com_google_android_gms_internal_zzcfe.zzar(query.getLong(16));
                    com_google_android_gms_internal_zzcfe.zzam(query.isNull(17) ? -2147483648L : (long) query.getInt(17));
                    com_google_android_gms_internal_zzcfe.zzio(query.getString(18));
                    com_google_android_gms_internal_zzcfe.zzax(query.getLong(19));
                    com_google_android_gms_internal_zzcfe.zzaw(query.getLong(20));
                    com_google_android_gms_internal_zzcfe.zziq(query.getString(21));
                    com_google_android_gms_internal_zzcfe.zzay(query.isNull(22) ? 0 : query.getLong(22));
                    z = query.isNull(23) || query.getInt(23) != 0;
                    com_google_android_gms_internal_zzcfe.zzbk(z);
                    com_google_android_gms_internal_zzcfe.zzawp();
                    if (query.moveToNext()) {
                        zzawm().zzayr().zzj("Got multiple records for app, expected one. appId", zzcgj.zzje(str));
                    }
                    if (query == null) {
                        return com_google_android_gms_internal_zzcfe;
                    }
                    query.close();
                    return com_google_android_gms_internal_zzcfe;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzawm().zzayr().zze("Error querying app. appId", zzcgj.zzje(str), e);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            zzawm().zzayr().zze("Error querying app. appId", zzcgj.zzje(str), e);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final long zziw(String str) {
        zzbq.zzgh(str);
        zzut();
        zzwu();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String valueOf = String.valueOf(Math.max(0, Math.min(1000000, zzawo().zzb(str, zzcfz.zzixx))));
            return (long) writableDatabase.delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, valueOf});
        } catch (SQLiteException e) {
            zzawm().zzayr().zze("Error deleting over the limit events. appId", zzcgj.zzje(str), e);
            return 0;
        }
    }

    @WorkerThread
    public final byte[] zzix(String str) {
        Object e;
        Throwable th;
        zzbq.zzgh(str);
        zzut();
        zzwu();
        Cursor query;
        try {
            query = getWritableDatabase().query("apps", new String[]{"remote_config"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    byte[] blob = query.getBlob(0);
                    if (query.moveToNext()) {
                        zzawm().zzayr().zzj("Got multiple records for app config, expected one. appId", zzcgj.zzje(str));
                    }
                    if (query == null) {
                        return blob;
                    }
                    query.close();
                    return blob;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzawm().zzayr().zze("Error querying remote config. appId", zzcgj.zzje(str), e);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            zzawm().zzayr().zze("Error querying remote config. appId", zzcgj.zzje(str), e);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    final Map<Integer, zzclc> zziy(String str) {
        Cursor query;
        Object e;
        Throwable th;
        zzwu();
        zzut();
        zzbq.zzgh(str);
        try {
            query = getWritableDatabase().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, null, null, null);
            if (query.moveToFirst()) {
                Map<Integer, zzclc> arrayMap = new ArrayMap();
                do {
                    int i = query.getInt(0);
                    byte[] blob = query.getBlob(1);
                    zzfhb zzn = zzfhb.zzn(blob, 0, blob.length);
                    zzfhk com_google_android_gms_internal_zzclc = new zzclc();
                    try {
                        com_google_android_gms_internal_zzclc.zza(zzn);
                        try {
                            arrayMap.put(Integer.valueOf(i), com_google_android_gms_internal_zzclc);
                        } catch (SQLiteException e2) {
                            e = e2;
                        }
                    } catch (IOException e3) {
                        zzawm().zzayr().zzd("Failed to merge filter results. appId, audienceId, error", zzcgj.zzje(str), Integer.valueOf(i), e3);
                    }
                } while (query.moveToNext());
                if (query == null) {
                    return arrayMap;
                }
                query.close();
                return arrayMap;
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (SQLiteException e4) {
            e = e4;
            query = null;
            try {
                zzawm().zzayr().zze("Database error querying filter results. appId", zzcgj.zzje(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final long zziz(String str) {
        zzbq.zzgh(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    @WorkerThread
    public final List<Pair<zzclb, Long>> zzl(String str, int i, int i2) {
        List<Pair<zzclb, Long>> arrayList;
        Object e;
        Cursor cursor;
        Throwable th;
        boolean z = true;
        zzut();
        zzwu();
        zzbq.checkArgument(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        zzbq.zzgh(str);
        Cursor query;
        try {
            query = getWritableDatabase().query("queue", new String[]{"rowid", "data"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
            try {
                if (query.moveToFirst()) {
                    arrayList = new ArrayList();
                    int i3 = 0;
                    while (true) {
                        long j = query.getLong(0);
                        int length;
                        try {
                            byte[] zzq = zzawi().zzq(query.getBlob(1));
                            if (!arrayList.isEmpty() && zzq.length + i3 > i2) {
                                break;
                            }
                            zzfhb zzn = zzfhb.zzn(zzq, 0, zzq.length);
                            zzfhk com_google_android_gms_internal_zzclb = new zzclb();
                            try {
                                com_google_android_gms_internal_zzclb.zza(zzn);
                                length = zzq.length + i3;
                                arrayList.add(Pair.create(com_google_android_gms_internal_zzclb, Long.valueOf(j)));
                            } catch (IOException e2) {
                                zzawm().zzayr().zze("Failed to merge queued bundle. appId", zzcgj.zzje(str), e2);
                                length = i3;
                            }
                            if (!query.moveToNext() || length > i2) {
                                break;
                            }
                            i3 = length;
                        } catch (IOException e22) {
                            zzawm().zzayr().zze("Failed to unzip queued bundle. appId", zzcgj.zzje(str), e22);
                            length = i3;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } else {
                    arrayList = Collections.emptyList();
                    if (query != null) {
                        query.close();
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e4) {
            e = e4;
            cursor = null;
            try {
                zzawm().zzayr().zze("Error querying bundles. appId", zzcgj.zzje(str), e);
                arrayList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                query = cursor;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return arrayList;
    }
}
