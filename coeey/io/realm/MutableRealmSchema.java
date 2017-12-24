package io.realm;

import io.realm.internal.Table;
import java.util.Locale;

class MutableRealmSchema extends RealmSchema {
    MutableRealmSchema(BaseRealm realm) {
        super(realm, null);
    }

    public RealmObjectSchema get(String className) {
        checkNotEmpty(className, "Null or empty class names are not allowed");
        String internalClassName = Table.getTableNameForClass(className);
        if (!this.realm.getSharedRealm().hasTable(internalClassName)) {
            return null;
        }
        return new MutableRealmObjectSchema(this.realm, this, this.realm.getSharedRealm().getTable(internalClassName));
    }

    public RealmObjectSchema create(String className) {
        checkNotEmpty(className, "Null or empty class names are not allowed");
        String internalTableName = Table.getTableNameForClass(className);
        if (className.length() <= Table.CLASS_NAME_MAX_LENGTH) {
            return new MutableRealmObjectSchema(this.realm, this, this.realm.getSharedRealm().createTable(internalTableName));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Class name is too long. Limit is %1$d characters: %2$s", new Object[]{Integer.valueOf(Table.CLASS_NAME_MAX_LENGTH), Integer.valueOf(className.length())}));
    }

    public void remove(String className) {
        this.realm.checkNotInSync();
        checkNotEmpty(className, "Null or empty class names are not allowed");
        String internalTableName = Table.getTableNameForClass(className);
        checkHasTable(className, "Cannot remove class because it is not in this Realm: " + className);
        Table table = getTable(className);
        if (table.hasPrimaryKey()) {
            table.setPrimaryKey(null);
        }
        this.realm.getSharedRealm().removeTable(internalTableName);
        removeFromClassNameToSchemaMap(internalTableName);
    }

    public RealmObjectSchema rename(String oldClassName, String newClassName) {
        this.realm.checkNotInSync();
        checkNotEmpty(oldClassName, "Class names cannot be empty or null");
        checkNotEmpty(newClassName, "Class names cannot be empty or null");
        String oldInternalName = Table.getTableNameForClass(oldClassName);
        String newInternalName = Table.getTableNameForClass(newClassName);
        checkHasTable(oldClassName, "Cannot rename class because it doesn't exist in this Realm: " + oldClassName);
        if (this.realm.getSharedRealm().hasTable(newInternalName)) {
            throw new IllegalArgumentException(oldClassName + " cannot be renamed because the new class already exists: " + newClassName);
        }
        Table oldTable = getTable(oldClassName);
        String pkField = null;
        if (oldTable.hasPrimaryKey()) {
            pkField = oldTable.getColumnName(oldTable.getPrimaryKey());
            oldTable.setPrimaryKey(null);
        }
        this.realm.getSharedRealm().renameTable(oldInternalName, newInternalName);
        Table table = this.realm.getSharedRealm().getTable(newInternalName);
        if (pkField != null) {
            table.setPrimaryKey(pkField);
        }
        RealmObjectSchema objectSchema = removeFromClassNameToSchemaMap(oldInternalName);
        if (!(objectSchema != null && objectSchema.getTable().isValid() && objectSchema.getClassName().equals(newClassName))) {
            objectSchema = new MutableRealmObjectSchema(this.realm, this, table);
        }
        putToClassNameToSchemaMap(newInternalName, objectSchema);
        return objectSchema;
    }
}
