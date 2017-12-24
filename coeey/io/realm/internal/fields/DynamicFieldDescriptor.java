package io.realm.internal.fields;

import io.realm.RealmFieldType;
import io.realm.internal.Table;
import java.util.List;
import java.util.Locale;
import java.util.Set;

class DynamicFieldDescriptor extends FieldDescriptor {
    private final Table table;

    DynamicFieldDescriptor(Table table, String fieldDescription, Set<RealmFieldType> validInternalColumnTypes, Set<RealmFieldType> validFinalColumnTypes) {
        super(fieldDescription, validInternalColumnTypes, validFinalColumnTypes);
        this.table = table;
    }

    protected void compileFieldDescription(List<String> fields) {
        int nFields = fields.size();
        long[] columnIndices = new long[nFields];
        Table currentTable = this.table;
        String currentClassName = null;
        String currentColumnName = null;
        RealmFieldType currentColumnType = null;
        for (int i = 0; i < nFields; i++) {
            currentColumnName = (String) fields.get(i);
            if (currentColumnName == null || currentColumnName.length() <= 0) {
                throw new IllegalArgumentException("Invalid query: Field descriptor contains an empty field.  A field description may not begin with or contain adjacent periods ('.').");
            }
            currentClassName = currentTable.getClassName();
            long columnIndex = currentTable.getColumnIndex(currentColumnName);
            if (columnIndex < 0) {
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid query: field '%s' not found in table '%s'.", new Object[]{currentColumnName, currentClassName}));
            }
            currentColumnType = currentTable.getColumnType(columnIndex);
            if (i < nFields - 1) {
                verifyInternalColumnType(currentClassName, currentColumnName, currentColumnType);
                currentTable = currentTable.getLinkTarget(columnIndex);
            }
            columnIndices[i] = columnIndex;
        }
        setCompilationResults(currentClassName, currentColumnName, currentColumnType, columnIndices, new long[nFields]);
    }
}
