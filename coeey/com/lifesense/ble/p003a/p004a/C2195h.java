package com.lifesense.ble.p003a.p004a;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;

class C2195h extends AbstractParser {
    C2195h() {
    }

    public C2194g m1343a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new C2194g(codedInputStream, extensionRegistryLite);
    }

    public /* synthetic */ Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return m1343a(codedInputStream, extensionRegistryLite);
    }
}
