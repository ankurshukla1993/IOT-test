package com.google.protobuf;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.MessageLite.Builder;
import com.google.protobuf.WireFormat.FieldType;
import com.google.protobuf.WireFormat.JavaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class FieldSet {
    private static final FieldSet DEFAULT_INSTANCE = new FieldSet(true);
    private final SmallSortedMap fields = SmallSortedMap.newFieldMap(16);
    private boolean hasLazyField = false;
    private boolean isImmutable;

    public interface FieldDescriptorLite extends Comparable {
        EnumLiteMap getEnumType();

        JavaType getLiteJavaType();

        FieldType getLiteType();

        int getNumber();

        Builder internalMergeFrom(Builder builder, MessageLite messageLite);

        boolean isPacked();

        boolean isRepeated();
    }

    private FieldSet() {
    }

    private FieldSet(boolean z) {
        makeImmutable();
    }

    private void cloneFieldEntry(Map map, Entry entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof LazyField) {
            map.put(fieldDescriptorLite, ((LazyField) value).getValue());
        } else {
            map.put(fieldDescriptorLite, value);
        }
    }

    private Object cloneIfMutable(Object obj) {
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        Object obj2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, obj2, 0, bArr.length);
        return obj2;
    }

    private static int computeElementSize(FieldType fieldType, int i, Object obj) {
        int computeTagSize = CodedOutputStream.computeTagSize(i);
        if (fieldType == FieldType.GROUP) {
            computeTagSize *= 2;
        }
        return computeTagSize + computeElementSizeNoTag(fieldType, obj);
    }

    private static int computeElementSizeNoTag(FieldType fieldType, Object obj) {
        switch (fieldType) {
            case DOUBLE:
                return CodedOutputStream.computeDoubleSizeNoTag(((Double) obj).doubleValue());
            case FLOAT:
                return CodedOutputStream.computeFloatSizeNoTag(((Float) obj).floatValue());
            case INT64:
                return CodedOutputStream.computeInt64SizeNoTag(((Long) obj).longValue());
            case UINT64:
                return CodedOutputStream.computeUInt64SizeNoTag(((Long) obj).longValue());
            case INT32:
                return CodedOutputStream.computeInt32SizeNoTag(((Integer) obj).intValue());
            case FIXED64:
                return CodedOutputStream.computeFixed64SizeNoTag(((Long) obj).longValue());
            case FIXED32:
                return CodedOutputStream.computeFixed32SizeNoTag(((Integer) obj).intValue());
            case BOOL:
                return CodedOutputStream.computeBoolSizeNoTag(((Boolean) obj).booleanValue());
            case STRING:
                return CodedOutputStream.computeStringSizeNoTag((String) obj);
            case BYTES:
                return obj instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag((ByteString) obj) : CodedOutputStream.computeByteArraySizeNoTag((byte[]) obj);
            case UINT32:
                return CodedOutputStream.computeUInt32SizeNoTag(((Integer) obj).intValue());
            case SFIXED32:
                return CodedOutputStream.computeSFixed32SizeNoTag(((Integer) obj).intValue());
            case SFIXED64:
                return CodedOutputStream.computeSFixed64SizeNoTag(((Long) obj).longValue());
            case SINT32:
                return CodedOutputStream.computeSInt32SizeNoTag(((Integer) obj).intValue());
            case SINT64:
                return CodedOutputStream.computeSInt64SizeNoTag(((Long) obj).longValue());
            case GROUP:
                return CodedOutputStream.computeGroupSizeNoTag((MessageLite) obj);
            case MESSAGE:
                return obj instanceof LazyField ? CodedOutputStream.computeLazyFieldSizeNoTag((LazyField) obj) : CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj);
            case ENUM:
                return obj instanceof EnumLite ? CodedOutputStream.computeEnumSizeNoTag(((EnumLite) obj).getNumber()) : CodedOutputStream.computeEnumSizeNoTag(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int computeFieldSize(FieldDescriptorLite fieldDescriptorLite, Object obj) {
        int i = 0;
        FieldType liteType = fieldDescriptorLite.getLiteType();
        int number = fieldDescriptorLite.getNumber();
        if (!fieldDescriptorLite.isRepeated()) {
            return computeElementSize(liteType, number, obj);
        }
        if (fieldDescriptorLite.isPacked()) {
            for (Object computeElementSizeNoTag : (List) obj) {
                i += computeElementSizeNoTag(liteType, computeElementSizeNoTag);
            }
            return CodedOutputStream.computeRawVarint32Size(i) + (CodedOutputStream.computeTagSize(number) + i);
        }
        for (Object computeElementSizeNoTag2 : (List) obj) {
            i += computeElementSize(liteType, number, computeElementSizeNoTag2);
        }
        return i;
    }

    public static FieldSet emptySet() {
        return DEFAULT_INSTANCE;
    }

    private int getMessageSetSerializedSize(Entry entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        Object value = entry.getValue();
        return (fieldDescriptorLite.getLiteJavaType() != JavaType.MESSAGE || fieldDescriptorLite.isRepeated() || fieldDescriptorLite.isPacked()) ? computeFieldSize(fieldDescriptorLite, value) : value instanceof LazyField ? CodedOutputStream.computeLazyFieldMessageSetExtensionSize(((FieldDescriptorLite) entry.getKey()).getNumber(), (LazyField) value) : CodedOutputStream.computeMessageSetExtensionSize(((FieldDescriptorLite) entry.getKey()).getNumber(), (MessageLite) value);
    }

    static int getWireFormatForFieldType(FieldType fieldType, boolean z) {
        return z ? 2 : fieldType.getWireType();
    }

    private boolean isInitialized(Entry entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        if (fieldDescriptorLite.getLiteJavaType() == JavaType.MESSAGE) {
            if (fieldDescriptorLite.isRepeated()) {
                for (MessageLite isInitialized : (List) entry.getValue()) {
                    if (!isInitialized.isInitialized()) {
                        return false;
                    }
                }
            }
            Object value = entry.getValue();
            if (value instanceof MessageLite) {
                if (!((MessageLite) value).isInitialized()) {
                    return false;
                }
            } else if (value instanceof LazyField) {
                return true;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    private void mergeFromField(Entry entry) {
        Comparable comparable = (FieldDescriptorLite) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof LazyField) {
            value = ((LazyField) value).getValue();
        }
        Object field;
        if (comparable.isRepeated()) {
            field = getField(comparable);
            if (field == null) {
                field = new ArrayList();
            }
            for (Object cloneIfMutable : (List) value) {
                ((List) field).add(cloneIfMutable(cloneIfMutable));
            }
            this.fields.put(comparable, field);
        } else if (comparable.getLiteJavaType() == JavaType.MESSAGE) {
            field = getField(comparable);
            if (field == null) {
                this.fields.put(comparable, cloneIfMutable(value));
                return;
            }
            this.fields.put(comparable, comparable.internalMergeFrom(((MessageLite) field).toBuilder(), (MessageLite) value).build());
        } else {
            this.fields.put(comparable, cloneIfMutable(value));
        }
    }

    public static FieldSet newFieldSet() {
        return new FieldSet();
    }

    public static Object readPrimitiveField(CodedInputStream codedInputStream, FieldType fieldType, boolean z) {
        switch (fieldType) {
            case DOUBLE:
                return Double.valueOf(codedInputStream.readDouble());
            case FLOAT:
                return Float.valueOf(codedInputStream.readFloat());
            case INT64:
                return Long.valueOf(codedInputStream.readInt64());
            case UINT64:
                return Long.valueOf(codedInputStream.readUInt64());
            case INT32:
                return Integer.valueOf(codedInputStream.readInt32());
            case FIXED64:
                return Long.valueOf(codedInputStream.readFixed64());
            case FIXED32:
                return Integer.valueOf(codedInputStream.readFixed32());
            case BOOL:
                return Boolean.valueOf(codedInputStream.readBool());
            case STRING:
                return z ? codedInputStream.readStringRequireUtf8() : codedInputStream.readString();
            case BYTES:
                return codedInputStream.readBytes();
            case UINT32:
                return Integer.valueOf(codedInputStream.readUInt32());
            case SFIXED32:
                return Integer.valueOf(codedInputStream.readSFixed32());
            case SFIXED64:
                return Long.valueOf(codedInputStream.readSFixed64());
            case SINT32:
                return Integer.valueOf(codedInputStream.readSInt32());
            case SINT64:
                return Long.valueOf(codedInputStream.readSInt64());
            case GROUP:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case MESSAGE:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case ENUM:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private static void verifyType(FieldType fieldType, Object obj) {
        boolean z = false;
        if (obj == null) {
            throw new NullPointerException();
        }
        switch (fieldType.getJavaType()) {
            case INT:
                z = obj instanceof Integer;
                break;
            case LONG:
                z = obj instanceof Long;
                break;
            case FLOAT:
                z = obj instanceof Float;
                break;
            case DOUBLE:
                z = obj instanceof Double;
                break;
            case BOOLEAN:
                z = obj instanceof Boolean;
                break;
            case STRING:
                z = obj instanceof String;
                break;
            case BYTE_STRING:
                if ((obj instanceof ByteString) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case ENUM:
                if ((obj instanceof Integer) || (obj instanceof EnumLite)) {
                    z = true;
                    break;
                }
            case MESSAGE:
                if ((obj instanceof MessageLite) || (obj instanceof LazyField)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    private static void writeElement(CodedOutputStream codedOutputStream, FieldType fieldType, int i, Object obj) {
        if (fieldType == FieldType.GROUP) {
            codedOutputStream.writeGroup(i, (MessageLite) obj);
            return;
        }
        codedOutputStream.writeTag(i, getWireFormatForFieldType(fieldType, false));
        writeElementNoTag(codedOutputStream, fieldType, obj);
    }

    private static void writeElementNoTag(CodedOutputStream codedOutputStream, FieldType fieldType, Object obj) {
        switch (fieldType) {
            case DOUBLE:
                codedOutputStream.writeDoubleNoTag(((Double) obj).doubleValue());
                return;
            case FLOAT:
                codedOutputStream.writeFloatNoTag(((Float) obj).floatValue());
                return;
            case INT64:
                codedOutputStream.writeInt64NoTag(((Long) obj).longValue());
                return;
            case UINT64:
                codedOutputStream.writeUInt64NoTag(((Long) obj).longValue());
                return;
            case INT32:
                codedOutputStream.writeInt32NoTag(((Integer) obj).intValue());
                return;
            case FIXED64:
                codedOutputStream.writeFixed64NoTag(((Long) obj).longValue());
                return;
            case FIXED32:
                codedOutputStream.writeFixed32NoTag(((Integer) obj).intValue());
                return;
            case BOOL:
                codedOutputStream.writeBoolNoTag(((Boolean) obj).booleanValue());
                return;
            case STRING:
                codedOutputStream.writeStringNoTag((String) obj);
                return;
            case BYTES:
                if (obj instanceof ByteString) {
                    codedOutputStream.writeBytesNoTag((ByteString) obj);
                    return;
                } else {
                    codedOutputStream.writeByteArrayNoTag((byte[]) obj);
                    return;
                }
            case UINT32:
                codedOutputStream.writeUInt32NoTag(((Integer) obj).intValue());
                return;
            case SFIXED32:
                codedOutputStream.writeSFixed32NoTag(((Integer) obj).intValue());
                return;
            case SFIXED64:
                codedOutputStream.writeSFixed64NoTag(((Long) obj).longValue());
                return;
            case SINT32:
                codedOutputStream.writeSInt32NoTag(((Integer) obj).intValue());
                return;
            case SINT64:
                codedOutputStream.writeSInt64NoTag(((Long) obj).longValue());
                return;
            case GROUP:
                codedOutputStream.writeGroupNoTag((MessageLite) obj);
                return;
            case MESSAGE:
                codedOutputStream.writeMessageNoTag((MessageLite) obj);
                return;
            case ENUM:
                if (obj instanceof EnumLite) {
                    codedOutputStream.writeEnumNoTag(((EnumLite) obj).getNumber());
                    return;
                } else {
                    codedOutputStream.writeEnumNoTag(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public static void writeField(FieldDescriptorLite fieldDescriptorLite, Object obj, CodedOutputStream codedOutputStream) {
        FieldType liteType = fieldDescriptorLite.getLiteType();
        int number = fieldDescriptorLite.getNumber();
        if (fieldDescriptorLite.isRepeated()) {
            List<Object> list = (List) obj;
            if (fieldDescriptorLite.isPacked()) {
                codedOutputStream.writeTag(number, 2);
                number = 0;
                for (Object computeElementSizeNoTag : list) {
                    number += computeElementSizeNoTag(liteType, computeElementSizeNoTag);
                }
                codedOutputStream.writeRawVarint32(number);
                for (Object writeElementNoTag : list) {
                    writeElementNoTag(codedOutputStream, liteType, writeElementNoTag);
                }
                return;
            }
            for (Object computeElementSizeNoTag2 : list) {
                writeElement(codedOutputStream, liteType, number, computeElementSizeNoTag2);
            }
        } else if (obj instanceof LazyField) {
            writeElement(codedOutputStream, liteType, number, ((LazyField) obj).getValue());
        } else {
            writeElement(codedOutputStream, liteType, number, obj);
        }
    }

    private void writeMessageSetTo(Entry entry, CodedOutputStream codedOutputStream) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        if (fieldDescriptorLite.getLiteJavaType() != JavaType.MESSAGE || fieldDescriptorLite.isRepeated() || fieldDescriptorLite.isPacked()) {
            writeField(fieldDescriptorLite, entry.getValue(), codedOutputStream);
            return;
        }
        MessageLite value = entry.getValue();
        codedOutputStream.writeMessageSetExtension(((FieldDescriptorLite) entry.getKey()).getNumber(), value instanceof LazyField ? ((LazyField) value).getValue() : value);
    }

    public void addRepeatedField(FieldDescriptorLite fieldDescriptorLite, Object obj) {
        if (fieldDescriptorLite.isRepeated()) {
            List arrayList;
            verifyType(fieldDescriptorLite.getLiteType(), obj);
            Object field = getField(fieldDescriptorLite);
            if (field == null) {
                arrayList = new ArrayList();
                this.fields.put((Comparable) fieldDescriptorLite, (Object) arrayList);
            } else {
                arrayList = (List) field;
            }
            arrayList.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    public void clear() {
        this.fields.clear();
        this.hasLazyField = false;
    }

    public void clearField(FieldDescriptorLite fieldDescriptorLite) {
        this.fields.remove(fieldDescriptorLite);
        if (this.fields.isEmpty()) {
            this.hasLazyField = false;
        }
    }

    public FieldSet clone() {
        FieldSet newFieldSet = newFieldSet();
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            Entry arrayEntryAt = this.fields.getArrayEntryAt(i);
            newFieldSet.setField((FieldDescriptorLite) arrayEntryAt.getKey(), arrayEntryAt.getValue());
        }
        for (Entry entry : this.fields.getOverflowEntries()) {
            newFieldSet.setField((FieldDescriptorLite) entry.getKey(), entry.getValue());
        }
        newFieldSet.hasLazyField = this.hasLazyField;
        return newFieldSet;
    }

    public Map getAllFields() {
        if (!this.hasLazyField) {
            return this.fields.isImmutable() ? this.fields : Collections.unmodifiableMap(this.fields);
        } else {
            Map newFieldMap = SmallSortedMap.newFieldMap(16);
            for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
                cloneFieldEntry(newFieldMap, this.fields.getArrayEntryAt(i));
            }
            for (Entry cloneFieldEntry : this.fields.getOverflowEntries()) {
                cloneFieldEntry(newFieldMap, cloneFieldEntry);
            }
            if (this.fields.isImmutable()) {
                newFieldMap.makeImmutable();
            }
            return newFieldMap;
        }
    }

    public Object getField(FieldDescriptorLite fieldDescriptorLite) {
        Object obj = this.fields.get(fieldDescriptorLite);
        return obj instanceof LazyField ? ((LazyField) obj).getValue() : obj;
    }

    public int getMessageSetSerializedSize() {
        int i;
        int i2 = 0;
        for (i = 0; i < this.fields.getNumArrayEntries(); i++) {
            i2 += getMessageSetSerializedSize(this.fields.getArrayEntryAt(i));
        }
        i = i2;
        for (Entry messageSetSerializedSize : this.fields.getOverflowEntries()) {
            i = getMessageSetSerializedSize(messageSetSerializedSize) + i;
        }
        return i;
    }

    public Object getRepeatedField(FieldDescriptorLite fieldDescriptorLite, int i) {
        if (fieldDescriptorLite.isRepeated()) {
            Object field = getField(fieldDescriptorLite);
            if (field != null) {
                return ((List) field).get(i);
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public int getRepeatedFieldCount(FieldDescriptorLite fieldDescriptorLite) {
        if (fieldDescriptorLite.isRepeated()) {
            Object field = getField(fieldDescriptorLite);
            return field == null ? 0 : ((List) field).size();
        } else {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
    }

    public int getSerializedSize() {
        int i = 0;
        int i2 = 0;
        while (i2 < this.fields.getNumArrayEntries()) {
            Entry arrayEntryAt = this.fields.getArrayEntryAt(i2);
            i2++;
            i = computeFieldSize((FieldDescriptorLite) arrayEntryAt.getKey(), arrayEntryAt.getValue()) + i;
        }
        i2 = i;
        for (Entry entry : this.fields.getOverflowEntries()) {
            i2 += computeFieldSize((FieldDescriptorLite) entry.getKey(), entry.getValue());
        }
        return i2;
    }

    public boolean hasField(FieldDescriptorLite fieldDescriptorLite) {
        if (!fieldDescriptorLite.isRepeated()) {
            return this.fields.get(fieldDescriptorLite) != null;
        } else {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        }
    }

    public boolean isImmutable() {
        return this.isImmutable;
    }

    public boolean isInitialized() {
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            if (!isInitialized(this.fields.getArrayEntryAt(i))) {
                return false;
            }
        }
        for (Entry isInitialized : this.fields.getOverflowEntries()) {
            if (!isInitialized(isInitialized)) {
                return false;
            }
        }
        return true;
    }

    public Iterator iterator() {
        return this.hasLazyField ? new LazyIterator(this.fields.entrySet().iterator()) : this.fields.entrySet().iterator();
    }

    public void makeImmutable() {
        if (!this.isImmutable) {
            this.fields.makeImmutable();
            this.isImmutable = true;
        }
    }

    public void mergeFrom(FieldSet fieldSet) {
        for (int i = 0; i < fieldSet.fields.getNumArrayEntries(); i++) {
            mergeFromField(fieldSet.fields.getArrayEntryAt(i));
        }
        for (Entry mergeFromField : fieldSet.fields.getOverflowEntries()) {
            mergeFromField(mergeFromField);
        }
    }

    public void setField(FieldDescriptorLite fieldDescriptorLite, Object obj) {
        if (!fieldDescriptorLite.isRepeated()) {
            verifyType(fieldDescriptorLite.getLiteType(), obj);
        } else if (obj instanceof List) {
            List<Object> arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            for (Object verifyType : arrayList) {
                verifyType(fieldDescriptorLite.getLiteType(), verifyType);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof LazyField) {
            this.hasLazyField = true;
        }
        this.fields.put((Comparable) fieldDescriptorLite, obj);
    }

    public void setRepeatedField(FieldDescriptorLite fieldDescriptorLite, int i, Object obj) {
        if (fieldDescriptorLite.isRepeated()) {
            Object field = getField(fieldDescriptorLite);
            if (field == null) {
                throw new IndexOutOfBoundsException();
            }
            verifyType(fieldDescriptorLite.getLiteType(), obj);
            ((List) field).set(i, obj);
            return;
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public void writeMessageSetTo(CodedOutputStream codedOutputStream) {
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            writeMessageSetTo(this.fields.getArrayEntryAt(i), codedOutputStream);
        }
        for (Entry writeMessageSetTo : this.fields.getOverflowEntries()) {
            writeMessageSetTo(writeMessageSetTo, codedOutputStream);
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            Entry arrayEntryAt = this.fields.getArrayEntryAt(i);
            writeField((FieldDescriptorLite) arrayEntryAt.getKey(), arrayEntryAt.getValue(), codedOutputStream);
        }
        for (Entry entry : this.fields.getOverflowEntries()) {
            writeField((FieldDescriptorLite) entry.getKey(), entry.getValue(), codedOutputStream);
        }
    }
}
