package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper.Factory;
import android.arch.persistence.room.RoomDatabase.Callback;
import android.arch.persistence.room.RoomDatabase.MigrationContainer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.List;

public class DatabaseConfiguration {
    public final boolean allowMainThreadQueries;
    @Nullable
    public final List<Callback> callbacks;
    @NonNull
    public final Context context;
    @NonNull
    public final MigrationContainer migrationContainer;
    @Nullable
    public final String name;
    public final boolean requireMigration;
    @NonNull
    public final Factory sqliteOpenHelperFactory;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public DatabaseConfiguration(@NonNull Context context, @Nullable String name, @NonNull Factory sqliteOpenHelperFactory, @NonNull MigrationContainer migrationContainer, @Nullable List<Callback> callbacks, boolean allowMainThreadQueries, boolean requireMigration) {
        this.sqliteOpenHelperFactory = sqliteOpenHelperFactory;
        this.context = context;
        this.name = name;
        this.migrationContainer = migrationContainer;
        this.callbacks = callbacks;
        this.allowMainThreadQueries = allowMainThreadQueries;
        this.requireMigration = requireMigration;
    }
}
