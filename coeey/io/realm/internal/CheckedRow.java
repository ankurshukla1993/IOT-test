package io.realm.internal;

import io.realm.RealmFieldType;

public class CheckedRow extends UncheckedRow {
    private UncheckedRow originalRow;

    protected native boolean nativeGetBoolean(long j, long j2);

    protected native byte[] nativeGetByteArray(long j, long j2);

    protected native long nativeGetColumnCount(long j);

    protected native long nativeGetColumnIndex(long j, String str);

    protected native String nativeGetColumnName(long j, long j2);

    protected native int nativeGetColumnType(long j, long j2);

    protected native double nativeGetDouble(long j, long j2);

    protected native float nativeGetFloat(long j, long j2);

    protected native long nativeGetLink(long j, long j2);

    protected native long nativeGetLong(long j, long j2);

    protected native String nativeGetString(long j, long j2);

    protected native long nativeGetTimestamp(long j, long j2);

    protected native boolean nativeIsNullLink(long j, long j2);

    protected native void nativeNullifyLink(long j, long j2);

    protected native void nativeSetBoolean(long j, long j2, boolean z);

    protected native void nativeSetByteArray(long j, long j2, byte[] bArr);

    protected native void nativeSetDouble(long j, long j2, double d);

    protected native void nativeSetFloat(long j, long j2, float f);

    protected native void nativeSetLink(long j, long j2, long j3);

    protected native void nativeSetLong(long j, long j2, long j3);

    protected native void nativeSetString(long j, long j2, String str);

    protected native void nativeSetTimestamp(long j, long j2, long j3);

    private CheckedRow(NativeContext context, Table parent, long nativePtr) {
        super(context, parent, nativePtr);
    }

    private CheckedRow(UncheckedRow row) {
        super(row);
        this.originalRow = row;
    }

    public static CheckedRow get(NativeContext context, Table table, long index) {
        return new CheckedRow(context, table, table.nativeGetRowPtr(table.getNativePtr(), index));
    }

    public static CheckedRow getFromRow(UncheckedRow row) {
        return new CheckedRow(row);
    }

    public boolean isNullLink(long columnIndex) {
        RealmFieldType columnType = getColumnType(columnIndex);
        if (columnType == RealmFieldType.OBJECT || columnType == RealmFieldType.LIST) {
            return super.isNullLink(columnIndex);
        }
        return false;
    }

    public boolean isNull(long columnIndex) {
        return super.isNull(columnIndex);
    }

    public void setNull(long columnIndex) {
        if (getColumnType(columnIndex) == RealmFieldType.BINARY) {
            super.setBinaryByteArray(columnIndex, null);
        } else {
            super.setNull(columnIndex);
        }
    }
}
