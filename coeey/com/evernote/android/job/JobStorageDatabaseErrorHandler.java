package com.evernote.android.job;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import com.evernote.android.job.util.JobCat;
import java.io.File;

final class JobStorageDatabaseErrorHandler implements DatabaseErrorHandler {
    private static final JobCat CAT = new JobCat("DatabaseErrorHandler");

    JobStorageDatabaseErrorHandler() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCorruption(android.database.sqlite.SQLiteDatabase r6) {
        /*
        r5 = this;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Corruption reported by sqlite on database: ";
        r2 = r2.append(r3);
        r3 = r6.getPath();
        r2 = r2.append(r3);
        r2 = r2.toString();
        net.vrallev.android.cat.Cat.e(r2);
        r2 = r6.isOpen();
        if (r2 != 0) goto L_0x0028;
    L_0x0020:
        r2 = r6.getPath();
        r5.deleteDatabaseFile(r2);
    L_0x0027:
        return;
    L_0x0028:
        r0 = 0;
        r0 = r6.getAttachedDbs();	 Catch:{ SQLiteException -> 0x0076, all -> 0x0052 }
    L_0x002d:
        r6.close();	 Catch:{ SQLiteException -> 0x0078, all -> 0x0052 }
    L_0x0030:
        if (r0 == 0) goto L_0x004a;
    L_0x0032:
        r3 = r0.iterator();
    L_0x0036:
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x0027;
    L_0x003c:
        r1 = r3.next();
        r1 = (android.util.Pair) r1;
        r2 = r1.second;
        r2 = (java.lang.String) r2;
        r5.deleteDatabaseFile(r2);
        goto L_0x0036;
    L_0x004a:
        r2 = r6.getPath();
        r5.deleteDatabaseFile(r2);
        goto L_0x0027;
    L_0x0052:
        r2 = move-exception;
        r3 = r2;
        if (r0 == 0) goto L_0x006e;
    L_0x0056:
        r4 = r0.iterator();
    L_0x005a:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x0075;
    L_0x0060:
        r1 = r4.next();
        r1 = (android.util.Pair) r1;
        r2 = r1.second;
        r2 = (java.lang.String) r2;
        r5.deleteDatabaseFile(r2);
        goto L_0x005a;
    L_0x006e:
        r2 = r6.getPath();
        r5.deleteDatabaseFile(r2);
    L_0x0075:
        throw r3;
    L_0x0076:
        r2 = move-exception;
        goto L_0x002d;
    L_0x0078:
        r2 = move-exception;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.evernote.android.job.JobStorageDatabaseErrorHandler.onCorruption(android.database.sqlite.SQLiteDatabase):void");
    }

    void deleteDatabaseFile(String fileName) {
        if (!fileName.equalsIgnoreCase(":memory:") && fileName.trim().length() != 0) {
            CAT.e("deleting the database file: " + fileName);
            try {
                File databaseFile = new File(fileName);
                if (VERSION.SDK_INT >= 16) {
                    deleteApi16(databaseFile);
                } else {
                    deleteApi14(JobManager.instance().getContext(), databaseFile);
                }
            } catch (Exception e) {
                CAT.w(e, "delete failed: " + e.getMessage(), new Object[0]);
            }
        }
    }

    @VisibleForTesting
    @TargetApi(16)
    void deleteApi16(File databaseFile) {
        SQLiteDatabase.deleteDatabase(databaseFile);
    }

    @VisibleForTesting
    void deleteApi14(Context context, File databaseFile) {
        context.deleteDatabase(databaseFile.getName());
    }
}
