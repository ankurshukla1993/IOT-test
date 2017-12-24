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

public final class C2206s extends GeneratedMessage implements C2205v {
    public static final int BASEREQUEST_FIELD_NUMBER = 1;
    public static final int DATA_FIELD_NUMBER = 2;
    public static Parser PARSER = new C2207t();
    public static final int TYPE_FIELD_NUMBER = 3;
    private static final C2206s f2243a = new C2206s(true);
    private final UnknownFieldSet f2244b;
    private int f2245c;
    private C2194g f2246d;
    private ByteString f2247e;
    private int f2248f;
    private byte f2249g;
    private int f2250h;

    static {
        f2243a.m1475m();
    }

    private C2206s(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.f2249g = (byte) -1;
        this.f2250h = -1;
        m1475m();
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
                        C2196i e = (this.f2245c & 1) == 1 ? this.f2246d.m1342e() : null;
                        this.f2246d = (C2194g) codedInputStream.readMessage(C2194g.PARSER, extensionRegistryLite);
                        if (e != null) {
                            e.m1350a(this.f2246d);
                            this.f2246d = e.m1354e();
                        }
                        this.f2245c |= 1;
                        break;
                    case 18:
                        this.f2245c |= 2;
                        this.f2247e = codedInputStream.readBytes();
                        break;
                    case 24:
                        this.f2245c |= 4;
                        this.f2248f = codedInputStream.readUInt32();
                        break;
                    default:
                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            obj = 1;
                            break;
                        }
                        break;
                }
            } catch (InvalidProtocolBufferException e2) {
                throw e2.setUnfinishedMessage(this);
            } catch (IOException e3) {
                throw new InvalidProtocolBufferException(e3.getMessage()).setUnfinishedMessage(this);
            } catch (Throwable th) {
                this.f2244b = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        this.f2244b = newBuilder.build();
        makeExtensionsImmutable();
    }

    private C2206s(GeneratedMessage.Builder builder) {
        super(builder);
        this.f2249g = (byte) -1;
        this.f2250h = -1;
        this.f2244b = builder.getUnknownFields();
    }

    private C2206s(boolean z) {
        this.f2249g = (byte) -1;
        this.f2250h = -1;
        this.f2244b = UnknownFieldSet.getDefaultInstance();
    }

    public static C2206s m1466a() {
        return f2243a;
    }

    public static C2206s m1467a(byte[] bArr) {
        return (C2206s) PARSER.parseFrom(bArr);
    }

    public static C2208u m1468a(C2206s c2206s) {
        return C2206s.m1473i().m1497a(c2206s);
    }

    public static C2208u m1473i() {
        return C2208u.m1489j();
    }

    private void m1475m() {
        this.f2246d = C2194g.m1334a();
        this.f2247e = ByteString.EMPTY;
        this.f2248f = 0;
    }

    protected C2208u m1476a(BuilderParent builderParent) {
        return new C2208u(builderParent);
    }

    public C2206s m1477b() {
        return f2243a;
    }

    public boolean m1478c() {
        return (this.f2245c & 1) == 1;
    }

    public C2194g m1479d() {
        return this.f2246d;
    }

    public boolean m1480e() {
        return (this.f2245c & 2) == 2;
    }

    public ByteString m1481f() {
        return this.f2247e;
    }

    public boolean m1482g() {
        return (this.f2245c & 4) == 4;
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1477b();
    }

    public /* synthetic */ MessageLite m2302getDefaultInstanceForType() {
        return m1477b();
    }

    public Parser getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.f2250h;
        if (i != -1) {
            return i;
        }
        i = 0;
        if ((this.f2245c & 1) == 1) {
            i = CodedOutputStream.computeMessageSize(1, this.f2246d) + 0;
        }
        if ((this.f2245c & 2) == 2) {
            i += CodedOutputStream.computeBytesSize(2, this.f2247e);
        }
        if ((this.f2245c & 4) == 4) {
            i += CodedOutputStream.computeUInt32Size(3, this.f2248f);
        }
        i += getUnknownFields().getSerializedSize();
        this.f2250h = i;
        return i;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.f2244b;
    }

    public int m1483h() {
        return this.f2248f;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2154p.ensureFieldAccessorsInitialized(C2206s.class, C2208u.class);
    }

    public final boolean isInitialized() {
        byte b = this.f2249g;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            if (!m1478c()) {
                this.f2249g = (byte) 0;
                return false;
            } else if (m1480e()) {
                this.f2249g = (byte) 1;
                return true;
            } else {
                this.f2249g = (byte) 0;
                return false;
            }
        }
    }

    public C2208u m1484j() {
        return C2206s.m1473i();
    }

    public C2208u m1485k() {
        return C2206s.m1468a(this);
    }

    public /* synthetic */ Message.Builder newBuilderForType() {
        return m1484j();
    }

    protected /* synthetic */ Message.Builder newBuilderForType(BuilderParent builderParent) {
        return m1476a(builderParent);
    }

    public /* synthetic */ MessageLite.Builder m2307newBuilderForType() {
        return m1484j();
    }

    public /* synthetic */ Message.Builder toBuilder() {
        return m1485k();
    }

    public /* synthetic */ MessageLite.Builder m2309toBuilder() {
        return m1485k();
    }

    protected Object writeReplace() {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        getSerializedSize();
        if ((this.f2245c & 1) == 1) {
            codedOutputStream.writeMessage(1, this.f2246d);
        }
        if ((this.f2245c & 2) == 2) {
            codedOutputStream.writeBytes(2, this.f2247e);
        }
        if ((this.f2245c & 4) == 4) {
            codedOutputStream.writeUInt32(3, this.f2248f);
        }
        getUnknownFields().writeTo(codedOutputStream);
    }
}
