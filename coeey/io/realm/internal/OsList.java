package io.realm.internal;

public class OsList implements NativeObject {
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private final NativeContext context;
    private final long nativePtr;
    private final Table targetTable;

    private static native void nativeAddRow(long j, long j2);

    private static native long[] nativeCreate(long j, long j2, long j3);

    private static native void nativeDelete(long j, long j2);

    private static native void nativeDeleteAll(long j);

    private static native long nativeGetFinalizerPtr();

    private static native long nativeGetQuery(long j);

    private static native long nativeGetRow(long j, long j2);

    private static native void nativeInsertRow(long j, long j2, long j3);

    private static native boolean nativeIsValid(long j);

    private static native void nativeMove(long j, long j2, long j3);

    private static native void nativeRemove(long j, long j2);

    private static native void nativeRemoveAll(long j);

    private static native void nativeSetRow(long j, long j2, long j3);

    private static native long nativeSize(long j);

    public OsList(UncheckedRow row, long columnIndex) {
        SharedRealm sharedRealm = row.getTable().getSharedRealm();
        long[] ptrs = nativeCreate(sharedRealm.getNativePtr(), row.getNativePtr(), columnIndex);
        this.nativePtr = ptrs[0];
        this.context = sharedRealm.context;
        this.context.addReference(this);
        this.targetTable = new Table(sharedRealm, ptrs[1]);
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }

    public UncheckedRow getUncheckedRow(long index) {
        return this.targetTable.getUncheckedRowByPointer(nativeGetRow(this.nativePtr, index));
    }

    public void addRow(long targetRowIndex) {
        nativeAddRow(this.nativePtr, targetRowIndex);
    }

    public void insertRow(long pos, long targetRowIndex) {
        nativeInsertRow(this.nativePtr, pos, targetRowIndex);
    }

    public void setRow(long pos, long targetRowIndex) {
        nativeSetRow(this.nativePtr, pos, targetRowIndex);
    }

    public void move(long sourceIndex, long targetIndex) {
        nativeMove(this.nativePtr, sourceIndex, targetIndex);
    }

    public void remove(long index) {
        nativeRemove(this.nativePtr, index);
    }

    public void removeAll() {
        nativeRemoveAll(this.nativePtr);
    }

    public long size() {
        return nativeSize(this.nativePtr);
    }

    public boolean isEmpty() {
        return nativeSize(this.nativePtr) <= 0;
    }

    public TableQuery getQuery() {
        return new TableQuery(this.context, this.targetTable, nativeGetQuery(this.nativePtr));
    }

    public boolean isValid() {
        return nativeIsValid(this.nativePtr);
    }

    public void delete(long index) {
        nativeDelete(this.nativePtr, index);
    }

    public void deleteAll() {
        nativeDeleteAll(this.nativePtr);
    }

    public Table getTargetTable() {
        return this.targetTable;
    }
}
