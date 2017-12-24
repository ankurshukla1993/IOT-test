package io.realm.internal;

import humanize.util.Constants;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;
import java.util.Date;
import javax.annotation.Nullable;

public class Table implements TableSchema, NativeObject {
    public static final int CLASS_NAME_MAX_LENGTH = (63 - TABLE_PREFIX.length());
    public static final long INFINITE = -1;
    public static final boolean NOT_NULLABLE = false;
    public static final int NO_MATCH = -1;
    public static final long NO_PRIMARY_KEY = -2;
    public static final boolean NULLABLE = true;
    private static final long PRIMARY_KEY_CLASS_COLUMN_INDEX = 0;
    private static final String PRIMARY_KEY_CLASS_COLUMN_NAME = "pk_table";
    private static final long PRIMARY_KEY_FIELD_COLUMN_INDEX = 1;
    private static final String PRIMARY_KEY_FIELD_COLUMN_NAME = "pk_property";
    private static final String PRIMARY_KEY_TABLE_NAME = "pk";
    private static final int TABLE_NAME_MAX_LENGTH = 63;
    private static final String TABLE_PREFIX = Util.getTablePrefix();
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private long cachedPrimaryKeyColumnIndex;
    private final NativeContext context;
    private final long nativePtr;
    private final SharedRealm sharedRealm;

    private native long nativeAddColumn(long j, int i, String str, boolean z);

    private native long nativeAddColumnLink(long j, int i, String str, long j2);

    private native void nativeAddSearchIndex(long j, long j2);

    private native void nativeClear(long j);

    private native void nativeConvertColumnToNotNullable(long j, long j2, boolean z);

    private native void nativeConvertColumnToNullable(long j, long j2, boolean z);

    private native long nativeCountDouble(long j, long j2, double d);

    private native long nativeCountFloat(long j, long j2, float f);

    private native long nativeCountLong(long j, long j2, long j3);

    private native long nativeCountString(long j, long j2, String str);

    private native long nativeFindFirstBool(long j, long j2, boolean z);

    private native long nativeFindFirstDouble(long j, long j2, double d);

    private native long nativeFindFirstFloat(long j, long j2, float f);

    public static native long nativeFindFirstInt(long j, long j2, long j3);

    public static native long nativeFindFirstNull(long j, long j2);

    public static native long nativeFindFirstString(long j, long j2, String str);

    private native long nativeFindFirstTimestamp(long j, long j2, long j3);

    private native boolean nativeGetBoolean(long j, long j2, long j3);

    private native byte[] nativeGetByteArray(long j, long j2, long j3);

    private native long nativeGetColumnCount(long j);

    private native long nativeGetColumnIndex(long j, String str);

    private native String nativeGetColumnName(long j, long j2);

    private native int nativeGetColumnType(long j, long j2);

    private native double nativeGetDouble(long j, long j2, long j3);

    private static native long nativeGetFinalizerPtr();

    private native float nativeGetFloat(long j, long j2, long j3);

    private native long nativeGetLink(long j, long j2, long j3);

    private native long nativeGetLinkTarget(long j, long j2);

    private native long nativeGetLong(long j, long j2, long j3);

    private native String nativeGetName(long j);

    private native long nativeGetSortedViewMulti(long j, long[] jArr, boolean[] zArr);

    private native String nativeGetString(long j, long j2, long j3);

    private native long nativeGetTimestamp(long j, long j2, long j3);

    private native boolean nativeHasSameSchema(long j, long j2);

    private native boolean nativeHasSearchIndex(long j, long j2);

    public static native void nativeIncrementLong(long j, long j2, long j3, long j4);

    private static native void nativeInsertColumn(long j, long j2, int i, String str);

    private native boolean nativeIsColumnNullable(long j, long j2);

    private native boolean nativeIsNull(long j, long j2, long j3);

    private native boolean nativeIsNullLink(long j, long j2, long j3);

    private native boolean nativeIsValid(long j);

    private native long nativeLowerBoundInt(long j, long j2, long j3);

