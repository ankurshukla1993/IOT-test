package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.Date;
import javax.annotation.Nullable;

public class UncheckedRow implements NativeObject, Row {
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private final NativeContext context;
    private final long nativePtr;
    private final Table parent;

    private static native long nativeGetFinalizerPtr();

    protected native boolean nativeGetBoolean(long j, long j2);

    protected native byte[] nativeGetByteArray(long j, long j2);

    protected native long nativeGetColumnCount(long j);

    protected native long nativeGetColumnIndex(long j, String str);

    protected native String nativeGetColumnName(long j, long j2);

    protected native int nativeGetColumnType(long j, long j2);

    protected native double nativeGetDouble(long j, long j2);

    protected native float nativeGetFloat(long j, long j2);

    protected native long nativeGetIndex(long j);

    protected native long nativeGetLink(long j, long j2);

    protected native long nativeGetLong(long j, long j2);

    protected native String nativeGetString(long j, long j2);

    protected native long nativeGetTimestamp(long j, long j2);

    protected native boolean nativeHasColumn(long j, String str);

    protected native boolean nativeIsAttached(long j);

    protected native boolean nativeIsNull(long j, long j2);

    protected native boolean nativeIsNullLink(long j, long j2);

    protected native void nativeNullifyLink(long j, long j2);

    protected native void nativeSetBoolean(long j, long j2, boolean z);

    protected native void nativeSetByteArray(long j, long j2, @Nullable byte[] bArr);

    protected native void nativeSetDouble(long j, long j2, double d);

    protected native void nativeSetFloat(long j, long j2, float f);

    protected native void nativeSetLink(long j, long j2, long j3);

    protected native void nativeSetLong(long j, long j2, long j3);

    protected native void nativeSetNull(long j, long j2);

    protected native void nativeSetString(long j, long j2, String str);

    protected native void nativeSetTimestamp(long j, long j2, long j3);

    UncheckedRow(NativeContext context, Table parent, long nativePtr) {
        this.context = context;
        this.parent = parent;
        this.nativePtr = nativePtr;
        context.addReference(this);
    }

    UncheckedRow(UncheckedRow row) {
        this.context = row.context;
        this.parent = row.parent;
        this.nativePtr = row.nativePtr;
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    static UncheckedRow getByRowIndex(NativeContext context, Table table, long index) {
        return new UncheckedRow(context, table, table.nativeGetRowPtr(table.getNativePtr(), index));
    }

    static UncheckedRow getByRowPointer(NativeContext context, Table table, long nativeRowPointer) {
        return new UncheckedRow(context, table, nativeRowPointer);
    }

    public long getColumnCount() {
        return nativeGetColumnCount(this.nativePtr);
    }

    public String getColumnName(long columnIndex) {
        return nativeGetColumnName(this.nativePtr, columnIndex);
    }

    public long getColumnIndex(String columnName) {
        if (columnName != null) {
            return nativeGetColumnIndex(this.nativePtr, columnName);
        }
        throw new IllegalArgumentException("Column name can not be null.");
    }

    public RealmFieldType getColumnType(long columnIndex) {
        return RealmFieldType.fromNativeValue(nativeGetColumnType(this.nativePtr, columnIndex));
    }

    public Table getTable() {
        return this.parent;
    }

    public long getIndex() {
        return nativeGetIndex(this.nativePtr);
    }

    public long getLong(long columnIndex) {
        return nativeGetLong(this.nativePtr, columnIndex);
    }

    public boolean getBoolean(long columnIndex) {
        return nativeGetBoolean(this.nativePtr, columnIndex);
    }

    public float getFloat(long columnIndex) {
        return nativeGetFloat(this.nativePtr, columnIndex);
    }

    public double getDouble(long columnIndex) {
        return nativeGetDouble(this.nativePtr, columnIndex);
    }

    public Date getDate(long columnIndex) {
        return new Date(nativeGetTimestamp(this.nativePtr, columnIndex));
    }

    public String getString(long columnIndex) {
        return nativeGetString(this.nativePtr, columnIndex);
    }

    public byte[] getBinaryByteArray(long columnIndex) {
        return nativeGetByteArray(this.nativePtr, columnIndex);
    }

    public long getLink(long columnIndex) {
        return nativeGetLink(this.nativePtr, columnIndex);
    }

    public boolean isNullLink(long columnIndex) {
        return nativeIsNullLink(this.nativePtr, columnIndex);
    }

    public OsList getLinkList(long columnIndex) {
        return new OsList(this, columnIndex);
    }

    public void setLong(long columnIndex, long value) {
        this.parent.checkImmutable();
        getTable().checkIntValueIsLegal(columnIndex, getIndex(), value);
        nativeSetLong(this.nativePtr, columnIndex, value);
    }

    public void setBoolean(long columnIndex, boolean value) {
        this.parent.checkImmutable();
        nativeSetBoolean(this.nativePtr, columnIndex, value);
    }

    public void setFloat(long columnIndex, float value) {
        this.parent.checkImmutable();
        nativeSetFloat(this.nativePtr, columnIndex, value);
    }

    public void setDouble(long columnIndex, double value) {
        this.parent.checkImmutable();
        nativeSetDouble(this.nativePtr, columnIndex, value);
    }

    public void setDate(long columnIndex, Date date) {
        this.parent.checkImmutable();
        if (date == null) {
            throw new IllegalArgumentException("Null Date is not allowed.");
        }
        nativeSetTimestamp(this.nativePtr, columnIndex, date.getTime());
    }

    public void setString(long columnIndex, @Nullable String value) {
        this.parent.checkImmutable();
        if (value == null) {
            getTable().checkDuplicatedNullForPrimaryKeyValue(columnIndex, getIndex());
            nativeSetNull(this.nativePtr, columnIndex);
            return;
        }
        getTable().checkStringValueIsLegal(columnIndex, getIndex(), value);
        nativeSetString(this.nativePtr, columnIndex, value);
    }

    public void setBinaryByteArray(long columnIndex, @Nullable byte[] data) {
        this.parent.checkImmutable();
        nativeSetByteArray(this.nativePtr, columnIndex, data);
    }

    public void setLink(long columnIndex, long value) {
        this.parent.checkImmutable();
        nativeSetLink(this.nativePtr, columnIndex, value);
    }

    public void nullifyLink(long columnIndex) {
        this.parent.checkImmutable();
        nativeNullifyLink(this.nativePtr, columnIndex);
    }

    public boolean isNull(long columnIndex) {
        return nativeIsNull(this.nativePtr, columnIndex);
    }

    public void setNull(long columnIndex) {
        this.parent.checkImmutable();
        getTable().checkDuplicatedNullForPrimaryKeyValue(columnIndex, getIndex());
        nativeSetNull(this.nativePtr, columnIndex);
    }

    public CheckedRow convertToChecked() {
        return CheckedRow.getFromRow(this);
    }

    public boolean isAttached() {
        return this.nativePtr != 0 && nativeIsAttached(this.nativePtr);
    }

    public void checkIfAttached() {
        if (!isAttached()) {
            throw new IllegalStateException("Object is no longer managed by Realm. Has it been deleted?");
        }
    }

    public boolean hasColumn(String fieldName) {
        return nativeHasColumn(this.nativePtr, fieldName);
    }
}
