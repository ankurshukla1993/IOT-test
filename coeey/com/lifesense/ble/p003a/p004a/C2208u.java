package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.GeneratedMessage.BuilderParent;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.SingleFieldBuilder;

public final class C2208u extends Builder implements C2205v {
    private int f2251a;
    private C2194g f2252b;
    private SingleFieldBuilder f2253c;
    private ByteString f2254d;
    private int f2255e;

    private C2208u() {
        this.f2252b = C2194g.m1334a();
        this.f2254d = ByteString.EMPTY;
        m1488i();
    }

    private C2208u(BuilderParent builderParent) {
        super(builderParent);
        this.f2252b = C2194g.m1334a();
        this.f2254d = ByteString.EMPTY;
        m1488i();
    }

    private void m1488i() {
        if (C2206s.alwaysUseFieldBuilders) {
            m1490k();
        }
    }

    private static C2208u m1489j() {
        return new C2208u();
    }

    private SingleFieldBuilder m1490k() {
        if (this.f2253c == null) {
            this.f2253c = new SingleFieldBuilder(this.f2252b, getParentForChildren(), isClean());
            this.f2252b = null;
        }
        return this.f2253c;
    }

    public C2208u m1491a() {
        super.clear();
        if (this.f2253c == null) {
            this.f2252b = C2194g.m1334a();
        } else {
            this.f2253c.clear();
        }
        this.f2251a &= -2;
        this.f2254d = ByteString.EMPTY;
        this.f2251a &= -3;
        this.f2255e = 0;
        this.f2251a &= -5;
        return this;
    }

    public C2208u m1492a(int i) {
        this.f2251a |= 4;
        this.f2255e = i;
        onChanged();
        return this;
    }

    public C2208u m1493a(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.f2251a |= 2;
        this.f2254d = byteString;
        onChanged();
        return this;
    }

    public C2208u m1494a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        C2206s c2206s;
        Throwable th;
        try {
            c2206s = (C2206s) C2206s.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (c2206s != null) {
                m1497a(c2206s);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            c2206s = (C2206s) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException;
        } catch (Throwable th2) {
            th = th2;
        }
        if (c2206s != null) {
            m1497a(c2206s);
        }
        throw th;
    }

    public C2208u m1495a(Message message) {
        if (message instanceof C2206s) {
            return m1497a((C2206s) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public C2208u m1496a(C2194g c2194g) {
        if (this.f2253c == null) {
            if ((this.f2251a & 1) != 1 || this.f2252b == C2194g.m1334a()) {
                this.f2252b = c2194g;
            } else {
                this.f2252b = C2194g.m1335a(this.f2252b).m1350a(c2194g).m1354e();
            }
            onChanged();
        } else {
            this.f2253c.mergeFrom(c2194g);
        }
        this.f2251a |= 1;
        return this;
    }

    public C2208u m1497a(C2206s c2206s) {
        if (c2206s != C2206s.m1466a()) {
            if (c2206s.m1478c()) {
                m1496a(c2206s.m1479d());
            }
            if (c2206s.m1480e()) {
                m1493a(c2206s.m1481f());
            }
            if (c2206s.m1482g()) {
                m1492a(c2206s.m1483h());
            }
            mergeUnknownFields(c2206s.getUnknownFields());
        }
        return this;
    }

    public C2208u m1498b() {
        return C2208u.m1489j().m1497a(m1501e());
    }

    public /* synthetic */ Message build() {
        return m1500d();
    }

    public /* synthetic */ MessageLite m2301build() {
        return m1500d();
    }

    public /* synthetic */ Message buildPartial() {
        return m1501e();
    }

    public /* synthetic */ MessageLite m2303buildPartial() {
        return m1501e();
    }

    public C2206s m1499c() {
        return C2206s.m1466a();
    }

    public /* synthetic */ Builder clear() {
        return m1491a();
    }

    public /* synthetic */ Message.Builder m2304clear() {
        return m1491a();
    }

    public /* synthetic */ MessageLite.Builder m2305clear() {
        return m1491a();
    }

    public /* synthetic */ Builder clone() {
        return m1498b();
    }

    public /* synthetic */ Message.Builder m2306clone() {
        return m1498b();
    }

    public /* synthetic */ MessageLite.Builder m2308clone() {
        return m1498b();
    }

    public C2206s m1500d() {
        Object e = m1501e();
        if (e.isInitialized()) {
            return e;
        }
        throw C2208u.newUninitializedMessageException(e);
    }

    public C2206s m1501e() {
        C2206s c2206s = new C2206s((Builder) this);
        int i = this.f2251a;
        int i2 = 0;
        if ((i & 1) == 1) {
            i2 = 1;
        }
        if (this.f2253c == null) {
            c2206s.f2246d = this.f2252b;
        } else {
            c2206s.f2246d = (C2194g) this.f2253c.build();
        }
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c2206s.f2247e = this.f2254d;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        c2206s.f2248f = this.f2255e;
        c2206s.f2245c = i2;
        onBuilt();
        return c2206s;
    }

    public boolean m1502f() {
        return (this.f2251a & 1) == 1;
    }

    public boolean m1503g() {
        return (this.f2251a & 2) == 2;
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1499c();
    }

    public /* synthetic */ MessageLite m2310getDefaultInstanceForType() {
        return m1499c();
    }

    public Descriptor getDescriptorForType() {
        return C2187a.f2153o;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2154p.ensureFieldAccessorsInitialized(C2206s.class, C2208u.class);
    }

    public final boolean isInitialized() {
        return m1502f() && m1503g();
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1494a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(Message message) {
        return m1495a(message);
    }

    public /* synthetic */ Message.Builder m2311mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1494a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Message.Builder m2312mergeFrom(Message message) {
        return m1495a(message);
    }

    public /* synthetic */ MessageLite.Builder m2313mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1494a(codedInputStream, extensionRegistryLite);
    }
}