    private static native boolean nativeMigratePrimaryKeyTableIfNeeded(long j, long j2);

    private native void nativeMoveLastOver(long j, long j2);

    public static native void nativeNullifyLink(long j, long j2, long j3);

    private static native boolean nativePrimaryKeyTableNeedsMigration(long j);

    private native void nativeRemoveColumn(long j, long j2);

    private native void nativeRemoveSearchIndex(long j, long j2);

    private native void nativeRenameColumn(long j, long j2, String str);

    public static native void nativeSetBoolean(long j, long j2, long j3, boolean z, boolean z2);

    public static native void nativeSetByteArray(long j, long j2, long j3, byte[] bArr, boolean z);

    public static native void nativeSetDouble(long j, long j2, long j3, double d, boolean z);

    public static native void nativeSetFloat(long j, long j2, long j3, float f, boolean z);

    public static native void nativeSetLink(long j, long j2, long j3, long j4, boolean z);

    public static native void nativeSetLong(long j, long j2, long j3, long j4, boolean z);

    public static native void nativeSetLongUnique(long j, long j2, long j3, long j4);

    public static native void nativeSetNull(long j, long j2, long j3, boolean z);

    public static native void nativeSetNullUnique(long j, long j2, long j3);

    private native long nativeSetPrimaryKey(long j, long j2, @Nullable String str);

    public static native void nativeSetString(long j, long j2, long j3, String str, boolean z);

    public static native void nativeSetStringUnique(long j, long j2, long j3, String str);

    public static native void nativeSetTimestamp(long j, long j2, long j3, long j4, boolean z);

    private native long nativeSize(long j);

    private native String nativeToJson(long j);

    private native long nativeUpperBoundInt(long j, long j2, long j3);

    private native long nativeVersion(long j);

    private native long nativeWhere(long j);

    native long nativeGetRowPtr(long j, long j2);

    Table(Table parent, long nativePointer) {
        this(parent.sharedRealm, nativePointer);
    }

