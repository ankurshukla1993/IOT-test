package com.facebook.stetho.inspector.database;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.protocol.module.BaseDatabaseDriver.ExecuteResultHandler;
import com.facebook.stetho.inspector.protocol.module.DatabaseDriver2;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SqliteDatabaseDriver extends DatabaseDriver2<SqliteDatabaseDescriptor> {
    private static final String[] UNINTERESTING_FILENAME_SUFFIXES = new String[]{"-journal", "-shm", "-uid", "-wal"};
    private final DatabaseConnectionProvider mDatabaseConnectionProvider;
    private final DatabaseFilesProvider mDatabaseFilesProvider;

    @Deprecated
    public SqliteDatabaseDriver(Context context) {
        this(context, new DefaultDatabaseFilesProvider(context), new DefaultDatabaseConnectionProvider());
    }

    @Deprecated
    public SqliteDatabaseDriver(Context context, DatabaseFilesProvider databaseFilesProvider) {
        this(context, databaseFilesProvider, new DefaultDatabaseConnectionProvider());
    }

    public SqliteDatabaseDriver(Context context, DatabaseFilesProvider databaseFilesProvider, DatabaseConnectionProvider databaseConnectionProvider) {
        super(context);
        this.mDatabaseFilesProvider = databaseFilesProvider;
        this.mDatabaseConnectionProvider = databaseConnectionProvider;
    }

    public List<SqliteDatabaseDescriptor> getDatabaseNames() {
        ArrayList<SqliteDatabaseDescriptor> databases = new ArrayList();
        List<File> potentialDatabaseFiles = this.mDatabaseFilesProvider.getDatabaseFiles();
        Collections.sort(potentialDatabaseFiles);
        for (File database : tidyDatabaseList(potentialDatabaseFiles)) {
            databases.add(new SqliteDatabaseDescriptor(database));
        }
        return databases;
    }

    static List<File> tidyDatabaseList(List<File> databaseFiles) {
        Set<File> originalAsSet = new HashSet(databaseFiles);
        List<File> tidiedList = new ArrayList();
        for (File databaseFile : databaseFiles) {
            String databaseFilename = databaseFile.getPath();
            String sansSuffix = removeSuffix(databaseFilename, UNINTERESTING_FILENAME_SUFFIXES);
            if (sansSuffix.equals(databaseFilename) || !originalAsSet.contains(new File(sansSuffix))) {
                tidiedList.add(databaseFile);
            }
        }
        return tidiedList;
    }

    private static String removeSuffix(String str, String[] suffixesToRemove) {
        for (String suffix : suffixesToRemove) {
            if (str.endsWith(suffix)) {
                return str.substring(0, str.length() - suffix.length());
            }
        }
        return str;
    }

    public List<String> getTableNames(SqliteDatabaseDescriptor databaseDesc) throws SQLiteException {
        SQLiteDatabase database = openDatabase(databaseDesc);
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type IN (?, ?)", new String[]{"table", "view"});
            List<String> tableNames = new ArrayList();
            while (cursor.moveToNext()) {
                tableNames.add(cursor.getString(0));
            }
            cursor.close();
            database.close();
            return tableNames;
        } catch (Throwable th) {
            database.close();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse executeSQL(com.facebook.stetho.inspector.database.SqliteDatabaseDriver.SqliteDatabaseDescriptor r5, java.lang.String r6, com.facebook.stetho.inspector.protocol.module.BaseDatabaseDriver.ExecuteResultHandler<com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse> r7) throws android.database.sqlite.SQLiteException {
        /*
        r4 = this;
        com.facebook.stetho.common.Util.throwIfNull(r6);
        com.facebook.stetho.common.Util.throwIfNull(r7);
        r0 = r4.openDatabase(r5);
        r2 = getFirstWord(r6);	 Catch:{ all -> 0x0081 }
        r1 = r2.toUpperCase();	 Catch:{ all -> 0x0081 }
        r2 = -1;
        r3 = r1.hashCode();	 Catch:{ all -> 0x0081 }
        switch(r3) {
            case -2130463047: goto L_0x003b;
            case -1926899396: goto L_0x004f;
            case -1852692228: goto L_0x0045;
            case -1785516855: goto L_0x0027;
            case -591179561: goto L_0x0059;
            case 2012838315: goto L_0x0031;
            default: goto L_0x001a;
        };	 Catch:{ all -> 0x0081 }
    L_0x001a:
        switch(r2) {
            case 0: goto L_0x0063;
            case 1: goto L_0x0063;
            case 2: goto L_0x006d;
            case 3: goto L_0x0077;
            case 4: goto L_0x0077;
            case 5: goto L_0x0077;
            default: goto L_0x001d;
        };	 Catch:{ all -> 0x0081 }
    L_0x001d:
        r2 = r4.executeRawQuery(r0, r6, r7);	 Catch:{ all -> 0x0081 }
        r2 = (com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse) r2;	 Catch:{ all -> 0x0081 }
        r0.close();
    L_0x0026:
        return r2;
    L_0x0027:
        r3 = "UPDATE";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0081 }
        if (r3 == 0) goto L_0x001a;
    L_0x002f:
        r2 = 0;
        goto L_0x001a;
    L_0x0031:
        r3 = "DELETE";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0081 }
        if (r3 == 0) goto L_0x001a;
    L_0x0039:
        r2 = 1;
        goto L_0x001a;
    L_0x003b:
        r3 = "INSERT";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0081 }
        if (r3 == 0) goto L_0x001a;
    L_0x0043:
        r2 = 2;
        goto L_0x001a;
    L_0x0045:
        r3 = "SELECT";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0081 }
        if (r3 == 0) goto L_0x001a;
    L_0x004d:
        r2 = 3;
        goto L_0x001a;
    L_0x004f:
        r3 = "PRAGMA";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0081 }
        if (r3 == 0) goto L_0x001a;
    L_0x0057:
        r2 = 4;
        goto L_0x001a;
    L_0x0059:
        r3 = "EXPLAIN";
        r3 = r1.equals(r3);	 Catch:{ all -> 0x0081 }
        if (r3 == 0) goto L_0x001a;
    L_0x0061:
        r2 = 5;
        goto L_0x001a;
    L_0x0063:
        r2 = r4.executeUpdateDelete(r0, r6, r7);	 Catch:{ all -> 0x0081 }
        r2 = (com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse) r2;	 Catch:{ all -> 0x0081 }
        r0.close();
        goto L_0x0026;
    L_0x006d:
        r2 = r4.executeInsert(r0, r6, r7);	 Catch:{ all -> 0x0081 }
        r2 = (com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse) r2;	 Catch:{ all -> 0x0081 }
        r0.close();
        goto L_0x0026;
    L_0x0077:
        r2 = r4.executeSelect(r0, r6, r7);	 Catch:{ all -> 0x0081 }
        r2 = (com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse) r2;	 Catch:{ all -> 0x0081 }
        r0.close();
        goto L_0x0026;
    L_0x0081:
        r2 = move-exception;
        r0.close();
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.inspector.database.SqliteDatabaseDriver.executeSQL(com.facebook.stetho.inspector.database.SqliteDatabaseDriver$SqliteDatabaseDescriptor, java.lang.String, com.facebook.stetho.inspector.protocol.module.BaseDatabaseDriver$ExecuteResultHandler):com.facebook.stetho.inspector.protocol.module.Database$ExecuteSQLResponse");
    }

    private static String getFirstWord(String s) {
        s = s.trim();
        int firstSpace = s.indexOf(32);
        return firstSpace >= 0 ? s.substring(0, firstSpace) : s;
    }

    @TargetApi(11)
    private <T> T executeUpdateDelete(SQLiteDatabase database, String query, ExecuteResultHandler<T> handler) {
        return handler.handleUpdateDelete(database.compileStatement(query).executeUpdateDelete());
    }

    private <T> T executeInsert(SQLiteDatabase database, String query, ExecuteResultHandler<T> handler) {
        return handler.handleInsert(database.compileStatement(query).executeInsert());
    }

    private <T> T executeSelect(SQLiteDatabase database, String query, ExecuteResultHandler<T> handler) {
        Cursor cursor = database.rawQuery(query, null);
        try {
            T handleSelect = handler.handleSelect(cursor);
            return handleSelect;
        } finally {
            cursor.close();
        }
    }

    private <T> T executeRawQuery(SQLiteDatabase database, String query, ExecuteResultHandler<T> handler) {
        database.execSQL(query);
        return handler.handleRawQuery();
    }

    private SQLiteDatabase openDatabase(SqliteDatabaseDescriptor databaseDesc) throws SQLiteException {
        Util.throwIfNull(databaseDesc);
        return this.mDatabaseConnectionProvider.openDatabase(databaseDesc.file);
    }
}
