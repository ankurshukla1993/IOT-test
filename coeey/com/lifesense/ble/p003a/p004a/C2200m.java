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

public final class C2200m extends Builder implements C2197n {
    private int f2207a;
    private int f2208b;
    private Object f2209c;

    private C2200m() {
        this.f2209c = "";
        m1374h();
    }

    private C2200m(BuilderParent builderParent) {
        super(builderParent);
        this.f2209c = "";
        m1374h();
    }

    private void m1374h() {
        C2198k.alwaysUseFieldBuilders;
    }

    private static C2200m m1375i() {
        return new C2200m();
    }

    public C2200m m1376a() {
        super.clear();
        this.f2208b = 0;
        this.f2207a &= -2;
        this.f2209c = "";
        this.f2207a &= -3;
        return this;
    }

    public C2200m m1377a(int i) {
        this.f2207a |= 1;
        this.f2208b = i;
        onChanged();
        return this;
    }

    public C2200m m1378a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        C2198k c2198k;
        Throwable th;
        try {
            c2198k = (C2198k) C2198k.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (c2198k != null) {
                m1380a(c2198k);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            c2198k = (C2198k) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException;
        } catch (Throwable th2) {
            th = th2;
        }
        if (c2198k != null) {
            m1380a(c2198k);
        }
        throw th;
    }

    public C2200m m1379a(Message message) {
        if (message instanceof C2198k) {
            return m1380a((C2198k) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public C2200m m1380a(C2198k c2198k) {
        if (c2198k != C2198k.m1355a()) {
            if (c2198k.m1366c()) {
                m1377a(c2198k.m1367d());
            }
            if (c2198k.m1368e()) {
                this.f2207a |= 2;
                this.f2209c = c2198k.f2204e;
                onChanged();
            }
            mergeUnknownFields(c2198k.getUnknownFields());
        }
        return this;
    }

    public C2200m m1381b() {
        return C2200m.m1375i().m1380a(m1384e());
    }

    public /* synthetic */ Message build() {
        return m1383d();
    }

    public /* synthetic */ MessageLite m2265build() {
        return m1383d();
    }

    public /* synthetic */ Message buildPartial() {
        return m1384e();
    }

    public /* synthetic */ MessageLite m2266buildPartial() {
        return m1384e();
    }

    public C2198k m1382c() {
        return C2198k.m1355a();
    }

    public /* synthetic */ Builder clear() {
        return m1376a();
    }

    public /* synthetic */ Message.Builder m2267clear() {
        return m1376a();
    }

    public /* synthetic */ MessageLite.Builder m2268clear() {
        return m1376a();
    }

    public /* synthetic */ Builder clone() {
        return m1381b();
    }

    public /* synthetic */ Message.Builder m2269clone() {
        return m1381b();
    }

    public /* synthetic */ MessageLite.Builder m2270clone() {
        return m1381b();
    }

    public C2198k m1383d() {
        Object e = m1384e();
        if (e.isInitialized()) {
            return e;
        }
        throw C2200m.newUninitializedMessageException(e);
    }

    public C2198k m1384e() {
        int i = 1;
        C2198k c2198k = new C2198k((Builder) this);
        int i2 = this.f2207a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        c2198k.f2203d = this.f2208b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        c2198k.f2204e = this.f2209c;
        c2198k.f2202c = i;
        onBuilt();
        return c2198k;
    }

    public boolean m1385f() {
        return (this.f2207a & 1) == 1;
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1382c();
    }

    public /* synthetic */ MessageLite m2271getDefaultInstanceForType() {
        return m1382c();
    }

    public Descriptor getDescriptorForType() {
        return C2187a.f2141c;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2142d.ensureFieldAccessorsInitialized(C2198k.class, C2200m.class);
    }

    public final boolean isInitialized() {
        return m1385f();
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1378a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(Message message) {
        return m1379a(message);
    }

    public /* synthetic */ Message.Builder m2272mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1378a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Message.Builder m2273mergeFrom(Message message) {
        return m1379a(message);
    }

    public /* synthetic */ MessageLite.Builder m2274mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1378a(codedInputStream, extensionRegistryLite);
    }
}
