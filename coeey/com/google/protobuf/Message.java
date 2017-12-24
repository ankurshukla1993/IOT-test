package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import java.io.InputStream;

public interface Message extends MessageLite, MessageOrBuilder {

    public interface Builder extends com.google.protobuf.MessageLite.Builder, MessageOrBuilder {
        Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj);

        Message build();

        Message buildPartial();

        Builder clear();

        Builder clearField(FieldDescriptor fieldDescriptor);

        Builder clearOneof(OneofDescriptor oneofDescriptor);

        Builder clone();

        Descriptor getDescriptorForType();

        Builder getFieldBuilder(FieldDescriptor fieldDescriptor);

        boolean mergeDelimitedFrom(InputStream inputStream);

        boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(ByteString byteString);

        Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(CodedInputStream codedInputStream);

        Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(Message message);

        Builder mergeFrom(InputStream inputStream);

        Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(byte[] bArr);

        Builder mergeFrom(byte[] bArr, int i, int i2);

        Builder mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite);

        Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet);

        Builder newBuilderForField(FieldDescriptor fieldDescriptor);

        Builder setField(FieldDescriptor fieldDescriptor, Object obj);

        Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj);

        Builder setUnknownFields(UnknownFieldSet unknownFieldSet);
    }

    boolean equals(Object obj);

    Parser getParserForType();

    int hashCode();

    Builder newBuilderForType();

    Builder toBuilder();

    String toString();
}
