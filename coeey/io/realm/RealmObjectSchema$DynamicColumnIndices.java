package io.realm;

import io.realm.internal.ColumnInfo;
import io.realm.internal.ColumnInfo$ColumnDetails;
import io.realm.internal.Table;

final class RealmObjectSchema$DynamicColumnIndices extends ColumnInfo {
    private final Table table;

    RealmObjectSchema$DynamicColumnIndices(Table table) {
        super(null, false);
        this.table = table;
    }

    public long getColumnIndex(String columnName) {
        return this.table.getColumnIndex(columnName);
    }

    public ColumnInfo$ColumnDetails getColumnDetails(String columnName) {
        throw new UnsupportedOperationException("DynamicColumnIndices do not support 'getColumnDetails'");
    }

    public void copyFrom(ColumnInfo src) {
        throw new UnsupportedOperationException("DynamicColumnIndices cannot be copied");
    }

    protected ColumnInfo copy(boolean immutable) {
        throw new UnsupportedOperationException("DynamicColumnIndices cannot be copied");
    }

    protected void copy(ColumnInfo src, ColumnInfo dst) {
        throw new UnsupportedOperationException("DynamicColumnIndices cannot copy");
    }
}
