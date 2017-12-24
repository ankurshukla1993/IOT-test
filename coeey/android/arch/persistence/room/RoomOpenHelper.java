package android.arch.persistence.room;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.room.migration.Migration;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class RoomOpenHelper extends Callback {
    @Nullable
    private DatabaseConfiguration mConfiguration;
    @NonNull
    private final Delegate mDelegate;
    @NonNull
    private final String mIdentityHash;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static abstract class Delegate {
        protected abstract void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        protected abstract void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        protected abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        protected abstract void onOpen(SupportSQLiteDatabase supportSQLiteDatabase);

        protected abstract void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase);
    }

    public RoomOpenHelper(@NonNull DatabaseConfiguration configuration, @NonNull Delegate delegate, @NonNull String identityHash) {
        this.mConfiguration = configuration;
        this.mDelegate = delegate;
        this.mIdentityHash = identityHash;
    }

    public void onConfigure(SupportSQLiteDatabase db) {
        super.onConfigure(db);
    }

    public void onCreate(SupportSQLiteDatabase db) {
        updateIdentity(db);
        this.mDelegate.createAllTables(db);
        this.mDelegate.onCreate(db);
    }

    public void onUpgrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
        boolean migrated = false;
        if (this.mConfiguration != null) {
            List<Migration> migrations = this.mConfiguration.migrationContainer.findMigrationPath(oldVersion, newVersion);
            if (migrations != null) {
                for (Migration migration : migrations) {
                    migration.migrate(db);
                }
                this.mDelegate.validateMigration(db);
                updateIdentity(db);
                migrated = true;
            }
        }
        if (!migrated) {
            if (this.mConfiguration == null || this.mConfiguration.requireMigration) {
                throw new IllegalStateException("A migration from " + oldVersion + " to " + newVersion + " is necessary. Please provide a Migration in the builder or call" + " fallbackToDestructiveMigration in the builder in which case Room will" + " re-create all of the tables.");
            }
            this.mDelegate.dropAllTables(db);
            this.mDelegate.createAllTables(db);
        }
    }

    public void onDowngrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void onOpen(SupportSQLiteDatabase db) {
        super.onOpen(db);
        checkIdentity(db);
        this.mDelegate.onOpen(db);
        this.mConfiguration = null;
    }

    private void checkIdentity(SupportSQLiteDatabase db) {
        createMasterTableIfNotExists(db);
        String identityHash = "";
        Cursor cursor = db.query(new SimpleSQLiteQuery(RoomMasterTable.READ_QUERY));
        try {
            if (cursor.moveToFirst()) {
                identityHash = cursor.getString(0);
            }
            cursor.close();
            if (!this.mIdentityHash.equals(identityHash)) {
                throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.");
            }
        } catch (Throwable th) {
            cursor.close();
        }
    }

    private void updateIdentity(SupportSQLiteDatabase db) {
        createMasterTableIfNotExists(db);
        db.execSQL(RoomMasterTable.createInsertQuery(this.mIdentityHash));
    }

    private void createMasterTableIfNotExists(SupportSQLiteDatabase db) {
        db.execSQL(RoomMasterTable.CREATE_QUERY);
    }
}
