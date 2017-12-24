package io.realm;

import io.realm.internal.ColumnInfo;
import io.realm.internal.Table;

class ImmutableRealmObjectSchema extends RealmObjectSchema {
    private static final String SCHEMA_IMMUTABLE_EXCEPTION_MSG = "This 'RealmObjectSchema' is immutable. Please use 'DynamicRealm.getSchema() to get a mutable instance.";

    ImmutableRealmObjectSchema(BaseRealm realm, RealmSchema schema, Table table, ColumnInfo columnInfo) {
        super(realm, schema, table, columnInfo);
    }

    ImmutableRealmObjectSchema(BaseRealm realm, RealmSchema schema, Table table) {
        super(realm, schema, table, new RealmObjectSchema$DynamicColumnIndices(table));
    }

    public RealmObjectSchema setClassName(String className) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema addField(String fieldName, Class<?> cls, FieldAttribute... attributes) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema addRealmObjectField(String fieldName, RealmObjectSchema objectSchema) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema addRealmListField(String fieldName, RealmObjectSchema objectSchema) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema removeField(String fieldName) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema renameField(String currentFieldName, String newFieldName) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema addIndex(String fieldName) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema removeIndex(String fieldName) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema addPrimaryKey(String fieldName) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema removePrimaryKey() {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema setRequired(String fieldName, boolean required) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema setNullable(String fieldName, boolean nullable) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }

    public RealmObjectSchema transform(RealmObjectSchema$Function function) {
        throw new UnsupportedOperationException(SCHEMA_IMMUTABLE_EXCEPTION_MSG);
    }
}
