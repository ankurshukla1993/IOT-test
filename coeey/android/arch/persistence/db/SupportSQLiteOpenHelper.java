package android.arch.persistence.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

public interface SupportSQLiteOpenHelper {

    public static abstract class Callback {
        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);

        public void onConfigure(SupportSQLiteDatabase db) {
        }

        public void onDowngrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
            throw new SQLiteException("Can't downgrade database from version " + oldVersion + " to " + newVersion);
        }

        public void onOpen(SupportSQLiteDatabase db) {
        }
    }

    public static class Configuration {
        @NonNull
        public final Callback callback;
        @NonNull
        public final Context context;
        @Nullable
        public final DatabaseErrorHandler errorHandler;
        @Nullable
        public final String name;
        public final int version;

        public static class Builder {
            Callback mCallback;
            Context mContext;
            DatabaseErrorHandler mErrorHandler;
            String mName;
            int mVersion = 1;

            public Configuration build() {
                if (this.mCallback == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                } else if (this.mContext == null) {
                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                } else {
                    if (this.mErrorHandler == null) {
                        this.mErrorHandler = new DefaultDatabaseErrorHandler();
                    }
                    return new Configuration(this.mContext, this.mName, this.mVersion, this.mErrorHandler, this.mCallback);
                }
            }

            Builder(@NonNull Context context) {
                this.mContext = context;
            }

            public Builder errorHandler(@Nullable DatabaseErrorHandler errorHandler) {
                this.mErrorHandler = errorHandler;
                return this;
            }

            public Builder name(@Nullable String name) {
                this.mName = name;
                return this;
            }

            public Builder callback(@NonNull Callback callback) {
                this.mCallback = callback;
                return this;
            }

            public Builder version(int version) {
                this.mVersion = version;
                return this;
            }
        }

        Configuration(@NonNull Context context, @Nullable String name, int version, @Nullable DatabaseErrorHandler errorHandler, @NonNull Callback callback) {
            this.context = context;
            this.name = name;
            this.version = version;
            this.callback = callback;
            this.errorHandler = errorHandler;
        }

        public static Builder builder(Context context) {
            return new Builder(context);
        }
    }

    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration configuration);
    }

    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getReadableDatabase();

    SupportSQLiteDatabase getWritableDatabase();

    @RequiresApi(api = 16)
    void setWriteAheadLoggingEnabled(boolean z);
}
