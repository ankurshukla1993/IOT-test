package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.Extension.ExtensionType;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.WireFormat.FieldType;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public abstract class GeneratedMessage extends AbstractMessage implements Serializable {
    protected static boolean alwaysUseFieldBuilders = false;
    private static final long serialVersionUID = 1;

    public abstract class Builder extends com.google.protobuf.AbstractMessage.Builder {
        private BuilderParent builderParent;
        private boolean isClean;
        private BuilderParentImpl meAsParent;
        private UnknownFieldSet unknownFields;

        class BuilderParentImpl implements BuilderParent {
            private BuilderParentImpl() {
            }

            public void markDirty() {
                Builder.this.onChanged();
            }
        }

        protected Builder() {
            this(null);
        }

        protected Builder(BuilderParent builderParent) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.builderParent = builderParent;
        }

        private Map getAllFieldsMutable() {
            Map treeMap = new TreeMap();
            for (FieldDescriptor fieldDescriptor : internalGetFieldAccessorTable().descriptor.getFields()) {
                if (fieldDescriptor.isRepeated()) {
                    List list = (List) getField(fieldDescriptor);
                    if (!list.isEmpty()) {
                        treeMap.put(fieldDescriptor, list);
                    }
                } else if (hasField(fieldDescriptor)) {
                    treeMap.put(fieldDescriptor, getField(fieldDescriptor));
                }
            }
            return treeMap;
        }

        public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).addRepeated(this, obj);
            return this;
        }

        public Builder clear() {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            onChanged();
            return this;
        }

        public Builder clearField(FieldDescriptor fieldDescriptor) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).clear(this);
            return this;
        }

        public Builder clearOneof(OneofDescriptor oneofDescriptor) {
            internalGetFieldAccessorTable().getOneof(oneofDescriptor).clear(this);
            return this;
        }

        public Builder clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        void dispose() {
            this.builderParent = null;
        }

        public Map getAllFields() {
            return Collections.unmodifiableMap(getAllFieldsMutable());
        }

        public Descriptor getDescriptorForType() {
            return internalGetFieldAccessorTable().descriptor;
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            Object obj = internalGetFieldAccessorTable().getField(fieldDescriptor).get(this);
            return fieldDescriptor.isRepeated() ? Collections.unmodifiableList((List) obj) : obj;
        }

        public com.google.protobuf.Message.Builder getFieldBuilder(FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getBuilder(this);
        }

        public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
            return internalGetFieldAccessorTable().getOneof(oneofDescriptor).get(this);
        }

        protected BuilderParent getParentForChildren() {
            if (this.meAsParent == null) {
                this.meAsParent = new BuilderParentImpl();
            }
            return this.meAsParent;
        }

        public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeated(this, i);
        }

        public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeatedCount(this);
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).has(this);
        }

        public boolean hasOneof(OneofDescriptor oneofDescriptor) {
            return internalGetFieldAccessorTable().getOneof(oneofDescriptor).has(this);
        }

        protected abstract FieldAccessorTable internalGetFieldAccessorTable();

        protected boolean isClean() {
            return this.isClean;
        }

        public boolean isInitialized() {
            for (FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                    return false;
                }
                if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                    if (fieldDescriptor.isRepeated()) {
                        for (Message isInitialized : (List) getField(fieldDescriptor)) {
                            if (!isInitialized.isInitialized()) {
                                return false;
                            }
                        }
                        continue;
                    } else if (hasField(fieldDescriptor) && !((Message) getField(fieldDescriptor)).isInitialized()) {
                        return false;
                    }
                }
            }
            return true;
        }

        protected void markClean() {
            this.isClean = true;
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFieldSet).build();
            onChanged();
            return this;
        }

        public com.google.protobuf.Message.Builder newBuilderForField(FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).newBuilder();
        }

        protected void onBuilt() {
            if (this.builderParent != null) {
                markClean();
            }
        }

        protected final void onChanged() {
            if (this.isClean && this.builderParent != null) {
                this.builderParent.markDirty();
                this.isClean = false;
            }
        }

        protected boolean parseUnknownField(CodedInputStream codedInputStream, com.google.protobuf.UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) {
            return builder.mergeFieldFrom(i, codedInputStream);
        }

        public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).set(this, obj);
            return this;
        }

        public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).setRepeated(this, i, obj);
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = unknownFieldSet;
            onChanged();
            return this;
        }
    }

    public interface ExtendableMessageOrBuilder extends MessageOrBuilder {
        Message getDefaultInstanceForType();

        Object getExtension(Extension extension);

        Object getExtension(Extension extension, int i);

        int getExtensionCount(Extension extension);

        boolean hasExtension(Extension extension);
    }

    public abstract class ExtendableBuilder extends Builder implements ExtendableMessageOrBuilder {
        private FieldSet extensions = FieldSet.emptySet();

        protected ExtendableBuilder() {
        }

        protected ExtendableBuilder(BuilderParent builderParent) {
            super(builderParent);
        }

        private FieldSet buildExtensions() {
            this.extensions.makeImmutable();
            return this.extensions;
        }

        private void ensureExtensionsIsMutable() {
            if (this.extensions.isImmutable()) {
                this.extensions = this.extensions.clone();
            }
        }

        private void verifyContainingType(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyExtensionContainingType(Extension extension) {
            if (extension.getDescriptor().getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("Extension is for type \"" + extension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + getDescriptorForType().getFullName() + "\".");
            }
        }

        public final ExtendableBuilder addExtension(Extension extension, Object obj) {
            verifyExtensionContainingType(extension);
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(extension.getDescriptor(), extension.singularToReflectionType(obj));
            onChanged();
            return this;
        }

        public ExtendableBuilder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            if (!fieldDescriptor.isExtension()) {
                return (ExtendableBuilder) super.addRepeatedField(fieldDescriptor, obj);
            }
            verifyContainingType(fieldDescriptor);
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(fieldDescriptor, obj);
            onChanged();
            return this;
        }

        public ExtendableBuilder clear() {
            this.extensions = FieldSet.emptySet();
            return (ExtendableBuilder) super.clear();
        }

        public final ExtendableBuilder clearExtension(Extension extension) {
            verifyExtensionContainingType(extension);
            ensureExtensionsIsMutable();
            this.extensions.clearField(extension.getDescriptor());
            onChanged();
            return this;
        }

        public ExtendableBuilder clearField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return (ExtendableBuilder) super.clearField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            ensureExtensionsIsMutable();
            this.extensions.clearField(fieldDescriptor);
            onChanged();
            return this;
        }

        public ExtendableBuilder clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        public Map getAllFields() {
            Map access$800 = getAllFieldsMutable();
            access$800.putAll(this.extensions.getAllFields());
            return Collections.unmodifiableMap(access$800);
        }

        public final Object getExtension(Extension extension) {
            verifyExtensionContainingType(extension);
            FieldDescriptor descriptor = extension.getDescriptor();
            Object field = this.extensions.getField(descriptor);
            return field == null ? descriptor.isRepeated() ? Collections.emptyList() : descriptor.getJavaType() == JavaType.MESSAGE ? extension.getMessageDefaultInstance() : extension.fromReflectionType(descriptor.getDefaultValue()) : extension.fromReflectionType(field);
        }

        public final Object getExtension(Extension extension, int i) {
            verifyExtensionContainingType(extension);
            return extension.singularFromReflectionType(this.extensions.getRepeatedField(extension.getDescriptor(), i));
        }

        public final int getExtensionCount(Extension extension) {
            verifyExtensionContainingType(extension);
            return this.extensions.getRepeatedFieldCount(extension.getDescriptor());
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            Object field = this.extensions.getField(fieldDescriptor);
            return field == null ? fieldDescriptor.getJavaType() == JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : field;
        }

        public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedField(fieldDescriptor, i);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedField(fieldDescriptor, i);
        }

        public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedFieldCount(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedFieldCount(fieldDescriptor);
        }

        public final boolean hasExtension(Extension extension) {
            verifyExtensionContainingType(extension);
            return this.extensions.hasField(extension.getDescriptor());
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.hasField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.hasField(fieldDescriptor);
        }

        void internalSetExtensionSet(FieldSet fieldSet) {
            this.extensions = fieldSet;
        }

        public boolean isInitialized() {
            return super.isInitialized() && extensionsAreInitialized();
        }

        protected final void mergeExtensionFields(ExtendableMessage extendableMessage) {
            ensureExtensionsIsMutable();
            this.extensions.mergeFrom(extendableMessage.extensions);
            onChanged();
        }

        protected boolean parseUnknownField(CodedInputStream codedInputStream, com.google.protobuf.UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) {
            return MessageReflection.mergeFieldFrom(codedInputStream, builder, extensionRegistryLite, getDescriptorForType(), new BuilderAdapter(this), i);
        }

        public final ExtendableBuilder setExtension(Extension extension, int i, Object obj) {
            verifyExtensionContainingType(extension);
            ensureExtensionsIsMutable();
            this.extensions.setRepeatedField(extension.getDescriptor(), i, extension.singularToReflectionType(obj));
            onChanged();
            return this;
        }

        public final ExtendableBuilder setExtension(Extension extension, Object obj) {
            verifyExtensionContainingType(extension);
            ensureExtensionsIsMutable();
            this.extensions.setField(extension.getDescriptor(), extension.toReflectionType(obj));
            onChanged();
            return this;
        }

        public ExtendableBuilder setField(FieldDescriptor fieldDescriptor, Object obj) {
            if (!fieldDescriptor.isExtension()) {
                return (ExtendableBuilder) super.setField(fieldDescriptor, obj);
            }
            verifyContainingType(fieldDescriptor);
            ensureExtensionsIsMutable();
            this.extensions.setField(fieldDescriptor, obj);
            onChanged();
            return this;
        }

        public ExtendableBuilder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            if (!fieldDescriptor.isExtension()) {
                return (ExtendableBuilder) super.setRepeatedField(fieldDescriptor, i, obj);
            }
            verifyContainingType(fieldDescriptor);
            ensureExtensionsIsMutable();
            this.extensions.setRepeatedField(fieldDescriptor, i, obj);
            onChanged();
            return this;
        }
    }

    public abstract class ExtendableMessage extends GeneratedMessage implements ExtendableMessageOrBuilder {
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
                while (this.next != null && ((FieldDescriptor) this.next.getKey()).getNumber() < i) {
                    FieldDescriptor fieldDescriptor = (FieldDescriptor) this.next.getKey();
                    if (!this.messageSetWireFormat || fieldDescriptor.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fieldDescriptor.isRepeated()) {
                        FieldSet.writeField(fieldDescriptor, this.next.getValue(), codedOutputStream);
                    } else if (this.next instanceof LazyEntry) {
                        codedOutputStream.writeRawMessageSetExtension(fieldDescriptor.getNumber(), ((LazyEntry) this.next).getField().toByteString());
                    } else {
                        codedOutputStream.writeMessageSetExtension(fieldDescriptor.getNumber(), (Message) this.next.getValue());
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
            super(extendableBuilder);
            this.extensions = extendableBuilder.buildExtensions();
        }

        private void verifyContainingType(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }

        private void verifyExtensionContainingType(Extension extension) {
            if (extension.getDescriptor().getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("Extension is for type \"" + extension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + getDescriptorForType().getFullName() + "\".");
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

        public Map getAllFields() {
            Map access$700 = getAllFieldsMutable();
            access$700.putAll(getExtensionFields());
            return Collections.unmodifiableMap(access$700);
        }

        public final Object getExtension(Extension extension) {
            verifyExtensionContainingType(extension);
            FieldDescriptor descriptor = extension.getDescriptor();
            Object field = this.extensions.getField(descriptor);
            return field == null ? descriptor.isRepeated() ? Collections.emptyList() : descriptor.getJavaType() == JavaType.MESSAGE ? extension.getMessageDefaultInstance() : extension.fromReflectionType(descriptor.getDefaultValue()) : extension.fromReflectionType(field);
        }

        public final Object getExtension(Extension extension, int i) {
            verifyExtensionContainingType(extension);
            return extension.singularFromReflectionType(this.extensions.getRepeatedField(extension.getDescriptor(), i));
        }

        public final int getExtensionCount(Extension extension) {
            verifyExtensionContainingType(extension);
            return this.extensions.getRepeatedFieldCount(extension.getDescriptor());
        }

        protected Map getExtensionFields() {
            return this.extensions.getAllFields();
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            Object field = this.extensions.getField(fieldDescriptor);
            return field == null ? fieldDescriptor.getJavaType() == JavaType.MESSAGE ? DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType()) : fieldDescriptor.getDefaultValue() : field;
        }

        public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedField(fieldDescriptor, i);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedField(fieldDescriptor, i);
        }

        public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedFieldCount(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedFieldCount(fieldDescriptor);
        }

        public final boolean hasExtension(Extension extension) {
            verifyExtensionContainingType(extension);
            return this.extensions.hasField(extension.getDescriptor());
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.hasField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.hasField(fieldDescriptor);
        }

        public boolean isInitialized() {
            return super.isInitialized() && extensionsAreInitialized();
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

        protected boolean parseUnknownField(CodedInputStream codedInputStream, com.google.protobuf.UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) {
            return MessageReflection.mergeFieldFrom(codedInputStream, builder, extensionRegistryLite, getDescriptorForType(), new ExtensionAdapter(this.extensions), i);
        }
    }

    interface ExtensionDescriptorRetriever {
        FieldDescriptor getDescriptor();
    }

    abstract class CachedDescriptorRetriever implements ExtensionDescriptorRetriever {
        private volatile FieldDescriptor descriptor;

        private CachedDescriptorRetriever() {
        }

        public FieldDescriptor getDescriptor() {
            if (this.descriptor == null) {
                synchronized (this) {
                    if (this.descriptor == null) {
                        this.descriptor = loadDescriptor();
                    }
                }
            }
            return this.descriptor;
        }

        protected abstract FieldDescriptor loadDescriptor();
    }

    final class C19921 extends CachedDescriptorRetriever {
        final /* synthetic */ int val$descriptorIndex;
        final /* synthetic */ Message val$scope;

        C19921(Message message, int i) {
            this.val$scope = message;
            this.val$descriptorIndex = i;
            super();
        }

        public FieldDescriptor loadDescriptor() {
            return (FieldDescriptor) this.val$scope.getDescriptorForType().getExtensions().get(this.val$descriptorIndex);
        }
    }

    final class C19932 extends CachedDescriptorRetriever {
        final /* synthetic */ String val$name;
        final /* synthetic */ Message val$scope;

        C19932(Message message, String str) {
            this.val$scope = message;
            this.val$name = str;
            super();
        }

        protected FieldDescriptor loadDescriptor() {
            return this.val$scope.getDescriptorForType().findFieldByName(this.val$name);
        }
    }

    final class C19943 extends CachedDescriptorRetriever {
        final /* synthetic */ String val$descriptorOuterClass;
        final /* synthetic */ String val$extensionName;
        final /* synthetic */ Class val$singularType;

        C19943(Class cls, String str, String str2) {
            this.val$singularType = cls;
            this.val$descriptorOuterClass = str;
            this.val$extensionName = str2;
            super();
        }

        protected FieldDescriptor loadDescriptor() {
            try {
                return ((FileDescriptor) this.val$singularType.getClassLoader().loadClass(this.val$descriptorOuterClass).getField("descriptor").get(null)).findExtensionByName(this.val$extensionName);
            } catch (Throwable e) {
                throw new RuntimeException("Cannot load descriptors: " + this.val$descriptorOuterClass + " is not a valid descriptor class name", e);
            }
        }
    }

    public interface BuilderParent {
        void markDirty();
    }

    public final class FieldAccessorTable {
        private String[] camelCaseNames;
        private final Descriptor descriptor;
        private final FieldAccessor[] fields;
        private volatile boolean initialized;
        private final OneofAccessor[] oneofs;

        interface FieldAccessor {
            void addRepeated(Builder builder, Object obj);

            void clear(Builder builder);

            Object get(Builder builder);

            Object get(GeneratedMessage generatedMessage);

            com.google.protobuf.Message.Builder getBuilder(Builder builder);

            Object getRepeated(Builder builder, int i);

            Object getRepeated(GeneratedMessage generatedMessage, int i);

            int getRepeatedCount(Builder builder);

            int getRepeatedCount(GeneratedMessage generatedMessage);

            boolean has(Builder builder);

            boolean has(GeneratedMessage generatedMessage);

            com.google.protobuf.Message.Builder newBuilder();

            void set(Builder builder, Object obj);

            void setRepeated(Builder builder, int i, Object obj);
        }

        class OneofAccessor {
            private final Method caseMethod;
            private final Method caseMethodBuilder;
            private final Method clearMethod;
            private final Descriptor descriptor;

            OneofAccessor(Descriptor descriptor, String str, Class cls, Class cls2) {
                this.descriptor = descriptor;
                this.caseMethod = GeneratedMessage.getMethodOrDie(cls, "get" + str + "Case", new Class[0]);
                this.caseMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, "get" + str + "Case", new Class[0]);
                this.clearMethod = GeneratedMessage.getMethodOrDie(cls2, "clear" + str, new Class[0]);
            }

            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            public FieldDescriptor get(Builder builder) {
                int number = ((EnumLite) GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
                return number > 0 ? this.descriptor.findFieldByNumber(number) : null;
            }

            public FieldDescriptor get(GeneratedMessage generatedMessage) {
                int number = ((EnumLite) GeneratedMessage.invokeOrDie(this.caseMethod, generatedMessage, new Object[0])).getNumber();
                return number > 0 ? this.descriptor.findFieldByNumber(number) : null;
            }

            public boolean has(Builder builder) {
                return ((EnumLite) GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber() != 0;
            }

            public boolean has(GeneratedMessage generatedMessage) {
                return ((EnumLite) GeneratedMessage.invokeOrDie(this.caseMethod, generatedMessage, new Object[0])).getNumber() != 0;
            }
        }

        class RepeatedFieldAccessor implements FieldAccessor {
            protected final Method addRepeatedMethod;
            protected final Method clearMethod;
            protected final Method getCountMethod;
            protected final Method getCountMethodBuilder;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final Method getRepeatedMethod;
            protected final Method getRepeatedMethodBuilder;
            protected final Method setRepeatedMethod;
            protected final Class type = this.getRepeatedMethod.getReturnType();

            RepeatedFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class cls, Class cls2) {
                this.getMethod = GeneratedMessage.getMethodOrDie(cls, "get" + str + "List", new Class[0]);
                this.getMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, "get" + str + "List", new Class[0]);
                this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(cls, "get" + str, Integer.TYPE);
                this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, "get" + str, Integer.TYPE);
                this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(cls2, "set" + str, Integer.TYPE, this.type);
                this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(cls2, "add" + str, this.type);
                this.getCountMethod = GeneratedMessage.getMethodOrDie(cls, "get" + str + "Count", new Class[0]);
                this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, "get" + str + "Count", new Class[0]);
                this.clearMethod = GeneratedMessage.getMethodOrDie(cls2, "clear" + str, new Class[0]);
            }

            public void addRepeated(Builder builder, Object obj) {
                GeneratedMessage.invokeOrDie(this.addRepeatedMethod, builder, obj);
            }

            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            public Object get(GeneratedMessage generatedMessage) {
                return GeneratedMessage.invokeOrDie(this.getMethod, generatedMessage, new Object[0]);
            }

            public com.google.protobuf.Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }

            public Object getRepeated(Builder builder, int i) {
                return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, builder, Integer.valueOf(i));
            }

            public Object getRepeated(GeneratedMessage generatedMessage, int i) {
                return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, generatedMessage, Integer.valueOf(i));
            }

            public int getRepeatedCount(Builder builder) {
                return ((Integer) GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, builder, new Object[0])).intValue();
            }

            public int getRepeatedCount(GeneratedMessage generatedMessage) {
                return ((Integer) GeneratedMessage.invokeOrDie(this.getCountMethod, generatedMessage, new Object[0])).intValue();
            }

            public boolean has(Builder builder) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            public boolean has(GeneratedMessage generatedMessage) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            public com.google.protobuf.Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            public void set(Builder builder, Object obj) {
                clear(builder);
                for (Object addRepeated : (List) obj) {
                    addRepeated(builder, addRepeated);
                }
            }

            public void setRepeated(Builder builder, int i, Object obj) {
                GeneratedMessage.invokeOrDie(this.setRepeatedMethod, builder, Integer.valueOf(i), obj);
            }
        }

        final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
            private final Method getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            private final Method valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", EnumValueDescriptor.class);

            RepeatedEnumFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class cls, Class cls2) {
                super(fieldDescriptor, str, cls, cls2);
            }

            public void addRepeated(Builder builder, Object obj) {
                super.addRepeated(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, obj));
            }

            public Object get(Builder builder) {
                List arrayList = new ArrayList();
                for (Object access$1100 : (List) super.get(builder)) {
                    arrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, access$1100, new Object[0]));
                }
                return Collections.unmodifiableList(arrayList);
            }

            public Object get(GeneratedMessage generatedMessage) {
                List arrayList = new ArrayList();
                for (Object access$1100 : (List) super.get(generatedMessage)) {
                    arrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, access$1100, new Object[0]));
                }
                return Collections.unmodifiableList(arrayList);
            }

            public Object getRepeated(Builder builder, int i) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, i), new Object[0]);
            }

            public Object getRepeated(GeneratedMessage generatedMessage, int i) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(generatedMessage, i), new Object[0]);
            }

            public void setRepeated(Builder builder, int i, Object obj) {
                super.setRepeated(builder, i, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, obj));
            }
        }

        final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
            private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);

            RepeatedMessageFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class cls, Class cls2) {
                super(fieldDescriptor, str, cls, cls2);
            }

            private Object coerceType(Object obj) {
                return this.type.isInstance(obj) ? obj : ((com.google.protobuf.Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) obj).build();
            }

            public void addRepeated(Builder builder, Object obj) {
                super.addRepeated(builder, coerceType(obj));
            }

            public com.google.protobuf.Message.Builder newBuilder() {
                return (com.google.protobuf.Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            public void setRepeated(Builder builder, int i, Object obj) {
                super.setRepeated(builder, i, coerceType(obj));
            }
        }

        class SingularFieldAccessor implements FieldAccessor {
            protected final Method caseMethod;
            protected final Method caseMethodBuilder;
            protected final Method clearMethod;
            protected final FieldDescriptor field;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final boolean hasHasMethod;
            protected final Method hasMethod;
            protected final Method hasMethodBuilder;
            protected final boolean isOneofField;
            protected final Method setMethod;
            protected final Class type;

            SingularFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class cls, Class cls2, String str2) {
                Method method = null;
                this.field = fieldDescriptor;
                this.isOneofField = fieldDescriptor.getContainingOneof() != null;
                boolean z = FieldAccessorTable.supportFieldPresence(fieldDescriptor.getFile()) || (!this.isOneofField && fieldDescriptor.getJavaType() == JavaType.MESSAGE);
                this.hasHasMethod = z;
                this.getMethod = GeneratedMessage.getMethodOrDie(cls, "get" + str, new Class[0]);
                this.getMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, "get" + str, new Class[0]);
                this.type = this.getMethod.getReturnType();
                this.setMethod = GeneratedMessage.getMethodOrDie(cls2, "set" + str, this.type);
                this.hasMethod = this.hasHasMethod ? GeneratedMessage.getMethodOrDie(cls, "has" + str, new Class[0]) : null;
                this.hasMethodBuilder = this.hasHasMethod ? GeneratedMessage.getMethodOrDie(cls2, "has" + str, new Class[0]) : null;
                this.clearMethod = GeneratedMessage.getMethodOrDie(cls2, "clear" + str, new Class[0]);
                this.caseMethod = this.isOneofField ? GeneratedMessage.getMethodOrDie(cls, "get" + str2 + "Case", new Class[0]) : null;
                if (this.isOneofField) {
                    method = GeneratedMessage.getMethodOrDie(cls2, "get" + str2 + "Case", new Class[0]);
                }
                this.caseMethodBuilder = method;
            }

            private int getOneofFieldNumber(Builder builder) {
                return ((EnumLite) GeneratedMessage.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
            }

            private int getOneofFieldNumber(GeneratedMessage generatedMessage) {
                return ((EnumLite) GeneratedMessage.invokeOrDie(this.caseMethod, generatedMessage, new Object[0])).getNumber();
            }

            public void addRepeated(Builder builder, Object obj) {
                throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
            }

            public void clear(Builder builder) {
                GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            public Object get(GeneratedMessage generatedMessage) {
                return GeneratedMessage.invokeOrDie(this.getMethod, generatedMessage, new Object[0]);
            }

            public com.google.protobuf.Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }

            public Object getRepeated(Builder builder, int i) {
                throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
            }

            public Object getRepeated(GeneratedMessage generatedMessage, int i) {
                throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
            }

            public int getRepeatedCount(Builder builder) {
                throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
            }

            public int getRepeatedCount(GeneratedMessage generatedMessage) {
                throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
            }

            public boolean has(Builder builder) {
                if (this.hasHasMethod) {
                    return ((Boolean) GeneratedMessage.invokeOrDie(this.hasMethodBuilder, builder, new Object[0])).booleanValue();
                }
                if (this.isOneofField) {
                    if (getOneofFieldNumber(builder) != this.field.getNumber()) {
                        return false;
                    }
                } else if (get(builder).equals(this.field.getDefaultValue())) {
                    return false;
                }
                return true;
            }

            public boolean has(GeneratedMessage generatedMessage) {
                if (this.hasHasMethod) {
                    return ((Boolean) GeneratedMessage.invokeOrDie(this.hasMethod, generatedMessage, new Object[0])).booleanValue();
                }
                if (this.isOneofField) {
                    if (getOneofFieldNumber(generatedMessage) != this.field.getNumber()) {
                        return false;
                    }
                } else if (get(generatedMessage).equals(this.field.getDefaultValue())) {
                    return false;
                }
                return true;
            }

            public com.google.protobuf.Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            public void set(Builder builder, Object obj) {
                GeneratedMessage.invokeOrDie(this.setMethod, builder, obj);
            }

            public void setRepeated(Builder builder, int i, Object obj) {
                throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
            }
        }

        final class SingularEnumFieldAccessor extends SingularFieldAccessor {
            private Method getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            private Method valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", EnumValueDescriptor.class);

            SingularEnumFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class cls, Class cls2, String str2) {
                super(fieldDescriptor, str, cls, cls2, str2);
            }

            public Object get(Builder builder) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(builder), new Object[0]);
            }

            public Object get(GeneratedMessage generatedMessage) {
                return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(generatedMessage), new Object[0]);
            }

            public void set(Builder builder, Object obj) {
                super.set(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, obj));
            }
        }

        final class SingularMessageFieldAccessor extends SingularFieldAccessor {
            private final Method getBuilderMethodBuilder;
            private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);

            SingularMessageFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class cls, Class cls2, String str2) {
                super(fieldDescriptor, str, cls, cls2, str2);
                this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(cls2, "get" + str + "Builder", new Class[0]);
            }

            private Object coerceType(Object obj) {
                return this.type.isInstance(obj) ? obj : ((com.google.protobuf.Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) obj).buildPartial();
            }

            public com.google.protobuf.Message.Builder getBuilder(Builder builder) {
                return (com.google.protobuf.Message.Builder) GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, builder, new Object[0]);
            }

            public com.google.protobuf.Message.Builder newBuilder() {
                return (com.google.protobuf.Message.Builder) GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            public void set(Builder builder, Object obj) {
                super.set(builder, coerceType(obj));
            }
        }

        public FieldAccessorTable(Descriptor descriptor, String[] strArr) {
            this.descriptor = descriptor;
            this.camelCaseNames = strArr;
            this.fields = new FieldAccessor[descriptor.getFields().size()];
            this.oneofs = new OneofAccessor[descriptor.getOneofs().size()];
            this.initialized = false;
        }

        public FieldAccessorTable(Descriptor descriptor, String[] strArr, Class cls, Class cls2) {
            this(descriptor, strArr);
            ensureFieldAccessorsInitialized(cls, cls2);
        }

        private FieldAccessor getField(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != this.descriptor) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            } else if (!fieldDescriptor.isExtension()) {
                return this.fields[fieldDescriptor.getIndex()];
            } else {
                throw new IllegalArgumentException("This type does not have extensions.");
            }
        }

        private OneofAccessor getOneof(OneofDescriptor oneofDescriptor) {
            if (oneofDescriptor.getContainingType() == this.descriptor) {
                return this.oneofs[oneofDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }

        private static boolean supportFieldPresence(FileDescriptor fileDescriptor) {
            return true;
        }

        public FieldAccessorTable ensureFieldAccessorsInitialized(Class cls, Class cls2) {
            if (!this.initialized) {
                synchronized (this) {
                    if (this.initialized) {
                    } else {
                        int length = this.fields.length;
                        for (int i = 0; i < length; i++) {
                            FieldDescriptor fieldDescriptor = (FieldDescriptor) this.descriptor.getFields().get(i);
                            String str = fieldDescriptor.getContainingOneof() != null ? this.camelCaseNames[fieldDescriptor.getContainingOneof().getIndex() + length] : null;
                            if (fieldDescriptor.isRepeated()) {
                                if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                                    this.fields[i] = new RepeatedMessageFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2);
                                } else if (fieldDescriptor.getJavaType() == JavaType.ENUM) {
                                    this.fields[i] = new RepeatedEnumFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2);
                                } else {
                                    this.fields[i] = new RepeatedFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2);
                                }
                            } else if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                                this.fields[i] = new SingularMessageFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2, str);
                            } else if (fieldDescriptor.getJavaType() == JavaType.ENUM) {
                                this.fields[i] = new SingularEnumFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2, str);
                            } else {
                                this.fields[i] = new SingularFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2, str);
                            }
                        }
                        int length2 = this.oneofs.length;
                        for (int i2 = 0; i2 < length2; i2++) {
                            this.oneofs[i2] = new OneofAccessor(this.descriptor, this.camelCaseNames[i2 + length], cls, cls2);
                        }
                        this.initialized = true;
                        this.camelCaseNames = null;
                    }
                }
            }
            return this;
        }
    }

    public class GeneratedExtension extends Extension {
        private ExtensionDescriptorRetriever descriptorRetriever;
        private final Method enumGetValueDescriptor;
        private final Method enumValueOf;
        private final ExtensionType extensionType;
        private final Message messageDefaultInstance;
        private final Class singularType;

        GeneratedExtension(ExtensionDescriptorRetriever extensionDescriptorRetriever, Class cls, Message message, ExtensionType extensionType) {
            if (!Message.class.isAssignableFrom(cls) || cls.isInstance(message)) {
                this.descriptorRetriever = extensionDescriptorRetriever;
                this.singularType = cls;
                this.messageDefaultInstance = message;
                if (ProtocolMessageEnum.class.isAssignableFrom(cls)) {
                    this.enumValueOf = GeneratedMessage.getMethodOrDie(cls, "valueOf", EnumValueDescriptor.class);
                    this.enumGetValueDescriptor = GeneratedMessage.getMethodOrDie(cls, "getValueDescriptor", new Class[0]);
                } else {
                    this.enumValueOf = null;
                    this.enumGetValueDescriptor = null;
                }
                this.extensionType = extensionType;
                return;
            }
            throw new IllegalArgumentException("Bad messageDefaultInstance for " + cls.getName());
        }

        protected Object fromReflectionType(Object obj) {
            FieldDescriptor descriptor = getDescriptor();
            if (!descriptor.isRepeated()) {
                return singularFromReflectionType(obj);
            }
            if (descriptor.getJavaType() != JavaType.MESSAGE && descriptor.getJavaType() != JavaType.ENUM) {
                return obj;
            }
            List arrayList = new ArrayList();
            for (Object singularFromReflectionType : (List) obj) {
                arrayList.add(singularFromReflectionType(singularFromReflectionType));
            }
            return arrayList;
        }

        public Object getDefaultValue() {
            return isRepeated() ? Collections.emptyList() : getDescriptor().getJavaType() == JavaType.MESSAGE ? this.messageDefaultInstance : singularFromReflectionType(getDescriptor().getDefaultValue());
        }

        public FieldDescriptor getDescriptor() {
            if (this.descriptorRetriever != null) {
                return this.descriptorRetriever.getDescriptor();
            }
            throw new IllegalStateException("getDescriptor() called before internalInit()");
        }

        protected ExtensionType getExtensionType() {
            return this.extensionType;
        }

        public FieldType getLiteType() {
            return getDescriptor().getLiteType();
        }

        public Message getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        public int getNumber() {
            return getDescriptor().getNumber();
        }

        public void internalInit(final FieldDescriptor fieldDescriptor) {
            if (this.descriptorRetriever != null) {
                throw new IllegalStateException("Already initialized.");
            }
            this.descriptorRetriever = new ExtensionDescriptorRetriever() {
                public FieldDescriptor getDescriptor() {
                    return fieldDescriptor;
                }
            };
        }

        public boolean isRepeated() {
            return getDescriptor().isRepeated();
        }

        protected Object singularFromReflectionType(Object obj) {
            switch (getDescriptor().getJavaType()) {
                case MESSAGE:
                    return !this.singularType.isInstance(obj) ? this.messageDefaultInstance.newBuilderForType().mergeFrom((Message) obj).build() : obj;
                case ENUM:
                    return GeneratedMessage.invokeOrDie(this.enumValueOf, null, (EnumValueDescriptor) obj);
                default:
                    return obj;
            }
        }

        protected Object singularToReflectionType(Object obj) {
            switch (getDescriptor().getJavaType()) {
                case ENUM:
                    return GeneratedMessage.invokeOrDie(this.enumGetValueDescriptor, obj, new Object[0]);
                default:
                    return obj;
            }
        }

        protected Object toReflectionType(Object obj) {
            FieldDescriptor descriptor = getDescriptor();
            if (!descriptor.isRepeated()) {
                return singularToReflectionType(obj);
            }
            if (descriptor.getJavaType() != JavaType.ENUM) {
                return obj;
            }
            List arrayList = new ArrayList();
            for (Object singularToReflectionType : (List) obj) {
                arrayList.add(singularToReflectionType(singularToReflectionType));
            }
            return arrayList;
        }
    }

    protected GeneratedMessage() {
    }

    protected GeneratedMessage(Builder builder) {
    }

    static void enableAlwaysUseFieldBuildersForTesting() {
        alwaysUseFieldBuilders = true;
    }

    private Map getAllFieldsMutable() {
        Map treeMap = new TreeMap();
        for (FieldDescriptor fieldDescriptor : internalGetFieldAccessorTable().descriptor.getFields()) {
            if (fieldDescriptor.isRepeated()) {
                List list = (List) getField(fieldDescriptor);
                if (!list.isEmpty()) {
                    treeMap.put(fieldDescriptor, list);
                }
            } else if (hasField(fieldDescriptor)) {
                treeMap.put(fieldDescriptor, getField(fieldDescriptor));
            }
        }
        return treeMap;
    }

    private static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (Throwable e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    private static Object invokeOrDie(Method method, Object obj, Object... objArr) {
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

    public static GeneratedExtension newFileScopedGeneratedExtension(Class cls, Message message) {
        return new GeneratedExtension(null, cls, message, ExtensionType.IMMUTABLE);
    }

    public static GeneratedExtension newFileScopedGeneratedExtension(Class cls, Message message, String str, String str2) {
        return new GeneratedExtension(new C19943(cls, str, str2), cls, message, ExtensionType.MUTABLE);
    }

    public static GeneratedExtension newMessageScopedGeneratedExtension(Message message, int i, Class cls, Message message2) {
        return new GeneratedExtension(new C19921(message, i), cls, message2, ExtensionType.IMMUTABLE);
    }

    public static GeneratedExtension newMessageScopedGeneratedExtension(Message message, String str, Class cls, Message message2) {
        return new GeneratedExtension(new C19932(message, str), cls, message2, ExtensionType.MUTABLE);
    }

    public Map getAllFields() {
        return Collections.unmodifiableMap(getAllFieldsMutable());
    }

    public Descriptor getDescriptorForType() {
        return internalGetFieldAccessorTable().descriptor;
    }

    public Object getField(FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).get(this);
    }

    public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
        return internalGetFieldAccessorTable().getOneof(oneofDescriptor).get(this);
    }

    public Parser getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeated(this, i);
    }

    public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeatedCount(this);
    }

    public UnknownFieldSet getUnknownFields() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    public boolean hasField(FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).has(this);
    }

    public boolean hasOneof(OneofDescriptor oneofDescriptor) {
        return internalGetFieldAccessorTable().getOneof(oneofDescriptor).has(this);
    }

    protected abstract FieldAccessorTable internalGetFieldAccessorTable();

    public boolean isInitialized() {
        for (FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                return false;
            }
            if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                if (fieldDescriptor.isRepeated()) {
                    for (Message isInitialized : (List) getField(fieldDescriptor)) {
                        if (!isInitialized.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (hasField(fieldDescriptor) && !((Message) getField(fieldDescriptor)).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    protected void makeExtensionsImmutable() {
    }

    protected abstract com.google.protobuf.Message.Builder newBuilderForType(BuilderParent builderParent);

    protected boolean parseUnknownField(CodedInputStream codedInputStream, com.google.protobuf.UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) {
        return builder.mergeFieldFrom(i, codedInputStream);
    }

    protected Object writeReplace() {
        return new SerializedForm(this);
    }
}
