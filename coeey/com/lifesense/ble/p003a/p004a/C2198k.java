package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.BuilderParent;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.UnknownFieldSet.Builder;
import java.io.IOException;

public final class C2198k extends GeneratedMessage implements C2197n {
    public static final int ERRCODE_FIELD_NUMBER = 1;
    public static final int ERRMSG_FIELD_NUMBER = 2;
    public static Parser PARSER = new C2199l();
    private static final C2198k f2200a = new C2198k(true);
    private final UnknownFieldSet f2201b;
    private int f2202c;
    private int f2203d;
    private Object f2204e;
    private byte f2205f;
    private int f2206g;

    static {
        f2200a.m1363k();
    }

    private C2198k(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.f2205f = (byte) -1;
        this.f2206g = -1;
        m1363k();
        Builder newBuilder = UnknownFieldSet.newBuilder();
        Object obj = null;
        while (obj == null) {
            try {
                int readTag = codedInputStream.readTag();
                switch (readTag) {
                    case 0:
                        obj = 1;
                        break;
                    case 8:
                        this.f2202c |= 1;
                        this.f2203d = codedInputStream.readInt32();
                        break;
                    case 18:
                        this.f2202c |= 2;
                        this.f2204e = codedInputStream.readBytes();
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
                this.f2201b = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        this.f2201b = newBuilder.build();
        makeExtensionsImmutable();
    }

    private C2198k(GeneratedMessage.Builder builder) {
        super(builder);
        this.f2205f = (byte) -1;
        this.f2206g = -1;
        this.f2201b = builder.getUnknownFields();
    }

    private C2198k(boolean z) {
        this.f2205f = (byte) -1;
        this.f2206g = -1;
        this.f2201b = UnknownFieldSet.getDefaultInstance();
    }

    public static C2198k m1355a() {
        return f2200a;
    }

    public static C2200m m1356a(C2198k c2198k) {
        return C2198k.m1361g().m1380a(c2198k);
    }

    public static C2200m m1361g() {
        return C2200m.m1375i();
    }

    private void m1363k() {
        this.f2203d = 0;
        this.f2204e = "";
    }

    protected C2200m m1364a(BuilderParent builderParent) {
        return new C2200m(builderParent);
    }

    public C2198k m1365b() {
        return f2200a;
    }

    public boolean m1366c() {
        return (this.f2202c & 1) == 1;
    }

    public int m1367d() {
        return this.f2203d;
    }

    public boolean m1368e() {
        return (this.f2202c & 2) == 2;
    }

    public ByteString m1369f() {
        Object obj = this.f2204e;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.f2204e = copyFromUtf8;
        return copyFromUtf8;
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1365b();
    }

    public /* synthetic */ MessageLite m2262getDefaultInstanceForType() {
        return m1365b();
    }

    public Parser getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.f2206g;
        if (i != -1) {
            return i;
        }
        i = 0;
        if ((this.f2202c & 1) == 1) {
            i = CodedOutputStream.computeInt32Size(1, this.f2203d) + 0;
        }
        if ((this.f2202c & 2) == 2) {
            i += CodedOutputStream.computeBytesSize(2, m1369f());
        }
        i += getUnknownFields().getSerializedSize();
        this.f2206g = i;
        return i;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.f2201b;
    }

    public C2200m m1370h() {
        return C2198k.m1361g();
    }

    public C2200m m1371i() {
        return C2198k.m1356a(this);
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2142d.ensureFieldAccessorsInitialized(C2198k.class, C2200m.class);
    }

    public final boolean isInitialized() {
        byte b = this.f2205f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            if (m1366c()) {
                this.f2205f = (byte) 1;
                return true;
            }
            this.f2205f = (byte) 0;
            return false;
        }
    }

    public /* synthetic */ Message.Builder newBuilderForType() {
        return m1370h();
    }

    protected /* synthetic */ Message.Builder newBuilderForType(BuilderParent builderParent) {
        return m1364a(builderParent);
    }

    public /* synthetic */ MessageLite.Builder m2263newBuilderForType() {
        return m1370h();
    }

    public /* synthetic */ Message.Builder toBuilder() {
        return m1371i();
    }

    public /* synthetic */ MessageLite.Builder m2264toBuilder() {
        return m1371i();
    }

    protected Object writeReplace() {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        getSerializedSize();
        if ((this.f2202c & 1) == 1) {
            codedOutputStream.writeInt32(1, this.f2203d);
        }
        if ((this.f2202c & 2) == 2) {
            codedOutputStream.writeBytes(2, m1369f());
        }
        getUnknownFields().writeTo(codedOutputStream);
    }
}
