package io.realm.internal;

import io.realm.RealmFieldType;
import javax.annotation.Nullable;

public final class ColumnInfo$ColumnDetails {
    public final long columnIndex;
    public final RealmFieldType columnType;
    public final String linkedClassName;

    private ColumnInfo$ColumnDetails(long columnIndex, RealmFieldType columnType, @Nullable String linkedClassName) {
        this.columnIndex = columnIndex;
        this.columnType = columnType;
        this.linkedClassName = linkedClassName;
    }

    ColumnInfo$ColumnDetails(Property property) {
        this(property.getColumnIndex(), property.getType(), property.getLinkedObjectName());
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("ColumnDetails[");
        buf.append(this.columnIndex);
        buf.append(", ").append(this.columnType);
        buf.append(", ").append(this.linkedClassName);
        return buf.append("]").toString();
    }
}
