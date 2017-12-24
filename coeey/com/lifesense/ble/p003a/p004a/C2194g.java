package com.lifesense.ble.p003a.p004a;

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

public final class C2194g extends GeneratedMessage implements C2193j {
    public static Parser PARSER = new C2195h();
    private static final C2194g f2196a = new C2194g(true);
    private final UnknownFieldSet f2197b;
    private byte f2198c;
    private int f2199d;

    static {
        f2196a.m1338g();
    }

    private C2194g(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        this.f2198c = (byte) -1;
        this.f2199d = -1;
        m1338g();
        Builder newBuilder = UnknownFieldSet.newBuilder();
        Object obj = null;
        while (obj == null) {
            try {
                int readTag = codedInputStream.readTag();
                switch (readTag) {
                    case 0:
                        obj = 1;
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
                this.f2197b = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
        this.f2197b = newBuilder.build();
        makeExtensionsImmutable();
    }

    private C2194g(GeneratedMessage.Builder builder) {
        super(builder);
        this.f2198c = (byte) -1;
        this.f2199d = -1;
        this.f2197b = builder.getUnknownFields();
    }

    private C2194g(boolean z) {
        this.f2198c = (byte) -1;
        this.f2199d = -1;
        this.f2197b = UnknownFieldSet.getDefaultInstance();
    }

    public static C2194g m1334a() {
        return f2196a;
    }

    public static C2196i m1335a(C2194g c2194g) {
        return C2194g.m1336c().m1350a(c2194g);
    }

    public static C2196i m1336c() {
        return C2196i.m1346h();
    }

    private void m1338g() {
    }

    protected C2196i m1339a(BuilderParent builderParent) {
        return new C2196i(builderParent);
    }

    public C2194g m1340b() {
        return f2196a;
    }

    public C2196i m1341d() {
        return C2194g.m1336c();
    }

    public C2196i m1342e() {
        return C2194g.m1335a(this);
    }

    public /* synthetic */ Message getDefaultInstanceForType() {
        return m1340b();
    }

    public /* synthetic */ MessageLite m2275getDefaultInstanceForType() {
        return m1340b();
    }

    public Parser getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.f2199d;
        if (i != -1) {
            return i;
        }
        i = getUnknownFields().getSerializedSize() + 0;
        this.f2199d = i;
        return i;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.f2197b;
    }

    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return C2187a.f2140b.ensureFieldAccessorsInitialized(C2194g.class, C2196i.class);
    }

    public final boolean isInitialized() {
        byte b = this.f2198c;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f2198c = (byte) 1;
            return true;
        }
    }

    public /* synthetic */ Message.Builder newBuilderForType() {
        return m1341d();
    }

    protected /* synthetic */ Message.Builder newBuilderForType(BuilderParent builderParent) {
        return m1339a(builderParent);
    }

    public /* synthetic */ MessageLite.Builder m2276newBuilderForType() {
        return m1341d();
    }

    public /* synthetic */ Message.Builder toBuilder() {
        return m1342e();
    }

    public /* synthetic */ MessageLite.Builder m2277toBuilder() {
        return m1342e();
    }

    protected Object writeReplace() {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        getSerializedSize();
        getUnknownFields().writeTo(codedOutputStream);
    }
}
