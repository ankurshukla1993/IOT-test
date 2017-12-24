package com.google.protobuf;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.facebook.yoga.YogaConstants;
import com.google.common.base.Ascii;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistry.ExtensionInfo;
import com.google.protobuf.Message.Builder;
import com.google.protobuf.UnknownFieldSet.Field;
import com.ihealth.communication.manager.iHealthDevicesManager;
import humanize.util.Constants;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormat {
    private static final Printer DEFAULT_PRINTER = new Printer();
    private static final Parser PARSER = Parser.newBuilder().build();
    private static final Printer SINGLE_LINE_PRINTER = new Printer().setSingleLineMode(true);
    private static final Printer UNICODE_PRINTER = new Printer().setEscapeNonAscii(false);
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());

    interface ByteSequence {
        byte byteAt(int i);

        int size();
    }

    final class C20071 implements ByteSequence {
        final /* synthetic */ ByteString val$input;

        C20071(ByteString byteString) {
            this.val$input = byteString;
        }

        public byte byteAt(int i) {
            return this.val$input.byteAt(i);
        }

        public int size() {
            return this.val$input.size();
        }
    }

    final class C20082 implements ByteSequence {
        final /* synthetic */ byte[] val$input;

        C20082(byte[] bArr) {
            this.val$input = bArr;
        }

        public byte byteAt(int i) {
            return this.val$input[i];
        }

        public int size() {
            return this.val$input.length;
        }
    }

    class InvalidEscapeSequenceException extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        InvalidEscapeSequenceException(String str) {
            super(str);
        }
    }

    public class ParseException extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int column;
        private final int line;

        public ParseException(int i, int i2, String str) {
            super(Integer.toString(i) + ":" + i2 + ": " + str);
            this.line = i;
            this.column = i2;
        }

        public ParseException(String str) {
            this(-1, -1, str);
        }

        public int getColumn() {
            return this.column;
        }

        public int getLine() {
            return this.line;
        }
    }

    public class Parser {
        private static final int BUFFER_SIZE = 4096;
        private final boolean allowUnknownFields;
        private final SingularOverwritePolicy singularOverwritePolicy;

        public class Builder {
            private boolean allowUnknownFields = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;

            public Parser build() {
                return new Parser(this.allowUnknownFields, this.singularOverwritePolicy);
            }

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy singularOverwritePolicy) {
                this.singularOverwritePolicy = singularOverwritePolicy;
                return this;
            }
        }

        public enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        private Parser(boolean z, SingularOverwritePolicy singularOverwritePolicy) {
            this.allowUnknownFields = z;
            this.singularOverwritePolicy = singularOverwritePolicy;
        }

        private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MergeTarget mergeTarget, FieldDescriptor fieldDescriptor, ExtensionInfo extensionInfo) {
            Object obj = null;
            if (fieldDescriptor.getJavaType() != JavaType.MESSAGE) {
                switch (fieldDescriptor.getType()) {
                    case INT32:
                    case SINT32:
                    case SFIXED32:
                        obj = Integer.valueOf(tokenizer.consumeInt32());
                        break;
                    case INT64:
                    case SINT64:
                    case SFIXED64:
                        obj = Long.valueOf(tokenizer.consumeInt64());
                        break;
                    case BOOL:
                        obj = Boolean.valueOf(tokenizer.consumeBoolean());
                        break;
                    case FLOAT:
                        obj = Float.valueOf(tokenizer.consumeFloat());
                        break;
                    case DOUBLE:
                        obj = Double.valueOf(tokenizer.consumeDouble());
                        break;
                    case UINT32:
                    case FIXED32:
                        obj = Integer.valueOf(tokenizer.consumeUInt32());
                        break;
                    case UINT64:
                    case FIXED64:
                        obj = Long.valueOf(tokenizer.consumeUInt64());
                        break;
                    case STRING:
                        obj = tokenizer.consumeString();
                        break;
                    case BYTES:
                        obj = tokenizer.consumeByteString();
                        break;
                    case ENUM:
                        EnumDescriptor enumType = fieldDescriptor.getEnumType();
                        if (tokenizer.lookingAtInteger()) {
                            int consumeInt32 = tokenizer.consumeInt32();
                            obj = enumType.findValueByNumber(consumeInt32);
                            if (obj == null) {
                                throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value with number " + consumeInt32 + '.');
                            }
                        }
                        String consumeIdentifier = tokenizer.consumeIdentifier();
                        obj = enumType.findValueByName(consumeIdentifier);
                        if (obj == null) {
                            throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value named \"" + consumeIdentifier + "\".");
                        }
                        break;
                    case MESSAGE:
                    case GROUP:
                        throw new RuntimeException("Can't get here.");
                    default:
                        break;
                }
            }
            String str;
            Message message;
            if (tokenizer.tryConsume("<")) {
                str = ">";
            } else {
                tokenizer.consume("{");
                str = "}";
            }
            if (extensionInfo != null) {
                message = extensionInfo.defaultInstance;
            }
            MergeTarget newMergeTargetForField = mergeTarget.newMergeTargetForField(fieldDescriptor, message);
            while (!tokenizer.tryConsume(str)) {
                if (tokenizer.atEnd()) {
                    throw tokenizer.parseException("Expected \"" + str + "\".");
                }
                mergeField(tokenizer, extensionRegistry, newMergeTargetForField);
            }
            obj = newMergeTargetForField.finish();
            if (fieldDescriptor.isRepeated()) {
                mergeTarget.addRepeatedField(fieldDescriptor, obj);
            } else if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && mergeTarget.hasField(fieldDescriptor)) {
                throw tokenizer.parseExceptionPreviousToken("Non-repeated field \"" + fieldDescriptor.getFullName() + "\" cannot be overwritten.");
            } else if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && fieldDescriptor.getContainingOneof() != null && mergeTarget.hasOneof(fieldDescriptor.getContainingOneof())) {
                OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
                throw tokenizer.parseExceptionPreviousToken("Field \"" + fieldDescriptor.getFullName() + "\" is specified along with field \"" + mergeTarget.getOneofFieldDescriptor(containingOneof).getFullName() + "\", another member of oneof \"" + containingOneof.getName() + "\".");
            } else {
                mergeTarget.setField(fieldDescriptor, obj);
            }
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MergeTarget mergeTarget) {
            ExtensionInfo findExtensionByName;
            FieldDescriptor fieldDescriptor;
            FieldDescriptor fieldDescriptor2 = null;
            Descriptor descriptorForType = mergeTarget.getDescriptorForType();
            if (tokenizer.tryConsume("[")) {
                StringBuilder stringBuilder = new StringBuilder(tokenizer.consumeIdentifier());
                while (tokenizer.tryConsume(".")) {
                    stringBuilder.append('.');
                    stringBuilder.append(tokenizer.consumeIdentifier());
                }
                findExtensionByName = mergeTarget.findExtensionByName(extensionRegistry, stringBuilder.toString());
                if (findExtensionByName == null) {
                    if (this.allowUnknownFields) {
                        TextFormat.logger.warning("Extension \"" + stringBuilder + "\" not found in the ExtensionRegistry.");
                    } else {
                        throw tokenizer.parseExceptionPreviousToken("Extension \"" + stringBuilder + "\" not found in the ExtensionRegistry.");
                    }
                } else if (findExtensionByName.descriptor.getContainingType() != descriptorForType) {
                    throw tokenizer.parseExceptionPreviousToken("Extension \"" + stringBuilder + "\" does not extend message type \"" + descriptorForType.getFullName() + "\".");
                } else {
                    fieldDescriptor2 = findExtensionByName.descriptor;
                }
                tokenizer.consume("]");
                fieldDescriptor = fieldDescriptor2;
            } else {
                String consumeIdentifier = tokenizer.consumeIdentifier();
                fieldDescriptor = descriptorForType.findFieldByName(consumeIdentifier);
                if (fieldDescriptor == null) {
                    fieldDescriptor = descriptorForType.findFieldByName(consumeIdentifier.toLowerCase(Locale.US));
                    if (!(fieldDescriptor == null || fieldDescriptor.getType() == Type.GROUP)) {
                        fieldDescriptor = null;
                    }
                }
                if (!(fieldDescriptor == null || fieldDescriptor.getType() != Type.GROUP || fieldDescriptor.getMessageType().getName().equals(consumeIdentifier))) {
                    fieldDescriptor = null;
                }
                if (fieldDescriptor == null) {
                    if (this.allowUnknownFields) {
                        TextFormat.logger.warning("Message type \"" + descriptorForType.getFullName() + "\" has no field named \"" + consumeIdentifier + "\".");
                    } else {
                        throw tokenizer.parseExceptionPreviousToken("Message type \"" + descriptorForType.getFullName() + "\" has no field named \"" + consumeIdentifier + "\".");
                    }
                }
                findExtensionByName = null;
            }
            if (fieldDescriptor != null) {
                if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                    tokenizer.tryConsume(":");
                } else {
                    tokenizer.consume(":");
                }
                if (fieldDescriptor.isRepeated() && tokenizer.tryConsume("[")) {
                    while (true) {
                        consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, findExtensionByName);
                        if (!tokenizer.tryConsume("]")) {
                            tokenizer.consume(",");
                        } else {
                            return;
                        }
                    }
                }
                consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, findExtensionByName);
            } else if (!tokenizer.tryConsume(":") || tokenizer.lookingAt("{") || tokenizer.lookingAt("<")) {
                skipFieldMessage(tokenizer);
            } else {
                skipFieldValue(tokenizer);
            }
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        private void skipField(Tokenizer tokenizer) {
            if (tokenizer.tryConsume("[")) {
                do {
                    tokenizer.consumeIdentifier();
                } while (tokenizer.tryConsume("."));
                tokenizer.consume("]");
            } else {
                tokenizer.consumeIdentifier();
            }
            if (!tokenizer.tryConsume(":") || tokenizer.lookingAt("<") || tokenizer.lookingAt("{")) {
                skipFieldMessage(tokenizer);
            } else {
                skipFieldValue(tokenizer);
            }
            if (!tokenizer.tryConsume(";")) {
                tokenizer.tryConsume(",");
            }
        }

        private void skipFieldMessage(Tokenizer tokenizer) {
            String str;
            if (tokenizer.tryConsume("<")) {
                str = ">";
            } else {
                tokenizer.consume("{");
                str = "}";
            }
            while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
                skipField(tokenizer);
            }
            tokenizer.consume(str);
        }

        private void skipFieldValue(Tokenizer tokenizer) {
            if (tokenizer.tryConsumeString()) {
                do {
                } while (tokenizer.tryConsumeString());
            } else if (!tokenizer.tryConsumeIdentifier() && !tokenizer.tryConsumeInt64() && !tokenizer.tryConsumeUInt64() && !tokenizer.tryConsumeDouble() && !tokenizer.tryConsumeFloat()) {
                throw tokenizer.parseException("Invalid field value: " + tokenizer.currentToken);
            }
        }

        private static StringBuilder toStringBuilder(Readable readable) {
            StringBuilder stringBuilder = new StringBuilder();
            CharSequence allocate = CharBuffer.allocate(4096);
            while (true) {
                int read = readable.read(allocate);
                if (read == -1) {
                    return stringBuilder;
                }
                allocate.flip();
                stringBuilder.append(allocate, 0, read);
            }
        }

        public void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, com.google.protobuf.Message.Builder builder) {
            Tokenizer tokenizer = new Tokenizer(charSequence);
            MergeTarget builderAdapter = new BuilderAdapter(builder);
            while (!tokenizer.atEnd()) {
                mergeField(tokenizer, extensionRegistry, builderAdapter);
            }
        }

        public void merge(CharSequence charSequence, com.google.protobuf.Message.Builder builder) {
            merge(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable readable, ExtensionRegistry extensionRegistry, com.google.protobuf.Message.Builder builder) {
            merge(toStringBuilder(readable), extensionRegistry, builder);
        }

        public void merge(Readable readable, com.google.protobuf.Message.Builder builder) {
            merge(readable, ExtensionRegistry.getEmptyRegistry(), builder);
        }
    }

    final class Printer {
        boolean escapeNonAscii;
        boolean singleLineMode;

        private Printer() {
            this.singleLineMode = false;
            this.escapeNonAscii = true;
        }

        private void print(MessageOrBuilder messageOrBuilder, TextGenerator textGenerator) {
            for (Entry entry : messageOrBuilder.getAllFields().entrySet()) {
                printField((FieldDescriptor) entry.getKey(), entry.getValue(), textGenerator);
            }
            printUnknownFields(messageOrBuilder.getUnknownFields(), textGenerator);
        }

        private void printField(FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) {
            if (fieldDescriptor.isRepeated()) {
                for (Object printSingleField : (List) obj) {
                    printSingleField(fieldDescriptor, printSingleField, textGenerator);
                }
                return;
            }
            printSingleField(fieldDescriptor, obj, textGenerator);
        }

        private void printFieldValue(FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) {
            switch (fieldDescriptor.getType()) {
                case INT32:
                case SINT32:
                case SFIXED32:
                    textGenerator.print(((Integer) obj).toString());
                    return;
                case INT64:
                case SINT64:
                case SFIXED64:
                    textGenerator.print(((Long) obj).toString());
                    return;
                case BOOL:
                    textGenerator.print(((Boolean) obj).toString());
                    return;
                case FLOAT:
                    textGenerator.print(((Float) obj).toString());
                    return;
                case DOUBLE:
                    textGenerator.print(((Double) obj).toString());
                    return;
                case UINT32:
                case FIXED32:
                    textGenerator.print(TextFormat.unsignedToString(((Integer) obj).intValue()));
                    return;
                case UINT64:
                case FIXED64:
                    textGenerator.print(TextFormat.unsignedToString(((Long) obj).longValue()));
                    return;
                case STRING:
                    textGenerator.print("\"");
                    textGenerator.print(this.escapeNonAscii ? TextFormat.escapeText((String) obj) : TextFormat.escapeDoubleQuotesAndBackslashes((String) obj));
                    textGenerator.print("\"");
                    return;
                case BYTES:
                    textGenerator.print("\"");
                    if (obj instanceof ByteString) {
                        textGenerator.print(TextFormat.escapeBytes((ByteString) obj));
                    } else {
                        textGenerator.print(TextFormat.escapeBytes((byte[]) obj));
                    }
                    textGenerator.print("\"");
                    return;
                case ENUM:
                    textGenerator.print(((EnumValueDescriptor) obj).getName());
                    return;
                case MESSAGE:
                case GROUP:
                    print((Message) obj, textGenerator);
                    return;
                default:
                    return;
            }
        }

        private void printSingleField(FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) {
            if (fieldDescriptor.isExtension()) {
                textGenerator.print("[");
                if (fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) {
                    textGenerator.print(fieldDescriptor.getMessageType().getFullName());
                } else {
                    textGenerator.print(fieldDescriptor.getFullName());
                }
                textGenerator.print("]");
            } else if (fieldDescriptor.getType() == Type.GROUP) {
                textGenerator.print(fieldDescriptor.getMessageType().getName());
            } else {
                textGenerator.print(fieldDescriptor.getName());
            }
            if (fieldDescriptor.getJavaType() != JavaType.MESSAGE) {
                textGenerator.print(": ");
            } else if (this.singleLineMode) {
                textGenerator.print(" { ");
            } else {
                textGenerator.print(" {\n");
                textGenerator.indent();
            }
            printFieldValue(fieldDescriptor, obj, textGenerator);
            if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                if (this.singleLineMode) {
                    textGenerator.print("} ");
                    return;
                }
                textGenerator.outdent();
                textGenerator.print("}\n");
            } else if (this.singleLineMode) {
                textGenerator.print(Constants.SPACE);
            } else {
                textGenerator.print("\n");
            }
        }

        private void printUnknownField(int i, int i2, List list, TextGenerator textGenerator) {
            for (Object next : list) {
                textGenerator.print(String.valueOf(i));
                textGenerator.print(": ");
                TextFormat.printUnknownFieldValue(i2, next, textGenerator);
                textGenerator.print(this.singleLineMode ? Constants.SPACE : "\n");
            }
        }

        private void printUnknownFields(UnknownFieldSet unknownFieldSet, TextGenerator textGenerator) {
            for (Entry entry : unknownFieldSet.asMap().entrySet()) {
                int intValue = ((Integer) entry.getKey()).intValue();
                Field field = (Field) entry.getValue();
                printUnknownField(intValue, 0, field.getVarintList(), textGenerator);
                printUnknownField(intValue, 5, field.getFixed32List(), textGenerator);
                printUnknownField(intValue, 1, field.getFixed64List(), textGenerator);
                printUnknownField(intValue, 2, field.getLengthDelimitedList(), textGenerator);
                for (UnknownFieldSet unknownFieldSet2 : field.getGroupList()) {
                    textGenerator.print(((Integer) entry.getKey()).toString());
                    if (this.singleLineMode) {
                        textGenerator.print(" { ");
                    } else {
                        textGenerator.print(" {\n");
                        textGenerator.indent();
                    }
                    printUnknownFields(unknownFieldSet2, textGenerator);
                    if (this.singleLineMode) {
                        textGenerator.print("} ");
                    } else {
                        textGenerator.outdent();
                        textGenerator.print("}\n");
                    }
                }
            }
        }

        private Printer setEscapeNonAscii(boolean z) {
            this.escapeNonAscii = z;
            return this;
        }

        private Printer setSingleLineMode(boolean z) {
            this.singleLineMode = z;
            return this;
        }
    }

    final class TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;

        private TextGenerator(Appendable appendable) {
            this.indent = new StringBuilder();
            this.atStartOfLine = true;
            this.output = appendable;
        }

        private void write(CharSequence charSequence) {
            if (charSequence.length() != 0) {
                if (this.atStartOfLine) {
                    this.atStartOfLine = false;
                    this.output.append(this.indent);
                }
                this.output.append(charSequence);
            }
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.indent.delete(length - 2, length);
        }

        public void print(CharSequence charSequence) {
            int i = 0;
            int length = charSequence.length();
            for (int i2 = 0; i2 < length; i2++) {
                if (charSequence.charAt(i2) == '\n') {
                    write(charSequence.subSequence(i, i2 + 1));
                    i = i2 + 1;
                    this.atStartOfLine = true;
                }
            }
            write(charSequence.subSequence(i, length));
        }
    }

    final class Tokenizer {
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private int column;
        private String currentToken;
        private int line;
        private final Matcher matcher;
        private int pos;
        private int previousColumn;
        private int previousLine;
        private final CharSequence text;

        private Tokenizer(CharSequence charSequence) {
            this.pos = 0;
            this.line = 0;
            this.column = 0;
            this.previousLine = 0;
            this.previousColumn = 0;
            this.text = charSequence;
            this.matcher = WHITESPACE.matcher(charSequence);
            skipWhitespace();
            nextToken();
        }

        private void consumeByteString(List list) {
            char c = '\u0000';
            if (this.currentToken.length() > 0) {
                c = this.currentToken.charAt(0);
            }
            if (c != '\"' && c != '\'') {
                throw parseException("Expected string.");
            } else if (this.currentToken.length() < 2 || this.currentToken.charAt(this.currentToken.length() - 1) != c) {
                throw parseException("String missing ending quote.");
            } else {
                try {
                    ByteString unescapeBytes = TextFormat.unescapeBytes(this.currentToken.substring(1, this.currentToken.length() - 1));
                    nextToken();
                    list.add(unescapeBytes);
                } catch (InvalidEscapeSequenceException e) {
                    throw parseException(e.getMessage());
                }
            }
        }

        private ParseException floatParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse number: " + numberFormatException.getMessage());
        }

        private ParseException integerParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse integer: " + numberFormatException.getMessage());
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
            }
        }

        public boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public void consume(String str) {
            if (!tryConsume(str)) {
                throw parseException("Expected \"" + str + "\".");
            }
        }

        public boolean consumeBoolean() {
            if (this.currentToken.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE) || this.currentToken.equals("t") || this.currentToken.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                nextToken();
                return true;
            } else if (this.currentToken.equals("false") || this.currentToken.equals("f") || this.currentToken.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                nextToken();
                return false;
            } else {
                throw parseException("Expected \"true\" or \"false\".");
            }
        }

        public ByteString consumeByteString() {
            Iterable arrayList = new ArrayList();
            consumeByteString(arrayList);
            while (true) {
                if (!this.currentToken.startsWith("'") && !this.currentToken.startsWith("\"")) {
                    return ByteString.copyFrom(arrayList);
                }
                consumeByteString(arrayList);
            }
        }

        public double consumeDouble() {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith("-");
                nextToken();
                return startsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else if (this.currentToken.equalsIgnoreCase("nan")) {
                nextToken();
                return Double.NaN;
            } else {
                try {
                    double parseDouble = Double.parseDouble(this.currentToken);
                    nextToken();
                    return parseDouble;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public float consumeFloat() {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith("-");
                nextToken();
                return startsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            } else if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                nextToken();
                return YogaConstants.UNDEFINED;
            } else {
                try {
                    float parseFloat = Float.parseFloat(this.currentToken);
                    nextToken();
                    return parseFloat;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public String consumeIdentifier() {
            for (int i = 0; i < this.currentToken.length(); i++) {
                char charAt = this.currentToken.charAt(i);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && !(('0' <= charAt && charAt <= '9') || charAt == '_' || charAt == '.'))) {
                    throw parseException("Expected identifier. Found '" + this.currentToken + "'");
                }
            }
            String str = this.currentToken;
            nextToken();
            return str;
        }

        public int consumeInt32() {
            try {
                int parseInt32 = TextFormat.parseInt32(this.currentToken);
                nextToken();
                return parseInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeInt64() {
            try {
                long parseInt64 = TextFormat.parseInt64(this.currentToken);
                nextToken();
                return parseInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public String consumeString() {
            return consumeByteString().toStringUtf8();
        }

        public int consumeUInt32() {
            try {
                int parseUInt32 = TextFormat.parseUInt32(this.currentToken);
                nextToken();
                return parseUInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeUInt64() {
            try {
                long parseUInt64 = TextFormat.parseUInt64(this.currentToken);
                nextToken();
                return parseUInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public boolean lookingAt(String str) {
            return this.currentToken.equals(str);
        }

        public boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char charAt = this.currentToken.charAt(0);
            return ('0' <= charAt && charAt <= '9') || charAt == '-' || charAt == '+';
        }

        public void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    this.line++;
                    this.column = 0;
                } else {
                    this.column++;
                }
                this.pos++;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
                return;
            }
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
                this.currentToken = this.matcher.group();
                this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
            } else {
                this.currentToken = String.valueOf(this.text.charAt(this.pos));
                this.matcher.region(this.pos + 1, this.matcher.regionEnd());
            }
            skipWhitespace();
        }

        public ParseException parseException(String str) {
            return new ParseException(this.line + 1, this.column + 1, str);
        }

        public ParseException parseExceptionPreviousToken(String str) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, str);
        }

        public boolean tryConsume(String str) {
            if (!this.currentToken.equals(str)) {
                return false;
            }
            nextToken();
            return true;
        }

        public boolean tryConsumeDouble() {
            try {
                consumeDouble();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeFloat() {
            try {
                consumeFloat();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeIdentifier() {
            try {
                consumeIdentifier();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeInt64() {
            try {
                consumeInt64();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeString() {
            try {
                consumeString();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean tryConsumeUInt64() {
            try {
                consumeUInt64();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
    }

    private TextFormat() {
    }

    private static int digitValue(byte b) {
        return ((byte) 48 > b || b > (byte) 57) ? ((byte) 97 > b || b > (byte) 122) ? (b - 65) + 10 : (b - 97) + 10 : b - 48;
    }

    static String escapeBytes(ByteString byteString) {
        return escapeBytes(new C20071(byteString));
    }

    private static String escapeBytes(ByteSequence byteSequence) {
        StringBuilder stringBuilder = new StringBuilder(byteSequence.size());
        for (int i = 0; i < byteSequence.size(); i++) {
            byte byteAt = byteSequence.byteAt(i);
            switch (byteAt) {
                case (byte) 7:
                    stringBuilder.append("\\a");
                    break;
                case (byte) 8:
                    stringBuilder.append("\\b");
                    break;
                case (byte) 9:
                    stringBuilder.append("\\t");
                    break;
                case (byte) 10:
                    stringBuilder.append("\\n");
                    break;
                case (byte) 11:
                    stringBuilder.append("\\v");
                    break;
                case (byte) 12:
                    stringBuilder.append("\\f");
                    break;
                case (byte) 13:
                    stringBuilder.append("\\r");
                    break;
                case (byte) 34:
                    stringBuilder.append("\\\"");
                    break;
                case (byte) 39:
                    stringBuilder.append("\\'");
                    break;
                case (byte) 92:
                    stringBuilder.append("\\\\");
                    break;
                default:
                    if (byteAt < (byte) 32) {
                        stringBuilder.append('\\');
                        stringBuilder.append((char) (((byteAt >>> 6) & 3) + 48));
                        stringBuilder.append((char) (((byteAt >>> 3) & 7) + 48));
                        stringBuilder.append((char) ((byteAt & 7) + 48));
                        break;
                    }
                    stringBuilder.append((char) byteAt);
                    break;
            }
        }
        return stringBuilder.toString();
    }

    static String escapeBytes(byte[] bArr) {
        return escapeBytes(new C20082(bArr));
    }

    public static String escapeDoubleQuotesAndBackslashes(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    public static Parser getParser() {
        return PARSER;
    }

    private static boolean isHex(byte b) {
        return ((byte) 48 <= b && b <= (byte) 57) || (((byte) 97 <= b && b <= (byte) 102) || ((byte) 65 <= b && b <= (byte) 70));
    }

    private static boolean isOctal(byte b) {
        return (byte) 48 <= b && b <= (byte) 55;
    }

    public static void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Builder builder) {
        PARSER.merge(charSequence, extensionRegistry, builder);
    }

    public static void merge(CharSequence charSequence, Builder builder) {
        PARSER.merge(charSequence, builder);
    }

    public static void merge(Readable readable, ExtensionRegistry extensionRegistry, Builder builder) {
        PARSER.merge(readable, extensionRegistry, builder);
    }

    public static void merge(Readable readable, Builder builder) {
        PARSER.merge(readable, builder);
    }

    static int parseInt32(String str) {
        return (int) parseInteger(str, true, false);
    }

    static long parseInt64(String str) {
        return parseInteger(str, true, true);
    }

    private static long parseInteger(String str, boolean z, boolean z2) {
        int i;
        int i2 = 0;
        int i3 = 1;
        if (!str.startsWith("-", 0)) {
            i3 = 0;
        } else if (z) {
            i2 = 1;
        } else {
            throw new NumberFormatException("Number must be positive: " + str);
        }
        if (str.startsWith("0x", i3)) {
            i = i3 + 2;
            i3 = 16;
        } else if (str.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_NO, i3)) {
            i = i3;
            i3 = 8;
        } else {
            i = i3;
            i3 = 10;
        }
        String substring = str.substring(i);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i3);
            long j = i2 != 0 ? -parseLong : parseLong;
            if (z2) {
                return j;
            }
            if (z) {
                if (j <= 2147483647L && j >= -2147483648L) {
                    return j;
                }
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
            } else if (j < iHealthDevicesManager.DISCOVERY_BG5 && j >= 0) {
                return j;
            } else {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
            }
        }
        BigInteger bigInteger = new BigInteger(substring, i3);
        BigInteger negate = i2 != 0 ? bigInteger.negate() : bigInteger;
        if (z2) {
            if (z) {
                if (negate.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: " + str);
                }
            } else if (negate.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + str);
            }
        } else if (z) {
            if (negate.bitLength() > 31) {
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
            }
        } else if (negate.bitLength() > 32) {
            throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
        }
        return negate.longValue();
    }

    static int parseUInt32(String str) {
        return (int) parseInteger(str, false, false);
    }

    static long parseUInt64(String str) {
        return parseInteger(str, false, true);
    }

    public static void print(MessageOrBuilder messageOrBuilder, Appendable appendable) {
        DEFAULT_PRINTER.print(messageOrBuilder, new TextGenerator(appendable));
    }

    public static void print(UnknownFieldSet unknownFieldSet, Appendable appendable) {
        DEFAULT_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(appendable));
    }

    public static void printField(FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) {
        DEFAULT_PRINTER.printField(fieldDescriptor, obj, new TextGenerator(appendable));
    }

    public static String printFieldToString(FieldDescriptor fieldDescriptor, Object obj) {
        try {
            Appendable stringBuilder = new StringBuilder();
            printField(fieldDescriptor, obj, stringBuilder);
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printFieldValue(FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) {
        DEFAULT_PRINTER.printFieldValue(fieldDescriptor, obj, new TextGenerator(appendable));
    }

    public static String printToString(MessageOrBuilder messageOrBuilder) {
        try {
            Appendable stringBuilder = new StringBuilder();
            print(messageOrBuilder, stringBuilder);
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(UnknownFieldSet unknownFieldSet) {
        try {
            Appendable stringBuilder = new StringBuilder();
            print(unknownFieldSet, stringBuilder);
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(MessageOrBuilder messageOrBuilder) {
        try {
            Appendable stringBuilder = new StringBuilder();
            UNICODE_PRINTER.print(messageOrBuilder, new TextGenerator(stringBuilder));
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(UnknownFieldSet unknownFieldSet) {
        try {
            Appendable stringBuilder = new StringBuilder();
            UNICODE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(stringBuilder));
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printUnicode(MessageOrBuilder messageOrBuilder, Appendable appendable) {
        UNICODE_PRINTER.print(messageOrBuilder, new TextGenerator(appendable));
    }

    public static void printUnicode(UnknownFieldSet unknownFieldSet, Appendable appendable) {
        UNICODE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(appendable));
    }

    private static void printUnknownFieldValue(int i, Object obj, TextGenerator textGenerator) {
        switch (WireFormat.getTagWireType(i)) {
            case 0:
                textGenerator.print(unsignedToString(((Long) obj).longValue()));
                return;
            case 1:
                textGenerator.print(String.format((Locale) null, "0x%016x", new Object[]{(Long) obj}));
                return;
            case 2:
                textGenerator.print("\"");
                textGenerator.print(escapeBytes((ByteString) obj));
                textGenerator.print("\"");
                return;
            case 3:
                DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet) obj, textGenerator);
                return;
            case 5:
                textGenerator.print(String.format((Locale) null, "0x%08x", new Object[]{(Integer) obj}));
                return;
            default:
                throw new IllegalArgumentException("Bad tag: " + i);
        }
    }

    public static void printUnknownFieldValue(int i, Object obj, Appendable appendable) {
        printUnknownFieldValue(i, obj, new TextGenerator(appendable));
    }

    public static String shortDebugString(MessageOrBuilder messageOrBuilder) {
        try {
            Appendable stringBuilder = new StringBuilder();
            SINGLE_LINE_PRINTER.print(messageOrBuilder, new TextGenerator(stringBuilder));
            return stringBuilder.toString().trim();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(UnknownFieldSet unknownFieldSet) {
        try {
            Appendable stringBuilder = new StringBuilder();
            SINGLE_LINE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(stringBuilder));
            return stringBuilder.toString().trim();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    static ByteString unescapeBytes(CharSequence charSequence) {
        ByteString copyFromUtf8 = ByteString.copyFromUtf8(charSequence.toString());
        byte[] bArr = new byte[copyFromUtf8.size()];
        int i = 0;
        int i2;
        for (int i3 = 0; i3 < copyFromUtf8.size(); i3 = i2 + 1) {
            byte byteAt = copyFromUtf8.byteAt(i3);
            if (byteAt != (byte) 92) {
                i2 = i + 1;
                bArr[i] = byteAt;
                i = i2;
                i2 = i3;
            } else if (i3 + 1 < copyFromUtf8.size()) {
                i2 = i3 + 1;
                byte byteAt2 = copyFromUtf8.byteAt(i2);
                int i4;
                if (isOctal(byteAt2)) {
                    i3 = digitValue(byteAt2);
                    if (i2 + 1 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i2 + 1))) {
                        i2++;
                        i3 = (i3 * 8) + digitValue(copyFromUtf8.byteAt(i2));
                    }
                    if (i2 + 1 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i2 + 1))) {
                        i2++;
                        i3 = (i3 * 8) + digitValue(copyFromUtf8.byteAt(i2));
                    }
                    i4 = i + 1;
                    bArr[i] = (byte) i3;
                    i = i4;
                } else {
                    switch (byteAt2) {
                        case (byte) 34:
                            i3 = i + 1;
                            bArr[i] = (byte) 34;
                            i = i3;
                            break;
                        case (byte) 39:
                            i3 = i + 1;
                            bArr[i] = (byte) 39;
                            i = i3;
                            break;
                        case (byte) 92:
                            i3 = i + 1;
                            bArr[i] = (byte) 92;
                            i = i3;
                            break;
                        case (byte) 97:
                            i3 = i + 1;
                            bArr[i] = (byte) 7;
                            i = i3;
                            break;
                        case (byte) 98:
                            i3 = i + 1;
                            bArr[i] = (byte) 8;
                            i = i3;
                            break;
                        case (byte) 102:
                            i3 = i + 1;
                            bArr[i] = Ascii.FF;
                            i = i3;
                            break;
                        case (byte) 110:
                            i3 = i + 1;
                            bArr[i] = (byte) 10;
                            i = i3;
                            break;
                        case (byte) 114:
                            i3 = i + 1;
                            bArr[i] = (byte) 13;
                            i = i3;
                            break;
                        case (byte) 116:
                            i3 = i + 1;
                            bArr[i] = (byte) 9;
                            i = i3;
                            break;
                        case (byte) 118:
                            i3 = i + 1;
                            bArr[i] = Ascii.VT;
                            i = i3;
                            break;
                        case (byte) 120:
                            if (i2 + 1 < copyFromUtf8.size() && isHex(copyFromUtf8.byteAt(i2 + 1))) {
                                i2++;
                                i3 = digitValue(copyFromUtf8.byteAt(i2));
                                if (i2 + 1 >= copyFromUtf8.size() || !isHex(copyFromUtf8.byteAt(i2 + 1))) {
                                    i4 = i3;
                                } else {
                                    i2++;
                                    i4 = (i3 * 16) + digitValue(copyFromUtf8.byteAt(i2));
                                }
                                i3 = i + 1;
                                bArr[i] = (byte) i4;
                                i = i3;
                                break;
                            }
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                        default:
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + ((char) byteAt2) + '\'');
                    }
                }
            } else {
                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
            }
        }
        return ByteString.copyFrom(bArr, 0, i);
    }

    static String unescapeText(String str) {
        return unescapeBytes(str).toStringUtf8();
    }

    public static String unsignedToString(int i) {
        return i >= 0 ? Integer.toString(i) : Long.toString(4294967295L & ((long) i));
    }

    public static String unsignedToString(long j) {
        return j >= 0 ? Long.toString(j) : BigInteger.valueOf(Long.MAX_VALUE & j).setBit(63).toString();
    }
}
