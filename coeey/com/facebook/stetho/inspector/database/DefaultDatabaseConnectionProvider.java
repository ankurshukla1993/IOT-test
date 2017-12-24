package com.facebook.stetho.inspector.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.database.SQLiteDatabaseCompat.SQLiteOpenOptions;
import java.io.File;

public class DefaultDatabaseConnectionProvider implements DatabaseConnectionProvider {
    public SQLiteDatabase openDatabase(File databaseFile) throws SQLiteException {
        return performOpen(databaseFile, determineOpenOptions(databaseFile));
    }

    @SQLiteOpenOptions
    protected int determineOpenOptions(File databaseFile) {
        if (new File(databaseFile.getParent(), databaseFile.getName() + "-wal").exists()) {
            return 0 | 1;
        }
        return 0;
    }

    protected SQLiteDatabase performOpen(File databaseFile, @SQLiteOpenOptions int options) {
        SQLiteDatabaseCompat compatInstance = SQLiteDatabaseCompat.getInstance();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(databaseFile.getAbsolutePath(), null, 0 | compatInstance.provideOpenFlags(options));
        compatInstance.enableFeatures(options, db);
        return db;
    }
}
