package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.ArrayList;
import java.util.List;

public class OsObjectSchemaInfo implements NativeObject {
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private long nativePtr;

    public static class Builder {
        private String className;
        private List<Property> computedPropertyList = new ArrayList();
        private List<Property> persistedPropertyList = new ArrayList();

        public Builder(String className) {
            this.className = className;
        }

        public Builder addPersistedProperty(String name, RealmFieldType type, boolean isPrimaryKey, boolean isIndexed, boolean isRequired) {
            this.persistedPropertyList.add(new Property(name, type, isPrimaryKey, isIndexed, isRequired));
            return this;
        }

        public Builder addPersistedLinkProperty(String name, RealmFieldType type, String linkedClassName) {
            this.persistedPropertyList.add(new Property(name, type, linkedClassName));
            return this;
        }

        public Builder addComputedLinkProperty(String name, String targetClassname, String targetFieldName) {
            this.computedPropertyList.add(new Property(name, targetClassname, targetFieldName));
            return this;
        }

        public OsObjectSchemaInfo build() {
            OsObjectSchemaInfo info = new OsObjectSchemaInfo(this.className);
            for (Property property : this.persistedPropertyList) {
                OsObjectSchemaInfo.nativeAddProperty(info.nativePtr, property.getNativePtr(), false);
            }
            for (Property property2 : this.computedPropertyList) {
                OsObjectSchemaInfo.nativeAddProperty(info.nativePtr, property2.getNativePtr(), true);
            }
            return info;
        }
    }

    private static native void nativeAddProperty(long j, long j2, boolean z);

    private static native long nativeCreateRealmObjectSchema(String str);

    private static native String nativeGetClassName(long j);

    private static native long nativeGetFinalizerPtr();

    private static native long nativeGetProperty(long j, String str);

    private OsObjectSchemaInfo(String className) {
        this(nativeCreateRealmObjectSchema(className));
    }

    OsObjectSchemaInfo(long nativePtr) {
        this.nativePtr = nativePtr;
        NativeContext.dummyContext.addReference(this);
    }

    public String getClassName() {
        return nativeGetClassName(this.nativePtr);
    }

    public Property getProperty(String propertyName) {
        return new Property(nativeGetProperty(this.nativePtr, propertyName));
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }
}
