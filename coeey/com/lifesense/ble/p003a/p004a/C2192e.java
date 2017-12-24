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

public final class C2192e extends Builder implements C2189f {
    private int f2192a;
    private C2198k f2193b;
    private SingleFieldBuilder f2194c;
    private ByteString f2195d;

    private C2192e() {
        this.f2193b = C2198k.m1355a();
        this.f2195d = ByteString.EMPTY;
        m1317j();
    }

    private C2192e(BuilderParent builderParent) {
        super(builderParent);
        this.f2193b = C2198k.m1355a();
        this.f2195d = ByteString.EMPTY;
        m1317j();
    }

    private void m1317j() {
        if (C2190c.alwaysUseFieldBuilders) {
            m1319l();
        }
    }

    private static C2192e m1318k() {
        return new C2192e();
    }

    private SingleFieldBuilder m1319l() {
        if (this.f2194c == null) {
            this.f2194c = new SingleFieldBuilder(this.f2193b, getParentForChildren(), isClean());
            this.f2193b = null;
        }
        return this.f2194c;
    }

    public C2192e m1320a() {
        super.clear();
        if (this.f2194c == null) {
            this.f2193b = C2198k.m1355a();
        } else {
            this.f2194c.clear();
        }
        this.f2192a &= -2;
        this.f2195d = ByteString.EMPTY;
        this.f2192a &= -3;
        return this;
    }

    public C2192e m1321a(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.f2192a |= 2;
        this.f2195d = byteString;
        onChanged();
        return this;
    }

    public C2192e m1322a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        C2190c c2190c;
        Throwable th;
        try {
            c2190c = (C2190c) C2190c.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (c2190c != null) {
                m1324a(c2190c);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            c2190c = (C2190c) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException;
        } catch (Throwable th2) {
            th = th2;
        }
        if (c2190c != null) {
            m1324a(c2190c);
        }
        throw th;
    }

    public C2192e m1323a(Message message) {
        if (message instanceof C2190c) {
            return m1324a((C2190c) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public C2192e m1324a(C2190c c2190c) {
        if (c2190c != C2190c.m1299a()) {
            if (c2190c.m1309c()) {
                m1327b(c2190c.m1310d());
            }
            if (c2190c.m1311e()) {
                m1321a(c2190c.m1312f());
            }
            mergeUnknownFields(c2190c.getUnknownFields());
        }
        return this;
    }

    public C2192e m1325a(C2198k c2198k) {
        if (this.f2194c != null) {
            this.f2194c.setMessage(c2198k);
        } else if (c2198k == null) {
            throw new NullPointerException();
        } else {
            this.f2193b = c2198k;
            onChanged();
        }
        this.f2192a |= 1;
        return this;
    }

    public C2192e m1326b() {
        return C2192e.m1318k().m1324a(m1330e());
    }

    public C2192e m1327b(C2198k c2198k) {
        if (this.f2194c == null) {
            if ((this.f2192a & 1) != 1 || this.f2193b == C2198k.m1355a()) {
                this.f2193b = c2198k;
            } else {
                this.f2193b = C2198k.m1356a(this.f2193b).m1380a(c2198k).m1384e();
            }
            onChanged();
        } else {
            this.f2194c.mergeFrom(c2198k);
        }
        this.f2192a |= 1;
        return this;
    }

    public /* synthetic */ Message build() {
        return m1329d();
    }

    public /* synthetic */ MessageLite m2252build() {
        return m1329d();
    }

    public /* synthetic */ Message buildPartial() {
        return m1330e();
    }

    public /* synthetic */ MessageLite m2253buildPartial() {
        return m1330e();
    }

    public C2190c m1328c() {
        return C2190c.m1299a();
    }

    public /* synthetic */ Builder clear() {
        return m1320a();
    }

    public /* synthetic */ Message.Builder m2254clear() {
        return m1320a();
    }

    public /* synthetic */ MessageLite.Builder m2255clear() {
        return m1320a();
    }

    public /* synthetic */ Builder clone() {
        return m1326b();
    }

    public /* synthetic */ Message.Builder m2256clone() {
        return m1326b();
    }

    public /* synthetic */ MessageLite.Builder m2257clone() {
        return m1326b();
    }

    public C2190c m1329d() {
        Object e = m1330e();
        if (e.isInitialized()) {
            return e;
        }
        throw C2192e.newUninitializedMessageException(e);
    }

    public C2190c m1330e() {
        C2190c c2190c = new C2190c((Builder) this);
        int i = this.f2192a;
        int i2 = 0;
        if ((i & 1) == 1) {
            i2 = 1;
        }
        if (this.f2194c == null) {
            c2190c.f2188d = this.f2193b;
        } else {
            c2190c.f2188d = (C2198k) this.f2194c.build();
        }
        if ((i & 2) == 2) {
            i2 |= 2;
        }
        c2190c.f2189e = this.f2195d;
        c2190c.f2187c = i2;
        onBuilt();
        return c2190c;
    }

    public boolean m1331f() {
        return (this.f2192a & 1) == 1;
    }

    public C2198k m1332g() {
        return this.f2194c == null ? this.f2193b : (C2198k) this.f2194c.getMessage();
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1328c();
    }

    public /* synthetic */ MessageLite m2258getDefaultInstanceForType() {
        return m1328c();
    }

    public Descriptor getDescriptorForType() {
        return C2187a.f2147i;
    }

    public boolean m1333h() {
        return (this.f2192a & 2) == 2;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2148j.ensureFieldAccessorsInitialized(C2190c.class, C2192e.class);
    }

    public final boolean isInitialized() {
        return m1331f() && m1333h() && m1332g().isInitialized();
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1322a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(Message message) {
        return m1323a(message);
    }

    public /* synthetic */ Message.Builder m2259mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1322a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Message.Builder m2260mergeFrom(Message message) {
        return m1323a(message);
    }

    public /* synthetic */ MessageLite.Builder m2261mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1322a(codedInputStream, extensionRegistryLite);
    }
}
