package io.realm.internal.fields;

import io.realm.RealmFieldType;
import io.realm.internal.ColumnInfo;
import io.realm.internal.Table;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public abstract class FieldDescriptor {
    public static final Set<RealmFieldType> ALL_LINK_FIELD_TYPES;
    public static final Set<RealmFieldType> LIST_LINK_FIELD_TYPE;
    public static final Set<RealmFieldType> NO_LINK_FIELD_TYPE = Collections.emptySet();
    public static final Set<RealmFieldType> OBJECT_LINK_FIELD_TYPE;
    public static final Set<RealmFieldType> SIMPLE_LINK_FIELD_TYPES;
    private long[] columnIndices;
    private final List<String> fields;
    private String finalColumnName;
    private RealmFieldType finalColumnType;
    private long[] nativeTablePointers;
    private final Set<RealmFieldType> validFinalColumnTypes;
    private final Set<RealmFieldType> validInternalColumnTypes;

    public interface SchemaProxy {
        ColumnInfo getColumnInfo(String str);

        long getNativeTablePtr(String str);

        boolean hasCache();
    }

    protected abstract void compileFieldDescription(List<String> list);

    static {
        Set<RealmFieldType> s = new HashSet(3);
        s.add(RealmFieldType.OBJECT);
        s.add(RealmFieldType.LIST);
        s.add(RealmFieldType.LINKING_OBJECTS);
        ALL_LINK_FIELD_TYPES = Collections.unmodifiableSet(s);
        s = new HashSet(2);
        s.add(RealmFieldType.OBJECT);
        s.add(RealmFieldType.LIST);
        SIMPLE_LINK_FIELD_TYPES = Collections.unmodifiableSet(s);
        s = new HashSet(1);
        s.add(RealmFieldType.LIST);
        LIST_LINK_FIELD_TYPE = Collections.unmodifiableSet(s);
        s = new HashSet(1);
        s.add(RealmFieldType.OBJECT);
        OBJECT_LINK_FIELD_TYPE = Collections.unmodifiableSet(s);
    }

    public static FieldDescriptor createStandardFieldDescriptor(SchemaProxy schema, Table table, String fieldDescription, RealmFieldType... validFinalColumnTypes) {
        return createFieldDescriptor(schema, table, fieldDescription, null, new HashSet(Arrays.asList(validFinalColumnTypes)));
    }

    public static FieldDescriptor createFieldDescriptor(SchemaProxy schema, Table table, String fieldDescription, Set<RealmFieldType> validInternalColumnTypes, Set<RealmFieldType> validFinalColumnTypes) {
        FieldDescriptor dynamicFieldDescriptor;
        if (schema == null || !schema.hasCache()) {
            if (validInternalColumnTypes == null) {
                validInternalColumnTypes = SIMPLE_LINK_FIELD_TYPES;
            }
            dynamicFieldDescriptor = new DynamicFieldDescriptor(table, fieldDescription, validInternalColumnTypes, validFinalColumnTypes);
        } else {
            dynamicFieldDescriptor = new CachedFieldDescriptor(schema, table.getClassName(), fieldDescription, validInternalColumnTypes != null ? validInternalColumnTypes : ALL_LINK_FIELD_TYPES, validFinalColumnTypes);
        }
        return dynamicFieldDescriptor;
    }

    protected FieldDescriptor(String fieldDescription, Set<RealmFieldType> validInternalColumnTypes, Set<RealmFieldType> validFinalColumnTypes) {
        this.fields = parseFieldDescription(fieldDescription);
        if (this.fields.size() <= 0) {
            throw new IllegalArgumentException("Invalid query: Empty field descriptor");
        }
        this.validInternalColumnTypes = validInternalColumnTypes;
        this.validFinalColumnTypes = validFinalColumnTypes;
    }

    public final int length() {
        return this.fields.size();
    }

    public final long[] getColumnIndices() {
        compileIfNecessary();
        return Arrays.copyOf(this.columnIndices, this.columnIndices.length);
    }

    public final long[] getNativeTablePointers() {
        compileIfNecessary();
        return Arrays.copyOf(this.nativeTablePointers, this.nativeTablePointers.length);
    }

    public final String getFinalColumnName() {
        compileIfNecessary();
        return this.finalColumnName;
    }

    public final RealmFieldType getFinalColumnType() {
        compileIfNecessary();
        return this.finalColumnType;
    }

    protected final void verifyInternalColumnType(String tableName, String columnName, RealmFieldType columnType) {
        verifyColumnType(tableName, columnName, columnType, this.validInternalColumnTypes);
    }

    protected final void setCompilationResults(String finalClassName, String finalColumnName, RealmFieldType finalColumnType, long[] columnIndices, long[] nativeTablePointers) {
        if (this.validFinalColumnTypes != null && this.validFinalColumnTypes.size() > 0) {
            verifyColumnType(finalClassName, finalColumnName, finalColumnType, this.validFinalColumnTypes);
        }
        this.finalColumnName = finalColumnName;
        this.finalColumnType = finalColumnType;
        this.columnIndices = columnIndices;
        this.nativeTablePointers = nativeTablePointers;
    }

    private List<String> parseFieldDescription(String fieldDescription) {
        if (fieldDescription == null || fieldDescription.equals("")) {
            throw new IllegalArgumentException("Invalid query: field name is empty");
        } else if (!fieldDescription.endsWith(".")) {
            return Arrays.asList(fieldDescription.split("\\."));
        } else {
            throw new IllegalArgumentException("Invalid query: field name must not end with a period ('.')");
        }
    }

    private void verifyColumnType(String className, String columnName, RealmFieldType columnType, Set<RealmFieldType> validTypes) {
        if (!validTypes.contains(columnType)) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid query: field '%s' in class '%s' is of invalid type '%s'.", new Object[]{columnName, className, columnType.toString()}));
        }
    }

    private void compileIfNecessary() {
        if (this.finalColumnType == null) {
            compileFieldDescription(this.fields);
        }
    }
}
