package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.AbstractMessage;
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

public final class C2204q extends Builder implements C2201r {
    private int f2228a;
    private C2198k f2229b;
    private SingleFieldBuilder f2230c;
    private int f2231d;
    private int f2232e;
    private int f2233f;
    private int f2234g;
    private int f2235h;
    private Object f2236i;
    private int f2237j;
    private Object f2238k;
    private Object f2239l;
    private int f2240m;
    private int f2241n;
    private Object f2242o;

    private C2204q() {
        this.f2229b = C2198k.m1355a();
        this.f2236i = "";
        this.f2238k = "";
        this.f2239l = "";
        this.f2242o = "";
        m1441k();
    }

    private C2204q(BuilderParent builderParent) {
        super(builderParent);
        this.f2229b = C2198k.m1355a();
        this.f2236i = "";
        this.f2238k = "";
        this.f2239l = "";
        this.f2242o = "";
        m1441k();
    }

    private void m1441k() {
        if (C2202o.alwaysUseFieldBuilders) {
            m1443m();
        }
    }

    private static C2204q m1442l() {
        return new C2204q();
    }

    private SingleFieldBuilder m1443m() {
        if (this.f2230c == null) {
            this.f2230c = new SingleFieldBuilder(this.f2229b, getParentForChildren(), isClean());
            this.f2229b = null;
        }
        return this.f2230c;
    }

    public C2204q m1444a() {
        super.clear();
        if (this.f2230c == null) {
            this.f2229b = C2198k.m1355a();
        } else {
            this.f2230c.clear();
        }
        this.f2228a &= -2;
        this.f2231d = 0;
        this.f2228a &= -3;
        this.f2232e = 0;
        this.f2228a &= -5;
        this.f2233f = 0;
        this.f2228a &= -9;
        this.f2234g = 0;
        this.f2228a &= -17;
        this.f2235h = 0;
        this.f2228a &= -33;
        this.f2236i = "";
        this.f2228a &= -65;
        this.f2237j = 0;
        this.f2228a &= -129;
        this.f2238k = "";
        this.f2228a &= -257;
        this.f2239l = "";
        this.f2228a &= -513;
        this.f2240m = 0;
        this.f2228a &= -1025;
        this.f2241n = 0;
        this.f2228a &= -2049;
        this.f2242o = "";
        this.f2228a &= -4097;
        return this;
    }

    public C2204q m1445a(int i) {
        this.f2228a |= 2;
        this.f2231d = i;
        onChanged();
        return this;
    }

    public C2204q m1446a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        C2202o c2202o;
        Throwable th;
        try {
            c2202o = (C2202o) C2202o.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (c2202o != null) {
                m1449a(c2202o);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            c2202o = (C2202o) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException;
        } catch (Throwable th2) {
            th = th2;
        }
        if (c2202o != null) {
            m1449a(c2202o);
        }
        throw th;
    }

