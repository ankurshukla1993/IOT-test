package io.realm;

import io.realm.internal.ColumnInfo;
import io.realm.internal.fields.FieldDescriptor.SchemaProxy;

class SchemaConnector implements SchemaProxy {
    private final RealmSchema schema;

    public SchemaConnector(RealmSchema schema) {
        this.schema = schema;
    }

    public boolean hasCache() {
        return this.schema.haveColumnInfo();
    }

    public ColumnInfo getColumnInfo(String tableName) {
        return this.schema.getColumnInfo(tableName);
    }

    public long getNativeTablePtr(String targetTable) {
        return this.schema.getTable(targetTable).getNativePtr();
    }
}
