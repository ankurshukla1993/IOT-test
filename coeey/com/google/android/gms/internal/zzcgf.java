package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.zzd;

public final class zzcgf extends zzcii {
    private final zzcgg zziys = new zzcgg(this, getContext(), "google_app_measurement_local.db");
    private boolean zziyt;

    zzcgf(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    @WorkerThread
    private final SQLiteDatabase getWritableDatabase() {
        if (this.zziyt) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zziys.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zziyt = true;
        return null;
    }

    @WorkerThread
    @TargetApi(11)
    private final boolean zzb(int i, byte[] bArr) {
        zzut();
        if (this.zziyt) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("entry", bArr);
        int i2 = 0;
        int i3 = 5;
        while (i2 < 5) {
            SQLiteDatabase sQLiteDatabase = null;
            Cursor cursor = null;
            try {
                sQLiteDatabase = getWritableDatabase();
                if (sQLiteDatabase == null) {
                    this.zziyt = true;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return false;
                }
                sQLiteDatabase.beginTransaction();
                long j = 0;
                cursor = sQLiteDatabase.rawQuery("select count(1) from messages", null);
                if (cursor != null && cursor.moveToFirst()) {
                    j = cursor.getLong(0);
                }
                if (j >= 100000) {
                    zzawm().zzayr().log("Data loss, local db full");
                    j = (100000 - j) + 1;
                    long delete = (long) sQLiteDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(j)});
                    if (delete != j) {
                        zzawm().zzayr().zzd("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j), Long.valueOf(delete), Long.valueOf(j - delete));
                    }
                }
                sQLiteDatabase.insertOrThrow("messages", null, contentValues);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return true;
            } catch (SQLiteFullException e) {
                zzawm().zzayr().zzj("Error writing entry to local database", e);
                this.zziyt = true;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
            } catch (SQLiteException e2) {
                if (VERSION.SDK_INT < 11 || !(e2 instanceof SQLiteDatabaseLockedException)) {
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    zzawm().zzayr().zzj("Error writing entry to local database", e2);
                    this.zziyt = true;
                } else {
                    SystemClock.sleep((long) i3);
                    i3 += 20;
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                i2++;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            }
        }
        zzawm().zzayt().log("Failed to write entry to local database");
        return false;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzut();
        try {
            int delete = getWritableDatabase().delete("messages", null, null) + 0;
            if (delete > 0) {
                zzawm().zzayx().zzj("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzawm().zzayr().zzj("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zza(zzcfx com_google_android_gms_internal_zzcfx) {
        Parcel obtain = Parcel.obtain();
        com_google_android_gms_internal_zzcfx.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzb(0, marshall);
        }
        zzawm().zzayt().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzckk com_google_android_gms_internal_zzckk) {
        Parcel obtain = Parcel.obtain();
        com_google_android_gms_internal_zzckk.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzb(1, marshall);
        }
        zzawm().zzayt().log("User property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzavw() {
        super.zzavw();
    }

    public final /* bridge */ /* synthetic */ void zzavx() {
        super.zzavx();
    }

    public final /* bridge */ /* synthetic */ zzcfa zzavy() {
        return super.zzavy();
    }

    public final /* bridge */ /* synthetic */ zzcfh zzavz() {
        return super.zzavz();
    }

    public final /* bridge */ /* synthetic */ zzcik zzawa() {
        return super.zzawa();
    }

    public final /* bridge */ /* synthetic */ zzcge zzawb() {
        return super.zzawb();
    }

    public final /* bridge */ /* synthetic */ zzcfr zzawc() {
        return super.zzawc();
    }

    public final /* bridge */ /* synthetic */ zzcjd zzawd() {
        return super.zzawd();
    }

    public final /* bridge */ /* synthetic */ zzciz zzawe() {
        return super.zzawe();
    }

    public final /* bridge */ /* synthetic */ zzcgf zzawf() {
        return super.zzawf();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzawg() {
        return super.zzawg();
    }

    public final /* bridge */ /* synthetic */ zzcgh zzawh() {
        return super.zzawh();
    }

    public final /* bridge */ /* synthetic */ zzckn zzawi() {
        return super.zzawi();
    }

    public final /* bridge */ /* synthetic */ zzchd zzawj() {
        return super.zzawj();
    }

    public final /* bridge */ /* synthetic */ zzckc zzawk() {
        return super.zzawk();
    }

    public final /* bridge */ /* synthetic */ zzche zzawl() {
        return super.zzawl();
    }

    public final /* bridge */ /* synthetic */ zzcgj zzawm() {
        return super.zzawm();
    }

    public final /* bridge */ /* synthetic */ zzcgu zzawn() {
        return super.zzawn();
    }

    public final /* bridge */ /* synthetic */ zzcfk zzawo() {
        return super.zzawo();
    }

    protected final boolean zzaxn() {
        return false;
    }

    public final boolean zzc(zzcfi com_google_android_gms_internal_zzcfi) {
        zzawi();
        byte[] zza = zzckn.zza((Parcelable) com_google_android_gms_internal_zzcfi);
        if (zza.length <= 131072) {
            return zzb(2, zza);
        }
        zzawm().zzayt().log("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    @android.annotation.TargetApi(11)
    public final java.util.List<com.google.android.gms.internal.zzbej> zzeb(int r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:148:0x00f2
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.modifyBlocksTree(BlockProcessor.java:248)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r14 = this;
        r14.zzut();
        r0 = r14.zziyt;
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        r0 = 0;
    L_0x0008:
        return r0;
    L_0x0009:
        r10 = new java.util.ArrayList;
        r10.<init>();
        r0 = r14.getContext();
        r1 = "google_app_measurement_local.db";
        r0 = r0.getDatabasePath(r1);
        r0 = r0.exists();
        if (r0 != 0) goto L_0x0020;
    L_0x001e:
        r0 = r10;
        goto L_0x0008;
    L_0x0020:
        r9 = 5;
        r0 = 0;
        r12 = r0;
    L_0x0023:
        r0 = 5;
        if (r12 >= r0) goto L_0x01dc;
    L_0x0026:
        r1 = 0;
        r11 = 0;
        r0 = r14.getWritableDatabase();	 Catch:{ SQLiteFullException -> 0x020b, SQLiteException -> 0x0200, all -> 0x01ec }
        if (r0 != 0) goto L_0x0038;
    L_0x002e:
        r1 = 1;
        r14.zziyt = r1;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        if (r0 == 0) goto L_0x0036;
    L_0x0033:
        r0.close();
    L_0x0036:
        r0 = 0;
        goto L_0x0008;
    L_0x0038:
        r0.beginTransaction();	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r1 = "messages";	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r2 = 3;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r2 = new java.lang.String[r2];	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r3 = 0;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r4 = "rowid";	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r2[r3] = r4;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r3 = 1;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r4 = "type";	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r2[r3] = r4;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r3 = 2;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r4 = "entry";	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r2[r3] = r4;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r3 = 0;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r4 = 0;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r5 = 0;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r6 = 0;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r7 = "rowid asc";	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r8 = 100;	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r8 = java.lang.Integer.toString(r8);	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteFullException -> 0x0210, SQLiteException -> 0x0204, all -> 0x01f0 }
        r4 = -1;
    L_0x0061:
        r1 = r2.moveToNext();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        if (r1 == 0) goto L_0x0186;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x0067:
        r1 = 0;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r4 = r2.getLong(r1);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r1 = 1;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r1 = r2.getInt(r1);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r3 = 2;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r6 = r2.getBlob(r3);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        if (r1 != 0) goto L_0x0101;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x0078:
        r3 = android.os.Parcel.obtain();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r1 = 0;
        r7 = r6.length;	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r3.unmarshall(r6, r1, r7);	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r1 = 0;	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r3.setDataPosition(r1);	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r1 = com.google.android.gms.internal.zzcfx.CREATOR;	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r1 = r1.createFromParcel(r3);	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r1 = (com.google.android.gms.internal.zzcfx) r1;	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r3.recycle();
        if (r1 == 0) goto L_0x0061;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x0092:
        r10.add(r1);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        goto L_0x0061;
    L_0x0096:
        r1 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r13;
    L_0x009b:
        r3 = r14.zzawm();	 Catch:{ all -> 0x01f7 }
        r3 = r3.zzayr();	 Catch:{ all -> 0x01f7 }
        r4 = "Error reading entries from local database";	 Catch:{ all -> 0x01f7 }
        r3.zzj(r4, r0);	 Catch:{ all -> 0x01f7 }
        r0 = 1;	 Catch:{ all -> 0x01f7 }
        r14.zziyt = r0;	 Catch:{ all -> 0x01f7 }
        if (r1 == 0) goto L_0x00b0;
    L_0x00ad:
        r1.close();
    L_0x00b0:
        if (r2 == 0) goto L_0x0216;
    L_0x00b2:
        r2.close();
        r0 = r9;
    L_0x00b6:
        r1 = r12 + 1;
        r12 = r1;
        r9 = r0;
        goto L_0x0023;
    L_0x00bc:
        r1 = move-exception;
        r1 = r14.zzawm();	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r1 = r1.zzayr();	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r6 = "Failed to load event from local database";	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r1.log(r6);	 Catch:{ zzbel -> 0x00bc, all -> 0x00ed }
        r3.recycle();
        goto L_0x0061;
    L_0x00ce:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x00d2:
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x01fd }
        r4 = 11;	 Catch:{ all -> 0x01fd }
        if (r3 < r4) goto L_0x01be;	 Catch:{ all -> 0x01fd }
    L_0x00d8:
        r3 = r0 instanceof android.database.sqlite.SQLiteDatabaseLockedException;	 Catch:{ all -> 0x01fd }
        if (r3 == 0) goto L_0x01be;	 Catch:{ all -> 0x01fd }
    L_0x00dc:
        r4 = (long) r9;	 Catch:{ all -> 0x01fd }
        android.os.SystemClock.sleep(r4);	 Catch:{ all -> 0x01fd }
        r0 = r9 + 20;
    L_0x00e2:
        if (r2 == 0) goto L_0x00e7;
    L_0x00e4:
        r2.close();
    L_0x00e7:
        if (r1 == 0) goto L_0x00b6;
    L_0x00e9:
        r1.close();
        goto L_0x00b6;
    L_0x00ed:
        r1 = move-exception;
        r3.recycle();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        throw r1;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x00f2:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x00f6:
        if (r2 == 0) goto L_0x00fb;
    L_0x00f8:
        r2.close();
    L_0x00fb:
        if (r1 == 0) goto L_0x0100;
    L_0x00fd:
        r1.close();
    L_0x0100:
        throw r0;
    L_0x0101:
        r3 = 1;
        if (r1 != r3) goto L_0x013c;
    L_0x0104:
        r7 = android.os.Parcel.obtain();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r3 = 0;
        r1 = 0;
        r8 = r6.length;	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r7.unmarshall(r6, r1, r8);	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r1 = 0;	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r7.setDataPosition(r1);	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r1 = com.google.android.gms.internal.zzckk.CREATOR;	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r1 = r1.createFromParcel(r7);	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r1 = (com.google.android.gms.internal.zzckk) r1;	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r7.recycle();
    L_0x011d:
        if (r1 == 0) goto L_0x0061;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x011f:
        r10.add(r1);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        goto L_0x0061;
    L_0x0124:
        r1 = move-exception;
        r1 = r14.zzawm();	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r1 = r1.zzayr();	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r6 = "Failed to load user property from local database";	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r1.log(r6);	 Catch:{ zzbel -> 0x0124, all -> 0x0137 }
        r7.recycle();
        r1 = r3;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        goto L_0x011d;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x0137:
        r1 = move-exception;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r7.recycle();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        throw r1;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x013c:
        r3 = 2;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        if (r1 != r3) goto L_0x0177;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x013f:
        r7 = android.os.Parcel.obtain();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r3 = 0;
        r1 = 0;
        r8 = r6.length;	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r7.unmarshall(r6, r1, r8);	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r1 = 0;	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r7.setDataPosition(r1);	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r1 = com.google.android.gms.internal.zzcfi.CREATOR;	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r1 = r1.createFromParcel(r7);	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r1 = (com.google.android.gms.internal.zzcfi) r1;	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r7.recycle();
    L_0x0158:
        if (r1 == 0) goto L_0x0061;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x015a:
        r10.add(r1);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        goto L_0x0061;
    L_0x015f:
        r1 = move-exception;
        r1 = r14.zzawm();	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r1 = r1.zzayr();	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r6 = "Failed to load user property from local database";	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r1.log(r6);	 Catch:{ zzbel -> 0x015f, all -> 0x0172 }
        r7.recycle();
        r1 = r3;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        goto L_0x0158;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x0172:
        r1 = move-exception;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r7.recycle();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        throw r1;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x0177:
        r1 = r14.zzawm();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r1 = r1.zzayr();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r3 = "Unknown record type in local database";	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r1.log(r3);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        goto L_0x0061;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x0186:
        r1 = "messages";	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r3 = "rowid <= ?";	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r6 = 1;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r6 = new java.lang.String[r6];	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r7 = 0;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r4 = java.lang.Long.toString(r4);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r6[r7] = r4;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r1 = r0.delete(r1, r3, r6);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r3 = r10.size();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        if (r1 >= r3) goto L_0x01ab;	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x019e:
        r1 = r14.zzawm();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r1 = r1.zzayr();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r3 = "Fewer entries removed from local database than expected";	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r1.log(r3);	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
    L_0x01ab:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        r0.endTransaction();	 Catch:{ SQLiteFullException -> 0x0096, SQLiteException -> 0x00ce, all -> 0x00f2 }
        if (r2 == 0) goto L_0x01b6;
    L_0x01b3:
        r2.close();
    L_0x01b6:
        if (r0 == 0) goto L_0x01bb;
    L_0x01b8:
        r0.close();
    L_0x01bb:
        r0 = r10;
        goto L_0x0008;
    L_0x01be:
        if (r1 == 0) goto L_0x01c9;
    L_0x01c0:
        r3 = r1.inTransaction();	 Catch:{ all -> 0x01fd }
        if (r3 == 0) goto L_0x01c9;	 Catch:{ all -> 0x01fd }
    L_0x01c6:
        r1.endTransaction();	 Catch:{ all -> 0x01fd }
    L_0x01c9:
        r3 = r14.zzawm();	 Catch:{ all -> 0x01fd }
        r3 = r3.zzayr();	 Catch:{ all -> 0x01fd }
        r4 = "Error reading entries from local database";	 Catch:{ all -> 0x01fd }
        r3.zzj(r4, r0);	 Catch:{ all -> 0x01fd }
        r0 = 1;	 Catch:{ all -> 0x01fd }
        r14.zziyt = r0;	 Catch:{ all -> 0x01fd }
        r0 = r9;
        goto L_0x00e2;
    L_0x01dc:
        r0 = r14.zzawm();
        r0 = r0.zzayt();
        r1 = "Failed to read events from database in reasonable time";
        r0.log(r1);
        r0 = 0;
        goto L_0x0008;
    L_0x01ec:
        r0 = move-exception;
        r2 = r11;
        goto L_0x00f6;
    L_0x01f0:
        r1 = move-exception;
        r2 = r11;
        r13 = r1;
        r1 = r0;
        r0 = r13;
        goto L_0x00f6;
    L_0x01f7:
        r0 = move-exception;
        r13 = r1;
        r1 = r2;
        r2 = r13;
        goto L_0x00f6;
    L_0x01fd:
        r0 = move-exception;
        goto L_0x00f6;
    L_0x0200:
        r0 = move-exception;
        r2 = r11;
        goto L_0x00d2;
    L_0x0204:
        r1 = move-exception;
        r2 = r11;
        r13 = r1;
        r1 = r0;
        r0 = r13;
        goto L_0x00d2;
    L_0x020b:
        r0 = move-exception;
        r2 = r1;
        r1 = r11;
        goto L_0x009b;
    L_0x0210:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r11;
        goto L_0x009b;
    L_0x0216:
        r0 = r9;
        goto L_0x00b6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcgf.zzeb(int):java.util.List<com.google.android.gms.internal.zzbej>");
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}
