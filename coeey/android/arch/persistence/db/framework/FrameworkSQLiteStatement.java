package android.arch.persistence.db.framework;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.database.sqlite.SQLiteStatement;

class FrameworkSQLiteStatement implements SupportSQLiteStatement {
    private final SQLiteStatement mDelegate;

    public FrameworkSQLiteStatement(SQLiteStatement delegate) {
        this.mDelegate = delegate;
    }

    public void bindNull(int index) {
        this.mDelegate.bindNull(index);
    }

    public void bindLong(int index, long value) {
        this.mDelegate.bindLong(index, value);
    }

    public void bindDouble(int index, double value) {
        this.mDelegate.bindDouble(index, value);
    }

    public void bindString(int index, String value) {
        this.mDelegate.bindString(index, value);
    }

    public void bindBlob(int index, byte[] value) {
        this.mDelegate.bindBlob(index, value);
    }

    public void clearBindings() {
        this.mDelegate.clearBindings();
    }

    public void execute() {
        this.mDelegate.execute();
    }

    public int executeUpdateDelete() {
        return this.mDelegate.executeUpdateDelete();
    }

    public long executeInsert() {
        return this.mDelegate.executeInsert();
    }

    public long simpleQueryForLong() {
        return this.mDelegate.simpleQueryForLong();
    }

    public String simpleQueryForString() {
        return this.mDelegate.simpleQueryForString();
    }

    public void close() throws Exception {
        this.mDelegate.close();
    }
}
