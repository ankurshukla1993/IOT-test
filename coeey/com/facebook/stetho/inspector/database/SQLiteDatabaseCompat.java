package com.facebook.stetho.inspector.database;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;

public abstract class SQLiteDatabaseCompat {
    public static final int ENABLE_FOREIGN_KEY_CONSTRAINTS = 2;
    public static final int ENABLE_WRITE_AHEAD_LOGGING = 1;
    private static final SQLiteDatabaseCompat sInstance;

    @TargetApi(11)
    private static class HoneycombImpl extends SQLiteDatabaseCompat {
        private HoneycombImpl() {
        }

        public int provideOpenFlags(@SQLiteOpenOptions int openOptions) {
            return 0;
        }

        public void enableFeatures(@SQLiteOpenOptions int openOptions, SQLiteDatabase db) {
            if ((openOptions & 1) != 0) {
                db.enableWriteAheadLogging();
            }
            if ((openOptions & 2) != 0) {
                db.execSQL("PRAGMA foreign_keys = ON");
            }
        }
    }

    @TargetApi(16)
    private static class JellyBeanAndBeyondImpl extends SQLiteDatabaseCompat {
        private JellyBeanAndBeyondImpl() {
        }

        public int provideOpenFlags(@SQLiteOpenOptions int openOptions) {
            if ((openOptions & 1) != 0) {
                return 0 | 536870912;
            }
            return 0;
        }

        public void enableFeatures(@SQLiteOpenOptions int openOptions, SQLiteDatabase db) {
            if ((openOptions & 2) != 0) {
                db.setForeignKeyConstraintsEnabled(true);
            }
        }
    }

    private static class NoopImpl extends SQLiteDatabaseCompat {
        private NoopImpl() {
        }

        public int provideOpenFlags(@SQLiteOpenOptions int openOptions) {
            return 0;
        }

        public void enableFeatures(@SQLiteOpenOptions int openOptions, SQLiteDatabase db) {
        }
    }

    public @interface SQLiteOpenOptions {
    }

    public abstract void enableFeatures(@SQLiteOpenOptions int i, SQLiteDatabase sQLiteDatabase);

    public abstract int provideOpenFlags(@SQLiteOpenOptions int i);

    static {
        if (VERSION.SDK_INT >= 16) {
            sInstance = new JellyBeanAndBeyondImpl();
        } else if (VERSION.SDK_INT >= 11) {
            sInstance = new HoneycombImpl();
        } else {
            sInstance = new NoopImpl();
        }
    }

    public static SQLiteDatabaseCompat getInstance() {
        return sInstance;
    }
}
