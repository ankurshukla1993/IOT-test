package io.realm;

import io.realm.internal.Table;
import java.util.Locale;

class MutableRealmObjectSchema extends RealmObjectSchema {
    MutableRealmObjectSchema(BaseRealm realm, RealmSchema schema, Table table) {
        super(realm, schema, table, new RealmObjectSchema$DynamicColumnIndices(table));
    }

    public RealmObjectSchema setClassName(String className) {
        this.realm.checkNotInSync();
        checkEmpty(className);
        String internalTableName = Table.getTableNameForClass(className);
        if (className.length() > Table.CLASS_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(Locale.US, "Class name is too long. Limit is %1$d characters: '%2$s' (%3$d)", new Object[]{Integer.valueOf(Table.CLASS_NAME_MAX_LENGTH), className, Integer.valueOf(className.length())}));
        } else if (this.realm.sharedRealm.hasTable(internalTableName)) {
            throw new IllegalArgumentException("Class already exists: " + className);
        } else {
            String oldTableName = null;
            String pkField = null;
            if (this.table.hasPrimaryKey()) {
                oldTableName = this.table.getName();
                pkField = getPrimaryKey();
                this.table.setPrimaryKey(null);
            }
            this.realm.sharedRealm.renameTable(this.table.getName(), internalTableName);
            if (!(pkField == null || pkField.isEmpty())) {
                try {
                    this.table.setPrimaryKey(pkField);
                } catch (Exception e) {
                    this.realm.sharedRealm.renameTable(this.table.getName(), oldTableName);
                    throw e;
                }
            }
            return this;
        }
    }

    private void checkEmpty(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Null or empty class names are not allowed");
        }
    }

    public RealmObjectSchema addField(String fieldName, Class<?> fieldType, FieldAttribute... attributes) {
        RealmObjectSchema$FieldMetaData metadata = (RealmObjectSchema$FieldMetaData) SUPPORTED_SIMPLE_FIELDS.get(fieldType);
        if (metadata != null) {
            checkNewFieldName(fieldName);
            boolean nullable = metadata.defaultNullable;
            if (containsAttribute(attributes, FieldAttribute.REQUIRED)) {
                nullable = false;
            }
            long columnIndex = this.table.addColumn(metadata.realmType, fieldName, nullable);
            try {
                addModifiers(fieldName, attributes);
                return this;
            } catch (Exception e) {
                this.table.removeColumn(columnIndex);
                throw e;
            }
        } else if (SUPPORTED_LINKED_FIELDS.containsKey(fieldType)) {
            throw new IllegalArgumentException("Use addRealmObjectField() instead to add fields that link to other RealmObjects: " + fieldName);
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "Realm doesn't support this field type: %s(%s)", new Object[]{fieldName, fieldType}));
        }
    }

    public RealmObjectSchema addRealmObjectField(String fieldName, RealmObjectSchema objectSchema) {
        checkLegalName(fieldName);
        checkFieldNameIsAvailable(fieldName);
        this.table.addColumnLink(RealmFieldType.OBJECT, fieldName, this.realm.sharedRealm.getTable(Table.getTableNameForClass(objectSchema.getClassName())));
        return this;
    }

    public RealmObjectSchema addRealmListField(String fieldName, RealmObjectSchema objectSchema) {
        checkLegalName(fieldName);
        checkFieldNameIsAvailable(fieldName);
        this.table.addColumnLink(RealmFieldType.LIST, fieldName, this.realm.sharedRealm.getTable(Table.getTableNameForClass(objectSchema.getClassName())));
        return this;
    }

    public RealmObjectSchema removeField(String fieldName) {
        this.realm.checkNotInSync();
        checkLegalName(fieldName);
        if (hasField(fieldName)) {
            long columnIndex = getColumnIndex(fieldName);
            if (this.table.getPrimaryKey() == columnIndex) {
                this.table.setPrimaryKey(null);
            }
            this.table.removeColumn(columnIndex);
            return this;
        }
        throw new IllegalStateException(fieldName + " does not exist.");
    }

    public RealmObjectSchema renameField(String currentFieldName, String newFieldName) {
        this.realm.checkNotInSync();
        checkLegalName(currentFieldName);
        checkFieldExists(currentFieldName);
        checkLegalName(newFieldName);
        checkFieldNameIsAvailable(newFieldName);
        this.table.renameColumn(getColumnIndex(currentFieldName), newFieldName);
        return this;
    }

    public RealmObjectSchema addIndex(String fieldName) {
        checkLegalName(fieldName);
        checkFieldExists(fieldName);
        long columnIndex = getColumnIndex(fieldName);
        if (this.table.hasSearchIndex(columnIndex)) {
            throw new IllegalStateException(fieldName + " already has an index.");
        }
        this.table.addSearchIndex(columnIndex);
        return this;
    }

    public RealmObjectSchema removeIndex(String fieldName) {
        this.realm.checkNotInSync();
        checkLegalName(fieldName);
        checkFieldExists(fieldName);
        long columnIndex = getColumnIndex(fieldName);
        if (this.table.hasSearchIndex(columnIndex)) {
            this.table.removeSearchIndex(columnIndex);
            return this;
        }
        throw new IllegalStateException("Field is not indexed: " + fieldName);
    }

    public RealmObjectSchema addPrimaryKey(String fieldName) {
        checkLegalName(fieldName);
        checkFieldExists(fieldName);
        if (this.table.hasPrimaryKey()) {
            throw new IllegalStateException("A primary key is already defined");
        }
        this.table.setPrimaryKey(fieldName);
        long columnIndex = getColumnIndex(fieldName);
        if (!this.table.hasSearchIndex(columnIndex)) {
            this.table.addSearchIndex(columnIndex);
        }
        return this;
    }

    public RealmObjectSchema removePrimaryKey() {
        this.realm.checkNotInSync();
        if (this.table.hasPrimaryKey()) {
            long columnIndex = this.table.getPrimaryKey();
            if (this.table.hasSearchIndex(columnIndex)) {
                this.table.removeSearchIndex(columnIndex);
            }
            this.table.setPrimaryKey("");
            return this;
        }
        throw new IllegalStateException(getClassName() + " doesn't have a primary key.");
    }

    public RealmObjectSchema setRequired(String fieldName, boolean required) {
        long columnIndex = this.table.getColumnIndex(fieldName);
        boolean currentColumnRequired = isRequired(fieldName);
        RealmFieldType type = this.table.getColumnType(columnIndex);
        if (type == RealmFieldType.OBJECT) {
            throw new IllegalArgumentException("Cannot modify the required state for RealmObject references: " + fieldName);
        } else if (type == RealmFieldType.LIST) {
            throw new IllegalArgumentException("Cannot modify the required state for RealmList references: " + fieldName);
        } else if (required && currentColumnRequired) {
            throw new IllegalStateException("Field is already required: " + fieldName);
        } else if (required || currentColumnRequired) {
            if (required) {
                this.table.convertColumnToNotNullable(columnIndex);
            } else {
                this.table.convertColumnToNullable(columnIndex);
            }
            return this;
        } else {
            throw new IllegalStateException("Field is already nullable: " + fieldName);
        }
    }

    public RealmObjectSchema setNullable(String fieldName, boolean nullable) {
        setRequired(fieldName, !nullable);
        return this;
    }

    public RealmObjectSchema transform(RealmObjectSchema$Function function) {
        if (function != null) {
            long size = this.table.size();
            for (long i = 0; i < size; i++) {
                function.apply(new DynamicRealmObject(this.realm, this.table.getCheckedRow(i)));
            }
        }
        return this;
    }

    private void addModifiers(String fieldName, FieldAttribute[] attributes) {
        if (attributes != null) {
            try {
                if (attributes.length > 0) {
                    if (containsAttribute(attributes, FieldAttribute.INDEXED)) {
                        addIndex(fieldName);
                    }
                    if (containsAttribute(attributes, FieldAttribute.PRIMARY_KEY)) {
                        addPrimaryKey(fieldName);
                    }
                }
            } catch (Exception e) {
                long columnIndex = getColumnIndex(fieldName);
                if (false) {
                    this.table.removeSearchIndex(columnIndex);
                }
                throw ((RuntimeException) e);
            }
        }
    }

    private boolean containsAttribute(FieldAttribute[] attributeList, FieldAttribute attribute) {
        if (attributeList == null || attributeList.length == 0) {
            return false;
        }
        for (FieldAttribute anAttributeList : attributeList) {
            if (anAttributeList == attribute) {
                return true;
            }
        }
        return false;
    }

    private void checkNewFieldName(String fieldName) {
        checkLegalName(fieldName);
        checkFieldNameIsAvailable(fieldName);
    }

    private void checkFieldNameIsAvailable(String fieldName) {
        if (this.table.getColumnIndex(fieldName) != -1) {
            throw new IllegalArgumentException("Field already exists in '" + getClassName() + "': " + fieldName);
        }
    }
}
