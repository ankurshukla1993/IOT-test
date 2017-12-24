package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;
import com.google.protobuf.Descriptors.OneofDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public final class DynamicMessage extends AbstractMessage {
    private final FieldSet fields;
    private int memoizedSize = -1;
    private final FieldDescriptor[] oneofCases;
    private final Descriptor type;
    private final UnknownFieldSet unknownFields;

    class C19891 extends AbstractParser {
        C19891() {
        }

        public DynamicMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            Builder newBuilder = DynamicMessage.newBuilder(DynamicMessage.this.type);
            try {
                newBuilder.mergeFrom(codedInputStream, extensionRegistryLite);
                return newBuilder.buildPartial();
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(newBuilder.buildPartial());
            } catch (IOException e2) {
                throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(newBuilder.buildPartial());
            }
        }
    }

    public final class Builder extends com.google.protobuf.AbstractMessage.Builder {
        private FieldSet fields;
        private final FieldDescriptor[] oneofCases;
        private final Descriptor type;
        private UnknownFieldSet unknownFields;

        private Builder(Descriptor descriptor) {
            this.type = descriptor;
            this.fields = FieldSet.newFieldSet();
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.oneofCases = new FieldDescriptor[descriptor.toProto().getOneofDeclCount()];
        }

        private DynamicMessage buildParsed() {
            if (isInitialized()) {
                return buildPartial();
            }
            throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(new DynamicMessage(this.type, this.fields, (FieldDescriptor[]) Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields)).asInvalidProtocolBufferException();
        }

        private void ensureIsMutable() {
            if (this.fields.isImmutable()) {
                this.fields = this.fields.clone();
            }
        }

        private void verifyContainingType(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != this.type) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyEnumType(FieldDescriptor fieldDescriptor, Object obj) {
            if (obj == null) {
                throw new NullPointerException();
            } else if (!(obj instanceof EnumValueDescriptor)) {
                throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
            } else if (fieldDescriptor.getEnumType() != ((EnumValueDescriptor) obj).getType()) {
                throw new IllegalArgumentException("EnumValueDescriptor doesn't much Enum Field.");
            }
        }

        private void verifyOneofContainingType(OneofDescriptor oneofDescriptor) {
            if (oneofDescriptor.getContainingType() != this.type) {
                throw new IllegalArgumentException("OneofDescriptor does not match message type.");
            }
        }

        public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            verifyContainingType(fieldDescriptor);
            ensureIsMutable();
            this.fields.addRepeatedField(fieldDescriptor, obj);
            return this;
        }

        public DynamicMessage build() {
            if (isInitialized()) {
                return buildPartial();
            }
            throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(new DynamicMessage(this.type, this.fields, (FieldDescriptor[]) Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields));
        }

        public DynamicMessage buildPartial() {
            this.fields.makeImmutable();
            return new DynamicMessage(this.type, this.fields, (FieldDescriptor[]) Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields);
        }

        public Builder clear() {
            if (this.fields.isImmutable()) {
                this.fields = FieldSet.newFieldSet();
            } else {
                this.fields.clear();
            }
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            return this;
        }

        public Builder clearField(FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            ensureIsMutable();
            OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
            if (containingOneof != null) {
                int index = containingOneof.getIndex();
                if (this.oneofCases[index] == fieldDescriptor) {
                    this.oneofCases[index] = null;
                }
            }
            this.fields.clearField(fieldDescriptor);
            return this;
        }

        public Builder clearOneof(OneofDescriptor oneofDescriptor) {
            verifyOneofContainingType(oneofDescriptor);
            FieldDescriptor fieldDescriptor = this.oneofCases[oneofDescriptor.getIndex()];
            if (fieldDescriptor != null) {
                clearField(fieldDescriptor);
            }
            return this;
        }

        public Builder clone() {
            Builder builder = new Builder(this.type);
            builder.fields.mergeFrom(this.fields);
            builder.mergeUnknownFields(this.unknownFields);
            System.arraycopy(this.oneofCases, 0, builder.oneofCases, 0, this.oneofCases.length);
            return builder;
        }

        public Map getAllFields() {
            return this.fields.getAllFields();
        }

        public DynamicMessage getDefaultInstanceForType() {
            return DynamicMessage.getDefaultInstance(this.type);
        }

        public Descriptor getDescriptorForType() {
            return this.type;
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            Object field = this.fields.getField(fieldDescriptor);
            return field == null ? fieldDescriptor.isRepeated() ? Collections.emptyList() : fieldDescriptor.getJavaType() == JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : field;
        }

        public com.google.protobuf.Message.Builder getFieldBuilder(FieldDescriptor fieldDescriptor) {
            throw new UnsupportedOperationException("getFieldBuilder() called on a dynamic message type.");
        }

        public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
            verifyOneofContainingType(oneofDescriptor);
            return this.oneofCases[oneofDescriptor.getIndex()];
        }

        public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
            verifyContainingType(fieldDescriptor);
            return this.fields.getRepeatedField(fieldDescriptor, i);
        }

        public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            return this.fields.getRepeatedFieldCount(fieldDescriptor);
        }

        public UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            return this.fields.hasField(fieldDescriptor);
        }

        public boolean hasOneof(OneofDescriptor oneofDescriptor) {
            verifyOneofContainingType(oneofDescriptor);
            return this.oneofCases[oneofDescriptor.getIndex()] != null;
        }

        public boolean isInitialized() {
            return DynamicMessage.isInitialized(this.type, this.fields);
        }

        public Builder mergeFrom(Message message) {
            if (!(message instanceof DynamicMessage)) {
                return (Builder) super.mergeFrom(message);
            }
            DynamicMessage dynamicMessage = (DynamicMessage) message;
            if (dynamicMessage.type != this.type) {
                throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
            }
            ensureIsMutable();
            this.fields.mergeFrom(dynamicMessage.fields);
            mergeUnknownFields(dynamicMessage.unknownFields);
            int i = 0;
            while (i < this.oneofCases.length) {
                if (this.oneofCases[i] == null) {
                    this.oneofCases[i] = dynamicMessage.oneofCases[i];
                } else if (!(dynamicMessage.oneofCases[i] == null || this.oneofCases[i] == dynamicMessage.oneofCases[i])) {
                    this.fields.clearField(this.oneofCases[i]);
                    this.oneofCases[i] = dynamicMessage.oneofCases[i];
                }
                i++;
            }
            return this;
        }

        public Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFieldSet).build();
            return this;
        }

        public Builder newBuilderForField(FieldDescriptor fieldDescriptor) {
            verifyContainingType(fieldDescriptor);
            if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                return new Builder(fieldDescriptor.getMessageType());
            }
            throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
        }

        public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
            verifyContainingType(fieldDescriptor);
            ensureIsMutable();
            if (fieldDescriptor.getType() == Type.ENUM) {
                verifyEnumType(fieldDescriptor, obj);
            }
            OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
            if (containingOneof != null) {
                int index = containingOneof.getIndex();
                Object obj2 = this.oneofCases[index];
                if (!(obj2 == null || obj2 == fieldDescriptor)) {
                    this.fields.clearField(obj2);
                }
                this.oneofCases[index] = fieldDescriptor;
            }
            this.fields.setField(fieldDescriptor, obj);
            return this;
        }

        public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            verifyContainingType(fieldDescriptor);
            ensureIsMutable();
            this.fields.setRepeatedField(fieldDescriptor, i, obj);
            return this;
        }

        public Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = unknownFieldSet;
            return this;
        }
    }

    DynamicMessage(Descriptor descriptor, FieldSet fieldSet, FieldDescriptor[] fieldDescriptorArr, UnknownFieldSet unknownFieldSet) {
        this.type = descriptor;
        this.fields = fieldSet;
        this.oneofCases = fieldDescriptorArr;
        this.unknownFields = unknownFieldSet;
    }

    public static DynamicMessage getDefaultInstance(Descriptor descriptor) {
        return new DynamicMessage(descriptor, FieldSet.emptySet(), new FieldDescriptor[descriptor.toProto().getOneofDeclCount()], UnknownFieldSet.getDefaultInstance());
    }

    static boolean isInitialized(Descriptor descriptor, FieldSet fieldSet) {
        for (FieldDescriptor fieldDescriptor : descriptor.getFields()) {
            if (fieldDescriptor.isRequired() && !fieldSet.hasField(fieldDescriptor)) {
                return false;
            }
        }
        return fieldSet.isInitialized();
    }

    public static Builder newBuilder(Descriptor descriptor) {
        return new Builder(descriptor);
    }

    public static Builder newBuilder(Message message) {
        return new Builder(message.getDescriptorForType()).mergeFrom(message);
    }

    public static DynamicMessage parseFrom(Descriptor descriptor, ByteString byteString) {
        return ((Builder) newBuilder(descriptor).mergeFrom(byteString)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptor descriptor, ByteString byteString, ExtensionRegistry extensionRegistry) {
        return ((Builder) newBuilder(descriptor).mergeFrom(byteString, (ExtensionRegistryLite) extensionRegistry)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptor descriptor, CodedInputStream codedInputStream) {
        return ((Builder) newBuilder(descriptor).mergeFrom(codedInputStream)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptor descriptor, CodedInputStream codedInputStream, ExtensionRegistry extensionRegistry) {
        return ((Builder) newBuilder(descriptor).mergeFrom(codedInputStream, (ExtensionRegistryLite) extensionRegistry)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptor descriptor, InputStream inputStream) {
        return ((Builder) newBuilder(descriptor).mergeFrom(inputStream)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptor descriptor, InputStream inputStream, ExtensionRegistry extensionRegistry) {
        return ((Builder) newBuilder(descriptor).mergeFrom(inputStream, (ExtensionRegistryLite) extensionRegistry)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptor descriptor, byte[] bArr) {
        return ((Builder) newBuilder(descriptor).mergeFrom(bArr)).buildParsed();
    }

    public static DynamicMessage parseFrom(Descriptor descriptor, byte[] bArr, ExtensionRegistry extensionRegistry) {
        return ((Builder) newBuilder(descriptor).mergeFrom(bArr, (ExtensionRegistryLite) extensionRegistry)).buildParsed();
    }

    private void verifyContainingType(FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getContainingType() != this.type) {
            throw new IllegalArgumentException("FieldDescriptor does not match message type.");
        }
    }

    private void verifyOneofContainingType(OneofDescriptor oneofDescriptor) {
        if (oneofDescriptor.getContainingType() != this.type) {
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }
    }

    public Map getAllFields() {
        return this.fields.getAllFields();
    }

    public DynamicMessage getDefaultInstanceForType() {
        return getDefaultInstance(this.type);
    }

    public Descriptor getDescriptorForType() {
        return this.type;
    }

    public Object getField(FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        Object field = this.fields.getField(fieldDescriptor);
        return field == null ? fieldDescriptor.isRepeated() ? Collections.emptyList() : fieldDescriptor.getJavaType() == JavaType.MESSAGE ? getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : field;
    }

    public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
        verifyOneofContainingType(oneofDescriptor);
        return this.oneofCases[oneofDescriptor.getIndex()];
    }

    public Parser getParserForType() {
        return new C19891();
    }

    public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
        verifyContainingType(fieldDescriptor);
        return this.fields.getRepeatedField(fieldDescriptor, i);
    }

    public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        return this.fields.getRepeatedFieldCount(fieldDescriptor);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i == -1) {
            i = this.type.getOptions().getMessageSetWireFormat() ? this.fields.getMessageSetSerializedSize() + this.unknownFields.getSerializedSizeAsMessageSet() : this.fields.getSerializedSize() + this.unknownFields.getSerializedSize();
            this.memoizedSize = i;
        }
        return i;
    }

    public UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    public boolean hasField(FieldDescriptor fieldDescriptor) {
        verifyContainingType(fieldDescriptor);
        return this.fields.hasField(fieldDescriptor);
    }

    public boolean hasOneof(OneofDescriptor oneofDescriptor) {
        verifyOneofContainingType(oneofDescriptor);
        return this.oneofCases[oneofDescriptor.getIndex()] != null;
    }

    public boolean isInitialized() {
        return isInitialized(this.type, this.fields);
    }

    public Builder newBuilderForType() {
        return new Builder(this.type);
    }

    public Builder toBuilder() {
        return newBuilderForType().mergeFrom((Message) this);
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        if (this.type.getOptions().getMessageSetWireFormat()) {
            this.fields.writeMessageSetTo(codedOutputStream);
            this.unknownFields.writeAsMessageSetTo(codedOutputStream);
            return;
        }
        this.fields.writeTo(codedOutputStream);
        this.unknownFields.writeTo(codedOutputStream);
    }
}