    Table(SharedRealm sharedRealm, long nativePointer) {
        this.cachedPrimaryKeyColumnIndex = -1;
        this.context = sharedRealm.context;
        this.sharedRealm = sharedRealm;
        this.nativePtr = nativePointer;
        this.context.addReference(this);
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    public Table getTable() {
        return this;
    }

    public boolean isValid() {
        return this.nativePtr != 0 && nativeIsValid(this.nativePtr);
    }

    private void verifyColumnName(String name) {
        if (name.length() > 63) {
            throw new IllegalArgumentException("Column names are currently limited to max 63 characters.");
        }
    }

    public long addColumn(RealmFieldType type, String name, boolean isNullable) {
        verifyColumnName(name);
        return nativeAddColumn(this.nativePtr, type.getNativeValue(), name, isNullable);
    }

    public long addColumn(RealmFieldType type, String name) {
        return addColumn(type, name, false);
    }

    public long addColumnLink(RealmFieldType type, String name, Table table) {
        verifyColumnName(name);
        return nativeAddColumnLink(this.nativePtr, type.getNativeValue(), name, table.nativePtr);
    }

    public void removeColumn(long columnIndex) {
        long oldPkColumnIndex = getPrimaryKey();
        nativeRemoveColumn(this.nativePtr, columnIndex);
        if (oldPkColumnIndex < 0) {
            return;
        }
        if (oldPkColumnIndex == columnIndex) {
            setPrimaryKey(null);
        } else if (oldPkColumnIndex > columnIndex) {
            invalidateCachedPrimaryKeyIndex();
        }
    }

    public void renameColumn(long columnIndex, String newName) {
        verifyColumnName(newName);
        String oldName = nativeGetColumnName(this.nativePtr, columnIndex);
        long oldPkColumnIndex = getPrimaryKey();
        nativeRenameColumn(this.nativePtr, columnIndex, newName);
        if (oldPkColumnIndex == columnIndex) {
            try {
                Table pkTable = getPrimaryKeyTable();
                if (pkTable == null) {
                    throw new IllegalStateException("Table is not created from a SharedRealm, primary key is not available");
                }
                long pkRowIndex = pkTable.findFirstString(0, getClassName());
                if (pkRowIndex != -1) {
                    nativeSetString(pkTable.nativePtr, 1, pkRowIndex, newName, false);
                    return;
                }
                throw new IllegalStateException("Non-existent PrimaryKey column cannot be renamed");
            } catch (Throwable e) {
                nativeRenameColumn(this.nativePtr, columnIndex, oldName);
                throw new RuntimeException(e);
            }
        }
    }

    public void insertColumn(long columnIndex, RealmFieldType type, String name) {
        verifyColumnName(name);
        nativeInsertColumn(this.nativePtr, columnIndex, type.getNativeValue(), name);
    }

    public boolean isColumnNullable(long columnIndex) {
        return nativeIsColumnNullable(this.nativePtr, columnIndex);
    }

    public void convertColumnToNullable(long columnIndex) {
        nativeConvertColumnToNullable(this.nativePtr, columnIndex, isPrimaryKey(columnIndex));
    }

    public void convertColumnToNotNullable(long columnIndex) {
        nativeConvertColumnToNotNullable(this.nativePtr, columnIndex, isPrimaryKey(columnIndex));
    }

    public long size() {
        return nativeSize(this.nativePtr);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        checkImmutable();
        nativeClear(this.nativePtr);
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

    public void moveLastOver(long rowIndex) {
        checkImmutable();
        nativeMoveLastOver(this.nativePtr, rowIndex);
    }

    private boolean isPrimaryKeyColumn(long columnIndex) {
        return columnIndex == getPrimaryKey();
    }

    public long getPrimaryKey() {
        if (this.cachedPrimaryKeyColumnIndex >= 0 || this.cachedPrimaryKeyColumnIndex == -2) {
            return this.cachedPrimaryKeyColumnIndex;
        }
        Table pkTable = getPrimaryKeyTable();
        if (pkTable == null) {
            return -2;
        }
        long rowIndex = pkTable.findFirstString(0, getClassName());
        if (rowIndex != -1) {
            this.cachedPrimaryKeyColumnIndex = getColumnIndex(pkTable.getUncheckedRow(rowIndex).getString(1));
        } else {
            this.cachedPrimaryKeyColumnIndex = -2;
        }
        return this.cachedPrimaryKeyColumnIndex;
    }

    private boolean isPrimaryKey(long columnIndex) {
        return columnIndex >= 0 && columnIndex == getPrimaryKey();
    }

    public boolean hasPrimaryKey() {
        return getPrimaryKey() >= 0;
    }

    void checkStringValueIsLegal(long columnIndex, long rowToUpdate, String value) {
        if (isPrimaryKey(columnIndex)) {
            long rowIndex = findFirstString(columnIndex, value);
            if (rowIndex != rowToUpdate && rowIndex != -1) {
                throwDuplicatePrimaryKeyException(value);
            }
        }
    }

    void checkIntValueIsLegal(long columnIndex, long rowToUpdate, long value) {
        if (isPrimaryKeyColumn(columnIndex)) {
            long rowIndex = findFirstLong(columnIndex, value);
            if (rowIndex != rowToUpdate && rowIndex != -1) {
                throwDuplicatePrimaryKeyException(Long.valueOf(value));
            }
        }
    }

    void checkDuplicatedNullForPrimaryKeyValue(long columnIndex, long rowToUpdate) {
        if (isPrimaryKeyColumn(columnIndex)) {
            switch (1.$SwitchMap$io$realm$RealmFieldType[getColumnType(columnIndex).ordinal()]) {
                case 1:
                case 2:
                    long rowIndex = findFirstNull(columnIndex);
                    if (rowIndex != rowToUpdate && rowIndex != -1) {
                        throwDuplicatePrimaryKeyException("null");
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public static void throwDuplicatePrimaryKeyException(Object value) {
        throw new RealmPrimaryKeyConstraintException("Value already exists: " + value);
    }

    SharedRealm getSharedRealm() {
        return this.sharedRealm;
    }

    public long getLong(long columnIndex, long rowIndex) {
        return nativeGetLong(this.nativePtr, columnIndex, rowIndex);
    }

    public boolean getBoolean(long columnIndex, long rowIndex) {
        return nativeGetBoolean(this.nativePtr, columnIndex, rowIndex);
    }

    public float getFloat(long columnIndex, long rowIndex) {
        return nativeGetFloat(this.nativePtr, columnIndex, rowIndex);
    }

    public double getDouble(long columnIndex, long rowIndex) {
        return nativeGetDouble(this.nativePtr, columnIndex, rowIndex);
    }

    public Date getDate(long columnIndex, long rowIndex) {
        return new Date(nativeGetTimestamp(this.nativePtr, columnIndex, rowIndex));
    }

    public String getString(long columnIndex, long rowIndex) {
        return nativeGetString(this.nativePtr, columnIndex, rowIndex);
    }

    public byte[] getBinaryByteArray(long columnIndex, long rowIndex) {
        return nativeGetByteArray(this.nativePtr, columnIndex, rowIndex);
    }

    public long getLink(long columnIndex, long rowIndex) {
        return nativeGetLink(this.nativePtr, columnIndex, rowIndex);
    }

    public Table getLinkTarget(long columnIndex) {
        return new Table(this.sharedRealm, nativeGetLinkTarget(this.nativePtr, columnIndex));
    }

    public boolean isNull(long columnIndex, long rowIndex) {
        return nativeIsNull(this.nativePtr, columnIndex, rowIndex);
    }

    public UncheckedRow getUncheckedRow(long index) {
        return UncheckedRow.getByRowIndex(this.context, this, index);
    }

    public UncheckedRow getUncheckedRowByPointer(long nativeRowPointer) {
        return UncheckedRow.getByRowPointer(this.context, this, nativeRowPointer);
    }

    public CheckedRow getCheckedRow(long index) {
        return CheckedRow.get(this.context, this, index);
    }

    public void setLong(long columnIndex, long rowIndex, long value, boolean isDefault) {
        checkImmutable();
        checkIntValueIsLegal(columnIndex, rowIndex, value);
        nativeSetLong(this.nativePtr, columnIndex, rowIndex, value, isDefault);
    }

    public void incrementLong(long columnIndex, long rowIndex, long value) {
        checkImmutable();
        nativeIncrementLong(this.nativePtr, columnIndex, rowIndex, value);
    }

    public void setBoolean(long columnIndex, long rowIndex, boolean value, boolean isDefault) {
        checkImmutable();
        nativeSetBoolean(this.nativePtr, columnIndex, rowIndex, value, isDefault);
    }

    public void setFloat(long columnIndex, long rowIndex, float value, boolean isDefault) {
        checkImmutable();
        nativeSetFloat(this.nativePtr, columnIndex, rowIndex, value, isDefault);
    }

    public void setDouble(long columnIndex, long rowIndex, double value, boolean isDefault) {
        checkImmutable();
        nativeSetDouble(this.nativePtr, columnIndex, rowIndex, value, isDefault);
    }

    public void setDate(long columnIndex, long rowIndex, Date date, boolean isDefault) {
        if (date == null) {
            throw new IllegalArgumentException("Null Date is not allowed.");
        }
        checkImmutable();
        nativeSetTimestamp(this.nativePtr, columnIndex, rowIndex, date.getTime(), isDefault);
    }

    public void setString(long columnIndex, long rowIndex, String value, boolean isDefault) {
        checkImmutable();
        if (value == null) {
            checkDuplicatedNullForPrimaryKeyValue(columnIndex, rowIndex);
            nativeSetNull(this.nativePtr, columnIndex, rowIndex, isDefault);
            return;
        }
        checkStringValueIsLegal(columnIndex, rowIndex, value);
        nativeSetString(this.nativePtr, columnIndex, rowIndex, value, isDefault);
    }

    public void setBinaryByteArray(long columnIndex, long rowIndex, byte[] data, boolean isDefault) {
        checkImmutable();
        nativeSetByteArray(this.nativePtr, columnIndex, rowIndex, data, isDefault);
    }

    public void setLink(long columnIndex, long rowIndex, long value, boolean isDefault) {
        checkImmutable();
        nativeSetLink(this.nativePtr, columnIndex, rowIndex, value, isDefault);
    }

    public void setNull(long columnIndex, long rowIndex, boolean isDefault) {
        checkImmutable();
        checkDuplicatedNullForPrimaryKeyValue(columnIndex, rowIndex);
        nativeSetNull(this.nativePtr, columnIndex, rowIndex, isDefault);
    }

    public void addSearchIndex(long columnIndex) {
        checkImmutable();
        nativeAddSearchIndex(this.nativePtr, columnIndex);
    }

    public void removeSearchIndex(long columnIndex) {
        checkImmutable();
        nativeRemoveSearchIndex(this.nativePtr, columnIndex);
    }

    public void setPrimaryKey(@Nullable String columnName) {
        Table pkTable = getPrimaryKeyTable();
        if (pkTable == null) {
            throw new RealmException("Primary keys are only supported if Table is part of a Group");
        }
        this.cachedPrimaryKeyColumnIndex = nativeSetPrimaryKey(pkTable.nativePtr, this.nativePtr, columnName);
    }

    public void setPrimaryKey(long columnIndex) {
        setPrimaryKey(nativeGetColumnName(this.nativePtr, columnIndex));
    }

    private Table getPrimaryKeyTable() {
        if (this.sharedRealm == null) {
            return null;
        }
        if (!this.sharedRealm.hasTable(PRIMARY_KEY_TABLE_NAME)) {
            this.sharedRealm.createTable(PRIMARY_KEY_TABLE_NAME);
        }
        Table pkTable = this.sharedRealm.getTable(PRIMARY_KEY_TABLE_NAME);
        if (pkTable.getColumnCount() != 0) {
            return pkTable;
        }
        checkImmutable();
        pkTable.addSearchIndex(pkTable.addColumn(RealmFieldType.STRING, PRIMARY_KEY_CLASS_COLUMN_NAME));
        pkTable.addColumn(RealmFieldType.STRING, PRIMARY_KEY_FIELD_COLUMN_NAME);
        return pkTable;
    }

    private void invalidateCachedPrimaryKeyIndex() {
        this.cachedPrimaryKeyColumnIndex = -1;
    }

    public static boolean migratePrimaryKeyTableIfNeeded(SharedRealm sharedRealm) {
        if (sharedRealm == null || !sharedRealm.isInTransaction()) {
            throwImmutable();
        }
        if (!sharedRealm.hasTable(PRIMARY_KEY_TABLE_NAME)) {
            return false;
        }
        return nativeMigratePrimaryKeyTableIfNeeded(sharedRealm.getGroupNative(), sharedRealm.getTable(PRIMARY_KEY_TABLE_NAME).nativePtr);
    }

    public static boolean primaryKeyTableNeedsMigration(SharedRealm sharedRealm) {
        if (sharedRealm.hasTable(PRIMARY_KEY_TABLE_NAME)) {
            return nativePrimaryKeyTableNeedsMigration(sharedRealm.getTable(PRIMARY_KEY_TABLE_NAME).nativePtr);
        }
        return false;
    }

    public boolean hasSearchIndex(long columnIndex) {
        return nativeHasSearchIndex(this.nativePtr, columnIndex);
    }

    public boolean isNullLink(long columnIndex, long rowIndex) {
        return nativeIsNullLink(this.nativePtr, columnIndex, rowIndex);
    }

    public void nullifyLink(long columnIndex, long rowIndex) {
        nativeNullifyLink(this.nativePtr, columnIndex, rowIndex);
    }

    boolean isImmutable() {
        return (this.sharedRealm == null || this.sharedRealm.isInTransaction()) ? false : true;
    }

    void checkImmutable() {
        if (isImmutable()) {
            throwImmutable();
        }
    }

    public long count(long columnIndex, long value) {
        return nativeCountLong(this.nativePtr, columnIndex, value);
    }

    public long count(long columnIndex, float value) {
        return nativeCountFloat(this.nativePtr, columnIndex, value);
    }

    public long count(long columnIndex, double value) {
        return nativeCountDouble(this.nativePtr, columnIndex, value);
    }

    public long count(long columnIndex, String value) {
        return nativeCountString(this.nativePtr, columnIndex, value);
    }

    public TableQuery where() {
        return new TableQuery(this.context, this, nativeWhere(this.nativePtr));
    }

    public long findFirstLong(long columnIndex, long value) {
        return nativeFindFirstInt(this.nativePtr, columnIndex, value);
    }

    public long findFirstBoolean(long columnIndex, boolean value) {
        return nativeFindFirstBool(this.nativePtr, columnIndex, value);
    }

    public long findFirstFloat(long columnIndex, float value) {
        return nativeFindFirstFloat(this.nativePtr, columnIndex, value);
    }

    public long findFirstDouble(long columnIndex, double value) {
        return nativeFindFirstDouble(this.nativePtr, columnIndex, value);
    }

    public long findFirstDate(long columnIndex, Date date) {
        if (date == null) {
            throw new IllegalArgumentException("null is not supported");
        }
        return nativeFindFirstTimestamp(this.nativePtr, columnIndex, date.getTime());
    }

    public long findFirstString(long columnIndex, String value) {
        if (value != null) {
            return nativeFindFirstString(this.nativePtr, columnIndex, value);
        }
        throw new IllegalArgumentException("null is not supported");
    }

    public long findFirstNull(long columnIndex) {
        return nativeFindFirstNull(this.nativePtr, columnIndex);
    }

    public long lowerBoundLong(long columnIndex, long value) {
        return nativeLowerBoundInt(this.nativePtr, columnIndex, value);
    }

    public long upperBoundLong(long columnIndex, long value) {
        return nativeUpperBoundInt(this.nativePtr, columnIndex, value);
    }

    @Nullable
    public String getName() {
        return nativeGetName(this.nativePtr);
    }

    @Nullable
    public String getClassName() {
        return getClassNameForTable(getName());
    }

    public String toJson() {
        return nativeToJson(this.nativePtr);
    }

    public String toString() {
        long columnCount = getColumnCount();
        String name = getName();
        StringBuilder stringBuilder = new StringBuilder("The Table ");
        if (!(name == null || name.isEmpty())) {
            stringBuilder.append(getName());
            stringBuilder.append(Constants.SPACE);
        }
        if (hasPrimaryKey()) {
            stringBuilder.append("has '").append(getColumnName(getPrimaryKey())).append("' field as a PrimaryKey, and ");
        }
        stringBuilder.append("contains ");
        stringBuilder.append(columnCount);
        stringBuilder.append(" columns: ");
        for (int i = 0; ((long) i) < columnCount; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(getColumnName((long) i));
        }
        stringBuilder.append(".");
        stringBuilder.append(" And ");
        stringBuilder.append(size());
        stringBuilder.append(" rows.");
        return stringBuilder.toString();
    }

    private static void throwImmutable() {
        throw new IllegalStateException("Cannot modify managed objects outside of a write transaction.");
    }

    public boolean hasSameSchema(Table table) {
        if (table != null) {
            return nativeHasSameSchema(this.nativePtr, table.nativePtr);
        }
        throw new IllegalArgumentException("The argument cannot be null");
    }

    public static boolean isModelTable(String tableName) {
        return tableName.startsWith(TABLE_PREFIX);
    }

    public long getVersion() {
        return nativeVersion(this.nativePtr);
    }

    @Nullable
    public static String getClassNameForTable(@Nullable String name) {
        if (name == null) {
            return null;
        }
        return name.startsWith(TABLE_PREFIX) ? name.substring(TABLE_PREFIX.length()) : name;
    }

    public static String getTableNameForClass(String name) {
        if (name == null) {
            return null;
        }
        return !name.startsWith(TABLE_PREFIX) ? TABLE_PREFIX + name : name;
    }
}
