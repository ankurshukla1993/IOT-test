package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;

class C2207t extends AbstractParser {
    C2207t() {
    }

    public C2206s m1486a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new C2206s(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1486a(codedInputStream, extensionRegistryLite);
    }
}
