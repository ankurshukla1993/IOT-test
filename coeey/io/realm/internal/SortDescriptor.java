package io.realm.internal;

import io.realm.RealmFieldType;
import io.realm.Sort;
import io.realm.internal.fields.FieldDescriptor;
import io.realm.internal.fields.FieldDescriptor.SchemaProxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nullable;

@Keep
public class SortDescriptor {
    static final Set<RealmFieldType> DISTINCT_VALID_FIELD_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new RealmFieldType[]{RealmFieldType.BOOLEAN, RealmFieldType.INTEGER, RealmFieldType.STRING, RealmFieldType.DATE})));
    static final Set<RealmFieldType> SORT_VALID_FIELD_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new RealmFieldType[]{RealmFieldType.BOOLEAN, RealmFieldType.INTEGER, RealmFieldType.FLOAT, RealmFieldType.DOUBLE, RealmFieldType.STRING, RealmFieldType.DATE})));
    private final boolean[] ascendings;
    private final long[][] columnIndices;
    private final Table table;

    public static SortDescriptor getInstanceForSort(SchemaProxy proxy, Table table, String fieldDescription, Sort sortOrder) {
        return getInstanceForSort(proxy, table, new String[]{fieldDescription}, new Sort[]{sortOrder});
    }

    public static SortDescriptor getInstanceForSort(SchemaProxy proxy, Table table, String[] fieldDescriptions, Sort[] sortOrders) {
        if (sortOrders == null || sortOrders.length == 0) {
            throw new IllegalArgumentException("You must provide at least one sort order.");
        } else if (fieldDescriptions.length != sortOrders.length) {
            throw new IllegalArgumentException("Number of fields and sort orders do not match.");
        } else {
            return getInstance(proxy, table, fieldDescriptions, sortOrders, FieldDescriptor.OBJECT_LINK_FIELD_TYPE, SORT_VALID_FIELD_TYPES, "Sort is not supported");
        }
    }

    public static SortDescriptor getInstanceForDistinct(SchemaProxy proxy, Table table, String fieldDescription) {
        return getInstanceForDistinct(proxy, table, new String[]{fieldDescription});
    }

    public static SortDescriptor getInstanceForDistinct(SchemaProxy proxy, Table table, String[] fieldDescriptions) {
        return getInstance(proxy, table, fieldDescriptions, null, FieldDescriptor.NO_LINK_FIELD_TYPE, DISTINCT_VALID_FIELD_TYPES, "Distinct is not supported");
    }

    private static SortDescriptor getInstance(SchemaProxy proxy, Table table, String[] fieldDescriptions, @Nullable Sort[] sortOrders, Set<RealmFieldType> legalInternalTypes, Set<RealmFieldType> legalTerminalTypes, String message) {
        if (fieldDescriptions == null || fieldDescriptions.length == 0) {
            throw new IllegalArgumentException("You must provide at least one field name.");
        }
        long[][] columnIndices = new long[fieldDescriptions.length][];
        for (int i = 0; i < fieldDescriptions.length; i++) {
            FieldDescriptor descriptor = FieldDescriptor.createFieldDescriptor(proxy, table, fieldDescriptions[i], legalInternalTypes, null);
            checkFieldType(descriptor, legalTerminalTypes, message, fieldDescriptions[i]);
            columnIndices[i] = descriptor.getColumnIndices();
        }
        return new SortDescriptor(table, columnIndices, sortOrders);
    }

    static SortDescriptor getTestInstance(Table table, long[] columnIndices) {
        return new SortDescriptor(table, new long[][]{columnIndices}, null);
    }

    private static void checkFieldType(FieldDescriptor descriptor, Set<RealmFieldType> legalTerminalTypes, String message, String fieldDescriptions) {
        if (!legalTerminalTypes.contains(descriptor.getFinalColumnType())) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s on '%s' field '%s' in '%s'.", new Object[]{message, descriptor.getFinalColumnType(), descriptor.getFinalColumnName(), fieldDescriptions}));
        }
    }

    private SortDescriptor(Table table, long[][] columnIndices, @Nullable Sort[] sortOrders) {
        this.table = table;
        this.columnIndices = columnIndices;
        if (sortOrders != null) {
            this.ascendings = new boolean[sortOrders.length];
            for (int i = 0; i < sortOrders.length; i++) {
                this.ascendings[i] = sortOrders[i].getValue();
            }
            return;
        }
        this.ascendings = null;
    }

    long[][] getColumnIndices() {
        return this.columnIndices;
    }

    boolean[] getAscendings() {
        return this.ascendings;
    }

    private long getTablePtr() {
        return this.table.getNativePtr();
    }
}
