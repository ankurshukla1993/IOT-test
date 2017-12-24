package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;

class C2199l extends AbstractParser {
    C2199l() {
    }

    public C2198k m1372a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new C2198k(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1372a(codedInputStream, extensionRegistryLite);
    }
}
