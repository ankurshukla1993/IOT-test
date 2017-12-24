package com.google.protobuf;

import com.google.protobuf.FieldSet.FieldDescriptorLite;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.WireFormat.FieldType;
import com.google.protobuf.WireFormat.JavaType;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public abstract class GeneratedMessageLite extends AbstractMessageLite implements Serializable {
    private static final long serialVersionUID = 1;

    public abstract class Builder extends com.google.protobuf.AbstractMessageLite.Builder {
        private ByteString unknownFields = ByteString.EMPTY;

        protected Builder() {
        }

        public Builder clear() {
            this.unknownFields = ByteString.EMPTY;
            return this;
        }

        public Builder clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        public abstract GeneratedMessageLite getDefaultInstanceForType();

        public final ByteString getUnknownFields() {
            return this.unknownFields;
        }

        public abstract Builder mergeFrom(GeneratedMessageLite generatedMessageLite);

        protected boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i) {
            return codedInputStream.skipField(i, codedOutputStream);
        }

        public final Builder setUnknownFields(ByteString byteString) {
            this.unknownFields = byteString;
            return this;
        }
    }

    public interface ExtendableMessageOrBuilder extends MessageLiteOrBuilder {
        Object getExtension(GeneratedExtension generatedExtension);

        Object getExtension(GeneratedExtension generatedExtension, int i);

        int getExtensionCount(GeneratedExtension generatedExtension);

        boolean hasExtension(GeneratedExtension generatedExtension);
    }

    public abstract class ExtendableBuilder extends Builder implements ExtendableMessageOrBuilder {
        private FieldSet extensions = FieldSet.emptySet();
        private boolean extensionsIsMutable;

        protected ExtendableBuilder() {
        }

        private FieldSet buildExtensions() {
            this.extensions.makeImmutable();
            this.extensionsIsMutable = false;
            return this.extensions;
        }

        private void ensureExtensionsIsMutable() {
            if (!this.extensionsIsMutable) {
                this.extensions = this.extensions.clone();
                this.extensionsIsMutable = true;
            }
        }

        private void verifyExtensionContainingType(GeneratedExtension generatedExtension) {
            if (generatedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        public final ExtendableBuilder addExtension(GeneratedExtension generatedExtension, Object obj) {
            verifyExtensionContainingType(generatedExtension);
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(generatedExtension.descriptor, generatedExtension.singularToFieldSetType(obj));
            return this;
        }

        public ExtendableBuilder clear() {
            this.extensions.clear();
            this.extensionsIsMutable = false;
            return (ExtendableBuilder) super.clear();
        }

        public final ExtendableBuilder clearExtension(GeneratedExtension generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            ensureExtensionsIsMutable();
            this.extensions.clearField(generatedExtension.descriptor);
            return this;
        }

        public ExtendableBuilder clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        public final Object getExtension(GeneratedExtension generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            Object field = this.extensions.getField(generatedExtension.descriptor);
            return field == null ? generatedExtension.defaultValue : generatedExtension.fromFieldSetType(field);
        }

        public final Object getExtension(GeneratedExtension generatedExtension, int i) {
            verifyExtensionContainingType(generatedExtension);
            return generatedExtension.singularFromFieldSetType(this.extensions.getRepeatedField(generatedExtension.descriptor, i));
        }

        public final int getExtensionCount(GeneratedExtension generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.getRepeatedFieldCount(generatedExtension.descriptor);
        }

        public final boolean hasExtension(GeneratedExtension generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.hasField(generatedExtension.descriptor);
        }

        void internalSetExtensionSet(FieldSet fieldSet) {
            this.extensions = fieldSet;
        }

        protected final void mergeExtensionFields(ExtendableMessage extendableMessage) {
            ensureExtensionsIsMutable();
            this.extensions.mergeFrom(extendableMessage.extensions);
        }

        protected boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i) {
            ensureExtensionsIsMutable();
            return GeneratedMessageLite.parseUnknownField(this.extensions, getDefaultInstanceForType(), codedInputStream, codedOutputStream, extensionRegistryLite, i);
        }

        public final ExtendableBuilder setExtension(GeneratedExtension generatedExtension, int i, Object obj) {
            verifyExtensionContainingType(generatedExtension);
            ensureExtensionsIsMutable();
            this.extensions.setRepeatedField(generatedExtension.descriptor, i, generatedExtension.singularToFieldSetType(obj));
            return this;
        }

        public final ExtendableBuilder setExtension(GeneratedExtension generatedExtension, Object obj) {
            verifyExtensionContainingType(generatedExtension);
            ensureExtensionsIsMutable();
            this.extensions.setField(generatedExtension.descriptor, generatedExtension.toFieldSetType(obj));
            return this;
        }
    }

    public abstract class ExtendableMessage extends GeneratedMessageLite implements ExtendableMessageOrBuilder {
        private final FieldSet extensions;

        public class ExtensionWriter {
            private final Iterator iter;
            private final boolean messageSetWireFormat;
            private Entry next;

            private ExtensionWriter(boolean z) {
                this.iter = ExtendableMessage.this.extensions.iterator();
                if (this.iter.hasNext()) {
                    this.next = (Entry) this.iter.next();
                }
                this.messageSetWireFormat = z;
            }

            public void writeUntil(int i, CodedOutputStream codedOutputStream) {
                while (this.next != null && ((ExtensionDescriptor) this.next.getKey()).getNumber() < i) {
                    ExtensionDescriptor extensionDescriptor = (ExtensionDescriptor) this.next.getKey();
                    if (this.messageSetWireFormat && extensionDescriptor.getLiteJavaType() == JavaType.MESSAGE && !extensionDescriptor.isRepeated()) {
                        codedOutputStream.writeMessageSetExtension(extensionDescriptor.getNumber(), (MessageLite) this.next.getValue());
                    } else {
                        FieldSet.writeField(extensionDescriptor, this.next.getValue(), codedOutputStream);
                    }
                    if (this.iter.hasNext()) {
                        this.next = (Entry) this.iter.next();
                    } else {
                        this.next = null;
                    }
                }
            }
        }

        protected ExtendableMessage() {
            this.extensions = FieldSet.newFieldSet();
        }

        protected ExtendableMessage(ExtendableBuilder extendableBuilder) {
            this.extensions = extendableBuilder.buildExtensions();
        }

        private void verifyExtensionContainingType(GeneratedExtension generatedExtension) {
            if (generatedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        protected int extensionsSerializedSize() {
            return this.extensions.getSerializedSize();
        }

        protected int extensionsSerializedSizeAsMessageSet() {
            return this.extensions.getMessageSetSerializedSize();
        }

        public final Object getExtension(GeneratedExtension generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            Object field = this.extensions.getField(generatedExtension.descriptor);
            return field == null ? generatedExtension.defaultValue : generatedExtension.fromFieldSetType(field);
        }

        public final Object getExtension(GeneratedExtension generatedExtension, int i) {
            verifyExtensionContainingType(generatedExtension);
            return generatedExtension.singularFromFieldSetType(this.extensions.getRepeatedField(generatedExtension.descriptor, i));
        }

        public final int getExtensionCount(GeneratedExtension generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.getRepeatedFieldCount(generatedExtension.descriptor);
        }

        public final boolean hasExtension(GeneratedExtension generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.hasField(generatedExtension.descriptor);
        }

        protected void makeExtensionsImmutable() {
            this.extensions.makeImmutable();
        }

        protected ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(false);
        }

        protected ExtensionWriter newMessageSetExtensionWriter() {
            return new ExtensionWriter(true);
        }

        protected boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i) {
            return GeneratedMessageLite.parseUnknownField(this.extensions, getDefaultInstanceForType(), codedInputStream, codedOutputStream, extensionRegistryLite, i);
        }
    }

    final class ExtensionDescriptor implements FieldDescriptorLite {
        final EnumLiteMap enumTypeMap;
        final boolean isPacked;
        final boolean isRepeated;
        final int number;
        final FieldType type;

        ExtensionDescriptor(EnumLiteMap enumLiteMap, int i, FieldType fieldType, boolean z, boolean z2) {
            this.enumTypeMap = enumLiteMap;
            this.number = i;
            this.type = fieldType;
            this.isRepeated = z;
            this.isPacked = z2;
        }

        public int compareTo(ExtensionDescriptor extensionDescriptor) {
            return this.number - extensionDescriptor.number;
        }

        public EnumLiteMap getEnumType() {
            return this.enumTypeMap;
        }

        public JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        public FieldType getLiteType() {
            return this.type;
        }

        public int getNumber() {
            return this.number;
        }

        public com.google.protobuf.MessageLite.Builder internalMergeFrom(com.google.protobuf.MessageLite.Builder builder, MessageLite messageLite) {
            return ((Builder) builder).mergeFrom((GeneratedMessageLite) messageLite);
        }

        public boolean isPacked() {
            return this.isPacked;
        }

        public boolean isRepeated() {
            return this.isRepeated;
        }
    }

    public class GeneratedExtension {
        final MessageLite containingTypeDefaultInstance;
        final Object defaultValue;
        final ExtensionDescriptor descriptor;
        final Method enumValueOf;
        final MessageLite messageDefaultInstance;
        final Class singularType;

        GeneratedExtension(MessageLite messageLite, Object obj, MessageLite messageLite2, ExtensionDescriptor extensionDescriptor, Class cls) {
            if (messageLite == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (extensionDescriptor.getLiteType() == FieldType.MESSAGE && messageLite2 == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.containingTypeDefaultInstance = messageLite;
                this.defaultValue = obj;
                this.messageDefaultInstance = messageLite2;
                this.descriptor = extensionDescriptor;
                this.singularType = cls;
                if (EnumLite.class.isAssignableFrom(cls)) {
                    this.enumValueOf = GeneratedMessageLite.getMethodOrDie(cls, "valueOf", Integer.TYPE);
                    return;
                }
                this.enumValueOf = null;
            }
        }

        Object fromFieldSetType(Object obj) {
            if (!this.descriptor.isRepeated()) {
                return singularFromFieldSetType(obj);
            }
            if (this.descriptor.getLiteJavaType() != JavaType.ENUM) {
                return obj;
            }
            List arrayList = new ArrayList();
            for (Object singularFromFieldSetType : (List) obj) {
                arrayList.add(singularFromFieldSetType(singularFromFieldSetType));
            }
            return arrayList;
        }

        public MessageLite getContainingTypeDefaultInstance() {
            return this.containingTypeDefaultInstance;
        }

        public MessageLite getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        public int getNumber() {
            return this.descriptor.getNumber();
        }

        Object singularFromFieldSetType(Object obj) {
            if (this.descriptor.getLiteJavaType() != JavaType.ENUM) {
                return obj;
            }
            return GeneratedMessageLite.invokeOrDie(this.enumValueOf, null, (Integer) obj);
        }

        Object singularToFieldSetType(Object obj) {
            return this.descriptor.getLiteJavaType() == JavaType.ENUM ? Integer.valueOf(((EnumLite) obj).getNumber()) : obj;
        }

        Object toFieldSetType(Object obj) {
            if (!this.descriptor.isRepeated()) {
                return singularToFieldSetType(obj);
            }
            if (this.descriptor.getLiteJavaType() != JavaType.ENUM) {
                return obj;
            }
            List arrayList = new ArrayList();
            for (Object singularToFieldSetType : (List) obj) {
                arrayList.add(singularToFieldSetType(singularToFieldSetType));
            }
            return arrayList;
        }
    }

    final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private byte[] asBytes;
        private String messageClassName;

        SerializedForm(MessageLite messageLite) {
            this.messageClassName = messageLite.getClass().getName();
            this.asBytes = messageLite.toByteArray();
        }

        protected Object readResolve() {
            try {
                com.google.protobuf.MessageLite.Builder builder = (com.google.protobuf.MessageLite.Builder) Class.forName(this.messageClassName).getMethod("newBuilder", new Class[0]).invoke(null, new Object[0]);
                builder.mergeFrom(this.asBytes);
                return builder.buildPartial();
            } catch (Throwable e) {
                throw new RuntimeException("Unable to find proto buffer class", e);
            } catch (Throwable e2) {
                throw new RuntimeException("Unable to find newBuilder method", e2);
            } catch (Throwable e22) {
                throw new RuntimeException("Unable to call newBuilder method", e22);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("Error calling newBuilder", e3.getCause());
            } catch (Throwable e222) {
                throw new RuntimeException("Unable to understand proto buffer", e222);
            }
        }
    }

    protected GeneratedMessageLite() {
    }

    protected GeneratedMessageLite(Builder builder) {
    }

    static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (Throwable e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    static Object invokeOrDie(Method method, Object obj, Object... objArr) {
        Throwable e;
        try {
            return method.invoke(obj, objArr);
        } catch (Throwable e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            e2 = e3.getCause();
            if (e2 instanceof RuntimeException) {
                throw ((RuntimeException) e2);
            } else if (e2 instanceof Error) {
                throw ((Error) e2);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", e2);
            }
        }
    }

    public static GeneratedExtension newRepeatedGeneratedExtension(MessageLite messageLite, MessageLite messageLite2, EnumLiteMap enumLiteMap, int i, FieldType fieldType, boolean z, Class cls) {
        return new GeneratedExtension(messageLite, Collections.emptyList(), messageLite2, new ExtensionDescriptor(enumLiteMap, i, fieldType, true, z), cls);
    }

    public static GeneratedExtension newSingularGeneratedExtension(MessageLite messageLite, Object obj, MessageLite messageLite2, EnumLiteMap enumLiteMap, int i, FieldType fieldType, Class cls) {
        return new GeneratedExtension(messageLite, obj, messageLite2, new ExtensionDescriptor(enumLiteMap, i, fieldType, false, false), cls);
    }

    private static boolean parseUnknownField(FieldSet fieldSet, MessageLite messageLite, CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i) {
        boolean z;
        boolean z2;
        int tagWireType = WireFormat.getTagWireType(i);
        GeneratedExtension findLiteExtensionByNumber = extensionRegistryLite.findLiteExtensionByNumber(messageLite, WireFormat.getTagFieldNumber(i));
        if (findLiteExtensionByNumber == null) {
            z = true;
            z2 = false;
        } else if (tagWireType == FieldSet.getWireFormatForFieldType(findLiteExtensionByNumber.descriptor.getLiteType(), false)) {
            z = false;
            z2 = false;
        } else if (findLiteExtensionByNumber.descriptor.isRepeated && findLiteExtensionByNumber.descriptor.type.isPackable() && tagWireType == FieldSet.getWireFormatForFieldType(findLiteExtensionByNumber.descriptor.getLiteType(), true)) {
            z = false;
            z2 = true;
        } else {
            z = true;
            z2 = false;
        }
        if (z) {
            return codedInputStream.skipField(i, codedOutputStream);
        }
        if (z2) {
            tagWireType = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
            if (findLiteExtensionByNumber.descriptor.getLiteType() == FieldType.ENUM) {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    EnumLite findValueByNumber = findLiteExtensionByNumber.descriptor.getEnumType().findValueByNumber(codedInputStream.readEnum());
                    if (findValueByNumber == null) {
                        return true;
                    }
                    fieldSet.addRepeatedField(findLiteExtensionByNumber.descriptor, findLiteExtensionByNumber.singularToFieldSetType(findValueByNumber));
                }
            } else {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    fieldSet.addRepeatedField(findLiteExtensionByNumber.descriptor, FieldSet.readPrimitiveField(codedInputStream, findLiteExtensionByNumber.descriptor.getLiteType(), false));
                }
            }
            codedInputStream.popLimit(tagWireType);
        } else {
            Object build;
            switch (findLiteExtensionByNumber.descriptor.getLiteJavaType()) {
                case MESSAGE:
                    com.google.protobuf.MessageLite.Builder toBuilder;
                    if (!findLiteExtensionByNumber.descriptor.isRepeated()) {
                        MessageLite messageLite2 = (MessageLite) fieldSet.getField(findLiteExtensionByNumber.descriptor);
                        if (messageLite2 != null) {
                            toBuilder = messageLite2.toBuilder();
                            if (toBuilder == null) {
                                toBuilder = findLiteExtensionByNumber.getMessageDefaultInstance().newBuilderForType();
                            }
                            if (findLiteExtensionByNumber.descriptor.getLiteType() != FieldType.GROUP) {
                                codedInputStream.readGroup(findLiteExtensionByNumber.getNumber(), toBuilder, extensionRegistryLite);
                            } else {
                                codedInputStream.readMessage(toBuilder, extensionRegistryLite);
                            }
                            build = toBuilder.build();
                            break;
                        }
                    }
                    toBuilder = null;
                    if (toBuilder == null) {
                        toBuilder = findLiteExtensionByNumber.getMessageDefaultInstance().newBuilderForType();
                    }
                    if (findLiteExtensionByNumber.descriptor.getLiteType() != FieldType.GROUP) {
                        codedInputStream.readMessage(toBuilder, extensionRegistryLite);
                    } else {
                        codedInputStream.readGroup(findLiteExtensionByNumber.getNumber(), toBuilder, extensionRegistryLite);
                    }
                    build = toBuilder.build();
                case ENUM:
                    int readEnum = codedInputStream.readEnum();
                    build = findLiteExtensionByNumber.descriptor.getEnumType().findValueByNumber(readEnum);
                    if (build == null) {
                        codedOutputStream.writeRawVarint32(i);
                        codedOutputStream.writeUInt32NoTag(readEnum);
                        return true;
                    }
                    break;
                default:
                    build = FieldSet.readPrimitiveField(codedInputStream, findLiteExtensionByNumber.descriptor.getLiteType(), false);
                    break;
            }
            if (findLiteExtensionByNumber.descriptor.isRepeated()) {
                fieldSet.addRepeatedField(findLiteExtensionByNumber.descriptor, findLiteExtensionByNumber.singularToFieldSetType(build));
            } else {
                fieldSet.setField(findLiteExtensionByNumber.descriptor, findLiteExtensionByNumber.singularToFieldSetType(build));
            }
        }
        return true;
    }

    public Parser getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    protected void makeExtensionsImmutable() {
    }

    protected boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i) {
        return codedInputStream.skipField(i, codedOutputStream);
    }

    protected Object writeReplace() {
        return new SerializedForm(this);
    }
}
