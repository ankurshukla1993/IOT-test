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

public final class C2202o extends GeneratedMessage implements C2201r {
    public static final int AUTOSYNCMAXDURATIONSECOND_FIELD_NUMBER = 6;
    public static final int BASERESPONSE_FIELD_NUMBER = 1;
    public static final int CHALLEANGEANSWER_FIELD_NUMBER = 4;
    public static final int INITSCENCE_FIELD_NUMBER = 5;
    public static final int MODEL_FIELD_NUMBER = 13;
    public static final int OS_FIELD_NUMBER = 14;
    public static Parser PARSER = new C2203p();
    public static final int PLATFORMTYPE_FIELD_NUMBER = 12;
    public static final int TIMESTRING_FIELD_NUMBER = 17;
    public static final int TIMEZONE_FIELD_NUMBER = 16;
    public static final int TIME_FIELD_NUMBER = 15;
    public static final int USERIDHIGH_FIELD_NUMBER = 2;
    public static final int USERIDLOW_FIELD_NUMBER = 3;
    public static final int USERNICKNAME_FIELD_NUMBER = 11;
    private static final C2202o f2210a = new C2202o(true);
    private final UnknownFieldSet f2211b;
    private int f2212c;
    private C2198k f2213d;
    private int f2214e;
    private int f2215f;
    private int f2216g;
    private int f2217h;
    private int f2218i;
    private Object f2219j;
    private int f2220k;
    private Object f2221l;
    private Object f2222m;
    private int f2223n;
    private int f2224o;
    private Object f2225p;
    private byte f2226q;
    private int f2227r;

    static {
        f2210a.m1388G();
    }

    private C2202o(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.f2226q = (byte) -1;
        this.f2227r = -1;
        m1388G();
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
                        C2200m i = (this.f2212c & 1) == 1 ? this.f2213d.m1371i() : null;
                        this.f2213d = (C2198k) codedInputStream.readMessage(C2198k.PARSER, extensionRegistryLite);
                        if (i != null) {
                            i.m1380a(this.f2213d);
                            this.f2213d = i.m1384e();
                        }
                        this.f2212c |= 1;
                        break;
                    case 16:
                        this.f2212c |= 2;
                        this.f2214e = codedInputStream.readUInt32();
                        break;
                    case 24:
                        this.f2212c |= 4;
                        this.f2215f = codedInputStream.readUInt32();
                        break;
                    case 32:
                        this.f2212c |= 8;
                        this.f2216g = codedInputStream.readUInt32();
                        break;
                    case 40:
                        this.f2212c |= 16;
                        this.f2217h = codedInputStream.readInt32();
                        break;
                    case 48:
                        this.f2212c |= 32;
                        this.f2218i = codedInputStream.readUInt32();
                        break;
                    case 90:
                        this.f2212c |= 64;
                        this.f2219j = codedInputStream.readBytes();
                        break;
                    case 96:
                        this.f2212c |= 128;
                        this.f2220k = codedInputStream.readInt32();
                        break;
                    case 106:
                        this.f2212c |= 256;
                        this.f2221l = codedInputStream.readBytes();
                        break;
                    case 114:
                        this.f2212c |= 512;
                        this.f2222m = codedInputStream.readBytes();
                        break;
                    case 120:
                        this.f2212c |= 1024;
                        this.f2223n = codedInputStream.readInt32();
                        break;
                    case 128:
                        this.f2212c |= 2048;
                        this.f2224o = codedInputStream.readInt32();
                        break;
                    case 138:
                        this.f2212c |= 4096;
                        this.f2225p = codedInputStream.readBytes();
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
                this.f2211b = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        this.f2211b = newBuilder.build();
        makeExtensionsImmutable();
    }

    private C2202o(GeneratedMessage.Builder builder) {
        super(builder);
        this.f2226q = (byte) -1;
        this.f2227r = -1;
        this.f2211b = builder.getUnknownFields();
    }

    private C2202o(boolean z) {
        this.f2226q = (byte) -1;
        this.f2227r = -1;
        this.f2211b = UnknownFieldSet.getDefaultInstance();
    }

