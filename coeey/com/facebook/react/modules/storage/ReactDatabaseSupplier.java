package com.facebook.react.modules.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import javax.annotation.Nullable;

public class ReactDatabaseSupplier extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "RKStorage";
    private static final int DATABASE_VERSION = 1;
    static final String KEY_COLUMN = "key";
    private static final int SLEEP_TIME_MS = 30;
    static final String TABLE_CATALYST = "catalystLocalStorage";
    static final String VALUE_COLUMN = "value";
    static final String VERSION_TABLE_CREATE = "CREATE TABLE catalystLocalStorage (key TEXT PRIMARY KEY, value TEXT NOT NULL)";
    @Nullable
    private static ReactDatabaseSupplier sReactDatabaseSupplierInstance;
    private Context mContext;
    @Nullable
    private SQLiteDatabase mDb;
    private long mMaximumDatabaseSize = 6291456;

    private ReactDatabaseSupplier(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    public static ReactDatabaseSupplier getInstance(Context context) {
        if (sReactDatabaseSupplierInstance == null) {
            sReactDatabaseSupplierInstance = new ReactDatabaseSupplier(context.getApplicationContext());
        }
        return sReactDatabaseSupplierInstance;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VERSION_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            deleteDatabase();
            onCreate(db);
        }
    }

    synchronized boolean ensureDatabase() {
        if (this.mDb == null || !this.mDb.isOpen()) {
            SQLiteException lastSQLiteException = null;
            int tries = 0;
            while (tries < 2) {
                if (tries > 0) {
                    try {
                        deleteDatabase();
                    } catch (SQLiteException e) {
                        lastSQLiteException = e;
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e2) {
                            Thread.currentThread().interrupt();
                        }
                        tries++;
                    }
                }
                this.mDb = getWritableDatabase();
            }
            if (this.mDb == null) {
                throw lastSQLiteException;
            }
            this.mDb.setMaximumSize(this.mMaximumDatabaseSize);
        }
        return true;
    }

    public synchronized SQLiteDatabase get() {
        ensureDatabase();
        return this.mDb;
    }

    public synchronized void clearAndCloseDatabase() throws RuntimeException {
        try {
            clear();
            closeDatabase();
            FLog.d(ReactConstants.TAG, "Cleaned RKStorage");
        } catch (Exception e) {
            if (deleteDatabase()) {
                FLog.d(ReactConstants.TAG, "Deleted Local Database RKStorage");
            } else {
                throw new RuntimeException("Clearing and deleting database RKStorage failed");
            }
        }
    }

    synchronized void clear() {
        get().delete(TABLE_CATALYST, null, null);
    }

    public synchronized void setMaximumSize(long size) {
        this.mMaximumDatabaseSize = size;
        if (this.mDb != null) {
            this.mDb.setMaximumSize(this.mMaximumDatabaseSize);
        }
    }

    private synchronized boolean deleteDatabase() {
        closeDatabase();
        return this.mContext.deleteDatabase(DATABASE_NAME);
    }

    private synchronized void closeDatabase() {
        if (this.mDb != null && this.mDb.isOpen()) {
            this.mDb.close();
            this.mDb = null;
        }
    }

    public static void deleteInstance() {
        sReactDatabaseSupplierInstance = null;
    }
}
