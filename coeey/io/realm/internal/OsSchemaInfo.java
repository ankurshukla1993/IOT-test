package io.realm.internal;

import java.util.Collection;

public class OsSchemaInfo implements NativeObject {
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private long nativePtr;
    private final SharedRealm sharedRealm;

    private static native long nativeCreateFromList(long[] jArr);

    private static native long nativeGetFinalizerPtr();

    private static native long nativeGetObjectSchemaInfo(long j, String str);

    public OsSchemaInfo(Collection<OsObjectSchemaInfo> objectSchemaInfoList) {
        this.nativePtr = nativeCreateFromList(convertObjectSchemaInfoListToNativePointerArray(objectSchemaInfoList));
        NativeContext.dummyContext.addReference(this);
        this.sharedRealm = null;
    }

    OsSchemaInfo(long nativePtr, SharedRealm sharedRealm) {
        this.nativePtr = nativePtr;
        this.sharedRealm = sharedRealm;
    }

    private static long[] convertObjectSchemaInfoListToNativePointerArray(Collection<OsObjectSchemaInfo> objectSchemaInfoList) {
        long[] schemaNativePointers = new long[objectSchemaInfoList.size()];
        int i = 0;
        for (OsObjectSchemaInfo info : objectSchemaInfoList) {
            schemaNativePointers[i] = info.getNativePtr();
            i++;
        }
        return schemaNativePointers;
    }

    public OsObjectSchemaInfo getObjectSchemaInfo(String className) {
        return new OsObjectSchemaInfo(nativeGetObjectSchemaInfo(this.nativePtr, className));
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }
}
