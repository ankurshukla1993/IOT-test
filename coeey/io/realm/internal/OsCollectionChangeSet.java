package io.realm.internal;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedCollectionChangeSet.Range;

public class OsCollectionChangeSet implements OrderedCollectionChangeSet, NativeObject {
    public static final int MAX_ARRAY_LENGTH = 2147483639;
    public static final int TYPE_DELETION = 0;
    public static final int TYPE_INSERTION = 1;
    public static final int TYPE_MODIFICATION = 2;
    private static long finalizerPtr = nativeGetFinalizerPtr();
    private final long nativePtr;

    private static native long nativeGetFinalizerPtr();

    private static native int[] nativeGetIndices(long j, int i);

    private static native int[] nativeGetRanges(long j, int i);

    public OsCollectionChangeSet(long nativePtr) {
        this.nativePtr = nativePtr;
        NativeContext.dummyContext.addReference(this);
    }

    public int[] getDeletions() {
        return nativeGetIndices(this.nativePtr, 0);
    }

    public int[] getInsertions() {
        return nativeGetIndices(this.nativePtr, 1);
    }

    public int[] getChanges() {
        return nativeGetIndices(this.nativePtr, 2);
    }

    public Range[] getDeletionRanges() {
        return longArrayToRangeArray(nativeGetRanges(this.nativePtr, 0));
    }

    public Range[] getInsertionRanges() {
        return longArrayToRangeArray(nativeGetRanges(this.nativePtr, 1));
    }

    public Range[] getChangeRanges() {
        return longArrayToRangeArray(nativeGetRanges(this.nativePtr, 2));
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return finalizerPtr;
    }

    private Range[] longArrayToRangeArray(int[] longArray) {
        if (longArray == null) {
            return new Range[0];
        }
        Range[] ranges = new Range[(longArray.length / 2)];
        for (int i = 0; i < ranges.length; i++) {
            ranges[i] = new Range(longArray[i * 2], longArray[(i * 2) + 1]);
        }
        return ranges;
    }
}