    public static C2204q m1386C() {
        return C2204q.m1442l();
    }

    private void m1388G() {
        this.f2213d = C2198k.m1355a();
        this.f2214e = 0;
        this.f2215f = 0;
        this.f2216g = 0;
        this.f2217h = 0;
        this.f2218i = 0;
        this.f2219j = "";
        this.f2220k = 0;
        this.f2221l = "";
        this.f2222m = "";
        this.f2223n = 0;
        this.f2224o = 0;
        this.f2225p = "";
    }

    public static C2202o m1389a() {
        return f2210a;
    }

    public static C2204q m1390a(C2202o c2202o) {
        return C2202o.m1386C().m1449a(c2202o);
    }

    public boolean m1409A() {
        return (this.f2212c & 4096) == 4096;
    }

    public ByteString m1410B() {
        Object obj = this.f2225p;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.f2225p = copyFromUtf8;
        return copyFromUtf8;
    }

    public C2204q m1411D() {
        return C2202o.m1386C();
    }

    public C2204q m1412E() {
        return C2202o.m1390a(this);
    }

    protected C2204q m1413a(BuilderParent builderParent) {
        return new C2204q(builderParent);
    }

    public C2202o m1414b() {
        return f2210a;
    }

    public boolean m1415c() {
        return (this.f2212c & 1) == 1;
    }

    public C2198k m1416d() {
        return this.f2213d;
    }

    public boolean m1417e() {
        return (this.f2212c & 2) == 2;
    }

    public int m1418f() {
        return this.f2214e;
    }

    public boolean m1419g() {
        return (this.f2212c & 4) == 4;
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1414b();
    }

    public /* synthetic */ MessageLite m2288getDefaultInstanceForType() {
        return m1414b();
    }