    public C2204q m1447a(Message message) {
        if (message instanceof C2202o) {
            return m1449a((C2202o) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public C2204q m1448a(C2198k c2198k) {
        if (this.f2230c != null) {
            this.f2230c.setMessage(c2198k);
        } else if (c2198k == null) {
            throw new NullPointerException();
        } else {
            this.f2229b = c2198k;
            onChanged();
        }
        this.f2228a |= 1;
        return this;
    }

    public C2204q m1449a(C2202o c2202o) {
        if (c2202o != C2202o.m1389a()) {
            if (c2202o.m1415c()) {
                m1452b(c2202o.m1416d());
            }
            if (c2202o.m1417e()) {
                m1445a(c2202o.m1418f());
            }
            if (c2202o.m1419g()) {
                m1451b(c2202o.m1420h());
            }
            if (c2202o.m1421i()) {
                m1454c(c2202o.m1422j());
            }
            if (c2202o.m1423k()) {
                m1456d(c2202o.m1424l());
            }
            if (c2202o.m1425m()) {
                m1458e(c2202o.m1426n());
            }
            if (c2202o.m1427o()) {
                this.f2228a |= 64;
                this.f2236i = c2202o.f2219j;
                onChanged();
            }
            if (c2202o.m1429q()) {
                m1459f(c2202o.m1430r());
            }
            if (c2202o.m1431s()) {
                this.f2228a |= 256;
                this.f2238k = c2202o.f2221l;
                onChanged();
            }
            if (c2202o.m1433u()) {
                this.f2228a |= 512;
                this.f2239l = c2202o.f2222m;
                onChanged();
            }
            if (c2202o.m1435w()) {
                m1462g(c2202o.m1436x());
            }
            if (c2202o.m1437y()) {
                m1463h(c2202o.m1438z());
            }
            if (c2202o.m1409A()) {
                this.f2228a |= 4096;
                this.f2242o = c2202o.f2225p;
                onChanged();
            }
            mergeUnknownFields(c2202o.getUnknownFields());
        }
        return this;
    }

    public C2204q m1450b() {
        return C2204q.m1442l().m1449a(m1457e());
    }

    public C2204q m1451b(int i) {
        this.f2228a |= 4;
        this.f2232e = i;
        onChanged();
        return this;
    }

    public C2204q m1452b(C2198k c2198k) {
        if (this.f2230c == null) {
            if ((this.f2228a & 1) != 1 || this.f2229b == C2198k.m1355a()) {
                this.f2229b = c2198k;
            } else {
                this.f2229b = C2198k.m1356a(this.f2229b).m1380a(c2198k).m1384e();
            }
            onChanged();
        } else {
            this.f2230c.mergeFrom(c2198k);
        }
        this.f2228a |= 1;
        return this;
    }

    public /* synthetic */ Message build() {
        return m1455d();
    }

    public /* synthetic */ MessageLite m2291build() {
        return m1455d();
    }

    public /* synthetic */ Message buildPartial() {
        return m1457e();
    }

    public /* synthetic */ MessageLite m2292buildPartial() {
        return m1457e();
    }

    public C2202o m1453c() {
        return C2202o.m1389a();
    }

    public C2204q m1454c(int i) {
        this.f2228a |= 8;
        this.f2233f = i;
        onChanged();
        return this;
    }

    public /* synthetic */ Builder clear() {
        return m1444a();
    }

    public /* synthetic */ Message.Builder m2293clear() {
        return m1444a();
    }

    public /* synthetic */ MessageLite.Builder m2294clear() {
        return m1444a();
    }

    public /* synthetic */ Builder clone() {
        return m1450b();
    }

    public /* synthetic */ Message.Builder m2295clone() {
        return m1450b();
    }

    public /* synthetic */ MessageLite.Builder m2296clone() {
        return m1450b();
    }

    public C2202o m1455d() {
        Object e = m1457e();
        if (e.isInitialized()) {
            return e;
        }
        throw C2204q.newUninitializedMessageException(e);
    }

    public C2204q m1456d(int i) {
        this.f2228a |= 16;
        this.f2234g = i;
        onChanged();
        return this;
    }

    public C2202o m1457e() {
        C2202o c2202o = new C2202o((Builder) this);
        int i = this.f2228a;
        int i2 = 0;
        if ((i & 1) == 1) {
            i2 = 1;
        }
        if (this.f2230c == null) {
            c2202o.f2213d = this.f2229b;
        } else {
            c2202o.f2213d = (C2198k) this.f2230c.build();
        }
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c2202o.f2214e = this.f2231d;
        if ((i & 4) == 4) {
            i2 |= 4;
        }
        c2202o.f2215f = this.f2232e;
        if ((i & 8) == 8) {
            i2 |= 8;
        }
        c2202o.f2216g = this.f2233f;
        if ((i & 16) == 16) {
            i2 |= 16;
        }
        c2202o.f2217h = this.f2234g;
        if ((i & 32) == 32) {
            i2 |= 32;
        }
        c2202o.f2218i = this.f2235h;
        if ((i & 64) == 64) {
            i2 |= 64;
        }
        c2202o.f2219j = this.f2236i;
        if ((i & 128) == 128) {
            i2 |= 128;
        }
        c2202o.f2220k = this.f2237j;
        if ((i & 256) == 256) {
            i2 |= 256;
        }
        c2202o.f2221l = this.f2238k;
        if ((i & 512) == 512) {
            i2 |= 512;
        }
        c2202o.f2222m = this.f2239l;
        if ((i & 1024) == 1024) {
            i2 |= 1024;
        }
        c2202o.f2223n = this.f2240m;
        if ((i & 2048) == 2048) {
            i2 |= 2048;
        }
        c2202o.f2224o = this.f2241n;
        if ((i & 4096) == 4096) {
            i2 |= 4096;
        }
        c2202o.f2225p = this.f2242o;
        c2202o.f2212c = i2;
        onBuilt();
        return c2202o;
    }

    public C2204q m1458e(int i) {
        this.f2228a |= 32;
        this.f2235h = i;
        onChanged();
        return this;
    }

    public C2204q m1459f(int i) {
        this.f2228a |= 128;
        this.f2237j = i;
        onChanged();
        return this;
    }

    public boolean m1460f() {
        return (this.f2228a & 1) == 1;
    }

    public C2198k m1461g() {
        return this.f2230c == null ? this.f2229b : (C2198k) this.f2230c.getMessage();
    }

    public C2204q m1462g(int i) {
        this.f2228a |= 1024;
        this.f2240m = i;
        onChanged();
        return this;
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1453c();
    }

    public /* synthetic */ MessageLite m2297getDefaultInstanceForType() {
        return m1453c();
    }

    public Descriptor getDescriptorForType() {
        return C2187a.f2151m;
    }

    public C2204q m1463h(int i) {
        this.f2228a |= 2048;
        this.f2241n = i;
        onChanged();
        return this;
    }

    public boolean m1464h() {
        return (this.f2228a & 2) == 2;
    }

    public boolean m1465i() {
        return (this.f2228a & 4) == 4;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2152n.ensureFieldAccessorsInitialized(C2202o.class, C2204q.class);
    }

    public final boolean isInitialized() {
        return m1460f() && m1464h() && m1465i() && m1461g().isInitialized();
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1446a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(Message message) {
        return m1447a(message);
    }

    public /* synthetic */ Message.Builder m2298mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1446a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Message.Builder m2299mergeFrom(Message message) {
        return m1447a(message);
    }

    public /* synthetic */ MessageLite.Builder m2300mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1446a(codedInputStream, extensionRegistryLite);
    }
}
