package io.realm;

import io.realm.internal.ColumnIndices;
import io.realm.internal.ColumnInfo;
import io.realm.internal.Table;
import io.realm.internal.Util;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public abstract class RealmSchema {
    static final String EMPTY_STRING_MSG = "Null or empty class names are not allowed";
    private final Map<Class<? extends RealmModel>, RealmObjectSchema> classToSchema = new HashMap();
    private final Map<Class<? extends RealmModel>, Table> classToTable = new HashMap();
    private final ColumnIndices columnIndices;
    private final Map<String, RealmObjectSchema> dynamicClassToSchema = new HashMap();
    private final Map<String, Table> dynamicClassToTable = new HashMap();
    final BaseRealm realm;

    public abstract RealmObjectSchema create(String str);

    @Nullable
    public abstract RealmObjectSchema get(String str);

    public abstract void remove(String str);

    public abstract RealmObjectSchema rename(String str, String str2);

    RealmSchema(BaseRealm realm, @Nullable ColumnIndices columnIndices) {
        this.realm = realm;
        this.columnIndices = columnIndices;
    }

    @Deprecated
    public void close() {
    }

    public Set<RealmObjectSchema> getAll() {
        int tableCount = (int) this.realm.getSharedRealm().size();
        Set<RealmObjectSchema> schemas = new LinkedHashSet(tableCount);
        for (int i = 0; i < tableCount; i++) {
            RealmObjectSchema objectSchema = get(Table.getClassNameForTable(this.realm.getSharedRealm().getTableName(i)));
            if (objectSchema != null) {
                schemas.add(objectSchema);
            }
        }
        return schemas;
    }

    public boolean contains(String className) {
        return this.realm.getSharedRealm().hasTable(Table.getTableNameForClass(className));
    }

    void checkNotEmpty(String str, String error) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
    }

    void checkHasTable(String className, String errorMsg) {
        if (!this.realm.getSharedRealm().hasTable(Table.getTableNameForClass(className))) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    Table getTable(String className) {
        String tableName = Table.getTableNameForClass(className);
        Table table = (Table) this.dynamicClassToTable.get(tableName);
        if (table != null) {
            return table;
        }
        table = this.realm.getSharedRealm().getTable(tableName);
        this.dynamicClassToTable.put(tableName, table);
        return table;
    }

    Table getTable(Class<? extends RealmModel> clazz) {
        Table table = (Table) this.classToTable.get(clazz);
        if (table != null) {
            return table;
        }
        Class<? extends RealmModel> originalClass = Util.getOriginalModelClass(clazz);
        if (isProxyClass(originalClass, clazz)) {
            table = (Table) this.classToTable.get(originalClass);
        }
        if (table == null) {
            table = this.realm.getSharedRealm().getTable(this.realm.getConfiguration().getSchemaMediator().getTableName(originalClass));
            this.classToTable.put(originalClass, table);
        }
        if (isProxyClass(originalClass, clazz)) {
            this.classToTable.put(clazz, table);
        }
        return table;
    }

    RealmObjectSchema getSchemaForClass(Class<? extends RealmModel> clazz) {
        RealmObjectSchema realmObjectSchema = (RealmObjectSchema) this.classToSchema.get(clazz);
        if (realmObjectSchema != null) {
            return realmObjectSchema;
        }
        Class originalClass = Util.getOriginalModelClass(clazz);
        if (isProxyClass(originalClass, clazz)) {
            realmObjectSchema = (RealmObjectSchema) this.classToSchema.get(originalClass);
        }
        if (realmObjectSchema == null) {
            realmObjectSchema = new ImmutableRealmObjectSchema(this.realm, this, getTable((Class) clazz), getColumnInfo(originalClass));
            this.classToSchema.put(originalClass, realmObjectSchema);
        }
        if (isProxyClass(originalClass, clazz)) {
            this.classToSchema.put(clazz, realmObjectSchema);
        }
        return realmObjectSchema;
    }

    RealmObjectSchema getSchemaForClass(String className) {
        String tableName = Table.getTableNameForClass(className);
        RealmObjectSchema dynamicSchema = (RealmObjectSchema) this.dynamicClassToSchema.get(tableName);
        if (dynamicSchema != null && dynamicSchema.getTable().isValid() && dynamicSchema.getClassName().equals(className)) {
            return dynamicSchema;
        }
        if (this.realm.getSharedRealm().hasTable(tableName)) {
            dynamicSchema = new ImmutableRealmObjectSchema(this.realm, this, this.realm.getSharedRealm().getTable(tableName));
            this.dynamicClassToSchema.put(tableName, dynamicSchema);
            return dynamicSchema;
        }
        throw new IllegalArgumentException("The class " + className + " doesn't exist in this Realm.");
    }

    private boolean isProxyClass(Class<? extends RealmModel> modelClass, Class<? extends RealmModel> testee) {
        return modelClass.equals(testee);
    }

    final boolean haveColumnInfo() {
        return this.columnIndices != null;
    }

    final ColumnInfo getColumnInfo(Class<? extends RealmModel> clazz) {
        checkIndices();
        return this.columnIndices.getColumnInfo((Class) clazz);
    }

    protected final ColumnInfo getColumnInfo(String className) {
        checkIndices();
        return this.columnIndices.getColumnInfo(className);
    }

    final void putToClassNameToSchemaMap(String name, RealmObjectSchema objectSchema) {
        this.dynamicClassToSchema.put(name, objectSchema);
    }

    final RealmObjectSchema removeFromClassNameToSchemaMap(String name) {
        return (RealmObjectSchema) this.dynamicClassToSchema.remove(name);
    }

    private void checkIndices() {
        if (!haveColumnInfo()) {
            throw new IllegalStateException("Attempt to use column index before set.");
        }
    }

    void refresh() {
        if (this.columnIndices != null) {
            this.columnIndices.refresh();
        }
        this.dynamicClassToTable.clear();
        this.classToTable.clear();
        this.classToSchema.clear();
        this.dynamicClassToSchema.clear();
    }
}
