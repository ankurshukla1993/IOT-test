package com.google.protobuf;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.Internal.EnumLite;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractMessage extends AbstractMessageLite implements Message {
    private int memoizedSize = -1;

    public abstract class Builder extends com.google.protobuf.AbstractMessageLite.Builder implements com.google.protobuf.Message.Builder {
        protected static UninitializedMessageException newUninitializedMessageException(Message message) {
            return new UninitializedMessageException(MessageReflection.findMissingFields(message));
        }

        public Builder clear() {
            for (Entry key : getAllFields().entrySet()) {
                clearField((FieldDescriptor) key.getKey());
            }
            return this;
        }

        public Builder clearOneof(OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("clearOneof() is not implemented.");
        }

        public abstract Builder clone();

        public List findInitializationErrors() {
            return MessageReflection.findMissingFields(this);
        }

        public com.google.protobuf.Message.Builder getFieldBuilder(FieldDescriptor fieldDescriptor) {
            throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
        }

        public String getInitializationErrorString() {
            return MessageReflection.delimitWithCommas(findInitializationErrors());
        }

        public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
        }

        public boolean hasOneof(OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("hasOneof() is not implemented.");
        }

        public boolean mergeDelimitedFrom(InputStream inputStream) {
            return super.mergeDelimitedFrom(inputStream);
        }

        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public Builder mergeFrom(ByteString byteString) {
            return (Builder) super.mergeFrom(byteString);
        }

        public Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (Builder) super.mergeFrom(byteString, extensionRegistryLite);
        }

        public Builder mergeFrom(CodedInputStream codedInputStream) {
            return mergeFrom(codedInputStream, ExtensionRegistry.getEmptyRegistry());
        }

        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
            int readTag;
            do {
                readTag = codedInputStream.readTag();
                if (readTag == 0) {
                    break;
                }
            } while (MessageReflection.mergeFieldFrom(codedInputStream, newBuilder, extensionRegistryLite, getDescriptorForType(), new BuilderAdapter(this), readTag));
            setUnknownFields(newBuilder.build());
            return this;
        }

        public Builder mergeFrom(Message message) {
            if (message.getDescriptorForType() != getDescriptorForType()) {
                throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
            }
            for (Entry entry : message.getAllFields().entrySet()) {
                FieldDescriptor fieldDescriptor = (FieldDescriptor) entry.getKey();
                if (fieldDescriptor.isRepeated()) {
                    for (Object addRepeatedField : (List) entry.getValue()) {
                        addRepeatedField(fieldDescriptor, addRepeatedField);
                    }
                } else if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                    Message message2 = (Message) getField(fieldDescriptor);
                    if (message2 == message2.getDefaultInstanceForType()) {
                        setField(fieldDescriptor, entry.getValue());
                    } else {
                        setField(fieldDescriptor, message2.newBuilderForType().mergeFrom(message2).mergeFrom((Message) entry.getValue()).build());
                    }
                } else {
                    setField(fieldDescriptor, entry.getValue());
                }
            }
            mergeUnknownFields(message.getUnknownFields());
            return this;
        }

        public Builder mergeFrom(InputStream inputStream) {
            return (Builder) super.mergeFrom(inputStream);
        }

        public Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (Builder) super.mergeFrom(inputStream, extensionRegistryLite);
        }

        public Builder mergeFrom(byte[] bArr) {
            return (Builder) super.mergeFrom(bArr);
        }

        public Builder mergeFrom(byte[] bArr, int i, int i2) {
            return (Builder) super.mergeFrom(bArr, i, i2);
        }

        public Builder mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) {
            return (Builder) super.mergeFrom(bArr, i, i2, extensionRegistryLite);
        }

        public Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (Builder) super.mergeFrom(bArr, extensionRegistryLite);
        }

        public Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFieldSet).build());
            return this;
        }

        public String toString() {
            return TextFormat.printToString((MessageOrBuilder) this);
        }
    }

    private static boolean compareBytes(Object obj, Object obj2) {
        return ((obj instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.equals((byte[]) obj, (byte[]) obj2) : toByteString(obj).equals(toByteString(obj2));
    }

    static boolean compareFields(Map map, Map map2) {
        if (map.size() != map2.size()) {
            return false;
        }
        for (FieldDescriptor fieldDescriptor : map.keySet()) {
            if (!map2.containsKey(fieldDescriptor)) {
                return false;
            }
            Object obj = map.get(fieldDescriptor);
            Object obj2 = map2.get(fieldDescriptor);
            if (fieldDescriptor.getType() == Type.BYTES) {
                if (fieldDescriptor.isRepeated()) {
                    List list = (List) obj;
                    List list2 = (List) obj2;
                    if (list.size() != list2.size()) {
                        return false;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (!compareBytes(list.get(i), list2.get(i))) {
                            return false;
                        }
                    }
                    continue;
                } else if (!compareBytes(obj, obj2)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    protected static int hashFields(int i, Map map) {
        for (Entry entry : map.entrySet()) {
            FieldDescriptor fieldDescriptor = (FieldDescriptor) entry.getKey();
            Object value = entry.getValue();
            int number = (i * 37) + fieldDescriptor.getNumber();
            i = fieldDescriptor.getType() != Type.ENUM ? (number * 53) + value.hashCode() : fieldDescriptor.isRepeated() ? (number * 53) + Internal.hashEnumList((List) value) : (number * 53) + Internal.hashEnum((EnumLite) value);
        }
        return i;
    }

    private static ByteString toByteString(Object obj) {
        return obj instanceof byte[] ? ByteString.copyFrom((byte[]) obj) : (ByteString) obj;
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof Message)) {
                return false;
            }
            Message message = (Message) obj;
            if (getDescriptorForType() != message.getDescriptorForType() || !compareFields(getAllFields(), message.getAllFields())) {
                return false;
            }
            if (!getUnknownFields().equals(message.getUnknownFields())) {
                return false;
            }
        }
        return true;
    }

    public List findInitializationErrors() {
        return MessageReflection.findMissingFields(this);
    }

    public String getInitializationErrorString() {
        return MessageReflection.delimitWithCommas(findInitializationErrors());
    }

    public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        this.memoizedSize = MessageReflection.getSerializedSize(this);
        return this.memoizedSize;
    }

    public boolean hasOneof(OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("hasOneof() is not implemented.");
    }

    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        i = (hashFields(getDescriptorForType().hashCode() + 779, getAllFields()) * 29) + getUnknownFields().hashCode();
        this.memoizedHashCode = i;
        return i;
    }

    public boolean isInitialized() {
        return MessageReflection.isInitialized(this);
    }

    UninitializedMessageException newUninitializedMessageException() {
        return Builder.newUninitializedMessageException(this);
    }

    public final String toString() {
        return TextFormat.printToString((MessageOrBuilder) this);
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        MessageReflection.writeMessageTo(this, codedOutputStream, false);
    }
}
