package io.realm;

import io.realm.internal.ColumnIndices;
import io.realm.internal.Table;

class ImmutableRealmSchema extends RealmSchema {
    private static final String SCHEMA_IMMUTABLE_EXCEPTION_MSG = "This 'RealmSchema' is immutable. Please use 'DynamicRealm.getSchema() to get a mutable instance.";

    ImmutableRealmSchema(BaseRealm realm, ColumnIndices columnIndices) {
        super(realm, columnIndices);
    }

    public RealmObjectSchema get(String className) {
        checkNotEmpty(className, "Null or empty class names are not allowed");
        String internalClassName = Table.getTableNameForClass(className);
        if (!this.realm.getSharedRealm().hasTable(internalClassName)) {
            return null;
        }
        return new ImmutableRealmObjectSchema(this.realm, this, this.realm.getSharedRealm().getTable(internalClassName), getColumnInfo(className));
    }

    public RealmObjectSchema create(String className) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public void remove(String className) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema rename(String oldClassName, String newClassName) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }
}
