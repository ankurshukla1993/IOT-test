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

public final class C2190c extends GeneratedMessage implements C2189f {
    public static final int AESSESSIONKEY_FIELD_NUMBER = 2;
    public static final int BASERESPONSE_FIELD_NUMBER = 1;
    public static Parser PARSER = new C2191d();
    private static final C2190c f2185a = new C2190c(true);
    private final UnknownFieldSet f2186b;
    private int f2187c;
    private C2198k f2188d;
    private ByteString f2189e;
    private byte f2190f;
    private int f2191g;

    static {
        f2185a.m1306k();
    }

    private C2190c(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.f2190f = (byte) -1;
        this.f2191g = -1;
        m1306k();
        Builder newBuilder = UnknownFieldSet.newBuilder();
        Object obj = null;
        while (obj == null) {
            try {
                int readTag = codedInputStream.readTag();
                switch (readTag) {
                    case 0:
                        obj = 1;
                        break;
                    case 10:
                        C2200m i = (this.f2187c & 1) == 1 ? this.f2188d.m1371i() : null;
                        this.f2188d = (C2198k) codedInputStream.readMessage(C2198k.PARSER, extensionRegistryLite);
                        if (i != null) {
                            i.m1380a(this.f2188d);
                            this.f2188d = i.m1384e();
                        }
                        this.f2187c |= 1;
                        break;
                    case 18:
                        this.f2187c |= 2;
                        this.f2189e = codedInputStream.readBytes();
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
                this.f2186b = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        this.f2186b = newBuilder.build();
        makeExtensionsImmutable();
    }

    private C2190c(GeneratedMessage.Builder builder) {
        super(builder);
        this.f2190f = (byte) -1;
        this.f2191g = -1;
        this.f2186b = builder.getUnknownFields();
    }

    private C2190c(boolean z) {
        this.f2190f = (byte) -1;
        this.f2191g = -1;
        this.f2186b = UnknownFieldSet.getDefaultInstance();
    }

    public static C2190c m1299a() {
        return f2185a;
    }

    public static C2192e m1300a(C2190c c2190c) {
        return C2190c.m1304g().m1324a(c2190c);
    }

    public static C2192e m1304g() {
        return C2192e.m1318k();
    }

    private void m1306k() {
        this.f2188d = C2198k.m1355a();
        this.f2189e = ByteString.EMPTY;
    }

    protected C2192e m1307a(BuilderParent builderParent) {
        return new C2192e(builderParent);
    }

    public C2190c m1308b() {
        return f2185a;
    }

    public boolean m1309c() {
        return (this.f2187c & 1) == 1;
    }

    public C2198k m1310d() {
        return this.f2188d;
    }

    public boolean m1311e() {
        return (this.f2187c & 2) == 2;
    }

    public ByteString m1312f() {
        return this.f2189e;
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1308b();
    }

    public /* synthetic */ MessageLite m2249getDefaultInstanceForType() {
        return m1308b();
    }

    public Parser getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.f2191g;
        if (i != -1) {
            return i;
        }
        i = 0;
        if ((this.f2187c & 1) == 1) {
            i = CodedOutputStream.computeMessageSize(1, this.f2188d) + 0;
        }
        if ((this.f2187c & 2) == 2) {
            i += CodedOutputStream.computeBytesSize(2, this.f2189e);
        }
        i += getUnknownFields().getSerializedSize();
        this.f2191g = i;
        return i;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.f2186b;
    }

    public C2192e m1313h() {
        return C2190c.m1304g();
    }

    public C2192e m1314i() {
        return C2190c.m1300a(this);
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2148j.ensureFieldAccessorsInitialized(C2190c.class, C2192e.class);
    }

    public final boolean isInitialized() {
        byte b = this.f2190f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            if (!m1309c()) {
                this.f2190f = (byte) 0;
                return false;
            } else if (!m1311e()) {
                this.f2190f = (byte) 0;
                return false;
            } else if (m1310d().isInitialized()) {
                this.f2190f = (byte) 1;
                return true;
            } else {
                this.f2190f = (byte) 0;
                return false;
            }
        }
    }

    public /* synthetic */ Message.Builder newBuilderForType() {
        return m1313h();
    }

    protected /* synthetic */ Message.Builder newBuilderForType(BuilderParent builderParent) {
        return m1307a(builderParent);
    }

    public /* synthetic */ MessageLite.Builder m2250newBuilderForType() {
        return m1313h();
    }

    public /* synthetic */ Message.Builder toBuilder() {
        return m1314i();
    }

    public /* synthetic */ MessageLite.Builder m2251toBuilder() {
        return m1314i();
    }

    protected Object writeReplace() {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        getSerializedSize();
        if ((this.f2187c & 1) == 1) {
            codedOutputStream.writeMessage(1, this.f2188d);
        }
        if ((this.f2187c & 2) == 2) {
            codedOutputStream.writeBytes(2, this.f2189e);
        }
        getUnknownFields().writeTo(codedOutputStream);
    }
}
