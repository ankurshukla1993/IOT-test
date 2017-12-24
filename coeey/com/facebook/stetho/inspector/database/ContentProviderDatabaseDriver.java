package com.facebook.stetho.inspector.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.facebook.stetho.inspector.protocol.module.BaseDatabaseDriver.ExecuteResultHandler;
import com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse;
import com.facebook.stetho.inspector.protocol.module.DatabaseDescriptor;
import com.facebook.stetho.inspector.protocol.module.DatabaseDriver2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class ContentProviderDatabaseDriver extends DatabaseDriver2<ContentProviderDatabaseDescriptor> {
    private static final String sDatabaseName = "content-providers";
    private final ContentProviderSchema[] mContentProviderSchemas;
    private List<String> mTableNames;

    static class ContentProviderDatabaseDescriptor implements DatabaseDescriptor {
        public String name() {
            return ContentProviderDatabaseDriver.sDatabaseName;
        }
    }

    public ContentProviderDatabaseDriver(Context context, ContentProviderSchema... contentProviderSchemas) {
        super(context);
        this.mContentProviderSchemas = contentProviderSchemas;
    }

    public List<ContentProviderDatabaseDescriptor> getDatabaseNames() {
        return Collections.singletonList(new ContentProviderDatabaseDescriptor());
    }

    public List<String> getTableNames(ContentProviderDatabaseDescriptor databaseDesc) {
        if (this.mTableNames == null) {
            this.mTableNames = new ArrayList();
            for (ContentProviderSchema schema : this.mContentProviderSchemas) {
                this.mTableNames.add(schema.getTableName());
            }
        }
        return this.mTableNames;
    }

    public ExecuteSQLResponse executeSQL(ContentProviderDatabaseDescriptor databaseDesc, String query, ExecuteResultHandler<ExecuteSQLResponse> handler) throws SQLiteException {
        ContentProviderSchema contentProviderSchema = this.mContentProviderSchemas[this.mTableNames.indexOf(fetchTableName(query))];
        Cursor cursor = this.mContext.getContentResolver().query(contentProviderSchema.getUri(), contentProviderSchema.getProjection(), null, null, null);
        try {
            ExecuteSQLResponse executeSQLResponse = (ExecuteSQLResponse) handler.handleSelect(cursor);
            return executeSQLResponse;
        } finally {
            cursor.close();
        }
    }

    private String fetchTableName(String query) {
        for (String tableName : this.mTableNames) {
            if (query.contains(tableName)) {
                return tableName;
            }
        }
        return "";
    }
}
