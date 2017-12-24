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

public final class C2210w extends GeneratedMessage implements C2209z {
    public static final int BASERESPONSE_FIELD_NUMBER = 1;
    public static final int DATA_FIELD_NUMBER = 2;
    public static Parser PARSER = new C2211x();
    private static final C2210w f2256a = new C2210w(true);
    private final UnknownFieldSet f2257b;
    private int f2258c;
    private C2198k f2259d;
    private ByteString f2260e;
    private byte f2261f;
    private int f2262g;

    static {
        f2256a.m1511k();
    }

    private C2210w(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.f2261f = (byte) -1;
        this.f2262g = -1;
        m1511k();
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
                        C2200m i = (this.f2258c & 1) == 1 ? this.f2259d.m1371i() : null;
                        this.f2259d = (C2198k) codedInputStream.readMessage(C2198k.PARSER, extensionRegistryLite);
                        if (i != null) {
                            i.m1380a(this.f2259d);
                            this.f2259d = i.m1384e();
                        }
                        this.f2258c |= 1;
                        break;
                    case 18:
                        this.f2258c |= 2;
                        this.f2260e = codedInputStream.readBytes();
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
                this.f2257b = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        this.f2257b = newBuilder.build();
        makeExtensionsImmutable();
    }

    private C2210w(GeneratedMessage.Builder builder) {
        super(builder);
        this.f2261f = (byte) -1;
        this.f2262g = -1;
        this.f2257b = builder.getUnknownFields();
    }

    private C2210w(boolean z) {
        this.f2261f = (byte) -1;
        this.f2262g = -1;
        this.f2257b = UnknownFieldSet.getDefaultInstance();
    }

    public static C2210w m1504a() {
        return f2256a;
    }

    public static C2212y m1505a(C2210w c2210w) {
        return C2210w.m1509g().m1530a(c2210w);
    }

    public static C2212y m1509g() {
        return C2212y.m1523j();
    }

    private void m1511k() {
        this.f2259d = C2198k.m1355a();
        this.f2260e = ByteString.EMPTY;
    }

    protected C2212y m1512a(BuilderParent builderParent) {
        return new C2212y(builderParent);
    }

    public C2210w m1513b() {
        return f2256a;
    }

    public boolean m1514c() {
        return (this.f2258c & 1) == 1;
    }

    public C2198k m1515d() {
        return this.f2259d;
    }

    public boolean m1516e() {
        return (this.f2258c & 2) == 2;
    }

    public ByteString m1517f() {
        return this.f2260e;
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1513b();
    }

    public /* synthetic */ MessageLite m2324getDefaultInstanceForType() {
        return m1513b();
    }

    public Parser getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.f2262g;
        if (i != -1) {
            return i;
        }
        i = 0;
        if ((this.f2258c & 1) == 1) {
            i = CodedOutputStream.computeMessageSize(1, this.f2259d) + 0;
        }
        if ((this.f2258c & 2) == 2) {
            i += CodedOutputStream.computeBytesSize(2, this.f2260e);
        }
        i += getUnknownFields().getSerializedSize();
        this.f2262g = i;
        return i;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.f2257b;
    }

    public C2212y m1518h() {
        return C2210w.m1509g();
    }

    public C2212y m1519i() {
        return C2210w.m1505a(this);
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2156r.ensureFieldAccessorsInitialized(C2210w.class, C2212y.class);
    }

    public final boolean isInitialized() {
        byte b = this.f2261f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            if (!m1514c()) {
                this.f2261f = (byte) 0;
                return false;
            } else if (m1515d().isInitialized()) {
                this.f2261f = (byte) 1;
                return true;
            } else {
                this.f2261f = (byte) 0;
                return false;
            }
        }
    }

    public /* synthetic */ Message.Builder newBuilderForType() {
        return m1518h();
    }

    protected /* synthetic */ Message.Builder newBuilderForType(BuilderParent builderParent) {
        return m1512a(builderParent);
    }

    public /* synthetic */ MessageLite.Builder m2325newBuilderForType() {
        return m1518h();
    }

    public /* synthetic */ Message.Builder toBuilder() {
        return m1519i();
    }

    public /* synthetic */ MessageLite.Builder m2326toBuilder() {
        return m1519i();
    }

    protected Object writeReplace() {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        getSerializedSize();
        if ((this.f2258c & 1) == 1) {
            codedOutputStream.writeMessage(1, this.f2259d);
        }
        if ((this.f2258c & 2) == 2) {
            codedOutputStream.writeBytes(2, this.f2260e);
        }
        getUnknownFields().writeTo(codedOutputStream);
    }
}
