package io.realm;

import io.realm.internal.ColumnInfo;
import io.realm.internal.Table;
import io.realm.internal.fields.FieldDescriptor;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public abstract class RealmObjectSchema {
    static final Map<Class<?>, FieldMetaData> SUPPORTED_LINKED_FIELDS;
    static final Map<Class<?>, FieldMetaData> SUPPORTED_SIMPLE_FIELDS;
    private final ColumnInfo columnInfo;
    final BaseRealm realm;
    final RealmSchema schema;
    final Table table;

    public abstract RealmObjectSchema addField(String str, Class<?> cls, FieldAttribute... fieldAttributeArr);

    public abstract RealmObjectSchema addIndex(String str);

    public abstract RealmObjectSchema addPrimaryKey(String str);

    public abstract RealmObjectSchema addRealmListField(String str, RealmObjectSchema realmObjectSchema);

    public abstract RealmObjectSchema addRealmObjectField(String str, RealmObjectSchema realmObjectSchema);

    public abstract RealmObjectSchema removeField(String str);

    public abstract RealmObjectSchema removeIndex(String str);

    public abstract RealmObjectSchema removePrimaryKey();

    public abstract RealmObjectSchema renameField(String str, String str2);

    public abstract RealmObjectSchema setClassName(String str);

    public abstract RealmObjectSchema setNullable(String str, boolean z);

    public abstract RealmObjectSchema setRequired(String str, boolean z);

    public abstract RealmObjectSchema transform(Function function);

    static {
        Map<Class<?>, FieldMetaData> m = new HashMap();
        m.put(String.class, new FieldMetaData(RealmFieldType.STRING, true));
        m.put(Short.TYPE, new FieldMetaData(RealmFieldType.INTEGER, false));
        m.put(Short.class, new FieldMetaData(RealmFieldType.INTEGER, true));
        m.put(Integer.TYPE, new FieldMetaData(RealmFieldType.INTEGER, false));
        m.put(Integer.class, new FieldMetaData(RealmFieldType.INTEGER, true));
        m.put(Long.TYPE, new FieldMetaData(RealmFieldType.INTEGER, false));
        m.put(Long.class, new FieldMetaData(RealmFieldType.INTEGER, true));
        m.put(Float.TYPE, new FieldMetaData(RealmFieldType.FLOAT, false));
        m.put(Float.class, new FieldMetaData(RealmFieldType.FLOAT, true));
        m.put(Double.TYPE, new FieldMetaData(RealmFieldType.DOUBLE, false));
        m.put(Double.class, new FieldMetaData(RealmFieldType.DOUBLE, true));
        m.put(Boolean.TYPE, new FieldMetaData(RealmFieldType.BOOLEAN, false));
        m.put(Boolean.class, new FieldMetaData(RealmFieldType.BOOLEAN, true));
        m.put(Byte.TYPE, new FieldMetaData(RealmFieldType.INTEGER, false));
        m.put(Byte.class, new FieldMetaData(RealmFieldType.INTEGER, true));
        m.put(byte[].class, new FieldMetaData(RealmFieldType.BINARY, true));
        m.put(Date.class, new FieldMetaData(RealmFieldType.DATE, true));
        SUPPORTED_SIMPLE_FIELDS = Collections.unmodifiableMap(m);
        m = new HashMap();
        m.put(RealmObject.class, new FieldMetaData(RealmFieldType.OBJECT, false));
        m.put(RealmList.class, new FieldMetaData(RealmFieldType.LIST, false));
        SUPPORTED_LINKED_FIELDS = Collections.unmodifiableMap(m);
    }

    RealmObjectSchema(BaseRealm realm, RealmSchema schema, Table table, ColumnInfo columnInfo) {
        this.schema = schema;
        this.realm = realm;
        this.table = table;
        this.columnInfo = columnInfo;
    }

    @Deprecated
    public void close() {
    }

    public String getClassName() {
        return this.table.getClassName();
    }

    public boolean hasField(String fieldName) {
        return this.table.getColumnIndex(fieldName) != -1;
    }

    public boolean hasIndex(String fieldName) {
        checkLegalName(fieldName);
        checkFieldExists(fieldName);
        return this.table.hasSearchIndex(this.table.getColumnIndex(fieldName));
    }

    public boolean isRequired(String fieldName) {
        return !this.table.isColumnNullable(getColumnIndex(fieldName));
    }

    public boolean isNullable(String fieldName) {
        return this.table.isColumnNullable(getColumnIndex(fieldName));
    }

    public boolean isPrimaryKey(String fieldName) {
        return getColumnIndex(fieldName) == this.table.getPrimaryKey();
    }

    public boolean hasPrimaryKey() {
        return this.table.hasPrimaryKey();
    }

    public String getPrimaryKey() {
        if (this.table.hasPrimaryKey()) {
            return this.table.getColumnName(this.table.getPrimaryKey());
        }
        throw new IllegalStateException(getClassName() + " doesn't have a primary key.");
    }

    public Set<String> getFieldNames() {
        int columnCount = (int) this.table.getColumnCount();
        Set<String> columnNames = new LinkedHashSet(columnCount);
        for (int i = 0; i < columnCount; i++) {
            columnNames.add(this.table.getColumnName((long) i));
        }
        return columnNames;
    }

    public RealmFieldType getFieldType(String fieldName) {
        return this.table.getColumnType(getColumnIndex(fieldName));
    }

    protected final FieldDescriptor getColumnIndices(String fieldDescription, RealmFieldType... validColumnTypes) {
        return FieldDescriptor.createStandardFieldDescriptor(getSchemaConnector(), getTable(), fieldDescription, validColumnTypes);
    }

    RealmObjectSchema add(String name, RealmFieldType type, boolean primary, boolean indexed, boolean required) {
        long columnIndex = this.table.addColumn(type, name, !required);
        if (indexed) {
            this.table.addSearchIndex(columnIndex);
        }
        if (primary) {
            this.table.setPrimaryKey(name);
        }
        return this;
    }

    RealmObjectSchema add(String name, RealmFieldType type, RealmObjectSchema linkedTo) {
        this.table.addColumnLink(type, name, this.realm.getSharedRealm().getTable(Table.getTableNameForClass(linkedTo.getClassName())));
        return this;
    }

    long getAndCheckFieldIndex(String fieldName) {
        long index = this.columnInfo.getColumnIndex(fieldName);
        if (index >= 0) {
            return index;
        }
        throw new IllegalArgumentException("Field does not exist: " + fieldName);
    }

    Table getTable() {
        return this.table;
    }

    private SchemaConnector getSchemaConnector() {
        return new SchemaConnector(this.schema);
    }

    long getFieldIndex(String fieldName) {
        return this.columnInfo.getColumnIndex(fieldName);
    }

    void checkLegalName(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field name can not be null or empty");
        } else if (fieldName.contains(".")) {
            throw new IllegalArgumentException("Field name can not contain '.'");
        }
    }

    void checkFieldExists(String fieldName) {
        if (this.table.getColumnIndex(fieldName) == -1) {
            throw new IllegalArgumentException("Field name doesn't exist on object '" + getClassName() + "': " + fieldName);
        }
    }

    long getColumnIndex(String fieldName) {
        long columnIndex = this.table.getColumnIndex(fieldName);
        if (columnIndex != -1) {
            return columnIndex;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Field name '%s' does not exist on schema for '%s'", new Object[]{fieldName, getClassName()}));
    }
}
