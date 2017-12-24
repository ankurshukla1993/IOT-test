package io.realm.internal.fields;

import io.realm.RealmFieldType;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ColumnInfo$ColumnDetails;
import io.realm.internal.fields.FieldDescriptor.SchemaProxy;
import java.util.List;
import java.util.Locale;
import java.util.Set;

class CachedFieldDescriptor extends FieldDescriptor {
    private final String className;
    private final SchemaProxy schema;

    CachedFieldDescriptor(SchemaProxy schema, String className, String fieldDescription, Set<RealmFieldType> validInternalColumnTypes, Set<RealmFieldType> validFinalColumnTypes) {
        super(fieldDescription, validInternalColumnTypes, validFinalColumnTypes);
        this.className = className;
        this.schema = schema;
    }

    protected void compileFieldDescription(List<String> fields) {
        int nFields = fields.size();
        long[] columnIndices = new long[nFields];
        long[] tableNativePointers = new long[nFields];
        String currentClassName = this.className;
        String currentColumnName = null;
        RealmFieldType currentColumnType = null;
        for (int i = 0; i < nFields; i++) {
            currentColumnName = (String) fields.get(i);
            if (currentColumnName == null || currentColumnName.length() <= 0) {
                throw new IllegalArgumentException("Invalid query: Field descriptor contains an empty field.  A field description may not begin with or contain adjacent periods ('.').");
            }
            ColumnInfo columnInfo = this.schema.getColumnInfo(currentClassName);
            if (columnInfo == null) {
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid query: class '%s' not found in this schema.", new Object[]{currentClassName}));
            }
            ColumnInfo$ColumnDetails details = columnInfo.getColumnDetails(currentColumnName);
            if (details == null) {
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid query: field '%s' not found in class '%s'.", new Object[]{currentColumnName, currentClassName}));
            }
            long j;
            currentColumnType = details.columnType;
            if (i < nFields - 1) {
                verifyInternalColumnType(currentClassName, currentColumnName, currentColumnType);
                currentClassName = details.linkedClassName;
            }
            columnIndices[i] = details.columnIndex;
            if (currentColumnType != RealmFieldType.LINKING_OBJECTS) {
                j = 0;
            } else {
                j = this.schema.getNativeTablePtr(details.linkedClassName);
            }
            tableNativePointers[i] = j;
        }
        setCompilationResults(currentClassName, currentColumnName, currentColumnType, columnIndices, tableNativePointers);
    }
}
