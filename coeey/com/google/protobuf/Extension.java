package com.google.protobuf;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.WireFormat.FieldType;

public abstract class Extension {

    public enum ExtensionType {
        IMMUTABLE,
        MUTABLE,
        PROTO1
    }

    public enum MessageType {
        PROTO1,
        PROTO2
    }

    protected abstract Object fromReflectionType(Object obj);

    public abstract Object getDefaultValue();

    public abstract FieldDescriptor getDescriptor();

    protected ExtensionType getExtensionType() {
        return ExtensionType.IMMUTABLE;
    }

    public abstract FieldType getLiteType();

    public abstract MessageLite getMessageDefaultInstance();

    public MessageType getMessageType() {
        return MessageType.PROTO2;
    }

    public abstract int getNumber();

    public abstract boolean isRepeated();

    protected abstract Object singularFromReflectionType(Object obj);

    protected abstract Object singularToReflectionType(Object obj);

    protected abstract Object toReflectionType(Object obj);
}
