package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistry.ExtensionInfo;
import com.google.protobuf.Message.Builder;
import com.google.protobuf.UnknownFieldSet.Field;
import com.google.protobuf.WireFormat.FieldType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

class MessageReflection {

    interface MergeTarget {

        public enum ContainerType {
            MESSAGE,
            EXTENSION_SET
        }

        MergeTarget addRepeatedField(FieldDescriptor fieldDescriptor, Object obj);

        MergeTarget clearField(FieldDescriptor fieldDescriptor);

        MergeTarget clearOneof(OneofDescriptor oneofDescriptor);

        ExtensionInfo findExtensionByName(ExtensionRegistry extensionRegistry, String str);

        ExtensionInfo findExtensionByNumber(ExtensionRegistry extensionRegistry, Descriptor descriptor, int i);

        Object finish();

        ContainerType getContainerType();

        Descriptor getDescriptorForType();

        Object getField(FieldDescriptor fieldDescriptor);

        FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor);

        boolean hasField(FieldDescriptor fieldDescriptor);

        boolean hasOneof(OneofDescriptor oneofDescriptor);

        MergeTarget newMergeTargetForField(FieldDescriptor fieldDescriptor, Message message);

        Object parseGroup(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message);

        Object parseMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message);

        Object parseMessageFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message);

        Object readPrimitiveField(CodedInputStream codedInputStream, FieldType fieldType, boolean z);

        MergeTarget setField(FieldDescriptor fieldDescriptor, Object obj);

        MergeTarget setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj);
    }

    class BuilderAdapter implements MergeTarget {
        private final Builder builder;

        public BuilderAdapter(Builder builder) {
            this.builder = builder;
        }

        public MergeTarget addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            this.builder.addRepeatedField(fieldDescriptor, obj);
            return this;
        }

        public MergeTarget clearField(FieldDescriptor fieldDescriptor) {
            this.builder.clearField(fieldDescriptor);
            return this;
        }

        public MergeTarget clearOneof(OneofDescriptor oneofDescriptor) {
            this.builder.clearOneof(oneofDescriptor);
            return this;
        }

        public ExtensionInfo findExtensionByName(ExtensionRegistry extensionRegistry, String str) {
            return extensionRegistry.findImmutableExtensionByName(str);
        }

        public ExtensionInfo findExtensionByNumber(ExtensionRegistry extensionRegistry, Descriptor descriptor, int i) {
            return extensionRegistry.findImmutableExtensionByNumber(descriptor, i);
        }

        public Object finish() {
            return this.builder.buildPartial();
        }

        public ContainerType getContainerType() {
            return ContainerType.MESSAGE;
        }

        public Descriptor getDescriptorForType() {
            return this.builder.getDescriptorForType();
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            return this.builder.getField(fieldDescriptor);
        }

        public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
            return this.builder.getOneofFieldDescriptor(oneofDescriptor);
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            return this.builder.hasField(fieldDescriptor);
        }

        public boolean hasOneof(OneofDescriptor oneofDescriptor) {
            return this.builder.hasOneof(oneofDescriptor);
        }

        public MergeTarget newMergeTargetForField(FieldDescriptor fieldDescriptor, Message message) {
            return message != null ? new BuilderAdapter(message.newBuilderForType()) : new BuilderAdapter(this.builder.newBuilderForField(fieldDescriptor));
        }

        public Object parseGroup(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) {
            Object newBuilderForType;
            if (message != null) {
                newBuilderForType = message.newBuilderForType();
            } else {
                Builder newBuilderForField = this.builder.newBuilderForField(fieldDescriptor);
            }
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    newBuilderForType.mergeFrom(message2);
                }
            }
            codedInputStream.readGroup(fieldDescriptor.getNumber(), newBuilderForType, extensionRegistryLite);
            return newBuilderForType.buildPartial();
        }

        public Object parseMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) {
            Object newBuilderForType;
            if (message != null) {
                newBuilderForType = message.newBuilderForType();
            } else {
                Builder newBuilderForField = this.builder.newBuilderForField(fieldDescriptor);
            }
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    newBuilderForType.mergeFrom(message2);
                }
            }
            codedInputStream.readMessage(newBuilderForType, extensionRegistryLite);
            return newBuilderForType.buildPartial();
        }

        public Object parseMessageFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) {
            Builder newBuilderForType = message != null ? message.newBuilderForType() : this.builder.newBuilderForField(fieldDescriptor);
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    newBuilderForType.mergeFrom(message2);
                }
            }
            newBuilderForType.mergeFrom(byteString, extensionRegistryLite);
            return newBuilderForType.buildPartial();
        }

        public Object readPrimitiveField(CodedInputStream codedInputStream, FieldType fieldType, boolean z) {
            return FieldSet.readPrimitiveField(codedInputStream, fieldType, z);
        }

        public MergeTarget setField(FieldDescriptor fieldDescriptor, Object obj) {
            this.builder.setField(fieldDescriptor, obj);
            return this;
        }

        public MergeTarget setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            this.builder.setRepeatedField(fieldDescriptor, i, obj);
            return this;
        }
    }

    class ExtensionAdapter implements MergeTarget {
        private final FieldSet extensions;

        ExtensionAdapter(FieldSet fieldSet) {
            this.extensions = fieldSet;
        }

        public MergeTarget addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            this.extensions.addRepeatedField(fieldDescriptor, obj);
            return this;
        }

        public MergeTarget clearField(FieldDescriptor fieldDescriptor) {
            this.extensions.clearField(fieldDescriptor);
            return this;
        }

        public MergeTarget clearOneof(OneofDescriptor oneofDescriptor) {
            return this;
        }

        public ExtensionInfo findExtensionByName(ExtensionRegistry extensionRegistry, String str) {
            return extensionRegistry.findImmutableExtensionByName(str);
        }

        public ExtensionInfo findExtensionByNumber(ExtensionRegistry extensionRegistry, Descriptor descriptor, int i) {
            return extensionRegistry.findImmutableExtensionByNumber(descriptor, i);
        }

        public Object finish() {
            throw new UnsupportedOperationException("finish() called on FieldSet object");
        }

        public ContainerType getContainerType() {
            return ContainerType.EXTENSION_SET;
        }

        public Descriptor getDescriptorForType() {
            throw new UnsupportedOperationException("getDescriptorForType() called on FieldSet object");
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            return this.extensions.getField(fieldDescriptor);
        }

        public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
            return null;
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            return this.extensions.hasField(fieldDescriptor);
        }

        public boolean hasOneof(OneofDescriptor oneofDescriptor) {
            return false;
        }

        public MergeTarget newMergeTargetForField(FieldDescriptor fieldDescriptor, Message message) {
            throw new UnsupportedOperationException("newMergeTargetForField() called on FieldSet object");
        }

        public Object parseGroup(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) {
            Object newBuilderForType = message.newBuilderForType();
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    newBuilderForType.mergeFrom(message2);
                }
            }
            codedInputStream.readGroup(fieldDescriptor.getNumber(), newBuilderForType, extensionRegistryLite);
            return newBuilderForType.buildPartial();
        }

        public Object parseMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) {
            Object newBuilderForType = message.newBuilderForType();
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    newBuilderForType.mergeFrom(message2);
                }
            }
            codedInputStream.readMessage(newBuilderForType, extensionRegistryLite);
            return newBuilderForType.buildPartial();
        }

        public Object parseMessageFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) {
            Builder newBuilderForType = message.newBuilderForType();
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    newBuilderForType.mergeFrom(message2);
                }
            }
            newBuilderForType.mergeFrom(byteString, extensionRegistryLite);
            return newBuilderForType.buildPartial();
        }

        public Object readPrimitiveField(CodedInputStream codedInputStream, FieldType fieldType, boolean z) {
            return FieldSet.readPrimitiveField(codedInputStream, fieldType, z);
        }

        public MergeTarget setField(FieldDescriptor fieldDescriptor, Object obj) {
            this.extensions.setField(fieldDescriptor, obj);
            return this;
        }

        public MergeTarget setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            this.extensions.setRepeatedField(fieldDescriptor, i, obj);
            return this;
        }
    }

    MessageReflection() {
    }

    static String delimitWithCommas(List list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : list) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    private static void eagerlyMergeMessageSetExtension(CodedInputStream codedInputStream, ExtensionInfo extensionInfo, ExtensionRegistryLite extensionRegistryLite, MergeTarget mergeTarget) {
        FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
        mergeTarget.setField(fieldDescriptor, mergeTarget.parseMessage(codedInputStream, extensionRegistryLite, fieldDescriptor, extensionInfo.defaultInstance));
    }

    static List findMissingFields(MessageOrBuilder messageOrBuilder) {
        List arrayList = new ArrayList();
        findMissingFields(messageOrBuilder, "", arrayList);
        return arrayList;
    }

    private static void findMissingFields(MessageOrBuilder messageOrBuilder, String str, List list) {
        for (FieldDescriptor fieldDescriptor : messageOrBuilder.getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !messageOrBuilder.hasField(fieldDescriptor)) {
                list.add(str + fieldDescriptor.getName());
            }
        }
        for (Entry entry : messageOrBuilder.getAllFields().entrySet()) {
            FieldDescriptor fieldDescriptor2 = (FieldDescriptor) entry.getKey();
            Object value = entry.getValue();
            if (fieldDescriptor2.getJavaType() == JavaType.MESSAGE) {
                if (fieldDescriptor2.isRepeated()) {
                    int i = 0;
                    for (MessageOrBuilder findMissingFields : (List) value) {
                        int i2 = i + 1;
                        findMissingFields(findMissingFields, subMessagePrefix(str, fieldDescriptor2, i), list);
                        i = i2;
                    }
                } else if (messageOrBuilder.hasField(fieldDescriptor2)) {
                    findMissingFields((MessageOrBuilder) value, subMessagePrefix(str, fieldDescriptor2, -1), list);
                }
            }
        }
    }

    static int getSerializedSize(Message message) {
        boolean messageSetWireFormat = message.getDescriptorForType().getOptions().getMessageSetWireFormat();
        int i = 0;
        for (Entry entry : message.getAllFields().entrySet()) {
            FieldDescriptor fieldDescriptor = (FieldDescriptor) entry.getKey();
            Object value = entry.getValue();
            int computeMessageSetExtensionSize = (messageSetWireFormat && fieldDescriptor.isExtension() && fieldDescriptor.getType() == Type.MESSAGE && !fieldDescriptor.isRepeated()) ? CodedOutputStream.computeMessageSetExtensionSize(fieldDescriptor.getNumber(), (Message) value) + i : FieldSet.computeFieldSize(fieldDescriptor, value) + i;
            i = computeMessageSetExtensionSize;
        }
        UnknownFieldSet unknownFields = message.getUnknownFields();
        return messageSetWireFormat ? unknownFields.getSerializedSizeAsMessageSet() + i : unknownFields.getSerializedSize() + i;
    }

    static boolean isInitialized(MessageOrBuilder messageOrBuilder) {
        for (FieldDescriptor fieldDescriptor : messageOrBuilder.getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !messageOrBuilder.hasField(fieldDescriptor)) {
                return false;
            }
        }
        for (Entry entry : messageOrBuilder.getAllFields().entrySet()) {
            FieldDescriptor fieldDescriptor2 = (FieldDescriptor) entry.getKey();
            if (fieldDescriptor2.getJavaType() == JavaType.MESSAGE) {
                if (fieldDescriptor2.isRepeated()) {
                    for (Message isInitialized : (List) entry.getValue()) {
                        if (!isInitialized.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (!((Message) entry.getValue()).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean mergeFieldFrom(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, Descriptor descriptor, MergeTarget mergeTarget, int i) {
        boolean z = false;
        FieldDescriptor fieldDescriptor = null;
        if (descriptor.getOptions().getMessageSetWireFormat() && i == WireFormat.MESSAGE_SET_ITEM_TAG) {
            mergeMessageSetExtensionFromCodedStream(codedInputStream, builder, extensionRegistryLite, descriptor, mergeTarget);
            return true;
        }
        Message message;
        boolean z2;
        int tagWireType = WireFormat.getTagWireType(i);
        int tagFieldNumber = WireFormat.getTagFieldNumber(i);
        if (descriptor.isExtensionNumber(tagFieldNumber)) {
            if (extensionRegistryLite instanceof ExtensionRegistry) {
                Message message2;
                ExtensionInfo findExtensionByNumber = mergeTarget.findExtensionByNumber((ExtensionRegistry) extensionRegistryLite, descriptor, tagFieldNumber);
                if (findExtensionByNumber == null) {
                    message2 = null;
                } else {
                    fieldDescriptor = findExtensionByNumber.descriptor;
                    message2 = findExtensionByNumber.defaultInstance;
                    if (message2 == null && fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                        throw new IllegalStateException("Message-typed extension lacked default instance: " + fieldDescriptor.getFullName());
                    }
                }
                message = message2;
            } else {
                message = null;
            }
        } else if (mergeTarget.getContainerType() == ContainerType.MESSAGE) {
            message = null;
            fieldDescriptor = descriptor.findFieldByNumber(tagFieldNumber);
        } else {
            message = null;
        }
        if (fieldDescriptor == null) {
            z2 = true;
        } else if (tagWireType == FieldSet.getWireFormatForFieldType(fieldDescriptor.getLiteType(), false)) {
            z2 = false;
        } else if (fieldDescriptor.isPackable() && tagWireType == FieldSet.getWireFormatForFieldType(fieldDescriptor.getLiteType(), true)) {
            z2 = false;
            z = true;
        } else {
            z2 = true;
        }
        if (z2) {
            return builder.mergeFieldFrom(i, codedInputStream);
        }
        if (z) {
            int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
            if (fieldDescriptor.getLiteType() == FieldType.ENUM) {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    EnumValueDescriptor findValueByNumber = fieldDescriptor.getEnumType().findValueByNumber(codedInputStream.readEnum());
                    if (findValueByNumber == null) {
                        return true;
                    }
                    mergeTarget.addRepeatedField(fieldDescriptor, findValueByNumber);
                }
            } else {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    mergeTarget.addRepeatedField(fieldDescriptor, mergeTarget.readPrimitiveField(codedInputStream, fieldDescriptor.getLiteType(), fieldDescriptor.needsUtf8Check()));
                }
            }
            codedInputStream.popLimit(pushLimit);
        } else {
            Object parseGroup;
            switch (fieldDescriptor.getType()) {
                case GROUP:
                    parseGroup = mergeTarget.parseGroup(codedInputStream, extensionRegistryLite, fieldDescriptor, message);
                    break;
                case MESSAGE:
                    parseGroup = mergeTarget.parseMessage(codedInputStream, extensionRegistryLite, fieldDescriptor, message);
                    break;
                case ENUM:
                    int readEnum = codedInputStream.readEnum();
                    parseGroup = fieldDescriptor.getEnumType().findValueByNumber(readEnum);
                    if (parseGroup == null) {
                        builder.mergeVarintField(tagFieldNumber, readEnum);
                        return true;
                    }
                    break;
                default:
                    parseGroup = mergeTarget.readPrimitiveField(codedInputStream, fieldDescriptor.getLiteType(), fieldDescriptor.needsUtf8Check());
                    break;
            }
            if (fieldDescriptor.isRepeated()) {
                mergeTarget.addRepeatedField(fieldDescriptor, parseGroup);
            } else {
                mergeTarget.setField(fieldDescriptor, parseGroup);
            }
        }
        return true;
    }

    private static void mergeMessageSetExtensionFromBytes(ByteString byteString, ExtensionInfo extensionInfo, ExtensionRegistryLite extensionRegistryLite, MergeTarget mergeTarget) {
        FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
        if (mergeTarget.hasField(fieldDescriptor) || ExtensionRegistryLite.isEagerlyParseMessageSets()) {
            mergeTarget.setField(fieldDescriptor, mergeTarget.parseMessageFromBytes(byteString, extensionRegistryLite, fieldDescriptor, extensionInfo.defaultInstance));
        } else {
            mergeTarget.setField(fieldDescriptor, new LazyField(extensionInfo.defaultInstance, extensionRegistryLite, byteString));
        }
    }

    private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, Descriptor descriptor, MergeTarget mergeTarget) {
        ByteString byteString = null;
        ExtensionInfo extensionInfo = null;
        int i = 0;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                i = codedInputStream.readUInt32();
                if (i != 0 && (extensionRegistryLite instanceof ExtensionRegistry)) {
                    extensionInfo = mergeTarget.findExtensionByNumber((ExtensionRegistry) extensionRegistryLite, descriptor, i);
                }
            } else if (readTag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if (i == 0 || extensionInfo == null || !ExtensionRegistryLite.isEagerlyParseMessageSets()) {
                    byteString = codedInputStream.readBytes();
                } else {
                    eagerlyMergeMessageSetExtension(codedInputStream, extensionInfo, extensionRegistryLite, mergeTarget);
                    byteString = null;
                }
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        codedInputStream.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
        if (byteString != null && i != 0) {
            if (extensionInfo != null) {
                mergeMessageSetExtensionFromBytes(byteString, extensionInfo, extensionRegistryLite, mergeTarget);
            } else if (byteString != null) {
                builder.mergeField(i, Field.newBuilder().addLengthDelimited(byteString).build());
            }
        }
    }

    private static String subMessagePrefix(String str, FieldDescriptor fieldDescriptor, int i) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (fieldDescriptor.isExtension()) {
            stringBuilder.append('(').append(fieldDescriptor.getFullName()).append(')');
        } else {
            stringBuilder.append(fieldDescriptor.getName());
        }
        if (i != -1) {
            stringBuilder.append('[').append(i).append(']');
        }
        stringBuilder.append('.');
        return stringBuilder.toString();
    }

    static void writeMessageTo(Message message, CodedOutputStream codedOutputStream, boolean z) {
        boolean messageSetWireFormat = message.getDescriptorForType().getOptions().getMessageSetWireFormat();
        Map allFields = message.getAllFields();
        if (z) {
            Map treeMap = new TreeMap(allFields);
            for (FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !treeMap.containsKey(fieldDescriptor)) {
                    treeMap.put(fieldDescriptor, message.getField(fieldDescriptor));
                }
            }
            allFields = treeMap;
        }
        for (Entry entry : r0.entrySet()) {
            FieldDescriptor fieldDescriptor2 = (FieldDescriptor) entry.getKey();
            Object value = entry.getValue();
            if (messageSetWireFormat && fieldDescriptor2.isExtension() && fieldDescriptor2.getType() == Type.MESSAGE && !fieldDescriptor2.isRepeated()) {
                codedOutputStream.writeMessageSetExtension(fieldDescriptor2.getNumber(), (Message) value);
            } else {
                FieldSet.writeField(fieldDescriptor2, value, codedOutputStream);
            }
        }
        UnknownFieldSet unknownFields = message.getUnknownFields();
        if (messageSetWireFormat) {
            unknownFields.writeAsMessageSetTo(codedOutputStream);
        } else {
            unknownFields.writeTo(codedOutputStream);
        }
    }
}
