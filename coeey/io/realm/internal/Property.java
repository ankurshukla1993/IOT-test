package io.realm.internal;

import io.realm.RealmFieldType;
import java.util.Locale;

public class Property implements NativeObject {
    public static final boolean INDEXED = true;
    public static final boolean PRIMARY_KEY = true;
    public static final boolean REQUIRED = true;
    public static final int TYPE_ARRAY = 128;
    public static final int TYPE_BOOL = 1;
    public static final int TYPE_DATA = 3;
    public static final int TYPE_DATE = 4;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_INT = 0;
    public static final int TYPE_LINKING_OBJECTS = 8;
    public static final int TYPE_NULLABLE = 64;
    public static final int TYPE_OBJECT = 7;
    public static final int TYPE_REQUIRED = 0;
    public static final int TYPE_STRING = 2;
    private static final long nativeFinalizerPtr = nativeGetFinalizerPtr();
    private long nativePtr;

    private static native long nativeCreateComputedLinkProperty(String str, String str2, String str3);

    private static native long nativeCreatePersistedLinkProperty(String str, int i, String str2);

    private static native long nativeCreatePersistedProperty(String str, int i, boolean z, boolean z2);

    private static native long nativeGetColumnIndex(long j);

    private static native long nativeGetFinalizerPtr();

    private static native String nativeGetLinkedObjectName(long j);

    private static native int nativeGetType(long j);

    Property(String name, RealmFieldType type, boolean isPrimary, boolean isIndexed, boolean isRequired) {
        this(nativeCreatePersistedProperty(name, convertFromRealmFieldType(type, isRequired), isPrimary, isIndexed));
    }

    Property(String name, RealmFieldType type, String linkedClassName) {
        this(nativeCreatePersistedLinkProperty(name, convertFromRealmFieldType(type, false), linkedClassName));
    }

    Property(String name, String sourceClassName, String sourceFieldName) {
        this(nativeCreateComputedLinkProperty(name, sourceClassName, sourceFieldName));
    }

    Property(long nativePtr) {
        this.nativePtr = nativePtr;
        NativeContext.dummyContext.addReference(this);
    }

    private static int convertFromRealmFieldType(RealmFieldType fieldType, boolean isRequired) {
        int type;
        int requiredFlag = 0;
        switch (fieldType) {
            case OBJECT:
                return 71;
            case LIST:
                return 135;
            case LINKING_OBJECTS:
                return 136;
            case INTEGER:
                type = 0;
                break;
            case BOOLEAN:
                type = 1;
                break;
            case STRING:
                type = 2;
                break;
            case BINARY:
                type = 3;
                break;
            case DATE:
                type = 4;
                break;
            case FLOAT:
                type = 5;
                break;
            case DOUBLE:
                type = 6;
                break;
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported filed type: '%s'.", new Object[]{fieldType.name()}));
        }
        if (!isRequired) {
            requiredFlag = 64;
        }
        return type | requiredFlag;
    }

    private static RealmFieldType convertToRealmFieldType(int propertyType) {
        switch (propertyType & -65) {
            case 0:
                return RealmFieldType.INTEGER;
            case 1:
                return RealmFieldType.BOOLEAN;
            case 2:
                return RealmFieldType.STRING;
            case 3:
                return RealmFieldType.BINARY;
            case 4:
                return RealmFieldType.DATE;
            case 5:
                return RealmFieldType.FLOAT;
            case 6:
                return RealmFieldType.DOUBLE;
            case 7:
                return RealmFieldType.OBJECT;
            case 135:
                return RealmFieldType.LIST;
            case 136:
                return RealmFieldType.LINKING_OBJECTS;
            default:
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported property type: '%d'", new Object[]{Integer.valueOf(propertyType)}));
        }
    }

    public RealmFieldType getType() {
        return convertToRealmFieldType(nativeGetType(this.nativePtr));
    }

    public String getLinkedObjectName() {
        return nativeGetLinkedObjectName(this.nativePtr);
    }

    public long getColumnIndex() {
        return nativeGetColumnIndex(this.nativePtr);
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public long getNativeFinalizerPtr() {
        return nativeFinalizerPtr;
    }
}