    public Parser getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.f2227r;
        if (i != -1) {
            return i;
        }
        i = 0;
        if ((this.f2212c & 1) == 1) {
            i = CodedOutputStream.computeMessageSize(1, this.f2213d) + 0;
        }
        if ((this.f2212c & 2) == 2) {
            i += CodedOutputStream.computeUInt32Size(2, this.f2214e);
        }
        if ((this.f2212c & 4) == 4) {
            i += CodedOutputStream.computeUInt32Size(3, this.f2215f);
        }
        if ((this.f2212c & 8) == 8) {
            i += CodedOutputStream.computeUInt32Size(4, this.f2216g);
        }
        if ((this.f2212c & 16) == 16) {
            i += CodedOutputStream.computeInt32Size(5, this.f2217h);
        }
        if ((this.f2212c & 32) == 32) {
            i += CodedOutputStream.computeUInt32Size(6, this.f2218i);
        }
        if ((this.f2212c & 64) == 64) {
            i += CodedOutputStream.computeBytesSize(11, m1428p());
        }
        if ((this.f2212c & 128) == 128) {
            i += CodedOutputStream.computeInt32Size(12, this.f2220k);
        }
        if ((this.f2212c & 256) == 256) {
            i += CodedOutputStream.computeBytesSize(13, m1432t());
        }
        if ((this.f2212c & 512) == 512) {
            i += CodedOutputStream.computeBytesSize(14, m1434v());
        }
        if ((this.f2212c & 1024) == 1024) {
            i += CodedOutputStream.computeInt32Size(15, this.f2223n);
        }
        if ((this.f2212c & 2048) == 2048) {
            i += CodedOutputStream.computeInt32Size(16, this.f2224o);
        }
        if ((this.f2212c & 4096) == 4096) {
            i += CodedOutputStream.computeBytesSize(17, m1410B());
        }
        i += getUnknownFields().getSerializedSize();
        this.f2227r = i;
        return i;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.f2211b;
    }

    public int m1420h() {
        return this.f2215f;
    }

    public boolean m1421i() {
        return (this.f2212c & 8) == 8;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2152n.ensureFieldAccessorsInitialized(C2202o.class, C2204q.class);
    }

    public final boolean isInitialized() {
        byte b = this.f2226q;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            if (!m1415c()) {
                this.f2226q = (byte) 0;
                return false;
            } else if (!m1417e()) {
                this.f2226q = (byte) 0;
                return false;
            } else if (!m1419g()) {
                this.f2226q = (byte) 0;
                return false;
            } else if (m1416d().isInitialized()) {
                this.f2226q = (byte) 1;
                return true;
            } else {
                this.f2226q = (byte) 0;
                return false;
            }
        }
    }

    public int m1422j() {
        return this.f2216g;
    }

    public boolean m1423k() {
        return (this.f2212c & 16) == 16;
    }

    public int m1424l() {
        return this.f2217h;
    }

    public boolean m1425m() {
        return (this.f2212c & 32) == 32;
    }

    public int m1426n() {
        return this.f2218i;
    }

    public /* synthetic */ Message.Builder newBuilderForType() {
        return m1411D();
    }

    protected /* synthetic */ Message.Builder newBuilderForType(BuilderParent builderParent) {
        return m1413a(builderParent);
    }

    public /* synthetic */ MessageLite.Builder m2289newBuilderForType() {
        return m1411D();
    }

    public boolean m1427o() {
        return (this.f2212c & 64) == 64;
    }

    public ByteString m1428p() {
        Object obj = this.f2219j;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.f2219j = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean m1429q() {
        return (this.f2212c & 128) == 128;
    }

    public int m1430r() {
        return this.f2220k;
    }

    public boolean m1431s() {
        return (this.f2212c & 256) == 256;
    }

    public ByteString m1432t() {
        Object obj = this.f2221l;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.f2221l = copyFromUtf8;
        return copyFromUtf8;
    }

    public /* synthetic */ Message.Builder toBuilder() {
        return m1412E();
    }

    public /* synthetic */ MessageLite.Builder m2290toBuilder() {
        return m1412E();
    }

    public boolean m1433u() {
        return (this.f2212c & 512) == 512;
    }

    public ByteString m1434v() {
        Object obj = this.f2222m;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.f2222m = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean m1435w() {
        return (this.f2212c & 1024) == 1024;
    }

    protected Object writeReplace() {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        getSerializedSize();
        if ((this.f2212c & 1) == 1) {
            codedOutputStream.writeMessage(1, this.f2213d);
        }
        if ((this.f2212c & 2) == 2) {
            codedOutputStream.writeUInt32(2, this.f2214e);
        }
        if ((this.f2212c & 4) == 4) {
            codedOutputStream.writeUInt32(3, this.f2215f);
        }
        if ((this.f2212c & 8) == 8) {
            codedOutputStream.writeUInt32(4, this.f2216g);
        }
        if ((this.f2212c & 16) == 16) {
            codedOutputStream.writeInt32(5, this.f2217h);
        }
        if ((this.f2212c & 32) == 32) {
            codedOutputStream.writeUInt32(6, this.f2218i);
        }
        if ((this.f2212c & 64) == 64) {
            codedOutputStream.writeBytes(11, m1428p());
        }
        if ((this.f2212c & 128) == 128) {
            codedOutputStream.writeInt32(12, this.f2220k);
        }
        if ((this.f2212c & 256) == 256) {
            codedOutputStream.writeBytes(13, m1432t());
        }
        if ((this.f2212c & 512) == 512) {
            codedOutputStream.writeBytes(14, m1434v());
        }
        if ((this.f2212c & 1024) == 1024) {
            codedOutputStream.writeInt32(15, this.f2223n);
        }
        if ((this.f2212c & 2048) == 2048) {
            codedOutputStream.writeInt32(16, this.f2224o);
        }
        if ((this.f2212c & 4096) == 4096) {
            codedOutputStream.writeBytes(17, m1410B());
        }
        getUnknownFields().writeTo(codedOutputStream);
    }

    public int m1436x() {
        return this.f2223n;
    }

    public boolean m1437y() {
        return (this.f2212c & 2048) == 2048;
    }

    public int m1438z() {
        return this.f2224o;
    }
}
