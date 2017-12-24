package com.facebook.stetho.inspector.database;

import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.protocol.module.BaseDatabaseDriver.ExecuteResultHandler;
import com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver;
import com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse;
import com.facebook.stetho.inspector.protocol.module.DatabaseDriver2;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class DatabaseDriver2Adapter extends DatabaseDriver2<StringDatabaseDescriptor> {
    private final DatabaseDriver mLegacy;

    public DatabaseDriver2Adapter(DatabaseDriver legacy) {
        super(legacy.getContext());
        this.mLegacy = legacy;
    }

    public List<StringDatabaseDescriptor> getDatabaseNames() {
        List<?> names = this.mLegacy.getDatabaseNames();
        List<StringDatabaseDescriptor> descriptors = new ArrayList(names.size());
        for (Object name : names) {
            descriptors.add(new StringDatabaseDescriptor(name.toString()));
        }
        return descriptors;
    }

    public List<String> getTableNames(StringDatabaseDescriptor database) {
        return this.mLegacy.getTableNames(database.name);
    }

    public ExecuteSQLResponse executeSQL(StringDatabaseDescriptor database, String query, ExecuteResultHandler handler) throws SQLiteException {
        return this.mLegacy.executeSQL(database.name, query, handler);
    }
}
