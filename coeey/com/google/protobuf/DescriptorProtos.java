package com.google.protobuf;

import com.facebook.imageutils.JfifUtil;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.GeneratedMessage.BuilderParent;
import com.google.protobuf.GeneratedMessage.ExtendableBuilder;
import com.google.protobuf.GeneratedMessage.ExtendableMessage;
import com.google.protobuf.GeneratedMessage.ExtendableMessage.ExtensionWriter;
import com.google.protobuf.GeneratedMessage.ExtendableMessageOrBuilder;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DescriptorProtos {
    private static FileDescriptor descriptor;
    private static final Descriptor f206x1458f8d = ((Descriptor) internal_static_google_protobuf_DescriptorProto_descriptor.getNestedTypes().get(0));
    private static FieldAccessorTable f207xf366d90b = new FieldAccessorTable(f206x1458f8d, new String[]{"Start", "End"});
    private static final Descriptor internal_static_google_protobuf_DescriptorProto_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    private static FieldAccessorTable f208x907d0bf0 = new FieldAccessorTable(internal_static_google_protobuf_DescriptorProto_descriptor, new String[]{"Name", "Field", "Extension", "NestedType", "EnumType", "ExtensionRange", "OneofDecl", "Options"});
    private static final Descriptor internal_static_google_protobuf_EnumDescriptorProto_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(5));
    private static FieldAccessorTable f209x9945f651 = new FieldAccessorTable(internal_static_google_protobuf_EnumDescriptorProto_descriptor, new String[]{"Name", "Value", "Options"});
    private static final Descriptor internal_static_google_protobuf_EnumOptions_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(12));
    private static FieldAccessorTable internal_static_google_protobuf_EnumOptions_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_EnumOptions_descriptor, new String[]{"AllowAlias", "Deprecated", "UninterpretedOption"});
    private static final Descriptor f210xf18031a8 = ((Descriptor) getDescriptor().getMessageTypes().get(6));
    private static FieldAccessorTable f211xfb173026 = new FieldAccessorTable(f210xf18031a8, new String[]{"Name", "Number", "Options"});
    private static final Descriptor internal_static_google_protobuf_EnumValueOptions_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(13));
    private static FieldAccessorTable f212xdf65a421 = new FieldAccessorTable(internal_static_google_protobuf_EnumValueOptions_descriptor, new String[]{"Deprecated", "UninterpretedOption"});
    private static final Descriptor internal_static_google_protobuf_FieldDescriptorProto_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(3));
    private static FieldAccessorTable f213x4734b330 = new FieldAccessorTable(internal_static_google_protobuf_FieldDescriptorProto_descriptor, new String[]{"Name", "Number", "Label", "Type", "TypeName", "Extendee", "DefaultValue", "OneofIndex", "Options"});
    private static final Descriptor internal_static_google_protobuf_FieldOptions_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(11));
    private static FieldAccessorTable internal_static_google_protobuf_FieldOptions_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_FieldOptions_descriptor, new String[]{"Ctype", "Packed", "Lazy", "Deprecated", "ExperimentalMapKey", "Weak", "UninterpretedOption"});
    private static final Descriptor internal_static_google_protobuf_FileDescriptorProto_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    private static FieldAccessorTable f214x4b52780c = new FieldAccessorTable(internal_static_google_protobuf_FileDescriptorProto_descriptor, new String[]{"Name", "Package", "Dependency", "PublicDependency", "WeakDependency", "MessageType", "EnumType", "Service", "Extension", "Options", "SourceCodeInfo"});
    private static final Descriptor internal_static_google_protobuf_FileDescriptorSet_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    private static FieldAccessorTable f215x15a6a952 = new FieldAccessorTable(internal_static_google_protobuf_FileDescriptorSet_descriptor, new String[]{"File"});
    private static final Descriptor internal_static_google_protobuf_FileOptions_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(9));
    private static FieldAccessorTable internal_static_google_protobuf_FileOptions_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_FileOptions_descriptor, new String[]{"JavaPackage", "JavaOuterClassname", "JavaMultipleFiles", "JavaGenerateEqualsAndHash", "JavaStringCheckUtf8", "OptimizeFor", "GoPackage", "CcGenericServices", "JavaGenericServices", "PyGenericServices", "Deprecated", "UninterpretedOption"});
    private static final Descriptor internal_static_google_protobuf_MessageOptions_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(10));
    private static FieldAccessorTable f216x9c0b3d38 = new FieldAccessorTable(internal_static_google_protobuf_MessageOptions_descriptor, new String[]{"MessageSetWireFormat", "NoStandardDescriptorAccessor", "Deprecated", "UninterpretedOption"});
    private static final Descriptor internal_static_google_protobuf_MethodDescriptorProto_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(8));
    private static FieldAccessorTable f217xc5331ef1 = new FieldAccessorTable(internal_static_google_protobuf_MethodDescriptorProto_descriptor, new String[]{"Name", "InputType", "OutputType", "Options"});
    private static final Descriptor internal_static_google_protobuf_MethodOptions_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(15));
    private static FieldAccessorTable internal_static_google_protobuf_MethodOptions_fieldAccessorTable = new FieldAccessorTable(internal_static_google_protobuf_MethodOptions_descriptor, new String[]{"Deprecated", "UninterpretedOption"});
    private static final Descriptor internal_static_google_protobuf_OneofDescriptorProto_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(4));
    private static FieldAccessorTable f218x69499c33 = new FieldAccessorTable(internal_static_google_protobuf_OneofDescriptorProto_descriptor, new String[]{"Name"});
    private static final Descriptor f219x158c73ed = ((Descriptor) getDescriptor().getMessageTypes().get(7));
    private static FieldAccessorTable f220xee335d6b = new FieldAccessorTable(f219x158c73ed, new String[]{"Name", "Method", "Options"});
    private static final Descriptor internal_static_google_protobuf_ServiceOptions_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(14));
    private static FieldAccessorTable f221x371e666 = new FieldAccessorTable(internal_static_google_protobuf_ServiceOptions_descriptor, new String[]{"Deprecated", "UninterpretedOption"});
    private static final Descriptor f222xb210d08d = ((Descriptor) internal_static_google_protobuf_SourceCodeInfo_descriptor.getNestedTypes().get(0));
    private static FieldAccessorTable f223x9611a0b = new FieldAccessorTable(f222xb210d08d, new String[]{"Path", "Span", "LeadingComments", "TrailingComments"});
    private static final Descriptor internal_static_google_protobuf_SourceCodeInfo_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(17));
    private static FieldAccessorTable f224x532209f9 = new FieldAccessorTable(internal_static_google_protobuf_SourceCodeInfo_descriptor, new String[]{"Location"});
    private static final Descriptor f225xb111d23c = ((Descriptor) internal_static_google_protobuf_UninterpretedOption_descriptor.getNestedTypes().get(0));
    private static FieldAccessorTable f226x1698fcba = new FieldAccessorTable(f225xb111d23c, new String[]{"NamePart", "IsExtension"});
    private static final Descriptor internal_static_google_protobuf_UninterpretedOption_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(16));
    private static FieldAccessorTable f227x2101041 = new FieldAccessorTable(internal_static_google_protobuf_UninterpretedOption_descriptor, new String[]{"Name", "IdentifierValue", "PositiveIntValue", "NegativeIntValue", "DoubleValue", "StringValue", "AggregateValue"});

    final class C19621 implements InternalDescriptorAssigner {
        C19621() {
        }

        public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
            DescriptorProtos.descriptor = fileDescriptor;
            return null;
        }
    }

    public interface DescriptorProtoOrBuilder extends MessageOrBuilder {
        EnumDescriptorProto getEnumType(int i);

        int getEnumTypeCount();

        List getEnumTypeList();

        EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int i);

        List getEnumTypeOrBuilderList();

        FieldDescriptorProto getExtension(int i);

        int getExtensionCount();

        List getExtensionList();

        FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int i);

        List getExtensionOrBuilderList();

        ExtensionRange getExtensionRange(int i);

        int getExtensionRangeCount();

        List getExtensionRangeList();

        ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int i);

        List getExtensionRangeOrBuilderList();

        FieldDescriptorProto getField(int i);

        int getFieldCount();

        List getFieldList();

        FieldDescriptorProtoOrBuilder getFieldOrBuilder(int i);

        List getFieldOrBuilderList();

        String getName();

        ByteString getNameBytes();

        DescriptorProto getNestedType(int i);

        int getNestedTypeCount();

        List getNestedTypeList();

        DescriptorProtoOrBuilder getNestedTypeOrBuilder(int i);

        List getNestedTypeOrBuilderList();

        OneofDescriptorProto getOneofDecl(int i);

        int getOneofDeclCount();

        List getOneofDeclList();

        OneofDescriptorProtoOrBuilder getOneofDeclOrBuilder(int i);

        List getOneofDeclOrBuilderList();

        MessageOptions getOptions();

        MessageOptionsOrBuilder getOptionsOrBuilder();

        boolean hasName();

        boolean hasOptions();
    }

    public final class DescriptorProto extends GeneratedMessage implements DescriptorProtoOrBuilder {
        public static final int ENUM_TYPE_FIELD_NUMBER = 4;
        public static final int EXTENSION_FIELD_NUMBER = 6;
        public static final int EXTENSION_RANGE_FIELD_NUMBER = 5;
        public static final int FIELD_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int NESTED_TYPE_FIELD_NUMBER = 3;
        public static final int ONEOF_DECL_FIELD_NUMBER = 8;
        public static final int OPTIONS_FIELD_NUMBER = 7;
        public static Parser PARSER = new C19631();
        private static final DescriptorProto defaultInstance = new DescriptorProto(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private List enumType_;
        private List extensionRange_;
        private List extension_;
        private List field_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object name_;
        private List nestedType_;
        private List oneofDecl_;
        private MessageOptions options_;
        private final UnknownFieldSet unknownFields;

        final class C19631 extends AbstractParser {
            C19631() {
            }

            public DescriptorProto parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new DescriptorProto(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements DescriptorProtoOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilder enumTypeBuilder_;
            private List enumType_;
            private RepeatedFieldBuilder extensionBuilder_;
            private RepeatedFieldBuilder extensionRangeBuilder_;
            private List extensionRange_;
            private List extension_;
            private RepeatedFieldBuilder fieldBuilder_;
            private List field_;
            private Object name_;
            private RepeatedFieldBuilder nestedTypeBuilder_;
            private List nestedType_;
            private RepeatedFieldBuilder oneofDeclBuilder_;
            private List oneofDecl_;
            private SingleFieldBuilder optionsBuilder_;
            private MessageOptions options_;

            private Builder() {
                this.name_ = "";
                this.field_ = Collections.emptyList();
                this.extension_ = Collections.emptyList();
                this.nestedType_ = Collections.emptyList();
                this.enumType_ = Collections.emptyList();
                this.extensionRange_ = Collections.emptyList();
                this.oneofDecl_ = Collections.emptyList();
                this.options_ = MessageOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.field_ = Collections.emptyList();
                this.extension_ = Collections.emptyList();
                this.nestedType_ = Collections.emptyList();
                this.enumType_ = Collections.emptyList();
                this.extensionRange_ = Collections.emptyList();
                this.oneofDecl_ = Collections.emptyList();
                this.options_ = MessageOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureEnumTypeIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.enumType_ = new ArrayList(this.enumType_);
                    this.bitField0_ |= 16;
                }
            }

            private void ensureExtensionIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.extension_ = new ArrayList(this.extension_);
                    this.bitField0_ |= 4;
                }
            }

            private void ensureExtensionRangeIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.extensionRange_ = new ArrayList(this.extensionRange_);
                    this.bitField0_ |= 32;
                }
            }

            private void ensureFieldIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.field_ = new ArrayList(this.field_);
                    this.bitField0_ |= 2;
                }
            }

            private void ensureNestedTypeIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.nestedType_ = new ArrayList(this.nestedType_);
                    this.bitField0_ |= 8;
                }
            }

            private void ensureOneofDeclIsMutable() {
                if ((this.bitField0_ & 64) != 64) {
                    this.oneofDecl_ = new ArrayList(this.oneofDecl_);
                    this.bitField0_ |= 64;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
            }

            private RepeatedFieldBuilder getEnumTypeFieldBuilder() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumTypeBuilder_ = new RepeatedFieldBuilder(this.enumType_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                    this.enumType_ = null;
                }
                return this.enumTypeBuilder_;
            }

            private RepeatedFieldBuilder getExtensionFieldBuilder() {
                if (this.extensionBuilder_ == null) {
                    this.extensionBuilder_ = new RepeatedFieldBuilder(this.extension_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                    this.extension_ = null;
                }
                return this.extensionBuilder_;
            }

            private RepeatedFieldBuilder getExtensionRangeFieldBuilder() {
                if (this.extensionRangeBuilder_ == null) {
                    this.extensionRangeBuilder_ = new RepeatedFieldBuilder(this.extensionRange_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
                    this.extensionRange_ = null;
                }
                return this.extensionRangeBuilder_;
            }

            private RepeatedFieldBuilder getFieldFieldBuilder() {
                if (this.fieldBuilder_ == null) {
                    this.fieldBuilder_ = new RepeatedFieldBuilder(this.field_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.field_ = null;
                }
                return this.fieldBuilder_;
            }

            private RepeatedFieldBuilder getNestedTypeFieldBuilder() {
                if (this.nestedTypeBuilder_ == null) {
                    this.nestedTypeBuilder_ = new RepeatedFieldBuilder(this.nestedType_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                    this.nestedType_ = null;
                }
                return this.nestedTypeBuilder_;
            }

            private RepeatedFieldBuilder getOneofDeclFieldBuilder() {
                if (this.oneofDeclBuilder_ == null) {
                    this.oneofDeclBuilder_ = new RepeatedFieldBuilder(this.oneofDecl_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
                    this.oneofDecl_ = null;
                }
                return this.oneofDeclBuilder_;
            }

            private SingleFieldBuilder getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getFieldFieldBuilder();
                    getExtensionFieldBuilder();
                    getNestedTypeFieldBuilder();
                    getEnumTypeFieldBuilder();
                    getExtensionRangeFieldBuilder();
                    getOneofDeclFieldBuilder();
                    getOptionsFieldBuilder();
                }
            }

            public Builder addAllEnumType(Iterable iterable) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.enumType_);
                    onChanged();
                } else {
                    this.enumTypeBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllExtension(Iterable iterable) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.extension_);
                    onChanged();
                } else {
                    this.extensionBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllExtensionRange(Iterable iterable) {
                if (this.extensionRangeBuilder_ == null) {
                    ensureExtensionRangeIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.extensionRange_);
                    onChanged();
                } else {
                    this.extensionRangeBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllField(Iterable iterable) {
                if (this.fieldBuilder_ == null) {
                    ensureFieldIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.field_);
                    onChanged();
                } else {
                    this.fieldBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllNestedType(Iterable iterable) {
                if (this.nestedTypeBuilder_ == null) {
                    ensureNestedTypeIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.nestedType_);
                    onChanged();
                } else {
                    this.nestedTypeBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllOneofDecl(Iterable iterable) {
                if (this.oneofDeclBuilder_ == null) {
                    ensureOneofDeclIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.oneofDecl_);
                    onChanged();
                } else {
                    this.oneofDeclBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addEnumType(int i, Builder builder) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    this.enumType_.add(i, builder.build());
                    onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addEnumType(int i, EnumDescriptorProto enumDescriptorProto) {
                if (this.enumTypeBuilder_ != null) {
                    this.enumTypeBuilder_.addMessage(i, enumDescriptorProto);
                } else if (enumDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureEnumTypeIsMutable();
                    this.enumType_.add(i, enumDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addEnumType(Builder builder) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    this.enumType_.add(builder.build());
                    onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addEnumType(EnumDescriptorProto enumDescriptorProto) {
                if (this.enumTypeBuilder_ != null) {
                    this.enumTypeBuilder_.addMessage(enumDescriptorProto);
                } else if (enumDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureEnumTypeIsMutable();
                    this.enumType_.add(enumDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addEnumTypeBuilder() {
                return (Builder) getEnumTypeFieldBuilder().addBuilder(EnumDescriptorProto.getDefaultInstance());
            }

            public Builder addEnumTypeBuilder(int i) {
                return (Builder) getEnumTypeFieldBuilder().addBuilder(i, EnumDescriptorProto.getDefaultInstance());
            }

            public Builder addExtension(int i, Builder builder) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    this.extension_.add(i, builder.build());
                    onChanged();
                } else {
                    this.extensionBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addExtension(int i, FieldDescriptorProto fieldDescriptorProto) {
                if (this.extensionBuilder_ != null) {
                    this.extensionBuilder_.addMessage(i, fieldDescriptorProto);
                } else if (fieldDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureExtensionIsMutable();
                    this.extension_.add(i, fieldDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addExtension(Builder builder) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    this.extension_.add(builder.build());
                    onChanged();
                } else {
                    this.extensionBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addExtension(FieldDescriptorProto fieldDescriptorProto) {
                if (this.extensionBuilder_ != null) {
                    this.extensionBuilder_.addMessage(fieldDescriptorProto);
                } else if (fieldDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureExtensionIsMutable();
                    this.extension_.add(fieldDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addExtensionBuilder() {
                return (Builder) getExtensionFieldBuilder().addBuilder(FieldDescriptorProto.getDefaultInstance());
            }

            public Builder addExtensionBuilder(int i) {
                return (Builder) getExtensionFieldBuilder().addBuilder(i, FieldDescriptorProto.getDefaultInstance());
            }

            public Builder addExtensionRange(int i, Builder builder) {
                if (this.extensionRangeBuilder_ == null) {
                    ensureExtensionRangeIsMutable();
                    this.extensionRange_.add(i, builder.build());
                    onChanged();
                } else {
                    this.extensionRangeBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addExtensionRange(int i, ExtensionRange extensionRange) {
                if (this.extensionRangeBuilder_ != null) {
                    this.extensionRangeBuilder_.addMessage(i, extensionRange);
                } else if (extensionRange == null) {
                    throw new NullPointerException();
                } else {
                    ensureExtensionRangeIsMutable();
                    this.extensionRange_.add(i, extensionRange);
                    onChanged();
                }
                return this;
            }

            public Builder addExtensionRange(Builder builder) {
                if (this.extensionRangeBuilder_ == null) {
                    ensureExtensionRangeIsMutable();
                    this.extensionRange_.add(builder.build());
                    onChanged();
                } else {
                    this.extensionRangeBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addExtensionRange(ExtensionRange extensionRange) {
                if (this.extensionRangeBuilder_ != null) {
                    this.extensionRangeBuilder_.addMessage(extensionRange);
                } else if (extensionRange == null) {
                    throw new NullPointerException();
                } else {
                    ensureExtensionRangeIsMutable();
                    this.extensionRange_.add(extensionRange);
                    onChanged();
                }
                return this;
            }

            public Builder addExtensionRangeBuilder() {
                return (Builder) getExtensionRangeFieldBuilder().addBuilder(ExtensionRange.getDefaultInstance());
            }

            public Builder addExtensionRangeBuilder(int i) {
                return (Builder) getExtensionRangeFieldBuilder().addBuilder(i, ExtensionRange.getDefaultInstance());
            }

            public Builder addField(int i, Builder builder) {
                if (this.fieldBuilder_ == null) {
                    ensureFieldIsMutable();
                    this.field_.add(i, builder.build());
                    onChanged();
                } else {
                    this.fieldBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addField(int i, FieldDescriptorProto fieldDescriptorProto) {
                if (this.fieldBuilder_ != null) {
                    this.fieldBuilder_.addMessage(i, fieldDescriptorProto);
                } else if (fieldDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureFieldIsMutable();
                    this.field_.add(i, fieldDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addField(Builder builder) {
                if (this.fieldBuilder_ == null) {
                    ensureFieldIsMutable();
                    this.field_.add(builder.build());
                    onChanged();
                } else {
                    this.fieldBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addField(FieldDescriptorProto fieldDescriptorProto) {
                if (this.fieldBuilder_ != null) {
                    this.fieldBuilder_.addMessage(fieldDescriptorProto);
                } else if (fieldDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureFieldIsMutable();
                    this.field_.add(fieldDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addFieldBuilder() {
                return (Builder) getFieldFieldBuilder().addBuilder(FieldDescriptorProto.getDefaultInstance());
            }

            public Builder addFieldBuilder(int i) {
                return (Builder) getFieldFieldBuilder().addBuilder(i, FieldDescriptorProto.getDefaultInstance());
            }

            public Builder addNestedType(int i, Builder builder) {
                if (this.nestedTypeBuilder_ == null) {
                    ensureNestedTypeIsMutable();
                    this.nestedType_.add(i, builder.build());
                    onChanged();
                } else {
                    this.nestedTypeBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addNestedType(int i, DescriptorProto descriptorProto) {
                if (this.nestedTypeBuilder_ != null) {
                    this.nestedTypeBuilder_.addMessage(i, descriptorProto);
                } else if (descriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureNestedTypeIsMutable();
                    this.nestedType_.add(i, descriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addNestedType(Builder builder) {
                if (this.nestedTypeBuilder_ == null) {
                    ensureNestedTypeIsMutable();
                    this.nestedType_.add(builder.build());
                    onChanged();
                } else {
                    this.nestedTypeBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addNestedType(DescriptorProto descriptorProto) {
                if (this.nestedTypeBuilder_ != null) {
                    this.nestedTypeBuilder_.addMessage(descriptorProto);
                } else if (descriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureNestedTypeIsMutable();
                    this.nestedType_.add(descriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addNestedTypeBuilder() {
                return (Builder) getNestedTypeFieldBuilder().addBuilder(DescriptorProto.getDefaultInstance());
            }

            public Builder addNestedTypeBuilder(int i) {
                return (Builder) getNestedTypeFieldBuilder().addBuilder(i, DescriptorProto.getDefaultInstance());
            }

            public Builder addOneofDecl(int i, Builder builder) {
                if (this.oneofDeclBuilder_ == null) {
                    ensureOneofDeclIsMutable();
                    this.oneofDecl_.add(i, builder.build());
                    onChanged();
                } else {
                    this.oneofDeclBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addOneofDecl(int i, OneofDescriptorProto oneofDescriptorProto) {
                if (this.oneofDeclBuilder_ != null) {
                    this.oneofDeclBuilder_.addMessage(i, oneofDescriptorProto);
                } else if (oneofDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureOneofDeclIsMutable();
                    this.oneofDecl_.add(i, oneofDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addOneofDecl(Builder builder) {
                if (this.oneofDeclBuilder_ == null) {
                    ensureOneofDeclIsMutable();
                    this.oneofDecl_.add(builder.build());
                    onChanged();
                } else {
                    this.oneofDeclBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addOneofDecl(OneofDescriptorProto oneofDescriptorProto) {
                if (this.oneofDeclBuilder_ != null) {
                    this.oneofDeclBuilder_.addMessage(oneofDescriptorProto);
                } else if (oneofDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureOneofDeclIsMutable();
                    this.oneofDecl_.add(oneofDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addOneofDeclBuilder() {
                return (Builder) getOneofDeclFieldBuilder().addBuilder(OneofDescriptorProto.getDefaultInstance());
            }

            public Builder addOneofDeclBuilder(int i) {
                return (Builder) getOneofDeclFieldBuilder().addBuilder(i, OneofDescriptorProto.getDefaultInstance());
            }

            public DescriptorProto build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public DescriptorProto buildPartial() {
                int i = 1;
                DescriptorProto descriptorProto = new DescriptorProto((com.google.protobuf.GeneratedMessage.Builder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                descriptorProto.name_ = this.name_;
                if (this.fieldBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.field_ = Collections.unmodifiableList(this.field_);
                        this.bitField0_ &= -3;
                    }
                    descriptorProto.field_ = this.field_;
                } else {
                    descriptorProto.field_ = this.fieldBuilder_.build();
                }
                if (this.extensionBuilder_ == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.extension_ = Collections.unmodifiableList(this.extension_);
                        this.bitField0_ &= -5;
                    }
                    descriptorProto.extension_ = this.extension_;
                } else {
                    descriptorProto.extension_ = this.extensionBuilder_.build();
                }
                if (this.nestedTypeBuilder_ == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.nestedType_ = Collections.unmodifiableList(this.nestedType_);
                        this.bitField0_ &= -9;
                    }
                    descriptorProto.nestedType_ = this.nestedType_;
                } else {
                    descriptorProto.nestedType_ = this.nestedTypeBuilder_.build();
                }
                if (this.enumTypeBuilder_ == null) {
                    if ((this.bitField0_ & 16) == 16) {
                        this.enumType_ = Collections.unmodifiableList(this.enumType_);
                        this.bitField0_ &= -17;
                    }
                    descriptorProto.enumType_ = this.enumType_;
                } else {
                    descriptorProto.enumType_ = this.enumTypeBuilder_.build();
                }
                if (this.extensionRangeBuilder_ == null) {
                    if ((this.bitField0_ & 32) == 32) {
                        this.extensionRange_ = Collections.unmodifiableList(this.extensionRange_);
                        this.bitField0_ &= -33;
                    }
                    descriptorProto.extensionRange_ = this.extensionRange_;
                } else {
                    descriptorProto.extensionRange_ = this.extensionRangeBuilder_.build();
                }
                if (this.oneofDeclBuilder_ == null) {
                    if ((this.bitField0_ & 64) == 64) {
                        this.oneofDecl_ = Collections.unmodifiableList(this.oneofDecl_);
                        this.bitField0_ &= -65;
                    }
                    descriptorProto.oneofDecl_ = this.oneofDecl_;
                } else {
                    descriptorProto.oneofDecl_ = this.oneofDeclBuilder_.build();
                }
                i2 = (i2 & 128) == 128 ? i | 2 : i;
                if (this.optionsBuilder_ == null) {
                    descriptorProto.options_ = this.options_;
                } else {
                    descriptorProto.options_ = (MessageOptions) this.optionsBuilder_.build();
                }
                descriptorProto.bitField0_ = i2;
                onBuilt();
                return descriptorProto;
            }

            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                if (this.fieldBuilder_ == null) {
                    this.field_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    this.fieldBuilder_.clear();
                }
                if (this.extensionBuilder_ == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    this.extensionBuilder_.clear();
                }
                if (this.nestedTypeBuilder_ == null) {
                    this.nestedType_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                } else {
                    this.nestedTypeBuilder_.clear();
                }
                if (this.enumTypeBuilder_ == null) {
                    this.enumType_ = Collections.emptyList();
                    this.bitField0_ &= -17;
                } else {
                    this.enumTypeBuilder_.clear();
                }
                if (this.extensionRangeBuilder_ == null) {
                    this.extensionRange_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                } else {
                    this.extensionRangeBuilder_.clear();
                }
                if (this.oneofDeclBuilder_ == null) {
                    this.oneofDecl_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                } else {
                    this.oneofDeclBuilder_.clear();
                }
                if (this.optionsBuilder_ == null) {
                    this.options_ = MessageOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -129;
                return this;
            }

            public Builder clearEnumType() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumType_ = Collections.emptyList();
                    this.bitField0_ &= -17;
                    onChanged();
                } else {
                    this.enumTypeBuilder_.clear();
                }
                return this;
            }

            public Builder clearExtension() {
                if (this.extensionBuilder_ == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    this.extensionBuilder_.clear();
                }
                return this;
            }

            public Builder clearExtensionRange() {
                if (this.extensionRangeBuilder_ == null) {
                    this.extensionRange_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                    onChanged();
                } else {
                    this.extensionRangeBuilder_.clear();
                }
                return this;
            }

            public Builder clearField() {
                if (this.fieldBuilder_ == null) {
                    this.field_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    this.fieldBuilder_.clear();
                }
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = DescriptorProto.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearNestedType() {
                if (this.nestedTypeBuilder_ == null) {
                    this.nestedType_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                    onChanged();
                } else {
                    this.nestedTypeBuilder_.clear();
                }
                return this;
            }

            public Builder clearOneofDecl() {
                if (this.oneofDeclBuilder_ == null) {
                    this.oneofDecl_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                    onChanged();
                } else {
                    this.oneofDeclBuilder_.clear();
                }
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = MessageOptions.getDefaultInstance();
                    onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -129;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public DescriptorProto getDefaultInstanceForType() {
                return DescriptorProto.getDefaultInstance();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
            }

            public EnumDescriptorProto getEnumType(int i) {
                return this.enumTypeBuilder_ == null ? (EnumDescriptorProto) this.enumType_.get(i) : (EnumDescriptorProto) this.enumTypeBuilder_.getMessage(i);
            }

            public Builder getEnumTypeBuilder(int i) {
                return (Builder) getEnumTypeFieldBuilder().getBuilder(i);
            }

            public List getEnumTypeBuilderList() {
                return getEnumTypeFieldBuilder().getBuilderList();
            }

            public int getEnumTypeCount() {
                return this.enumTypeBuilder_ == null ? this.enumType_.size() : this.enumTypeBuilder_.getCount();
            }

            public List getEnumTypeList() {
                return this.enumTypeBuilder_ == null ? Collections.unmodifiableList(this.enumType_) : this.enumTypeBuilder_.getMessageList();
            }

            public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int i) {
                return this.enumTypeBuilder_ == null ? (EnumDescriptorProtoOrBuilder) this.enumType_.get(i) : (EnumDescriptorProtoOrBuilder) this.enumTypeBuilder_.getMessageOrBuilder(i);
            }

            public List getEnumTypeOrBuilderList() {
                return this.enumTypeBuilder_ != null ? this.enumTypeBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.enumType_);
            }

            public FieldDescriptorProto getExtension(int i) {
                return this.extensionBuilder_ == null ? (FieldDescriptorProto) this.extension_.get(i) : (FieldDescriptorProto) this.extensionBuilder_.getMessage(i);
            }

            public Builder getExtensionBuilder(int i) {
                return (Builder) getExtensionFieldBuilder().getBuilder(i);
            }

            public List getExtensionBuilderList() {
                return getExtensionFieldBuilder().getBuilderList();
            }

            public int getExtensionCount() {
                return this.extensionBuilder_ == null ? this.extension_.size() : this.extensionBuilder_.getCount();
            }

            public List getExtensionList() {
                return this.extensionBuilder_ == null ? Collections.unmodifiableList(this.extension_) : this.extensionBuilder_.getMessageList();
            }

            public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int i) {
                return this.extensionBuilder_ == null ? (FieldDescriptorProtoOrBuilder) this.extension_.get(i) : (FieldDescriptorProtoOrBuilder) this.extensionBuilder_.getMessageOrBuilder(i);
            }

            public List getExtensionOrBuilderList() {
                return this.extensionBuilder_ != null ? this.extensionBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.extension_);
            }

            public ExtensionRange getExtensionRange(int i) {
                return this.extensionRangeBuilder_ == null ? (ExtensionRange) this.extensionRange_.get(i) : (ExtensionRange) this.extensionRangeBuilder_.getMessage(i);
            }

            public Builder getExtensionRangeBuilder(int i) {
                return (Builder) getExtensionRangeFieldBuilder().getBuilder(i);
            }

            public List getExtensionRangeBuilderList() {
                return getExtensionRangeFieldBuilder().getBuilderList();
            }

            public int getExtensionRangeCount() {
                return this.extensionRangeBuilder_ == null ? this.extensionRange_.size() : this.extensionRangeBuilder_.getCount();
            }

            public List getExtensionRangeList() {
                return this.extensionRangeBuilder_ == null ? Collections.unmodifiableList(this.extensionRange_) : this.extensionRangeBuilder_.getMessageList();
            }

            public ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int i) {
                return this.extensionRangeBuilder_ == null ? (ExtensionRangeOrBuilder) this.extensionRange_.get(i) : (ExtensionRangeOrBuilder) this.extensionRangeBuilder_.getMessageOrBuilder(i);
            }

            public List getExtensionRangeOrBuilderList() {
                return this.extensionRangeBuilder_ != null ? this.extensionRangeBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.extensionRange_);
            }

            public FieldDescriptorProto getField(int i) {
                return this.fieldBuilder_ == null ? (FieldDescriptorProto) this.field_.get(i) : (FieldDescriptorProto) this.fieldBuilder_.getMessage(i);
            }

            public Builder getFieldBuilder(int i) {
                return (Builder) getFieldFieldBuilder().getBuilder(i);
            }

            public List getFieldBuilderList() {
                return getFieldFieldBuilder().getBuilderList();
            }

            public int getFieldCount() {
                return this.fieldBuilder_ == null ? this.field_.size() : this.fieldBuilder_.getCount();
            }

            public List getFieldList() {
                return this.fieldBuilder_ == null ? Collections.unmodifiableList(this.field_) : this.fieldBuilder_.getMessageList();
            }

            public FieldDescriptorProtoOrBuilder getFieldOrBuilder(int i) {
                return this.fieldBuilder_ == null ? (FieldDescriptorProtoOrBuilder) this.field_.get(i) : (FieldDescriptorProtoOrBuilder) this.fieldBuilder_.getMessageOrBuilder(i);
            }

            public List getFieldOrBuilderList() {
                return this.fieldBuilder_ != null ? this.fieldBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.field_);
            }

            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public DescriptorProto getNestedType(int i) {
                return this.nestedTypeBuilder_ == null ? (DescriptorProto) this.nestedType_.get(i) : (DescriptorProto) this.nestedTypeBuilder_.getMessage(i);
            }

            public Builder getNestedTypeBuilder(int i) {
                return (Builder) getNestedTypeFieldBuilder().getBuilder(i);
            }

            public List getNestedTypeBuilderList() {
                return getNestedTypeFieldBuilder().getBuilderList();
            }

            public int getNestedTypeCount() {
                return this.nestedTypeBuilder_ == null ? this.nestedType_.size() : this.nestedTypeBuilder_.getCount();
            }

            public List getNestedTypeList() {
                return this.nestedTypeBuilder_ == null ? Collections.unmodifiableList(this.nestedType_) : this.nestedTypeBuilder_.getMessageList();
            }

            public DescriptorProtoOrBuilder getNestedTypeOrBuilder(int i) {
                return this.nestedTypeBuilder_ == null ? (DescriptorProtoOrBuilder) this.nestedType_.get(i) : (DescriptorProtoOrBuilder) this.nestedTypeBuilder_.getMessageOrBuilder(i);
            }

            public List getNestedTypeOrBuilderList() {
                return this.nestedTypeBuilder_ != null ? this.nestedTypeBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.nestedType_);
            }

            public OneofDescriptorProto getOneofDecl(int i) {
                return this.oneofDeclBuilder_ == null ? (OneofDescriptorProto) this.oneofDecl_.get(i) : (OneofDescriptorProto) this.oneofDeclBuilder_.getMessage(i);
            }

            public Builder getOneofDeclBuilder(int i) {
                return (Builder) getOneofDeclFieldBuilder().getBuilder(i);
            }

            public List getOneofDeclBuilderList() {
                return getOneofDeclFieldBuilder().getBuilderList();
            }

            public int getOneofDeclCount() {
                return this.oneofDeclBuilder_ == null ? this.oneofDecl_.size() : this.oneofDeclBuilder_.getCount();
            }

            public List getOneofDeclList() {
                return this.oneofDeclBuilder_ == null ? Collections.unmodifiableList(this.oneofDecl_) : this.oneofDeclBuilder_.getMessageList();
            }

            public OneofDescriptorProtoOrBuilder getOneofDeclOrBuilder(int i) {
                return this.oneofDeclBuilder_ == null ? (OneofDescriptorProtoOrBuilder) this.oneofDecl_.get(i) : (OneofDescriptorProtoOrBuilder) this.oneofDeclBuilder_.getMessageOrBuilder(i);
            }

            public List getOneofDeclOrBuilderList() {
                return this.oneofDeclBuilder_ != null ? this.oneofDeclBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.oneofDecl_);
            }

            public MessageOptions getOptions() {
                return this.optionsBuilder_ == null ? this.options_ : (MessageOptions) this.optionsBuilder_.getMessage();
            }

            public Builder getOptionsBuilder() {
                this.bitField0_ |= 128;
                onChanged();
                return (Builder) getOptionsFieldBuilder().getBuilder();
            }

            public MessageOptionsOrBuilder getOptionsOrBuilder() {
                return this.optionsBuilder_ != null ? (MessageOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder() : this.options_;
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 128) == 128;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f208x907d0bf0.ensureFieldAccessorsInitialized(DescriptorProto.class, Builder.class);
            }

            public final boolean isInitialized() {
                int i;
                for (i = 0; i < getFieldCount(); i++) {
                    if (!getField(i).isInitialized()) {
                        return false;
                    }
                }
                for (i = 0; i < getExtensionCount(); i++) {
                    if (!getExtension(i).isInitialized()) {
                        return false;
                    }
                }
                for (i = 0; i < getNestedTypeCount(); i++) {
                    if (!getNestedType(i).isInitialized()) {
                        return false;
                    }
                }
                for (i = 0; i < getEnumTypeCount(); i++) {
                    if (!getEnumType(i).isInitialized()) {
                        return false;
                    }
                }
                return !hasOptions() || getOptions().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                DescriptorProto descriptorProto;
                Throwable th;
                try {
                    descriptorProto = (DescriptorProto) DescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (descriptorProto != null) {
                        mergeFrom(descriptorProto);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    descriptorProto = (DescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (descriptorProto != null) {
                    mergeFrom(descriptorProto);
                }
                throw th;
            }

            public Builder mergeFrom(DescriptorProto descriptorProto) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (descriptorProto != DescriptorProto.getDefaultInstance()) {
                    if (descriptorProto.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = descriptorProto.name_;
                        onChanged();
                    }
                    if (this.fieldBuilder_ == null) {
                        if (!descriptorProto.field_.isEmpty()) {
                            if (this.field_.isEmpty()) {
                                this.field_ = descriptorProto.field_;
                                this.bitField0_ &= -3;
                            } else {
                                ensureFieldIsMutable();
                                this.field_.addAll(descriptorProto.field_);
                            }
                            onChanged();
                        }
                    } else if (!descriptorProto.field_.isEmpty()) {
                        if (this.fieldBuilder_.isEmpty()) {
                            this.fieldBuilder_.dispose();
                            this.fieldBuilder_ = null;
                            this.field_ = descriptorProto.field_;
                            this.bitField0_ &= -3;
                            this.fieldBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getFieldFieldBuilder() : null;
                        } else {
                            this.fieldBuilder_.addAllMessages(descriptorProto.field_);
                        }
                    }
                    if (this.extensionBuilder_ == null) {
                        if (!descriptorProto.extension_.isEmpty()) {
                            if (this.extension_.isEmpty()) {
                                this.extension_ = descriptorProto.extension_;
                                this.bitField0_ &= -5;
                            } else {
                                ensureExtensionIsMutable();
                                this.extension_.addAll(descriptorProto.extension_);
                            }
                            onChanged();
                        }
                    } else if (!descriptorProto.extension_.isEmpty()) {
                        if (this.extensionBuilder_.isEmpty()) {
                            this.extensionBuilder_.dispose();
                            this.extensionBuilder_ = null;
                            this.extension_ = descriptorProto.extension_;
                            this.bitField0_ &= -5;
                            this.extensionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getExtensionFieldBuilder() : null;
                        } else {
                            this.extensionBuilder_.addAllMessages(descriptorProto.extension_);
                        }
                    }
                    if (this.nestedTypeBuilder_ == null) {
                        if (!descriptorProto.nestedType_.isEmpty()) {
                            if (this.nestedType_.isEmpty()) {
                                this.nestedType_ = descriptorProto.nestedType_;
                                this.bitField0_ &= -9;
                            } else {
                                ensureNestedTypeIsMutable();
                                this.nestedType_.addAll(descriptorProto.nestedType_);
                            }
                            onChanged();
                        }
                    } else if (!descriptorProto.nestedType_.isEmpty()) {
                        if (this.nestedTypeBuilder_.isEmpty()) {
                            this.nestedTypeBuilder_.dispose();
                            this.nestedTypeBuilder_ = null;
                            this.nestedType_ = descriptorProto.nestedType_;
                            this.bitField0_ &= -9;
                            this.nestedTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getNestedTypeFieldBuilder() : null;
                        } else {
                            this.nestedTypeBuilder_.addAllMessages(descriptorProto.nestedType_);
                        }
                    }
                    if (this.enumTypeBuilder_ == null) {
                        if (!descriptorProto.enumType_.isEmpty()) {
                            if (this.enumType_.isEmpty()) {
                                this.enumType_ = descriptorProto.enumType_;
                                this.bitField0_ &= -17;
                            } else {
                                ensureEnumTypeIsMutable();
                                this.enumType_.addAll(descriptorProto.enumType_);
                            }
                            onChanged();
                        }
                    } else if (!descriptorProto.enumType_.isEmpty()) {
                        if (this.enumTypeBuilder_.isEmpty()) {
                            this.enumTypeBuilder_.dispose();
                            this.enumTypeBuilder_ = null;
                            this.enumType_ = descriptorProto.enumType_;
                            this.bitField0_ &= -17;
                            this.enumTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null;
                        } else {
                            this.enumTypeBuilder_.addAllMessages(descriptorProto.enumType_);
                        }
                    }
                    if (this.extensionRangeBuilder_ == null) {
                        if (!descriptorProto.extensionRange_.isEmpty()) {
                            if (this.extensionRange_.isEmpty()) {
                                this.extensionRange_ = descriptorProto.extensionRange_;
                                this.bitField0_ &= -33;
                            } else {
                                ensureExtensionRangeIsMutable();
                                this.extensionRange_.addAll(descriptorProto.extensionRange_);
                            }
                            onChanged();
                        }
                    } else if (!descriptorProto.extensionRange_.isEmpty()) {
                        if (this.extensionRangeBuilder_.isEmpty()) {
                            this.extensionRangeBuilder_.dispose();
                            this.extensionRangeBuilder_ = null;
                            this.extensionRange_ = descriptorProto.extensionRange_;
                            this.bitField0_ &= -33;
                            this.extensionRangeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getExtensionRangeFieldBuilder() : null;
                        } else {
                            this.extensionRangeBuilder_.addAllMessages(descriptorProto.extensionRange_);
                        }
                    }
                    if (this.oneofDeclBuilder_ == null) {
                        if (!descriptorProto.oneofDecl_.isEmpty()) {
                            if (this.oneofDecl_.isEmpty()) {
                                this.oneofDecl_ = descriptorProto.oneofDecl_;
                                this.bitField0_ &= -65;
                            } else {
                                ensureOneofDeclIsMutable();
                                this.oneofDecl_.addAll(descriptorProto.oneofDecl_);
                            }
                            onChanged();
                        }
                    } else if (!descriptorProto.oneofDecl_.isEmpty()) {
                        if (this.oneofDeclBuilder_.isEmpty()) {
                            this.oneofDeclBuilder_.dispose();
                            this.oneofDeclBuilder_ = null;
                            this.oneofDecl_ = descriptorProto.oneofDecl_;
                            this.bitField0_ &= -65;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getOneofDeclFieldBuilder();
                            }
                            this.oneofDeclBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.oneofDeclBuilder_.addAllMessages(descriptorProto.oneofDecl_);
                        }
                    }
                    if (descriptorProto.hasOptions()) {
                        mergeOptions(descriptorProto.getOptions());
                    }
                    mergeUnknownFields(descriptorProto.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof DescriptorProto) {
                    return mergeFrom((DescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(MessageOptions messageOptions) {
                if (this.optionsBuilder_ == null) {
                    if ((this.bitField0_ & 128) != 128 || this.options_ == MessageOptions.getDefaultInstance()) {
                        this.options_ = messageOptions;
                    } else {
                        this.options_ = MessageOptions.newBuilder(this.options_).mergeFrom(messageOptions).buildPartial();
                    }
                    onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(messageOptions);
                }
                this.bitField0_ |= 128;
                return this;
            }

            public Builder removeEnumType(int i) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    this.enumType_.remove(i);
                    onChanged();
                } else {
                    this.enumTypeBuilder_.remove(i);
                }
                return this;
            }

            public Builder removeExtension(int i) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    this.extension_.remove(i);
                    onChanged();
                } else {
                    this.extensionBuilder_.remove(i);
                }
                return this;
            }

            public Builder removeExtensionRange(int i) {
                if (this.extensionRangeBuilder_ == null) {
                    ensureExtensionRangeIsMutable();
                    this.extensionRange_.remove(i);
                    onChanged();
                } else {
                    this.extensionRangeBuilder_.remove(i);
                }
                return this;
            }

            public Builder removeField(int i) {
                if (this.fieldBuilder_ == null) {
                    ensureFieldIsMutable();
                    this.field_.remove(i);
                    onChanged();
                } else {
                    this.fieldBuilder_.remove(i);
                }
                return this;
            }

            public Builder removeNestedType(int i) {
                if (this.nestedTypeBuilder_ == null) {
                    ensureNestedTypeIsMutable();
                    this.nestedType_.remove(i);
                    onChanged();
                } else {
                    this.nestedTypeBuilder_.remove(i);
                }
                return this;
            }

            public Builder removeOneofDecl(int i) {
                if (this.oneofDeclBuilder_ == null) {
                    ensureOneofDeclIsMutable();
                    this.oneofDecl_.remove(i);
                    onChanged();
                } else {
                    this.oneofDeclBuilder_.remove(i);
                }
                return this;
            }

            public Builder setEnumType(int i, Builder builder) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    this.enumType_.set(i, builder.build());
                    onChanged();
                } else {
                    this.enumTypeBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setEnumType(int i, EnumDescriptorProto enumDescriptorProto) {
                if (this.enumTypeBuilder_ != null) {
                    this.enumTypeBuilder_.setMessage(i, enumDescriptorProto);
                } else if (enumDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureEnumTypeIsMutable();
                    this.enumType_.set(i, enumDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setExtension(int i, Builder builder) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    this.extension_.set(i, builder.build());
                    onChanged();
                } else {
                    this.extensionBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setExtension(int i, FieldDescriptorProto fieldDescriptorProto) {
                if (this.extensionBuilder_ != null) {
                    this.extensionBuilder_.setMessage(i, fieldDescriptorProto);
                } else if (fieldDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureExtensionIsMutable();
                    this.extension_.set(i, fieldDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setExtensionRange(int i, Builder builder) {
                if (this.extensionRangeBuilder_ == null) {
                    ensureExtensionRangeIsMutable();
                    this.extensionRange_.set(i, builder.build());
                    onChanged();
                } else {
                    this.extensionRangeBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setExtensionRange(int i, ExtensionRange extensionRange) {
                if (this.extensionRangeBuilder_ != null) {
                    this.extensionRangeBuilder_.setMessage(i, extensionRange);
                } else if (extensionRange == null) {
                    throw new NullPointerException();
                } else {
                    ensureExtensionRangeIsMutable();
                    this.extensionRange_.set(i, extensionRange);
                    onChanged();
                }
                return this;
            }

            public Builder setField(int i, Builder builder) {
                if (this.fieldBuilder_ == null) {
                    ensureFieldIsMutable();
                    this.field_.set(i, builder.build());
                    onChanged();
                } else {
                    this.fieldBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setField(int i, FieldDescriptorProto fieldDescriptorProto) {
                if (this.fieldBuilder_ != null) {
                    this.fieldBuilder_.setMessage(i, fieldDescriptorProto);
                } else if (fieldDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureFieldIsMutable();
                    this.field_.set(i, fieldDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder setNestedType(int i, Builder builder) {
                if (this.nestedTypeBuilder_ == null) {
                    ensureNestedTypeIsMutable();
                    this.nestedType_.set(i, builder.build());
                    onChanged();
                } else {
                    this.nestedTypeBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setNestedType(int i, DescriptorProto descriptorProto) {
                if (this.nestedTypeBuilder_ != null) {
                    this.nestedTypeBuilder_.setMessage(i, descriptorProto);
                } else if (descriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureNestedTypeIsMutable();
                    this.nestedType_.set(i, descriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setOneofDecl(int i, Builder builder) {
                if (this.oneofDeclBuilder_ == null) {
                    ensureOneofDeclIsMutable();
                    this.oneofDecl_.set(i, builder.build());
                    onChanged();
                } else {
                    this.oneofDeclBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setOneofDecl(int i, OneofDescriptorProto oneofDescriptorProto) {
                if (this.oneofDeclBuilder_ != null) {
                    this.oneofDeclBuilder_.setMessage(i, oneofDescriptorProto);
                } else if (oneofDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureOneofDeclIsMutable();
                    this.oneofDecl_.set(i, oneofDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setOptions(Builder builder) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builder.build());
                }
                this.bitField0_ |= 128;
                return this;
            }

            public Builder setOptions(MessageOptions messageOptions) {
                if (this.optionsBuilder_ != null) {
                    this.optionsBuilder_.setMessage(messageOptions);
                } else if (messageOptions == null) {
                    throw new NullPointerException();
                } else {
                    this.options_ = messageOptions;
                    onChanged();
                }
                this.bitField0_ |= 128;
                return this;
            }
        }

        public interface ExtensionRangeOrBuilder extends MessageOrBuilder {
            int getEnd();

            int getStart();

            boolean hasEnd();

            boolean hasStart();
        }

        public final class ExtensionRange extends GeneratedMessage implements ExtensionRangeOrBuilder {
            public static final int END_FIELD_NUMBER = 2;
            public static Parser PARSER = new C19641();
            public static final int START_FIELD_NUMBER = 1;
            private static final ExtensionRange defaultInstance = new ExtensionRange(true);
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private int end_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            private int start_;
            private final UnknownFieldSet unknownFields;

            final class C19641 extends AbstractParser {
                C19641() {
                }

                public ExtensionRange parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new ExtensionRange(codedInputStream, extensionRegistryLite);
                }
            }

            public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements ExtensionRangeOrBuilder {
                private int bitField0_;
                private int end_;
                private int start_;

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                private static Builder create() {
                    return new Builder();
                }

                public static final Descriptor getDescriptor() {
                    return DescriptorProtos.f206x1458f8d;
                }

                private void maybeForceBuilderInitialization() {
                    if (!GeneratedMessage.alwaysUseFieldBuilders) {
                    }
                }

                public ExtensionRange build() {
                    Object buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
                }

                public ExtensionRange buildPartial() {
                    int i = 1;
                    ExtensionRange extensionRange = new ExtensionRange((com.google.protobuf.GeneratedMessage.Builder) this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 1) {
                        i = 0;
                    }
                    extensionRange.start_ = this.start_;
                    if ((i2 & 2) == 2) {
                        i |= 2;
                    }
                    extensionRange.end_ = this.end_;
                    extensionRange.bitField0_ = i;
                    onBuilt();
                    return extensionRange;
                }

                public Builder clear() {
                    super.clear();
                    this.start_ = 0;
                    this.bitField0_ &= -2;
                    this.end_ = 0;
                    this.bitField0_ &= -3;
                    return this;
                }

                public Builder clearEnd() {
                    this.bitField0_ &= -3;
                    this.end_ = 0;
                    onChanged();
                    return this;
                }

                public Builder clearStart() {
                    this.bitField0_ &= -2;
                    this.start_ = 0;
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return create().mergeFrom(buildPartial());
                }

                public ExtensionRange getDefaultInstanceForType() {
                    return ExtensionRange.getDefaultInstance();
                }

                public Descriptor getDescriptorForType() {
                    return DescriptorProtos.f206x1458f8d;
                }

                public int getEnd() {
                    return this.end_;
                }

                public int getStart() {
                    return this.start_;
                }

                public boolean hasEnd() {
                    return (this.bitField0_ & 2) == 2;
                }

                public boolean hasStart() {
                    return (this.bitField0_ & 1) == 1;
                }

                protected FieldAccessorTable internalGetFieldAccessorTable() {
                    return DescriptorProtos.f207xf366d90b.ensureFieldAccessorsInitialized(ExtensionRange.class, Builder.class);
                }

                public final boolean isInitialized() {
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    ExtensionRange extensionRange;
                    Throwable th;
                    try {
                        extensionRange = (ExtensionRange) ExtensionRange.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (extensionRange != null) {
                            mergeFrom(extensionRange);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        InvalidProtocolBufferException invalidProtocolBufferException = e;
                        extensionRange = (ExtensionRange) invalidProtocolBufferException.getUnfinishedMessage();
                        throw invalidProtocolBufferException;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    if (extensionRange != null) {
                        mergeFrom(extensionRange);
                    }
                    throw th;
                }

                public Builder mergeFrom(ExtensionRange extensionRange) {
                    if (extensionRange != ExtensionRange.getDefaultInstance()) {
                        if (extensionRange.hasStart()) {
                            setStart(extensionRange.getStart());
                        }
                        if (extensionRange.hasEnd()) {
                            setEnd(extensionRange.getEnd());
                        }
                        mergeUnknownFields(extensionRange.getUnknownFields());
                    }
                    return this;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof ExtensionRange) {
                        return mergeFrom((ExtensionRange) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder setEnd(int i) {
                    this.bitField0_ |= 2;
                    this.end_ = i;
                    onChanged();
                    return this;
                }

                public Builder setStart(int i) {
                    this.bitField0_ |= 1;
                    this.start_ = i;
                    onChanged();
                    return this;
                }
            }

            static {
                defaultInstance.initFields();
            }

            private ExtensionRange(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                initFields();
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                Object obj = null;
                while (obj == null) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                obj = 1;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.start_ = codedInputStream.readInt32();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.end_ = codedInputStream.readInt32();
                                break;
                            default:
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    obj = 1;
                                    break;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }

            private ExtensionRange(com.google.protobuf.GeneratedMessage.Builder builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private ExtensionRange(boolean z) {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static ExtensionRange getDefaultInstance() {
                return defaultInstance;
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.f206x1458f8d;
            }

            private void initFields() {
                this.start_ = 0;
                this.end_ = 0;
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            public static Builder newBuilder(ExtensionRange extensionRange) {
                return newBuilder().mergeFrom(extensionRange);
            }

            public static ExtensionRange parseDelimitedFrom(InputStream inputStream) {
                return (ExtensionRange) PARSER.parseDelimitedFrom(inputStream);
            }

            public static ExtensionRange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ExtensionRange) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static ExtensionRange parseFrom(ByteString byteString) {
                return (ExtensionRange) PARSER.parseFrom(byteString);
            }

            public static ExtensionRange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (ExtensionRange) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ExtensionRange parseFrom(CodedInputStream codedInputStream) {
                return (ExtensionRange) PARSER.parseFrom(codedInputStream);
            }

            public static ExtensionRange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ExtensionRange) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static ExtensionRange parseFrom(InputStream inputStream) {
                return (ExtensionRange) PARSER.parseFrom(inputStream);
            }

            public static ExtensionRange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (ExtensionRange) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static ExtensionRange parseFrom(byte[] bArr) {
                return (ExtensionRange) PARSER.parseFrom(bArr);
            }

            public static ExtensionRange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (ExtensionRange) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public ExtensionRange getDefaultInstanceForType() {
                return defaultInstance;
            }

            public int getEnd() {
                return this.end_;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                i = 0;
                if ((this.bitField0_ & 1) == 1) {
                    i = CodedOutputStream.computeInt32Size(1, this.start_) + 0;
                }
                if ((this.bitField0_ & 2) == 2) {
                    i += CodedOutputStream.computeInt32Size(2, this.end_);
                }
                i += getUnknownFields().getSerializedSize();
                this.memoizedSerializedSize = i;
                return i;
            }

            public int getStart() {
                return this.start_;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            public boolean hasEnd() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasStart() {
                return (this.bitField0_ & 1) == 1;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f207xf366d90b.ensureFieldAccessorsInitialized(ExtensionRange.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == (byte) 1) {
                    return true;
                }
                if (b == (byte) 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            protected Builder newBuilderForType(BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            protected Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    codedOutputStream.writeInt32(1, this.start_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    codedOutputStream.writeInt32(2, this.end_);
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        static {
            defaultInstance.initFields();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private DescriptorProto(com.google.protobuf.CodedInputStream r12, com.google.protobuf.ExtensionRegistryLite r13) {
            /*
            r11 = this;
            r9 = 32;
            r8 = 16;
            r7 = 8;
            r6 = 4;
            r5 = 2;
            r11.<init>();
            r0 = -1;
            r11.memoizedIsInitialized = r0;
            r0 = -1;
            r11.memoizedSerializedSize = r0;
            r11.initFields();
            r2 = 0;
            r4 = com.google.protobuf.UnknownFieldSet.newBuilder();
            r1 = 0;
        L_0x001a:
            if (r1 != 0) goto L_0x018b;
        L_0x001c:
            r0 = r12.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            switch(r0) {
                case 0: goto L_0x01f9;
                case 10: goto L_0x002e;
                case 18: goto L_0x003d;
                case 26: goto L_0x0059;
                case 34: goto L_0x0075;
                case 42: goto L_0x0091;
                case 50: goto L_0x00ae;
                case 58: goto L_0x00cb;
                case 66: goto L_0x00f9;
                default: goto L_0x0023;
            };	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
        L_0x0023:
            r0 = r11.parseUnknownField(r12, r4, r13, r0);	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            if (r0 != 0) goto L_0x01f5;
        L_0x0029:
            r0 = 1;
            r1 = r2;
        L_0x002b:
            r2 = r1;
            r1 = r0;
            goto L_0x001a;
        L_0x002e:
            r0 = r12.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r3 = r11.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r3 = r3 | 1;
            r11.bitField0_ = r3;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r11.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r1;
            r1 = r2;
            goto L_0x002b;
        L_0x003d:
            r0 = r2 & 2;
            if (r0 == r5) goto L_0x01f2;
        L_0x0041:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r11.field_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r2 | 2;
        L_0x004a:
            r2 = r11.field_;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = com.google.protobuf.DescriptorProtos.FieldDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = r12.readMessage(r3, r13);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r10 = r1;
            r1 = r0;
            r0 = r10;
            goto L_0x002b;
        L_0x0059:
            r0 = r2 & 8;
            if (r0 == r7) goto L_0x01ef;
        L_0x005d:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r11.nestedType_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r2 | 8;
        L_0x0066:
            r2 = r11.nestedType_;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = r12.readMessage(r3, r13);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r10 = r1;
            r1 = r0;
            r0 = r10;
            goto L_0x002b;
        L_0x0075:
            r0 = r2 & 16;
            if (r0 == r8) goto L_0x01ec;
        L_0x0079:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r11.enumType_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r2 | 16;
        L_0x0082:
            r2 = r11.enumType_;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = com.google.protobuf.DescriptorProtos.EnumDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = r12.readMessage(r3, r13);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r10 = r1;
            r1 = r0;
            r0 = r10;
            goto L_0x002b;
        L_0x0091:
            r0 = r2 & 32;
            if (r0 == r9) goto L_0x01e9;
        L_0x0095:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r11.extensionRange_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r2 | 32;
        L_0x009e:
            r2 = r11.extensionRange_;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = com.google.protobuf.DescriptorProtos.DescriptorProto.ExtensionRange.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = r12.readMessage(r3, r13);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r10 = r1;
            r1 = r0;
            r0 = r10;
            goto L_0x002b;
        L_0x00ae:
            r0 = r2 & 4;
            if (r0 == r6) goto L_0x01e6;
        L_0x00b2:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r11.extension_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r2 | 4;
        L_0x00bb:
            r2 = r11.extension_;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = com.google.protobuf.DescriptorProtos.FieldDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = r12.readMessage(r3, r13);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r10 = r1;
            r1 = r0;
            r0 = r10;
            goto L_0x002b;
        L_0x00cb:
            r0 = r11.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r0 & 2;
            if (r0 != r5) goto L_0x01e2;
        L_0x00d1:
            r0 = r11.options_;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r3 = r0;
        L_0x00d8:
            r0 = com.google.protobuf.DescriptorProtos.MessageOptions.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r12.readMessage(r0, r13);	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = (com.google.protobuf.DescriptorProtos.MessageOptions) r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r11.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            if (r3 == 0) goto L_0x00ef;
        L_0x00e4:
            r0 = r11.options_;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r3.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r3.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r11.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
        L_0x00ef:
            r0 = r11.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r0 | 2;
            r11.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r1;
            r1 = r2;
            goto L_0x002b;
        L_0x00f9:
            r0 = r2 & 64;
            r3 = 64;
            if (r0 == r3) goto L_0x01df;
        L_0x00ff:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r11.oneofDecl_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x0206, IOException -> 0x0203 }
            r0 = r2 | 64;
        L_0x0108:
            r2 = r11.oneofDecl_;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = com.google.protobuf.DescriptorProtos.OneofDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r3 = r12.readMessage(r3, r13);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0118, IOException -> 0x0178, all -> 0x01fd }
            r10 = r1;
            r1 = r0;
            r0 = r10;
            goto L_0x002b;
        L_0x0118:
            r1 = move-exception;
            r10 = r1;
            r1 = r0;
            r0 = r10;
            r2 = r1;
        L_0x011d:
            r0 = r0.setUnfinishedMessage(r11);	 Catch:{ all -> 0x0122 }
            throw r0;	 Catch:{ all -> 0x0122 }
        L_0x0122:
            r0 = move-exception;
            r1 = r2;
        L_0x0124:
            r2 = r1 & 2;
            if (r2 != r5) goto L_0x0130;
        L_0x0128:
            r2 = r11.field_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r11.field_ = r2;
        L_0x0130:
            r2 = r1 & 8;
            if (r2 != r7) goto L_0x013c;
        L_0x0134:
            r2 = r11.nestedType_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r11.nestedType_ = r2;
        L_0x013c:
            r2 = r1 & 16;
            if (r2 != r8) goto L_0x0148;
        L_0x0140:
            r2 = r11.enumType_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r11.enumType_ = r2;
        L_0x0148:
            r2 = r1 & 32;
            if (r2 != r9) goto L_0x0154;
        L_0x014c:
            r2 = r11.extensionRange_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r11.extensionRange_ = r2;
        L_0x0154:
            r2 = r1 & 4;
            if (r2 != r6) goto L_0x0160;
        L_0x0158:
            r2 = r11.extension_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r11.extension_ = r2;
        L_0x0160:
            r1 = r1 & 64;
            r2 = 64;
            if (r1 != r2) goto L_0x016e;
        L_0x0166:
            r1 = r11.oneofDecl_;
            r1 = java.util.Collections.unmodifiableList(r1);
            r11.oneofDecl_ = r1;
        L_0x016e:
            r1 = r4.build();
            r11.unknownFields = r1;
            r11.makeExtensionsImmutable();
            throw r0;
        L_0x0178:
            r1 = move-exception;
            r10 = r1;
            r1 = r0;
            r0 = r10;
            r2 = r1;
        L_0x017d:
            r1 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x0122 }
            r0 = r0.getMessage();	 Catch:{ all -> 0x0122 }
            r1.<init>(r0);	 Catch:{ all -> 0x0122 }
            r0 = r1.setUnfinishedMessage(r11);	 Catch:{ all -> 0x0122 }
            throw r0;	 Catch:{ all -> 0x0122 }
        L_0x018b:
            r0 = r2 & 2;
            if (r0 != r5) goto L_0x0197;
        L_0x018f:
            r0 = r11.field_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r11.field_ = r0;
        L_0x0197:
            r0 = r2 & 8;
            if (r0 != r7) goto L_0x01a3;
        L_0x019b:
            r0 = r11.nestedType_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r11.nestedType_ = r0;
        L_0x01a3:
            r0 = r2 & 16;
            if (r0 != r8) goto L_0x01af;
        L_0x01a7:
            r0 = r11.enumType_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r11.enumType_ = r0;
        L_0x01af:
            r0 = r2 & 32;
            if (r0 != r9) goto L_0x01bb;
        L_0x01b3:
            r0 = r11.extensionRange_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r11.extensionRange_ = r0;
        L_0x01bb:
            r0 = r2 & 4;
            if (r0 != r6) goto L_0x01c7;
        L_0x01bf:
            r0 = r11.extension_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r11.extension_ = r0;
        L_0x01c7:
            r0 = r2 & 64;
            r1 = 64;
            if (r0 != r1) goto L_0x01d5;
        L_0x01cd:
            r0 = r11.oneofDecl_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r11.oneofDecl_ = r0;
        L_0x01d5:
            r0 = r4.build();
            r11.unknownFields = r0;
            r11.makeExtensionsImmutable();
            return;
        L_0x01df:
            r0 = r2;
            goto L_0x0108;
        L_0x01e2:
            r0 = 0;
            r3 = r0;
            goto L_0x00d8;
        L_0x01e6:
            r0 = r2;
            goto L_0x00bb;
        L_0x01e9:
            r0 = r2;
            goto L_0x009e;
        L_0x01ec:
            r0 = r2;
            goto L_0x0082;
        L_0x01ef:
            r0 = r2;
            goto L_0x0066;
        L_0x01f2:
            r0 = r2;
            goto L_0x004a;
        L_0x01f5:
            r0 = r1;
            r1 = r2;
            goto L_0x002b;
        L_0x01f9:
            r0 = 1;
            r1 = r2;
            goto L_0x002b;
        L_0x01fd:
            r1 = move-exception;
            r10 = r1;
            r1 = r0;
            r0 = r10;
            goto L_0x0124;
        L_0x0203:
            r0 = move-exception;
            goto L_0x017d;
        L_0x0206:
            r0 = move-exception;
            goto L_0x011d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.DescriptorProtos.DescriptorProto.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
        }

        private DescriptorProto(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private DescriptorProto(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static DescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
        }

        private void initFields() {
            this.name_ = "";
            this.field_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.nestedType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.extensionRange_ = Collections.emptyList();
            this.oneofDecl_ = Collections.emptyList();
            this.options_ = MessageOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(DescriptorProto descriptorProto) {
            return newBuilder().mergeFrom(descriptorProto);
        }

        public static DescriptorProto parseDelimitedFrom(InputStream inputStream) {
            return (DescriptorProto) PARSER.parseDelimitedFrom(inputStream);
        }

        public static DescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DescriptorProto) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static DescriptorProto parseFrom(ByteString byteString) {
            return (DescriptorProto) PARSER.parseFrom(byteString);
        }

        public static DescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (DescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static DescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return (DescriptorProto) PARSER.parseFrom(codedInputStream);
        }

        public static DescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DescriptorProto) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static DescriptorProto parseFrom(InputStream inputStream) {
            return (DescriptorProto) PARSER.parseFrom(inputStream);
        }

        public static DescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DescriptorProto) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static DescriptorProto parseFrom(byte[] bArr) {
            return (DescriptorProto) PARSER.parseFrom(bArr);
        }

        public static DescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (DescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public DescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public EnumDescriptorProto getEnumType(int i) {
            return (EnumDescriptorProto) this.enumType_.get(i);
        }

        public int getEnumTypeCount() {
            return this.enumType_.size();
        }

        public List getEnumTypeList() {
            return this.enumType_;
        }

        public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int i) {
            return (EnumDescriptorProtoOrBuilder) this.enumType_.get(i);
        }

        public List getEnumTypeOrBuilderList() {
            return this.enumType_;
        }

        public FieldDescriptorProto getExtension(int i) {
            return (FieldDescriptorProto) this.extension_.get(i);
        }

        public int getExtensionCount() {
            return this.extension_.size();
        }

        public List getExtensionList() {
            return this.extension_;
        }

        public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int i) {
            return (FieldDescriptorProtoOrBuilder) this.extension_.get(i);
        }

        public List getExtensionOrBuilderList() {
            return this.extension_;
        }

        public ExtensionRange getExtensionRange(int i) {
            return (ExtensionRange) this.extensionRange_.get(i);
        }

        public int getExtensionRangeCount() {
            return this.extensionRange_.size();
        }

        public List getExtensionRangeList() {
            return this.extensionRange_;
        }

        public ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int i) {
            return (ExtensionRangeOrBuilder) this.extensionRange_.get(i);
        }

        public List getExtensionRangeOrBuilderList() {
            return this.extensionRange_;
        }

        public FieldDescriptorProto getField(int i) {
            return (FieldDescriptorProto) this.field_.get(i);
        }

        public int getFieldCount() {
            return this.field_.size();
        }

        public List getFieldList() {
            return this.field_;
        }

        public FieldDescriptorProtoOrBuilder getFieldOrBuilder(int i) {
            return (FieldDescriptorProtoOrBuilder) this.field_.get(i);
        }

        public List getFieldOrBuilderList() {
            return this.field_;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public DescriptorProto getNestedType(int i) {
            return (DescriptorProto) this.nestedType_.get(i);
        }

        public int getNestedTypeCount() {
            return this.nestedType_.size();
        }

        public List getNestedTypeList() {
            return this.nestedType_;
        }

        public DescriptorProtoOrBuilder getNestedTypeOrBuilder(int i) {
            return (DescriptorProtoOrBuilder) this.nestedType_.get(i);
        }

        public List getNestedTypeOrBuilderList() {
            return this.nestedType_;
        }

        public OneofDescriptorProto getOneofDecl(int i) {
            return (OneofDescriptorProto) this.oneofDecl_.get(i);
        }

        public int getOneofDeclCount() {
            return this.oneofDecl_.size();
        }

        public List getOneofDeclList() {
            return this.oneofDecl_;
        }

        public OneofDescriptorProtoOrBuilder getOneofDeclOrBuilder(int i) {
            return (OneofDescriptorProtoOrBuilder) this.oneofDecl_.get(i);
        }

        public List getOneofDeclOrBuilderList() {
            return this.oneofDecl_;
        }

        public MessageOptions getOptions() {
            return this.options_;
        }

        public MessageOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = 0;
            int i2 = this.memoizedSerializedSize;
            if (i2 != -1) {
                return i2;
            }
            int computeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getNameBytes()) + 0 : 0;
            int i3 = 0;
            while (i3 < this.field_.size()) {
                i2 = CodedOutputStream.computeMessageSize(2, (MessageLite) this.field_.get(i3)) + computeBytesSize;
                i3++;
                computeBytesSize = i2;
            }
            i3 = computeBytesSize;
            for (computeBytesSize = 0; computeBytesSize < this.nestedType_.size(); computeBytesSize++) {
                i3 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.nestedType_.get(computeBytesSize));
            }
            for (computeBytesSize = 0; computeBytesSize < this.enumType_.size(); computeBytesSize++) {
                i3 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.enumType_.get(computeBytesSize));
            }
            for (computeBytesSize = 0; computeBytesSize < this.extensionRange_.size(); computeBytesSize++) {
                i3 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.extensionRange_.get(computeBytesSize));
            }
            for (computeBytesSize = 0; computeBytesSize < this.extension_.size(); computeBytesSize++) {
                i3 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.extension_.get(computeBytesSize));
            }
            if ((this.bitField0_ & 2) == 2) {
                i3 += CodedOutputStream.computeMessageSize(7, this.options_);
            }
            while (i < this.oneofDecl_.size()) {
                i3 += CodedOutputStream.computeMessageSize(8, (MessageLite) this.oneofDecl_.get(i));
                i++;
            }
            i2 = getUnknownFields().getSerializedSize() + i3;
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 2) == 2;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f208x907d0bf0.ensureFieldAccessorsInitialized(DescriptorProto.class, Builder.class);
        }

        public final boolean isInitialized() {
            boolean z = true;
            byte b = this.memoizedIsInitialized;
            if (b != (byte) 1) {
                if (b != (byte) 0) {
                    int i = 0;
                    while (i < getFieldCount()) {
                        if (getField(i).isInitialized()) {
                            i++;
                        } else {
                            this.memoizedIsInitialized = (byte) 0;
                            return false;
                        }
                    }
                    i = 0;
                    while (i < getExtensionCount()) {
                        if (getExtension(i).isInitialized()) {
                            i++;
                        } else {
                            this.memoizedIsInitialized = (byte) 0;
                            return false;
                        }
                    }
                    i = 0;
                    while (i < getNestedTypeCount()) {
                        if (getNestedType(i).isInitialized()) {
                            i++;
                        } else {
                            this.memoizedIsInitialized = (byte) 0;
                            return false;
                        }
                    }
                    i = 0;
                    while (i < getEnumTypeCount()) {
                        if (getEnumType(i).isInitialized()) {
                            i++;
                        } else {
                            this.memoizedIsInitialized = (byte) 0;
                            return false;
                        }
                    }
                    if (!hasOptions() || getOptions().isInitialized()) {
                        this.memoizedIsInitialized = (byte) 1;
                        return true;
                    }
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                z = false;
            }
            return z;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            int i;
            int i2 = 0;
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getNameBytes());
            }
            for (i = 0; i < this.field_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.field_.get(i));
            }
            for (i = 0; i < this.nestedType_.size(); i++) {
                codedOutputStream.writeMessage(3, (MessageLite) this.nestedType_.get(i));
            }
            for (i = 0; i < this.enumType_.size(); i++) {
                codedOutputStream.writeMessage(4, (MessageLite) this.enumType_.get(i));
            }
            for (i = 0; i < this.extensionRange_.size(); i++) {
                codedOutputStream.writeMessage(5, (MessageLite) this.extensionRange_.get(i));
            }
            for (i = 0; i < this.extension_.size(); i++) {
                codedOutputStream.writeMessage(6, (MessageLite) this.extension_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(7, this.options_);
            }
            while (i2 < this.oneofDecl_.size()) {
                codedOutputStream.writeMessage(8, (MessageLite) this.oneofDecl_.get(i2));
                i2++;
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface EnumDescriptorProtoOrBuilder extends MessageOrBuilder {
        String getName();

        ByteString getNameBytes();

        EnumOptions getOptions();

        EnumOptionsOrBuilder getOptionsOrBuilder();

        EnumValueDescriptorProto getValue(int i);

        int getValueCount();

        List getValueList();

        EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int i);

        List getValueOrBuilderList();

        boolean hasName();

        boolean hasOptions();
    }

    public final class EnumDescriptorProto extends GeneratedMessage implements EnumDescriptorProtoOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        public static Parser PARSER = new C19651();
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final EnumDescriptorProto defaultInstance = new EnumDescriptorProto(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object name_;
        private EnumOptions options_;
        private final UnknownFieldSet unknownFields;
        private List value_;

        final class C19651 extends AbstractParser {
            C19651() {
            }

            public EnumDescriptorProto parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new EnumDescriptorProto(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements EnumDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_;
            private SingleFieldBuilder optionsBuilder_;
            private EnumOptions options_;
            private RepeatedFieldBuilder valueBuilder_;
            private List value_;

            private Builder() {
                this.name_ = "";
                this.value_ = Collections.emptyList();
                this.options_ = EnumOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.value_ = Collections.emptyList();
                this.options_ = EnumOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureValueIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.value_ = new ArrayList(this.value_);
                    this.bitField0_ |= 2;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
            }

            private SingleFieldBuilder getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            private RepeatedFieldBuilder getValueFieldBuilder() {
                if (this.valueBuilder_ == null) {
                    this.valueBuilder_ = new RepeatedFieldBuilder(this.value_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.value_ = null;
                }
                return this.valueBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getValueFieldBuilder();
                    getOptionsFieldBuilder();
                }
            }

            public Builder addAllValue(Iterable iterable) {
                if (this.valueBuilder_ == null) {
                    ensureValueIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.value_);
                    onChanged();
                } else {
                    this.valueBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addValue(int i, Builder builder) {
                if (this.valueBuilder_ == null) {
                    ensureValueIsMutable();
                    this.value_.add(i, builder.build());
                    onChanged();
                } else {
                    this.valueBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addValue(int i, EnumValueDescriptorProto enumValueDescriptorProto) {
                if (this.valueBuilder_ != null) {
                    this.valueBuilder_.addMessage(i, enumValueDescriptorProto);
                } else if (enumValueDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureValueIsMutable();
                    this.value_.add(i, enumValueDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addValue(Builder builder) {
                if (this.valueBuilder_ == null) {
                    ensureValueIsMutable();
                    this.value_.add(builder.build());
                    onChanged();
                } else {
                    this.valueBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addValue(EnumValueDescriptorProto enumValueDescriptorProto) {
                if (this.valueBuilder_ != null) {
                    this.valueBuilder_.addMessage(enumValueDescriptorProto);
                } else if (enumValueDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureValueIsMutable();
                    this.value_.add(enumValueDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addValueBuilder() {
                return (Builder) getValueFieldBuilder().addBuilder(EnumValueDescriptorProto.getDefaultInstance());
            }

            public Builder addValueBuilder(int i) {
                return (Builder) getValueFieldBuilder().addBuilder(i, EnumValueDescriptorProto.getDefaultInstance());
            }

            public EnumDescriptorProto build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public EnumDescriptorProto buildPartial() {
                int i = 1;
                EnumDescriptorProto enumDescriptorProto = new EnumDescriptorProto((com.google.protobuf.GeneratedMessage.Builder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                enumDescriptorProto.name_ = this.name_;
                if (this.valueBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.value_ = Collections.unmodifiableList(this.value_);
                        this.bitField0_ &= -3;
                    }
                    enumDescriptorProto.value_ = this.value_;
                } else {
                    enumDescriptorProto.value_ = this.valueBuilder_.build();
                }
                i2 = (i2 & 4) == 4 ? i | 2 : i;
                if (this.optionsBuilder_ == null) {
                    enumDescriptorProto.options_ = this.options_;
                } else {
                    enumDescriptorProto.options_ = (EnumOptions) this.optionsBuilder_.build();
                }
                enumDescriptorProto.bitField0_ = i2;
                onBuilt();
                return enumDescriptorProto;
            }

            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    this.valueBuilder_.clear();
                }
                if (this.optionsBuilder_ == null) {
                    this.options_ = EnumOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = EnumDescriptorProto.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = EnumOptions.getDefaultInstance();
                    onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clearValue() {
                if (this.valueBuilder_ == null) {
                    this.value_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    this.valueBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public EnumDescriptorProto getDefaultInstanceForType() {
                return EnumDescriptorProto.getDefaultInstance();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
            }

            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public EnumOptions getOptions() {
                return this.optionsBuilder_ == null ? this.options_ : (EnumOptions) this.optionsBuilder_.getMessage();
            }

            public Builder getOptionsBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return (Builder) getOptionsFieldBuilder().getBuilder();
            }

            public EnumOptionsOrBuilder getOptionsOrBuilder() {
                return this.optionsBuilder_ != null ? (EnumOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder() : this.options_;
            }

            public EnumValueDescriptorProto getValue(int i) {
                return this.valueBuilder_ == null ? (EnumValueDescriptorProto) this.value_.get(i) : (EnumValueDescriptorProto) this.valueBuilder_.getMessage(i);
            }

            public Builder getValueBuilder(int i) {
                return (Builder) getValueFieldBuilder().getBuilder(i);
            }

            public List getValueBuilderList() {
                return getValueFieldBuilder().getBuilderList();
            }

            public int getValueCount() {
                return this.valueBuilder_ == null ? this.value_.size() : this.valueBuilder_.getCount();
            }

            public List getValueList() {
                return this.valueBuilder_ == null ? Collections.unmodifiableList(this.value_) : this.valueBuilder_.getMessageList();
            }

            public EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int i) {
                return this.valueBuilder_ == null ? (EnumValueDescriptorProtoOrBuilder) this.value_.get(i) : (EnumValueDescriptorProtoOrBuilder) this.valueBuilder_.getMessageOrBuilder(i);
            }

            public List getValueOrBuilderList() {
                return this.valueBuilder_ != null ? this.valueBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.value_);
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 4) == 4;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f209x9945f651.ensureFieldAccessorsInitialized(EnumDescriptorProto.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getValueCount(); i++) {
                    if (!getValue(i).isInitialized()) {
                        return false;
                    }
                }
                return !hasOptions() || getOptions().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                EnumDescriptorProto enumDescriptorProto;
                Throwable th;
                try {
                    enumDescriptorProto = (EnumDescriptorProto) EnumDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (enumDescriptorProto != null) {
                        mergeFrom(enumDescriptorProto);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    enumDescriptorProto = (EnumDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (enumDescriptorProto != null) {
                    mergeFrom(enumDescriptorProto);
                }
                throw th;
            }

            public Builder mergeFrom(EnumDescriptorProto enumDescriptorProto) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (enumDescriptorProto != EnumDescriptorProto.getDefaultInstance()) {
                    if (enumDescriptorProto.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = enumDescriptorProto.name_;
                        onChanged();
                    }
                    if (this.valueBuilder_ == null) {
                        if (!enumDescriptorProto.value_.isEmpty()) {
                            if (this.value_.isEmpty()) {
                                this.value_ = enumDescriptorProto.value_;
                                this.bitField0_ &= -3;
                            } else {
                                ensureValueIsMutable();
                                this.value_.addAll(enumDescriptorProto.value_);
                            }
                            onChanged();
                        }
                    } else if (!enumDescriptorProto.value_.isEmpty()) {
                        if (this.valueBuilder_.isEmpty()) {
                            this.valueBuilder_.dispose();
                            this.valueBuilder_ = null;
                            this.value_ = enumDescriptorProto.value_;
                            this.bitField0_ &= -3;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getValueFieldBuilder();
                            }
                            this.valueBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.valueBuilder_.addAllMessages(enumDescriptorProto.value_);
                        }
                    }
                    if (enumDescriptorProto.hasOptions()) {
                        mergeOptions(enumDescriptorProto.getOptions());
                    }
                    mergeUnknownFields(enumDescriptorProto.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof EnumDescriptorProto) {
                    return mergeFrom((EnumDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(EnumOptions enumOptions) {
                if (this.optionsBuilder_ == null) {
                    if ((this.bitField0_ & 4) != 4 || this.options_ == EnumOptions.getDefaultInstance()) {
                        this.options_ = enumOptions;
                    } else {
                        this.options_ = EnumOptions.newBuilder(this.options_).mergeFrom(enumOptions).buildPartial();
                    }
                    onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(enumOptions);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder removeValue(int i) {
                if (this.valueBuilder_ == null) {
                    ensureValueIsMutable();
                    this.value_.remove(i);
                    onChanged();
                } else {
                    this.valueBuilder_.remove(i);
                }
                return this;
            }

            public Builder setName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder setOptions(Builder builder) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builder.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setOptions(EnumOptions enumOptions) {
                if (this.optionsBuilder_ != null) {
                    this.optionsBuilder_.setMessage(enumOptions);
                } else if (enumOptions == null) {
                    throw new NullPointerException();
                } else {
                    this.options_ = enumOptions;
                    onChanged();
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setValue(int i, Builder builder) {
                if (this.valueBuilder_ == null) {
                    ensureValueIsMutable();
                    this.value_.set(i, builder.build());
                    onChanged();
                } else {
                    this.valueBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setValue(int i, EnumValueDescriptorProto enumValueDescriptorProto) {
                if (this.valueBuilder_ != null) {
                    this.valueBuilder_.setMessage(i, enumValueDescriptorProto);
                } else if (enumValueDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureValueIsMutable();
                    this.value_.set(i, enumValueDescriptorProto);
                    onChanged();
                }
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private EnumDescriptorProto(com.google.protobuf.CodedInputStream r9, com.google.protobuf.ExtensionRegistryLite r10) {
            /*
            r8 = this;
            r2 = 1;
            r1 = 0;
            r0 = -1;
            r6 = 2;
            r8.<init>();
            r8.memoizedIsInitialized = r0;
            r8.memoizedSerializedSize = r0;
            r8.initFields();
            r5 = com.google.protobuf.UnknownFieldSet.newBuilder();
            r3 = r1;
        L_0x0013:
            if (r3 != 0) goto L_0x00b2;
        L_0x0015:
            r0 = r9.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            switch(r0) {
                case 0: goto L_0x00d2;
                case 10: goto L_0x0027;
                case 18: goto L_0x0036;
                case 26: goto L_0x0050;
                default: goto L_0x001c;
            };	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
        L_0x001c:
            r0 = r8.parseUnknownField(r9, r5, r10, r0);	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            if (r0 != 0) goto L_0x00ce;
        L_0x0022:
            r0 = r1;
            r1 = r2;
        L_0x0024:
            r3 = r1;
            r1 = r0;
            goto L_0x0013;
        L_0x0027:
            r0 = r9.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r4 = r8.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r4 = r4 | 1;
            r8.bitField0_ = r4;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r8.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r1;
            r1 = r3;
            goto L_0x0024;
        L_0x0036:
            r0 = r1 & 2;
            if (r0 == r6) goto L_0x00cb;
        L_0x003a:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r8.value_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r1 | 2;
        L_0x0043:
            r1 = r8.value_;	 Catch:{ InvalidProtocolBufferException -> 0x007d, IOException -> 0x00a0, all -> 0x00d6 }
            r4 = com.google.protobuf.DescriptorProtos.EnumValueDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x007d, IOException -> 0x00a0, all -> 0x00d6 }
            r4 = r9.readMessage(r4, r10);	 Catch:{ InvalidProtocolBufferException -> 0x007d, IOException -> 0x00a0, all -> 0x00d6 }
            r1.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x007d, IOException -> 0x00a0, all -> 0x00d6 }
            r1 = r3;
            goto L_0x0024;
        L_0x0050:
            r0 = r8.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r0 & 2;
            if (r0 != r6) goto L_0x00c8;
        L_0x0056:
            r0 = r8.options_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r4 = r0;
        L_0x005d:
            r0 = com.google.protobuf.DescriptorProtos.EnumOptions.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r9.readMessage(r0, r10);	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = (com.google.protobuf.DescriptorProtos.EnumOptions) r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r8.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            if (r4 == 0) goto L_0x0074;
        L_0x0069:
            r0 = r8.options_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r4.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r4.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r8.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
        L_0x0074:
            r0 = r8.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r0 | 2;
            r8.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r1;
            r1 = r3;
            goto L_0x0024;
        L_0x007d:
            r1 = move-exception;
            r7 = r1;
            r1 = r0;
            r0 = r7;
        L_0x0081:
            r0 = r0.setUnfinishedMessage(r8);	 Catch:{ all -> 0x0086 }
            throw r0;	 Catch:{ all -> 0x0086 }
        L_0x0086:
            r0 = move-exception;
            r7 = r0;
            r0 = r1;
            r1 = r7;
        L_0x008a:
            r0 = r0 & 2;
            if (r0 != r6) goto L_0x0096;
        L_0x008e:
            r0 = r8.value_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r8.value_ = r0;
        L_0x0096:
            r0 = r5.build();
            r8.unknownFields = r0;
            r8.makeExtensionsImmutable();
            throw r1;
        L_0x00a0:
            r1 = move-exception;
            r7 = r1;
            r1 = r0;
            r0 = r7;
        L_0x00a4:
            r2 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x0086 }
            r0 = r0.getMessage();	 Catch:{ all -> 0x0086 }
            r2.<init>(r0);	 Catch:{ all -> 0x0086 }
            r0 = r2.setUnfinishedMessage(r8);	 Catch:{ all -> 0x0086 }
            throw r0;	 Catch:{ all -> 0x0086 }
        L_0x00b2:
            r0 = r1 & 2;
            if (r0 != r6) goto L_0x00be;
        L_0x00b6:
            r0 = r8.value_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r8.value_ = r0;
        L_0x00be:
            r0 = r5.build();
            r8.unknownFields = r0;
            r8.makeExtensionsImmutable();
            return;
        L_0x00c8:
            r0 = 0;
            r4 = r0;
            goto L_0x005d;
        L_0x00cb:
            r0 = r1;
            goto L_0x0043;
        L_0x00ce:
            r0 = r1;
            r1 = r3;
            goto L_0x0024;
        L_0x00d2:
            r0 = r1;
            r1 = r2;
            goto L_0x0024;
        L_0x00d6:
            r1 = move-exception;
            goto L_0x008a;
        L_0x00d8:
            r0 = move-exception;
            goto L_0x00a4;
        L_0x00da:
            r0 = move-exception;
            goto L_0x0081;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.DescriptorProtos.EnumDescriptorProto.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
        }

        private EnumDescriptorProto(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private EnumDescriptorProto(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static EnumDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
        }

        private void initFields() {
            this.name_ = "";
            this.value_ = Collections.emptyList();
            this.options_ = EnumOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(EnumDescriptorProto enumDescriptorProto) {
            return newBuilder().mergeFrom(enumDescriptorProto);
        }

        public static EnumDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            return (EnumDescriptorProto) PARSER.parseDelimitedFrom(inputStream);
        }

        public static EnumDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumDescriptorProto) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static EnumDescriptorProto parseFrom(ByteString byteString) {
            return (EnumDescriptorProto) PARSER.parseFrom(byteString);
        }

        public static EnumDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static EnumDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return (EnumDescriptorProto) PARSER.parseFrom(codedInputStream);
        }

        public static EnumDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumDescriptorProto) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static EnumDescriptorProto parseFrom(InputStream inputStream) {
            return (EnumDescriptorProto) PARSER.parseFrom(inputStream);
        }

        public static EnumDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumDescriptorProto) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static EnumDescriptorProto parseFrom(byte[] bArr) {
            return (EnumDescriptorProto) PARSER.parseFrom(bArr);
        }

        public static EnumDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public EnumDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public EnumOptions getOptions() {
            return this.options_;
        }

        public EnumOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int computeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getNameBytes()) + 0 : 0;
            while (i2 < this.value_.size()) {
                i = CodedOutputStream.computeMessageSize(2, (MessageLite) this.value_.get(i2)) + computeBytesSize;
                i2++;
                computeBytesSize = i;
            }
            if ((this.bitField0_ & 2) == 2) {
                computeBytesSize += CodedOutputStream.computeMessageSize(3, this.options_);
            }
            i = getUnknownFields().getSerializedSize() + computeBytesSize;
            this.memoizedSerializedSize = i;
            return i;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public EnumValueDescriptorProto getValue(int i) {
            return (EnumValueDescriptorProto) this.value_.get(i);
        }

        public int getValueCount() {
            return this.value_.size();
        }

        public List getValueList() {
            return this.value_;
        }

        public EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int i) {
            return (EnumValueDescriptorProtoOrBuilder) this.value_.get(i);
        }

        public List getValueOrBuilderList() {
            return this.value_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 2) == 2;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f209x9945f651.ensureFieldAccessorsInitialized(EnumDescriptorProto.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getValueCount()) {
                if (getValue(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!hasOptions() || getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getNameBytes());
            }
            for (int i = 0; i < this.value_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.value_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(3, this.options_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface EnumOptionsOrBuilder extends ExtendableMessageOrBuilder {
        boolean getAllowAlias();

        boolean getDeprecated();

        UninterpretedOption getUninterpretedOption(int i);

        int getUninterpretedOptionCount();

        List getUninterpretedOptionList();

        UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i);

        List getUninterpretedOptionOrBuilderList();

        boolean hasAllowAlias();

        boolean hasDeprecated();
    }

    public final class EnumOptions extends ExtendableMessage implements EnumOptionsOrBuilder {
        public static final int ALLOW_ALIAS_FIELD_NUMBER = 2;
        public static final int DEPRECATED_FIELD_NUMBER = 3;
        public static Parser PARSER = new C19661();
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final EnumOptions defaultInstance = new EnumOptions(true);
        private static final long serialVersionUID = 0;
        private boolean allowAlias_;
        private int bitField0_;
        private boolean deprecated_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List uninterpretedOption_;
        private final UnknownFieldSet unknownFields;

        final class C19661 extends AbstractParser {
            C19661() {
            }

            public EnumOptions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new EnumOptions(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends ExtendableBuilder implements EnumOptionsOrBuilder {
            private boolean allowAlias_;
            private int bitField0_;
            private boolean deprecated_;
            private RepeatedFieldBuilder uninterpretedOptionBuilder_;
            private List uninterpretedOption_;

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 4;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
            }

            private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getUninterpretedOptionFieldBuilder();
                }
            }

            public Builder addAllUninterpretedOption(Iterable iterable) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.uninterpretedOption_);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOption(Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOptionBuilder() {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public Builder addUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(i, UninterpretedOption.getDefaultInstance());
            }

            public EnumOptions build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public EnumOptions buildPartial() {
                int i = 1;
                EnumOptions enumOptions = new EnumOptions((ExtendableBuilder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                enumOptions.allowAlias_ = this.allowAlias_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                enumOptions.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= -5;
                    }
                    enumOptions.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    enumOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                enumOptions.bitField0_ = i;
                onBuilt();
                return enumOptions;
            }

            public Builder clear() {
                super.clear();
                this.allowAlias_ = false;
                this.bitField0_ &= -2;
                this.deprecated_ = false;
                this.bitField0_ &= -3;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clearAllowAlias() {
                this.bitField0_ &= -2;
                this.allowAlias_ = false;
                onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= -3;
                this.deprecated_ = false;
                onChanged();
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public boolean getAllowAlias() {
                return this.allowAlias_;
            }

            public EnumOptions getDefaultInstanceForType() {
                return EnumOptions.getDefaultInstance();
            }

            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOption) this.uninterpretedOption_.get(i) : (UninterpretedOption) this.uninterpretedOptionBuilder_.getMessage(i);
            }

            public Builder getUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().getBuilder(i);
            }

            public List getUninterpretedOptionBuilderList() {
                return getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            public int getUninterpretedOptionCount() {
                return this.uninterpretedOptionBuilder_ == null ? this.uninterpretedOption_.size() : this.uninterpretedOptionBuilder_.getCount();
            }

            public List getUninterpretedOptionList() {
                return this.uninterpretedOptionBuilder_ == null ? Collections.unmodifiableList(this.uninterpretedOption_) : this.uninterpretedOptionBuilder_.getMessageList();
            }

            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i) : (UninterpretedOptionOrBuilder) this.uninterpretedOptionBuilder_.getMessageOrBuilder(i);
            }

            public List getUninterpretedOptionOrBuilderList() {
                return this.uninterpretedOptionBuilder_ != null ? this.uninterpretedOptionBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public boolean hasAllowAlias() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasDeprecated() {
                return (this.bitField0_ & 2) == 2;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumOptions.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                EnumOptions enumOptions;
                Throwable th;
                try {
                    enumOptions = (EnumOptions) EnumOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (enumOptions != null) {
                        mergeFrom(enumOptions);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    enumOptions = (EnumOptions) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (enumOptions != null) {
                    mergeFrom(enumOptions);
                }
                throw th;
            }

            public Builder mergeFrom(EnumOptions enumOptions) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (enumOptions != EnumOptions.getDefaultInstance()) {
                    if (enumOptions.hasAllowAlias()) {
                        setAllowAlias(enumOptions.getAllowAlias());
                    }
                    if (enumOptions.hasDeprecated()) {
                        setDeprecated(enumOptions.getDeprecated());
                    }
                    if (this.uninterpretedOptionBuilder_ == null) {
                        if (!enumOptions.uninterpretedOption_.isEmpty()) {
                            if (this.uninterpretedOption_.isEmpty()) {
                                this.uninterpretedOption_ = enumOptions.uninterpretedOption_;
                                this.bitField0_ &= -5;
                            } else {
                                ensureUninterpretedOptionIsMutable();
                                this.uninterpretedOption_.addAll(enumOptions.uninterpretedOption_);
                            }
                            onChanged();
                        }
                    } else if (!enumOptions.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOptionBuilder_.isEmpty()) {
                            this.uninterpretedOptionBuilder_.dispose();
                            this.uninterpretedOptionBuilder_ = null;
                            this.uninterpretedOption_ = enumOptions.uninterpretedOption_;
                            this.bitField0_ &= -5;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getUninterpretedOptionFieldBuilder();
                            }
                            this.uninterpretedOptionBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.uninterpretedOptionBuilder_.addAllMessages(enumOptions.uninterpretedOption_);
                        }
                    }
                    mergeExtensionFields(enumOptions);
                    mergeUnknownFields(enumOptions.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof EnumOptions) {
                    return mergeFrom((EnumOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeUninterpretedOption(int i) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(i);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(i);
                }
                return this;
            }

            public Builder setAllowAlias(boolean z) {
                this.bitField0_ |= 1;
                this.allowAlias_ = z;
                onChanged();
                return this;
            }

            public Builder setDeprecated(boolean z) {
                this.bitField0_ |= 2;
                this.deprecated_ = z;
                onChanged();
                return this;
            }

            public Builder setUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.setMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        private EnumOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 16:
                            this.bitField0_ |= 1;
                            this.allowAlias_ = codedInputStream.readBool();
                            break;
                        case 24:
                            this.bitField0_ |= 2;
                            this.deprecated_ = codedInputStream.readBool();
                            break;
                        case 7994:
                            if ((i & 4) != 4) {
                                this.uninterpretedOption_ = new ArrayList();
                                i |= 4;
                            }
                            this.uninterpretedOption_.add(codedInputStream.readMessage(UninterpretedOption.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 4) == 4) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 4) == 4) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private EnumOptions(ExtendableBuilder extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private EnumOptions(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static EnumOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
        }

        private void initFields() {
            this.allowAlias_ = false;
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(EnumOptions enumOptions) {
            return newBuilder().mergeFrom(enumOptions);
        }

        public static EnumOptions parseDelimitedFrom(InputStream inputStream) {
            return (EnumOptions) PARSER.parseDelimitedFrom(inputStream);
        }

        public static EnumOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumOptions) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static EnumOptions parseFrom(ByteString byteString) {
            return (EnumOptions) PARSER.parseFrom(byteString);
        }

        public static EnumOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static EnumOptions parseFrom(CodedInputStream codedInputStream) {
            return (EnumOptions) PARSER.parseFrom(codedInputStream);
        }

        public static EnumOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumOptions) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static EnumOptions parseFrom(InputStream inputStream) {
            return (EnumOptions) PARSER.parseFrom(inputStream);
        }

        public static EnumOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumOptions) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static EnumOptions parseFrom(byte[] bArr) {
            return (EnumOptions) PARSER.parseFrom(bArr);
        }

        public static EnumOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public boolean getAllowAlias() {
            return this.allowAlias_;
        }

        public EnumOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            i = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBoolSize(2, this.allowAlias_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeBoolSize(3, this.deprecated_);
            }
            int i2 = 0;
            int i3 = i;
            while (i2 < this.uninterpretedOption_.size()) {
                i = CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i2)) + i3;
                i2++;
                i3 = i;
            }
            i = (extensionsSerializedSize() + i3) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return (UninterpretedOption) this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
            return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
        }

        public List getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasAllowAlias() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 2) == 2;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(EnumOptions.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getUninterpretedOptionCount()) {
                if (getUninterpretedOption(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBool(2, this.allowAlias_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBool(3, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
            }
            newExtensionWriter.writeUntil(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface EnumValueDescriptorProtoOrBuilder extends MessageOrBuilder {
        String getName();

        ByteString getNameBytes();

        int getNumber();

        EnumValueOptions getOptions();

        EnumValueOptionsOrBuilder getOptionsOrBuilder();

        boolean hasName();

        boolean hasNumber();

        boolean hasOptions();
    }

    public final class EnumValueDescriptorProto extends GeneratedMessage implements EnumValueDescriptorProtoOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int NUMBER_FIELD_NUMBER = 2;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        public static Parser PARSER = new C19671();
        private static final EnumValueDescriptorProto defaultInstance = new EnumValueDescriptorProto(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object name_;
        private int number_;
        private EnumValueOptions options_;
        private final UnknownFieldSet unknownFields;

        final class C19671 extends AbstractParser {
            C19671() {
            }

            public EnumValueDescriptorProto parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new EnumValueDescriptorProto(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements EnumValueDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_;
            private int number_;
            private SingleFieldBuilder optionsBuilder_;
            private EnumValueOptions options_;

            private Builder() {
                this.name_ = "";
                this.options_ = EnumValueOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.options_ = EnumValueOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.f210xf18031a8;
            }

            private SingleFieldBuilder getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getOptionsFieldBuilder();
                }
            }

            public EnumValueDescriptorProto build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public EnumValueDescriptorProto buildPartial() {
                int i = 1;
                EnumValueDescriptorProto enumValueDescriptorProto = new EnumValueDescriptorProto((com.google.protobuf.GeneratedMessage.Builder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                enumValueDescriptorProto.name_ = this.name_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                enumValueDescriptorProto.number_ = this.number_;
                i2 = (i2 & 4) == 4 ? i | 4 : i;
                if (this.optionsBuilder_ == null) {
                    enumValueDescriptorProto.options_ = this.options_;
                } else {
                    enumValueDescriptorProto.options_ = (EnumValueOptions) this.optionsBuilder_.build();
                }
                enumValueDescriptorProto.bitField0_ = i2;
                onBuilt();
                return enumValueDescriptorProto;
            }

            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                this.number_ = 0;
                this.bitField0_ &= -3;
                if (this.optionsBuilder_ == null) {
                    this.options_ = EnumValueOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = EnumValueDescriptorProto.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearNumber() {
                this.bitField0_ &= -3;
                this.number_ = 0;
                onChanged();
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = EnumValueOptions.getDefaultInstance();
                    onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public EnumValueDescriptorProto getDefaultInstanceForType() {
                return EnumValueDescriptorProto.getDefaultInstance();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.f210xf18031a8;
            }

            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getNumber() {
                return this.number_;
            }

            public EnumValueOptions getOptions() {
                return this.optionsBuilder_ == null ? this.options_ : (EnumValueOptions) this.optionsBuilder_.getMessage();
            }

            public Builder getOptionsBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return (Builder) getOptionsFieldBuilder().getBuilder();
            }

            public EnumValueOptionsOrBuilder getOptionsOrBuilder() {
                return this.optionsBuilder_ != null ? (EnumValueOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder() : this.options_;
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasNumber() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 4) == 4;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f211xfb173026.ensureFieldAccessorsInitialized(EnumValueDescriptorProto.class, Builder.class);
            }

            public final boolean isInitialized() {
                return !hasOptions() || getOptions().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                EnumValueDescriptorProto enumValueDescriptorProto;
                Throwable th;
                try {
                    enumValueDescriptorProto = (EnumValueDescriptorProto) EnumValueDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (enumValueDescriptorProto != null) {
                        mergeFrom(enumValueDescriptorProto);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    enumValueDescriptorProto = (EnumValueDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (enumValueDescriptorProto != null) {
                    mergeFrom(enumValueDescriptorProto);
                }
                throw th;
            }

            public Builder mergeFrom(EnumValueDescriptorProto enumValueDescriptorProto) {
                if (enumValueDescriptorProto != EnumValueDescriptorProto.getDefaultInstance()) {
                    if (enumValueDescriptorProto.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = enumValueDescriptorProto.name_;
                        onChanged();
                    }
                    if (enumValueDescriptorProto.hasNumber()) {
                        setNumber(enumValueDescriptorProto.getNumber());
                    }
                    if (enumValueDescriptorProto.hasOptions()) {
                        mergeOptions(enumValueDescriptorProto.getOptions());
                    }
                    mergeUnknownFields(enumValueDescriptorProto.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof EnumValueDescriptorProto) {
                    return mergeFrom((EnumValueDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(EnumValueOptions enumValueOptions) {
                if (this.optionsBuilder_ == null) {
                    if ((this.bitField0_ & 4) != 4 || this.options_ == EnumValueOptions.getDefaultInstance()) {
                        this.options_ = enumValueOptions;
                    } else {
                        this.options_ = EnumValueOptions.newBuilder(this.options_).mergeFrom(enumValueOptions).buildPartial();
                    }
                    onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(enumValueOptions);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder setNumber(int i) {
                this.bitField0_ |= 2;
                this.number_ = i;
                onChanged();
                return this;
            }

            public Builder setOptions(Builder builder) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builder.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setOptions(EnumValueOptions enumValueOptions) {
                if (this.optionsBuilder_ != null) {
                    this.optionsBuilder_.setMessage(enumValueOptions);
                } else if (enumValueOptions == null) {
                    throw new NullPointerException();
                } else {
                    this.options_ = enumValueOptions;
                    onChanged();
                }
                this.bitField0_ |= 4;
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        private EnumValueDescriptorProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            Object obj = null;
            while (obj == null) {
                try {
                    Object obj2;
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            readTag = 1;
                            break;
                        case 10:
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.name_ = readBytes;
                            obj2 = obj;
                            break;
                        case 16:
                            this.bitField0_ |= 2;
                            this.number_ = codedInputStream.readInt32();
                            obj2 = obj;
                            break;
                        case 26:
                            Builder toBuilder = (this.bitField0_ & 4) == 4 ? this.options_.toBuilder() : null;
                            this.options_ = (EnumValueOptions) codedInputStream.readMessage(EnumValueOptions.PARSER, extensionRegistryLite);
                            if (toBuilder != null) {
                                toBuilder.mergeFrom(this.options_);
                                this.options_ = toBuilder.buildPartial();
                            }
                            this.bitField0_ |= 4;
                            obj2 = obj;
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                obj2 = 1;
                                break;
                            } else {
                                obj2 = obj;
                                break;
                            }
                    }
                    obj = obj2;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private EnumValueDescriptorProto(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private EnumValueDescriptorProto(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static EnumValueDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.f210xf18031a8;
        }

        private void initFields() {
            this.name_ = "";
            this.number_ = 0;
            this.options_ = EnumValueOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(EnumValueDescriptorProto enumValueDescriptorProto) {
            return newBuilder().mergeFrom(enumValueDescriptorProto);
        }

        public static EnumValueDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            return (EnumValueDescriptorProto) PARSER.parseDelimitedFrom(inputStream);
        }

        public static EnumValueDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueDescriptorProto) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static EnumValueDescriptorProto parseFrom(ByteString byteString) {
            return (EnumValueDescriptorProto) PARSER.parseFrom(byteString);
        }

        public static EnumValueDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static EnumValueDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return (EnumValueDescriptorProto) PARSER.parseFrom(codedInputStream);
        }

        public static EnumValueDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueDescriptorProto) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static EnumValueDescriptorProto parseFrom(InputStream inputStream) {
            return (EnumValueDescriptorProto) PARSER.parseFrom(inputStream);
        }

        public static EnumValueDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueDescriptorProto) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static EnumValueDescriptorProto parseFrom(byte[] bArr) {
            return (EnumValueDescriptorProto) PARSER.parseFrom(bArr);
        }

        public static EnumValueDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public EnumValueDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public int getNumber() {
            return this.number_;
        }

        public EnumValueOptions getOptions() {
            return this.options_;
        }

        public EnumValueOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.bitField0_ & 1) == 1) {
                i = CodedOutputStream.computeBytesSize(1, getNameBytes()) + 0;
            }
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeInt32Size(2, this.number_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeMessageSize(3, this.options_);
            }
            i += getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasNumber() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 4) == 4;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f211xfb173026.ensureFieldAccessorsInitialized(EnumValueDescriptorProto.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!hasOptions() || getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.number_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(3, this.options_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface EnumValueOptionsOrBuilder extends ExtendableMessageOrBuilder {
        boolean getDeprecated();

        UninterpretedOption getUninterpretedOption(int i);

        int getUninterpretedOptionCount();

        List getUninterpretedOptionList();

        UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i);

        List getUninterpretedOptionOrBuilderList();

        boolean hasDeprecated();
    }

    public final class EnumValueOptions extends ExtendableMessage implements EnumValueOptionsOrBuilder {
        public static final int DEPRECATED_FIELD_NUMBER = 1;
        public static Parser PARSER = new C19681();
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final EnumValueOptions defaultInstance = new EnumValueOptions(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean deprecated_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List uninterpretedOption_;
        private final UnknownFieldSet unknownFields;

        final class C19681 extends AbstractParser {
            C19681() {
            }

            public EnumValueOptions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new EnumValueOptions(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends ExtendableBuilder implements EnumValueOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private RepeatedFieldBuilder uninterpretedOptionBuilder_;
            private List uninterpretedOption_;

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 2;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
            }

            private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getUninterpretedOptionFieldBuilder();
                }
            }

            public Builder addAllUninterpretedOption(Iterable iterable) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.uninterpretedOption_);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOption(Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOptionBuilder() {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public Builder addUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(i, UninterpretedOption.getDefaultInstance());
            }

            public EnumValueOptions build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public EnumValueOptions buildPartial() {
                int i = 1;
                EnumValueOptions enumValueOptions = new EnumValueOptions((ExtendableBuilder) this);
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                enumValueOptions.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= -3;
                    }
                    enumValueOptions.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    enumValueOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                enumValueOptions.bitField0_ = i;
                onBuilt();
                return enumValueOptions;
            }

            public Builder clear() {
                super.clear();
                this.deprecated_ = false;
                this.bitField0_ &= -2;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= -2;
                this.deprecated_ = false;
                onChanged();
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public EnumValueOptions getDefaultInstanceForType() {
                return EnumValueOptions.getDefaultInstance();
            }

            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOption) this.uninterpretedOption_.get(i) : (UninterpretedOption) this.uninterpretedOptionBuilder_.getMessage(i);
            }

            public Builder getUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().getBuilder(i);
            }

            public List getUninterpretedOptionBuilderList() {
                return getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            public int getUninterpretedOptionCount() {
                return this.uninterpretedOptionBuilder_ == null ? this.uninterpretedOption_.size() : this.uninterpretedOptionBuilder_.getCount();
            }

            public List getUninterpretedOptionList() {
                return this.uninterpretedOptionBuilder_ == null ? Collections.unmodifiableList(this.uninterpretedOption_) : this.uninterpretedOptionBuilder_.getMessageList();
            }

            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i) : (UninterpretedOptionOrBuilder) this.uninterpretedOptionBuilder_.getMessageOrBuilder(i);
            }

            public List getUninterpretedOptionOrBuilderList() {
                return this.uninterpretedOptionBuilder_ != null ? this.uninterpretedOptionBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public boolean hasDeprecated() {
                return (this.bitField0_ & 1) == 1;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f212xdf65a421.ensureFieldAccessorsInitialized(EnumValueOptions.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                EnumValueOptions enumValueOptions;
                Throwable th;
                try {
                    enumValueOptions = (EnumValueOptions) EnumValueOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (enumValueOptions != null) {
                        mergeFrom(enumValueOptions);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    enumValueOptions = (EnumValueOptions) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (enumValueOptions != null) {
                    mergeFrom(enumValueOptions);
                }
                throw th;
            }

            public Builder mergeFrom(EnumValueOptions enumValueOptions) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (enumValueOptions != EnumValueOptions.getDefaultInstance()) {
                    if (enumValueOptions.hasDeprecated()) {
                        setDeprecated(enumValueOptions.getDeprecated());
                    }
                    if (this.uninterpretedOptionBuilder_ == null) {
                        if (!enumValueOptions.uninterpretedOption_.isEmpty()) {
                            if (this.uninterpretedOption_.isEmpty()) {
                                this.uninterpretedOption_ = enumValueOptions.uninterpretedOption_;
                                this.bitField0_ &= -3;
                            } else {
                                ensureUninterpretedOptionIsMutable();
                                this.uninterpretedOption_.addAll(enumValueOptions.uninterpretedOption_);
                            }
                            onChanged();
                        }
                    } else if (!enumValueOptions.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOptionBuilder_.isEmpty()) {
                            this.uninterpretedOptionBuilder_.dispose();
                            this.uninterpretedOptionBuilder_ = null;
                            this.uninterpretedOption_ = enumValueOptions.uninterpretedOption_;
                            this.bitField0_ &= -3;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getUninterpretedOptionFieldBuilder();
                            }
                            this.uninterpretedOptionBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.uninterpretedOptionBuilder_.addAllMessages(enumValueOptions.uninterpretedOption_);
                        }
                    }
                    mergeExtensionFields(enumValueOptions);
                    mergeUnknownFields(enumValueOptions.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof EnumValueOptions) {
                    return mergeFrom((EnumValueOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeUninterpretedOption(int i) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(i);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(i);
                }
                return this;
            }

            public Builder setDeprecated(boolean z) {
                this.bitField0_ |= 1;
                this.deprecated_ = z;
                onChanged();
                return this;
            }

            public Builder setUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.setMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        private EnumValueOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 8:
                            this.bitField0_ |= 1;
                            this.deprecated_ = codedInputStream.readBool();
                            break;
                        case 7994:
                            if ((i & 2) != 2) {
                                this.uninterpretedOption_ = new ArrayList();
                                i |= 2;
                            }
                            this.uninterpretedOption_.add(codedInputStream.readMessage(UninterpretedOption.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 2) == 2) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private EnumValueOptions(ExtendableBuilder extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private EnumValueOptions(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static EnumValueOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
        }

        private void initFields() {
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(EnumValueOptions enumValueOptions) {
            return newBuilder().mergeFrom(enumValueOptions);
        }

        public static EnumValueOptions parseDelimitedFrom(InputStream inputStream) {
            return (EnumValueOptions) PARSER.parseDelimitedFrom(inputStream);
        }

        public static EnumValueOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueOptions) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static EnumValueOptions parseFrom(ByteString byteString) {
            return (EnumValueOptions) PARSER.parseFrom(byteString);
        }

        public static EnumValueOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static EnumValueOptions parseFrom(CodedInputStream codedInputStream) {
            return (EnumValueOptions) PARSER.parseFrom(codedInputStream);
        }

        public static EnumValueOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueOptions) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static EnumValueOptions parseFrom(InputStream inputStream) {
            return (EnumValueOptions) PARSER.parseFrom(inputStream);
        }

        public static EnumValueOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueOptions) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static EnumValueOptions parseFrom(byte[] bArr) {
            return (EnumValueOptions) PARSER.parseFrom(bArr);
        }

        public static EnumValueOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (EnumValueOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public EnumValueOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int computeBoolSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBoolSize(1, this.deprecated_) + 0 : 0;
            while (i2 < this.uninterpretedOption_.size()) {
                i = CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i2)) + computeBoolSize;
                i2++;
                computeBoolSize = i;
            }
            i = (extensionsSerializedSize() + computeBoolSize) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return (UninterpretedOption) this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
            return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
        }

        public List getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 1) == 1;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f212xdf65a421.ensureFieldAccessorsInitialized(EnumValueOptions.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getUninterpretedOptionCount()) {
                if (getUninterpretedOption(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBool(1, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
            }
            newExtensionWriter.writeUntil(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface FieldDescriptorProtoOrBuilder extends MessageOrBuilder {
        String getDefaultValue();

        ByteString getDefaultValueBytes();

        String getExtendee();

        ByteString getExtendeeBytes();

        Label getLabel();

        String getName();

        ByteString getNameBytes();

        int getNumber();

        int getOneofIndex();

        FieldOptions getOptions();

        FieldOptionsOrBuilder getOptionsOrBuilder();

        Type getType();

        String getTypeName();

        ByteString getTypeNameBytes();

        boolean hasDefaultValue();

        boolean hasExtendee();

        boolean hasLabel();

        boolean hasName();

        boolean hasNumber();

        boolean hasOneofIndex();

        boolean hasOptions();

        boolean hasType();

        boolean hasTypeName();
    }

    public final class FieldDescriptorProto extends GeneratedMessage implements FieldDescriptorProtoOrBuilder {
        public static final int DEFAULT_VALUE_FIELD_NUMBER = 7;
        public static final int EXTENDEE_FIELD_NUMBER = 2;
        public static final int LABEL_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int NUMBER_FIELD_NUMBER = 3;
        public static final int ONEOF_INDEX_FIELD_NUMBER = 9;
        public static final int OPTIONS_FIELD_NUMBER = 8;
        public static Parser PARSER = new C19691();
        public static final int TYPE_FIELD_NUMBER = 5;
        public static final int TYPE_NAME_FIELD_NUMBER = 6;
        private static final FieldDescriptorProto defaultInstance = new FieldDescriptorProto(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private Object defaultValue_;
        private Object extendee_;
        private Label label_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object name_;
        private int number_;
        private int oneofIndex_;
        private FieldOptions options_;
        private Object typeName_;
        private Type type_;
        private final UnknownFieldSet unknownFields;

        final class C19691 extends AbstractParser {
            C19691() {
            }

            public FieldDescriptorProto parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new FieldDescriptorProto(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements FieldDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object defaultValue_;
            private Object extendee_;
            private Label label_;
            private Object name_;
            private int number_;
            private int oneofIndex_;
            private SingleFieldBuilder optionsBuilder_;
            private FieldOptions options_;
            private Object typeName_;
            private Type type_;

            private Builder() {
                this.name_ = "";
                this.label_ = Label.LABEL_OPTIONAL;
                this.type_ = Type.TYPE_DOUBLE;
                this.typeName_ = "";
                this.extendee_ = "";
                this.defaultValue_ = "";
                this.options_ = FieldOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.label_ = Label.LABEL_OPTIONAL;
                this.type_ = Type.TYPE_DOUBLE;
                this.typeName_ = "";
                this.extendee_ = "";
                this.defaultValue_ = "";
                this.options_ = FieldOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
            }

            private SingleFieldBuilder getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getOptionsFieldBuilder();
                }
            }

            public FieldDescriptorProto build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public FieldDescriptorProto buildPartial() {
                int i = 1;
                FieldDescriptorProto fieldDescriptorProto = new FieldDescriptorProto((com.google.protobuf.GeneratedMessage.Builder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                fieldDescriptorProto.name_ = this.name_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                fieldDescriptorProto.number_ = this.number_;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                fieldDescriptorProto.label_ = this.label_;
                if ((i2 & 8) == 8) {
                    i |= 8;
                }
                fieldDescriptorProto.type_ = this.type_;
                if ((i2 & 16) == 16) {
                    i |= 16;
                }
                fieldDescriptorProto.typeName_ = this.typeName_;
                if ((i2 & 32) == 32) {
                    i |= 32;
                }
                fieldDescriptorProto.extendee_ = this.extendee_;
                if ((i2 & 64) == 64) {
                    i |= 64;
                }
                fieldDescriptorProto.defaultValue_ = this.defaultValue_;
                if ((i2 & 128) == 128) {
                    i |= 128;
                }
                fieldDescriptorProto.oneofIndex_ = this.oneofIndex_;
                i2 = (i2 & 256) == 256 ? i | 256 : i;
                if (this.optionsBuilder_ == null) {
                    fieldDescriptorProto.options_ = this.options_;
                } else {
                    fieldDescriptorProto.options_ = (FieldOptions) this.optionsBuilder_.build();
                }
                fieldDescriptorProto.bitField0_ = i2;
                onBuilt();
                return fieldDescriptorProto;
            }

            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                this.number_ = 0;
                this.bitField0_ &= -3;
                this.label_ = Label.LABEL_OPTIONAL;
                this.bitField0_ &= -5;
                this.type_ = Type.TYPE_DOUBLE;
                this.bitField0_ &= -9;
                this.typeName_ = "";
                this.bitField0_ &= -17;
                this.extendee_ = "";
                this.bitField0_ &= -33;
                this.defaultValue_ = "";
                this.bitField0_ &= -65;
                this.oneofIndex_ = 0;
                this.bitField0_ &= -129;
                if (this.optionsBuilder_ == null) {
                    this.options_ = FieldOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -257;
                return this;
            }

            public Builder clearDefaultValue() {
                this.bitField0_ &= -65;
                this.defaultValue_ = FieldDescriptorProto.getDefaultInstance().getDefaultValue();
                onChanged();
                return this;
            }

            public Builder clearExtendee() {
                this.bitField0_ &= -33;
                this.extendee_ = FieldDescriptorProto.getDefaultInstance().getExtendee();
                onChanged();
                return this;
            }

            public Builder clearLabel() {
                this.bitField0_ &= -5;
                this.label_ = Label.LABEL_OPTIONAL;
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = FieldDescriptorProto.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearNumber() {
                this.bitField0_ &= -3;
                this.number_ = 0;
                onChanged();
                return this;
            }

            public Builder clearOneofIndex() {
                this.bitField0_ &= -129;
                this.oneofIndex_ = 0;
                onChanged();
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = FieldOptions.getDefaultInstance();
                    onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -257;
                return this;
            }

            public Builder clearType() {
                this.bitField0_ &= -9;
                this.type_ = Type.TYPE_DOUBLE;
                onChanged();
                return this;
            }

            public Builder clearTypeName() {
                this.bitField0_ &= -17;
                this.typeName_ = FieldDescriptorProto.getDefaultInstance().getTypeName();
                onChanged();
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public FieldDescriptorProto getDefaultInstanceForType() {
                return FieldDescriptorProto.getDefaultInstance();
            }

            public String getDefaultValue() {
                Object obj = this.defaultValue_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.defaultValue_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getDefaultValueBytes() {
                Object obj = this.defaultValue_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.defaultValue_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
            }

            public String getExtendee() {
                Object obj = this.extendee_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.extendee_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getExtendeeBytes() {
                Object obj = this.extendee_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.extendee_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Label getLabel() {
                return this.label_;
            }

            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getNumber() {
                return this.number_;
            }

            public int getOneofIndex() {
                return this.oneofIndex_;
            }

            public FieldOptions getOptions() {
                return this.optionsBuilder_ == null ? this.options_ : (FieldOptions) this.optionsBuilder_.getMessage();
            }

            public Builder getOptionsBuilder() {
                this.bitField0_ |= 256;
                onChanged();
                return (Builder) getOptionsFieldBuilder().getBuilder();
            }

            public FieldOptionsOrBuilder getOptionsOrBuilder() {
                return this.optionsBuilder_ != null ? (FieldOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder() : this.options_;
            }

            public Type getType() {
                return this.type_;
            }

            public String getTypeName() {
                Object obj = this.typeName_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.typeName_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getTypeNameBytes() {
                Object obj = this.typeName_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.typeName_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasDefaultValue() {
                return (this.bitField0_ & 64) == 64;
            }

            public boolean hasExtendee() {
                return (this.bitField0_ & 32) == 32;
            }

            public boolean hasLabel() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasNumber() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasOneofIndex() {
                return (this.bitField0_ & 128) == 128;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 256) == 256;
            }

            public boolean hasType() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasTypeName() {
                return (this.bitField0_ & 16) == 16;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f213x4734b330.ensureFieldAccessorsInitialized(FieldDescriptorProto.class, Builder.class);
            }

            public final boolean isInitialized() {
                return !hasOptions() || getOptions().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                FieldDescriptorProto fieldDescriptorProto;
                Throwable th;
                try {
                    fieldDescriptorProto = (FieldDescriptorProto) FieldDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (fieldDescriptorProto != null) {
                        mergeFrom(fieldDescriptorProto);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    fieldDescriptorProto = (FieldDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (fieldDescriptorProto != null) {
                    mergeFrom(fieldDescriptorProto);
                }
                throw th;
            }

            public Builder mergeFrom(FieldDescriptorProto fieldDescriptorProto) {
                if (fieldDescriptorProto != FieldDescriptorProto.getDefaultInstance()) {
                    if (fieldDescriptorProto.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = fieldDescriptorProto.name_;
                        onChanged();
                    }
                    if (fieldDescriptorProto.hasNumber()) {
                        setNumber(fieldDescriptorProto.getNumber());
                    }
                    if (fieldDescriptorProto.hasLabel()) {
                        setLabel(fieldDescriptorProto.getLabel());
                    }
                    if (fieldDescriptorProto.hasType()) {
                        setType(fieldDescriptorProto.getType());
                    }
                    if (fieldDescriptorProto.hasTypeName()) {
                        this.bitField0_ |= 16;
                        this.typeName_ = fieldDescriptorProto.typeName_;
                        onChanged();
                    }
                    if (fieldDescriptorProto.hasExtendee()) {
                        this.bitField0_ |= 32;
                        this.extendee_ = fieldDescriptorProto.extendee_;
                        onChanged();
                    }
                    if (fieldDescriptorProto.hasDefaultValue()) {
                        this.bitField0_ |= 64;
                        this.defaultValue_ = fieldDescriptorProto.defaultValue_;
                        onChanged();
                    }
                    if (fieldDescriptorProto.hasOneofIndex()) {
                        setOneofIndex(fieldDescriptorProto.getOneofIndex());
                    }
                    if (fieldDescriptorProto.hasOptions()) {
                        mergeOptions(fieldDescriptorProto.getOptions());
                    }
                    mergeUnknownFields(fieldDescriptorProto.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof FieldDescriptorProto) {
                    return mergeFrom((FieldDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(FieldOptions fieldOptions) {
                if (this.optionsBuilder_ == null) {
                    if ((this.bitField0_ & 256) != 256 || this.options_ == FieldOptions.getDefaultInstance()) {
                        this.options_ = fieldOptions;
                    } else {
                        this.options_ = FieldOptions.newBuilder(this.options_).mergeFrom(fieldOptions).buildPartial();
                    }
                    onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(fieldOptions);
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder setDefaultValue(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.defaultValue_ = str;
                onChanged();
                return this;
            }

            public Builder setDefaultValueBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.defaultValue_ = byteString;
                onChanged();
                return this;
            }

            public Builder setExtendee(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.extendee_ = str;
                onChanged();
                return this;
            }

            public Builder setExtendeeBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.extendee_ = byteString;
                onChanged();
                return this;
            }

            public Builder setLabel(Label label) {
                if (label == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.label_ = label;
                onChanged();
                return this;
            }

            public Builder setName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder setNumber(int i) {
                this.bitField0_ |= 2;
                this.number_ = i;
                onChanged();
                return this;
            }

            public Builder setOneofIndex(int i) {
                this.bitField0_ |= 128;
                this.oneofIndex_ = i;
                onChanged();
                return this;
            }

            public Builder setOptions(Builder builder) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builder.build());
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder setOptions(FieldOptions fieldOptions) {
                if (this.optionsBuilder_ != null) {
                    this.optionsBuilder_.setMessage(fieldOptions);
                } else if (fieldOptions == null) {
                    throw new NullPointerException();
                } else {
                    this.options_ = fieldOptions;
                    onChanged();
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder setType(Type type) {
                if (type == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.type_ = type;
                onChanged();
                return this;
            }

            public Builder setTypeName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.typeName_ = str;
                onChanged();
                return this;
            }

            public Builder setTypeNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.typeName_ = byteString;
                onChanged();
                return this;
            }
        }

        public enum Label implements ProtocolMessageEnum {
            LABEL_OPTIONAL(0, 1),
            LABEL_REQUIRED(1, 2),
            LABEL_REPEATED(2, 3);
            
            public static final int LABEL_OPTIONAL_VALUE = 1;
            public static final int LABEL_REPEATED_VALUE = 3;
            public static final int LABEL_REQUIRED_VALUE = 2;
            private static final Label[] VALUES = null;
            private static EnumLiteMap internalValueMap;
            private final int index;
            private final int value;

            final class C19701 implements EnumLiteMap {
                C19701() {
                }

                public Label findValueByNumber(int i) {
                    return Label.valueOf(i);
                }
            }

            static {
                internalValueMap = new C19701();
                VALUES = values();
            }

            private Label(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) FieldDescriptorProto.getDescriptor().getEnumTypes().get(1);
            }

            public static EnumLiteMap internalGetValueMap() {
                return internalValueMap;
            }

            public static Label valueOf(int i) {
                switch (i) {
                    case 1:
                        return LABEL_OPTIONAL;
                    case 2:
                        return LABEL_REQUIRED;
                    case 3:
                        return LABEL_REPEATED;
                    default:
                        return null;
                }
            }

            public static Label valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public final int getNumber() {
                return this.value;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }
        }

        public enum Type implements ProtocolMessageEnum {
            TYPE_DOUBLE(0, 1),
            TYPE_FLOAT(1, 2),
            TYPE_INT64(2, 3),
            TYPE_UINT64(3, 4),
            TYPE_INT32(4, 5),
            TYPE_FIXED64(5, 6),
            TYPE_FIXED32(6, 7),
            TYPE_BOOL(7, 8),
            TYPE_STRING(8, 9),
            TYPE_GROUP(9, 10),
            TYPE_MESSAGE(10, 11),
            TYPE_BYTES(11, 12),
            TYPE_UINT32(12, 13),
            TYPE_ENUM(13, 14),
            TYPE_SFIXED32(14, 15),
            TYPE_SFIXED64(15, 16),
            TYPE_SINT32(16, 17),
            TYPE_SINT64(17, 18);
            
            public static final int TYPE_BOOL_VALUE = 8;
            public static final int TYPE_BYTES_VALUE = 12;
            public static final int TYPE_DOUBLE_VALUE = 1;
            public static final int TYPE_ENUM_VALUE = 14;
            public static final int TYPE_FIXED32_VALUE = 7;
            public static final int TYPE_FIXED64_VALUE = 6;
            public static final int TYPE_FLOAT_VALUE = 2;
            public static final int TYPE_GROUP_VALUE = 10;
            public static final int TYPE_INT32_VALUE = 5;
            public static final int TYPE_INT64_VALUE = 3;
            public static final int TYPE_MESSAGE_VALUE = 11;
            public static final int TYPE_SFIXED32_VALUE = 15;
            public static final int TYPE_SFIXED64_VALUE = 16;
            public static final int TYPE_SINT32_VALUE = 17;
            public static final int TYPE_SINT64_VALUE = 18;
            public static final int TYPE_STRING_VALUE = 9;
            public static final int TYPE_UINT32_VALUE = 13;
            public static final int TYPE_UINT64_VALUE = 4;
            private static final Type[] VALUES = null;
            private static EnumLiteMap internalValueMap;
            private final int index;
            private final int value;

            final class C19711 implements EnumLiteMap {
                C19711() {
                }

                public Type findValueByNumber(int i) {
                    return Type.valueOf(i);
                }
            }

            static {
                internalValueMap = new C19711();
                VALUES = values();
            }

            private Type(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) FieldDescriptorProto.getDescriptor().getEnumTypes().get(0);
            }

            public static EnumLiteMap internalGetValueMap() {
                return internalValueMap;
            }

            public static Type valueOf(int i) {
                switch (i) {
                    case 1:
                        return TYPE_DOUBLE;
                    case 2:
                        return TYPE_FLOAT;
                    case 3:
                        return TYPE_INT64;
                    case 4:
                        return TYPE_UINT64;
                    case 5:
                        return TYPE_INT32;
                    case 6:
                        return TYPE_FIXED64;
                    case 7:
                        return TYPE_FIXED32;
                    case 8:
                        return TYPE_BOOL;
                    case 9:
                        return TYPE_STRING;
                    case 10:
                        return TYPE_GROUP;
                    case 11:
                        return TYPE_MESSAGE;
                    case 12:
                        return TYPE_BYTES;
                    case 13:
                        return TYPE_UINT32;
                    case 14:
                        return TYPE_ENUM;
                    case 15:
                        return TYPE_SFIXED32;
                    case 16:
                        return TYPE_SFIXED64;
                    case 17:
                        return TYPE_SINT32;
                    case 18:
                        return TYPE_SINT64;
                    default:
                        return null;
                }
            }

            public static Type valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public final int getNumber() {
                return this.value;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }
        }

        static {
            defaultInstance.initFields();
        }

        private FieldDescriptorProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            Object obj = null;
            while (obj == null) {
                try {
                    Object obj2;
                    int readTag = codedInputStream.readTag();
                    ByteString readBytes;
                    switch (readTag) {
                        case 0:
                            readTag = 1;
                            continue;
                        case 10:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.name_ = readBytes;
                            obj2 = obj;
                            continue;
                        case 18:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 32;
                            this.extendee_ = readBytes;
                            obj2 = obj;
                            continue;
                        case 24:
                            this.bitField0_ |= 2;
                            this.number_ = codedInputStream.readInt32();
                            obj2 = obj;
                            continue;
                        case 32:
                            readTag = codedInputStream.readEnum();
                            Label valueOf = Label.valueOf(readTag);
                            if (valueOf != null) {
                                this.bitField0_ |= 4;
                                this.label_ = valueOf;
                                obj2 = obj;
                                break;
                            }
                            newBuilder.mergeVarintField(4, readTag);
                            obj2 = obj;
                            continue;
                        case 40:
                            readTag = codedInputStream.readEnum();
                            Type valueOf2 = Type.valueOf(readTag);
                            if (valueOf2 != null) {
                                this.bitField0_ |= 8;
                                this.type_ = valueOf2;
                                obj2 = obj;
                                break;
                            }
                            newBuilder.mergeVarintField(5, readTag);
                            obj2 = obj;
                            continue;
                        case 50:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 16;
                            this.typeName_ = readBytes;
                            obj2 = obj;
                            continue;
                        case 58:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 64;
                            this.defaultValue_ = readBytes;
                            obj2 = obj;
                            continue;
                        case 66:
                            Builder toBuilder = (this.bitField0_ & 256) == 256 ? this.options_.toBuilder() : null;
                            this.options_ = (FieldOptions) codedInputStream.readMessage(FieldOptions.PARSER, extensionRegistryLite);
                            if (toBuilder != null) {
                                toBuilder.mergeFrom(this.options_);
                                this.options_ = toBuilder.buildPartial();
                            }
                            this.bitField0_ |= 256;
                            obj2 = obj;
                            continue;
                        case 72:
                            this.bitField0_ |= 128;
                            this.oneofIndex_ = codedInputStream.readInt32();
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                obj2 = 1;
                                continue;
                            }
                            break;
                    }
                    obj2 = obj;
                    obj = obj2;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private FieldDescriptorProto(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private FieldDescriptorProto(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FieldDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
        }

        private void initFields() {
            this.name_ = "";
            this.number_ = 0;
            this.label_ = Label.LABEL_OPTIONAL;
            this.type_ = Type.TYPE_DOUBLE;
            this.typeName_ = "";
            this.extendee_ = "";
            this.defaultValue_ = "";
            this.oneofIndex_ = 0;
            this.options_ = FieldOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(FieldDescriptorProto fieldDescriptorProto) {
            return newBuilder().mergeFrom(fieldDescriptorProto);
        }

        public static FieldDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            return (FieldDescriptorProto) PARSER.parseDelimitedFrom(inputStream);
        }

        public static FieldDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldDescriptorProto) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static FieldDescriptorProto parseFrom(ByteString byteString) {
            return (FieldDescriptorProto) PARSER.parseFrom(byteString);
        }

        public static FieldDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FieldDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return (FieldDescriptorProto) PARSER.parseFrom(codedInputStream);
        }

        public static FieldDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldDescriptorProto) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static FieldDescriptorProto parseFrom(InputStream inputStream) {
            return (FieldDescriptorProto) PARSER.parseFrom(inputStream);
        }

        public static FieldDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldDescriptorProto) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static FieldDescriptorProto parseFrom(byte[] bArr) {
            return (FieldDescriptorProto) PARSER.parseFrom(bArr);
        }

        public static FieldDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public FieldDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getDefaultValue() {
            Object obj = this.defaultValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.defaultValue_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getDefaultValueBytes() {
            Object obj = this.defaultValue_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.defaultValue_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public String getExtendee() {
            Object obj = this.extendee_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.extendee_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getExtendeeBytes() {
            Object obj = this.extendee_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.extendee_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Label getLabel() {
            return this.label_;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public int getNumber() {
            return this.number_;
        }

        public int getOneofIndex() {
            return this.oneofIndex_;
        }

        public FieldOptions getOptions() {
            return this.options_;
        }

        public FieldOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.bitField0_ & 1) == 1) {
                i = CodedOutputStream.computeBytesSize(1, getNameBytes()) + 0;
            }
            if ((this.bitField0_ & 32) == 32) {
                i += CodedOutputStream.computeBytesSize(2, getExtendeeBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeInt32Size(3, this.number_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeEnumSize(4, this.label_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                i += CodedOutputStream.computeEnumSize(5, this.type_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                i += CodedOutputStream.computeBytesSize(6, getTypeNameBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                i += CodedOutputStream.computeBytesSize(7, getDefaultValueBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                i += CodedOutputStream.computeMessageSize(8, this.options_);
            }
            if ((this.bitField0_ & 128) == 128) {
                i += CodedOutputStream.computeInt32Size(9, this.oneofIndex_);
            }
            i += getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public Type getType() {
            return this.type_;
        }

        public String getTypeName() {
            Object obj = this.typeName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.typeName_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getTypeNameBytes() {
            Object obj = this.typeName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.typeName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasDefaultValue() {
            return (this.bitField0_ & 64) == 64;
        }

        public boolean hasExtendee() {
            return (this.bitField0_ & 32) == 32;
        }

        public boolean hasLabel() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasNumber() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasOneofIndex() {
            return (this.bitField0_ & 128) == 128;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 256) == 256;
        }

        public boolean hasType() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasTypeName() {
            return (this.bitField0_ & 16) == 16;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f213x4734b330.ensureFieldAccessorsInitialized(FieldDescriptorProto.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!hasOptions() || getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getNameBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBytes(2, getExtendeeBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(3, this.number_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeEnum(4, this.label_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeEnum(5, this.type_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(6, getTypeNameBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeBytes(7, getDefaultValueBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeMessage(8, this.options_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeInt32(9, this.oneofIndex_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface FieldOptionsOrBuilder extends ExtendableMessageOrBuilder {
        CType getCtype();

        boolean getDeprecated();

        String getExperimentalMapKey();

        ByteString getExperimentalMapKeyBytes();

        boolean getLazy();

        boolean getPacked();

        UninterpretedOption getUninterpretedOption(int i);

        int getUninterpretedOptionCount();

        List getUninterpretedOptionList();

        UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i);

        List getUninterpretedOptionOrBuilderList();

        boolean getWeak();

        boolean hasCtype();

        boolean hasDeprecated();

        boolean hasExperimentalMapKey();

        boolean hasLazy();

        boolean hasPacked();

        boolean hasWeak();
    }

    public final class FieldOptions extends ExtendableMessage implements FieldOptionsOrBuilder {
        public static final int CTYPE_FIELD_NUMBER = 1;
        public static final int DEPRECATED_FIELD_NUMBER = 3;
        public static final int EXPERIMENTAL_MAP_KEY_FIELD_NUMBER = 9;
        public static final int LAZY_FIELD_NUMBER = 5;
        public static final int PACKED_FIELD_NUMBER = 2;
        public static Parser PARSER = new C19721();
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        public static final int WEAK_FIELD_NUMBER = 10;
        private static final FieldOptions defaultInstance = new FieldOptions(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private CType ctype_;
        private boolean deprecated_;
        private Object experimentalMapKey_;
        private boolean lazy_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private boolean packed_;
        private List uninterpretedOption_;
        private final UnknownFieldSet unknownFields;
        private boolean weak_;

        final class C19721 extends AbstractParser {
            C19721() {
            }

            public FieldOptions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new FieldOptions(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends ExtendableBuilder implements FieldOptionsOrBuilder {
            private int bitField0_;
            private CType ctype_;
            private boolean deprecated_;
            private Object experimentalMapKey_;
            private boolean lazy_;
            private boolean packed_;
            private RepeatedFieldBuilder uninterpretedOptionBuilder_;
            private List uninterpretedOption_;
            private boolean weak_;

            private Builder() {
                this.ctype_ = CType.STRING;
                this.experimentalMapKey_ = "";
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.ctype_ = CType.STRING;
                this.experimentalMapKey_ = "";
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 64) != 64) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 64;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
            }

            private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getUninterpretedOptionFieldBuilder();
                }
            }

            public Builder addAllUninterpretedOption(Iterable iterable) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.uninterpretedOption_);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOption(Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOptionBuilder() {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public Builder addUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(i, UninterpretedOption.getDefaultInstance());
            }

            public FieldOptions build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public FieldOptions buildPartial() {
                int i = 1;
                FieldOptions fieldOptions = new FieldOptions((ExtendableBuilder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                fieldOptions.ctype_ = this.ctype_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                fieldOptions.packed_ = this.packed_;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                fieldOptions.lazy_ = this.lazy_;
                if ((i2 & 8) == 8) {
                    i |= 8;
                }
                fieldOptions.deprecated_ = this.deprecated_;
                if ((i2 & 16) == 16) {
                    i |= 16;
                }
                fieldOptions.experimentalMapKey_ = this.experimentalMapKey_;
                if ((i2 & 32) == 32) {
                    i |= 32;
                }
                fieldOptions.weak_ = this.weak_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 64) == 64) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= -65;
                    }
                    fieldOptions.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    fieldOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                fieldOptions.bitField0_ = i;
                onBuilt();
                return fieldOptions;
            }

            public Builder clear() {
                super.clear();
                this.ctype_ = CType.STRING;
                this.bitField0_ &= -2;
                this.packed_ = false;
                this.bitField0_ &= -3;
                this.lazy_ = false;
                this.bitField0_ &= -5;
                this.deprecated_ = false;
                this.bitField0_ &= -9;
                this.experimentalMapKey_ = "";
                this.bitField0_ &= -17;
                this.weak_ = false;
                this.bitField0_ &= -33;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clearCtype() {
                this.bitField0_ &= -2;
                this.ctype_ = CType.STRING;
                onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= -9;
                this.deprecated_ = false;
                onChanged();
                return this;
            }

            public Builder clearExperimentalMapKey() {
                this.bitField0_ &= -17;
                this.experimentalMapKey_ = FieldOptions.getDefaultInstance().getExperimentalMapKey();
                onChanged();
                return this;
            }

            public Builder clearLazy() {
                this.bitField0_ &= -5;
                this.lazy_ = false;
                onChanged();
                return this;
            }

            public Builder clearPacked() {
                this.bitField0_ &= -3;
                this.packed_ = false;
                onChanged();
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clearWeak() {
                this.bitField0_ &= -33;
                this.weak_ = false;
                onChanged();
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public CType getCtype() {
                return this.ctype_;
            }

            public FieldOptions getDefaultInstanceForType() {
                return FieldOptions.getDefaultInstance();
            }

            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
            }

            public String getExperimentalMapKey() {
                Object obj = this.experimentalMapKey_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.experimentalMapKey_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getExperimentalMapKeyBytes() {
                Object obj = this.experimentalMapKey_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.experimentalMapKey_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean getLazy() {
                return this.lazy_;
            }

            public boolean getPacked() {
                return this.packed_;
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOption) this.uninterpretedOption_.get(i) : (UninterpretedOption) this.uninterpretedOptionBuilder_.getMessage(i);
            }

            public Builder getUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().getBuilder(i);
            }

            public List getUninterpretedOptionBuilderList() {
                return getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            public int getUninterpretedOptionCount() {
                return this.uninterpretedOptionBuilder_ == null ? this.uninterpretedOption_.size() : this.uninterpretedOptionBuilder_.getCount();
            }

            public List getUninterpretedOptionList() {
                return this.uninterpretedOptionBuilder_ == null ? Collections.unmodifiableList(this.uninterpretedOption_) : this.uninterpretedOptionBuilder_.getMessageList();
            }

            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i) : (UninterpretedOptionOrBuilder) this.uninterpretedOptionBuilder_.getMessageOrBuilder(i);
            }

            public List getUninterpretedOptionOrBuilderList() {
                return this.uninterpretedOptionBuilder_ != null ? this.uninterpretedOptionBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public boolean getWeak() {
                return this.weak_;
            }

            public boolean hasCtype() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasDeprecated() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasExperimentalMapKey() {
                return (this.bitField0_ & 16) == 16;
            }

            public boolean hasLazy() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasPacked() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasWeak() {
                return (this.bitField0_ & 32) == 32;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldOptions.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                FieldOptions fieldOptions;
                Throwable th;
                try {
                    fieldOptions = (FieldOptions) FieldOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (fieldOptions != null) {
                        mergeFrom(fieldOptions);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    fieldOptions = (FieldOptions) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (fieldOptions != null) {
                    mergeFrom(fieldOptions);
                }
                throw th;
            }

            public Builder mergeFrom(FieldOptions fieldOptions) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (fieldOptions != FieldOptions.getDefaultInstance()) {
                    if (fieldOptions.hasCtype()) {
                        setCtype(fieldOptions.getCtype());
                    }
                    if (fieldOptions.hasPacked()) {
                        setPacked(fieldOptions.getPacked());
                    }
                    if (fieldOptions.hasLazy()) {
                        setLazy(fieldOptions.getLazy());
                    }
                    if (fieldOptions.hasDeprecated()) {
                        setDeprecated(fieldOptions.getDeprecated());
                    }
                    if (fieldOptions.hasExperimentalMapKey()) {
                        this.bitField0_ |= 16;
                        this.experimentalMapKey_ = fieldOptions.experimentalMapKey_;
                        onChanged();
                    }
                    if (fieldOptions.hasWeak()) {
                        setWeak(fieldOptions.getWeak());
                    }
                    if (this.uninterpretedOptionBuilder_ == null) {
                        if (!fieldOptions.uninterpretedOption_.isEmpty()) {
                            if (this.uninterpretedOption_.isEmpty()) {
                                this.uninterpretedOption_ = fieldOptions.uninterpretedOption_;
                                this.bitField0_ &= -65;
                            } else {
                                ensureUninterpretedOptionIsMutable();
                                this.uninterpretedOption_.addAll(fieldOptions.uninterpretedOption_);
                            }
                            onChanged();
                        }
                    } else if (!fieldOptions.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOptionBuilder_.isEmpty()) {
                            this.uninterpretedOptionBuilder_.dispose();
                            this.uninterpretedOptionBuilder_ = null;
                            this.uninterpretedOption_ = fieldOptions.uninterpretedOption_;
                            this.bitField0_ &= -65;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getUninterpretedOptionFieldBuilder();
                            }
                            this.uninterpretedOptionBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.uninterpretedOptionBuilder_.addAllMessages(fieldOptions.uninterpretedOption_);
                        }
                    }
                    mergeExtensionFields(fieldOptions);
                    mergeUnknownFields(fieldOptions.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof FieldOptions) {
                    return mergeFrom((FieldOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeUninterpretedOption(int i) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(i);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(i);
                }
                return this;
            }

            public Builder setCtype(CType cType) {
                if (cType == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.ctype_ = cType;
                onChanged();
                return this;
            }

            public Builder setDeprecated(boolean z) {
                this.bitField0_ |= 8;
                this.deprecated_ = z;
                onChanged();
                return this;
            }

            public Builder setExperimentalMapKey(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.experimentalMapKey_ = str;
                onChanged();
                return this;
            }

            public Builder setExperimentalMapKeyBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.experimentalMapKey_ = byteString;
                onChanged();
                return this;
            }

            public Builder setLazy(boolean z) {
                this.bitField0_ |= 4;
                this.lazy_ = z;
                onChanged();
                return this;
            }

            public Builder setPacked(boolean z) {
                this.bitField0_ |= 2;
                this.packed_ = z;
                onChanged();
                return this;
            }

            public Builder setUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.setMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder setWeak(boolean z) {
                this.bitField0_ |= 32;
                this.weak_ = z;
                onChanged();
                return this;
            }
        }

        public enum CType implements ProtocolMessageEnum {
            STRING(0, 0),
            CORD(1, 1),
            STRING_PIECE(2, 2);
            
            public static final int CORD_VALUE = 1;
            public static final int STRING_PIECE_VALUE = 2;
            public static final int STRING_VALUE = 0;
            private static final CType[] VALUES = null;
            private static EnumLiteMap internalValueMap;
            private final int index;
            private final int value;

            final class C19731 implements EnumLiteMap {
                C19731() {
                }

                public CType findValueByNumber(int i) {
                    return CType.valueOf(i);
                }
            }

            static {
                internalValueMap = new C19731();
                VALUES = values();
            }

            private CType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) FieldOptions.getDescriptor().getEnumTypes().get(0);
            }

            public static EnumLiteMap internalGetValueMap() {
                return internalValueMap;
            }

            public static CType valueOf(int i) {
                switch (i) {
                    case 0:
                        return STRING;
                    case 1:
                        return CORD;
                    case 2:
                        return STRING_PIECE;
                    default:
                        return null;
                }
            }

            public static CType valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public final int getNumber() {
                return this.value;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }
        }

        static {
            defaultInstance.initFields();
        }

        private FieldOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 8:
                            readTag = codedInputStream.readEnum();
                            CType valueOf = CType.valueOf(readTag);
                            if (valueOf != null) {
                                this.bitField0_ |= 1;
                                this.ctype_ = valueOf;
                                break;
                            }
                            newBuilder.mergeVarintField(1, readTag);
                            break;
                        case 16:
                            this.bitField0_ |= 2;
                            this.packed_ = codedInputStream.readBool();
                            break;
                        case 24:
                            this.bitField0_ |= 8;
                            this.deprecated_ = codedInputStream.readBool();
                            break;
                        case 40:
                            this.bitField0_ |= 4;
                            this.lazy_ = codedInputStream.readBool();
                            break;
                        case 74:
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 16;
                            this.experimentalMapKey_ = readBytes;
                            break;
                        case 80:
                            this.bitField0_ |= 32;
                            this.weak_ = codedInputStream.readBool();
                            break;
                        case 7994:
                            if ((i & 64) != 64) {
                                this.uninterpretedOption_ = new ArrayList();
                                i |= 64;
                            }
                            this.uninterpretedOption_.add(codedInputStream.readMessage(UninterpretedOption.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 64) == 64) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 64) == 64) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private FieldOptions(ExtendableBuilder extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private FieldOptions(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FieldOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
        }

        private void initFields() {
            this.ctype_ = CType.STRING;
            this.packed_ = false;
            this.lazy_ = false;
            this.deprecated_ = false;
            this.experimentalMapKey_ = "";
            this.weak_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(FieldOptions fieldOptions) {
            return newBuilder().mergeFrom(fieldOptions);
        }

        public static FieldOptions parseDelimitedFrom(InputStream inputStream) {
            return (FieldOptions) PARSER.parseDelimitedFrom(inputStream);
        }

        public static FieldOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldOptions) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static FieldOptions parseFrom(ByteString byteString) {
            return (FieldOptions) PARSER.parseFrom(byteString);
        }

        public static FieldOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FieldOptions parseFrom(CodedInputStream codedInputStream) {
            return (FieldOptions) PARSER.parseFrom(codedInputStream);
        }

        public static FieldOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldOptions) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static FieldOptions parseFrom(InputStream inputStream) {
            return (FieldOptions) PARSER.parseFrom(inputStream);
        }

        public static FieldOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldOptions) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static FieldOptions parseFrom(byte[] bArr) {
            return (FieldOptions) PARSER.parseFrom(bArr);
        }

        public static FieldOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (FieldOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public CType getCtype() {
            return this.ctype_;
        }

        public FieldOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public String getExperimentalMapKey() {
            Object obj = this.experimentalMapKey_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.experimentalMapKey_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getExperimentalMapKeyBytes() {
            Object obj = this.experimentalMapKey_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.experimentalMapKey_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean getLazy() {
            return this.lazy_;
        }

        public boolean getPacked() {
            return this.packed_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            i = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.ctype_.getNumber()) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeBoolSize(2, this.packed_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i += CodedOutputStream.computeBoolSize(3, this.deprecated_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeBoolSize(5, this.lazy_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i += CodedOutputStream.computeBytesSize(9, getExperimentalMapKeyBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                i += CodedOutputStream.computeBoolSize(10, this.weak_);
            }
            int i2 = 0;
            int i3 = i;
            while (i2 < this.uninterpretedOption_.size()) {
                i = CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i2)) + i3;
                i2++;
                i3 = i;
            }
            i = (extensionsSerializedSize() + i3) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return (UninterpretedOption) this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
            return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
        }

        public List getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean getWeak() {
            return this.weak_;
        }

        public boolean hasCtype() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasExperimentalMapKey() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasLazy() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasPacked() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasWeak() {
            return (this.bitField0_ & 32) == 32;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldOptions.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getUninterpretedOptionCount()) {
                if (getUninterpretedOption(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.ctype_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBool(2, this.packed_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBool(3, this.deprecated_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBool(5, this.lazy_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(9, getExperimentalMapKeyBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBool(10, this.weak_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
            }
            newExtensionWriter.writeUntil(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface FileDescriptorProtoOrBuilder extends MessageOrBuilder {
        String getDependency(int i);

        ByteString getDependencyBytes(int i);

        int getDependencyCount();

        ProtocolStringList getDependencyList();

        EnumDescriptorProto getEnumType(int i);

        int getEnumTypeCount();

        List getEnumTypeList();

        EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int i);

        List getEnumTypeOrBuilderList();

        FieldDescriptorProto getExtension(int i);

        int getExtensionCount();

        List getExtensionList();

        FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int i);

        List getExtensionOrBuilderList();

        DescriptorProto getMessageType(int i);

        int getMessageTypeCount();

        List getMessageTypeList();

        DescriptorProtoOrBuilder getMessageTypeOrBuilder(int i);

        List getMessageTypeOrBuilderList();

        String getName();

        ByteString getNameBytes();

        FileOptions getOptions();

        FileOptionsOrBuilder getOptionsOrBuilder();

        String getPackage();

        ByteString getPackageBytes();

        int getPublicDependency(int i);

        int getPublicDependencyCount();

        List getPublicDependencyList();

        ServiceDescriptorProto getService(int i);

        int getServiceCount();

        List getServiceList();

        ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int i);

        List getServiceOrBuilderList();

        SourceCodeInfo getSourceCodeInfo();

        SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder();

        int getWeakDependency(int i);

        int getWeakDependencyCount();

        List getWeakDependencyList();

        boolean hasName();

        boolean hasOptions();

        boolean hasPackage();

        boolean hasSourceCodeInfo();
    }

    public final class FileDescriptorProto extends GeneratedMessage implements FileDescriptorProtoOrBuilder {
        public static final int DEPENDENCY_FIELD_NUMBER = 3;
        public static final int ENUM_TYPE_FIELD_NUMBER = 5;
        public static final int EXTENSION_FIELD_NUMBER = 7;
        public static final int MESSAGE_TYPE_FIELD_NUMBER = 4;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OPTIONS_FIELD_NUMBER = 8;
        public static final int PACKAGE_FIELD_NUMBER = 2;
        public static Parser PARSER = new C19741();
        public static final int PUBLIC_DEPENDENCY_FIELD_NUMBER = 10;
        public static final int SERVICE_FIELD_NUMBER = 6;
        public static final int SOURCE_CODE_INFO_FIELD_NUMBER = 9;
        public static final int WEAK_DEPENDENCY_FIELD_NUMBER = 11;
        private static final FileDescriptorProto defaultInstance = new FileDescriptorProto(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private LazyStringList dependency_;
        private List enumType_;
        private List extension_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List messageType_;
        private Object name_;
        private FileOptions options_;
        private Object package_;
        private List publicDependency_;
        private List service_;
        private SourceCodeInfo sourceCodeInfo_;
        private final UnknownFieldSet unknownFields;
        private List weakDependency_;

        final class C19741 extends AbstractParser {
            C19741() {
            }

            public FileDescriptorProto parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new FileDescriptorProto(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements FileDescriptorProtoOrBuilder {
            private int bitField0_;
            private LazyStringList dependency_;
            private RepeatedFieldBuilder enumTypeBuilder_;
            private List enumType_;
            private RepeatedFieldBuilder extensionBuilder_;
            private List extension_;
            private RepeatedFieldBuilder messageTypeBuilder_;
            private List messageType_;
            private Object name_;
            private SingleFieldBuilder optionsBuilder_;
            private FileOptions options_;
            private Object package_;
            private List publicDependency_;
            private RepeatedFieldBuilder serviceBuilder_;
            private List service_;
            private SingleFieldBuilder sourceCodeInfoBuilder_;
            private SourceCodeInfo sourceCodeInfo_;
            private List weakDependency_;

            private Builder() {
                this.name_ = "";
                this.package_ = "";
                this.dependency_ = LazyStringArrayList.EMPTY;
                this.publicDependency_ = Collections.emptyList();
                this.weakDependency_ = Collections.emptyList();
                this.messageType_ = Collections.emptyList();
                this.enumType_ = Collections.emptyList();
                this.service_ = Collections.emptyList();
                this.extension_ = Collections.emptyList();
                this.options_ = FileOptions.getDefaultInstance();
                this.sourceCodeInfo_ = SourceCodeInfo.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.package_ = "";
                this.dependency_ = LazyStringArrayList.EMPTY;
                this.publicDependency_ = Collections.emptyList();
                this.weakDependency_ = Collections.emptyList();
                this.messageType_ = Collections.emptyList();
                this.enumType_ = Collections.emptyList();
                this.service_ = Collections.emptyList();
                this.extension_ = Collections.emptyList();
                this.options_ = FileOptions.getDefaultInstance();
                this.sourceCodeInfo_ = SourceCodeInfo.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureDependencyIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.dependency_ = new LazyStringArrayList(this.dependency_);
                    this.bitField0_ |= 4;
                }
            }

            private void ensureEnumTypeIsMutable() {
                if ((this.bitField0_ & 64) != 64) {
                    this.enumType_ = new ArrayList(this.enumType_);
                    this.bitField0_ |= 64;
                }
            }

            private void ensureExtensionIsMutable() {
                if ((this.bitField0_ & 256) != 256) {
                    this.extension_ = new ArrayList(this.extension_);
                    this.bitField0_ |= 256;
                }
            }

            private void ensureMessageTypeIsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.messageType_ = new ArrayList(this.messageType_);
                    this.bitField0_ |= 32;
                }
            }

            private void ensurePublicDependencyIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.publicDependency_ = new ArrayList(this.publicDependency_);
                    this.bitField0_ |= 8;
                }
            }

            private void ensureServiceIsMutable() {
                if ((this.bitField0_ & 128) != 128) {
                    this.service_ = new ArrayList(this.service_);
                    this.bitField0_ |= 128;
                }
            }

            private void ensureWeakDependencyIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.weakDependency_ = new ArrayList(this.weakDependency_);
                    this.bitField0_ |= 16;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
            }

            private RepeatedFieldBuilder getEnumTypeFieldBuilder() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumTypeBuilder_ = new RepeatedFieldBuilder(this.enumType_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
                    this.enumType_ = null;
                }
                return this.enumTypeBuilder_;
            }

            private RepeatedFieldBuilder getExtensionFieldBuilder() {
                if (this.extensionBuilder_ == null) {
                    this.extensionBuilder_ = new RepeatedFieldBuilder(this.extension_, (this.bitField0_ & 256) == 256, getParentForChildren(), isClean());
                    this.extension_ = null;
                }
                return this.extensionBuilder_;
            }

            private RepeatedFieldBuilder getMessageTypeFieldBuilder() {
                if (this.messageTypeBuilder_ == null) {
                    this.messageTypeBuilder_ = new RepeatedFieldBuilder(this.messageType_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
                    this.messageType_ = null;
                }
                return this.messageTypeBuilder_;
            }

            private SingleFieldBuilder getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            private RepeatedFieldBuilder getServiceFieldBuilder() {
                if (this.serviceBuilder_ == null) {
                    this.serviceBuilder_ = new RepeatedFieldBuilder(this.service_, (this.bitField0_ & 128) == 128, getParentForChildren(), isClean());
                    this.service_ = null;
                }
                return this.serviceBuilder_;
            }

            private SingleFieldBuilder getSourceCodeInfoFieldBuilder() {
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfoBuilder_ = new SingleFieldBuilder(getSourceCodeInfo(), getParentForChildren(), isClean());
                    this.sourceCodeInfo_ = null;
                }
                return this.sourceCodeInfoBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getMessageTypeFieldBuilder();
                    getEnumTypeFieldBuilder();
                    getServiceFieldBuilder();
                    getExtensionFieldBuilder();
                    getOptionsFieldBuilder();
                    getSourceCodeInfoFieldBuilder();
                }
            }

            public Builder addAllDependency(Iterable iterable) {
                ensureDependencyIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.dependency_);
                onChanged();
                return this;
            }

            public Builder addAllEnumType(Iterable iterable) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.enumType_);
                    onChanged();
                } else {
                    this.enumTypeBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllExtension(Iterable iterable) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.extension_);
                    onChanged();
                } else {
                    this.extensionBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllMessageType(Iterable iterable) {
                if (this.messageTypeBuilder_ == null) {
                    ensureMessageTypeIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.messageType_);
                    onChanged();
                } else {
                    this.messageTypeBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllPublicDependency(Iterable iterable) {
                ensurePublicDependencyIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.publicDependency_);
                onChanged();
                return this;
            }

            public Builder addAllService(Iterable iterable) {
                if (this.serviceBuilder_ == null) {
                    ensureServiceIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.service_);
                    onChanged();
                } else {
                    this.serviceBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addAllWeakDependency(Iterable iterable) {
                ensureWeakDependencyIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.weakDependency_);
                onChanged();
                return this;
            }

            public Builder addDependency(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureDependencyIsMutable();
                this.dependency_.add(str);
                onChanged();
                return this;
            }

            public Builder addDependencyBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                ensureDependencyIsMutable();
                this.dependency_.add(byteString);
                onChanged();
                return this;
            }

            public Builder addEnumType(int i, Builder builder) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    this.enumType_.add(i, builder.build());
                    onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addEnumType(int i, EnumDescriptorProto enumDescriptorProto) {
                if (this.enumTypeBuilder_ != null) {
                    this.enumTypeBuilder_.addMessage(i, enumDescriptorProto);
                } else if (enumDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureEnumTypeIsMutable();
                    this.enumType_.add(i, enumDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addEnumType(Builder builder) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    this.enumType_.add(builder.build());
                    onChanged();
                } else {
                    this.enumTypeBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addEnumType(EnumDescriptorProto enumDescriptorProto) {
                if (this.enumTypeBuilder_ != null) {
                    this.enumTypeBuilder_.addMessage(enumDescriptorProto);
                } else if (enumDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureEnumTypeIsMutable();
                    this.enumType_.add(enumDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addEnumTypeBuilder() {
                return (Builder) getEnumTypeFieldBuilder().addBuilder(EnumDescriptorProto.getDefaultInstance());
            }

            public Builder addEnumTypeBuilder(int i) {
                return (Builder) getEnumTypeFieldBuilder().addBuilder(i, EnumDescriptorProto.getDefaultInstance());
            }

            public Builder addExtension(int i, Builder builder) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    this.extension_.add(i, builder.build());
                    onChanged();
                } else {
                    this.extensionBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addExtension(int i, FieldDescriptorProto fieldDescriptorProto) {
                if (this.extensionBuilder_ != null) {
                    this.extensionBuilder_.addMessage(i, fieldDescriptorProto);
                } else if (fieldDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureExtensionIsMutable();
                    this.extension_.add(i, fieldDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addExtension(Builder builder) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    this.extension_.add(builder.build());
                    onChanged();
                } else {
                    this.extensionBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addExtension(FieldDescriptorProto fieldDescriptorProto) {
                if (this.extensionBuilder_ != null) {
                    this.extensionBuilder_.addMessage(fieldDescriptorProto);
                } else if (fieldDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureExtensionIsMutable();
                    this.extension_.add(fieldDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addExtensionBuilder() {
                return (Builder) getExtensionFieldBuilder().addBuilder(FieldDescriptorProto.getDefaultInstance());
            }

            public Builder addExtensionBuilder(int i) {
                return (Builder) getExtensionFieldBuilder().addBuilder(i, FieldDescriptorProto.getDefaultInstance());
            }

            public Builder addMessageType(int i, Builder builder) {
                if (this.messageTypeBuilder_ == null) {
                    ensureMessageTypeIsMutable();
                    this.messageType_.add(i, builder.build());
                    onChanged();
                } else {
                    this.messageTypeBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addMessageType(int i, DescriptorProto descriptorProto) {
                if (this.messageTypeBuilder_ != null) {
                    this.messageTypeBuilder_.addMessage(i, descriptorProto);
                } else if (descriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureMessageTypeIsMutable();
                    this.messageType_.add(i, descriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addMessageType(Builder builder) {
                if (this.messageTypeBuilder_ == null) {
                    ensureMessageTypeIsMutable();
                    this.messageType_.add(builder.build());
                    onChanged();
                } else {
                    this.messageTypeBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addMessageType(DescriptorProto descriptorProto) {
                if (this.messageTypeBuilder_ != null) {
                    this.messageTypeBuilder_.addMessage(descriptorProto);
                } else if (descriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureMessageTypeIsMutable();
                    this.messageType_.add(descriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addMessageTypeBuilder() {
                return (Builder) getMessageTypeFieldBuilder().addBuilder(DescriptorProto.getDefaultInstance());
            }

            public Builder addMessageTypeBuilder(int i) {
                return (Builder) getMessageTypeFieldBuilder().addBuilder(i, DescriptorProto.getDefaultInstance());
            }

            public Builder addPublicDependency(int i) {
                ensurePublicDependencyIsMutable();
                this.publicDependency_.add(Integer.valueOf(i));
                onChanged();
                return this;
            }

            public Builder addService(int i, Builder builder) {
                if (this.serviceBuilder_ == null) {
                    ensureServiceIsMutable();
                    this.service_.add(i, builder.build());
                    onChanged();
                } else {
                    this.serviceBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addService(int i, ServiceDescriptorProto serviceDescriptorProto) {
                if (this.serviceBuilder_ != null) {
                    this.serviceBuilder_.addMessage(i, serviceDescriptorProto);
                } else if (serviceDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureServiceIsMutable();
                    this.service_.add(i, serviceDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addService(Builder builder) {
                if (this.serviceBuilder_ == null) {
                    ensureServiceIsMutable();
                    this.service_.add(builder.build());
                    onChanged();
                } else {
                    this.serviceBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addService(ServiceDescriptorProto serviceDescriptorProto) {
                if (this.serviceBuilder_ != null) {
                    this.serviceBuilder_.addMessage(serviceDescriptorProto);
                } else if (serviceDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureServiceIsMutable();
                    this.service_.add(serviceDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addServiceBuilder() {
                return (Builder) getServiceFieldBuilder().addBuilder(ServiceDescriptorProto.getDefaultInstance());
            }

            public Builder addServiceBuilder(int i) {
                return (Builder) getServiceFieldBuilder().addBuilder(i, ServiceDescriptorProto.getDefaultInstance());
            }

            public Builder addWeakDependency(int i) {
                ensureWeakDependencyIsMutable();
                this.weakDependency_.add(Integer.valueOf(i));
                onChanged();
                return this;
            }

            public FileDescriptorProto build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public FileDescriptorProto buildPartial() {
                int i = 1;
                FileDescriptorProto fileDescriptorProto = new FileDescriptorProto((com.google.protobuf.GeneratedMessage.Builder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                fileDescriptorProto.name_ = this.name_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                fileDescriptorProto.package_ = this.package_;
                if ((this.bitField0_ & 4) == 4) {
                    this.dependency_ = this.dependency_.getUnmodifiableView();
                    this.bitField0_ &= -5;
                }
                fileDescriptorProto.dependency_ = this.dependency_;
                if ((this.bitField0_ & 8) == 8) {
                    this.publicDependency_ = Collections.unmodifiableList(this.publicDependency_);
                    this.bitField0_ &= -9;
                }
                fileDescriptorProto.publicDependency_ = this.publicDependency_;
                if ((this.bitField0_ & 16) == 16) {
                    this.weakDependency_ = Collections.unmodifiableList(this.weakDependency_);
                    this.bitField0_ &= -17;
                }
                fileDescriptorProto.weakDependency_ = this.weakDependency_;
                if (this.messageTypeBuilder_ == null) {
                    if ((this.bitField0_ & 32) == 32) {
                        this.messageType_ = Collections.unmodifiableList(this.messageType_);
                        this.bitField0_ &= -33;
                    }
                    fileDescriptorProto.messageType_ = this.messageType_;
                } else {
                    fileDescriptorProto.messageType_ = this.messageTypeBuilder_.build();
                }
                if (this.enumTypeBuilder_ == null) {
                    if ((this.bitField0_ & 64) == 64) {
                        this.enumType_ = Collections.unmodifiableList(this.enumType_);
                        this.bitField0_ &= -65;
                    }
                    fileDescriptorProto.enumType_ = this.enumType_;
                } else {
                    fileDescriptorProto.enumType_ = this.enumTypeBuilder_.build();
                }
                if (this.serviceBuilder_ == null) {
                    if ((this.bitField0_ & 128) == 128) {
                        this.service_ = Collections.unmodifiableList(this.service_);
                        this.bitField0_ &= -129;
                    }
                    fileDescriptorProto.service_ = this.service_;
                } else {
                    fileDescriptorProto.service_ = this.serviceBuilder_.build();
                }
                if (this.extensionBuilder_ == null) {
                    if ((this.bitField0_ & 256) == 256) {
                        this.extension_ = Collections.unmodifiableList(this.extension_);
                        this.bitField0_ &= -257;
                    }
                    fileDescriptorProto.extension_ = this.extension_;
                } else {
                    fileDescriptorProto.extension_ = this.extensionBuilder_.build();
                }
                int i3 = (i2 & 512) == 512 ? i | 4 : i;
                if (this.optionsBuilder_ == null) {
                    fileDescriptorProto.options_ = this.options_;
                } else {
                    fileDescriptorProto.options_ = (FileOptions) this.optionsBuilder_.build();
                }
                if ((i2 & 1024) == 1024) {
                    i3 |= 8;
                }
                if (this.sourceCodeInfoBuilder_ == null) {
                    fileDescriptorProto.sourceCodeInfo_ = this.sourceCodeInfo_;
                } else {
                    fileDescriptorProto.sourceCodeInfo_ = (SourceCodeInfo) this.sourceCodeInfoBuilder_.build();
                }
                fileDescriptorProto.bitField0_ = i3;
                onBuilt();
                return fileDescriptorProto;
            }

            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                this.package_ = "";
                this.bitField0_ &= -3;
                this.dependency_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -5;
                this.publicDependency_ = Collections.emptyList();
                this.bitField0_ &= -9;
                this.weakDependency_ = Collections.emptyList();
                this.bitField0_ &= -17;
                if (this.messageTypeBuilder_ == null) {
                    this.messageType_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                } else {
                    this.messageTypeBuilder_.clear();
                }
                if (this.enumTypeBuilder_ == null) {
                    this.enumType_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                } else {
                    this.enumTypeBuilder_.clear();
                }
                if (this.serviceBuilder_ == null) {
                    this.service_ = Collections.emptyList();
                    this.bitField0_ &= -129;
                } else {
                    this.serviceBuilder_.clear();
                }
                if (this.extensionBuilder_ == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= -257;
                } else {
                    this.extensionBuilder_.clear();
                }
                if (this.optionsBuilder_ == null) {
                    this.options_ = FileOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -513;
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfo_ = SourceCodeInfo.getDefaultInstance();
                } else {
                    this.sourceCodeInfoBuilder_.clear();
                }
                this.bitField0_ &= -1025;
                return this;
            }

            public Builder clearDependency() {
                this.dependency_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -5;
                onChanged();
                return this;
            }

            public Builder clearEnumType() {
                if (this.enumTypeBuilder_ == null) {
                    this.enumType_ = Collections.emptyList();
                    this.bitField0_ &= -65;
                    onChanged();
                } else {
                    this.enumTypeBuilder_.clear();
                }
                return this;
            }

            public Builder clearExtension() {
                if (this.extensionBuilder_ == null) {
                    this.extension_ = Collections.emptyList();
                    this.bitField0_ &= -257;
                    onChanged();
                } else {
                    this.extensionBuilder_.clear();
                }
                return this;
            }

            public Builder clearMessageType() {
                if (this.messageTypeBuilder_ == null) {
                    this.messageType_ = Collections.emptyList();
                    this.bitField0_ &= -33;
                    onChanged();
                } else {
                    this.messageTypeBuilder_.clear();
                }
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = FileDescriptorProto.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = FileOptions.getDefaultInstance();
                    onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -513;
                return this;
            }

            public Builder clearPackage() {
                this.bitField0_ &= -3;
                this.package_ = FileDescriptorProto.getDefaultInstance().getPackage();
                onChanged();
                return this;
            }

            public Builder clearPublicDependency() {
                this.publicDependency_ = Collections.emptyList();
                this.bitField0_ &= -9;
                onChanged();
                return this;
            }

            public Builder clearService() {
                if (this.serviceBuilder_ == null) {
                    this.service_ = Collections.emptyList();
                    this.bitField0_ &= -129;
                    onChanged();
                } else {
                    this.serviceBuilder_.clear();
                }
                return this;
            }

            public Builder clearSourceCodeInfo() {
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfo_ = SourceCodeInfo.getDefaultInstance();
                    onChanged();
                } else {
                    this.sourceCodeInfoBuilder_.clear();
                }
                this.bitField0_ &= -1025;
                return this;
            }

            public Builder clearWeakDependency() {
                this.weakDependency_ = Collections.emptyList();
                this.bitField0_ &= -17;
                onChanged();
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public FileDescriptorProto getDefaultInstanceForType() {
                return FileDescriptorProto.getDefaultInstance();
            }

            public String getDependency(int i) {
                return (String) this.dependency_.get(i);
            }

            public ByteString getDependencyBytes(int i) {
                return this.dependency_.getByteString(i);
            }

            public int getDependencyCount() {
                return this.dependency_.size();
            }

            public ProtocolStringList getDependencyList() {
                return this.dependency_.getUnmodifiableView();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
            }

            public EnumDescriptorProto getEnumType(int i) {
                return this.enumTypeBuilder_ == null ? (EnumDescriptorProto) this.enumType_.get(i) : (EnumDescriptorProto) this.enumTypeBuilder_.getMessage(i);
            }

            public Builder getEnumTypeBuilder(int i) {
                return (Builder) getEnumTypeFieldBuilder().getBuilder(i);
            }

            public List getEnumTypeBuilderList() {
                return getEnumTypeFieldBuilder().getBuilderList();
            }

            public int getEnumTypeCount() {
                return this.enumTypeBuilder_ == null ? this.enumType_.size() : this.enumTypeBuilder_.getCount();
            }

            public List getEnumTypeList() {
                return this.enumTypeBuilder_ == null ? Collections.unmodifiableList(this.enumType_) : this.enumTypeBuilder_.getMessageList();
            }

            public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int i) {
                return this.enumTypeBuilder_ == null ? (EnumDescriptorProtoOrBuilder) this.enumType_.get(i) : (EnumDescriptorProtoOrBuilder) this.enumTypeBuilder_.getMessageOrBuilder(i);
            }

            public List getEnumTypeOrBuilderList() {
                return this.enumTypeBuilder_ != null ? this.enumTypeBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.enumType_);
            }

            public FieldDescriptorProto getExtension(int i) {
                return this.extensionBuilder_ == null ? (FieldDescriptorProto) this.extension_.get(i) : (FieldDescriptorProto) this.extensionBuilder_.getMessage(i);
            }

            public Builder getExtensionBuilder(int i) {
                return (Builder) getExtensionFieldBuilder().getBuilder(i);
            }

            public List getExtensionBuilderList() {
                return getExtensionFieldBuilder().getBuilderList();
            }

            public int getExtensionCount() {
                return this.extensionBuilder_ == null ? this.extension_.size() : this.extensionBuilder_.getCount();
            }

            public List getExtensionList() {
                return this.extensionBuilder_ == null ? Collections.unmodifiableList(this.extension_) : this.extensionBuilder_.getMessageList();
            }

            public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int i) {
                return this.extensionBuilder_ == null ? (FieldDescriptorProtoOrBuilder) this.extension_.get(i) : (FieldDescriptorProtoOrBuilder) this.extensionBuilder_.getMessageOrBuilder(i);
            }

            public List getExtensionOrBuilderList() {
                return this.extensionBuilder_ != null ? this.extensionBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.extension_);
            }

            public DescriptorProto getMessageType(int i) {
                return this.messageTypeBuilder_ == null ? (DescriptorProto) this.messageType_.get(i) : (DescriptorProto) this.messageTypeBuilder_.getMessage(i);
            }

            public Builder getMessageTypeBuilder(int i) {
                return (Builder) getMessageTypeFieldBuilder().getBuilder(i);
            }

            public List getMessageTypeBuilderList() {
                return getMessageTypeFieldBuilder().getBuilderList();
            }

            public int getMessageTypeCount() {
                return this.messageTypeBuilder_ == null ? this.messageType_.size() : this.messageTypeBuilder_.getCount();
            }

            public List getMessageTypeList() {
                return this.messageTypeBuilder_ == null ? Collections.unmodifiableList(this.messageType_) : this.messageTypeBuilder_.getMessageList();
            }

            public DescriptorProtoOrBuilder getMessageTypeOrBuilder(int i) {
                return this.messageTypeBuilder_ == null ? (DescriptorProtoOrBuilder) this.messageType_.get(i) : (DescriptorProtoOrBuilder) this.messageTypeBuilder_.getMessageOrBuilder(i);
            }

            public List getMessageTypeOrBuilderList() {
                return this.messageTypeBuilder_ != null ? this.messageTypeBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.messageType_);
            }

            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public FileOptions getOptions() {
                return this.optionsBuilder_ == null ? this.options_ : (FileOptions) this.optionsBuilder_.getMessage();
            }

            public Builder getOptionsBuilder() {
                this.bitField0_ |= 512;
                onChanged();
                return (Builder) getOptionsFieldBuilder().getBuilder();
            }

            public FileOptionsOrBuilder getOptionsOrBuilder() {
                return this.optionsBuilder_ != null ? (FileOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder() : this.options_;
            }

            public String getPackage() {
                Object obj = this.package_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.package_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getPackageBytes() {
                Object obj = this.package_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.package_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public int getPublicDependency(int i) {
                return ((Integer) this.publicDependency_.get(i)).intValue();
            }

            public int getPublicDependencyCount() {
                return this.publicDependency_.size();
            }

            public List getPublicDependencyList() {
                return Collections.unmodifiableList(this.publicDependency_);
            }

            public ServiceDescriptorProto getService(int i) {
                return this.serviceBuilder_ == null ? (ServiceDescriptorProto) this.service_.get(i) : (ServiceDescriptorProto) this.serviceBuilder_.getMessage(i);
            }

            public Builder getServiceBuilder(int i) {
                return (Builder) getServiceFieldBuilder().getBuilder(i);
            }

            public List getServiceBuilderList() {
                return getServiceFieldBuilder().getBuilderList();
            }

            public int getServiceCount() {
                return this.serviceBuilder_ == null ? this.service_.size() : this.serviceBuilder_.getCount();
            }

            public List getServiceList() {
                return this.serviceBuilder_ == null ? Collections.unmodifiableList(this.service_) : this.serviceBuilder_.getMessageList();
            }

            public ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int i) {
                return this.serviceBuilder_ == null ? (ServiceDescriptorProtoOrBuilder) this.service_.get(i) : (ServiceDescriptorProtoOrBuilder) this.serviceBuilder_.getMessageOrBuilder(i);
            }

            public List getServiceOrBuilderList() {
                return this.serviceBuilder_ != null ? this.serviceBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.service_);
            }

            public SourceCodeInfo getSourceCodeInfo() {
                return this.sourceCodeInfoBuilder_ == null ? this.sourceCodeInfo_ : (SourceCodeInfo) this.sourceCodeInfoBuilder_.getMessage();
            }

            public Builder getSourceCodeInfoBuilder() {
                this.bitField0_ |= 1024;
                onChanged();
                return (Builder) getSourceCodeInfoFieldBuilder().getBuilder();
            }

            public SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder() {
                return this.sourceCodeInfoBuilder_ != null ? (SourceCodeInfoOrBuilder) this.sourceCodeInfoBuilder_.getMessageOrBuilder() : this.sourceCodeInfo_;
            }

            public int getWeakDependency(int i) {
                return ((Integer) this.weakDependency_.get(i)).intValue();
            }

            public int getWeakDependencyCount() {
                return this.weakDependency_.size();
            }

            public List getWeakDependencyList() {
                return Collections.unmodifiableList(this.weakDependency_);
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 512) == 512;
            }

            public boolean hasPackage() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasSourceCodeInfo() {
                return (this.bitField0_ & 1024) == 1024;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f214x4b52780c.ensureFieldAccessorsInitialized(FileDescriptorProto.class, Builder.class);
            }

            public final boolean isInitialized() {
                int i;
                for (i = 0; i < getMessageTypeCount(); i++) {
                    if (!getMessageType(i).isInitialized()) {
                        return false;
                    }
                }
                for (i = 0; i < getEnumTypeCount(); i++) {
                    if (!getEnumType(i).isInitialized()) {
                        return false;
                    }
                }
                for (i = 0; i < getServiceCount(); i++) {
                    if (!getService(i).isInitialized()) {
                        return false;
                    }
                }
                for (i = 0; i < getExtensionCount(); i++) {
                    if (!getExtension(i).isInitialized()) {
                        return false;
                    }
                }
                return !hasOptions() || getOptions().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                FileDescriptorProto fileDescriptorProto;
                Throwable th;
                try {
                    fileDescriptorProto = (FileDescriptorProto) FileDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (fileDescriptorProto != null) {
                        mergeFrom(fileDescriptorProto);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    fileDescriptorProto = (FileDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (fileDescriptorProto != null) {
                    mergeFrom(fileDescriptorProto);
                }
                throw th;
            }

            public Builder mergeFrom(FileDescriptorProto fileDescriptorProto) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (fileDescriptorProto != FileDescriptorProto.getDefaultInstance()) {
                    if (fileDescriptorProto.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = fileDescriptorProto.name_;
                        onChanged();
                    }
                    if (fileDescriptorProto.hasPackage()) {
                        this.bitField0_ |= 2;
                        this.package_ = fileDescriptorProto.package_;
                        onChanged();
                    }
                    if (!fileDescriptorProto.dependency_.isEmpty()) {
                        if (this.dependency_.isEmpty()) {
                            this.dependency_ = fileDescriptorProto.dependency_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureDependencyIsMutable();
                            this.dependency_.addAll(fileDescriptorProto.dependency_);
                        }
                        onChanged();
                    }
                    if (!fileDescriptorProto.publicDependency_.isEmpty()) {
                        if (this.publicDependency_.isEmpty()) {
                            this.publicDependency_ = fileDescriptorProto.publicDependency_;
                            this.bitField0_ &= -9;
                        } else {
                            ensurePublicDependencyIsMutable();
                            this.publicDependency_.addAll(fileDescriptorProto.publicDependency_);
                        }
                        onChanged();
                    }
                    if (!fileDescriptorProto.weakDependency_.isEmpty()) {
                        if (this.weakDependency_.isEmpty()) {
                            this.weakDependency_ = fileDescriptorProto.weakDependency_;
                            this.bitField0_ &= -17;
                        } else {
                            ensureWeakDependencyIsMutable();
                            this.weakDependency_.addAll(fileDescriptorProto.weakDependency_);
                        }
                        onChanged();
                    }
                    if (this.messageTypeBuilder_ == null) {
                        if (!fileDescriptorProto.messageType_.isEmpty()) {
                            if (this.messageType_.isEmpty()) {
                                this.messageType_ = fileDescriptorProto.messageType_;
                                this.bitField0_ &= -33;
                            } else {
                                ensureMessageTypeIsMutable();
                                this.messageType_.addAll(fileDescriptorProto.messageType_);
                            }
                            onChanged();
                        }
                    } else if (!fileDescriptorProto.messageType_.isEmpty()) {
                        if (this.messageTypeBuilder_.isEmpty()) {
                            this.messageTypeBuilder_.dispose();
                            this.messageTypeBuilder_ = null;
                            this.messageType_ = fileDescriptorProto.messageType_;
                            this.bitField0_ &= -33;
                            this.messageTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getMessageTypeFieldBuilder() : null;
                        } else {
                            this.messageTypeBuilder_.addAllMessages(fileDescriptorProto.messageType_);
                        }
                    }
                    if (this.enumTypeBuilder_ == null) {
                        if (!fileDescriptorProto.enumType_.isEmpty()) {
                            if (this.enumType_.isEmpty()) {
                                this.enumType_ = fileDescriptorProto.enumType_;
                                this.bitField0_ &= -65;
                            } else {
                                ensureEnumTypeIsMutable();
                                this.enumType_.addAll(fileDescriptorProto.enumType_);
                            }
                            onChanged();
                        }
                    } else if (!fileDescriptorProto.enumType_.isEmpty()) {
                        if (this.enumTypeBuilder_.isEmpty()) {
                            this.enumTypeBuilder_.dispose();
                            this.enumTypeBuilder_ = null;
                            this.enumType_ = fileDescriptorProto.enumType_;
                            this.bitField0_ &= -65;
                            this.enumTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null;
                        } else {
                            this.enumTypeBuilder_.addAllMessages(fileDescriptorProto.enumType_);
                        }
                    }
                    if (this.serviceBuilder_ == null) {
                        if (!fileDescriptorProto.service_.isEmpty()) {
                            if (this.service_.isEmpty()) {
                                this.service_ = fileDescriptorProto.service_;
                                this.bitField0_ &= -129;
                            } else {
                                ensureServiceIsMutable();
                                this.service_.addAll(fileDescriptorProto.service_);
                            }
                            onChanged();
                        }
                    } else if (!fileDescriptorProto.service_.isEmpty()) {
                        if (this.serviceBuilder_.isEmpty()) {
                            this.serviceBuilder_.dispose();
                            this.serviceBuilder_ = null;
                            this.service_ = fileDescriptorProto.service_;
                            this.bitField0_ &= -129;
                            this.serviceBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getServiceFieldBuilder() : null;
                        } else {
                            this.serviceBuilder_.addAllMessages(fileDescriptorProto.service_);
                        }
                    }
                    if (this.extensionBuilder_ == null) {
                        if (!fileDescriptorProto.extension_.isEmpty()) {
                            if (this.extension_.isEmpty()) {
                                this.extension_ = fileDescriptorProto.extension_;
                                this.bitField0_ &= -257;
                            } else {
                                ensureExtensionIsMutable();
                                this.extension_.addAll(fileDescriptorProto.extension_);
                            }
                            onChanged();
                        }
                    } else if (!fileDescriptorProto.extension_.isEmpty()) {
                        if (this.extensionBuilder_.isEmpty()) {
                            this.extensionBuilder_.dispose();
                            this.extensionBuilder_ = null;
                            this.extension_ = fileDescriptorProto.extension_;
                            this.bitField0_ &= -257;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getExtensionFieldBuilder();
                            }
                            this.extensionBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.extensionBuilder_.addAllMessages(fileDescriptorProto.extension_);
                        }
                    }
                    if (fileDescriptorProto.hasOptions()) {
                        mergeOptions(fileDescriptorProto.getOptions());
                    }
                    if (fileDescriptorProto.hasSourceCodeInfo()) {
                        mergeSourceCodeInfo(fileDescriptorProto.getSourceCodeInfo());
                    }
                    mergeUnknownFields(fileDescriptorProto.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof FileDescriptorProto) {
                    return mergeFrom((FileDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(FileOptions fileOptions) {
                if (this.optionsBuilder_ == null) {
                    if ((this.bitField0_ & 512) != 512 || this.options_ == FileOptions.getDefaultInstance()) {
                        this.options_ = fileOptions;
                    } else {
                        this.options_ = FileOptions.newBuilder(this.options_).mergeFrom(fileOptions).buildPartial();
                    }
                    onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(fileOptions);
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder mergeSourceCodeInfo(SourceCodeInfo sourceCodeInfo) {
                if (this.sourceCodeInfoBuilder_ == null) {
                    if ((this.bitField0_ & 1024) != 1024 || this.sourceCodeInfo_ == SourceCodeInfo.getDefaultInstance()) {
                        this.sourceCodeInfo_ = sourceCodeInfo;
                    } else {
                        this.sourceCodeInfo_ = SourceCodeInfo.newBuilder(this.sourceCodeInfo_).mergeFrom(sourceCodeInfo).buildPartial();
                    }
                    onChanged();
                } else {
                    this.sourceCodeInfoBuilder_.mergeFrom(sourceCodeInfo);
                }
                this.bitField0_ |= 1024;
                return this;
            }

            public Builder removeEnumType(int i) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    this.enumType_.remove(i);
                    onChanged();
                } else {
                    this.enumTypeBuilder_.remove(i);
                }
                return this;
            }

            public Builder removeExtension(int i) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    this.extension_.remove(i);
                    onChanged();
                } else {
                    this.extensionBuilder_.remove(i);
                }
                return this;
            }

            public Builder removeMessageType(int i) {
                if (this.messageTypeBuilder_ == null) {
                    ensureMessageTypeIsMutable();
                    this.messageType_.remove(i);
                    onChanged();
                } else {
                    this.messageTypeBuilder_.remove(i);
                }
                return this;
            }

            public Builder removeService(int i) {
                if (this.serviceBuilder_ == null) {
                    ensureServiceIsMutable();
                    this.service_.remove(i);
                    onChanged();
                } else {
                    this.serviceBuilder_.remove(i);
                }
                return this;
            }

            public Builder setDependency(int i, String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureDependencyIsMutable();
                this.dependency_.set(i, str);
                onChanged();
                return this;
            }

            public Builder setEnumType(int i, Builder builder) {
                if (this.enumTypeBuilder_ == null) {
                    ensureEnumTypeIsMutable();
                    this.enumType_.set(i, builder.build());
                    onChanged();
                } else {
                    this.enumTypeBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setEnumType(int i, EnumDescriptorProto enumDescriptorProto) {
                if (this.enumTypeBuilder_ != null) {
                    this.enumTypeBuilder_.setMessage(i, enumDescriptorProto);
                } else if (enumDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureEnumTypeIsMutable();
                    this.enumType_.set(i, enumDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setExtension(int i, Builder builder) {
                if (this.extensionBuilder_ == null) {
                    ensureExtensionIsMutable();
                    this.extension_.set(i, builder.build());
                    onChanged();
                } else {
                    this.extensionBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setExtension(int i, FieldDescriptorProto fieldDescriptorProto) {
                if (this.extensionBuilder_ != null) {
                    this.extensionBuilder_.setMessage(i, fieldDescriptorProto);
                } else if (fieldDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureExtensionIsMutable();
                    this.extension_.set(i, fieldDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setMessageType(int i, Builder builder) {
                if (this.messageTypeBuilder_ == null) {
                    ensureMessageTypeIsMutable();
                    this.messageType_.set(i, builder.build());
                    onChanged();
                } else {
                    this.messageTypeBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setMessageType(int i, DescriptorProto descriptorProto) {
                if (this.messageTypeBuilder_ != null) {
                    this.messageTypeBuilder_.setMessage(i, descriptorProto);
                } else if (descriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureMessageTypeIsMutable();
                    this.messageType_.set(i, descriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder setOptions(Builder builder) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builder.build());
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder setOptions(FileOptions fileOptions) {
                if (this.optionsBuilder_ != null) {
                    this.optionsBuilder_.setMessage(fileOptions);
                } else if (fileOptions == null) {
                    throw new NullPointerException();
                } else {
                    this.options_ = fileOptions;
                    onChanged();
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder setPackage(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.package_ = str;
                onChanged();
                return this;
            }

            public Builder setPackageBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.package_ = byteString;
                onChanged();
                return this;
            }

            public Builder setPublicDependency(int i, int i2) {
                ensurePublicDependencyIsMutable();
                this.publicDependency_.set(i, Integer.valueOf(i2));
                onChanged();
                return this;
            }

            public Builder setService(int i, Builder builder) {
                if (this.serviceBuilder_ == null) {
                    ensureServiceIsMutable();
                    this.service_.set(i, builder.build());
                    onChanged();
                } else {
                    this.serviceBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setService(int i, ServiceDescriptorProto serviceDescriptorProto) {
                if (this.serviceBuilder_ != null) {
                    this.serviceBuilder_.setMessage(i, serviceDescriptorProto);
                } else if (serviceDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureServiceIsMutable();
                    this.service_.set(i, serviceDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setSourceCodeInfo(Builder builder) {
                if (this.sourceCodeInfoBuilder_ == null) {
                    this.sourceCodeInfo_ = builder.build();
                    onChanged();
                } else {
                    this.sourceCodeInfoBuilder_.setMessage(builder.build());
                }
                this.bitField0_ |= 1024;
                return this;
            }

            public Builder setSourceCodeInfo(SourceCodeInfo sourceCodeInfo) {
                if (this.sourceCodeInfoBuilder_ != null) {
                    this.sourceCodeInfoBuilder_.setMessage(sourceCodeInfo);
                } else if (sourceCodeInfo == null) {
                    throw new NullPointerException();
                } else {
                    this.sourceCodeInfo_ = sourceCodeInfo;
                    onChanged();
                }
                this.bitField0_ |= 1024;
                return this;
            }

            public Builder setWeakDependency(int i, int i2) {
                ensureWeakDependencyIsMutable();
                this.weakDependency_.set(i, Integer.valueOf(i2));
                onChanged();
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private FileDescriptorProto(com.google.protobuf.CodedInputStream r13, com.google.protobuf.ExtensionRegistryLite r14) {
            /*
            r12 = this;
            r10 = 64;
            r9 = 32;
            r8 = 16;
            r7 = 4;
            r6 = 8;
            r12.<init>();
            r0 = -1;
            r12.memoizedIsInitialized = r0;
            r0 = -1;
            r12.memoizedSerializedSize = r0;
            r12.initFields();
            r2 = 0;
            r4 = com.google.protobuf.UnknownFieldSet.newBuilder();
            r1 = 0;
        L_0x001b:
            if (r1 != 0) goto L_0x0266;
        L_0x001d:
            r0 = r13.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            switch(r0) {
                case 0: goto L_0x02ef;
                case 10: goto L_0x002f;
                case 18: goto L_0x003e;
                case 26: goto L_0x004d;
                case 34: goto L_0x0067;
                case 42: goto L_0x0083;
                case 50: goto L_0x009f;
                case 58: goto L_0x00be;
                case 66: goto L_0x00dd;
                case 74: goto L_0x010b;
                case 80: goto L_0x0139;
                case 82: goto L_0x0158;
                case 88: goto L_0x01fd;
                case 90: goto L_0x021c;
                default: goto L_0x0024;
            };	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
        L_0x0024:
            r0 = r12.parseUnknownField(r13, r4, r14, r0);	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            if (r0 != 0) goto L_0x02eb;
        L_0x002a:
            r0 = 1;
            r1 = r2;
        L_0x002c:
            r2 = r1;
            r1 = r0;
            goto L_0x001b;
        L_0x002f:
            r0 = r13.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3 = r3 | 1;
            r12.bitField0_ = r3;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r1;
            r1 = r2;
            goto L_0x002c;
        L_0x003e:
            r0 = r13.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3 = r3 | 2;
            r12.bitField0_ = r3;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.package_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r1;
            r1 = r2;
            goto L_0x002c;
        L_0x004d:
            r3 = r13.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 & 4;
            if (r0 == r7) goto L_0x02e8;
        L_0x0055:
            r0 = new com.google.protobuf.LazyStringArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.dependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 | 4;
        L_0x005e:
            r2 = r12.dependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x002c;
        L_0x0067:
            r0 = r2 & 32;
            if (r0 == r9) goto L_0x02e5;
        L_0x006b:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.messageType_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 | 32;
        L_0x0074:
            r2 = r12.messageType_;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = com.google.protobuf.DescriptorProtos.DescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x002c;
        L_0x0083:
            r0 = r2 & 64;
            if (r0 == r10) goto L_0x02e2;
        L_0x0087:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.enumType_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 | 64;
        L_0x0090:
            r2 = r12.enumType_;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = com.google.protobuf.DescriptorProtos.EnumDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x002c;
        L_0x009f:
            r0 = r2 & 128;
            r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
            if (r0 == r3) goto L_0x02df;
        L_0x00a5:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.service_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 | 128;
        L_0x00ae:
            r2 = r12.service_;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = com.google.protobuf.DescriptorProtos.ServiceDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x002c;
        L_0x00be:
            r0 = r2 & 256;
            r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
            if (r0 == r3) goto L_0x02dc;
        L_0x00c4:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.extension_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 | 256;
        L_0x00cd:
            r2 = r12.extension_;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = com.google.protobuf.DescriptorProtos.FieldDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = r13.readMessage(r3, r14);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x002c;
        L_0x00dd:
            r0 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r0 & 4;
            if (r0 != r7) goto L_0x02d8;
        L_0x00e3:
            r0 = r12.options_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3 = r0;
        L_0x00ea:
            r0 = com.google.protobuf.DescriptorProtos.FileOptions.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r13.readMessage(r0, r14);	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = (com.google.protobuf.DescriptorProtos.FileOptions) r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            if (r3 == 0) goto L_0x0101;
        L_0x00f6:
            r0 = r12.options_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r3.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
        L_0x0101:
            r0 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r0 | 4;
            r12.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r1;
            r1 = r2;
            goto L_0x002c;
        L_0x010b:
            r0 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r0 & 8;
            if (r0 != r6) goto L_0x02d4;
        L_0x0111:
            r0 = r12.sourceCodeInfo_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3 = r0;
        L_0x0118:
            r0 = com.google.protobuf.DescriptorProtos.SourceCodeInfo.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r13.readMessage(r0, r14);	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = (com.google.protobuf.DescriptorProtos.SourceCodeInfo) r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.sourceCodeInfo_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            if (r3 == 0) goto L_0x012f;
        L_0x0124:
            r0 = r12.sourceCodeInfo_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r3.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.sourceCodeInfo_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
        L_0x012f:
            r0 = r12.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r0 | 8;
            r12.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r1;
            r1 = r2;
            goto L_0x002c;
        L_0x0139:
            r0 = r2 & 8;
            if (r0 == r6) goto L_0x02d1;
        L_0x013d:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.publicDependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 | 8;
        L_0x0146:
            r2 = r12.publicDependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = r13.readInt32();	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = java.lang.Integer.valueOf(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x002c;
        L_0x0158:
            r0 = r13.readRawVarint32();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3 = r13.pushLimit(r0);	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 & 8;
            if (r0 == r6) goto L_0x02ce;
        L_0x0164:
            r0 = r13.getBytesUntilLimit();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            if (r0 <= 0) goto L_0x02ce;
        L_0x016a:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.publicDependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 | 8;
        L_0x0173:
            r2 = r13.getBytesUntilLimit();	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            if (r2 <= 0) goto L_0x01f5;
        L_0x0179:
            r2 = r12.publicDependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r5 = r13.readInt32();	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r5 = java.lang.Integer.valueOf(r5);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r2.add(r5);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            goto L_0x0173;
        L_0x0187:
            r1 = move-exception;
            r11 = r1;
            r1 = r0;
            r0 = r11;
            r2 = r1;
        L_0x018c:
            r0 = r0.setUnfinishedMessage(r12);	 Catch:{ all -> 0x0191 }
            throw r0;	 Catch:{ all -> 0x0191 }
        L_0x0191:
            r0 = move-exception;
            r1 = r2;
        L_0x0193:
            r2 = r1 & 4;
            if (r2 != r7) goto L_0x019f;
        L_0x0197:
            r2 = r12.dependency_;
            r2 = r2.getUnmodifiableView();
            r12.dependency_ = r2;
        L_0x019f:
            r2 = r1 & 32;
            if (r2 != r9) goto L_0x01ab;
        L_0x01a3:
            r2 = r12.messageType_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r12.messageType_ = r2;
        L_0x01ab:
            r2 = r1 & 64;
            if (r2 != r10) goto L_0x01b7;
        L_0x01af:
            r2 = r12.enumType_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r12.enumType_ = r2;
        L_0x01b7:
            r2 = r1 & 128;
            r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
            if (r2 != r3) goto L_0x01c5;
        L_0x01bd:
            r2 = r12.service_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r12.service_ = r2;
        L_0x01c5:
            r2 = r1 & 256;
            r3 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
            if (r2 != r3) goto L_0x01d3;
        L_0x01cb:
            r2 = r12.extension_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r12.extension_ = r2;
        L_0x01d3:
            r2 = r1 & 8;
            if (r2 != r6) goto L_0x01df;
        L_0x01d7:
            r2 = r12.publicDependency_;
            r2 = java.util.Collections.unmodifiableList(r2);
            r12.publicDependency_ = r2;
        L_0x01df:
            r1 = r1 & 16;
            if (r1 != r8) goto L_0x01eb;
        L_0x01e3:
            r1 = r12.weakDependency_;
            r1 = java.util.Collections.unmodifiableList(r1);
            r12.weakDependency_ = r1;
        L_0x01eb:
            r1 = r4.build();
            r12.unknownFields = r1;
            r12.makeExtensionsImmutable();
            throw r0;
        L_0x01f5:
            r13.popLimit(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x002c;
        L_0x01fd:
            r0 = r2 & 16;
            if (r0 == r8) goto L_0x02cb;
        L_0x0201:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.weakDependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 | 16;
        L_0x020a:
            r2 = r12.weakDependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = r13.readInt32();	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r3 = java.lang.Integer.valueOf(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r2.add(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x002c;
        L_0x021c:
            r0 = r13.readRawVarint32();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r3 = r13.pushLimit(r0);	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 & 16;
            if (r0 == r8) goto L_0x02c8;
        L_0x0228:
            r0 = r13.getBytesUntilLimit();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            if (r0 <= 0) goto L_0x02c8;
        L_0x022e:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r12.weakDependency_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x02f3, IOException -> 0x02f6 }
            r0 = r2 | 16;
        L_0x0237:
            r2 = r13.getBytesUntilLimit();	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            if (r2 <= 0) goto L_0x025e;
        L_0x023d:
            r2 = r12.weakDependency_;	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r5 = r13.readInt32();	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r5 = java.lang.Integer.valueOf(r5);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r2.add(r5);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            goto L_0x0237;
        L_0x024b:
            r1 = move-exception;
            r11 = r1;
            r1 = r0;
            r0 = r11;
            r2 = r1;
        L_0x0250:
            r1 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x0191 }
            r0 = r0.getMessage();	 Catch:{ all -> 0x0191 }
            r1.<init>(r0);	 Catch:{ all -> 0x0191 }
            r0 = r1.setUnfinishedMessage(r12);	 Catch:{ all -> 0x0191 }
            throw r0;	 Catch:{ all -> 0x0191 }
        L_0x025e:
            r13.popLimit(r3);	 Catch:{ InvalidProtocolBufferException -> 0x0187, IOException -> 0x024b, all -> 0x02f9 }
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x002c;
        L_0x0266:
            r0 = r2 & 4;
            if (r0 != r7) goto L_0x0272;
        L_0x026a:
            r0 = r12.dependency_;
            r0 = r0.getUnmodifiableView();
            r12.dependency_ = r0;
        L_0x0272:
            r0 = r2 & 32;
            if (r0 != r9) goto L_0x027e;
        L_0x0276:
            r0 = r12.messageType_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r12.messageType_ = r0;
        L_0x027e:
            r0 = r2 & 64;
            if (r0 != r10) goto L_0x028a;
        L_0x0282:
            r0 = r12.enumType_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r12.enumType_ = r0;
        L_0x028a:
            r0 = r2 & 128;
            r1 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
            if (r0 != r1) goto L_0x0298;
        L_0x0290:
            r0 = r12.service_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r12.service_ = r0;
        L_0x0298:
            r0 = r2 & 256;
            r1 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
            if (r0 != r1) goto L_0x02a6;
        L_0x029e:
            r0 = r12.extension_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r12.extension_ = r0;
        L_0x02a6:
            r0 = r2 & 8;
            if (r0 != r6) goto L_0x02b2;
        L_0x02aa:
            r0 = r12.publicDependency_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r12.publicDependency_ = r0;
        L_0x02b2:
            r0 = r2 & 16;
            if (r0 != r8) goto L_0x02be;
        L_0x02b6:
            r0 = r12.weakDependency_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r12.weakDependency_ = r0;
        L_0x02be:
            r0 = r4.build();
            r12.unknownFields = r0;
            r12.makeExtensionsImmutable();
            return;
        L_0x02c8:
            r0 = r2;
            goto L_0x0237;
        L_0x02cb:
            r0 = r2;
            goto L_0x020a;
        L_0x02ce:
            r0 = r2;
            goto L_0x0173;
        L_0x02d1:
            r0 = r2;
            goto L_0x0146;
        L_0x02d4:
            r0 = 0;
            r3 = r0;
            goto L_0x0118;
        L_0x02d8:
            r0 = 0;
            r3 = r0;
            goto L_0x00ea;
        L_0x02dc:
            r0 = r2;
            goto L_0x00cd;
        L_0x02df:
            r0 = r2;
            goto L_0x00ae;
        L_0x02e2:
            r0 = r2;
            goto L_0x0090;
        L_0x02e5:
            r0 = r2;
            goto L_0x0074;
        L_0x02e8:
            r0 = r2;
            goto L_0x005e;
        L_0x02eb:
            r0 = r1;
            r1 = r2;
            goto L_0x002c;
        L_0x02ef:
            r0 = 1;
            r1 = r2;
            goto L_0x002c;
        L_0x02f3:
            r0 = move-exception;
            goto L_0x018c;
        L_0x02f6:
            r0 = move-exception;
            goto L_0x0250;
        L_0x02f9:
            r1 = move-exception;
            r11 = r1;
            r1 = r0;
            r0 = r11;
            goto L_0x0193;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.DescriptorProtos.FileDescriptorProto.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
        }

        private FileDescriptorProto(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private FileDescriptorProto(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FileDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
        }

        private void initFields() {
            this.name_ = "";
            this.package_ = "";
            this.dependency_ = LazyStringArrayList.EMPTY;
            this.publicDependency_ = Collections.emptyList();
            this.weakDependency_ = Collections.emptyList();
            this.messageType_ = Collections.emptyList();
            this.enumType_ = Collections.emptyList();
            this.service_ = Collections.emptyList();
            this.extension_ = Collections.emptyList();
            this.options_ = FileOptions.getDefaultInstance();
            this.sourceCodeInfo_ = SourceCodeInfo.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(FileDescriptorProto fileDescriptorProto) {
            return newBuilder().mergeFrom(fileDescriptorProto);
        }

        public static FileDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            return (FileDescriptorProto) PARSER.parseDelimitedFrom(inputStream);
        }

        public static FileDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorProto) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static FileDescriptorProto parseFrom(ByteString byteString) {
            return (FileDescriptorProto) PARSER.parseFrom(byteString);
        }

        public static FileDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FileDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return (FileDescriptorProto) PARSER.parseFrom(codedInputStream);
        }

        public static FileDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorProto) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static FileDescriptorProto parseFrom(InputStream inputStream) {
            return (FileDescriptorProto) PARSER.parseFrom(inputStream);
        }

        public static FileDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorProto) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static FileDescriptorProto parseFrom(byte[] bArr) {
            return (FileDescriptorProto) PARSER.parseFrom(bArr);
        }

        public static FileDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public FileDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getDependency(int i) {
            return (String) this.dependency_.get(i);
        }

        public ByteString getDependencyBytes(int i) {
            return this.dependency_.getByteString(i);
        }

        public int getDependencyCount() {
            return this.dependency_.size();
        }

        public ProtocolStringList getDependencyList() {
            return this.dependency_;
        }

        public EnumDescriptorProto getEnumType(int i) {
            return (EnumDescriptorProto) this.enumType_.get(i);
        }

        public int getEnumTypeCount() {
            return this.enumType_.size();
        }

        public List getEnumTypeList() {
            return this.enumType_;
        }

        public EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int i) {
            return (EnumDescriptorProtoOrBuilder) this.enumType_.get(i);
        }

        public List getEnumTypeOrBuilderList() {
            return this.enumType_;
        }

        public FieldDescriptorProto getExtension(int i) {
            return (FieldDescriptorProto) this.extension_.get(i);
        }

        public int getExtensionCount() {
            return this.extension_.size();
        }

        public List getExtensionList() {
            return this.extension_;
        }

        public FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int i) {
            return (FieldDescriptorProtoOrBuilder) this.extension_.get(i);
        }

        public List getExtensionOrBuilderList() {
            return this.extension_;
        }

        public DescriptorProto getMessageType(int i) {
            return (DescriptorProto) this.messageType_.get(i);
        }

        public int getMessageTypeCount() {
            return this.messageType_.size();
        }

        public List getMessageTypeList() {
            return this.messageType_;
        }

        public DescriptorProtoOrBuilder getMessageTypeOrBuilder(int i) {
            return (DescriptorProtoOrBuilder) this.messageType_.get(i);
        }

        public List getMessageTypeOrBuilderList() {
            return this.messageType_;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public FileOptions getOptions() {
            return this.options_;
        }

        public FileOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        public String getPackage() {
            Object obj = this.package_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.package_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getPackageBytes() {
            Object obj = this.package_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.package_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getPublicDependency(int i) {
            return ((Integer) this.publicDependency_.get(i)).intValue();
        }

        public int getPublicDependencyCount() {
            return this.publicDependency_.size();
        }

        public List getPublicDependencyList() {
            return this.publicDependency_;
        }

        public int getSerializedSize() {
            int i = 0;
            int i2 = this.memoizedSerializedSize;
            if (i2 != -1) {
                return i2;
            }
            int i3;
            i2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getNameBytes()) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, getPackageBytes());
            }
            int i4 = 0;
            for (i3 = 0; i3 < this.dependency_.size(); i3++) {
                i4 += CodedOutputStream.computeBytesSizeNoTag(this.dependency_.getByteString(i3));
            }
            i4 = (i2 + i4) + (getDependencyList().size() * 1);
            i3 = 0;
            while (i3 < this.messageType_.size()) {
                i2 = CodedOutputStream.computeMessageSize(4, (MessageLite) this.messageType_.get(i3)) + i4;
                i3++;
                i4 = i2;
            }
            i3 = i4;
            for (i4 = 0; i4 < this.enumType_.size(); i4++) {
                i3 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.enumType_.get(i4));
            }
            for (i4 = 0; i4 < this.service_.size(); i4++) {
                i3 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.service_.get(i4));
            }
            for (i4 = 0; i4 < this.extension_.size(); i4++) {
                i3 += CodedOutputStream.computeMessageSize(7, (MessageLite) this.extension_.get(i4));
            }
            if ((this.bitField0_ & 4) == 4) {
                i3 += CodedOutputStream.computeMessageSize(8, this.options_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i3 += CodedOutputStream.computeMessageSize(9, this.sourceCodeInfo_);
            }
            i4 = 0;
            int i5 = 0;
            while (i5 < this.publicDependency_.size()) {
                i2 = CodedOutputStream.computeInt32SizeNoTag(((Integer) this.publicDependency_.get(i5)).intValue()) + i4;
                i5++;
                i4 = i2;
            }
            i3 = (i3 + i4) + (getPublicDependencyList().size() * 1);
            for (i4 = 0; i4 < this.weakDependency_.size(); i4++) {
                i += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.weakDependency_.get(i4)).intValue());
            }
            i2 = ((i3 + i) + (getWeakDependencyList().size() * 1)) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public ServiceDescriptorProto getService(int i) {
            return (ServiceDescriptorProto) this.service_.get(i);
        }

        public int getServiceCount() {
            return this.service_.size();
        }

        public List getServiceList() {
            return this.service_;
        }

        public ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int i) {
            return (ServiceDescriptorProtoOrBuilder) this.service_.get(i);
        }

        public List getServiceOrBuilderList() {
            return this.service_;
        }

        public SourceCodeInfo getSourceCodeInfo() {
            return this.sourceCodeInfo_;
        }

        public SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder() {
            return this.sourceCodeInfo_;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public int getWeakDependency(int i) {
            return ((Integer) this.weakDependency_.get(i)).intValue();
        }

        public int getWeakDependencyCount() {
            return this.weakDependency_.size();
        }

        public List getWeakDependencyList() {
            return this.weakDependency_;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasPackage() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasSourceCodeInfo() {
            return (this.bitField0_ & 8) == 8;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f214x4b52780c.ensureFieldAccessorsInitialized(FileDescriptorProto.class, Builder.class);
        }

        public final boolean isInitialized() {
            boolean z = true;
            byte b = this.memoizedIsInitialized;
            if (b != (byte) 1) {
                if (b != (byte) 0) {
                    int i = 0;
                    while (i < getMessageTypeCount()) {
                        if (getMessageType(i).isInitialized()) {
                            i++;
                        } else {
                            this.memoizedIsInitialized = (byte) 0;
                            return false;
                        }
                    }
                    i = 0;
                    while (i < getEnumTypeCount()) {
                        if (getEnumType(i).isInitialized()) {
                            i++;
                        } else {
                            this.memoizedIsInitialized = (byte) 0;
                            return false;
                        }
                    }
                    i = 0;
                    while (i < getServiceCount()) {
                        if (getService(i).isInitialized()) {
                            i++;
                        } else {
                            this.memoizedIsInitialized = (byte) 0;
                            return false;
                        }
                    }
                    i = 0;
                    while (i < getExtensionCount()) {
                        if (getExtension(i).isInitialized()) {
                            i++;
                        } else {
                            this.memoizedIsInitialized = (byte) 0;
                            return false;
                        }
                    }
                    if (!hasOptions() || getOptions().isInitialized()) {
                        this.memoizedIsInitialized = (byte) 1;
                        return true;
                    }
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
                z = false;
            }
            return z;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            int i;
            int i2 = 0;
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, getPackageBytes());
            }
            for (int i3 = 0; i3 < this.dependency_.size(); i3++) {
                codedOutputStream.writeBytes(3, this.dependency_.getByteString(i3));
            }
            for (i = 0; i < this.messageType_.size(); i++) {
                codedOutputStream.writeMessage(4, (MessageLite) this.messageType_.get(i));
            }
            for (i = 0; i < this.enumType_.size(); i++) {
                codedOutputStream.writeMessage(5, (MessageLite) this.enumType_.get(i));
            }
            for (i = 0; i < this.service_.size(); i++) {
                codedOutputStream.writeMessage(6, (MessageLite) this.service_.get(i));
            }
            for (i = 0; i < this.extension_.size(); i++) {
                codedOutputStream.writeMessage(7, (MessageLite) this.extension_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(8, this.options_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeMessage(9, this.sourceCodeInfo_);
            }
            for (i = 0; i < this.publicDependency_.size(); i++) {
                codedOutputStream.writeInt32(10, ((Integer) this.publicDependency_.get(i)).intValue());
            }
            while (i2 < this.weakDependency_.size()) {
                codedOutputStream.writeInt32(11, ((Integer) this.weakDependency_.get(i2)).intValue());
                i2++;
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface FileDescriptorSetOrBuilder extends MessageOrBuilder {
        FileDescriptorProto getFile(int i);

        int getFileCount();

        List getFileList();

        FileDescriptorProtoOrBuilder getFileOrBuilder(int i);

        List getFileOrBuilderList();
    }

    public final class FileDescriptorSet extends GeneratedMessage implements FileDescriptorSetOrBuilder {
        public static final int FILE_FIELD_NUMBER = 1;
        public static Parser PARSER = new C19751();
        private static final FileDescriptorSet defaultInstance = new FileDescriptorSet(true);
        private static final long serialVersionUID = 0;
        private List file_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        final class C19751 extends AbstractParser {
            C19751() {
            }

            public FileDescriptorSet parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new FileDescriptorSet(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements FileDescriptorSetOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilder fileBuilder_;
            private List file_;

            private Builder() {
                this.file_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.file_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureFileIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.file_ = new ArrayList(this.file_);
                    this.bitField0_ |= 1;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor;
            }

            private RepeatedFieldBuilder getFileFieldBuilder() {
                boolean z = true;
                if (this.fileBuilder_ == null) {
                    List list = this.file_;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.fileBuilder_ = new RepeatedFieldBuilder(list, z, getParentForChildren(), isClean());
                    this.file_ = null;
                }
                return this.fileBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getFileFieldBuilder();
                }
            }

            public Builder addAllFile(Iterable iterable) {
                if (this.fileBuilder_ == null) {
                    ensureFileIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.file_);
                    onChanged();
                } else {
                    this.fileBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addFile(int i, Builder builder) {
                if (this.fileBuilder_ == null) {
                    ensureFileIsMutable();
                    this.file_.add(i, builder.build());
                    onChanged();
                } else {
                    this.fileBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addFile(int i, FileDescriptorProto fileDescriptorProto) {
                if (this.fileBuilder_ != null) {
                    this.fileBuilder_.addMessage(i, fileDescriptorProto);
                } else if (fileDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureFileIsMutable();
                    this.file_.add(i, fileDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addFile(Builder builder) {
                if (this.fileBuilder_ == null) {
                    ensureFileIsMutable();
                    this.file_.add(builder.build());
                    onChanged();
                } else {
                    this.fileBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addFile(FileDescriptorProto fileDescriptorProto) {
                if (this.fileBuilder_ != null) {
                    this.fileBuilder_.addMessage(fileDescriptorProto);
                } else if (fileDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureFileIsMutable();
                    this.file_.add(fileDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addFileBuilder() {
                return (Builder) getFileFieldBuilder().addBuilder(FileDescriptorProto.getDefaultInstance());
            }

            public Builder addFileBuilder(int i) {
                return (Builder) getFileFieldBuilder().addBuilder(i, FileDescriptorProto.getDefaultInstance());
            }

            public FileDescriptorSet build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public FileDescriptorSet buildPartial() {
                FileDescriptorSet fileDescriptorSet = new FileDescriptorSet((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                if (this.fileBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.file_ = Collections.unmodifiableList(this.file_);
                        this.bitField0_ &= -2;
                    }
                    fileDescriptorSet.file_ = this.file_;
                } else {
                    fileDescriptorSet.file_ = this.fileBuilder_.build();
                }
                onBuilt();
                return fileDescriptorSet;
            }

            public Builder clear() {
                super.clear();
                if (this.fileBuilder_ == null) {
                    this.file_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    this.fileBuilder_.clear();
                }
                return this;
            }

            public Builder clearFile() {
                if (this.fileBuilder_ == null) {
                    this.file_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    this.fileBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public FileDescriptorSet getDefaultInstanceForType() {
                return FileDescriptorSet.getDefaultInstance();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor;
            }

            public FileDescriptorProto getFile(int i) {
                return this.fileBuilder_ == null ? (FileDescriptorProto) this.file_.get(i) : (FileDescriptorProto) this.fileBuilder_.getMessage(i);
            }

            public Builder getFileBuilder(int i) {
                return (Builder) getFileFieldBuilder().getBuilder(i);
            }

            public List getFileBuilderList() {
                return getFileFieldBuilder().getBuilderList();
            }

            public int getFileCount() {
                return this.fileBuilder_ == null ? this.file_.size() : this.fileBuilder_.getCount();
            }

            public List getFileList() {
                return this.fileBuilder_ == null ? Collections.unmodifiableList(this.file_) : this.fileBuilder_.getMessageList();
            }

            public FileDescriptorProtoOrBuilder getFileOrBuilder(int i) {
                return this.fileBuilder_ == null ? (FileDescriptorProtoOrBuilder) this.file_.get(i) : (FileDescriptorProtoOrBuilder) this.fileBuilder_.getMessageOrBuilder(i);
            }

            public List getFileOrBuilderList() {
                return this.fileBuilder_ != null ? this.fileBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.file_);
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f215x15a6a952.ensureFieldAccessorsInitialized(FileDescriptorSet.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getFileCount(); i++) {
                    if (!getFile(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                FileDescriptorSet fileDescriptorSet;
                Throwable th;
                try {
                    fileDescriptorSet = (FileDescriptorSet) FileDescriptorSet.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (fileDescriptorSet != null) {
                        mergeFrom(fileDescriptorSet);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    fileDescriptorSet = (FileDescriptorSet) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (fileDescriptorSet != null) {
                    mergeFrom(fileDescriptorSet);
                }
                throw th;
            }

            public Builder mergeFrom(FileDescriptorSet fileDescriptorSet) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (fileDescriptorSet != FileDescriptorSet.getDefaultInstance()) {
                    if (this.fileBuilder_ == null) {
                        if (!fileDescriptorSet.file_.isEmpty()) {
                            if (this.file_.isEmpty()) {
                                this.file_ = fileDescriptorSet.file_;
                                this.bitField0_ &= -2;
                            } else {
                                ensureFileIsMutable();
                                this.file_.addAll(fileDescriptorSet.file_);
                            }
                            onChanged();
                        }
                    } else if (!fileDescriptorSet.file_.isEmpty()) {
                        if (this.fileBuilder_.isEmpty()) {
                            this.fileBuilder_.dispose();
                            this.fileBuilder_ = null;
                            this.file_ = fileDescriptorSet.file_;
                            this.bitField0_ &= -2;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getFileFieldBuilder();
                            }
                            this.fileBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.fileBuilder_.addAllMessages(fileDescriptorSet.file_);
                        }
                    }
                    mergeUnknownFields(fileDescriptorSet.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof FileDescriptorSet) {
                    return mergeFrom((FileDescriptorSet) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeFile(int i) {
                if (this.fileBuilder_ == null) {
                    ensureFileIsMutable();
                    this.file_.remove(i);
                    onChanged();
                } else {
                    this.fileBuilder_.remove(i);
                }
                return this;
            }

            public Builder setFile(int i, Builder builder) {
                if (this.fileBuilder_ == null) {
                    ensureFileIsMutable();
                    this.file_.set(i, builder.build());
                    onChanged();
                } else {
                    this.fileBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setFile(int i, FileDescriptorProto fileDescriptorProto) {
                if (this.fileBuilder_ != null) {
                    this.fileBuilder_.setMessage(i, fileDescriptorProto);
                } else if (fileDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureFileIsMutable();
                    this.file_.set(i, fileDescriptorProto);
                    onChanged();
                }
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        private FileDescriptorSet(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 10:
                            if ((i & 1) != 1) {
                                this.file_ = new ArrayList();
                                i |= 1;
                            }
                            this.file_.add(codedInputStream.readMessage(FileDescriptorProto.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 1) == 1) {
                        this.file_ = Collections.unmodifiableList(this.file_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 1) == 1) {
                this.file_ = Collections.unmodifiableList(this.file_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private FileDescriptorSet(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private FileDescriptorSet(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FileDescriptorSet getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor;
        }

        private void initFields() {
            this.file_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(FileDescriptorSet fileDescriptorSet) {
            return newBuilder().mergeFrom(fileDescriptorSet);
        }

        public static FileDescriptorSet parseDelimitedFrom(InputStream inputStream) {
            return (FileDescriptorSet) PARSER.parseDelimitedFrom(inputStream);
        }

        public static FileDescriptorSet parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorSet) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static FileDescriptorSet parseFrom(ByteString byteString) {
            return (FileDescriptorSet) PARSER.parseFrom(byteString);
        }

        public static FileDescriptorSet parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorSet) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FileDescriptorSet parseFrom(CodedInputStream codedInputStream) {
            return (FileDescriptorSet) PARSER.parseFrom(codedInputStream);
        }

        public static FileDescriptorSet parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorSet) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static FileDescriptorSet parseFrom(InputStream inputStream) {
            return (FileDescriptorSet) PARSER.parseFrom(inputStream);
        }

        public static FileDescriptorSet parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorSet) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static FileDescriptorSet parseFrom(byte[] bArr) {
            return (FileDescriptorSet) PARSER.parseFrom(bArr);
        }

        public static FileDescriptorSet parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (FileDescriptorSet) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public FileDescriptorSet getDefaultInstanceForType() {
            return defaultInstance;
        }

        public FileDescriptorProto getFile(int i) {
            return (FileDescriptorProto) this.file_.get(i);
        }

        public int getFileCount() {
            return this.file_.size();
        }

        public List getFileList() {
            return this.file_;
        }

        public FileDescriptorProtoOrBuilder getFileOrBuilder(int i) {
            return (FileDescriptorProtoOrBuilder) this.file_.get(i);
        }

        public List getFileOrBuilderList() {
            return this.file_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize;
            i = 0;
            int i2 = 0;
            while (i2 < this.file_.size()) {
                computeMessageSize = CodedOutputStream.computeMessageSize(1, (MessageLite) this.file_.get(i2)) + i;
                i2++;
                i = computeMessageSize;
            }
            computeMessageSize = getUnknownFields().getSerializedSize() + i;
            this.memoizedSerializedSize = computeMessageSize;
            return computeMessageSize;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f215x15a6a952.ensureFieldAccessorsInitialized(FileDescriptorSet.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getFileCount()) {
                if (getFile(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            for (int i = 0; i < this.file_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.file_.get(i));
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface FileOptionsOrBuilder extends ExtendableMessageOrBuilder {
        boolean getCcGenericServices();

        boolean getDeprecated();

        String getGoPackage();

        ByteString getGoPackageBytes();

        boolean getJavaGenerateEqualsAndHash();

        boolean getJavaGenericServices();

        boolean getJavaMultipleFiles();

        String getJavaOuterClassname();

        ByteString getJavaOuterClassnameBytes();

        String getJavaPackage();

        ByteString getJavaPackageBytes();

        boolean getJavaStringCheckUtf8();

        OptimizeMode getOptimizeFor();

        boolean getPyGenericServices();

        UninterpretedOption getUninterpretedOption(int i);

        int getUninterpretedOptionCount();

        List getUninterpretedOptionList();

        UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i);

        List getUninterpretedOptionOrBuilderList();

        boolean hasCcGenericServices();

        boolean hasDeprecated();

        boolean hasGoPackage();

        boolean hasJavaGenerateEqualsAndHash();

        boolean hasJavaGenericServices();

        boolean hasJavaMultipleFiles();

        boolean hasJavaOuterClassname();

        boolean hasJavaPackage();

        boolean hasJavaStringCheckUtf8();

        boolean hasOptimizeFor();

        boolean hasPyGenericServices();
    }

    public final class FileOptions extends ExtendableMessage implements FileOptionsOrBuilder {
        public static final int CC_GENERIC_SERVICES_FIELD_NUMBER = 16;
        public static final int DEPRECATED_FIELD_NUMBER = 23;
        public static final int GO_PACKAGE_FIELD_NUMBER = 11;
        public static final int JAVA_GENERATE_EQUALS_AND_HASH_FIELD_NUMBER = 20;
        public static final int JAVA_GENERIC_SERVICES_FIELD_NUMBER = 17;
        public static final int JAVA_MULTIPLE_FILES_FIELD_NUMBER = 10;
        public static final int JAVA_OUTER_CLASSNAME_FIELD_NUMBER = 8;
        public static final int JAVA_PACKAGE_FIELD_NUMBER = 1;
        public static final int JAVA_STRING_CHECK_UTF8_FIELD_NUMBER = 27;
        public static final int OPTIMIZE_FOR_FIELD_NUMBER = 9;
        public static Parser PARSER = new C19761();
        public static final int PY_GENERIC_SERVICES_FIELD_NUMBER = 18;
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final FileOptions defaultInstance = new FileOptions(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean ccGenericServices_;
        private boolean deprecated_;
        private Object goPackage_;
        private boolean javaGenerateEqualsAndHash_;
        private boolean javaGenericServices_;
        private boolean javaMultipleFiles_;
        private Object javaOuterClassname_;
        private Object javaPackage_;
        private boolean javaStringCheckUtf8_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private OptimizeMode optimizeFor_;
        private boolean pyGenericServices_;
        private List uninterpretedOption_;
        private final UnknownFieldSet unknownFields;

        final class C19761 extends AbstractParser {
            C19761() {
            }

            public FileOptions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new FileOptions(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends ExtendableBuilder implements FileOptionsOrBuilder {
            private int bitField0_;
            private boolean ccGenericServices_;
            private boolean deprecated_;
            private Object goPackage_;
            private boolean javaGenerateEqualsAndHash_;
            private boolean javaGenericServices_;
            private boolean javaMultipleFiles_;
            private Object javaOuterClassname_;
            private Object javaPackage_;
            private boolean javaStringCheckUtf8_;
            private OptimizeMode optimizeFor_;
            private boolean pyGenericServices_;
            private RepeatedFieldBuilder uninterpretedOptionBuilder_;
            private List uninterpretedOption_;

            private Builder() {
                this.javaPackage_ = "";
                this.javaOuterClassname_ = "";
                this.optimizeFor_ = OptimizeMode.SPEED;
                this.goPackage_ = "";
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.javaPackage_ = "";
                this.javaOuterClassname_ = "";
                this.optimizeFor_ = OptimizeMode.SPEED;
                this.goPackage_ = "";
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 2048) != 2048) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 2048;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor;
            }

            private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 2048) == 2048, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getUninterpretedOptionFieldBuilder();
                }
            }

            public Builder addAllUninterpretedOption(Iterable iterable) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.uninterpretedOption_);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOption(Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOptionBuilder() {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public Builder addUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(i, UninterpretedOption.getDefaultInstance());
            }

            public FileOptions build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public FileOptions buildPartial() {
                int i = 1;
                FileOptions fileOptions = new FileOptions((ExtendableBuilder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                fileOptions.javaPackage_ = this.javaPackage_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                fileOptions.javaOuterClassname_ = this.javaOuterClassname_;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                fileOptions.javaMultipleFiles_ = this.javaMultipleFiles_;
                if ((i2 & 8) == 8) {
                    i |= 8;
                }
                fileOptions.javaGenerateEqualsAndHash_ = this.javaGenerateEqualsAndHash_;
                if ((i2 & 16) == 16) {
                    i |= 16;
                }
                fileOptions.javaStringCheckUtf8_ = this.javaStringCheckUtf8_;
                if ((i2 & 32) == 32) {
                    i |= 32;
                }
                fileOptions.optimizeFor_ = this.optimizeFor_;
                if ((i2 & 64) == 64) {
                    i |= 64;
                }
                fileOptions.goPackage_ = this.goPackage_;
                if ((i2 & 128) == 128) {
                    i |= 128;
                }
                fileOptions.ccGenericServices_ = this.ccGenericServices_;
                if ((i2 & 256) == 256) {
                    i |= 256;
                }
                fileOptions.javaGenericServices_ = this.javaGenericServices_;
                if ((i2 & 512) == 512) {
                    i |= 512;
                }
                fileOptions.pyGenericServices_ = this.pyGenericServices_;
                if ((i2 & 1024) == 1024) {
                    i |= 1024;
                }
                fileOptions.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 2048) == 2048) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= -2049;
                    }
                    fileOptions.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    fileOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                fileOptions.bitField0_ = i;
                onBuilt();
                return fileOptions;
            }

            public Builder clear() {
                super.clear();
                this.javaPackage_ = "";
                this.bitField0_ &= -2;
                this.javaOuterClassname_ = "";
                this.bitField0_ &= -3;
                this.javaMultipleFiles_ = false;
                this.bitField0_ &= -5;
                this.javaGenerateEqualsAndHash_ = false;
                this.bitField0_ &= -9;
                this.javaStringCheckUtf8_ = false;
                this.bitField0_ &= -17;
                this.optimizeFor_ = OptimizeMode.SPEED;
                this.bitField0_ &= -33;
                this.goPackage_ = "";
                this.bitField0_ &= -65;
                this.ccGenericServices_ = false;
                this.bitField0_ &= -129;
                this.javaGenericServices_ = false;
                this.bitField0_ &= -257;
                this.pyGenericServices_ = false;
                this.bitField0_ &= -513;
                this.deprecated_ = false;
                this.bitField0_ &= -1025;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -2049;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clearCcGenericServices() {
                this.bitField0_ &= -129;
                this.ccGenericServices_ = false;
                onChanged();
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= -1025;
                this.deprecated_ = false;
                onChanged();
                return this;
            }

            public Builder clearGoPackage() {
                this.bitField0_ &= -65;
                this.goPackage_ = FileOptions.getDefaultInstance().getGoPackage();
                onChanged();
                return this;
            }

            public Builder clearJavaGenerateEqualsAndHash() {
                this.bitField0_ &= -9;
                this.javaGenerateEqualsAndHash_ = false;
                onChanged();
                return this;
            }

            public Builder clearJavaGenericServices() {
                this.bitField0_ &= -257;
                this.javaGenericServices_ = false;
                onChanged();
                return this;
            }

            public Builder clearJavaMultipleFiles() {
                this.bitField0_ &= -5;
                this.javaMultipleFiles_ = false;
                onChanged();
                return this;
            }

            public Builder clearJavaOuterClassname() {
                this.bitField0_ &= -3;
                this.javaOuterClassname_ = FileOptions.getDefaultInstance().getJavaOuterClassname();
                onChanged();
                return this;
            }

            public Builder clearJavaPackage() {
                this.bitField0_ &= -2;
                this.javaPackage_ = FileOptions.getDefaultInstance().getJavaPackage();
                onChanged();
                return this;
            }

            public Builder clearJavaStringCheckUtf8() {
                this.bitField0_ &= -17;
                this.javaStringCheckUtf8_ = false;
                onChanged();
                return this;
            }

            public Builder clearOptimizeFor() {
                this.bitField0_ &= -33;
                this.optimizeFor_ = OptimizeMode.SPEED;
                onChanged();
                return this;
            }

            public Builder clearPyGenericServices() {
                this.bitField0_ &= -513;
                this.pyGenericServices_ = false;
                onChanged();
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -2049;
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public boolean getCcGenericServices() {
                return this.ccGenericServices_;
            }

            public FileOptions getDefaultInstanceForType() {
                return FileOptions.getDefaultInstance();
            }

            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor;
            }

            public String getGoPackage() {
                Object obj = this.goPackage_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.goPackage_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getGoPackageBytes() {
                Object obj = this.goPackage_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.goPackage_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean getJavaGenerateEqualsAndHash() {
                return this.javaGenerateEqualsAndHash_;
            }

            public boolean getJavaGenericServices() {
                return this.javaGenericServices_;
            }

            public boolean getJavaMultipleFiles() {
                return this.javaMultipleFiles_;
            }

            public String getJavaOuterClassname() {
                Object obj = this.javaOuterClassname_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.javaOuterClassname_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getJavaOuterClassnameBytes() {
                Object obj = this.javaOuterClassname_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.javaOuterClassname_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getJavaPackage() {
                Object obj = this.javaPackage_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.javaPackage_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getJavaPackageBytes() {
                Object obj = this.javaPackage_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.javaPackage_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean getJavaStringCheckUtf8() {
                return this.javaStringCheckUtf8_;
            }

            public OptimizeMode getOptimizeFor() {
                return this.optimizeFor_;
            }

            public boolean getPyGenericServices() {
                return this.pyGenericServices_;
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOption) this.uninterpretedOption_.get(i) : (UninterpretedOption) this.uninterpretedOptionBuilder_.getMessage(i);
            }

            public Builder getUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().getBuilder(i);
            }

            public List getUninterpretedOptionBuilderList() {
                return getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            public int getUninterpretedOptionCount() {
                return this.uninterpretedOptionBuilder_ == null ? this.uninterpretedOption_.size() : this.uninterpretedOptionBuilder_.getCount();
            }

            public List getUninterpretedOptionList() {
                return this.uninterpretedOptionBuilder_ == null ? Collections.unmodifiableList(this.uninterpretedOption_) : this.uninterpretedOptionBuilder_.getMessageList();
            }

            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i) : (UninterpretedOptionOrBuilder) this.uninterpretedOptionBuilder_.getMessageOrBuilder(i);
            }

            public List getUninterpretedOptionOrBuilderList() {
                return this.uninterpretedOptionBuilder_ != null ? this.uninterpretedOptionBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public boolean hasCcGenericServices() {
                return (this.bitField0_ & 128) == 128;
            }

            public boolean hasDeprecated() {
                return (this.bitField0_ & 1024) == 1024;
            }

            public boolean hasGoPackage() {
                return (this.bitField0_ & 64) == 64;
            }

            public boolean hasJavaGenerateEqualsAndHash() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasJavaGenericServices() {
                return (this.bitField0_ & 256) == 256;
            }

            public boolean hasJavaMultipleFiles() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasJavaOuterClassname() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasJavaPackage() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasJavaStringCheckUtf8() {
                return (this.bitField0_ & 16) == 16;
            }

            public boolean hasOptimizeFor() {
                return (this.bitField0_ & 32) == 32;
            }

            public boolean hasPyGenericServices() {
                return (this.bitField0_ & 512) == 512;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_FileOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FileOptions.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                FileOptions fileOptions;
                Throwable th;
                try {
                    fileOptions = (FileOptions) FileOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (fileOptions != null) {
                        mergeFrom(fileOptions);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    fileOptions = (FileOptions) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (fileOptions != null) {
                    mergeFrom(fileOptions);
                }
                throw th;
            }

            public Builder mergeFrom(FileOptions fileOptions) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (fileOptions != FileOptions.getDefaultInstance()) {
                    if (fileOptions.hasJavaPackage()) {
                        this.bitField0_ |= 1;
                        this.javaPackage_ = fileOptions.javaPackage_;
                        onChanged();
                    }
                    if (fileOptions.hasJavaOuterClassname()) {
                        this.bitField0_ |= 2;
                        this.javaOuterClassname_ = fileOptions.javaOuterClassname_;
                        onChanged();
                    }
                    if (fileOptions.hasJavaMultipleFiles()) {
                        setJavaMultipleFiles(fileOptions.getJavaMultipleFiles());
                    }
                    if (fileOptions.hasJavaGenerateEqualsAndHash()) {
                        setJavaGenerateEqualsAndHash(fileOptions.getJavaGenerateEqualsAndHash());
                    }
                    if (fileOptions.hasJavaStringCheckUtf8()) {
                        setJavaStringCheckUtf8(fileOptions.getJavaStringCheckUtf8());
                    }
                    if (fileOptions.hasOptimizeFor()) {
                        setOptimizeFor(fileOptions.getOptimizeFor());
                    }
                    if (fileOptions.hasGoPackage()) {
                        this.bitField0_ |= 64;
                        this.goPackage_ = fileOptions.goPackage_;
                        onChanged();
                    }
                    if (fileOptions.hasCcGenericServices()) {
                        setCcGenericServices(fileOptions.getCcGenericServices());
                    }
                    if (fileOptions.hasJavaGenericServices()) {
                        setJavaGenericServices(fileOptions.getJavaGenericServices());
                    }
                    if (fileOptions.hasPyGenericServices()) {
                        setPyGenericServices(fileOptions.getPyGenericServices());
                    }
                    if (fileOptions.hasDeprecated()) {
                        setDeprecated(fileOptions.getDeprecated());
                    }
                    if (this.uninterpretedOptionBuilder_ == null) {
                        if (!fileOptions.uninterpretedOption_.isEmpty()) {
                            if (this.uninterpretedOption_.isEmpty()) {
                                this.uninterpretedOption_ = fileOptions.uninterpretedOption_;
                                this.bitField0_ &= -2049;
                            } else {
                                ensureUninterpretedOptionIsMutable();
                                this.uninterpretedOption_.addAll(fileOptions.uninterpretedOption_);
                            }
                            onChanged();
                        }
                    } else if (!fileOptions.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOptionBuilder_.isEmpty()) {
                            this.uninterpretedOptionBuilder_.dispose();
                            this.uninterpretedOptionBuilder_ = null;
                            this.uninterpretedOption_ = fileOptions.uninterpretedOption_;
                            this.bitField0_ &= -2049;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getUninterpretedOptionFieldBuilder();
                            }
                            this.uninterpretedOptionBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.uninterpretedOptionBuilder_.addAllMessages(fileOptions.uninterpretedOption_);
                        }
                    }
                    mergeExtensionFields(fileOptions);
                    mergeUnknownFields(fileOptions.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof FileOptions) {
                    return mergeFrom((FileOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeUninterpretedOption(int i) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(i);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(i);
                }
                return this;
            }

            public Builder setCcGenericServices(boolean z) {
                this.bitField0_ |= 128;
                this.ccGenericServices_ = z;
                onChanged();
                return this;
            }

            public Builder setDeprecated(boolean z) {
                this.bitField0_ |= 1024;
                this.deprecated_ = z;
                onChanged();
                return this;
            }

            public Builder setGoPackage(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.goPackage_ = str;
                onChanged();
                return this;
            }

            public Builder setGoPackageBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.goPackage_ = byteString;
                onChanged();
                return this;
            }

            public Builder setJavaGenerateEqualsAndHash(boolean z) {
                this.bitField0_ |= 8;
                this.javaGenerateEqualsAndHash_ = z;
                onChanged();
                return this;
            }

            public Builder setJavaGenericServices(boolean z) {
                this.bitField0_ |= 256;
                this.javaGenericServices_ = z;
                onChanged();
                return this;
            }

            public Builder setJavaMultipleFiles(boolean z) {
                this.bitField0_ |= 4;
                this.javaMultipleFiles_ = z;
                onChanged();
                return this;
            }

            public Builder setJavaOuterClassname(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.javaOuterClassname_ = str;
                onChanged();
                return this;
            }

            public Builder setJavaOuterClassnameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.javaOuterClassname_ = byteString;
                onChanged();
                return this;
            }

            public Builder setJavaPackage(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.javaPackage_ = str;
                onChanged();
                return this;
            }

            public Builder setJavaPackageBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.javaPackage_ = byteString;
                onChanged();
                return this;
            }

            public Builder setJavaStringCheckUtf8(boolean z) {
                this.bitField0_ |= 16;
                this.javaStringCheckUtf8_ = z;
                onChanged();
                return this;
            }

            public Builder setOptimizeFor(OptimizeMode optimizeMode) {
                if (optimizeMode == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.optimizeFor_ = optimizeMode;
                onChanged();
                return this;
            }

            public Builder setPyGenericServices(boolean z) {
                this.bitField0_ |= 512;
                this.pyGenericServices_ = z;
                onChanged();
                return this;
            }

            public Builder setUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.setMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }
        }

        public enum OptimizeMode implements ProtocolMessageEnum {
            SPEED(0, 1),
            CODE_SIZE(1, 2),
            LITE_RUNTIME(2, 3);
            
            public static final int CODE_SIZE_VALUE = 2;
            public static final int LITE_RUNTIME_VALUE = 3;
            public static final int SPEED_VALUE = 1;
            private static final OptimizeMode[] VALUES = null;
            private static EnumLiteMap internalValueMap;
            private final int index;
            private final int value;

            final class C19771 implements EnumLiteMap {
                C19771() {
                }

                public OptimizeMode findValueByNumber(int i) {
                    return OptimizeMode.valueOf(i);
                }
            }

            static {
                internalValueMap = new C19771();
                VALUES = values();
            }

            private OptimizeMode(int i, int i2) {
                this.index = i;
                this.value = i2;
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) FileOptions.getDescriptor().getEnumTypes().get(0);
            }

            public static EnumLiteMap internalGetValueMap() {
                return internalValueMap;
            }

            public static OptimizeMode valueOf(int i) {
                switch (i) {
                    case 1:
                        return SPEED;
                    case 2:
                        return CODE_SIZE;
                    case 3:
                        return LITE_RUNTIME;
                    default:
                        return null;
                }
            }

            public static OptimizeMode valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public final int getNumber() {
                return this.value;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }
        }

        static {
            defaultInstance.initFields();
        }

        private FileOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    ByteString readBytes;
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 10:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.javaPackage_ = readBytes;
                            break;
                        case 66:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 2;
                            this.javaOuterClassname_ = readBytes;
                            break;
                        case 72:
                            readTag = codedInputStream.readEnum();
                            OptimizeMode valueOf = OptimizeMode.valueOf(readTag);
                            if (valueOf != null) {
                                this.bitField0_ |= 32;
                                this.optimizeFor_ = valueOf;
                                break;
                            }
                            newBuilder.mergeVarintField(9, readTag);
                            break;
                        case 80:
                            this.bitField0_ |= 4;
                            this.javaMultipleFiles_ = codedInputStream.readBool();
                            break;
                        case 90:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 64;
                            this.goPackage_ = readBytes;
                            break;
                        case 128:
                            this.bitField0_ |= 128;
                            this.ccGenericServices_ = codedInputStream.readBool();
                            break;
                        case 136:
                            this.bitField0_ |= 256;
                            this.javaGenericServices_ = codedInputStream.readBool();
                            break;
                        case 144:
                            this.bitField0_ |= 512;
                            this.pyGenericServices_ = codedInputStream.readBool();
                            break;
                        case 160:
                            this.bitField0_ |= 8;
                            this.javaGenerateEqualsAndHash_ = codedInputStream.readBool();
                            break;
                        case 184:
                            this.bitField0_ |= 1024;
                            this.deprecated_ = codedInputStream.readBool();
                            break;
                        case JfifUtil.MARKER_SOI /*216*/:
                            this.bitField0_ |= 16;
                            this.javaStringCheckUtf8_ = codedInputStream.readBool();
                            break;
                        case 7994:
                            if ((i & 2048) != 2048) {
                                this.uninterpretedOption_ = new ArrayList();
                                i |= 2048;
                            }
                            this.uninterpretedOption_.add(codedInputStream.readMessage(UninterpretedOption.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 2048) == 2048) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 2048) == 2048) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private FileOptions(ExtendableBuilder extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private FileOptions(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static FileOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor;
        }

        private void initFields() {
            this.javaPackage_ = "";
            this.javaOuterClassname_ = "";
            this.javaMultipleFiles_ = false;
            this.javaGenerateEqualsAndHash_ = false;
            this.javaStringCheckUtf8_ = false;
            this.optimizeFor_ = OptimizeMode.SPEED;
            this.goPackage_ = "";
            this.ccGenericServices_ = false;
            this.javaGenericServices_ = false;
            this.pyGenericServices_ = false;
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(FileOptions fileOptions) {
            return newBuilder().mergeFrom(fileOptions);
        }

        public static FileOptions parseDelimitedFrom(InputStream inputStream) {
            return (FileOptions) PARSER.parseDelimitedFrom(inputStream);
        }

        public static FileOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FileOptions) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static FileOptions parseFrom(ByteString byteString) {
            return (FileOptions) PARSER.parseFrom(byteString);
        }

        public static FileOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (FileOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FileOptions parseFrom(CodedInputStream codedInputStream) {
            return (FileOptions) PARSER.parseFrom(codedInputStream);
        }

        public static FileOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FileOptions) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static FileOptions parseFrom(InputStream inputStream) {
            return (FileOptions) PARSER.parseFrom(inputStream);
        }

        public static FileOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (FileOptions) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static FileOptions parseFrom(byte[] bArr) {
            return (FileOptions) PARSER.parseFrom(bArr);
        }

        public static FileOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (FileOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public boolean getCcGenericServices() {
            return this.ccGenericServices_;
        }

        public FileOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public String getGoPackage() {
            Object obj = this.goPackage_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.goPackage_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getGoPackageBytes() {
            Object obj = this.goPackage_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.goPackage_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean getJavaGenerateEqualsAndHash() {
            return this.javaGenerateEqualsAndHash_;
        }

        public boolean getJavaGenericServices() {
            return this.javaGenericServices_;
        }

        public boolean getJavaMultipleFiles() {
            return this.javaMultipleFiles_;
        }

        public String getJavaOuterClassname() {
            Object obj = this.javaOuterClassname_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.javaOuterClassname_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getJavaOuterClassnameBytes() {
            Object obj = this.javaOuterClassname_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.javaOuterClassname_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public String getJavaPackage() {
            Object obj = this.javaPackage_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.javaPackage_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getJavaPackageBytes() {
            Object obj = this.javaPackage_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.javaPackage_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean getJavaStringCheckUtf8() {
            return this.javaStringCheckUtf8_;
        }

        public OptimizeMode getOptimizeFor() {
            return this.optimizeFor_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public boolean getPyGenericServices() {
            return this.pyGenericServices_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            i = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getJavaPackageBytes()) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeBytesSize(8, getJavaOuterClassnameBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                i += CodedOutputStream.computeEnumSize(9, this.optimizeFor_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeBoolSize(10, this.javaMultipleFiles_);
            }
            if ((this.bitField0_ & 64) == 64) {
                i += CodedOutputStream.computeBytesSize(11, getGoPackageBytes());
            }
            if ((this.bitField0_ & 128) == 128) {
                i += CodedOutputStream.computeBoolSize(16, this.ccGenericServices_);
            }
            if ((this.bitField0_ & 256) == 256) {
                i += CodedOutputStream.computeBoolSize(17, this.javaGenericServices_);
            }
            if ((this.bitField0_ & 512) == 512) {
                i += CodedOutputStream.computeBoolSize(18, this.pyGenericServices_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i += CodedOutputStream.computeBoolSize(20, this.javaGenerateEqualsAndHash_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                i += CodedOutputStream.computeBoolSize(23, this.deprecated_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i += CodedOutputStream.computeBoolSize(27, this.javaStringCheckUtf8_);
            }
            int i2 = 0;
            int i3 = i;
            while (i2 < this.uninterpretedOption_.size()) {
                i = CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i2)) + i3;
                i2++;
                i3 = i;
            }
            i = (extensionsSerializedSize() + i3) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return (UninterpretedOption) this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
            return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
        }

        public List getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasCcGenericServices() {
            return (this.bitField0_ & 128) == 128;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public boolean hasGoPackage() {
            return (this.bitField0_ & 64) == 64;
        }

        public boolean hasJavaGenerateEqualsAndHash() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasJavaGenericServices() {
            return (this.bitField0_ & 256) == 256;
        }

        public boolean hasJavaMultipleFiles() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasJavaOuterClassname() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasJavaPackage() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasJavaStringCheckUtf8() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasOptimizeFor() {
            return (this.bitField0_ & 32) == 32;
        }

        public boolean hasPyGenericServices() {
            return (this.bitField0_ & 512) == 512;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_FileOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(FileOptions.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getUninterpretedOptionCount()) {
                if (getUninterpretedOption(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getJavaPackageBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(8, getJavaOuterClassnameBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeEnum(9, this.optimizeFor_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBool(10, this.javaMultipleFiles_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeBytes(11, getGoPackageBytes());
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeBool(16, this.ccGenericServices_);
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeBool(17, this.javaGenericServices_);
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeBool(18, this.pyGenericServices_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBool(20, this.javaGenerateEqualsAndHash_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                codedOutputStream.writeBool(23, this.deprecated_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBool(27, this.javaStringCheckUtf8_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
            }
            newExtensionWriter.writeUntil(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface MessageOptionsOrBuilder extends ExtendableMessageOrBuilder {
        boolean getDeprecated();

        boolean getMessageSetWireFormat();

        boolean getNoStandardDescriptorAccessor();

        UninterpretedOption getUninterpretedOption(int i);

        int getUninterpretedOptionCount();

        List getUninterpretedOptionList();

        UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i);

        List getUninterpretedOptionOrBuilderList();

        boolean hasDeprecated();

        boolean hasMessageSetWireFormat();

        boolean hasNoStandardDescriptorAccessor();
    }

    public final class MessageOptions extends ExtendableMessage implements MessageOptionsOrBuilder {
        public static final int DEPRECATED_FIELD_NUMBER = 3;
        public static final int MESSAGE_SET_WIRE_FORMAT_FIELD_NUMBER = 1;
        public static final int NO_STANDARD_DESCRIPTOR_ACCESSOR_FIELD_NUMBER = 2;
        public static Parser PARSER = new C19781();
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final MessageOptions defaultInstance = new MessageOptions(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean deprecated_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private boolean messageSetWireFormat_;
        private boolean noStandardDescriptorAccessor_;
        private List uninterpretedOption_;
        private final UnknownFieldSet unknownFields;

        final class C19781 extends AbstractParser {
            C19781() {
            }

            public MessageOptions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new MessageOptions(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends ExtendableBuilder implements MessageOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private boolean messageSetWireFormat_;
            private boolean noStandardDescriptorAccessor_;
            private RepeatedFieldBuilder uninterpretedOptionBuilder_;
            private List uninterpretedOption_;

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 8;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
            }

            private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getUninterpretedOptionFieldBuilder();
                }
            }

            public Builder addAllUninterpretedOption(Iterable iterable) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.uninterpretedOption_);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOption(Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOptionBuilder() {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public Builder addUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(i, UninterpretedOption.getDefaultInstance());
            }

            public MessageOptions build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public MessageOptions buildPartial() {
                int i = 1;
                MessageOptions messageOptions = new MessageOptions((ExtendableBuilder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                messageOptions.messageSetWireFormat_ = this.messageSetWireFormat_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                messageOptions.noStandardDescriptorAccessor_ = this.noStandardDescriptorAccessor_;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                messageOptions.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= -9;
                    }
                    messageOptions.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    messageOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                messageOptions.bitField0_ = i;
                onBuilt();
                return messageOptions;
            }

            public Builder clear() {
                super.clear();
                this.messageSetWireFormat_ = false;
                this.bitField0_ &= -2;
                this.noStandardDescriptorAccessor_ = false;
                this.bitField0_ &= -3;
                this.deprecated_ = false;
                this.bitField0_ &= -5;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= -5;
                this.deprecated_ = false;
                onChanged();
                return this;
            }

            public Builder clearMessageSetWireFormat() {
                this.bitField0_ &= -2;
                this.messageSetWireFormat_ = false;
                onChanged();
                return this;
            }

            public Builder clearNoStandardDescriptorAccessor() {
                this.bitField0_ &= -3;
                this.noStandardDescriptorAccessor_ = false;
                onChanged();
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -9;
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public MessageOptions getDefaultInstanceForType() {
                return MessageOptions.getDefaultInstance();
            }

            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
            }

            public boolean getMessageSetWireFormat() {
                return this.messageSetWireFormat_;
            }

            public boolean getNoStandardDescriptorAccessor() {
                return this.noStandardDescriptorAccessor_;
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOption) this.uninterpretedOption_.get(i) : (UninterpretedOption) this.uninterpretedOptionBuilder_.getMessage(i);
            }

            public Builder getUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().getBuilder(i);
            }

            public List getUninterpretedOptionBuilderList() {
                return getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            public int getUninterpretedOptionCount() {
                return this.uninterpretedOptionBuilder_ == null ? this.uninterpretedOption_.size() : this.uninterpretedOptionBuilder_.getCount();
            }

            public List getUninterpretedOptionList() {
                return this.uninterpretedOptionBuilder_ == null ? Collections.unmodifiableList(this.uninterpretedOption_) : this.uninterpretedOptionBuilder_.getMessageList();
            }

            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i) : (UninterpretedOptionOrBuilder) this.uninterpretedOptionBuilder_.getMessageOrBuilder(i);
            }

            public List getUninterpretedOptionOrBuilderList() {
                return this.uninterpretedOptionBuilder_ != null ? this.uninterpretedOptionBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public boolean hasDeprecated() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasMessageSetWireFormat() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasNoStandardDescriptorAccessor() {
                return (this.bitField0_ & 2) == 2;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f216x9c0b3d38.ensureFieldAccessorsInitialized(MessageOptions.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                MessageOptions messageOptions;
                Throwable th;
                try {
                    messageOptions = (MessageOptions) MessageOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (messageOptions != null) {
                        mergeFrom(messageOptions);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    messageOptions = (MessageOptions) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (messageOptions != null) {
                    mergeFrom(messageOptions);
                }
                throw th;
            }

            public Builder mergeFrom(MessageOptions messageOptions) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (messageOptions != MessageOptions.getDefaultInstance()) {
                    if (messageOptions.hasMessageSetWireFormat()) {
                        setMessageSetWireFormat(messageOptions.getMessageSetWireFormat());
                    }
                    if (messageOptions.hasNoStandardDescriptorAccessor()) {
                        setNoStandardDescriptorAccessor(messageOptions.getNoStandardDescriptorAccessor());
                    }
                    if (messageOptions.hasDeprecated()) {
                        setDeprecated(messageOptions.getDeprecated());
                    }
                    if (this.uninterpretedOptionBuilder_ == null) {
                        if (!messageOptions.uninterpretedOption_.isEmpty()) {
                            if (this.uninterpretedOption_.isEmpty()) {
                                this.uninterpretedOption_ = messageOptions.uninterpretedOption_;
                                this.bitField0_ &= -9;
                            } else {
                                ensureUninterpretedOptionIsMutable();
                                this.uninterpretedOption_.addAll(messageOptions.uninterpretedOption_);
                            }
                            onChanged();
                        }
                    } else if (!messageOptions.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOptionBuilder_.isEmpty()) {
                            this.uninterpretedOptionBuilder_.dispose();
                            this.uninterpretedOptionBuilder_ = null;
                            this.uninterpretedOption_ = messageOptions.uninterpretedOption_;
                            this.bitField0_ &= -9;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getUninterpretedOptionFieldBuilder();
                            }
                            this.uninterpretedOptionBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.uninterpretedOptionBuilder_.addAllMessages(messageOptions.uninterpretedOption_);
                        }
                    }
                    mergeExtensionFields(messageOptions);
                    mergeUnknownFields(messageOptions.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof MessageOptions) {
                    return mergeFrom((MessageOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeUninterpretedOption(int i) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(i);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(i);
                }
                return this;
            }

            public Builder setDeprecated(boolean z) {
                this.bitField0_ |= 4;
                this.deprecated_ = z;
                onChanged();
                return this;
            }

            public Builder setMessageSetWireFormat(boolean z) {
                this.bitField0_ |= 1;
                this.messageSetWireFormat_ = z;
                onChanged();
                return this;
            }

            public Builder setNoStandardDescriptorAccessor(boolean z) {
                this.bitField0_ |= 2;
                this.noStandardDescriptorAccessor_ = z;
                onChanged();
                return this;
            }

            public Builder setUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.setMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        private MessageOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 8:
                            this.bitField0_ |= 1;
                            this.messageSetWireFormat_ = codedInputStream.readBool();
                            break;
                        case 16:
                            this.bitField0_ |= 2;
                            this.noStandardDescriptorAccessor_ = codedInputStream.readBool();
                            break;
                        case 24:
                            this.bitField0_ |= 4;
                            this.deprecated_ = codedInputStream.readBool();
                            break;
                        case 7994:
                            if ((i & 8) != 8) {
                                this.uninterpretedOption_ = new ArrayList();
                                i |= 8;
                            }
                            this.uninterpretedOption_.add(codedInputStream.readMessage(UninterpretedOption.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 8) == 8) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 8) == 8) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private MessageOptions(ExtendableBuilder extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private MessageOptions(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MessageOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
        }

        private void initFields() {
            this.messageSetWireFormat_ = false;
            this.noStandardDescriptorAccessor_ = false;
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(MessageOptions messageOptions) {
            return newBuilder().mergeFrom(messageOptions);
        }

        public static MessageOptions parseDelimitedFrom(InputStream inputStream) {
            return (MessageOptions) PARSER.parseDelimitedFrom(inputStream);
        }

        public static MessageOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MessageOptions) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static MessageOptions parseFrom(ByteString byteString) {
            return (MessageOptions) PARSER.parseFrom(byteString);
        }

        public static MessageOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (MessageOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MessageOptions parseFrom(CodedInputStream codedInputStream) {
            return (MessageOptions) PARSER.parseFrom(codedInputStream);
        }

        public static MessageOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MessageOptions) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static MessageOptions parseFrom(InputStream inputStream) {
            return (MessageOptions) PARSER.parseFrom(inputStream);
        }

        public static MessageOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MessageOptions) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static MessageOptions parseFrom(byte[] bArr) {
            return (MessageOptions) PARSER.parseFrom(bArr);
        }

        public static MessageOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (MessageOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public MessageOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public boolean getMessageSetWireFormat() {
            return this.messageSetWireFormat_;
        }

        public boolean getNoStandardDescriptorAccessor() {
            return this.noStandardDescriptorAccessor_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            i = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBoolSize(1, this.messageSetWireFormat_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeBoolSize(2, this.noStandardDescriptorAccessor_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeBoolSize(3, this.deprecated_);
            }
            int i2 = 0;
            int i3 = i;
            while (i2 < this.uninterpretedOption_.size()) {
                i = CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i2)) + i3;
                i2++;
                i3 = i;
            }
            i = (extensionsSerializedSize() + i3) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return (UninterpretedOption) this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
            return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
        }

        public List getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasMessageSetWireFormat() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasNoStandardDescriptorAccessor() {
            return (this.bitField0_ & 2) == 2;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f216x9c0b3d38.ensureFieldAccessorsInitialized(MessageOptions.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getUninterpretedOptionCount()) {
                if (getUninterpretedOption(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBool(1, this.messageSetWireFormat_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBool(2, this.noStandardDescriptorAccessor_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBool(3, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
            }
            newExtensionWriter.writeUntil(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface MethodDescriptorProtoOrBuilder extends MessageOrBuilder {
        String getInputType();

        ByteString getInputTypeBytes();

        String getName();

        ByteString getNameBytes();

        MethodOptions getOptions();

        MethodOptionsOrBuilder getOptionsOrBuilder();

        String getOutputType();

        ByteString getOutputTypeBytes();

        boolean hasInputType();

        boolean hasName();

        boolean hasOptions();

        boolean hasOutputType();
    }

    public final class MethodDescriptorProto extends GeneratedMessage implements MethodDescriptorProtoOrBuilder {
        public static final int INPUT_TYPE_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OPTIONS_FIELD_NUMBER = 4;
        public static final int OUTPUT_TYPE_FIELD_NUMBER = 3;
        public static Parser PARSER = new C19791();
        private static final MethodDescriptorProto defaultInstance = new MethodDescriptorProto(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private Object inputType_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object name_;
        private MethodOptions options_;
        private Object outputType_;
        private final UnknownFieldSet unknownFields;

        final class C19791 extends AbstractParser {
            C19791() {
            }

            public MethodDescriptorProto parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new MethodDescriptorProto(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements MethodDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object inputType_;
            private Object name_;
            private SingleFieldBuilder optionsBuilder_;
            private MethodOptions options_;
            private Object outputType_;

            private Builder() {
                this.name_ = "";
                this.inputType_ = "";
                this.outputType_ = "";
                this.options_ = MethodOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.inputType_ = "";
                this.outputType_ = "";
                this.options_ = MethodOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
            }

            private SingleFieldBuilder getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getOptionsFieldBuilder();
                }
            }

            public MethodDescriptorProto build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public MethodDescriptorProto buildPartial() {
                int i = 1;
                MethodDescriptorProto methodDescriptorProto = new MethodDescriptorProto((com.google.protobuf.GeneratedMessage.Builder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                methodDescriptorProto.name_ = this.name_;
                if ((i2 & 2) == 2) {
                    i |= 2;
                }
                methodDescriptorProto.inputType_ = this.inputType_;
                if ((i2 & 4) == 4) {
                    i |= 4;
                }
                methodDescriptorProto.outputType_ = this.outputType_;
                i2 = (i2 & 8) == 8 ? i | 8 : i;
                if (this.optionsBuilder_ == null) {
                    methodDescriptorProto.options_ = this.options_;
                } else {
                    methodDescriptorProto.options_ = (MethodOptions) this.optionsBuilder_.build();
                }
                methodDescriptorProto.bitField0_ = i2;
                onBuilt();
                return methodDescriptorProto;
            }

            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                this.inputType_ = "";
                this.bitField0_ &= -3;
                this.outputType_ = "";
                this.bitField0_ &= -5;
                if (this.optionsBuilder_ == null) {
                    this.options_ = MethodOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clearInputType() {
                this.bitField0_ &= -3;
                this.inputType_ = MethodDescriptorProto.getDefaultInstance().getInputType();
                onChanged();
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = MethodDescriptorProto.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = MethodOptions.getDefaultInstance();
                    onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clearOutputType() {
                this.bitField0_ &= -5;
                this.outputType_ = MethodDescriptorProto.getDefaultInstance().getOutputType();
                onChanged();
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public MethodDescriptorProto getDefaultInstanceForType() {
                return MethodDescriptorProto.getDefaultInstance();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
            }

            public String getInputType() {
                Object obj = this.inputType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.inputType_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getInputTypeBytes() {
                Object obj = this.inputType_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.inputType_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public MethodOptions getOptions() {
                return this.optionsBuilder_ == null ? this.options_ : (MethodOptions) this.optionsBuilder_.getMessage();
            }

            public Builder getOptionsBuilder() {
                this.bitField0_ |= 8;
                onChanged();
                return (Builder) getOptionsFieldBuilder().getBuilder();
            }

            public MethodOptionsOrBuilder getOptionsOrBuilder() {
                return this.optionsBuilder_ != null ? (MethodOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder() : this.options_;
            }

            public String getOutputType() {
                Object obj = this.outputType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.outputType_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getOutputTypeBytes() {
                Object obj = this.outputType_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.outputType_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasInputType() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasOutputType() {
                return (this.bitField0_ & 4) == 4;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f217xc5331ef1.ensureFieldAccessorsInitialized(MethodDescriptorProto.class, Builder.class);
            }

            public final boolean isInitialized() {
                return !hasOptions() || getOptions().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                MethodDescriptorProto methodDescriptorProto;
                Throwable th;
                try {
                    methodDescriptorProto = (MethodDescriptorProto) MethodDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (methodDescriptorProto != null) {
                        mergeFrom(methodDescriptorProto);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    methodDescriptorProto = (MethodDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (methodDescriptorProto != null) {
                    mergeFrom(methodDescriptorProto);
                }
                throw th;
            }

            public Builder mergeFrom(MethodDescriptorProto methodDescriptorProto) {
                if (methodDescriptorProto != MethodDescriptorProto.getDefaultInstance()) {
                    if (methodDescriptorProto.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = methodDescriptorProto.name_;
                        onChanged();
                    }
                    if (methodDescriptorProto.hasInputType()) {
                        this.bitField0_ |= 2;
                        this.inputType_ = methodDescriptorProto.inputType_;
                        onChanged();
                    }
                    if (methodDescriptorProto.hasOutputType()) {
                        this.bitField0_ |= 4;
                        this.outputType_ = methodDescriptorProto.outputType_;
                        onChanged();
                    }
                    if (methodDescriptorProto.hasOptions()) {
                        mergeOptions(methodDescriptorProto.getOptions());
                    }
                    mergeUnknownFields(methodDescriptorProto.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof MethodDescriptorProto) {
                    return mergeFrom((MethodDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(MethodOptions methodOptions) {
                if (this.optionsBuilder_ == null) {
                    if ((this.bitField0_ & 8) != 8 || this.options_ == MethodOptions.getDefaultInstance()) {
                        this.options_ = methodOptions;
                    } else {
                        this.options_ = MethodOptions.newBuilder(this.options_).mergeFrom(methodOptions).buildPartial();
                    }
                    onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(methodOptions);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setInputType(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.inputType_ = str;
                onChanged();
                return this;
            }

            public Builder setInputTypeBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.inputType_ = byteString;
                onChanged();
                return this;
            }

            public Builder setName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder setOptions(Builder builder) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builder.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setOptions(MethodOptions methodOptions) {
                if (this.optionsBuilder_ != null) {
                    this.optionsBuilder_.setMessage(methodOptions);
                } else if (methodOptions == null) {
                    throw new NullPointerException();
                } else {
                    this.options_ = methodOptions;
                    onChanged();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setOutputType(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.outputType_ = str;
                onChanged();
                return this;
            }

            public Builder setOutputTypeBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.outputType_ = byteString;
                onChanged();
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        private MethodDescriptorProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            Object obj = null;
            while (obj == null) {
                try {
                    Object obj2;
                    int readTag = codedInputStream.readTag();
                    ByteString readBytes;
                    switch (readTag) {
                        case 0:
                            readTag = 1;
                            break;
                        case 10:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.name_ = readBytes;
                            obj2 = obj;
                            break;
                        case 18:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 2;
                            this.inputType_ = readBytes;
                            obj2 = obj;
                            break;
                        case 26:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 4;
                            this.outputType_ = readBytes;
                            obj2 = obj;
                            break;
                        case 34:
                            Builder toBuilder = (this.bitField0_ & 8) == 8 ? this.options_.toBuilder() : null;
                            this.options_ = (MethodOptions) codedInputStream.readMessage(MethodOptions.PARSER, extensionRegistryLite);
                            if (toBuilder != null) {
                                toBuilder.mergeFrom(this.options_);
                                this.options_ = toBuilder.buildPartial();
                            }
                            this.bitField0_ |= 8;
                            obj2 = obj;
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                obj2 = 1;
                                break;
                            } else {
                                obj2 = obj;
                                break;
                            }
                    }
                    obj = obj2;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private MethodDescriptorProto(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private MethodDescriptorProto(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MethodDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
        }

        private void initFields() {
            this.name_ = "";
            this.inputType_ = "";
            this.outputType_ = "";
            this.options_ = MethodOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(MethodDescriptorProto methodDescriptorProto) {
            return newBuilder().mergeFrom(methodDescriptorProto);
        }

        public static MethodDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            return (MethodDescriptorProto) PARSER.parseDelimitedFrom(inputStream);
        }

        public static MethodDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodDescriptorProto) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static MethodDescriptorProto parseFrom(ByteString byteString) {
            return (MethodDescriptorProto) PARSER.parseFrom(byteString);
        }

        public static MethodDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MethodDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return (MethodDescriptorProto) PARSER.parseFrom(codedInputStream);
        }

        public static MethodDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodDescriptorProto) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static MethodDescriptorProto parseFrom(InputStream inputStream) {
            return (MethodDescriptorProto) PARSER.parseFrom(inputStream);
        }

        public static MethodDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodDescriptorProto) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static MethodDescriptorProto parseFrom(byte[] bArr) {
            return (MethodDescriptorProto) PARSER.parseFrom(bArr);
        }

        public static MethodDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public MethodDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getInputType() {
            Object obj = this.inputType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.inputType_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getInputTypeBytes() {
            Object obj = this.inputType_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.inputType_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public MethodOptions getOptions() {
            return this.options_;
        }

        public MethodOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        public String getOutputType() {
            Object obj = this.outputType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.outputType_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getOutputTypeBytes() {
            Object obj = this.outputType_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.outputType_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.bitField0_ & 1) == 1) {
                i = CodedOutputStream.computeBytesSize(1, getNameBytes()) + 0;
            }
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeBytesSize(2, getInputTypeBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeBytesSize(3, getOutputTypeBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                i += CodedOutputStream.computeMessageSize(4, this.options_);
            }
            i += getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasInputType() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasOutputType() {
            return (this.bitField0_ & 4) == 4;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f217xc5331ef1.ensureFieldAccessorsInitialized(MethodDescriptorProto.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            if (!hasOptions() || getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getNameBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, getInputTypeBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, getOutputTypeBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeMessage(4, this.options_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface MethodOptionsOrBuilder extends ExtendableMessageOrBuilder {
        boolean getDeprecated();

        UninterpretedOption getUninterpretedOption(int i);

        int getUninterpretedOptionCount();

        List getUninterpretedOptionList();

        UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i);

        List getUninterpretedOptionOrBuilderList();

        boolean hasDeprecated();
    }

    public final class MethodOptions extends ExtendableMessage implements MethodOptionsOrBuilder {
        public static final int DEPRECATED_FIELD_NUMBER = 33;
        public static Parser PARSER = new C19801();
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final MethodOptions defaultInstance = new MethodOptions(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean deprecated_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List uninterpretedOption_;
        private final UnknownFieldSet unknownFields;

        final class C19801 extends AbstractParser {
            C19801() {
            }

            public MethodOptions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new MethodOptions(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends ExtendableBuilder implements MethodOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private RepeatedFieldBuilder uninterpretedOptionBuilder_;
            private List uninterpretedOption_;

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 2;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
            }

            private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getUninterpretedOptionFieldBuilder();
                }
            }

            public Builder addAllUninterpretedOption(Iterable iterable) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.uninterpretedOption_);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOption(Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOptionBuilder() {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public Builder addUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(i, UninterpretedOption.getDefaultInstance());
            }

            public MethodOptions build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public MethodOptions buildPartial() {
                int i = 1;
                MethodOptions methodOptions = new MethodOptions((ExtendableBuilder) this);
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                methodOptions.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= -3;
                    }
                    methodOptions.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    methodOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                methodOptions.bitField0_ = i;
                onBuilt();
                return methodOptions;
            }

            public Builder clear() {
                super.clear();
                this.deprecated_ = false;
                this.bitField0_ &= -2;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= -2;
                this.deprecated_ = false;
                onChanged();
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public MethodOptions getDefaultInstanceForType() {
                return MethodOptions.getDefaultInstance();
            }

            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOption) this.uninterpretedOption_.get(i) : (UninterpretedOption) this.uninterpretedOptionBuilder_.getMessage(i);
            }

            public Builder getUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().getBuilder(i);
            }

            public List getUninterpretedOptionBuilderList() {
                return getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            public int getUninterpretedOptionCount() {
                return this.uninterpretedOptionBuilder_ == null ? this.uninterpretedOption_.size() : this.uninterpretedOptionBuilder_.getCount();
            }

            public List getUninterpretedOptionList() {
                return this.uninterpretedOptionBuilder_ == null ? Collections.unmodifiableList(this.uninterpretedOption_) : this.uninterpretedOptionBuilder_.getMessageList();
            }

            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i) : (UninterpretedOptionOrBuilder) this.uninterpretedOptionBuilder_.getMessageOrBuilder(i);
            }

            public List getUninterpretedOptionOrBuilderList() {
                return this.uninterpretedOptionBuilder_ != null ? this.uninterpretedOptionBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public boolean hasDeprecated() {
                return (this.bitField0_ & 1) == 1;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodOptions.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                MethodOptions methodOptions;
                Throwable th;
                try {
                    methodOptions = (MethodOptions) MethodOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (methodOptions != null) {
                        mergeFrom(methodOptions);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    methodOptions = (MethodOptions) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (methodOptions != null) {
                    mergeFrom(methodOptions);
                }
                throw th;
            }

            public Builder mergeFrom(MethodOptions methodOptions) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (methodOptions != MethodOptions.getDefaultInstance()) {
                    if (methodOptions.hasDeprecated()) {
                        setDeprecated(methodOptions.getDeprecated());
                    }
                    if (this.uninterpretedOptionBuilder_ == null) {
                        if (!methodOptions.uninterpretedOption_.isEmpty()) {
                            if (this.uninterpretedOption_.isEmpty()) {
                                this.uninterpretedOption_ = methodOptions.uninterpretedOption_;
                                this.bitField0_ &= -3;
                            } else {
                                ensureUninterpretedOptionIsMutable();
                                this.uninterpretedOption_.addAll(methodOptions.uninterpretedOption_);
                            }
                            onChanged();
                        }
                    } else if (!methodOptions.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOptionBuilder_.isEmpty()) {
                            this.uninterpretedOptionBuilder_.dispose();
                            this.uninterpretedOptionBuilder_ = null;
                            this.uninterpretedOption_ = methodOptions.uninterpretedOption_;
                            this.bitField0_ &= -3;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getUninterpretedOptionFieldBuilder();
                            }
                            this.uninterpretedOptionBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.uninterpretedOptionBuilder_.addAllMessages(methodOptions.uninterpretedOption_);
                        }
                    }
                    mergeExtensionFields(methodOptions);
                    mergeUnknownFields(methodOptions.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof MethodOptions) {
                    return mergeFrom((MethodOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeUninterpretedOption(int i) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(i);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(i);
                }
                return this;
            }

            public Builder setDeprecated(boolean z) {
                this.bitField0_ |= 1;
                this.deprecated_ = z;
                onChanged();
                return this;
            }

            public Builder setUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.setMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        private MethodOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 264:
                            this.bitField0_ |= 1;
                            this.deprecated_ = codedInputStream.readBool();
                            break;
                        case 7994:
                            if ((i & 2) != 2) {
                                this.uninterpretedOption_ = new ArrayList();
                                i |= 2;
                            }
                            this.uninterpretedOption_.add(codedInputStream.readMessage(UninterpretedOption.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 2) == 2) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private MethodOptions(ExtendableBuilder extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private MethodOptions(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MethodOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
        }

        private void initFields() {
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(MethodOptions methodOptions) {
            return newBuilder().mergeFrom(methodOptions);
        }

        public static MethodOptions parseDelimitedFrom(InputStream inputStream) {
            return (MethodOptions) PARSER.parseDelimitedFrom(inputStream);
        }

        public static MethodOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodOptions) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static MethodOptions parseFrom(ByteString byteString) {
            return (MethodOptions) PARSER.parseFrom(byteString);
        }

        public static MethodOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MethodOptions parseFrom(CodedInputStream codedInputStream) {
            return (MethodOptions) PARSER.parseFrom(codedInputStream);
        }

        public static MethodOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodOptions) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static MethodOptions parseFrom(InputStream inputStream) {
            return (MethodOptions) PARSER.parseFrom(inputStream);
        }

        public static MethodOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodOptions) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static MethodOptions parseFrom(byte[] bArr) {
            return (MethodOptions) PARSER.parseFrom(bArr);
        }

        public static MethodOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (MethodOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public MethodOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int computeBoolSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBoolSize(33, this.deprecated_) + 0 : 0;
            while (i2 < this.uninterpretedOption_.size()) {
                i = CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i2)) + computeBoolSize;
                i2++;
                computeBoolSize = i;
            }
            i = (extensionsSerializedSize() + computeBoolSize) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return (UninterpretedOption) this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
            return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
        }

        public List getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 1) == 1;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable.ensureFieldAccessorsInitialized(MethodOptions.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getUninterpretedOptionCount()) {
                if (getUninterpretedOption(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBool(33, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
            }
            newExtensionWriter.writeUntil(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface OneofDescriptorProtoOrBuilder extends MessageOrBuilder {
        String getName();

        ByteString getNameBytes();

        boolean hasName();
    }

    public final class OneofDescriptorProto extends GeneratedMessage implements OneofDescriptorProtoOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static Parser PARSER = new C19811();
        private static final OneofDescriptorProto defaultInstance = new OneofDescriptorProto(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object name_;
        private final UnknownFieldSet unknownFields;

        final class C19811 extends AbstractParser {
            C19811() {
            }

            public OneofDescriptorProto parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new OneofDescriptorProto(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements OneofDescriptorProtoOrBuilder {
            private int bitField0_;
            private Object name_;

            private Builder() {
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                if (!GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }

            public OneofDescriptorProto build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public OneofDescriptorProto buildPartial() {
                int i = 1;
                OneofDescriptorProto oneofDescriptorProto = new OneofDescriptorProto((com.google.protobuf.GeneratedMessage.Builder) this);
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                oneofDescriptorProto.name_ = this.name_;
                oneofDescriptorProto.bitField0_ = i;
                onBuilt();
                return oneofDescriptorProto;
            }

            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = OneofDescriptorProto.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public OneofDescriptorProto getDefaultInstanceForType() {
                return OneofDescriptorProto.getDefaultInstance();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_descriptor;
            }

            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f218x69499c33.ensureFieldAccessorsInitialized(OneofDescriptorProto.class, Builder.class);
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                OneofDescriptorProto oneofDescriptorProto;
                Throwable th;
                try {
                    oneofDescriptorProto = (OneofDescriptorProto) OneofDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (oneofDescriptorProto != null) {
                        mergeFrom(oneofDescriptorProto);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    oneofDescriptorProto = (OneofDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (oneofDescriptorProto != null) {
                    mergeFrom(oneofDescriptorProto);
                }
                throw th;
            }

            public Builder mergeFrom(OneofDescriptorProto oneofDescriptorProto) {
                if (oneofDescriptorProto != OneofDescriptorProto.getDefaultInstance()) {
                    if (oneofDescriptorProto.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = oneofDescriptorProto.name_;
                        onChanged();
                    }
                    mergeUnknownFields(oneofDescriptorProto.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof OneofDescriptorProto) {
                    return mergeFrom((OneofDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder setName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = byteString;
                onChanged();
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        private OneofDescriptorProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            Object obj = null;
            while (obj == null) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            obj = 1;
                            break;
                        case 10:
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.name_ = readBytes;
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private OneofDescriptorProto(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private OneofDescriptorProto(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static OneofDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_OneofDescriptorProto_descriptor;
        }

        private void initFields() {
            this.name_ = "";
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(OneofDescriptorProto oneofDescriptorProto) {
            return newBuilder().mergeFrom(oneofDescriptorProto);
        }

        public static OneofDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            return (OneofDescriptorProto) PARSER.parseDelimitedFrom(inputStream);
        }

        public static OneofDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (OneofDescriptorProto) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static OneofDescriptorProto parseFrom(ByteString byteString) {
            return (OneofDescriptorProto) PARSER.parseFrom(byteString);
        }

        public static OneofDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (OneofDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static OneofDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return (OneofDescriptorProto) PARSER.parseFrom(codedInputStream);
        }

        public static OneofDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (OneofDescriptorProto) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static OneofDescriptorProto parseFrom(InputStream inputStream) {
            return (OneofDescriptorProto) PARSER.parseFrom(inputStream);
        }

        public static OneofDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (OneofDescriptorProto) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static OneofDescriptorProto parseFrom(byte[] bArr) {
            return (OneofDescriptorProto) PARSER.parseFrom(bArr);
        }

        public static OneofDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (OneofDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public OneofDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            i = 0;
            if ((this.bitField0_ & 1) == 1) {
                i = CodedOutputStream.computeBytesSize(1, getNameBytes()) + 0;
            }
            i += getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f218x69499c33.ensureFieldAccessorsInitialized(OneofDescriptorProto.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getNameBytes());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface ServiceDescriptorProtoOrBuilder extends MessageOrBuilder {
        MethodDescriptorProto getMethod(int i);

        int getMethodCount();

        List getMethodList();

        MethodDescriptorProtoOrBuilder getMethodOrBuilder(int i);

        List getMethodOrBuilderList();

        String getName();

        ByteString getNameBytes();

        ServiceOptions getOptions();

        ServiceOptionsOrBuilder getOptionsOrBuilder();

        boolean hasName();

        boolean hasOptions();
    }

    public final class ServiceDescriptorProto extends GeneratedMessage implements ServiceDescriptorProtoOrBuilder {
        public static final int METHOD_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        public static Parser PARSER = new C19821();
        private static final ServiceDescriptorProto defaultInstance = new ServiceDescriptorProto(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List method_;
        private Object name_;
        private ServiceOptions options_;
        private final UnknownFieldSet unknownFields;

        final class C19821 extends AbstractParser {
            C19821() {
            }

            public ServiceDescriptorProto parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new ServiceDescriptorProto(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements ServiceDescriptorProtoOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilder methodBuilder_;
            private List method_;
            private Object name_;
            private SingleFieldBuilder optionsBuilder_;
            private ServiceOptions options_;

            private Builder() {
                this.name_ = "";
                this.method_ = Collections.emptyList();
                this.options_ = ServiceOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.method_ = Collections.emptyList();
                this.options_ = ServiceOptions.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureMethodIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.method_ = new ArrayList(this.method_);
                    this.bitField0_ |= 2;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.f219x158c73ed;
            }

            private RepeatedFieldBuilder getMethodFieldBuilder() {
                if (this.methodBuilder_ == null) {
                    this.methodBuilder_ = new RepeatedFieldBuilder(this.method_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.method_ = null;
                }
                return this.methodBuilder_;
            }

            private SingleFieldBuilder getOptionsFieldBuilder() {
                if (this.optionsBuilder_ == null) {
                    this.optionsBuilder_ = new SingleFieldBuilder(getOptions(), getParentForChildren(), isClean());
                    this.options_ = null;
                }
                return this.optionsBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getMethodFieldBuilder();
                    getOptionsFieldBuilder();
                }
            }

            public Builder addAllMethod(Iterable iterable) {
                if (this.methodBuilder_ == null) {
                    ensureMethodIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.method_);
                    onChanged();
                } else {
                    this.methodBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addMethod(int i, Builder builder) {
                if (this.methodBuilder_ == null) {
                    ensureMethodIsMutable();
                    this.method_.add(i, builder.build());
                    onChanged();
                } else {
                    this.methodBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addMethod(int i, MethodDescriptorProto methodDescriptorProto) {
                if (this.methodBuilder_ != null) {
                    this.methodBuilder_.addMessage(i, methodDescriptorProto);
                } else if (methodDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureMethodIsMutable();
                    this.method_.add(i, methodDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addMethod(Builder builder) {
                if (this.methodBuilder_ == null) {
                    ensureMethodIsMutable();
                    this.method_.add(builder.build());
                    onChanged();
                } else {
                    this.methodBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addMethod(MethodDescriptorProto methodDescriptorProto) {
                if (this.methodBuilder_ != null) {
                    this.methodBuilder_.addMessage(methodDescriptorProto);
                } else if (methodDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureMethodIsMutable();
                    this.method_.add(methodDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder addMethodBuilder() {
                return (Builder) getMethodFieldBuilder().addBuilder(MethodDescriptorProto.getDefaultInstance());
            }

            public Builder addMethodBuilder(int i) {
                return (Builder) getMethodFieldBuilder().addBuilder(i, MethodDescriptorProto.getDefaultInstance());
            }

            public ServiceDescriptorProto build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public ServiceDescriptorProto buildPartial() {
                int i = 1;
                ServiceDescriptorProto serviceDescriptorProto = new ServiceDescriptorProto((com.google.protobuf.GeneratedMessage.Builder) this);
                int i2 = this.bitField0_;
                if ((i2 & 1) != 1) {
                    i = 0;
                }
                serviceDescriptorProto.name_ = this.name_;
                if (this.methodBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.method_ = Collections.unmodifiableList(this.method_);
                        this.bitField0_ &= -3;
                    }
                    serviceDescriptorProto.method_ = this.method_;
                } else {
                    serviceDescriptorProto.method_ = this.methodBuilder_.build();
                }
                i2 = (i2 & 4) == 4 ? i | 2 : i;
                if (this.optionsBuilder_ == null) {
                    serviceDescriptorProto.options_ = this.options_;
                } else {
                    serviceDescriptorProto.options_ = (ServiceOptions) this.optionsBuilder_.build();
                }
                serviceDescriptorProto.bitField0_ = i2;
                onBuilt();
                return serviceDescriptorProto;
            }

            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= -2;
                if (this.methodBuilder_ == null) {
                    this.method_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    this.methodBuilder_.clear();
                }
                if (this.optionsBuilder_ == null) {
                    this.options_ = ServiceOptions.getDefaultInstance();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clearMethod() {
                if (this.methodBuilder_ == null) {
                    this.method_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    this.methodBuilder_.clear();
                }
                return this;
            }

            public Builder clearName() {
                this.bitField0_ &= -2;
                this.name_ = ServiceDescriptorProto.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearOptions() {
                if (this.optionsBuilder_ == null) {
                    this.options_ = ServiceOptions.getDefaultInstance();
                    onChanged();
                } else {
                    this.optionsBuilder_.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public ServiceDescriptorProto getDefaultInstanceForType() {
                return ServiceDescriptorProto.getDefaultInstance();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.f219x158c73ed;
            }

            public MethodDescriptorProto getMethod(int i) {
                return this.methodBuilder_ == null ? (MethodDescriptorProto) this.method_.get(i) : (MethodDescriptorProto) this.methodBuilder_.getMessage(i);
            }

            public Builder getMethodBuilder(int i) {
                return (Builder) getMethodFieldBuilder().getBuilder(i);
            }

            public List getMethodBuilderList() {
                return getMethodFieldBuilder().getBuilderList();
            }

            public int getMethodCount() {
                return this.methodBuilder_ == null ? this.method_.size() : this.methodBuilder_.getCount();
            }

            public List getMethodList() {
                return this.methodBuilder_ == null ? Collections.unmodifiableList(this.method_) : this.methodBuilder_.getMessageList();
            }

            public MethodDescriptorProtoOrBuilder getMethodOrBuilder(int i) {
                return this.methodBuilder_ == null ? (MethodDescriptorProtoOrBuilder) this.method_.get(i) : (MethodDescriptorProtoOrBuilder) this.methodBuilder_.getMessageOrBuilder(i);
            }

            public List getMethodOrBuilderList() {
                return this.methodBuilder_ != null ? this.methodBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.method_);
            }

            public String getName() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.name_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public ServiceOptions getOptions() {
                return this.optionsBuilder_ == null ? this.options_ : (ServiceOptions) this.optionsBuilder_.getMessage();
            }

            public Builder getOptionsBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return (Builder) getOptionsFieldBuilder().getBuilder();
            }

            public ServiceOptionsOrBuilder getOptionsOrBuilder() {
                return this.optionsBuilder_ != null ? (ServiceOptionsOrBuilder) this.optionsBuilder_.getMessageOrBuilder() : this.options_;
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasOptions() {
                return (this.bitField0_ & 4) == 4;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f220xee335d6b.ensureFieldAccessorsInitialized(ServiceDescriptorProto.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getMethodCount(); i++) {
                    if (!getMethod(i).isInitialized()) {
                        return false;
                    }
                }
                return !hasOptions() || getOptions().isInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                ServiceDescriptorProto serviceDescriptorProto;
                Throwable th;
                try {
                    serviceDescriptorProto = (ServiceDescriptorProto) ServiceDescriptorProto.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (serviceDescriptorProto != null) {
                        mergeFrom(serviceDescriptorProto);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    serviceDescriptorProto = (ServiceDescriptorProto) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (serviceDescriptorProto != null) {
                    mergeFrom(serviceDescriptorProto);
                }
                throw th;
            }

            public Builder mergeFrom(ServiceDescriptorProto serviceDescriptorProto) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (serviceDescriptorProto != ServiceDescriptorProto.getDefaultInstance()) {
                    if (serviceDescriptorProto.hasName()) {
                        this.bitField0_ |= 1;
                        this.name_ = serviceDescriptorProto.name_;
                        onChanged();
                    }
                    if (this.methodBuilder_ == null) {
                        if (!serviceDescriptorProto.method_.isEmpty()) {
                            if (this.method_.isEmpty()) {
                                this.method_ = serviceDescriptorProto.method_;
                                this.bitField0_ &= -3;
                            } else {
                                ensureMethodIsMutable();
                                this.method_.addAll(serviceDescriptorProto.method_);
                            }
                            onChanged();
                        }
                    } else if (!serviceDescriptorProto.method_.isEmpty()) {
                        if (this.methodBuilder_.isEmpty()) {
                            this.methodBuilder_.dispose();
                            this.methodBuilder_ = null;
                            this.method_ = serviceDescriptorProto.method_;
                            this.bitField0_ &= -3;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getMethodFieldBuilder();
                            }
                            this.methodBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.methodBuilder_.addAllMessages(serviceDescriptorProto.method_);
                        }
                    }
                    if (serviceDescriptorProto.hasOptions()) {
                        mergeOptions(serviceDescriptorProto.getOptions());
                    }
                    mergeUnknownFields(serviceDescriptorProto.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof ServiceDescriptorProto) {
                    return mergeFrom((ServiceDescriptorProto) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeOptions(ServiceOptions serviceOptions) {
                if (this.optionsBuilder_ == null) {
                    if ((this.bitField0_ & 4) != 4 || this.options_ == ServiceOptions.getDefaultInstance()) {
                        this.options_ = serviceOptions;
                    } else {
                        this.options_ = ServiceOptions.newBuilder(this.options_).mergeFrom(serviceOptions).buildPartial();
                    }
                    onChanged();
                } else {
                    this.optionsBuilder_.mergeFrom(serviceOptions);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder removeMethod(int i) {
                if (this.methodBuilder_ == null) {
                    ensureMethodIsMutable();
                    this.method_.remove(i);
                    onChanged();
                } else {
                    this.methodBuilder_.remove(i);
                }
                return this;
            }

            public Builder setMethod(int i, Builder builder) {
                if (this.methodBuilder_ == null) {
                    ensureMethodIsMutable();
                    this.method_.set(i, builder.build());
                    onChanged();
                } else {
                    this.methodBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setMethod(int i, MethodDescriptorProto methodDescriptorProto) {
                if (this.methodBuilder_ != null) {
                    this.methodBuilder_.setMessage(i, methodDescriptorProto);
                } else if (methodDescriptorProto == null) {
                    throw new NullPointerException();
                } else {
                    ensureMethodIsMutable();
                    this.method_.set(i, methodDescriptorProto);
                    onChanged();
                }
                return this;
            }

            public Builder setName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.name_ = byteString;
                onChanged();
                return this;
            }

            public Builder setOptions(Builder builder) {
                if (this.optionsBuilder_ == null) {
                    this.options_ = builder.build();
                    onChanged();
                } else {
                    this.optionsBuilder_.setMessage(builder.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setOptions(ServiceOptions serviceOptions) {
                if (this.optionsBuilder_ != null) {
                    this.optionsBuilder_.setMessage(serviceOptions);
                } else if (serviceOptions == null) {
                    throw new NullPointerException();
                } else {
                    this.options_ = serviceOptions;
                    onChanged();
                }
                this.bitField0_ |= 4;
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private ServiceDescriptorProto(com.google.protobuf.CodedInputStream r9, com.google.protobuf.ExtensionRegistryLite r10) {
            /*
            r8 = this;
            r2 = 1;
            r1 = 0;
            r0 = -1;
            r6 = 2;
            r8.<init>();
            r8.memoizedIsInitialized = r0;
            r8.memoizedSerializedSize = r0;
            r8.initFields();
            r5 = com.google.protobuf.UnknownFieldSet.newBuilder();
            r3 = r1;
        L_0x0013:
            if (r3 != 0) goto L_0x00b2;
        L_0x0015:
            r0 = r9.readTag();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            switch(r0) {
                case 0: goto L_0x00d2;
                case 10: goto L_0x0027;
                case 18: goto L_0x0036;
                case 26: goto L_0x0050;
                default: goto L_0x001c;
            };	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
        L_0x001c:
            r0 = r8.parseUnknownField(r9, r5, r10, r0);	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            if (r0 != 0) goto L_0x00ce;
        L_0x0022:
            r0 = r1;
            r1 = r2;
        L_0x0024:
            r3 = r1;
            r1 = r0;
            goto L_0x0013;
        L_0x0027:
            r0 = r9.readBytes();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r4 = r8.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r4 = r4 | 1;
            r8.bitField0_ = r4;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r8.name_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r1;
            r1 = r3;
            goto L_0x0024;
        L_0x0036:
            r0 = r1 & 2;
            if (r0 == r6) goto L_0x00cb;
        L_0x003a:
            r0 = new java.util.ArrayList;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0.<init>();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r8.method_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r1 | 2;
        L_0x0043:
            r1 = r8.method_;	 Catch:{ InvalidProtocolBufferException -> 0x007d, IOException -> 0x00a0, all -> 0x00d6 }
            r4 = com.google.protobuf.DescriptorProtos.MethodDescriptorProto.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x007d, IOException -> 0x00a0, all -> 0x00d6 }
            r4 = r9.readMessage(r4, r10);	 Catch:{ InvalidProtocolBufferException -> 0x007d, IOException -> 0x00a0, all -> 0x00d6 }
            r1.add(r4);	 Catch:{ InvalidProtocolBufferException -> 0x007d, IOException -> 0x00a0, all -> 0x00d6 }
            r1 = r3;
            goto L_0x0024;
        L_0x0050:
            r0 = r8.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r0 & 2;
            if (r0 != r6) goto L_0x00c8;
        L_0x0056:
            r0 = r8.options_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r0.toBuilder();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r4 = r0;
        L_0x005d:
            r0 = com.google.protobuf.DescriptorProtos.ServiceOptions.PARSER;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r9.readMessage(r0, r10);	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = (com.google.protobuf.DescriptorProtos.ServiceOptions) r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r8.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            if (r4 == 0) goto L_0x0074;
        L_0x0069:
            r0 = r8.options_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r4.mergeFrom(r0);	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r4.buildPartial();	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r8.options_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
        L_0x0074:
            r0 = r8.bitField0_;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r0 | 2;
            r8.bitField0_ = r0;	 Catch:{ InvalidProtocolBufferException -> 0x00da, IOException -> 0x00d8 }
            r0 = r1;
            r1 = r3;
            goto L_0x0024;
        L_0x007d:
            r1 = move-exception;
            r7 = r1;
            r1 = r0;
            r0 = r7;
        L_0x0081:
            r0 = r0.setUnfinishedMessage(r8);	 Catch:{ all -> 0x0086 }
            throw r0;	 Catch:{ all -> 0x0086 }
        L_0x0086:
            r0 = move-exception;
            r7 = r0;
            r0 = r1;
            r1 = r7;
        L_0x008a:
            r0 = r0 & 2;
            if (r0 != r6) goto L_0x0096;
        L_0x008e:
            r0 = r8.method_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r8.method_ = r0;
        L_0x0096:
            r0 = r5.build();
            r8.unknownFields = r0;
            r8.makeExtensionsImmutable();
            throw r1;
        L_0x00a0:
            r1 = move-exception;
            r7 = r1;
            r1 = r0;
            r0 = r7;
        L_0x00a4:
            r2 = new com.google.protobuf.InvalidProtocolBufferException;	 Catch:{ all -> 0x0086 }
            r0 = r0.getMessage();	 Catch:{ all -> 0x0086 }
            r2.<init>(r0);	 Catch:{ all -> 0x0086 }
            r0 = r2.setUnfinishedMessage(r8);	 Catch:{ all -> 0x0086 }
            throw r0;	 Catch:{ all -> 0x0086 }
        L_0x00b2:
            r0 = r1 & 2;
            if (r0 != r6) goto L_0x00be;
        L_0x00b6:
            r0 = r8.method_;
            r0 = java.util.Collections.unmodifiableList(r0);
            r8.method_ = r0;
        L_0x00be:
            r0 = r5.build();
            r8.unknownFields = r0;
            r8.makeExtensionsImmutable();
            return;
        L_0x00c8:
            r0 = 0;
            r4 = r0;
            goto L_0x005d;
        L_0x00cb:
            r0 = r1;
            goto L_0x0043;
        L_0x00ce:
            r0 = r1;
            r1 = r3;
            goto L_0x0024;
        L_0x00d2:
            r0 = r1;
            r1 = r2;
            goto L_0x0024;
        L_0x00d6:
            r1 = move-exception;
            goto L_0x008a;
        L_0x00d8:
            r0 = move-exception;
            goto L_0x00a4;
        L_0x00da:
            r0 = move-exception;
            goto L_0x0081;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.DescriptorProtos.ServiceDescriptorProto.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
        }

        private ServiceDescriptorProto(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ServiceDescriptorProto(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ServiceDescriptorProto getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.f219x158c73ed;
        }

        private void initFields() {
            this.name_ = "";
            this.method_ = Collections.emptyList();
            this.options_ = ServiceOptions.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(ServiceDescriptorProto serviceDescriptorProto) {
            return newBuilder().mergeFrom(serviceDescriptorProto);
        }

        public static ServiceDescriptorProto parseDelimitedFrom(InputStream inputStream) {
            return (ServiceDescriptorProto) PARSER.parseDelimitedFrom(inputStream);
        }

        public static ServiceDescriptorProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceDescriptorProto) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ServiceDescriptorProto parseFrom(ByteString byteString) {
            return (ServiceDescriptorProto) PARSER.parseFrom(byteString);
        }

        public static ServiceDescriptorProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceDescriptorProto) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ServiceDescriptorProto parseFrom(CodedInputStream codedInputStream) {
            return (ServiceDescriptorProto) PARSER.parseFrom(codedInputStream);
        }

        public static ServiceDescriptorProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceDescriptorProto) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static ServiceDescriptorProto parseFrom(InputStream inputStream) {
            return (ServiceDescriptorProto) PARSER.parseFrom(inputStream);
        }

        public static ServiceDescriptorProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceDescriptorProto) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ServiceDescriptorProto parseFrom(byte[] bArr) {
            return (ServiceDescriptorProto) PARSER.parseFrom(bArr);
        }

        public static ServiceDescriptorProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceDescriptorProto) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public ServiceDescriptorProto getDefaultInstanceForType() {
            return defaultInstance;
        }

        public MethodDescriptorProto getMethod(int i) {
            return (MethodDescriptorProto) this.method_.get(i);
        }

        public int getMethodCount() {
            return this.method_.size();
        }

        public List getMethodList() {
            return this.method_;
        }

        public MethodDescriptorProtoOrBuilder getMethodOrBuilder(int i) {
            return (MethodDescriptorProtoOrBuilder) this.method_.get(i);
        }

        public List getMethodOrBuilderList() {
            return this.method_;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public ServiceOptions getOptions() {
            return this.options_;
        }

        public ServiceOptionsOrBuilder getOptionsOrBuilder() {
            return this.options_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int computeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getNameBytes()) + 0 : 0;
            while (i2 < this.method_.size()) {
                i = CodedOutputStream.computeMessageSize(2, (MessageLite) this.method_.get(i2)) + computeBytesSize;
                i2++;
                computeBytesSize = i;
            }
            if ((this.bitField0_ & 2) == 2) {
                computeBytesSize += CodedOutputStream.computeMessageSize(3, this.options_);
            }
            i = getUnknownFields().getSerializedSize() + computeBytesSize;
            this.memoizedSerializedSize = i;
            return i;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasOptions() {
            return (this.bitField0_ & 2) == 2;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f220xee335d6b.ensureFieldAccessorsInitialized(ServiceDescriptorProto.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getMethodCount()) {
                if (getMethod(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (!hasOptions() || getOptions().isInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getNameBytes());
            }
            for (int i = 0; i < this.method_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.method_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(3, this.options_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface ServiceOptionsOrBuilder extends ExtendableMessageOrBuilder {
        boolean getDeprecated();

        UninterpretedOption getUninterpretedOption(int i);

        int getUninterpretedOptionCount();

        List getUninterpretedOptionList();

        UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i);

        List getUninterpretedOptionOrBuilderList();

        boolean hasDeprecated();
    }

    public final class ServiceOptions extends ExtendableMessage implements ServiceOptionsOrBuilder {
        public static final int DEPRECATED_FIELD_NUMBER = 33;
        public static Parser PARSER = new C19831();
        public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
        private static final ServiceOptions defaultInstance = new ServiceOptions(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean deprecated_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List uninterpretedOption_;
        private final UnknownFieldSet unknownFields;

        final class C19831 extends AbstractParser {
            C19831() {
            }

            public ServiceOptions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new ServiceOptions(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends ExtendableBuilder implements ServiceOptionsOrBuilder {
            private int bitField0_;
            private boolean deprecated_;
            private RepeatedFieldBuilder uninterpretedOptionBuilder_;
            private List uninterpretedOption_;

            private Builder() {
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.uninterpretedOption_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureUninterpretedOptionIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.uninterpretedOption_ = new ArrayList(this.uninterpretedOption_);
                    this.bitField0_ |= 2;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
            }

            private RepeatedFieldBuilder getUninterpretedOptionFieldBuilder() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder(this.uninterpretedOption_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.uninterpretedOption_ = null;
                }
                return this.uninterpretedOptionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getUninterpretedOptionFieldBuilder();
                }
            }

            public Builder addAllUninterpretedOption(Iterable iterable) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.uninterpretedOption_);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOption(Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addUninterpretedOption(UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.addMessage(uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.add(uninterpretedOption);
                    onChanged();
                }
                return this;
            }

            public Builder addUninterpretedOptionBuilder() {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(UninterpretedOption.getDefaultInstance());
            }

            public Builder addUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().addBuilder(i, UninterpretedOption.getDefaultInstance());
            }

            public ServiceOptions build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public ServiceOptions buildPartial() {
                int i = 1;
                ServiceOptions serviceOptions = new ServiceOptions((ExtendableBuilder) this);
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                serviceOptions.deprecated_ = this.deprecated_;
                if (this.uninterpretedOptionBuilder_ == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                        this.bitField0_ &= -3;
                    }
                    serviceOptions.uninterpretedOption_ = this.uninterpretedOption_;
                } else {
                    serviceOptions.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
                }
                serviceOptions.bitField0_ = i;
                onBuilt();
                return serviceOptions;
            }

            public Builder clear() {
                super.clear();
                this.deprecated_ = false;
                this.bitField0_ &= -2;
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clearDeprecated() {
                this.bitField0_ &= -2;
                this.deprecated_ = false;
                onChanged();
                return this;
            }

            public Builder clearUninterpretedOption() {
                if (this.uninterpretedOptionBuilder_ == null) {
                    this.uninterpretedOption_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public ServiceOptions getDefaultInstanceForType() {
                return ServiceOptions.getDefaultInstance();
            }

            public boolean getDeprecated() {
                return this.deprecated_;
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
            }

            public UninterpretedOption getUninterpretedOption(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOption) this.uninterpretedOption_.get(i) : (UninterpretedOption) this.uninterpretedOptionBuilder_.getMessage(i);
            }

            public Builder getUninterpretedOptionBuilder(int i) {
                return (Builder) getUninterpretedOptionFieldBuilder().getBuilder(i);
            }

            public List getUninterpretedOptionBuilderList() {
                return getUninterpretedOptionFieldBuilder().getBuilderList();
            }

            public int getUninterpretedOptionCount() {
                return this.uninterpretedOptionBuilder_ == null ? this.uninterpretedOption_.size() : this.uninterpretedOptionBuilder_.getCount();
            }

            public List getUninterpretedOptionList() {
                return this.uninterpretedOptionBuilder_ == null ? Collections.unmodifiableList(this.uninterpretedOption_) : this.uninterpretedOptionBuilder_.getMessageList();
            }

            public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
                return this.uninterpretedOptionBuilder_ == null ? (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i) : (UninterpretedOptionOrBuilder) this.uninterpretedOptionBuilder_.getMessageOrBuilder(i);
            }

            public List getUninterpretedOptionOrBuilderList() {
                return this.uninterpretedOptionBuilder_ != null ? this.uninterpretedOptionBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.uninterpretedOption_);
            }

            public boolean hasDeprecated() {
                return (this.bitField0_ & 1) == 1;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f221x371e666.ensureFieldAccessorsInitialized(ServiceOptions.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getUninterpretedOptionCount(); i++) {
                    if (!getUninterpretedOption(i).isInitialized()) {
                        return false;
                    }
                }
                return extensionsAreInitialized();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                ServiceOptions serviceOptions;
                Throwable th;
                try {
                    serviceOptions = (ServiceOptions) ServiceOptions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (serviceOptions != null) {
                        mergeFrom(serviceOptions);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    serviceOptions = (ServiceOptions) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (serviceOptions != null) {
                    mergeFrom(serviceOptions);
                }
                throw th;
            }

            public Builder mergeFrom(ServiceOptions serviceOptions) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (serviceOptions != ServiceOptions.getDefaultInstance()) {
                    if (serviceOptions.hasDeprecated()) {
                        setDeprecated(serviceOptions.getDeprecated());
                    }
                    if (this.uninterpretedOptionBuilder_ == null) {
                        if (!serviceOptions.uninterpretedOption_.isEmpty()) {
                            if (this.uninterpretedOption_.isEmpty()) {
                                this.uninterpretedOption_ = serviceOptions.uninterpretedOption_;
                                this.bitField0_ &= -3;
                            } else {
                                ensureUninterpretedOptionIsMutable();
                                this.uninterpretedOption_.addAll(serviceOptions.uninterpretedOption_);
                            }
                            onChanged();
                        }
                    } else if (!serviceOptions.uninterpretedOption_.isEmpty()) {
                        if (this.uninterpretedOptionBuilder_.isEmpty()) {
                            this.uninterpretedOptionBuilder_.dispose();
                            this.uninterpretedOptionBuilder_ = null;
                            this.uninterpretedOption_ = serviceOptions.uninterpretedOption_;
                            this.bitField0_ &= -3;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getUninterpretedOptionFieldBuilder();
                            }
                            this.uninterpretedOptionBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.uninterpretedOptionBuilder_.addAllMessages(serviceOptions.uninterpretedOption_);
                        }
                    }
                    mergeExtensionFields(serviceOptions);
                    mergeUnknownFields(serviceOptions.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof ServiceOptions) {
                    return mergeFrom((ServiceOptions) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeUninterpretedOption(int i) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.remove(i);
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.remove(i);
                }
                return this;
            }

            public Builder setDeprecated(boolean z) {
                this.bitField0_ |= 1;
                this.deprecated_ = z;
                onChanged();
                return this;
            }

            public Builder setUninterpretedOption(int i, Builder builder) {
                if (this.uninterpretedOptionBuilder_ == null) {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, builder.build());
                    onChanged();
                } else {
                    this.uninterpretedOptionBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setUninterpretedOption(int i, UninterpretedOption uninterpretedOption) {
                if (this.uninterpretedOptionBuilder_ != null) {
                    this.uninterpretedOptionBuilder_.setMessage(i, uninterpretedOption);
                } else if (uninterpretedOption == null) {
                    throw new NullPointerException();
                } else {
                    ensureUninterpretedOptionIsMutable();
                    this.uninterpretedOption_.set(i, uninterpretedOption);
                    onChanged();
                }
                return this;
            }
        }

        static {
            defaultInstance.initFields();
        }

        private ServiceOptions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 264:
                            this.bitField0_ |= 1;
                            this.deprecated_ = codedInputStream.readBool();
                            break;
                        case 7994:
                            if ((i & 2) != 2) {
                                this.uninterpretedOption_ = new ArrayList();
                                i |= 2;
                            }
                            this.uninterpretedOption_.add(codedInputStream.readMessage(UninterpretedOption.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 2) == 2) {
                        this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 2) == 2) {
                this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private ServiceOptions(ExtendableBuilder extendableBuilder) {
            super(extendableBuilder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = extendableBuilder.getUnknownFields();
        }

        private ServiceOptions(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ServiceOptions getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
        }

        private void initFields() {
            this.deprecated_ = false;
            this.uninterpretedOption_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(ServiceOptions serviceOptions) {
            return newBuilder().mergeFrom(serviceOptions);
        }

        public static ServiceOptions parseDelimitedFrom(InputStream inputStream) {
            return (ServiceOptions) PARSER.parseDelimitedFrom(inputStream);
        }

        public static ServiceOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceOptions) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ServiceOptions parseFrom(ByteString byteString) {
            return (ServiceOptions) PARSER.parseFrom(byteString);
        }

        public static ServiceOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceOptions) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ServiceOptions parseFrom(CodedInputStream codedInputStream) {
            return (ServiceOptions) PARSER.parseFrom(codedInputStream);
        }

        public static ServiceOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceOptions) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static ServiceOptions parseFrom(InputStream inputStream) {
            return (ServiceOptions) PARSER.parseFrom(inputStream);
        }

        public static ServiceOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceOptions) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ServiceOptions parseFrom(byte[] bArr) {
            return (ServiceOptions) PARSER.parseFrom(bArr);
        }

        public static ServiceOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ServiceOptions) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public ServiceOptions getDefaultInstanceForType() {
            return defaultInstance;
        }

        public boolean getDeprecated() {
            return this.deprecated_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int computeBoolSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBoolSize(33, this.deprecated_) + 0 : 0;
            while (i2 < this.uninterpretedOption_.size()) {
                i = CodedOutputStream.computeMessageSize(999, (MessageLite) this.uninterpretedOption_.get(i2)) + computeBoolSize;
                i2++;
                computeBoolSize = i;
            }
            i = (extensionsSerializedSize() + computeBoolSize) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = i;
            return i;
        }

        public UninterpretedOption getUninterpretedOption(int i) {
            return (UninterpretedOption) this.uninterpretedOption_.get(i);
        }

        public int getUninterpretedOptionCount() {
            return this.uninterpretedOption_.size();
        }

        public List getUninterpretedOptionList() {
            return this.uninterpretedOption_;
        }

        public UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int i) {
            return (UninterpretedOptionOrBuilder) this.uninterpretedOption_.get(i);
        }

        public List getUninterpretedOptionOrBuilderList() {
            return this.uninterpretedOption_;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasDeprecated() {
            return (this.bitField0_ & 1) == 1;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f221x371e666.ensureFieldAccessorsInitialized(ServiceOptions.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getUninterpretedOptionCount()) {
                if (getUninterpretedOption(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            if (extensionsAreInitialized()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            ExtensionWriter newExtensionWriter = newExtensionWriter();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBool(33, this.deprecated_);
            }
            for (int i = 0; i < this.uninterpretedOption_.size(); i++) {
                codedOutputStream.writeMessage(999, (MessageLite) this.uninterpretedOption_.get(i));
            }
            newExtensionWriter.writeUntil(536870912, codedOutputStream);
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface SourceCodeInfoOrBuilder extends MessageOrBuilder {
        Location getLocation(int i);

        int getLocationCount();

        List getLocationList();

        LocationOrBuilder getLocationOrBuilder(int i);

        List getLocationOrBuilderList();
    }

    public final class SourceCodeInfo extends GeneratedMessage implements SourceCodeInfoOrBuilder {
        public static final int LOCATION_FIELD_NUMBER = 1;
        public static Parser PARSER = new C19841();
        private static final SourceCodeInfo defaultInstance = new SourceCodeInfo(true);
        private static final long serialVersionUID = 0;
        private List location_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        final class C19841 extends AbstractParser {
            C19841() {
            }

            public SourceCodeInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new SourceCodeInfo(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements SourceCodeInfoOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilder locationBuilder_;
            private List location_;

            private Builder() {
                this.location_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.location_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureLocationIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.location_ = new ArrayList(this.location_);
                    this.bitField0_ |= 1;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
            }

            private RepeatedFieldBuilder getLocationFieldBuilder() {
                boolean z = true;
                if (this.locationBuilder_ == null) {
                    List list = this.location_;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.locationBuilder_ = new RepeatedFieldBuilder(list, z, getParentForChildren(), isClean());
                    this.location_ = null;
                }
                return this.locationBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getLocationFieldBuilder();
                }
            }

            public Builder addAllLocation(Iterable iterable) {
                if (this.locationBuilder_ == null) {
                    ensureLocationIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.location_);
                    onChanged();
                } else {
                    this.locationBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addLocation(int i, Builder builder) {
                if (this.locationBuilder_ == null) {
                    ensureLocationIsMutable();
                    this.location_.add(i, builder.build());
                    onChanged();
                } else {
                    this.locationBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addLocation(int i, Location location) {
                if (this.locationBuilder_ != null) {
                    this.locationBuilder_.addMessage(i, location);
                } else if (location == null) {
                    throw new NullPointerException();
                } else {
                    ensureLocationIsMutable();
                    this.location_.add(i, location);
                    onChanged();
                }
                return this;
            }

            public Builder addLocation(Builder builder) {
                if (this.locationBuilder_ == null) {
                    ensureLocationIsMutable();
                    this.location_.add(builder.build());
                    onChanged();
                } else {
                    this.locationBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addLocation(Location location) {
                if (this.locationBuilder_ != null) {
                    this.locationBuilder_.addMessage(location);
                } else if (location == null) {
                    throw new NullPointerException();
                } else {
                    ensureLocationIsMutable();
                    this.location_.add(location);
                    onChanged();
                }
                return this;
            }

            public Builder addLocationBuilder() {
                return (Builder) getLocationFieldBuilder().addBuilder(Location.getDefaultInstance());
            }

            public Builder addLocationBuilder(int i) {
                return (Builder) getLocationFieldBuilder().addBuilder(i, Location.getDefaultInstance());
            }

            public SourceCodeInfo build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public SourceCodeInfo buildPartial() {
                SourceCodeInfo sourceCodeInfo = new SourceCodeInfo((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                if (this.locationBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.location_ = Collections.unmodifiableList(this.location_);
                        this.bitField0_ &= -2;
                    }
                    sourceCodeInfo.location_ = this.location_;
                } else {
                    sourceCodeInfo.location_ = this.locationBuilder_.build();
                }
                onBuilt();
                return sourceCodeInfo;
            }

            public Builder clear() {
                super.clear();
                if (this.locationBuilder_ == null) {
                    this.location_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    this.locationBuilder_.clear();
                }
                return this;
            }

            public Builder clearLocation() {
                if (this.locationBuilder_ == null) {
                    this.location_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    this.locationBuilder_.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public SourceCodeInfo getDefaultInstanceForType() {
                return SourceCodeInfo.getDefaultInstance();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
            }

            public Location getLocation(int i) {
                return this.locationBuilder_ == null ? (Location) this.location_.get(i) : (Location) this.locationBuilder_.getMessage(i);
            }

            public Builder getLocationBuilder(int i) {
                return (Builder) getLocationFieldBuilder().getBuilder(i);
            }

            public List getLocationBuilderList() {
                return getLocationFieldBuilder().getBuilderList();
            }

            public int getLocationCount() {
                return this.locationBuilder_ == null ? this.location_.size() : this.locationBuilder_.getCount();
            }

            public List getLocationList() {
                return this.locationBuilder_ == null ? Collections.unmodifiableList(this.location_) : this.locationBuilder_.getMessageList();
            }

            public LocationOrBuilder getLocationOrBuilder(int i) {
                return this.locationBuilder_ == null ? (LocationOrBuilder) this.location_.get(i) : (LocationOrBuilder) this.locationBuilder_.getMessageOrBuilder(i);
            }

            public List getLocationOrBuilderList() {
                return this.locationBuilder_ != null ? this.locationBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.location_);
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f224x532209f9.ensureFieldAccessorsInitialized(SourceCodeInfo.class, Builder.class);
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                SourceCodeInfo sourceCodeInfo;
                Throwable th;
                try {
                    sourceCodeInfo = (SourceCodeInfo) SourceCodeInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (sourceCodeInfo != null) {
                        mergeFrom(sourceCodeInfo);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    sourceCodeInfo = (SourceCodeInfo) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (sourceCodeInfo != null) {
                    mergeFrom(sourceCodeInfo);
                }
                throw th;
            }

            public Builder mergeFrom(SourceCodeInfo sourceCodeInfo) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (sourceCodeInfo != SourceCodeInfo.getDefaultInstance()) {
                    if (this.locationBuilder_ == null) {
                        if (!sourceCodeInfo.location_.isEmpty()) {
                            if (this.location_.isEmpty()) {
                                this.location_ = sourceCodeInfo.location_;
                                this.bitField0_ &= -2;
                            } else {
                                ensureLocationIsMutable();
                                this.location_.addAll(sourceCodeInfo.location_);
                            }
                            onChanged();
                        }
                    } else if (!sourceCodeInfo.location_.isEmpty()) {
                        if (this.locationBuilder_.isEmpty()) {
                            this.locationBuilder_.dispose();
                            this.locationBuilder_ = null;
                            this.location_ = sourceCodeInfo.location_;
                            this.bitField0_ &= -2;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getLocationFieldBuilder();
                            }
                            this.locationBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.locationBuilder_.addAllMessages(sourceCodeInfo.location_);
                        }
                    }
                    mergeUnknownFields(sourceCodeInfo.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof SourceCodeInfo) {
                    return mergeFrom((SourceCodeInfo) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeLocation(int i) {
                if (this.locationBuilder_ == null) {
                    ensureLocationIsMutable();
                    this.location_.remove(i);
                    onChanged();
                } else {
                    this.locationBuilder_.remove(i);
                }
                return this;
            }

            public Builder setLocation(int i, Builder builder) {
                if (this.locationBuilder_ == null) {
                    ensureLocationIsMutable();
                    this.location_.set(i, builder.build());
                    onChanged();
                } else {
                    this.locationBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setLocation(int i, Location location) {
                if (this.locationBuilder_ != null) {
                    this.locationBuilder_.setMessage(i, location);
                } else if (location == null) {
                    throw new NullPointerException();
                } else {
                    ensureLocationIsMutable();
                    this.location_.set(i, location);
                    onChanged();
                }
                return this;
            }
        }

        public interface LocationOrBuilder extends MessageOrBuilder {
            String getLeadingComments();

            ByteString getLeadingCommentsBytes();

            int getPath(int i);

            int getPathCount();

            List getPathList();

            int getSpan(int i);

            int getSpanCount();

            List getSpanList();

            String getTrailingComments();

            ByteString getTrailingCommentsBytes();

            boolean hasLeadingComments();

            boolean hasTrailingComments();
        }

        public final class Location extends GeneratedMessage implements LocationOrBuilder {
            public static final int LEADING_COMMENTS_FIELD_NUMBER = 3;
            public static Parser PARSER = new C19851();
            public static final int PATH_FIELD_NUMBER = 1;
            public static final int SPAN_FIELD_NUMBER = 2;
            public static final int TRAILING_COMMENTS_FIELD_NUMBER = 4;
            private static final Location defaultInstance = new Location(true);
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private Object leadingComments_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            private int pathMemoizedSerializedSize;
            private List path_;
            private int spanMemoizedSerializedSize;
            private List span_;
            private Object trailingComments_;
            private final UnknownFieldSet unknownFields;

            final class C19851 extends AbstractParser {
                C19851() {
                }

                public Location parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new Location(codedInputStream, extensionRegistryLite);
                }
            }

            public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements LocationOrBuilder {
                private int bitField0_;
                private Object leadingComments_;
                private List path_;
                private List span_;
                private Object trailingComments_;

                private Builder() {
                    this.path_ = Collections.emptyList();
                    this.span_ = Collections.emptyList();
                    this.leadingComments_ = "";
                    this.trailingComments_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(BuilderParent builderParent) {
                    super(builderParent);
                    this.path_ = Collections.emptyList();
                    this.span_ = Collections.emptyList();
                    this.leadingComments_ = "";
                    this.trailingComments_ = "";
                    maybeForceBuilderInitialization();
                }

                private static Builder create() {
                    return new Builder();
                }

                private void ensurePathIsMutable() {
                    if ((this.bitField0_ & 1) != 1) {
                        this.path_ = new ArrayList(this.path_);
                        this.bitField0_ |= 1;
                    }
                }

                private void ensureSpanIsMutable() {
                    if ((this.bitField0_ & 2) != 2) {
                        this.span_ = new ArrayList(this.span_);
                        this.bitField0_ |= 2;
                    }
                }

                public static final Descriptor getDescriptor() {
                    return DescriptorProtos.f222xb210d08d;
                }

                private void maybeForceBuilderInitialization() {
                    if (!GeneratedMessage.alwaysUseFieldBuilders) {
                    }
                }

                public Builder addAllPath(Iterable iterable) {
                    ensurePathIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.path_);
                    onChanged();
                    return this;
                }

                public Builder addAllSpan(Iterable iterable) {
                    ensureSpanIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.span_);
                    onChanged();
                    return this;
                }

                public Builder addPath(int i) {
                    ensurePathIsMutable();
                    this.path_.add(Integer.valueOf(i));
                    onChanged();
                    return this;
                }

                public Builder addSpan(int i) {
                    ensureSpanIsMutable();
                    this.span_.add(Integer.valueOf(i));
                    onChanged();
                    return this;
                }

                public Location build() {
                    Object buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
                }

                public Location buildPartial() {
                    int i = 1;
                    Location location = new Location((com.google.protobuf.GeneratedMessage.Builder) this);
                    int i2 = this.bitField0_;
                    if ((this.bitField0_ & 1) == 1) {
                        this.path_ = Collections.unmodifiableList(this.path_);
                        this.bitField0_ &= -2;
                    }
                    location.path_ = this.path_;
                    if ((this.bitField0_ & 2) == 2) {
                        this.span_ = Collections.unmodifiableList(this.span_);
                        this.bitField0_ &= -3;
                    }
                    location.span_ = this.span_;
                    if ((i2 & 4) != 4) {
                        i = 0;
                    }
                    location.leadingComments_ = this.leadingComments_;
                    if ((i2 & 8) == 8) {
                        i |= 2;
                    }
                    location.trailingComments_ = this.trailingComments_;
                    location.bitField0_ = i;
                    onBuilt();
                    return location;
                }

                public Builder clear() {
                    super.clear();
                    this.path_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    this.span_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    this.leadingComments_ = "";
                    this.bitField0_ &= -5;
                    this.trailingComments_ = "";
                    this.bitField0_ &= -9;
                    return this;
                }

                public Builder clearLeadingComments() {
                    this.bitField0_ &= -5;
                    this.leadingComments_ = Location.getDefaultInstance().getLeadingComments();
                    onChanged();
                    return this;
                }

                public Builder clearPath() {
                    this.path_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                    return this;
                }

                public Builder clearSpan() {
                    this.span_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                    return this;
                }

                public Builder clearTrailingComments() {
                    this.bitField0_ &= -9;
                    this.trailingComments_ = Location.getDefaultInstance().getTrailingComments();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return create().mergeFrom(buildPartial());
                }

                public Location getDefaultInstanceForType() {
                    return Location.getDefaultInstance();
                }

                public Descriptor getDescriptorForType() {
                    return DescriptorProtos.f222xb210d08d;
                }

                public String getLeadingComments() {
                    Object obj = this.leadingComments_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String toStringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.leadingComments_ = toStringUtf8;
                    }
                    return toStringUtf8;
                }

                public ByteString getLeadingCommentsBytes() {
                    Object obj = this.leadingComments_;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.leadingComments_ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public int getPath(int i) {
                    return ((Integer) this.path_.get(i)).intValue();
                }

                public int getPathCount() {
                    return this.path_.size();
                }

                public List getPathList() {
                    return Collections.unmodifiableList(this.path_);
                }

                public int getSpan(int i) {
                    return ((Integer) this.span_.get(i)).intValue();
                }

                public int getSpanCount() {
                    return this.span_.size();
                }

                public List getSpanList() {
                    return Collections.unmodifiableList(this.span_);
                }

                public String getTrailingComments() {
                    Object obj = this.trailingComments_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String toStringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.trailingComments_ = toStringUtf8;
                    }
                    return toStringUtf8;
                }

                public ByteString getTrailingCommentsBytes() {
                    Object obj = this.trailingComments_;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.trailingComments_ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasLeadingComments() {
                    return (this.bitField0_ & 4) == 4;
                }

                public boolean hasTrailingComments() {
                    return (this.bitField0_ & 8) == 8;
                }

                protected FieldAccessorTable internalGetFieldAccessorTable() {
                    return DescriptorProtos.f223x9611a0b.ensureFieldAccessorsInitialized(Location.class, Builder.class);
                }

                public final boolean isInitialized() {
                    return true;
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    Location location;
                    Throwable th;
                    try {
                        location = (Location) Location.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (location != null) {
                            mergeFrom(location);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        InvalidProtocolBufferException invalidProtocolBufferException = e;
                        location = (Location) invalidProtocolBufferException.getUnfinishedMessage();
                        throw invalidProtocolBufferException;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    if (location != null) {
                        mergeFrom(location);
                    }
                    throw th;
                }

                public Builder mergeFrom(Location location) {
                    if (location != Location.getDefaultInstance()) {
                        if (!location.path_.isEmpty()) {
                            if (this.path_.isEmpty()) {
                                this.path_ = location.path_;
                                this.bitField0_ &= -2;
                            } else {
                                ensurePathIsMutable();
                                this.path_.addAll(location.path_);
                            }
                            onChanged();
                        }
                        if (!location.span_.isEmpty()) {
                            if (this.span_.isEmpty()) {
                                this.span_ = location.span_;
                                this.bitField0_ &= -3;
                            } else {
                                ensureSpanIsMutable();
                                this.span_.addAll(location.span_);
                            }
                            onChanged();
                        }
                        if (location.hasLeadingComments()) {
                            this.bitField0_ |= 4;
                            this.leadingComments_ = location.leadingComments_;
                            onChanged();
                        }
                        if (location.hasTrailingComments()) {
                            this.bitField0_ |= 8;
                            this.trailingComments_ = location.trailingComments_;
                            onChanged();
                        }
                        mergeUnknownFields(location.getUnknownFields());
                    }
                    return this;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof Location) {
                        return mergeFrom((Location) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder setLeadingComments(String str) {
                    if (str == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 4;
                    this.leadingComments_ = str;
                    onChanged();
                    return this;
                }

                public Builder setLeadingCommentsBytes(ByteString byteString) {
                    if (byteString == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 4;
                    this.leadingComments_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder setPath(int i, int i2) {
                    ensurePathIsMutable();
                    this.path_.set(i, Integer.valueOf(i2));
                    onChanged();
                    return this;
                }

                public Builder setSpan(int i, int i2) {
                    ensureSpanIsMutable();
                    this.span_.set(i, Integer.valueOf(i2));
                    onChanged();
                    return this;
                }

                public Builder setTrailingComments(String str) {
                    if (str == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 8;
                    this.trailingComments_ = str;
                    onChanged();
                    return this;
                }

                public Builder setTrailingCommentsBytes(ByteString byteString) {
                    if (byteString == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 8;
                    this.trailingComments_ = byteString;
                    onChanged();
                    return this;
                }
            }

            static {
                defaultInstance.initFields();
            }

            private Location(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                int i = 0;
                this.pathMemoizedSerializedSize = -1;
                this.spanMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                initFields();
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                int i2 = 0;
                while (i2 == 0) {
                    try {
                        int readTag = codedInputStream.readTag();
                        ByteString readBytes;
                        switch (readTag) {
                            case 0:
                                i2 = 1;
                                break;
                            case 8:
                                if ((i & 1) != 1) {
                                    this.path_ = new ArrayList();
                                    i |= 1;
                                }
                                this.path_.add(Integer.valueOf(codedInputStream.readInt32()));
                                break;
                            case 10:
                                readTag = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 1) != 1 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.path_ = new ArrayList();
                                    i |= 1;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.path_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(readTag);
                                break;
                            case 16:
                                if ((i & 2) != 2) {
                                    this.span_ = new ArrayList();
                                    i |= 2;
                                }
                                this.span_.add(Integer.valueOf(codedInputStream.readInt32()));
                                break;
                            case 18:
                                readTag = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 2) != 2 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.span_ = new ArrayList();
                                    i |= 2;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.span_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(readTag);
                                break;
                            case 26:
                                readBytes = codedInputStream.readBytes();
                                this.bitField0_ |= 1;
                                this.leadingComments_ = readBytes;
                                break;
                            case 34:
                                readBytes = codedInputStream.readBytes();
                                this.bitField0_ |= 2;
                                this.trailingComments_ = readBytes;
                                break;
                            default:
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    i2 = 1;
                                    break;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if ((i & 1) == 1) {
                            this.path_ = Collections.unmodifiableList(this.path_);
                        }
                        if ((i & 2) == 2) {
                            this.span_ = Collections.unmodifiableList(this.span_);
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
                if ((i & 1) == 1) {
                    this.path_ = Collections.unmodifiableList(this.path_);
                }
                if ((i & 2) == 2) {
                    this.span_ = Collections.unmodifiableList(this.span_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }

            private Location(com.google.protobuf.GeneratedMessage.Builder builder) {
                super(builder);
                this.pathMemoizedSerializedSize = -1;
                this.spanMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private Location(boolean z) {
                this.pathMemoizedSerializedSize = -1;
                this.spanMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static Location getDefaultInstance() {
                return defaultInstance;
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.f222xb210d08d;
            }

            private void initFields() {
                this.path_ = Collections.emptyList();
                this.span_ = Collections.emptyList();
                this.leadingComments_ = "";
                this.trailingComments_ = "";
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            public static Builder newBuilder(Location location) {
                return newBuilder().mergeFrom(location);
            }

            public static Location parseDelimitedFrom(InputStream inputStream) {
                return (Location) PARSER.parseDelimitedFrom(inputStream);
            }

            public static Location parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Location) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static Location parseFrom(ByteString byteString) {
                return (Location) PARSER.parseFrom(byteString);
            }

            public static Location parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (Location) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Location parseFrom(CodedInputStream codedInputStream) {
                return (Location) PARSER.parseFrom(codedInputStream);
            }

            public static Location parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Location) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static Location parseFrom(InputStream inputStream) {
                return (Location) PARSER.parseFrom(inputStream);
            }

            public static Location parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (Location) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static Location parseFrom(byte[] bArr) {
                return (Location) PARSER.parseFrom(bArr);
            }

            public static Location parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (Location) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public Location getDefaultInstanceForType() {
                return defaultInstance;
            }

            public String getLeadingComments() {
                Object obj = this.leadingComments_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.leadingComments_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getLeadingCommentsBytes() {
                Object obj = this.leadingComments_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.leadingComments_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getPath(int i) {
                return ((Integer) this.path_.get(i)).intValue();
            }

            public int getPathCount() {
                return this.path_.size();
            }

            public List getPathList() {
                return this.path_;
            }

            public int getSerializedSize() {
                int i = 0;
                int i2 = this.memoizedSerializedSize;
                if (i2 != -1) {
                    return i2;
                }
                int i3 = 0;
                int i4 = 0;
                while (i4 < this.path_.size()) {
                    i2 = CodedOutputStream.computeInt32SizeNoTag(((Integer) this.path_.get(i4)).intValue()) + i3;
                    i4++;
                    i3 = i2;
                }
                i2 = i3 + 0;
                i4 = !getPathList().isEmpty() ? (i2 + 1) + CodedOutputStream.computeInt32SizeNoTag(i3) : i2;
                this.pathMemoizedSerializedSize = i3;
                for (i3 = 0; i3 < this.span_.size(); i3++) {
                    i += CodedOutputStream.computeInt32SizeNoTag(((Integer) this.span_.get(i3)).intValue());
                }
                i2 = i4 + i;
                if (!getSpanList().isEmpty()) {
                    i2 = (i2 + 1) + CodedOutputStream.computeInt32SizeNoTag(i);
                }
                this.spanMemoizedSerializedSize = i;
                if ((this.bitField0_ & 1) == 1) {
                    i2 += CodedOutputStream.computeBytesSize(3, getLeadingCommentsBytes());
                }
                if ((this.bitField0_ & 2) == 2) {
                    i2 += CodedOutputStream.computeBytesSize(4, getTrailingCommentsBytes());
                }
                i2 += getUnknownFields().getSerializedSize();
                this.memoizedSerializedSize = i2;
                return i2;
            }

            public int getSpan(int i) {
                return ((Integer) this.span_.get(i)).intValue();
            }

            public int getSpanCount() {
                return this.span_.size();
            }

            public List getSpanList() {
                return this.span_;
            }

            public String getTrailingComments() {
                Object obj = this.trailingComments_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.trailingComments_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getTrailingCommentsBytes() {
                Object obj = this.trailingComments_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.trailingComments_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            public boolean hasLeadingComments() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasTrailingComments() {
                return (this.bitField0_ & 2) == 2;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f223x9611a0b.ensureFieldAccessorsInitialized(Location.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == (byte) 1) {
                    return true;
                }
                if (b == (byte) 0) {
                    return false;
                }
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            protected Builder newBuilderForType(BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            protected Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                int i = 0;
                getSerializedSize();
                if (getPathList().size() > 0) {
                    codedOutputStream.writeRawVarint32(10);
                    codedOutputStream.writeRawVarint32(this.pathMemoizedSerializedSize);
                }
                for (int i2 = 0; i2 < this.path_.size(); i2++) {
                    codedOutputStream.writeInt32NoTag(((Integer) this.path_.get(i2)).intValue());
                }
                if (getSpanList().size() > 0) {
                    codedOutputStream.writeRawVarint32(18);
                    codedOutputStream.writeRawVarint32(this.spanMemoizedSerializedSize);
                }
                while (i < this.span_.size()) {
                    codedOutputStream.writeInt32NoTag(((Integer) this.span_.get(i)).intValue());
                    i++;
                }
                if ((this.bitField0_ & 1) == 1) {
                    codedOutputStream.writeBytes(3, getLeadingCommentsBytes());
                }
                if ((this.bitField0_ & 2) == 2) {
                    codedOutputStream.writeBytes(4, getTrailingCommentsBytes());
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        static {
            defaultInstance.initFields();
        }

        private SourceCodeInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 10:
                            if ((i & 1) != 1) {
                                this.location_ = new ArrayList();
                                i |= 1;
                            }
                            this.location_.add(codedInputStream.readMessage(Location.PARSER, extensionRegistryLite));
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 1) == 1) {
                        this.location_ = Collections.unmodifiableList(this.location_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 1) == 1) {
                this.location_ = Collections.unmodifiableList(this.location_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private SourceCodeInfo(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private SourceCodeInfo(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static SourceCodeInfo getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
        }

        private void initFields() {
            this.location_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(SourceCodeInfo sourceCodeInfo) {
            return newBuilder().mergeFrom(sourceCodeInfo);
        }

        public static SourceCodeInfo parseDelimitedFrom(InputStream inputStream) {
            return (SourceCodeInfo) PARSER.parseDelimitedFrom(inputStream);
        }

        public static SourceCodeInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (SourceCodeInfo) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static SourceCodeInfo parseFrom(ByteString byteString) {
            return (SourceCodeInfo) PARSER.parseFrom(byteString);
        }

        public static SourceCodeInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (SourceCodeInfo) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static SourceCodeInfo parseFrom(CodedInputStream codedInputStream) {
            return (SourceCodeInfo) PARSER.parseFrom(codedInputStream);
        }

        public static SourceCodeInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (SourceCodeInfo) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static SourceCodeInfo parseFrom(InputStream inputStream) {
            return (SourceCodeInfo) PARSER.parseFrom(inputStream);
        }

        public static SourceCodeInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (SourceCodeInfo) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static SourceCodeInfo parseFrom(byte[] bArr) {
            return (SourceCodeInfo) PARSER.parseFrom(bArr);
        }

        public static SourceCodeInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (SourceCodeInfo) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public SourceCodeInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        public Location getLocation(int i) {
            return (Location) this.location_.get(i);
        }

        public int getLocationCount() {
            return this.location_.size();
        }

        public List getLocationList() {
            return this.location_;
        }

        public LocationOrBuilder getLocationOrBuilder(int i) {
            return (LocationOrBuilder) this.location_.get(i);
        }

        public List getLocationOrBuilderList() {
            return this.location_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize;
            i = 0;
            int i2 = 0;
            while (i2 < this.location_.size()) {
                computeMessageSize = CodedOutputStream.computeMessageSize(1, (MessageLite) this.location_.get(i2)) + i;
                i2++;
                i = computeMessageSize;
            }
            computeMessageSize = getUnknownFields().getSerializedSize() + i;
            this.memoizedSerializedSize = computeMessageSize;
            return computeMessageSize;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f224x532209f9.ensureFieldAccessorsInitialized(SourceCodeInfo.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            for (int i = 0; i < this.location_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.location_.get(i));
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    public interface UninterpretedOptionOrBuilder extends MessageOrBuilder {
        String getAggregateValue();

        ByteString getAggregateValueBytes();

        double getDoubleValue();

        String getIdentifierValue();

        ByteString getIdentifierValueBytes();

        NamePart getName(int i);

        int getNameCount();

        List getNameList();

        NamePartOrBuilder getNameOrBuilder(int i);

        List getNameOrBuilderList();

        long getNegativeIntValue();

        long getPositiveIntValue();

        ByteString getStringValue();

        boolean hasAggregateValue();

        boolean hasDoubleValue();

        boolean hasIdentifierValue();

        boolean hasNegativeIntValue();

        boolean hasPositiveIntValue();

        boolean hasStringValue();
    }

    public final class UninterpretedOption extends GeneratedMessage implements UninterpretedOptionOrBuilder {
        public static final int AGGREGATE_VALUE_FIELD_NUMBER = 8;
        public static final int DOUBLE_VALUE_FIELD_NUMBER = 6;
        public static final int IDENTIFIER_VALUE_FIELD_NUMBER = 3;
        public static final int NAME_FIELD_NUMBER = 2;
        public static final int NEGATIVE_INT_VALUE_FIELD_NUMBER = 5;
        public static Parser PARSER = new C19861();
        public static final int POSITIVE_INT_VALUE_FIELD_NUMBER = 4;
        public static final int STRING_VALUE_FIELD_NUMBER = 7;
        private static final UninterpretedOption defaultInstance = new UninterpretedOption(true);
        private static final long serialVersionUID = 0;
        private Object aggregateValue_;
        private int bitField0_;
        private double doubleValue_;
        private Object identifierValue_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List name_;
        private long negativeIntValue_;
        private long positiveIntValue_;
        private ByteString stringValue_;
        private final UnknownFieldSet unknownFields;

        final class C19861 extends AbstractParser {
            C19861() {
            }

            public UninterpretedOption parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return new UninterpretedOption(codedInputStream, extensionRegistryLite);
            }
        }

        public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements UninterpretedOptionOrBuilder {
            private Object aggregateValue_;
            private int bitField0_;
            private double doubleValue_;
            private Object identifierValue_;
            private RepeatedFieldBuilder nameBuilder_;
            private List name_;
            private long negativeIntValue_;
            private long positiveIntValue_;
            private ByteString stringValue_;

            private Builder() {
                this.name_ = Collections.emptyList();
                this.identifierValue_ = "";
                this.stringValue_ = ByteString.EMPTY;
                this.aggregateValue_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.name_ = Collections.emptyList();
                this.identifierValue_ = "";
                this.stringValue_ = ByteString.EMPTY;
                this.aggregateValue_ = "";
                maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureNameIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.name_ = new ArrayList(this.name_);
                    this.bitField0_ |= 1;
                }
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
            }

            private RepeatedFieldBuilder getNameFieldBuilder() {
                boolean z = true;
                if (this.nameBuilder_ == null) {
                    List list = this.name_;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.nameBuilder_ = new RepeatedFieldBuilder(list, z, getParentForChildren(), isClean());
                    this.name_ = null;
                }
                return this.nameBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessage.alwaysUseFieldBuilders) {
                    getNameFieldBuilder();
                }
            }

            public Builder addAllName(Iterable iterable) {
                if (this.nameBuilder_ == null) {
                    ensureNameIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.name_);
                    onChanged();
                } else {
                    this.nameBuilder_.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addName(int i, Builder builder) {
                if (this.nameBuilder_ == null) {
                    ensureNameIsMutable();
                    this.name_.add(i, builder.build());
                    onChanged();
                } else {
                    this.nameBuilder_.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addName(int i, NamePart namePart) {
                if (this.nameBuilder_ != null) {
                    this.nameBuilder_.addMessage(i, namePart);
                } else if (namePart == null) {
                    throw new NullPointerException();
                } else {
                    ensureNameIsMutable();
                    this.name_.add(i, namePart);
                    onChanged();
                }
                return this;
            }

            public Builder addName(Builder builder) {
                if (this.nameBuilder_ == null) {
                    ensureNameIsMutable();
                    this.name_.add(builder.build());
                    onChanged();
                } else {
                    this.nameBuilder_.addMessage(builder.build());
                }
                return this;
            }

            public Builder addName(NamePart namePart) {
                if (this.nameBuilder_ != null) {
                    this.nameBuilder_.addMessage(namePart);
                } else if (namePart == null) {
                    throw new NullPointerException();
                } else {
                    ensureNameIsMutable();
                    this.name_.add(namePart);
                    onChanged();
                }
                return this;
            }

            public Builder addNameBuilder() {
                return (Builder) getNameFieldBuilder().addBuilder(NamePart.getDefaultInstance());
            }

            public Builder addNameBuilder(int i) {
                return (Builder) getNameFieldBuilder().addBuilder(i, NamePart.getDefaultInstance());
            }

            public UninterpretedOption build() {
                Object buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
            }

            public UninterpretedOption buildPartial() {
                int i = 1;
                UninterpretedOption uninterpretedOption = new UninterpretedOption((com.google.protobuf.GeneratedMessage.Builder) this);
                int i2 = this.bitField0_;
                if (this.nameBuilder_ == null) {
                    if ((this.bitField0_ & 1) == 1) {
                        this.name_ = Collections.unmodifiableList(this.name_);
                        this.bitField0_ &= -2;
                    }
                    uninterpretedOption.name_ = this.name_;
                } else {
                    uninterpretedOption.name_ = this.nameBuilder_.build();
                }
                if ((i2 & 2) != 2) {
                    i = 0;
                }
                uninterpretedOption.identifierValue_ = this.identifierValue_;
                if ((i2 & 4) == 4) {
                    i |= 2;
                }
                uninterpretedOption.positiveIntValue_ = this.positiveIntValue_;
                if ((i2 & 8) == 8) {
                    i |= 4;
                }
                uninterpretedOption.negativeIntValue_ = this.negativeIntValue_;
                if ((i2 & 16) == 16) {
                    i |= 8;
                }
                uninterpretedOption.doubleValue_ = this.doubleValue_;
                if ((i2 & 32) == 32) {
                    i |= 16;
                }
                uninterpretedOption.stringValue_ = this.stringValue_;
                if ((i2 & 64) == 64) {
                    i |= 32;
                }
                uninterpretedOption.aggregateValue_ = this.aggregateValue_;
                uninterpretedOption.bitField0_ = i;
                onBuilt();
                return uninterpretedOption;
            }

            public Builder clear() {
                super.clear();
                if (this.nameBuilder_ == null) {
                    this.name_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    this.nameBuilder_.clear();
                }
                this.identifierValue_ = "";
                this.bitField0_ &= -3;
                this.positiveIntValue_ = 0;
                this.bitField0_ &= -5;
                this.negativeIntValue_ = 0;
                this.bitField0_ &= -9;
                this.doubleValue_ = 0.0d;
                this.bitField0_ &= -17;
                this.stringValue_ = ByteString.EMPTY;
                this.bitField0_ &= -33;
                this.aggregateValue_ = "";
                this.bitField0_ &= -65;
                return this;
            }

            public Builder clearAggregateValue() {
                this.bitField0_ &= -65;
                this.aggregateValue_ = UninterpretedOption.getDefaultInstance().getAggregateValue();
                onChanged();
                return this;
            }

            public Builder clearDoubleValue() {
                this.bitField0_ &= -17;
                this.doubleValue_ = 0.0d;
                onChanged();
                return this;
            }

            public Builder clearIdentifierValue() {
                this.bitField0_ &= -3;
                this.identifierValue_ = UninterpretedOption.getDefaultInstance().getIdentifierValue();
                onChanged();
                return this;
            }

            public Builder clearName() {
                if (this.nameBuilder_ == null) {
                    this.name_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    this.nameBuilder_.clear();
                }
                return this;
            }

            public Builder clearNegativeIntValue() {
                this.bitField0_ &= -9;
                this.negativeIntValue_ = 0;
                onChanged();
                return this;
            }

            public Builder clearPositiveIntValue() {
                this.bitField0_ &= -5;
                this.positiveIntValue_ = 0;
                onChanged();
                return this;
            }

            public Builder clearStringValue() {
                this.bitField0_ &= -33;
                this.stringValue_ = UninterpretedOption.getDefaultInstance().getStringValue();
                onChanged();
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public String getAggregateValue() {
                Object obj = this.aggregateValue_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.aggregateValue_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getAggregateValueBytes() {
                Object obj = this.aggregateValue_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.aggregateValue_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public UninterpretedOption getDefaultInstanceForType() {
                return UninterpretedOption.getDefaultInstance();
            }

            public Descriptor getDescriptorForType() {
                return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
            }

            public double getDoubleValue() {
                return this.doubleValue_;
            }

            public String getIdentifierValue() {
                Object obj = this.identifierValue_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.identifierValue_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getIdentifierValueBytes() {
                Object obj = this.identifierValue_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.identifierValue_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public NamePart getName(int i) {
                return this.nameBuilder_ == null ? (NamePart) this.name_.get(i) : (NamePart) this.nameBuilder_.getMessage(i);
            }

            public Builder getNameBuilder(int i) {
                return (Builder) getNameFieldBuilder().getBuilder(i);
            }

            public List getNameBuilderList() {
                return getNameFieldBuilder().getBuilderList();
            }

            public int getNameCount() {
                return this.nameBuilder_ == null ? this.name_.size() : this.nameBuilder_.getCount();
            }

            public List getNameList() {
                return this.nameBuilder_ == null ? Collections.unmodifiableList(this.name_) : this.nameBuilder_.getMessageList();
            }

            public NamePartOrBuilder getNameOrBuilder(int i) {
                return this.nameBuilder_ == null ? (NamePartOrBuilder) this.name_.get(i) : (NamePartOrBuilder) this.nameBuilder_.getMessageOrBuilder(i);
            }

            public List getNameOrBuilderList() {
                return this.nameBuilder_ != null ? this.nameBuilder_.getMessageOrBuilderList() : Collections.unmodifiableList(this.name_);
            }

            public long getNegativeIntValue() {
                return this.negativeIntValue_;
            }

            public long getPositiveIntValue() {
                return this.positiveIntValue_;
            }

            public ByteString getStringValue() {
                return this.stringValue_;
            }

            public boolean hasAggregateValue() {
                return (this.bitField0_ & 64) == 64;
            }

            public boolean hasDoubleValue() {
                return (this.bitField0_ & 16) == 16;
            }

            public boolean hasIdentifierValue() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasNegativeIntValue() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasPositiveIntValue() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasStringValue() {
                return (this.bitField0_ & 32) == 32;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f227x2101041.ensureFieldAccessorsInitialized(UninterpretedOption.class, Builder.class);
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getNameCount(); i++) {
                    if (!getName(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                UninterpretedOption uninterpretedOption;
                Throwable th;
                try {
                    uninterpretedOption = (UninterpretedOption) UninterpretedOption.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (uninterpretedOption != null) {
                        mergeFrom(uninterpretedOption);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    InvalidProtocolBufferException invalidProtocolBufferException = e;
                    uninterpretedOption = (UninterpretedOption) invalidProtocolBufferException.getUnfinishedMessage();
                    throw invalidProtocolBufferException;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (uninterpretedOption != null) {
                    mergeFrom(uninterpretedOption);
                }
                throw th;
            }

            public Builder mergeFrom(UninterpretedOption uninterpretedOption) {
                RepeatedFieldBuilder repeatedFieldBuilder = null;
                if (uninterpretedOption != UninterpretedOption.getDefaultInstance()) {
                    if (this.nameBuilder_ == null) {
                        if (!uninterpretedOption.name_.isEmpty()) {
                            if (this.name_.isEmpty()) {
                                this.name_ = uninterpretedOption.name_;
                                this.bitField0_ &= -2;
                            } else {
                                ensureNameIsMutable();
                                this.name_.addAll(uninterpretedOption.name_);
                            }
                            onChanged();
                        }
                    } else if (!uninterpretedOption.name_.isEmpty()) {
                        if (this.nameBuilder_.isEmpty()) {
                            this.nameBuilder_.dispose();
                            this.nameBuilder_ = null;
                            this.name_ = uninterpretedOption.name_;
                            this.bitField0_ &= -2;
                            if (GeneratedMessage.alwaysUseFieldBuilders) {
                                repeatedFieldBuilder = getNameFieldBuilder();
                            }
                            this.nameBuilder_ = repeatedFieldBuilder;
                        } else {
                            this.nameBuilder_.addAllMessages(uninterpretedOption.name_);
                        }
                    }
                    if (uninterpretedOption.hasIdentifierValue()) {
                        this.bitField0_ |= 2;
                        this.identifierValue_ = uninterpretedOption.identifierValue_;
                        onChanged();
                    }
                    if (uninterpretedOption.hasPositiveIntValue()) {
                        setPositiveIntValue(uninterpretedOption.getPositiveIntValue());
                    }
                    if (uninterpretedOption.hasNegativeIntValue()) {
                        setNegativeIntValue(uninterpretedOption.getNegativeIntValue());
                    }
                    if (uninterpretedOption.hasDoubleValue()) {
                        setDoubleValue(uninterpretedOption.getDoubleValue());
                    }
                    if (uninterpretedOption.hasStringValue()) {
                        setStringValue(uninterpretedOption.getStringValue());
                    }
                    if (uninterpretedOption.hasAggregateValue()) {
                        this.bitField0_ |= 64;
                        this.aggregateValue_ = uninterpretedOption.aggregateValue_;
                        onChanged();
                    }
                    mergeUnknownFields(uninterpretedOption.getUnknownFields());
                }
                return this;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof UninterpretedOption) {
                    return mergeFrom((UninterpretedOption) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder removeName(int i) {
                if (this.nameBuilder_ == null) {
                    ensureNameIsMutable();
                    this.name_.remove(i);
                    onChanged();
                } else {
                    this.nameBuilder_.remove(i);
                }
                return this;
            }

            public Builder setAggregateValue(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.aggregateValue_ = str;
                onChanged();
                return this;
            }

            public Builder setAggregateValueBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.aggregateValue_ = byteString;
                onChanged();
                return this;
            }

            public Builder setDoubleValue(double d) {
                this.bitField0_ |= 16;
                this.doubleValue_ = d;
                onChanged();
                return this;
            }

            public Builder setIdentifierValue(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.identifierValue_ = str;
                onChanged();
                return this;
            }

            public Builder setIdentifierValueBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.identifierValue_ = byteString;
                onChanged();
                return this;
            }

            public Builder setName(int i, Builder builder) {
                if (this.nameBuilder_ == null) {
                    ensureNameIsMutable();
                    this.name_.set(i, builder.build());
                    onChanged();
                } else {
                    this.nameBuilder_.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder setName(int i, NamePart namePart) {
                if (this.nameBuilder_ != null) {
                    this.nameBuilder_.setMessage(i, namePart);
                } else if (namePart == null) {
                    throw new NullPointerException();
                } else {
                    ensureNameIsMutable();
                    this.name_.set(i, namePart);
                    onChanged();
                }
                return this;
            }

            public Builder setNegativeIntValue(long j) {
                this.bitField0_ |= 8;
                this.negativeIntValue_ = j;
                onChanged();
                return this;
            }

            public Builder setPositiveIntValue(long j) {
                this.bitField0_ |= 4;
                this.positiveIntValue_ = j;
                onChanged();
                return this;
            }

            public Builder setStringValue(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.stringValue_ = byteString;
                onChanged();
                return this;
            }
        }

        public interface NamePartOrBuilder extends MessageOrBuilder {
            boolean getIsExtension();

            String getNamePart();

            ByteString getNamePartBytes();

            boolean hasIsExtension();

            boolean hasNamePart();
        }

        public final class NamePart extends GeneratedMessage implements NamePartOrBuilder {
            public static final int IS_EXTENSION_FIELD_NUMBER = 2;
            public static final int NAME_PART_FIELD_NUMBER = 1;
            public static Parser PARSER = new C19871();
            private static final NamePart defaultInstance = new NamePart(true);
            private static final long serialVersionUID = 0;
            private int bitField0_;
            private boolean isExtension_;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            private Object namePart_;
            private final UnknownFieldSet unknownFields;

            final class C19871 extends AbstractParser {
                C19871() {
                }

                public NamePart parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    return new NamePart(codedInputStream, extensionRegistryLite);
                }
            }

            public final class Builder extends com.google.protobuf.GeneratedMessage.Builder implements NamePartOrBuilder {
                private int bitField0_;
                private boolean isExtension_;
                private Object namePart_;

                private Builder() {
                    this.namePart_ = "";
                    maybeForceBuilderInitialization();
                }

                private Builder(BuilderParent builderParent) {
                    super(builderParent);
                    this.namePart_ = "";
                    maybeForceBuilderInitialization();
                }

                private static Builder create() {
                    return new Builder();
                }

                public static final Descriptor getDescriptor() {
                    return DescriptorProtos.f225xb111d23c;
                }

                private void maybeForceBuilderInitialization() {
                    if (!GeneratedMessage.alwaysUseFieldBuilders) {
                    }
                }

                public NamePart build() {
                    Object buildPartial = buildPartial();
                    if (buildPartial.isInitialized()) {
                        return buildPartial;
                    }
                    throw com.google.protobuf.AbstractMessage.Builder.newUninitializedMessageException(buildPartial);
                }

                public NamePart buildPartial() {
                    int i = 1;
                    NamePart namePart = new NamePart((com.google.protobuf.GeneratedMessage.Builder) this);
                    int i2 = this.bitField0_;
                    if ((i2 & 1) != 1) {
                        i = 0;
                    }
                    namePart.namePart_ = this.namePart_;
                    if ((i2 & 2) == 2) {
                        i |= 2;
                    }
                    namePart.isExtension_ = this.isExtension_;
                    namePart.bitField0_ = i;
                    onBuilt();
                    return namePart;
                }

                public Builder clear() {
                    super.clear();
                    this.namePart_ = "";
                    this.bitField0_ &= -2;
                    this.isExtension_ = false;
                    this.bitField0_ &= -3;
                    return this;
                }

                public Builder clearIsExtension() {
                    this.bitField0_ &= -3;
                    this.isExtension_ = false;
                    onChanged();
                    return this;
                }

                public Builder clearNamePart() {
                    this.bitField0_ &= -2;
                    this.namePart_ = NamePart.getDefaultInstance().getNamePart();
                    onChanged();
                    return this;
                }

                public Builder clone() {
                    return create().mergeFrom(buildPartial());
                }

                public NamePart getDefaultInstanceForType() {
                    return NamePart.getDefaultInstance();
                }

                public Descriptor getDescriptorForType() {
                    return DescriptorProtos.f225xb111d23c;
                }

                public boolean getIsExtension() {
                    return this.isExtension_;
                }

                public String getNamePart() {
                    Object obj = this.namePart_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    ByteString byteString = (ByteString) obj;
                    String toStringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.namePart_ = toStringUtf8;
                    }
                    return toStringUtf8;
                }

                public ByteString getNamePartBytes() {
                    Object obj = this.namePart_;
                    if (!(obj instanceof String)) {
                        return (ByteString) obj;
                    }
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.namePart_ = copyFromUtf8;
                    return copyFromUtf8;
                }

                public boolean hasIsExtension() {
                    return (this.bitField0_ & 2) == 2;
                }

                public boolean hasNamePart() {
                    return (this.bitField0_ & 1) == 1;
                }

                protected FieldAccessorTable internalGetFieldAccessorTable() {
                    return DescriptorProtos.f226x1698fcba.ensureFieldAccessorsInitialized(NamePart.class, Builder.class);
                }

                public final boolean isInitialized() {
                    return hasNamePart() && hasIsExtension();
                }

                public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                    NamePart namePart;
                    Throwable th;
                    try {
                        namePart = (NamePart) NamePart.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (namePart != null) {
                            mergeFrom(namePart);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        InvalidProtocolBufferException invalidProtocolBufferException = e;
                        namePart = (NamePart) invalidProtocolBufferException.getUnfinishedMessage();
                        throw invalidProtocolBufferException;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    if (namePart != null) {
                        mergeFrom(namePart);
                    }
                    throw th;
                }

                public Builder mergeFrom(NamePart namePart) {
                    if (namePart != NamePart.getDefaultInstance()) {
                        if (namePart.hasNamePart()) {
                            this.bitField0_ |= 1;
                            this.namePart_ = namePart.namePart_;
                            onChanged();
                        }
                        if (namePart.hasIsExtension()) {
                            setIsExtension(namePart.getIsExtension());
                        }
                        mergeUnknownFields(namePart.getUnknownFields());
                    }
                    return this;
                }

                public Builder mergeFrom(Message message) {
                    if (message instanceof NamePart) {
                        return mergeFrom((NamePart) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder setIsExtension(boolean z) {
                    this.bitField0_ |= 2;
                    this.isExtension_ = z;
                    onChanged();
                    return this;
                }

                public Builder setNamePart(String str) {
                    if (str == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.namePart_ = str;
                    onChanged();
                    return this;
                }

                public Builder setNamePartBytes(ByteString byteString) {
                    if (byteString == null) {
                        throw new NullPointerException();
                    }
                    this.bitField0_ |= 1;
                    this.namePart_ = byteString;
                    onChanged();
                    return this;
                }
            }

            static {
                defaultInstance.initFields();
            }

            private NamePart(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                initFields();
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                Object obj = null;
                while (obj == null) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                obj = 1;
                                break;
                            case 10:
                                ByteString readBytes = codedInputStream.readBytes();
                                this.bitField0_ |= 1;
                                this.namePart_ = readBytes;
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.isExtension_ = codedInputStream.readBool();
                                break;
                            default:
                                if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    obj = 1;
                                    break;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }

            private NamePart(com.google.protobuf.GeneratedMessage.Builder builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = builder.getUnknownFields();
            }

            private NamePart(boolean z) {
                this.memoizedIsInitialized = (byte) -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = UnknownFieldSet.getDefaultInstance();
            }

            public static NamePart getDefaultInstance() {
                return defaultInstance;
            }

            public static final Descriptor getDescriptor() {
                return DescriptorProtos.f225xb111d23c;
            }

            private void initFields() {
                this.namePart_ = "";
                this.isExtension_ = false;
            }

            public static Builder newBuilder() {
                return Builder.create();
            }

            public static Builder newBuilder(NamePart namePart) {
                return newBuilder().mergeFrom(namePart);
            }

            public static NamePart parseDelimitedFrom(InputStream inputStream) {
                return (NamePart) PARSER.parseDelimitedFrom(inputStream);
            }

            public static NamePart parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (NamePart) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
            }

            public static NamePart parseFrom(ByteString byteString) {
                return (NamePart) PARSER.parseFrom(byteString);
            }

            public static NamePart parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
                return (NamePart) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static NamePart parseFrom(CodedInputStream codedInputStream) {
                return (NamePart) PARSER.parseFrom(codedInputStream);
            }

            public static NamePart parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (NamePart) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
            }

            public static NamePart parseFrom(InputStream inputStream) {
                return (NamePart) PARSER.parseFrom(inputStream);
            }

            public static NamePart parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
                return (NamePart) PARSER.parseFrom(inputStream, extensionRegistryLite);
            }

            public static NamePart parseFrom(byte[] bArr) {
                return (NamePart) PARSER.parseFrom(bArr);
            }

            public static NamePart parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
                return (NamePart) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public NamePart getDefaultInstanceForType() {
                return defaultInstance;
            }

            public boolean getIsExtension() {
                return this.isExtension_;
            }

            public String getNamePart() {
                Object obj = this.namePart_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String toStringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.namePart_ = toStringUtf8;
                }
                return toStringUtf8;
            }

            public ByteString getNamePartBytes() {
                Object obj = this.namePart_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.namePart_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Parser getParserForType() {
                return PARSER;
            }

            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                i = 0;
                if ((this.bitField0_ & 1) == 1) {
                    i = CodedOutputStream.computeBytesSize(1, getNamePartBytes()) + 0;
                }
                if ((this.bitField0_ & 2) == 2) {
                    i += CodedOutputStream.computeBoolSize(2, this.isExtension_);
                }
                i += getUnknownFields().getSerializedSize();
                this.memoizedSerializedSize = i;
                return i;
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            public boolean hasIsExtension() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasNamePart() {
                return (this.bitField0_ & 1) == 1;
            }

            protected FieldAccessorTable internalGetFieldAccessorTable() {
                return DescriptorProtos.f226x1698fcba.ensureFieldAccessorsInitialized(NamePart.class, Builder.class);
            }

            public final boolean isInitialized() {
                byte b = this.memoizedIsInitialized;
                if (b == (byte) 1) {
                    return true;
                }
                if (b == (byte) 0) {
                    return false;
                }
                if (!hasNamePart()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                } else if (hasIsExtension()) {
                    this.memoizedIsInitialized = (byte) 1;
                    return true;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }

            public Builder newBuilderForType() {
                return newBuilder();
            }

            protected Builder newBuilderForType(BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public Builder toBuilder() {
                return newBuilder(this);
            }

            protected Object writeReplace() {
                return super.writeReplace();
            }

            public void writeTo(CodedOutputStream codedOutputStream) {
                getSerializedSize();
                if ((this.bitField0_ & 1) == 1) {
                    codedOutputStream.writeBytes(1, getNamePartBytes());
                }
                if ((this.bitField0_ & 2) == 2) {
                    codedOutputStream.writeBool(2, this.isExtension_);
                }
                getUnknownFields().writeTo(codedOutputStream);
            }
        }

        static {
            defaultInstance.initFields();
        }

        private UninterpretedOption(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            int i = 0;
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            int i2 = 0;
            while (i2 == 0) {
                try {
                    int readTag = codedInputStream.readTag();
                    ByteString readBytes;
                    switch (readTag) {
                        case 0:
                            i2 = 1;
                            break;
                        case 18:
                            if ((i & 1) != 1) {
                                this.name_ = new ArrayList();
                                i |= 1;
                            }
                            this.name_.add(codedInputStream.readMessage(NamePart.PARSER, extensionRegistryLite));
                            break;
                        case 26:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 1;
                            this.identifierValue_ = readBytes;
                            break;
                        case 32:
                            this.bitField0_ |= 2;
                            this.positiveIntValue_ = codedInputStream.readUInt64();
                            break;
                        case 40:
                            this.bitField0_ |= 4;
                            this.negativeIntValue_ = codedInputStream.readInt64();
                            break;
                        case 49:
                            this.bitField0_ |= 8;
                            this.doubleValue_ = codedInputStream.readDouble();
                            break;
                        case 58:
                            this.bitField0_ |= 16;
                            this.stringValue_ = codedInputStream.readBytes();
                            break;
                        case 66:
                            readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 32;
                            this.aggregateValue_ = readBytes;
                            break;
                        default:
                            if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                i2 = 1;
                                break;
                            }
                            break;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if ((i & 1) == 1) {
                        this.name_ = Collections.unmodifiableList(this.name_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
            if ((i & 1) == 1) {
                this.name_ = Collections.unmodifiableList(this.name_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        private UninterpretedOption(com.google.protobuf.GeneratedMessage.Builder builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private UninterpretedOption(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static UninterpretedOption getDefaultInstance() {
            return defaultInstance;
        }

        public static final Descriptor getDescriptor() {
            return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
        }

        private void initFields() {
            this.name_ = Collections.emptyList();
            this.identifierValue_ = "";
            this.positiveIntValue_ = 0;
            this.negativeIntValue_ = 0;
            this.doubleValue_ = 0.0d;
            this.stringValue_ = ByteString.EMPTY;
            this.aggregateValue_ = "";
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(UninterpretedOption uninterpretedOption) {
            return newBuilder().mergeFrom(uninterpretedOption);
        }

        public static UninterpretedOption parseDelimitedFrom(InputStream inputStream) {
            return (UninterpretedOption) PARSER.parseDelimitedFrom(inputStream);
        }

        public static UninterpretedOption parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (UninterpretedOption) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static UninterpretedOption parseFrom(ByteString byteString) {
            return (UninterpretedOption) PARSER.parseFrom(byteString);
        }

        public static UninterpretedOption parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (UninterpretedOption) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UninterpretedOption parseFrom(CodedInputStream codedInputStream) {
            return (UninterpretedOption) PARSER.parseFrom(codedInputStream);
        }

        public static UninterpretedOption parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (UninterpretedOption) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static UninterpretedOption parseFrom(InputStream inputStream) {
            return (UninterpretedOption) PARSER.parseFrom(inputStream);
        }

        public static UninterpretedOption parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (UninterpretedOption) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static UninterpretedOption parseFrom(byte[] bArr) {
            return (UninterpretedOption) PARSER.parseFrom(bArr);
        }

        public static UninterpretedOption parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (UninterpretedOption) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public String getAggregateValue() {
            Object obj = this.aggregateValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.aggregateValue_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getAggregateValueBytes() {
            Object obj = this.aggregateValue_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.aggregateValue_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public UninterpretedOption getDefaultInstanceForType() {
            return defaultInstance;
        }

        public double getDoubleValue() {
            return this.doubleValue_;
        }

        public String getIdentifierValue() {
            Object obj = this.identifierValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String toStringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.identifierValue_ = toStringUtf8;
            }
            return toStringUtf8;
        }

        public ByteString getIdentifierValueBytes() {
            Object obj = this.identifierValue_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.identifierValue_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public NamePart getName(int i) {
            return (NamePart) this.name_.get(i);
        }

        public int getNameCount() {
            return this.name_.size();
        }

        public List getNameList() {
            return this.name_;
        }

        public NamePartOrBuilder getNameOrBuilder(int i) {
            return (NamePartOrBuilder) this.name_.get(i);
        }

        public List getNameOrBuilderList() {
            return this.name_;
        }

        public long getNegativeIntValue() {
            return this.negativeIntValue_;
        }

        public Parser getParserForType() {
            return PARSER;
        }

        public long getPositiveIntValue() {
            return this.positiveIntValue_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize;
            i = 0;
            int i2 = 0;
            while (i2 < this.name_.size()) {
                computeMessageSize = CodedOutputStream.computeMessageSize(2, (MessageLite) this.name_.get(i2)) + i;
                i2++;
                i = computeMessageSize;
            }
            if ((this.bitField0_ & 1) == 1) {
                i += CodedOutputStream.computeBytesSize(3, getIdentifierValueBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                i += CodedOutputStream.computeUInt64Size(4, this.positiveIntValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i += CodedOutputStream.computeInt64Size(5, this.negativeIntValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i += CodedOutputStream.computeDoubleSize(6, this.doubleValue_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i += CodedOutputStream.computeBytesSize(7, this.stringValue_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i += CodedOutputStream.computeBytesSize(8, getAggregateValueBytes());
            }
            computeMessageSize = getUnknownFields().getSerializedSize() + i;
            this.memoizedSerializedSize = computeMessageSize;
            return computeMessageSize;
        }

        public ByteString getStringValue() {
            return this.stringValue_;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasAggregateValue() {
            return (this.bitField0_ & 32) == 32;
        }

        public boolean hasDoubleValue() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasIdentifierValue() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasNegativeIntValue() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasPositiveIntValue() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasStringValue() {
            return (this.bitField0_ & 16) == 16;
        }

        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return DescriptorProtos.f227x2101041.ensureFieldAccessorsInitialized(UninterpretedOption.class, Builder.class);
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == (byte) 1) {
                return true;
            }
            if (b == (byte) 0) {
                return false;
            }
            int i = 0;
            while (i < getNameCount()) {
                if (getName(i).isInitialized()) {
                    i++;
                } else {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        protected Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        protected Object writeReplace() {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) {
            getSerializedSize();
            for (int i = 0; i < this.name_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.name_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(3, getIdentifierValueBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeUInt64(4, this.positiveIntValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt64(5, this.negativeIntValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeDouble(6, this.doubleValue_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(7, this.stringValue_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBytes(8, getAggregateValueBytes());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n google/protobuf/descriptor.proto\u0012\u000fgoogle.protobuf\"G\n\u0011FileDescriptorSet\u00122\n\u0004file\u0018\u0001 \u0003(\u000b2$.google.protobuf.FileDescriptorProto\"Ë\u0003\n\u0013FileDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007package\u0018\u0002 \u0001(\t\u0012\u0012\n\ndependency\u0018\u0003 \u0003(\t\u0012\u0019\n\u0011public_dependency\u0018\n \u0003(\u0005\u0012\u0017\n\u000fweak_dependency\u0018\u000b \u0003(\u0005\u00126\n\fmessage_type\u0018\u0004 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type\u0018\u0005 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u00128\n\u0007service\u0018\u0006 \u0003(\u000b2'.google.protobuf.", "ServiceDescriptorProto\u00128\n\textension\u0018\u0007 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u0012-\n\u0007options\u0018\b \u0001(\u000b2\u001c.google.protobuf.FileOptions\u00129\n\u0010source_code_info\u0018\t \u0001(\u000b2\u001f.google.protobuf.SourceCodeInfo\"ä\u0003\n\u000fDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00124\n\u0005field\u0018\u0002 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00128\n\textension\u0018\u0006 \u0003(\u000b2%.google.protobuf.FieldDescriptorProto\u00125\n\u000bnested_type\u0018\u0003 \u0003(\u000b2 .google.protobuf.DescriptorProto\u00127\n\tenum_type", "\u0018\u0004 \u0003(\u000b2$.google.protobuf.EnumDescriptorProto\u0012H\n\u000fextension_range\u0018\u0005 \u0003(\u000b2/.google.protobuf.DescriptorProto.ExtensionRange\u00129\n\noneof_decl\u0018\b \u0003(\u000b2%.google.protobuf.OneofDescriptorProto\u00120\n\u0007options\u0018\u0007 \u0001(\u000b2\u001f.google.protobuf.MessageOptions\u001a,\n\u000eExtensionRange\u0012\r\n\u0005start\u0018\u0001 \u0001(\u0005\u0012\u000b\n\u0003end\u0018\u0002 \u0001(\u0005\"©\u0005\n\u0014FieldDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0003 \u0001(\u0005\u0012:\n\u0005label\u0018\u0004 \u0001(\u000e2+.google.protobuf.FieldDescriptorProto.Label\u00128\n\u0004type\u0018\u0005 \u0001", "(\u000e2*.google.protobuf.FieldDescriptorProto.Type\u0012\u0011\n\ttype_name\u0018\u0006 \u0001(\t\u0012\u0010\n\bextendee\u0018\u0002 \u0001(\t\u0012\u0015\n\rdefault_value\u0018\u0007 \u0001(\t\u0012\u0013\n\u000boneof_index\u0018\t \u0001(\u0005\u0012.\n\u0007options\u0018\b \u0001(\u000b2\u001d.google.protobuf.FieldOptions\"¶\u0002\n\u0004Type\u0012\u000f\n\u000bTYPE_DOUBLE\u0010\u0001\u0012\u000e\n\nTYPE_FLOAT\u0010\u0002\u0012\u000e\n\nTYPE_INT64\u0010\u0003\u0012\u000f\n\u000bTYPE_UINT64\u0010\u0004\u0012\u000e\n\nTYPE_INT32\u0010\u0005\u0012\u0010\n\fTYPE_FIXED64\u0010\u0006\u0012\u0010\n\fTYPE_FIXED32\u0010\u0007\u0012\r\n\tTYPE_BOOL\u0010\b\u0012\u000f\n\u000bTYPE_STRING\u0010\t\u0012\u000e\n\nTYPE_GROUP\u0010\n\u0012\u0010\n\fTYPE_MESSAGE\u0010\u000b\u0012\u000e\n\nTYPE_BYTES\u0010\f\u0012\u000f\n\u000bTYPE_UINT32\u0010", "\r\u0012\r\n\tTYPE_ENUM\u0010\u000e\u0012\u0011\n\rTYPE_SFIXED32\u0010\u000f\u0012\u0011\n\rTYPE_SFIXED64\u0010\u0010\u0012\u000f\n\u000bTYPE_SINT32\u0010\u0011\u0012\u000f\n\u000bTYPE_SINT64\u0010\u0012\"C\n\u0005Label\u0012\u0012\n\u000eLABEL_OPTIONAL\u0010\u0001\u0012\u0012\n\u000eLABEL_REQUIRED\u0010\u0002\u0012\u0012\n\u000eLABEL_REPEATED\u0010\u0003\"$\n\u0014OneofDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\u0001\n\u0013EnumDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00128\n\u0005value\u0018\u0002 \u0003(\u000b2).google.protobuf.EnumValueDescriptorProto\u0012-\n\u0007options\u0018\u0003 \u0001(\u000b2\u001c.google.protobuf.EnumOptions\"l\n\u0018EnumValueDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006number\u0018\u0002 \u0001(\u0005\u00122\n\u0007", "options\u0018\u0003 \u0001(\u000b2!.google.protobuf.EnumValueOptions\"\u0001\n\u0016ServiceDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00126\n\u0006method\u0018\u0002 \u0003(\u000b2&.google.protobuf.MethodDescriptorProto\u00120\n\u0007options\u0018\u0003 \u0001(\u000b2\u001f.google.protobuf.ServiceOptions\"\n\u0015MethodDescriptorProto\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0012\n\ninput_type\u0018\u0002 \u0001(\t\u0012\u0013\n\u000boutput_type\u0018\u0003 \u0001(\t\u0012/\n\u0007options\u0018\u0004 \u0001(\u000b2\u001e.google.protobuf.MethodOptions\"«\u0004\n\u000bFileOptions\u0012\u0014\n\fjava_package\u0018\u0001 \u0001(\t\u0012\u001c\n\u0014java_outer_classname\u0018\b \u0001(\t\u0012\"\n\u0013java", "_multiple_files\u0018\n \u0001(\b:\u0005false\u0012,\n\u001djava_generate_equals_and_hash\u0018\u0014 \u0001(\b:\u0005false\u0012%\n\u0016java_string_check_utf8\u0018\u001b \u0001(\b:\u0005false\u0012F\n\foptimize_for\u0018\t \u0001(\u000e2).google.protobuf.FileOptions.OptimizeMode:\u0005SPEED\u0012\u0012\n\ngo_package\u0018\u000b \u0001(\t\u0012\"\n\u0013cc_generic_services\u0018\u0010 \u0001(\b:\u0005false\u0012$\n\u0015java_generic_services\u0018\u0011 \u0001(\b:\u0005false\u0012\"\n\u0013py_generic_services\u0018\u0012 \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0017 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.Uninterp", "retedOption\":\n\fOptimizeMode\u0012\t\n\u0005SPEED\u0010\u0001\u0012\r\n\tCODE_SIZE\u0010\u0002\u0012\u0010\n\fLITE_RUNTIME\u0010\u0003*\t\bè\u0007\u0010\u0002\"Ó\u0001\n\u000eMessageOptions\u0012&\n\u0017message_set_wire_format\u0018\u0001 \u0001(\b:\u0005false\u0012.\n\u001fno_standard_descriptor_accessor\u0018\u0002 \u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002\"¾\u0002\n\fFieldOptions\u0012:\n\u0005ctype\u0018\u0001 \u0001(\u000e2#.google.protobuf.FieldOptions.CType:\u0006STRING\u0012\u000e\n\u0006packed\u0018\u0002 \u0001(\b\u0012\u0013\n\u0004lazy\u0018\u0005 ", "\u0001(\b:\u0005false\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012\u001c\n\u0014experimental_map_key\u0018\t \u0001(\t\u0012\u0013\n\u0004weak\u0018\n \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption\"/\n\u0005CType\u0012\n\n\u0006STRING\u0010\u0000\u0012\b\n\u0004CORD\u0010\u0001\u0012\u0010\n\fSTRING_PIECE\u0010\u0002*\t\bè\u0007\u0010\u0002\"\u0001\n\u000bEnumOptions\u0012\u0013\n\u000ballow_alias\u0018\u0002 \u0001(\b\u0012\u0019\n\ndeprecated\u0018\u0003 \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002\"}\n\u0010EnumValueOptions\u0012\u0019\n\ndeprecated\u0018\u0001 \u0001(", "\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002\"{\n\u000eServiceOptions\u0012\u0019\n\ndeprecated\u0018! \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002\"z\n\rMethodOptions\u0012\u0019\n\ndeprecated\u0018! \u0001(\b:\u0005false\u0012C\n\u0014uninterpreted_option\u0018ç\u0007 \u0003(\u000b2$.google.protobuf.UninterpretedOption*\t\bè\u0007\u0010\u0002\"\u0002\n\u0013UninterpretedOption\u0012;\n\u0004name\u0018\u0002 \u0003(\u000b2-.google.protobuf.Uninte", "rpretedOption.NamePart\u0012\u0018\n\u0010identifier_value\u0018\u0003 \u0001(\t\u0012\u001a\n\u0012positive_int_value\u0018\u0004 \u0001(\u0004\u0012\u001a\n\u0012negative_int_value\u0018\u0005 \u0001(\u0003\u0012\u0014\n\fdouble_value\u0018\u0006 \u0001(\u0001\u0012\u0014\n\fstring_value\u0018\u0007 \u0001(\f\u0012\u0017\n\u000faggregate_value\u0018\b \u0001(\t\u001a3\n\bNamePart\u0012\u0011\n\tname_part\u0018\u0001 \u0002(\t\u0012\u0014\n\fis_extension\u0018\u0002 \u0002(\b\"±\u0001\n\u000eSourceCodeInfo\u0012:\n\blocation\u0018\u0001 \u0003(\u000b2(.google.protobuf.SourceCodeInfo.Location\u001ac\n\bLocation\u0012\u0010\n\u0004path\u0018\u0001 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0010\n\u0004span\u0018\u0002 \u0003(\u0005B\u0002\u0010\u0001\u0012\u0018\n\u0010leading_comments\u0018\u0003 \u0001(\t\u0012\u0019\n\u0011trailing_comments", "\u0018\u0004 \u0001(\tB)\n\u0013com.google.protobufB\u0010DescriptorProtosH\u0001"}, new FileDescriptor[0], new C19621());
    }

    private DescriptorProtos() {
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
    }
}
