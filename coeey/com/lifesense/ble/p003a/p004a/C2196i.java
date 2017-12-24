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

public final class C2196i extends Builder implements C2193j {
    private C2196i() {
        m1345g();
    }

    private C2196i(BuilderParent builderParent) {
        super(builderParent);
        m1345g();
    }

    private void m1345g() {
        C2194g.alwaysUseFieldBuilders;
    }

    private static C2196i m1346h() {
        return new C2196i();
    }

    public C2196i m1347a() {
        super.clear();
        return this;
    }

    public C2196i m1348a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        C2194g c2194g;
        Throwable th;
        try {
            c2194g = (C2194g) C2194g.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            if (c2194g != null) {
                m1350a(c2194g);
            }
            return this;
        } catch (InvalidProtocolBufferException e) {
            InvalidProtocolBufferException invalidProtocolBufferException = e;
            c2194g = (C2194g) invalidProtocolBufferException.getUnfinishedMessage();
            throw invalidProtocolBufferException;
        } catch (Throwable th2) {
            th = th2;
        }
        if (c2194g != null) {
            m1350a(c2194g);
        }
        throw th;
    }

    public C2196i m1349a(Message message) {
        if (message instanceof C2194g) {
            return m1350a((C2194g) message);
        }
        super.mergeFrom(message);
        return this;
    }

    public C2196i m1350a(C2194g c2194g) {
        if (c2194g != C2194g.m1334a()) {
            mergeUnknownFields(c2194g.getUnknownFields());
        }
        return this;
    }

    public C2196i m1351b() {
        return C2196i.m1346h().m1350a(m1354e());
    }

    public /* synthetic */ Message build() {
        return m1353d();
    }

    public /* synthetic */ MessageLite m2278build() {
        return m1353d();
    }

    public /* synthetic */ Message buildPartial() {
        return m1354e();
    }

    public /* synthetic */ MessageLite m2279buildPartial() {
        return m1354e();
    }

    public C2194g m1352c() {
        return C2194g.m1334a();
    }

    public /* synthetic */ Builder clear() {
        return m1347a();
    }

    public /* synthetic */ Message.Builder m2280clear() {
        return m1347a();
    }

    public /* synthetic */ MessageLite.Builder m2281clear() {
        return m1347a();
    }

    public /* synthetic */ Builder clone() {
        return m1351b();
    }

    public /* synthetic */ Message.Builder m2282clone() {
        return m1351b();
    }

    public /* synthetic */ MessageLite.Builder m2283clone() {
        return m1351b();
    }

    public C2194g m1353d() {
        Object e = m1354e();
        if (e.isInitialized()) {
            return e;
        }
        throw C2196i.newUninitializedMessageException(e);
    }

    public C2194g m1354e() {
        C2194g c2194g = new C2194g((Builder) this);
        onBuilt();
        return c2194g;
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1352c();
    }

    public /* synthetic */ MessageLite m2284getDefaultInstanceForType() {
        return m1352c();
    }

    public Descriptor getDescriptorForType() {
        return C2187a.f2139a;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2140b.ensureFieldAccessorsInitialized(C2194g.class, C2196i.class);
    }

    public final boolean isInitialized() {
        return true;
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1348a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ AbstractMessage.Builder mergeFrom(Message message) {
        return m1349a(message);
    }

    public /* synthetic */ Message.Builder m2285mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1348a(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Message.Builder m2286mergeFrom(Message message) {
        return m1349a(message);
    }

    public /* synthetic */ MessageLite.Builder m2287mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1348a(codedInputStream, extensionRegistryLite);
    }
}
