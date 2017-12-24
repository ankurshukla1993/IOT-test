package android.arch.persistence.db.framework;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.RequiresApi;

class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    private final OpenHelper mDelegate;

    static abstract class OpenHelper extends SQLiteOpenHelper {
        FrameworkSQLiteDatabase mWrappedDb;

        OpenHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        SupportSQLiteDatabase getWritableSupportDatabase() {
            return getWrappedDb(super.getWritableDatabase());
        }

        SupportSQLiteDatabase getReadableSupportDatabase() {
            return getWrappedDb(super.getReadableDatabase());
        }

        FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sqLiteDatabase) {
            if (this.mWrappedDb == null) {
                this.mWrappedDb = new FrameworkSQLiteDatabase(sqLiteDatabase);
            }
            return this.mWrappedDb;
        }

        public synchronized void close() {
            super.close();
            this.mWrappedDb = null;
        }
    }

    FrameworkSQLiteOpenHelper(Context context, String name, int version, DatabaseErrorHandler errorHandler, Callback callback) {
        this.mDelegate = createDelegate(context, name, version, errorHandler, callback);
    }

    private OpenHelper createDelegate(Context context, String name, int version, DatabaseErrorHandler errorHandler, Callback callback) {
        final Callback callback2 = callback;
        return new OpenHelper(context, name, null, version, errorHandler) {
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                this.mWrappedDb = new FrameworkSQLiteDatabase(sqLiteDatabase);
                callback2.onCreate(this.mWrappedDb);
            }

            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
                callback2.onUpgrade(getWrappedDb(sqLiteDatabase), oldVersion, newVersion);
            }

            public void onConfigure(SQLiteDatabase db) {
                callback2.onConfigure(getWrappedDb(db));
            }

            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                callback2.onDowngrade(getWrappedDb(db), oldVersion, newVersion);
            }

            public void onOpen(SQLiteDatabase db) {
                callback2.onOpen(getWrappedDb(db));
            }
        };
    }

    public String getDatabaseName() {
        return this.mDelegate.getDatabaseName();
    }

    @RequiresApi(api = 16)
    public void setWriteAheadLoggingEnabled(boolean enabled) {
        this.mDelegate.setWriteAheadLoggingEnabled(enabled);
    }

    public SupportSQLiteDatabase getWritableDatabase() {
        return this.mDelegate.getWritableSupportDatabase();
    }

    public SupportSQLiteDatabase getReadableDatabase() {
        return this.mDelegate.getReadableSupportDatabase();
    }

    public void close() {
        this.mDelegate.close();
    }
}
