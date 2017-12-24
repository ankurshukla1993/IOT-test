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

public final class C2212y extends Builder implements C2209z {
    private int f2263a;
    private C2198k f2264b;
    private SingleFieldBuilder f2265c;
    private ByteString f2266d;

    private C2212y() {
        this.f2264b = C2198k.m1355a();
        this.f2266d = ByteString.EMPTY;
        m1522i();
    }

    private C2212y(BuilderParent builderParent) {
        super(builderParent);
        this.f2264b = C2198k.m1355a();
        this.f2266d = ByteString.EMPTY;
        m1522i();
    }

    private void m1522i() {
        if (C2210w.alwaysUseFieldBuilders) {
            m1524k();
        }
    }

    private static C2212y m1523j() {
        return new C2212y();
    }

    private SingleFieldBuilder m1524k() {
        if (this.f2265c == null) {
            this.f2265c = new SingleFieldBuilder(this.f2264b, getParentForChildren(), isClean());
            this.f2264b = null;
        }
        return this.f2265c;
    }

    public C2212y m1525a() {
        super.clear();
        if (this.f2265c == null) {
            this.f2264b = C2198k.m1355a();
        } else {
            this.f2265c.clear();
        }
        this.f2263a &= -2;
        this.f2266d = ByteString.EMPTY;
        this.f2263a &= -3;
        return this;
    }

    public C2212y m1526a(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.f2263a |= 2;
        this.f2266d = byteString;
        onChanged();
        return this;
    }

    public C2212y m1527a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        C2210w c2210w;
        Throwable th;
        try {
            c2210w = (C2210w) C2210w.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (c2210w != null) {
                m1530a(c2210w);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            c2210w = (C2210w) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException;
        } catch (Throwable th2) {
            th = th2;
        }
        if (c2210w != null) {
            m1530a(c2210w);
        }
        throw th;
    }

    public C2212y m1528a(Message message) {
        if (message instanceof C2210w) {
            return m1530a((C2210w) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public C2212y m1529a(C2198k c2198k) {
        if (this.f2265c != null) {
            this.f2265c.setMessage(c2198k);
        } else if (c2198k == null) {
            throw new NullPointerException();
        } else {
            this.f2264b = c2198k;
            onChanged();
        }
        this.f2263a |= 1;
        return this;
    }

    public C2212y m1530a(C2210w c2210w) {
        if (c2210w != C2210w.m1504a()) {
            if (c2210w.m1514c()) {
                m1532b(c2210w.m1515d());
            }
            if (c2210w.m1516e()) {
                m1526a(c2210w.m1517f());
            }
            mergeUnknownFields(c2210w.getUnknownFields());
        }
        return this;
    }

    public C2212y m1531b() {
        return C2212y.m1523j().m1530a(m1535e());
    }

    public C2212y m1532b(C2198k c2198k) {
        if (this.f2265c == null) {
            if ((this.f2263a & 1) != 1 || this.f2264b == C2198k.m1355a()) {
                this.f2264b = c2198k;
            } else {
                this.f2264b = C2198k.m1356a(this.f2264b).m1380a(c2198k).m1384e();
            }
            onChanged();
        } else {
            this.f2265c.mergeFrom(c2198k);
        }
        this.f2263a |= 1;
        return this;
    }

    public /* synthetic */ Message build() {
        return m1534d();
    }

    public /* synthetic */ MessageLite m2314build() {
        return m1534d();
    }

    public /* synthetic */ Message buildPartial() {
        return m1535e();
    }

    public /* synthetic */ MessageLite m2315buildPartial() {
        return m1535e();
    }

    public C2210w m1533c() {
        return C2210w.m1504a();
    }

    public /* synthetic */ Builder clear() {
        return m1525a();
    }

    public /* synthetic */ Message.Builder m2316clear() {
        return m1525a();
    }

    public /* synthetic */ MessageLite.Builder m2317clear() {
        return m1525a();
    }

    public /* synthetic */ Builder clone() {
        return m1531b();
    }

    public /* synthetic */ Message.Builder m2318clone() {
        return m1531b();
    }

    public /* synthetic */ MessageLite.Builder m2319clone() {
        return m1531b();
    }

    public C2210w m1534d() {
        Object e = m1535e();
        if (e.isInitialized()) {
            return e;
        }
        throw C2212y.newUninitializedMessageException(e);
    }

    public C2210w m1535e() {
        C2210w c2210w = new C2210w((Builder) this);
        int i = this.f2263a;
        int i2 = 0;
        if ((i & 1) == 1) {
            i2 = 1;
        }
        if (this.f2265c == null) {
            c2210w.f2259d = this.f2264b;
        } else {
            c2210w.f2259d = (C2198k) this.f2265c.build();
        }
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c2210w.f2260e = this.f2266d;
        c2210w.f2258c = i2;
        onBuilt();
        return c2210w;
    }

    public boolean m1536f() {
        return (this.f2263a & 1) == 1;
    }

    public C2198k m1537g() {
        return this.f2265c == null ? this.f2264b : (C2198k) this.f2265c.getMessage();
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1533c();
    }

    public /* synthetic */ MessageLite m2320getDefaultInstanceForType() {
        return m1533c();
    }

    public Descriptor getDescriptorForType() {
        return C2187a.f2155q;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2156r.ensureFieldAccessorsInitialized(C2210w.class, C2212y.class);
    }

    public final boolean isInitialized() {
        return m1536f() && m1537g().isInitialized();
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1527a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(Message message) {
        return m1528a(message);
    }

    public /* synthetic */ Message.Builder m2321mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1527a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Message.Builder m2322mergeFrom(Message message) {
        return m1528a(message);
    }

    public /* synthetic */ MessageLite.Builder m2323mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1527a(codedInputStream, extensionRegistryLite);
    }
}
